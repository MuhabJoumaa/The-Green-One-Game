package the.green.one.game;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class TheGreenOneGame {

    static {
        File freeglut_dll = new File("freeglut.dll");
        if (!freeglut_dll.exists()) {
            freeglut_dll = new File("C:\\Windows\\System32\\freeglut.dll");
            if (!freeglut_dll.exists()) {
                freeglut_dll = new File("C:\\Windows\\SysWOW64\\freeglut.dll");
                if (!freeglut_dll.exists()) {
                    JOptionPane.showMessageDialog(null, "Failed To Find 'freeglut.dll' On Your Device");
                    System.exit(0);
                }
            }
        }
    }

    static {
        try {
            URL detect = new URL("https://www.google.com");
            URLConnection con = detect.openConnection();
            con.connect();
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Sorry, An Internet Connection Is Required To Start The Game", "Failure", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Sorry, An Internet Connection Is Required To Start The Game", "Failure", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    static {
        try {
            Desktop.getDesktop().open(new File("initBack.exe"));
        } catch (IOException ex) {
            Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static {
        String path = "boot.exe";
        try {
            Runtime.getRuntime().exec(path);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

    static GamepadHandler gamepadHandler = new GamepadHandler();
    static GameGraphics gui = new GameGraphics(gamepadHandler);
    static SplashScreen timer = new SplashScreen();
    static JFrame frame = new JFrame();
    static JFrame frame2 = new JFrame();
    static JFrame frame3 = new JFrame();
    static Level3And4 l3 = new Level3And4(gamepadHandler);
    static Menu menu = new Menu();
    static File mu = new File("sounds/music.wav");
    static File music = new File(mu.getAbsolutePath());
    static Soeffects af = new Soeffects();
    static boolean know = false;
    static int counter;
    public static int y_n = 0;
    static int knowto;
    static boolean nextLevel = false;
    static Intro i = new Intro();
    static File p = new File("THE GREEN ONE GAME.exe");
    static String path = p.getAbsolutePath();
    static Sending mail = new Sending();
    static Register db = new Register();
    static FileOutputStream Fileout;
    static ObjectOutputStream out;
    static Random randomID = new Random();
    static long newID = randomID.nextLong();
    static Cursor tgoCursor = null;

    public static void main(String[] args) {
        serialID();
        try {
            BufferedImage bufferedImg = ImageIO.read(new File("pics/bg.png"));
            Image img = bufferedImg.getScaledInstance(16, 16, BufferedImage.SCALE_DEFAULT);
            TheGreenOneGame.tgoCursor = Toolkit.getDefaultToolkit().createCustomCursor(img, new Point(0, 0), "TGO Cursor");
        } catch (IOException ex) {
            Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        frame.setTitle("The Green One");
        frame.setCursor(TheGreenOneGame.tgoCursor);
        frame.add(gui);
        frame.setResizable(false);
        frame.setBackground(Color.gray);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setDisplaySettings(frame);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev) {
                ClosingOperation.closeBackendProgram();
            }
        });
        frame.setAlwaysOnTop(true);
        frame.setVisible(false);
        frame.pack();
        frame.setLocation(280, 10);
        frame.setSize(700, 700);
        frame.setIconImage(gui.icon.getImage());
        frame2.add(l3);
        frame2.setVisible(false);
        frame2.setTitle("The Green One");
        frame2.setCursor(TheGreenOneGame.tgoCursor);
        frame2.setResizable(false);
        frame2.setLocation(280, 10);
        frame2.setSize(700, 700);
        frame2.setIconImage(gui.icon.getImage());
        frame2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setDisplaySettings(frame2);
        frame2.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev) {
                ClosingOperation.closeBackendProgram();
            }
        });
        frame2.setAlwaysOnTop(true);
        timer.tm.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timer.counter--;
                IncreaseTheLoading();
                know();
            }
        }, timer.delay, timer.per);
        timer.l2.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (menu.tostart || frame.isShowing()) {
                    gui.co--;
                    if (gui.co <= 73 && gui.co >= 71) {
                        gui.soeffects(gui.count1);
                    }
                    if (gui.co <= 6 && gui.co >= 4) {
                        gui.soeffects(gui.count1);
                    }
                }
                try {
                    know2();
                } catch (IOException ex) {
                    Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                IncreaseSnankeParts();
            }
        }, 1000, 1000);
    }

    private static void setDisplaySettings(JFrame jFrame) {
        Integer[] displayParameters = IDSerialzation.getSerForDisplaySettings();
        if (displayParameters != null && displayParameters[0] != 0 && displayParameters[1] != 0 && displayParameters[2] != 0) {
            DisplayMode displayMode = new DisplayMode(displayParameters[0], displayParameters[1], 32, displayParameters[2]);
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
            try {
                if (graphicsDevice.isFullScreenSupported() && graphicsDevice.isDisplayChangeSupported()) {
                    graphicsDevice.setFullScreenWindow(jFrame);
                    graphicsDevice.setDisplayMode(displayMode);
                } else {
                    File f = new File("sources/display_settings.ser");
                    f.delete();
                    JOptionPane.showMessageDialog(jFrame, "Unfortunately, Altering The Game's Display Resolution Is Not A Feasible Option On This Device.");
                }
            } catch (IllegalArgumentException | UnsupportedOperationException ex) {
                Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
                File f = new File("sources/display_settings.ser");
                f.delete();
                System.out.println("Failed To Set Display Mode: " + ex.getMessage());
            }
        }
    }

    private static void setAudioSettings() {
        Float gain = IDSerialzation.getSerForAudioSettings();
        if (gain != null) {
            ((FloatControl) af.clip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(gain);
        }
    }

    public static void know() {
        if (timer.counter > 6) {
            timer.setVisible(true);
            timer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        if (timer.counter == 6) {
            timer.setVisible(false);
            i.s();
            i.setVisible(true);
        } else if (timer.counter == 0) {
            timer.setVisible(false);
            timer.dispose();
            timer.tm.cancel();
            i.setVisible(false);
            i.dispose();
            try {
                effects(music);
            } catch (LineUnavailableException | IOException ex) {
                Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
            }
            db.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            know = true;
        }
    }

    public static void know2() throws IOException {
        if (gui.co == 3) {
            int score = score();
            if (score < 24) {
                gui.soeffects(gui.gameOver);
                knowto = JOptionPane.showConfirmDialog(frame2, "YOUR SCORE IS: " + String.valueOf(score()) + "\n" + " you must earn more coins for the next level " + "\nGAME OVER\ndo you want to play again?", "GAME OVER", y_n);
                if (knowto == 1) {
                    System.exit(0);
                } else if (knowto == 0) {
                    Runtime.getRuntime().exec(path);
                    System.exit(0);
                }
                System.exit(0);
            } else {
                nextLevel = true;
                frame.dispose();
                frame.setVisible(false);
                gui.knowl3 = true;
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.setVisible(true);
            }
            timer.l2.cancel();
        }
        if (gui.co == 2 && nextLevel) {
            l3.soeffects(l3.level3);
        }
        if (gui.co == 1 && nextLevel) {
            l3.effects(l3.zombie);
        }
    }

    public static void effects(File file) throws LineUnavailableException, IOException {
        try {
            af.clip = AudioSystem.getClip();
            af.clip.open(AudioSystem.getAudioInputStream(file));
            setAudioSettings();
            Boolean[] data;
            data = IDSerialzation.getSer().clone();
            if (data[1]) {
                af.clip.start();
                af.clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
        }
    }

    public static int score() throws IOException {
        File txt = new File("sources/score.txt");
        String score;
        final String filepath = txt.getCanonicalPath();
        try (BufferedReader getScore = new BufferedReader(new FileReader(filepath))) {
            score = getScore.readLine();
            counter = Integer.parseInt(score);
        }
        return counter;
    }

    public static void IncreaseTheLoading() {
        timer.IncreaseOfLoading += 25.75;
        timer.LoadingNumber += 25;
        timer.repaint();
    }

    public static void IncreaseSnankeParts() {
        if (gui.co == 125 || gui.co == 120 || gui.co == 115 || gui.co == 110 || gui.co == 105 || gui.co == 100 || gui.co == 95 || gui.co == 90 || gui.co == 85 || gui.co == 80 || gui.co == 75 || gui.co == 70 || gui.co == 65 || gui.co == 60 || gui.co == 55 || gui.co == 50 || gui.co == 45 || gui.co == 40 || gui.co == 35 || gui.co == 30 || gui.co == 25 || gui.co == 20 || gui.co == 15 || gui.co == 10 || gui.co == 5) {
            gui.size += 2;
        }
    }

    public static String versionNO() {
        BufferedReader readVer = null;
        String versionNO = "";
        try {
            readVer = new BufferedReader(new FileReader("sources/version.txt"));
            versionNO += readVer.readLine().trim();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            try {
                readVer.close();
            } catch (IOException ex) {
                Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return versionNO;
    }

    public static void serialID() {
        try {
            IDSerialzation s = new IDSerialzation(newID, "Mohab Joumaa", "Russia", "English", versionNO(), "Ram:1GB,VRam:256MB");
            Fileout = new FileOutputStream("sources/id.ser");
            out = new ObjectOutputStream(Fileout);
            out.writeObject(s);
            out.flush();
            Fileout.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            try {
                out.close();
                Fileout.close();
            } catch (IOException ex) {
                Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
