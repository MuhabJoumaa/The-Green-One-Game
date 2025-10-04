package the.green.one.game;

import com.googlecode.javacv.CanvasFrame;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static org.neo4j.driver.Config.TrustStrategy.trustAllCertificates;

public class TakeButton extends JFrame {

    File cam = new File("pics/camera.png");
    Soeffects cl = new Soeffects();
    File Taking = new File("sounds/TakePhoto.wav");
    File TakePhotoSound = new File(Taking.getAbsolutePath());
    ImageIcon camer = new ImageIcon(cam.getAbsolutePath());
    File ThePhoto;
    ImageIcon ProfilePhoto;
    Image Resize;
    Image Re;
    ImageIcon TheNewPhoto;
    public File toKnowFile;
    public boolean KnowClose = false;
    public File toSave;
    Ellipse2D Ellips = new Ellipse2D.Double(0, 0, 80, 80);
    int k;
    boolean b;
    private final Neo4jDatabaseManager neo4jdatabase = Neo4jDatabaseManager.getInstance();
    private final String email;
    private CanvasFrame frame = null;

    public TakeButton(int k, boolean b) {
        this.k = k;
        this.b = b;
        this.email = "";
        this.setUndecorated(true);
        this.initComponents();
        this.setAlwaysOnTop(true);
        this.setTitle("");
        this.setResizable(false);
        this.setBackground(Color.gray);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(false);
        this.setSize(80, 80);
        this.setIconImage(new ImageIcon(new File("pics/icon.png").getAbsolutePath()).getImage());
        this.setShape(Ellips);
        this.setBackground(Color.RED);
    }

    public TakeButton(int k, boolean b, String email) {
        this.k = k;
        this.b = b;
        this.email = email;
        this.setUndecorated(true);
        this.initComponents();
        this.setAlwaysOnTop(true);
        this.setTitle("");
        this.setResizable(false);
        this.setBackground(Color.gray);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(false);
        this.setSize(80, 80);
        this.setIconImage(new ImageIcon(new File("pics/icon.png").getAbsolutePath()).getImage());
        this.setShape(Ellips);
        this.setBackground(Color.RED);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        B1 = new JButton();
        B1.setBackground(Color.BLACK);
        B1.setIcon(camer);
        this.setUndecorated(true);
        B1.addActionListener((ActionEvent evt) -> {
            B1ActionPerformed(evt);
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(B1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(B1, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
        );
        pack();
    }

    public void B1ActionPerformed(ActionEvent evt) {
        RobotTaking(this.k, this.b);
    }

    public void RobotTaking(int k, boolean b) {
        Point FrameLocation = null;
        BufferedImage capture = null;
        int w = 0, h = 0;
        if (k == 1) {
            FrameLocation = Register.frame.getLocationOnScreen();
            w = Register.frame.getWidth() - 25;
            h = Register.frame.getHeight() - 80;
        } else if (k == 2) {
            FrameLocation = Menu.frameCanvas2.getLocationOnScreen();
            w = Menu.frameCanvas2.getWidth() - 25;
            h = Menu.frameCanvas2.getHeight() - 80;
        }
        this.toSave = new File("Screenshots/ProfilePhoto.jpg");
        try {
            File PathOfCaptureImage = new File(this.toSave.getAbsolutePath());
            Robot TakePhoto = new Robot();
            Rectangle ScreenPhoto = new Rectangle(FrameLocation.x + 10, FrameLocation.y + 50, w, h);
            capture = TakePhoto.createScreenCapture(ScreenPhoto);
            ImageIcon CapturePhoto = new ImageIcon(capture);
            ImageIO.write(capture, "jpg", PathOfCaptureImage);
        } catch (AWTException | IOException ex) {
            Logger.getLogger(GameGraphics.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        soeffects(this.TakePhotoSound);
        this.KnowClose = true;
        try {
            this.toKnowFile = File.createTempFile("1eRt", ".jpg", new File("Screenshots/"));
        } catch (IOException ex) {
            Logger.getLogger(GameGraphics.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        this.Save(k, b, capture);
    }

    public void Save(int k, boolean b, BufferedImage capture) {
        if (!b) {
            JOptionPane.showMessageDialog(this.frame, "Profile Photo Taken Successfully" + "\n" + "Your Photo Has Been Saved in ScreenShots Folder Inside The Game Folder");
            try {
                Notification.sendNotification("Profile Photo Taken Successfully" + "\n" + "Your Photo Has Been Saved in ScreenShots Folder Inside The Game Folder", "Photo Taken Successfully", TrayIcon.MessageType.INFO);
            } catch (AWTException | MalformedURLException ex) {
                Logger.getLogger(TakeButton.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (k == 1) {
            this.ThePhoto = new File("Screenshots/ProfilePhoto.jpg");
            Register.PhotoPath = this.ThePhoto.getAbsolutePath();
            this.ProfilePhoto = new ImageIcon(Register.PhotoPath);
            this.Resize = this.ProfilePhoto.getImage();
            this.Re = this.Resize.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            this.TheNewPhoto = new ImageIcon(this.Re);
            Register.photo.setIcon(this.TheNewPhoto);
            Register.photo.setSize(this.TheNewPhoto.getIconWidth(), this.TheNewPhoto.getIconHeight());
            Register.PhotoBorder = BorderFactory.createLineBorder(Color.GREEN);
            Register.photo.setBorder(Register.PhotoBorder);
            try {
                Register.PhotoInsert = new FileInputStream(this.ThePhoto);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TakeButton.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage() + "\n" + "your image is incorrect");
            }
            Register.webcam.close();
            if (Register.frame.isShowing()) {
                Register.frame.setVisible(false);
            }
        } else if (k == 2) {
            if (b) {
                this.saveStatusImageInNeo4j(capture);
            }
            if (Menu.frameCanvas2.isVisible() || Menu.frameCanvas2.isShowing() || Menu.CheckVisiblity) {
                Menu.frameCanvas2.setVisible(false);
                Menu.frameCanvas2.dispose();
                Menu.webcam2.close();
            }
        }
        this.setVisible(false);
        this.dispose();
    }

    private void saveStatusImageInNeo4j(BufferedImage capture) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(capture, "jpg", baos);
            byte[] imageBytes = baos.toByteArray();
            this.neo4jdatabase.setPlayerStatus(this.email, imageBytes);
            JOptionPane.showMessageDialog(this.frame, "Your Status Has Been Created Successfully.\nYour Status Is Public For All Players Of The Green One Game.");
        } catch (IOException ex) {
            Logger.getLogger(TakeButton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public final void soeffects(File file) {
        try {
            this.cl.clip = AudioSystem.getClip();
            this.cl.clip.open(AudioSystem.getAudioInputStream(file));
            this.cl.clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException iOException) {
        }
    }

    public void setCanvasFrame(CanvasFrame frame) {
        this.frame = frame;
    }
    public JButton B1;
}
