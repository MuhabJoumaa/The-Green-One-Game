package the.green.one.game;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_SPACE;
import static java.awt.event.KeyEvent.VK_UP;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import static the.green.one.game.TheGreenOneGame.frame2;

public class Level3And4 extends JPanel implements ActionListener, KeyListener {

    public javax.swing.Timer timer = new javax.swing.Timer(100, this);
    File sky = new File("pics/bg1.jpg");
    File bgsky = new File(sky.getAbsolutePath());
    File night = new File("pics/bg3.jpg");
    File bgNight = new File(night.getAbsolutePath());
    File lv3 = new File("sounds/level3.wav");
    File level3 = new File(lv3.getAbsolutePath());
    File ri = new File("pics/head.png");
    ImageIcon right = new ImageIcon(ri.getAbsolutePath());
    File lft = new File("pics/left.png");
    ImageIcon left = new ImageIcon(lft.getAbsolutePath());
    Soeffects cl = new Soeffects();
    public int know1 = 0;
    public int know2 = 0;
    public int headX = 10;
    public int headY = 600;
    public int changeX = 0;
    public int changeY = 0;
    public int counter;
    File pei = new File("pics/peice.png");
    File coin = new File("pics/coin.png");
    File p1 = new File(pei.getAbsolutePath());
    File p2 = new File(pei.getAbsolutePath());
    File p3 = new File(pei.getAbsolutePath());
    File p4 = new File(pei.getAbsolutePath());
    File p5 = new File(pei.getAbsolutePath());
    File p6 = new File(pei.getAbsolutePath());
    File co1 = new File(coin.getAbsolutePath());
    File co2 = new File(coin.getAbsolutePath());
    File co3 = new File(coin.getAbsolutePath());
    File co4 = new File(coin.getAbsolutePath());
    File co5 = new File(coin.getAbsolutePath());
    File co6 = new File(coin.getAbsolutePath());
    public boolean jump = false;
    public boolean fall = false;
    public double gravity = 10;
    public BufferedImage bg1;
    public BufferedImage peice1;
    public BufferedImage peice2;
    public BufferedImage peice3;
    public BufferedImage peice4;
    public BufferedImage peice5;
    public BufferedImage peice6;
    public BufferedImage coin1;
    public BufferedImage coin2;
    public BufferedImage coin3;
    public BufferedImage coin4;
    public BufferedImage coin5;
    public BufferedImage coin6;
    public int peice1X = 60;
    public int peice1Y = 500;
    public int peice2X = 160;
    public int peice2Y = 400;
    public int peice3X = 260;
    public int peice3Y = 280;
    public int peice4X = 360;
    public int peice4Y = 160;
    public int peice5X = 460;
    public int peice5Y = 40;
    public int peice6X = 560;
    public int peice6Y = 375;
    public int c1y = 500;
    public int c2y = 400;
    public int c3y = 280;
    public int c4y = 160;
    public boolean fal1 = false;
    public boolean fal2 = false;
    public boolean fal3 = false;
    public boolean fal4 = false;
    public boolean fal5 = false;
    public boolean fal6 = false;
    public boolean fal7 = false;
    public boolean fal8 = false;
    public boolean fal9 = false;
    public boolean fal10 = false;
    public boolean fal11 = false;
    public boolean fal12 = false;
    public boolean fal13 = false;
    public boolean fal14 = false;
    public boolean e1 = false;
    public boolean k1 = false;
    public boolean k2 = false;
    public boolean k3 = false;
    public boolean k4 = false;
    public boolean k5 = false;
    public boolean k6 = false;
    public boolean k7 = false;
    public boolean atc = false;
    public int y_n = 0;
    public int c5y = 40;
    public int c6y = 375;
    public int c1x = peice1X + 40;
    public int c2x = peice2X + 40;
    public int c3x = peice3X + 40;
    public int c4x = peice4X + 40;
    public int c5x = peice5X + 40;
    public int c6x = peice6X + 40;
    public Rectangle head;
    public double strokeRect = 5.0D;
    public int enB = 70;
    public int enG = 70;
// stones
    File sto = new File("pics/ston.png");
    ImageIcon ston = new ImageIcon(this.sto.getAbsolutePath());
    File ss1 = new File("pics/ston.png");
    File ss2 = new File("pics/ston.png");
    File ss3 = new File("pics/ston.png");
    File ss4 = new File("pics/ston.png");
    File ss5 = new File("pics/ston.png");
    File st1 = new File(this.ss1.getAbsolutePath());
    File st2 = new File(this.ss2.getAbsolutePath());
    File st3 = new File(this.ss3.getAbsolutePath());
    File st4 = new File(this.ss4.getAbsolutePath());
    File st5 = new File(this.ss5.getAbsolutePath());
    Random newone = new Random();
    Random newrun = new Random();
    public int number = this.newrun.nextInt(6);
    public int run1 = this.newone.nextInt(8) + 7;
    public int run2 = this.newone.nextInt(8) + 7;
    public int run3 = this.newone.nextInt(8) + 7;
    public int run4 = this.newone.nextInt(8) + 7;
    public int run5 = this.newone.nextInt(8) + 7;
    public int stonx1 = 15;
    public int stony1 = 1;
    public int stonx2 = 120;
    public int stony2 = 1;
    public int stonx3 = 225;
    public int stony3 = 1;
    public int stonx4 = 330;
    public int stonx5 = 450;
    public int stony5 = 1;
    public int stony4 = 1;
    public BufferedImage s1;
    public BufferedImage s2;
    public BufferedImage s3;
    public BufferedImage s4;
    public BufferedImage s5;
//...
    File ju = new File("sounds/falling.wav");
    File jumping = new File(ju.getAbsolutePath());
    File coins = new File("sounds/coinSound.wav");
    File coinSound = new File(coins.getAbsolutePath());
    File swd = new File("pics/sowrd.png");
    File s = new File(swd.getAbsolutePath());
    File att = new File("pics/attack.png");
    File at = new File(att.getAbsolutePath());
    public BufferedImage sowrd;
    public BufferedImage attack;
    int swdX = 0;
    int swdY = 595;
    int atcX;
    int atcY;
    public boolean defaultw = true;
    public boolean lose = false;
    File p = new File("THE GREEN ONE GAME.exe");
    public final String path = this.p.getAbsolutePath();
    File k = new File("sounds/kill.wav");
    File kill = new File(k.getAbsolutePath());
    File h = new File("sounds/hit.wav");
    File hit = new File(h.getAbsolutePath());
// enemies
    File en = new File("pics/enemies.png");
    ImageIcon enem1 = new ImageIcon(en.getAbsolutePath());
    ImageIcon enem2 = new ImageIcon(en.getAbsolutePath());
    ImageIcon enem3 = new ImageIcon(en.getAbsolutePath());
    ImageIcon enem4 = new ImageIcon(en.getAbsolutePath());
    ImageIcon enem5 = new ImageIcon(en.getAbsolutePath());
    ImageIcon enem6 = new ImageIcon(en.getAbsolutePath());
    ImageIcon enem7 = new ImageIcon(en.getAbsolutePath());
    ImageIcon enem8 = new ImageIcon(en.getAbsolutePath());
    ImageIcon enem9 = new ImageIcon(en.getAbsolutePath());
    ImageIcon enem10 = new ImageIcon(en.getAbsolutePath());
    Random num = new Random();
    Random enemy = new Random();
    public int r1 = this.enemy.nextInt((20 - 7) + 1);
    public int r2 = this.enemy.nextInt((20 - 7) + 1);
    public int r3 = this.enemy.nextInt((20 - 7) + 1);
    public int r4 = this.enemy.nextInt((20 - 7) + 1);
    public int r5 = this.enemy.nextInt((20 - 7) + 1);
    public int r6 = this.enemy.nextInt((20 - 7) + 1);
    public int r7 = this.enemy.nextInt((20 - 7) + 1);
    public int r8 = this.enemy.nextInt((20 - 7) + 1);
    public int r9 = this.enemy.nextInt((20 - 7) + 1);
    public int r10 = this.enemy.nextInt((20 - 7) + 1);
    public int numbers = num.nextInt(10);
    public int en1X = 600;
    public int en1Y = 600;
    int en2X = 600;
    int en2Y = 600;
    int en3X = 600;
    int en3Y = 600;
    int en4X = 600;
    int en4Y = 600;
    int en5X = 600;
    int en5Y = 600;
    int en6X = 600;
    int en6Y = 600;
    int en7X = 600;
    int en7Y = 600;
    int en8X = 600;
    int en8Y = 600;
    int en9X = 600;
    int en9Y = 600;
    int en10X = 600;
    int en10Y = 600;
    File tt1 = new File("pics/enemies.png");
    File tt2 = new File("pics/enemies.png");
    File tt3 = new File("pics/enemies.png");
    File tt4 = new File("pics/enemies.png");
    File tt5 = new File("pics/enemies.png");
    File tt6 = new File("pics/enemies.png");
    File tt7 = new File("pics/enemies.png");
    File tt8 = new File("pics/enemies.png");
    File tt9 = new File("pics/enemies.png");
    File tt10 = new File("pics/enemies.png");
    File t1 = new File(tt1.getAbsolutePath());
    File t2 = new File(tt2.getAbsolutePath());
    File t3 = new File(tt3.getAbsolutePath());
    File t4 = new File(tt4.getAbsolutePath());
    File t5 = new File(tt5.getAbsolutePath());
    File t6 = new File(tt6.getAbsolutePath());
    File t7 = new File(tt7.getAbsolutePath());
    File t8 = new File(tt8.getAbsolutePath());
    File t9 = new File(tt9.getAbsolutePath());
    File t10 = new File(tt10.getAbsolutePath());
    public BufferedImage ts1;
    public BufferedImage ts2;
    public BufferedImage ts3;
    public BufferedImage ts4;
    public BufferedImage ts5;
    public BufferedImage ts6;
    public BufferedImage ts7;
    public BufferedImage ts8;
    public BufferedImage ts9;
    public BufferedImage ts10;
    File zomb = new File("sounds/zombie.wav");
    File zombie = new File(zomb.getAbsolutePath());
    File grd = new File("sounds/ground.wav");
    File ground = new File(grd.getAbsolutePath());
    public Soeffects cl2 = new Soeffects();
    Random recoins = new Random();
    public int coinsPlus = 0;
    public boolean up1;
    public boolean up2;
    public boolean up3;
    public boolean up4;
    public boolean up5;
    public boolean up6;
    File atcL = new File("pics/attackLeft.png");
    File al = new File(atcL.getAbsolutePath());
    public BufferedImage attackLeft;
    File p7 = new File(pei.getAbsolutePath());
    public BufferedImage peice7;
    public double peice7X = 370.0;
    public double peice7Y = 290.0;
    public double radian = 0.0;
    public double radianPls = 0.27;
    File apl = new File("pics/apl.png");
    File apll = new File(apl.getAbsolutePath());
    public BufferedImage apple;
    File ea = new File("sounds/eat.wav");
    File eat = new File(ea.getAbsolutePath());
    File gmo = new File("sounds/gameOver.wav");
    File gameOver = new File(gmo.getAbsolutePath());
    public Color redColor = new Color(255, 0, 0);
    public Color greenColor = new Color(124, 252, 0);
    BufferedImage capture;
    Robot TakePhoto;
    ImageIcon CapturePhoto;
    File PathOfCaptureImage;
    int NumberOfPhotos = 1;
    File tp = new File("sounds/TakePhoto.wav");
    File TakePhotoSound = new File(tp.getAbsolutePath());
    public boolean OneTime = true;
    BufferedImage bg3;
    boolean ConvertToL4 = false;
    public Font IndieFlower;
    // level 4
    File l4 = new File("sounds/l4Music.wav");
    File l4Music = new File(l4.getAbsolutePath());
    Color brown = new Color(73, 49, 49);
    boolean OneTimeMusic = false;
    double LineStroke = 5.0D;
    double Line1XP1 = 390.0;
    double Line1YP1 = 300.0;
    double Line1XP2 = 340.0;
    double Line1YP2 = 300.0;
    double LineRadian = 0.0;
    double LineRadianPlus = 0.17;
    Rectangle DimondRect;
    Point2D LineP1;
    Point2D LineP2;
    Line2D Line;
    File dmnd = new File("pics/Dimond.png");
    File Di = new File(dmnd.getAbsolutePath());
    BufferedImage Dimond;
    public int DimondX;
    public int DimondY;
    public boolean OneTimeEnd = false;
    boolean end = false;
    File lv4 = new File("sounds/Level4.wav");
    File Level4 = new File(l4.getAbsolutePath());
    Random WaveH1 = new Random();
    Random WaveH2 = new Random();
    Random WaveH3 = new Random();
    Random WaveH4 = new Random();
    Random WaveH5 = new Random();
    Random WaveH6 = new Random();
    Random WaveH7 = new Random();
    boolean OneTimeL4 = false;
    double WaveX = 1.0;
    double WaveY = 700.0;
    double WaveLength = 0.04;
    double WaveFull1 = 85;
    double WaveFull2 = 85;
    double WaveFull3 = 85;
    double WaveFull4 = 85;
    double WaveFull5 = 85;
    double WaveFull6 = 85;
    double WaveFull7 = 85;
    double WaveFreq = 0.04;
    double WaveStroke = 2.0D;
    Color DarkOrange = new Color(223, 77, 16);
    Point2D Wave1P1;
    Point2D Wave1P2;
    Point2D Wave2P1;
    Point2D Wave2P2;
    Point2D Wave3P1;
    Point2D Wave3P2;
    Point2D Wave4P1;
    Point2D Wave4P2;
    Point2D Wave5P1;
    Point2D Wave5P2;
    Point2D Wave6P1;
    Point2D Wave6P2;
    Point2D Wave7P1;
    Point2D Wave7P2;
    Line2D Wave1;
    Line2D Wave2;
    Line2D Wave3;
    Line2D Wave4;
    Line2D Wave5;
    Line2D Wave6;
    Line2D Wave7;
    File Brn = new File("sounds/Burning.wav");
    File Burning = new File(Brn.getAbsolutePath());
    boolean OneTimeBurn1 = false;
    boolean OneTimeBurn2 = false;
    boolean OneTimeBurn3 = false;
    boolean OneTimeBurn4 = false;
    boolean OneTimeBurn5 = false;
    boolean OneTimeBurn6 = false;
    boolean OneTimeBurn7 = false;
    // enemies touching
    public int mouseX;
    public int mouseY;
    // Random movement
    private Random randomMovement = new Random();
    private final Neo4jDatabaseManager neo4jdatabase = Neo4jDatabaseManager.getInstance();
    // Gamepad handling
    private final GamepadHandler gamepadHandler;
    // free fall of stones
    private final double K = 1.225, G = 9.81, DRAG_COEFF = 0.47, STONE_MASS = 650.5;
    private double stoneA = 0.0;
    private final int PIXELS_PER_METER = 100;

    public class mouseMoving extends MouseAdapter implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent me) {
        }

        @Override
        public void mouseMoved(MouseEvent me) {
            Level3And4.this.mouseX = me.getX();
            Level3And4.this.mouseY = me.getY();
        }
    }

    public Level3And4(GamepadHandler gamepadHandler) {
        this.addKeyListener(this);
        this.addMouseMotionListener(new mouseMoving());
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(true);
        this.setBackground(Color.gray);
        this.gamepadHandler = gamepadHandler;
        this.gamepadHandler.setToCenter();
        this.gamepadHandler.gamepadStatuses[0] = this.gamepadHandler.checkConnection();
        try {
            this.bg1 = ImageIO.read(this.bgsky);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.bg3 = ImageIO.read(this.bgNight);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.attackLeft = ImageIO.read(this.al);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.peice1 = ImageIO.read(this.p1);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.peice2 = ImageIO.read(this.p2);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            peice3 = ImageIO.read(p3);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            peice4 = ImageIO.read(p4);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.peice5 = ImageIO.read(this.p5);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.peice6 = ImageIO.read(this.p6);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.peice7 = ImageIO.read(this.p7);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.apple = ImageIO.read(this.apll);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.coin1 = ImageIO.read(this.co1);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.coin2 = ImageIO.read(this.co2);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.coin3 = ImageIO.read(this.co3);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.coin4 = ImageIO.read(this.co4);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.coin5 = ImageIO.read(this.co5);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.coin6 = ImageIO.read(this.co6);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.sowrd = ImageIO.read(this.s);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.attack = ImageIO.read(this.at);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.ts1 = ImageIO.read(this.t1);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.ts2 = ImageIO.read(this.t2);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.ts3 = ImageIO.read(this.t3);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.ts4 = ImageIO.read(this.t4);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.ts5 = ImageIO.read(this.t5);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.ts6 = ImageIO.read(this.t6);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.ts7 = ImageIO.read(this.t7);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.ts8 = ImageIO.read(this.t8);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.ts9 = ImageIO.read(this.t9);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.ts10 = ImageIO.read(this.t10);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.s1 = ImageIO.read(this.st1);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        this.stoneA = this.calculateCrossSectionalAreaOfStone();
        try {
            this.s2 = ImageIO.read(this.st2);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.s3 = ImageIO.read(this.st3);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.s4 = ImageIO.read(this.st4);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.s5 = ImageIO.read(this.st5);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        try {
            this.Dimond = ImageIO.read(this.Di);
        } catch (IOException e) {
            System.out.print("Error try agian");
            e.getMessage();
        }
        this.timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (!ConvertToL4) {
            g.drawImage(bg1, 0, 0, this);
        } else {
            g.drawImage(bg3, 0, 0, this);
        }
        final Rectangle floor = new Rectangle(1, 645, 700, 40);
        Graphics2D g2 = (Graphics2D) g;
        g.fillRect(10, 10, this.enB, 20);
        if (enG <= 30) {
            g.setColor(redColor);
        } else {
            g.setColor(greenColor);
        }
        g.fillRect(10, 10, this.enG, 20);
        g2.setStroke(new BasicStroke((float) this.strokeRect));
        g.setColor(Color.RED);
        g2.drawRect(8, 8, 75, 25);
        g2.setStroke(new BasicStroke((float) 0.01));
        head = new Rectangle(headX, headY, 50, 50);
        g.setColor(Color.black);
        g.setFont(this.indieFlower());
        try {
            g.drawString("Score:" + "\n" + String.valueOf(score()), 565, 25);
        } catch (IOException ex) {
            Logger.getLogger(Level3And4.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.setColor(Color.pink);
        g.setFont(this.indieFlower());
        if (!ConvertToL4) {
            g.drawString("Level 3", 560, 50);
        } else {
            g.drawString("Level 4", 560, 50);
        }
        g.drawImage(peice1, peice1X, peice1Y, this);
        g.drawImage(peice2, peice2X, peice2Y, this);
        g.drawImage(peice3, peice3X, peice3Y, this);
        g.drawImage(peice4, peice4X, peice4Y, this);
        g.drawImage(peice5, peice5X, peice5Y, this);
        g.drawImage(peice6, peice6X, peice6Y, this);
        g.drawImage(peice7, (int) peice7X, (int) peice7Y, this);
        g.drawImage(coin1, peice1X + 40, peice1Y, this);
        g.drawImage(coin2, peice2X + 40, peice2Y, this);
        g.drawImage(coin3, peice3X + 40, peice3Y, this);
        g.drawImage(coin4, peice4X + 40, peice4Y, this);
        g.drawImage(coin5, peice5X + 40, peice5Y, this);
        g.drawImage(coin6, peice6X + 40, peice6Y, this);
        g.drawImage(apple, (int) peice7X + 30, (int) peice7Y, this);
        Rectangle end = new Rectangle(headX + 20, headY + 40, 10, 10);
        Rectangle top = new Rectangle(headX + 12, headY - 13, 10, 5);
        Rectangle c1 = new Rectangle(c1x, c1y, 20, 20);
        Rectangle c2 = new Rectangle(c2x, c2y, 20, 20);
        Rectangle c3 = new Rectangle(c3x, c3y, 20, 20);
        Rectangle c4 = new Rectangle(c4x, c4y, 20, 20);
        Rectangle c5 = new Rectangle(c5x, c5y, 20, 20);
        Rectangle c6 = new Rectangle(c6x, c6y, 20, 20);
        Rectangle p1 = new Rectangle(peice1X, peice1Y, 80, 80);
        Rectangle p2 = new Rectangle(peice2X, peice2Y, 80, 80);
        Rectangle p3 = new Rectangle(peice3X, peice3Y, 80, 80);
        Rectangle p4 = new Rectangle(peice4X, peice4Y, 80, 80);
        Rectangle p5 = new Rectangle(peice5X, peice5Y, 80, 80);
        Rectangle p6 = new Rectangle(peice6X, peice6Y, 80, 80);
        Rectangle p7 = new Rectangle((int) peice7X, (int) peice7Y, 80, 80);
        Rectangle aples = new Rectangle((int) peice7X + 30, (int) peice7Y, 25, 25);
        Rectangle endp1 = new Rectangle(peice1X, peice1Y + 65, 80, 10);
        Rectangle endp2 = new Rectangle(peice2X, peice2Y + 65, 80, 10);
        Rectangle endp3 = new Rectangle(peice3X, peice3Y + 65, 80, 10);
        Rectangle endp4 = new Rectangle(peice4X, peice4Y + 65, 80, 10);
        Rectangle endp5 = new Rectangle(peice5X, peice5Y + 65, 80, 10);
        Rectangle endp6 = new Rectangle(peice6X, peice6Y + 65, 80, 10);
        Rectangle endp7 = new Rectangle((int) peice7X, (int) peice7Y + 65, 80, 7);
        if (defaultw) {
            right.paintIcon(this, g, headX, headY);
        }
        if (know1 == 1) {
            right.paintIcon(this, g, headX, headY);
        } else if (know2 == 1) {
            left.paintIcon(this, g, headX, headY);
        }
        //collision coins
        ArrayList<Rectangle> cns = new ArrayList<>();
        if (head.intersects(c1)) {
            coinsPlus++;
            soeffects(coinSound);
            c1y = 0;
            c1x = 0;
            c1.width = 1;
            c1.height = 1;
            coin1 = null;
        }
        if (head.intersects(c2)) {
            coinsPlus++;
            soeffects(coinSound);
            c2y = 0;
            c2x = 0;
            c2.width = 1;
            c2.height = 1;
            coin2 = null;
        }
        if (head.intersects(c3)) {
            coinsPlus++;
            soeffects(coinSound);
            c3y = 0;
            c3x = 0;
            c3.width = 1;
            c3.height = 1;
            coin3 = null;
        }
        if (head.intersects(c4)) {
            coinsPlus++;
            soeffects(coinSound);
            c4y = 0;
            c4x = 0;
            c4.width = 1;
            c4.height = 1;
            coin4 = null;
        }
        if (head.intersects(c5)) {
            coinsPlus++;
            soeffects(coinSound);
            c5y = 0;
            c5x = 0;
            c5.width = 1;
            c5.height = 1;
            coin5 = null;
        }
        if (head.intersects(c6)) {
            coinsPlus++;
            soeffects(coinSound);
            c6y = 0;
            c6x = 0;
            c6.width = 1;
            c6.height = 1;
            coin6 = null;
        }
        if (coin1 == null) {
            cns.remove(c1);
        } else {
            cns.add(c1);
        }
        if (coin2 == null) {
            cns.remove(c2);
        } else {
            cns.add(c2);
        }
        if (coin3 == null) {
            cns.remove(c3);
        } else {
            cns.add(c3);
        }
        if (coin4 == null) {
            cns.remove(c4);
        } else {
            cns.add(c4);
        }
        if (coin5 == null) {
            cns.remove(c5);
        } else {
            cns.add(c5);
        }
        if (coin6 == null) {
            cns.remove(c6);
        } else {
            cns.add(c6);
        }
        //collision blocks
        if (end.intersects(p1)) {
            changeY = 0;
            headY = p1.y;
            fal1 = true;
        } else if (end.intersects(p2)) {
            changeY = 0;
            headY = p2.y;
            fal2 = true;
        } else if (end.intersects(p3)) {
            changeY = 0;
            headY = p3.y;
            fal3 = true;
        } else if (end.intersects(p4)) {
            changeY = 0;
            headY = p4.y;
            fal4 = true;
        } else if (end.intersects(p5)) {
            changeY = 0;
            headY = p5.y;
            fal5 = true;
        } else if (end.intersects(p6)) {
            changeY = 0;
            headY = p6.y;
            fal6 = true;
        } // circular
        else if (end.intersects(p7)) {
            changeY = 0;
            changeX = 0;
            headY = p7.y;
            headX = p7.x;
            fal13 = true;
        }
        //collision end of blocks
        if (endp1.intersects(head)) {
            changeY = 0;
            Toolkit.getDefaultToolkit().beep();
            changeY += gravity + 4;
            fal7 = true;
            soeffects(ground);
        } else if (endp2.intersects(head)) {
            changeY = 0;
            Toolkit.getDefaultToolkit().beep();
            changeY += gravity + 4;
            fal8 = true;
            soeffects(ground);
        } else if (endp3.intersects(head)) {
            changeY = 0;
            Toolkit.getDefaultToolkit().beep();
            changeY += gravity + 4;
            fal9 = true;
            soeffects(ground);
        } else if (endp4.intersects(head)) {
            changeY = 0;
            Toolkit.getDefaultToolkit().beep();
            changeY += gravity + 4;
            fal10 = true;
            soeffects(ground);
        } else if (endp5.intersects(head)) {
            changeY = 0;
            Toolkit.getDefaultToolkit().beep();
            changeY += gravity + 4;
            fal11 = true;
            soeffects(ground);
        } else if (endp6.intersects(head)) {
            changeY = 0;
            Toolkit.getDefaultToolkit().beep();
            changeY += gravity + 4;
            fal12 = true;
            soeffects(ground);
        } //end of circular
        else if (endp7.intersects(top)) {
            changeY = 0;
            Toolkit.getDefaultToolkit().beep();
            changeY += gravity + 4;
            fal14 = true;
            soeffects(ground);
        }
        //collision floor
        if (fal1 && end.intersects(floor)) {
            fal1 = false;
            headY = 600;
            changeY = 0;
        } else if (fal2 && end.intersects(floor)) {
            fal2 = false;
            headY = 600;
            changeY = 0;
        } else if (fal3 && end.intersects(floor)) {
            fal3 = false;
            headY = 600;
            changeY = 0;
        } else if (fal4 && end.intersects(floor)) {
            fal4 = false;
            headY = 600;
            changeY = 0;
        } else if (fal5 && end.intersects(floor)) {
            fal5 = false;
            headY = 600;
            changeY = 0;
        } else if (fal6 && end.intersects(floor)) {
            fal6 = false;
            headY = 600;
            changeY = 0;
        } else if (fal7 && end.intersects(floor)) {
            fal7 = false;
            headY = 600;
            changeY = 0;
        } else if (fal8 && end.intersects(floor)) {
            fal8 = false;
            headY = 600;
            changeY = 0;
        } else if (fal9 && end.intersects(floor)) {
            fal9 = false;
            headY = 600;
            changeY = 0;
        } else if (fal10 && end.intersects(floor)) {
            fal10 = false;
            headY = 600;
            changeY = 0;
        } else if (fal11 && end.intersects(floor)) {
            fal11 = false;
            headY = 600;
            changeY = 0;
        } else if (fal12 && end.intersects(floor)) {
            fal12 = false;
            headY = 600;
            changeY = 0;
        } // circular
        else if (fal13 && end.intersects(floor)) {
            fal13 = false;
            headY = 600;
            changeY = 0;
        } else if (fal14 && end.intersects(floor)) {
            fal14 = false;
            headY = 600;
            changeY = 0;
        }
        e1 = end.intersects(floor);
        k1 = end.intersects(p1);
        k2 = end.intersects(p2);
        k3 = end.intersects(p3);
        k4 = end.intersects(p4);
        k5 = end.intersects(p5);
        k6 = end.intersects(p6);
        k7 = end.intersects(p7);
        Rectangle stone1 = new Rectangle(this.stonx1, this.stony1, 90, 90);
        Rectangle stone2 = new Rectangle(this.stonx2, this.stony2, 90, 90);
        Rectangle stone3 = new Rectangle(this.stonx3, this.stony3, 90, 90);
        Rectangle stone4 = new Rectangle(this.stonx4, this.stony4, 90, 90);
        Rectangle stone5 = new Rectangle(this.stonx5, this.stony5, 90, 90);
        switch (this.number) {
            case 0:
            case 1: {
                double stone1D = this.calculateDisplacementOfStone(1);
                this.ston.paintIcon(this, g, this.stonx1, this.stony1);
                this.stony1 += stone1D * this.PIXELS_PER_METER;
                break;
            }
            case 2: {
                double stone1D = this.calculateDisplacementOfStone(1),
                        stone2D = this.calculateDisplacementOfStone(2);
                this.ston.paintIcon(this, g, this.stonx1, this.stony1);
                this.ston.paintIcon(this, g, this.stonx2, this.stony2);
                this.stony1 += stone1D * this.PIXELS_PER_METER;
                this.stony2 += stone2D * this.PIXELS_PER_METER;
                break;
            }
            case 3: {
                double stone1D = this.calculateDisplacementOfStone(1),
                        stone2D = this.calculateDisplacementOfStone(2),
                        stone3D = this.calculateDisplacementOfStone(3);
                this.ston.paintIcon(this, g, this.stonx1, this.stony1);
                this.ston.paintIcon(this, g, this.stonx2, this.stony2);
                this.ston.paintIcon(this, g, this.stonx3, this.stony3);
                this.stony1 += stone1D * this.PIXELS_PER_METER;
                this.stony2 += stone2D * this.PIXELS_PER_METER;
                this.stony3 += stone3D * this.PIXELS_PER_METER;
                break;
            }
            case 4: {
                double stone1D = this.calculateDisplacementOfStone(1),
                        stone2D = this.calculateDisplacementOfStone(2),
                        stone3D = this.calculateDisplacementOfStone(3),
                        stone4D = this.calculateDisplacementOfStone(4);
                this.ston.paintIcon(this, g, this.stonx1, this.stony1);
                this.ston.paintIcon(this, g, this.stonx2, this.stony2);
                this.ston.paintIcon(this, g, this.stonx3, this.stony3);
                this.ston.paintIcon(this, g, this.stonx4, this.stony4);
                this.stony1 += stone1D * this.PIXELS_PER_METER;
                this.stony2 += stone2D * this.PIXELS_PER_METER;
                this.stony3 += stone3D * this.PIXELS_PER_METER;
                this.stony4 += stone4D * this.PIXELS_PER_METER;
                break;
            }
            case 5: {
                double stone1D = this.calculateDisplacementOfStone(1),
                        stone2D = this.calculateDisplacementOfStone(2),
                        stone3D = this.calculateDisplacementOfStone(3),
                        stone4D = this.calculateDisplacementOfStone(4),
                        stone5D = this.calculateDisplacementOfStone(5);
                this.ston.paintIcon(this, g, this.stonx1, this.stony1);
                this.ston.paintIcon(this, g, this.stonx2, this.stony2);
                this.ston.paintIcon(this, g, this.stonx3, this.stony3);
                this.ston.paintIcon(this, g, this.stonx4, this.stony4);
                this.ston.paintIcon(this, g, this.stonx5, this.stony5);
                this.stony1 += stone1D * this.PIXELS_PER_METER;
                this.stony2 += stone2D * this.PIXELS_PER_METER;
                this.stony3 += stone3D * this.PIXELS_PER_METER;
                this.stony4 += stone4D * this.PIXELS_PER_METER;
                this.stony5 += stone5D * this.PIXELS_PER_METER;
                break;
            }
        }
        if (stone1.y > 1) {
            g.drawImage(this.s1, stone1.x, stone1.y, this);
        }
        if (stone2.y > 1) {
            g.drawImage(this.s2, stone2.x, stone2.y, this);
        }
        if (stone3.y > 1) {
            g.drawImage(this.s3, stone3.x, stone3.y, this);
        }
        if (stone4.y > 1) {
            g.drawImage(this.s4, stone4.x, stone4.y, this);
        }
        if (stone5.y > 1) {
            g.drawImage(this.s5, stone5.x, stone5.y, this);
        }
        // enemies
        Rectangle enemy1 = new Rectangle(this.en1X, this.en1Y, 40, 60);
        Rectangle enemy2 = new Rectangle(this.en2X, this.en2Y, 40, 60);
        Rectangle enemy3 = new Rectangle(this.en3X, this.en3Y, 40, 60);
        Rectangle enemy4 = new Rectangle(this.en4X, this.en4Y, 40, 60);
        Rectangle enemy5 = new Rectangle(this.en5X, this.en5Y, 40, 60);
        Rectangle enemy6 = new Rectangle(this.en6X, this.en6Y, 40, 60);
        Rectangle enemy7 = new Rectangle(this.en7X, this.en8Y, 40, 60);
        Rectangle enemy8 = new Rectangle(this.en8X, this.en8Y, 40, 60);
        Rectangle enemy9 = new Rectangle(this.en9X, this.en9Y, 40, 60);
        Rectangle enemy10 = new Rectangle(this.en10X, this.en10Y, 60, 60);
        Point mousePoint = new Point(this.mouseX, this.mouseY);
        Rectangle mouseRect = new Rectangle((int) mousePoint.getX() - 3, (int) mousePoint.getY() - 3, 12, 12);
        g.setColor(Color.BLACK);
        if (enemy1.x < 600) {
            g.drawImage(ts1, enemy1.x, enemy1.y, this);
        }
        if (enemy2.x < 600) {
            g.drawImage(ts2, enemy2.x, enemy2.y, this);
        }
        if (enemy3.x < 600) {
            g.drawImage(ts3, enemy3.x, enemy3.y, this);
        }
        if (enemy4.x < 600) {
            g.drawImage(ts4, enemy4.x, enemy4.y, this);
        }
        if (enemy5.x < 600) {
            g.drawImage(ts5, enemy5.x, enemy5.y, this);
        }
        if (enemy6.x < 600) {
            g.drawImage(ts6, enemy6.x, enemy6.y, this);
        }
        if (enemy7.x < 600) {
            g.drawImage(ts7, enemy7.x, enemy7.y, this);
        }
        if (enemy8.x < 600) {
            g.drawImage(ts8, enemy8.x, enemy8.y, this);
        }
        if (enemy9.x < 600) {
            g.drawImage(ts9, enemy9.x, enemy9.y, this);
        }
        if (enemy10.x < 600) {
            g.drawImage(ts10, enemy10.x, enemy10.y, this);
        }
        if (enemy1.intersects(mouseRect) || enemy2.contains(mouseRect) || enemy3.contains(mouseRect) || enemy4.contains(mouseRect) || enemy5.contains(mouseRect) || enemy6.contains(mouseRect) || enemy7.contains(mouseRect) || enemy8.contains(mouseRect) || enemy9.contains(mouseRect) || enemy10.contains(mouseRect)) {
            this.soeffects(this.zombie);
        }
        switch (this.numbers) {
            case 0:
                this.enem1.paintIcon(this, g, this.en1X, this.en1Y);
                this.en1X -= this.r1;
                break;
            case 1:
                this.enem1.paintIcon(this, g, this.en1X, this.en1Y);
                this.en1X -= this.r1;
                break;
            case 2:
                this.enem1.paintIcon(this, g, this.en1X, this.en1Y);
                this.enem2.paintIcon(this, g, this.en2X, this.en2Y);
                this.en1X -= this.r1;
                this.en2X -= this.r2;
                break;
            case 3:
                this.enem1.paintIcon(this, g, this.en1X, this.en1Y);
                this.enem2.paintIcon(this, g, this.en2X, this.en2Y);
                this.enem3.paintIcon(this, g, this.en3X, this.en3Y);
                this.en1X -= this.r1;
                this.en2X -= this.r2;
                this.en3X -= this.r3;
                break;
            case 4:
                this.enem1.paintIcon(this, g, this.en1X, this.en1Y);
                this.enem2.paintIcon(this, g, this.en2X, this.en2Y);
                this.enem3.paintIcon(this, g, this.en3X, this.en3Y);
                this.enem4.paintIcon(this, g, this.en4X, this.en4Y);
                this.en1X -= this.r1;
                this.en2X -= this.r2;
                this.en3X -= this.r3;
                this.en4X -= this.r4;
                break;
            case 5:
                this.enem1.paintIcon(this, g, this.en1X, this.en1Y);
                this.enem2.paintIcon(this, g, this.en2X, this.en2Y);
                this.enem3.paintIcon(this, g, this.en3X, this.en3Y);
                this.enem4.paintIcon(this, g, this.en4X, this.en4Y);
                this.enem5.paintIcon(this, g, this.en5X, this.en5Y);
                this.en1X -= this.r1;
                this.en2X -= this.r2;
                this.en3X -= this.r3;
                this.en4X -= this.r4;
                this.en5X -= this.r5;
                break;
            case 6:
                this.enem1.paintIcon(this, g, this.en1X, this.en1Y);
                this.enem2.paintIcon(this, g, this.en2X, this.en2Y);
                this.enem3.paintIcon(this, g, this.en3X, this.en3Y);
                this.enem4.paintIcon(this, g, this.en4X, this.en4Y);
                this.enem5.paintIcon(this, g, this.en5X, this.en5Y);
                this.enem6.paintIcon(this, g, this.en6X, this.en6Y);
                this.en1X -= this.r1;
                this.en2X -= this.r2;
                this.en3X -= this.r3;
                this.en4X -= this.r4;
                this.en5X -= this.r5;
                this.en6X -= this.r6;
                break;
            case 7:
                this.enem1.paintIcon(this, g, this.en1X, this.en1Y);
                this.enem2.paintIcon(this, g, this.en2X, this.en2Y);
                this.enem3.paintIcon(this, g, this.en3X, this.en3Y);
                this.enem4.paintIcon(this, g, this.en4X, this.en4Y);
                this.enem5.paintIcon(this, g, this.en5X, this.en5Y);
                this.enem6.paintIcon(this, g, this.en6X, this.en6Y);
                this.enem7.paintIcon(this, g, this.en7X, this.en7Y);
                this.en1X -= this.r1;
                this.en2X -= this.r2;
                this.en3X -= this.r3;
                this.en4X -= this.r4;
                this.en5X -= this.r5;
                this.en6X -= this.r6;
                this.en7X -= this.r7;
                break;
            case 8:
                this.enem1.paintIcon(this, g, this.en1X, this.en1Y);
                this.enem2.paintIcon(this, g, this.en2X, this.en2Y);
                this.enem3.paintIcon(this, g, this.en3X, this.en3Y);
                this.enem4.paintIcon(this, g, this.en4X, this.en4Y);
                this.enem5.paintIcon(this, g, this.en5X, this.en5Y);
                this.enem6.paintIcon(this, g, this.en6X, this.en6Y);
                this.enem7.paintIcon(this, g, this.en7X, this.en7Y);
                this.enem8.paintIcon(this, g, this.en8X, this.en8Y);
                this.en1X -= this.r1;
                this.en2X -= this.r2;
                this.en3X -= this.r3;
                this.en4X -= this.r4;
                this.en5X -= this.r5;
                this.en6X -= this.r6;
                this.en7X -= this.r7;
                this.en8X -= this.r8;
                break;
            case 9:
                this.enem1.paintIcon(this, g, this.en1X, this.en1Y);
                this.enem2.paintIcon(this, g, this.en2X, this.en2Y);
                this.enem3.paintIcon(this, g, this.en3X, this.en3Y);
                this.enem4.paintIcon(this, g, this.en4X, this.en4Y);
                this.enem5.paintIcon(this, g, this.en5X, this.en5Y);
                this.enem6.paintIcon(this, g, this.en6X, this.en6Y);
                this.enem7.paintIcon(this, g, this.en7X, this.en7Y);
                this.enem8.paintIcon(this, g, this.en8X, this.en8Y);
                this.enem9.paintIcon(this, g, this.en9X, this.en9Y);
                this.en1X -= this.r1;
                this.en2X -= this.r2;
                this.en3X -= this.r3;
                this.en4X -= this.r4;
                this.en5X -= this.r5;
                this.en6X -= this.r6;
                this.en7X -= this.r7;
                this.en8X -= this.r8;
                this.en9X -= this.r9;
                break;
            case 10:
                this.enem1.paintIcon(this, g, this.en1X, this.en1Y);
                this.enem2.paintIcon(this, g, this.en2X, this.en2Y);
                this.enem3.paintIcon(this, g, this.en3X, this.en3Y);
                this.enem4.paintIcon(this, g, this.en4X, this.en4Y);
                this.enem5.paintIcon(this, g, this.en5X, this.en5Y);
                this.enem6.paintIcon(this, g, this.en6X, this.en6Y);
                this.enem7.paintIcon(this, g, this.en7X, this.en7Y);
                this.enem8.paintIcon(this, g, this.en8X, this.en8Y);
                this.enem9.paintIcon(this, g, this.en9X, this.en9Y);
                this.enem10.paintIcon(this, g, this.en10X, this.en10Y);
                this.en1X -= this.r1;
                this.en2X -= this.r2;
                this.en3X -= this.r3;
                this.en4X -= this.r4;
                this.en5X -= this.r5;
                this.en6X -= this.r6;
                this.en7X -= this.r7;
                this.en8X -= this.r8;
                this.en9X -= this.r9;
                this.en10X -= this.r10;
                break;
        }
        if (!atc) {
            g.drawImage(sowrd, swdX, swdY, this);
            Rectangle swd = new Rectangle(swdX + 20, swdY + 4, 10, 10);
        } else if (atc) {
            soeffects(hit);
            Rectangle attac = new Rectangle(atcX + 35, atcY + 20, 10, 10);
            if (know1 == 1) {
                g.drawImage(attack, atcX, atcY, this);
            }
            if (know2 == 1) {
                g.drawImage(attackLeft, atcX, atcY, this);
                attac.x = atcX - 4;
            }
            atc = false;
            if (attac.intersects(enemy1)) {
                soeffects(kill);
                enemy1.x = 0;
                enemy1.y = 0;
                this.numbers = this.num.nextInt(10);
                this.en1X = 700;
            }
            if (attac.intersects(enemy2)) {
                soeffects(kill);
                enemy2.x = 0;
                enemy2.y = 0;
                this.numbers = this.num.nextInt(10);
                this.en2X = 700;
            }
            if (attac.intersects(enemy3)) {
                soeffects(kill);
                enemy3.x = 0;
                enemy3.y = 0;
                this.numbers = this.num.nextInt(10);
                this.en3X = 700;
            }
            if (attac.intersects(enemy4)) {
                soeffects(kill);
                enemy4.x = 0;
                enemy4.y = 0;
                this.numbers = this.num.nextInt(10);
                this.en4X = 700;
            }
            if (attac.intersects(enemy5)) {
                soeffects(kill);
                enemy5.x = 0;
                enemy5.y = 0;
                this.numbers = this.num.nextInt(10);
                this.en5X = 700;
            }
            if (attac.intersects(enemy6)) {
                soeffects(kill);
                enemy6.x = 0;
                enemy6.y = 0;
                this.numbers = this.num.nextInt(10);
                this.en6X = 700;
            }
            if (attac.intersects(enemy7)) {
                soeffects(kill);
                enemy7.x = 0;
                enemy7.y = 0;
                this.numbers = this.num.nextInt(10);
                this.en7X = 700;
            }
            if (attac.intersects(enemy8)) {
                soeffects(kill);
                enemy8.x = 0;
                enemy8.y = 0;
                this.numbers = this.num.nextInt(10);
                this.en8X = 700;
            }
            if (attac.intersects(enemy9)) {
                soeffects(kill);
                enemy9.x = 0;
                enemy9.y = 0;
                this.numbers = this.num.nextInt(10);
                this.en9X = 700;
            }
            if (attac.intersects(enemy10)) {
                soeffects(kill);
                enemy10.x = 0;
                enemy10.y = 0;
                this.numbers = this.num.nextInt(10);
                this.en10X = 700;
            }
        }
        //..
        if (head.intersects(stone1) && stone1.y != 1) {
            this.decreaseEnergy(5);
            Toolkit.getDefaultToolkit().beep();
        } else if (head.intersects(stone2) && stone2.y != 1) {
            this.decreaseEnergy(5);
            Toolkit.getDefaultToolkit().beep();
        } else if (head.intersects(stone3) && stone3.y != 1) {
            this.decreaseEnergy(5);
            Toolkit.getDefaultToolkit().beep();
        } else if (head.intersects(stone4) && stone4.y != 1) {
            this.decreaseEnergy(5);
            Toolkit.getDefaultToolkit().beep();
        } else if (head.intersects(stone5) && stone5.y != 1) {
            this.decreaseEnergy(5);
            Toolkit.getDefaultToolkit().beep();
        }
        if (head.intersects(enemy1)) {
            this.decreaseEnergy(5);
            Toolkit.getDefaultToolkit().beep();
        } else if (head.intersects(enemy2)) {
            this.decreaseEnergy(5);
            Toolkit.getDefaultToolkit().beep();
        } else if (head.intersects(enemy3)) {
            this.decreaseEnergy(5);
            Toolkit.getDefaultToolkit().beep();
        } else if (head.intersects(enemy4)) {
            this.decreaseEnergy(5);
            Toolkit.getDefaultToolkit().beep();
        } else if (head.intersects(enemy5)) {
            this.decreaseEnergy(5);
            Toolkit.getDefaultToolkit().beep();
        } else if (head.intersects(enemy6)) {
            this.decreaseEnergy(5);
            Toolkit.getDefaultToolkit().beep();
        } else if (head.intersects(enemy7)) {
            this.decreaseEnergy(5);
            Toolkit.getDefaultToolkit().beep();
        } else if (head.intersects(enemy8)) {
            this.decreaseEnergy(5);
            Toolkit.getDefaultToolkit().beep();
        } else if (head.intersects(enemy9)) {
            this.decreaseEnergy(5);
            Toolkit.getDefaultToolkit().beep();
        } else if (head.intersects(enemy10)) {
            this.decreaseEnergy(5);
            Toolkit.getDefaultToolkit().beep();
        } else if (this.enG <= 2) {
            this.lose = true;
            Toolkit.getDefaultToolkit().beep();
        }
        if (headY > 640) {
            this.lose = true;
        }
        if (head.intersects(aples) && enG < 68) {
            soeffects(eat);
            this.increaseEnergy(10);
        }
        if (cns.isEmpty() || cns.size() == 1) {
            int recns = recoins.nextInt(6);
            switch (recns) {
                case 0:
                    c1x = peice1X + 40;
                    c1y = peice1Y;
                    try {
                        coin1 = ImageIO.read(co1);
                    } catch (IOException e) {
                        System.out.print("Error try agian");
                        e.getMessage();
                    }
                    g.drawImage(coin1, peice1X + 40, peice1Y, this);
                    c1 = new Rectangle(c1x, c1y, 20, 20);
                    cns.add(c1);
                    break;
                case 1:
                    c1x = peice1X + 40;
                    c2x = peice2X + 40;
                    c1y = peice1Y;
                    c2y = peice2Y;
                    try {
                        coin1 = ImageIO.read(co1);
                    } catch (IOException e) {
                        System.out.print("Error try agian");
                    }
                    try {
                        coin2 = ImageIO.read(co2);
                    } catch (IOException e) {
                        System.out.print("Error try agian");
                    }
                    g.drawImage(coin1, peice1X + 40, peice1Y, this);
                    c1 = new Rectangle(c1x, c1y, 20, 20);
                    cns.add(c1);
                    g.drawImage(coin2, peice2X + 40, peice2Y, this);
                    c2 = new Rectangle(c2x, c2y, 20, 20);
                    cns.add(c2);
                    break;
                case 2:
                    c1x = peice1X + 40;
                    c2x = peice2X + 40;
                    c3x = peice3X + 40;
                    c1y = peice1Y;
                    c2y = peice2Y;
                    c3y = peice3Y;
                    try {
                        coin1 = ImageIO.read(co1);
                    } catch (IOException e) {
                        System.out.print("Error try agian");
                    }
                    try {
                        coin2 = ImageIO.read(co2);
                    } catch (IOException e) {
                        System.out.print("Error try agian");
                    }
                    try {
                        coin3 = ImageIO.read(co3);
                    } catch (IOException e) {
                        System.out.print("Error try agian");
                    }
                    g.drawImage(coin1, peice1X + 40, peice1Y, this);
                    c1 = new Rectangle(c1x, c1y, 20, 20);
                    cns.add(c1);
                    g.drawImage(coin2, peice2X + 40, peice2Y, this);
                    c2 = new Rectangle(c2x, c2y, 20, 20);
                    cns.add(c2);
                    g.drawImage(coin3, peice3X + 40, peice3Y, this);
                    c3 = new Rectangle(c3x, c3y, 20, 20);
                    cns.add(c3);
                    break;
                case 3:
                    c1x = peice1X + 40;
                    c2x = peice2X + 40;
                    c3x = peice3X + 40;
                    c4x = peice4X + 40;
                    c1y = peice1Y;
                    c2y = peice2Y;
                    c3y = peice3Y;
                    c4y = peice4Y;
                    try {
                        coin1 = ImageIO.read(co1);
                    } catch (IOException e) {
                        System.out.print("Error try agian");
                    }
                    try {
                        coin2 = ImageIO.read(co2);
                    } catch (IOException e) {
                        System.out.print("Error try agian");
                    }
                    try {
                        coin3 = ImageIO.read(co3);
                    } catch (IOException e) {
                        System.out.print("Error try agian");
                    }
                    try {
                        coin4 = ImageIO.read(co4);
                    } catch (IOException e) {
                        System.out.print("Error try agian");
                    }
                    g.drawImage(coin1, peice1X + 40, peice1Y, this);
                    c1 = new Rectangle(c1x, c1y, 20, 20);
                    cns.add(c1);
                    g.drawImage(coin2, peice2X + 40, peice2Y, this);
                    c2 = new Rectangle(c2x, c2y, 20, 20);
                    cns.add(c2);
                    g.drawImage(coin3, peice3X + 40, peice3Y, this);
                    c3 = new Rectangle(c3x, c3y, 20, 20);
                    cns.add(c3);
                    g.drawImage(coin4, peice4X + 40, peice4Y, this);
                    c4 = new Rectangle(c4x, c4y, 20, 20);
                    cns.add(c4);
                    break;
                case 4:
                    c1x = peice1X + 40;
                    c2x = peice2X + 40;
                    c3x = peice3X + 40;
                    c4x = peice4X + 40;
                    c5x = peice5X + 40;
                    c1y = peice1Y;
                    c2y = peice2Y;
                    c3y = peice3Y;
                    c4y = peice4Y;
                    c5y = peice5Y;
                    try {
                        coin1 = ImageIO.read(co1);
                    } catch (IOException e) {
                        System.out.print("Error try agian");
                    }
                    try {
                        coin2 = ImageIO.read(co2);
                    } catch (IOException e) {
                        System.out.print("Error try agian");
                    }
                    try {
                        coin3 = ImageIO.read(co3);
                    } catch (IOException e) {
                        System.out.print("Error try agian");
                    }
                    try {
                        coin4 = ImageIO.read(co4);
                    } catch (IOException e) {
                        System.out.print("Error try agian");
                    }
                    try {
                        coin5 = ImageIO.read(co5);
                    } catch (IOException e) {
                        System.out.print("Error try agian");
                    }
                    g.drawImage(coin1, peice1X + 40, peice1Y, this);
                    c1 = new Rectangle(c1x, c1y, 20, 20);
                    cns.add(c1);
                    g.drawImage(coin2, peice2X + 40, peice2Y, this);
                    c2 = new Rectangle(c2x, c2y, 20, 20);
                    cns.add(c2);
                    g.drawImage(coin3, peice3X + 40, peice3Y, this);
                    c3 = new Rectangle(c3x, c3y, 20, 20);
                    cns.add(c3);
                    g.drawImage(coin4, peice4X + 40, peice4Y, this);
                    c4 = new Rectangle(c4x, c4y, 20, 20);
                    cns.add(c4);
                    g.drawImage(coin5, peice5X + 40, peice5Y, this);
                    c5 = new Rectangle(c5x, c5y, 20, 20);
                    cns.add(c5);
                    break;
                case 5:
                    c1x = peice1X + 40;
                    c2x = peice2X + 40;
                    c3x = peice3X + 40;
                    c4x = peice4X + 40;
                    c5x = peice5X + 40;
                    c6x = peice6X + 40;
                    c1y = peice1Y;
                    c2y = peice2Y;
                    c3y = peice3Y;
                    c4y = peice4Y;
                    c5y = peice5Y;
                    c6y = peice6Y;
                    try {
                        coin1 = ImageIO.read(co1);
                    } catch (IOException e) {
                        System.out.print("Error try agian");
                    }
                    try {
                        coin2 = ImageIO.read(co2);
                    } catch (IOException e) {
                        System.out.print("Error try agian");
                    }
                    try {
                        coin3 = ImageIO.read(co3);
                    } catch (IOException e) {
                        System.out.print("Error try agian");
                    }
                    try {
                        coin4 = ImageIO.read(co4);
                    } catch (IOException e) {
                        System.out.print("Error try agian");
                    }
                    try {
                        coin5 = ImageIO.read(co5);
                    } catch (IOException e) {
                        System.out.print("Error try agian");
                    }
                    try {
                        coin6 = ImageIO.read(co6);
                    } catch (IOException e) {
                        System.out.print("Error try agian");
                    }
                    g.drawImage(coin1, peice1X + 40, peice1Y, this);
                    c1 = new Rectangle(c1x, c1y, 20, 20);
                    cns.add(c1);
                    g.drawImage(coin2, peice2X + 40, peice2Y, this);
                    c2 = new Rectangle(c2x, c2y, 20, 20);
                    cns.add(c2);
                    g.drawImage(coin3, peice3X + 40, peice3Y, this);
                    c3 = new Rectangle(c3x, c3y, 20, 20);
                    cns.add(c3);
                    g.drawImage(coin4, peice4X + 40, peice4Y, this);
                    c4 = new Rectangle(c4x, c4y, 20, 20);
                    cns.add(c4);
                    g.drawImage(coin5, peice5X + 40, peice5Y, this);
                    c5 = new Rectangle(c5x, c5y, 20, 20);
                    cns.add(c5);
                    g.drawImage(coin6, peice6X + 40, peice6Y, this);
                    c6 = new Rectangle(c6x, c6y, 20, 20);
                    cns.add(c6);
                    break;
            }
        }
        Rectangle top1 = new Rectangle(60, 250, 80, 5);
        Rectangle top2 = new Rectangle(160, 150, 80, 5);
        Rectangle top3 = new Rectangle(260, 30, 80, 5);
        Rectangle top4 = new Rectangle(360, 10, 80, 5);
        Rectangle top5 = new Rectangle(460, 190, 80, 15);
        Rectangle top6 = new Rectangle(560, 125, 80, 5);
        Rectangle end1 = new Rectangle(60, 520, 80, 5);
        Rectangle end2 = new Rectangle(160, 420, 80, 5);
        Rectangle end3 = new Rectangle(260, 300, 80, 5);
        Rectangle end4 = new Rectangle(360, 180, 80, 5);
        Rectangle end5 = new Rectangle(460, 50, 80, 5);
        Rectangle end6 = new Rectangle(560, 400, 80, 5);
        if (p1.intersects(top1)) {
            up1 = false;
        }
        if (p1.intersects(end1)) {
            up1 = true;
        }
        if (p2.intersects(top2)) {
            up2 = false;
        }
        if (p2.intersects(end2)) {
            up2 = true;
        }
        if (p3.intersects(top3)) {
            up3 = false;
        }
        if (p3.intersects(end3)) {
            up3 = true;
        }
        if (p4.intersects(top4)) {
            up4 = false;
        }
        if (p4.intersects(end4)) {
            up4 = true;
        }
        if (p5.intersects(top5)) {
            up5 = false;
        }
        if (p5.intersects(end5)) {
            up5 = true;
        }
        if (p6.intersects(top6)) {
            up6 = false;
        }
        if (p6.intersects(end6)) {
            up6 = true;
        }
        circular();
        UpandDown();
        if (frame2.isShowing() && OneTime) {
            soeffects(zombie);
            soeffects(zombie);
            soeffects(zombie);
            soeffects(zombie);
            soeffects(zombie);
            OneTime = false;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Level3And4.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.soeffects(level3);
        }
// level 4
        try {
            if (score() >= 33) {
                ConvertToL4 = true;
                g.setColor(Color.pink);
                g.setFont(this.indieFlower());
                if (!this.OneTimeL4) {
                    soeffects(Level4);
                    this.SpeechToText("Level four");
                    this.OneTimeL4 = true;
                }
                if (!OneTimeMusic) {
                    effects(l4Music);
                    OneTimeMusic = true;
                }
                DimondX = peice5X + 20;
                DimondY = peice5Y - 10;
                g.drawImage(Dimond, DimondX, DimondY, this);
                DimondRect = new Rectangle(DimondX, DimondY, 45, 45);
                LineCircular();
                g.setColor(brown);
                g2.setStroke(new BasicStroke((float) LineStroke));
                g.drawLine((int) this.Line1XP1, (int) this.Line1YP1, (int) this.Line1XP2, (int) this.Line1YP2);
                LineP1 = new Point2D.Double(this.Line1XP1, this.Line1YP1);
                LineP2 = new Point2D.Double(this.Line1XP2, this.Line1YP2);
                Line = new Line2D.Double(LineP1, LineP2);
                g.setColor(Color.WHITE);
                if (Line.intersects(head)) {
                    this.decreaseEnergy(3);
                    Toolkit.getDefaultToolkit().beep();
                }
                // Waves
                g.setColor(this.DarkOrange);
                g2.setStroke(new BasicStroke((float) WaveStroke));
                for (int i = 0; i < 100; i++) {
                    g.drawLine((int) WaveX, (int) WaveY, i, (int) WaveY + (int) (Math.sin(i * this.WaveLength + this.WaveFreq) * this.WaveFull1));
                    this.Wave1P1 = new Point2D.Double(this.WaveX, this.WaveY);
                    this.Wave1P2 = new Point2D.Double(i, WaveY + (Math.sin(i * this.WaveLength + this.WaveFreq) * this.WaveFull1));
                    Wave1 = new Line2D.Double(this.Wave1P1, this.Wave1P2);
                }
                if (this.Wave1.intersects(this.head)) {
                    this.OneTimeBurn1 = false;
                    this.decreaseEnergy(3);
                    if (!this.OneTimeBurn1) {
                        soeffects(this.Burning);
                        this.OneTimeBurn1 = true;
                    }
                    Toolkit.getDefaultToolkit().beep();
                }
                if (this.Wave1.intersectsLine(Line)) {
                    cl.clip.start();
                    this.OneTimeBurn1 = false;
                    if (!this.OneTimeBurn1) {
                        soeffects(this.Burning);
                        cl.clip.stop();
                        this.OneTimeBurn1 = true;
                    }
                }
                for (int i = 100; i < 200; i++) {
                    g.drawLine((int) WaveX, (int) WaveY, i, (int) WaveY + (int) (Math.sin(i * this.WaveLength + this.WaveFreq) * this.WaveFull2));
                    this.Wave2P1 = new Point2D.Double(this.WaveX, this.WaveY);
                    this.Wave2P2 = new Point2D.Double(i, WaveY + (Math.sin(i * this.WaveLength + this.WaveFreq) * this.WaveFull2));
                    Wave2 = new Line2D.Double(this.Wave2P1, this.Wave2P2);
                }
                if (this.Wave2.intersects(this.head)) {
                    this.OneTimeBurn2 = false;
                    this.decreaseEnergy(3);
                    if (!this.OneTimeBurn2) {
                        soeffects(this.Burning);
                        this.OneTimeBurn2 = true;
                    }
                    Toolkit.getDefaultToolkit().beep();
                }
                if (this.Wave2.intersectsLine(Line)) {
                    cl.clip.start();
                    this.OneTimeBurn2 = false;
                    if (!this.OneTimeBurn2) {
                        soeffects(this.Burning);
                        cl.clip.stop();
                        this.OneTimeBurn2 = true;
                    }
                }
                for (int i = 200; i < 300; i++) {
                    g.drawLine((int) WaveX, (int) WaveY, i, (int) WaveY + (int) (Math.sin(i * this.WaveLength + this.WaveFreq) * this.WaveFull3));
                    this.Wave3P1 = new Point2D.Double(this.WaveX, this.WaveY);
                    this.Wave3P2 = new Point2D.Double(i, WaveY + (Math.sin(i * this.WaveLength + this.WaveFreq) * this.WaveFull3));
                    Wave3 = new Line2D.Double(this.Wave3P1, this.Wave3P2);
                }
                if (this.Wave3.intersects(this.head)) {
                    this.OneTimeBurn3 = false;
                    this.decreaseEnergy(3);
                    if (!this.OneTimeBurn3) {
                        soeffects(this.Burning);
                        this.OneTimeBurn3 = true;
                    }
                    Toolkit.getDefaultToolkit().beep();
                }
                if (this.Wave3.intersectsLine(Line)) {
                    cl.clip.start();
                    this.OneTimeBurn3 = false;
                    if (!this.OneTimeBurn3) {
                        soeffects(this.Burning);
                        cl.clip.stop();
                        this.OneTimeBurn3 = true;
                    }
                }
                for (int i = 300; i < 400; i++) {
                    g.drawLine((int) WaveX, (int) WaveY, i, (int) WaveY + (int) (Math.sin(i * this.WaveLength + this.WaveFreq) * this.WaveFull4));
                    this.Wave4P1 = new Point2D.Double(this.WaveX, this.WaveY);
                    this.Wave4P2 = new Point2D.Double(i, WaveY + (Math.sin(i * this.WaveLength + this.WaveFreq) * this.WaveFull4));
                    Wave4 = new Line2D.Double(this.Wave4P1, this.Wave4P2);
                }
                if (this.Wave4.intersects(this.head)) {
                    this.OneTimeBurn4 = false;
                    this.decreaseEnergy(3);
                    if (!this.OneTimeBurn4) {
                        soeffects(this.Burning);
                        this.OneTimeBurn4 = true;
                    }
                    Toolkit.getDefaultToolkit().beep();
                }
                if (this.Wave4.intersectsLine(Line)) {
                    cl.clip.start();
                    this.OneTimeBurn4 = false;
                    if (!this.OneTimeBurn4) {
                        soeffects(this.Burning);
                        cl.clip.stop();
                        this.OneTimeBurn4 = true;
                    }
                }
                for (int i = 400; i < 500; i++) {
                    g.drawLine((int) WaveX, (int) WaveY, i, (int) WaveY + (int) (Math.sin(i * this.WaveLength + this.WaveFreq) * this.WaveFull5));
                    this.Wave5P1 = new Point2D.Double(this.WaveX, this.WaveY);
                    this.Wave5P2 = new Point2D.Double(i, WaveY + (Math.sin(i * this.WaveLength + this.WaveFreq) * this.WaveFull5));
                    Wave5 = new Line2D.Double(this.Wave5P1, this.Wave5P2);
                }
                if (this.Wave5.intersects(this.head)) {
                    this.OneTimeBurn5 = false;
                    this.decreaseEnergy(3);
                    if (!this.OneTimeBurn5) {
                        soeffects(this.Burning);
                        this.OneTimeBurn5 = true;
                    }
                    Toolkit.getDefaultToolkit().beep();
                }
                if (this.Wave5.intersectsLine(Line)) {
                    cl.clip.start();
                    this.OneTimeBurn5 = false;
                    if (!this.OneTimeBurn5) {
                        soeffects(this.Burning);
                        cl.clip.stop();
                        this.OneTimeBurn5 = true;
                    }
                }
                for (int i = 500; i < 600; i++) {
                    g.drawLine((int) WaveX, (int) WaveY, i, (int) WaveY + (int) (Math.sin(i * this.WaveLength + this.WaveFreq) * this.WaveFull6));
                    this.Wave6P1 = new Point2D.Double(this.WaveX, this.WaveY);
                    this.Wave6P2 = new Point2D.Double(i, WaveY + (Math.sin(i * this.WaveLength + this.WaveFreq) * this.WaveFull6));
                    Wave6 = new Line2D.Double(this.Wave6P1, this.Wave6P2);
                }
                if (this.Wave6.intersects(this.head)) {
                    this.OneTimeBurn6 = false;
                    this.decreaseEnergy(3);
                    if (!this.OneTimeBurn6) {
                        soeffects(this.Burning);
                        this.OneTimeBurn6 = true;
                    }
                    Toolkit.getDefaultToolkit().beep();
                }
                if (this.Wave6.intersectsLine(Line)) {
                    cl.clip.start();
                    this.OneTimeBurn6 = false;
                    if (!this.OneTimeBurn6) {
                        soeffects(this.Burning);
                        cl.clip.stop();
                        this.OneTimeBurn6 = true;
                    }
                }
                for (int i = 600; i < 700; i++) {
                    g.drawLine((int) WaveX, (int) WaveY, i, (int) WaveY + (int) (Math.sin(i * this.WaveLength + this.WaveFreq) * this.WaveFull7));
                    this.Wave7P1 = new Point2D.Double(this.WaveX, this.WaveY);
                    this.Wave7P2 = new Point2D.Double(i, WaveY + (Math.sin(i * this.WaveLength + this.WaveFreq) * this.WaveFull7));
                    Wave7 = new Line2D.Double(this.Wave7P1, this.Wave7P2);
                }
                if (this.Wave7.intersects(this.head)) {
                    this.OneTimeBurn7 = false;
                    this.decreaseEnergy(3);
                    if (!this.OneTimeBurn7) {
                        soeffects(this.Burning);
                        this.OneTimeBurn7 = true;
                    }
                    Toolkit.getDefaultToolkit().beep();
                }
                if (this.Wave7.intersectsLine(Line)) {
                    cl.clip.start();
                    this.OneTimeBurn7 = false;
                    if (!this.OneTimeBurn7) {
                        soeffects(this.Burning);
                        cl.clip.stop();
                        this.OneTimeBurn7 = true;
                    }
                }
                this.WaveFull1 = WaveH1.nextInt(90);
                this.WaveFull2 = WaveH2.nextInt(90);
                this.WaveFull3 = WaveH3.nextInt(90);
                this.WaveFull4 = WaveH4.nextInt(90);
                this.WaveFull5 = WaveH5.nextInt(90);
                this.WaveFull6 = WaveH6.nextInt(90);
                this.WaveFull7 = WaveH7.nextInt(90);
                WaveFreq += 0.04;
                if (head.intersects(DimondRect)) {
                    this.cl.clip.close();
                    this.cl.clip.flush();
                    this.cl.clip.stop();
                    this.cl2.clip.close();
                    this.cl2.clip.stop();
                    this.zombie = null;
                    this.zomb = null;
                    this.l4Music = null;
                    jumping = null;
                    coinSound = null;
                    kill = null;
                    hit = null;
                    ground = null;
                    eat = null;
                    gameOver = null;
                    this.end = true;
                    File vbsScript = new File("sources/l5vbs.vbs");
                    String[] commands = new String[]{"wscript", vbsScript.getAbsolutePath()};
                    try {
                        ProcessBuilder invoke = new ProcessBuilder(commands);
                        Process process = invoke.start();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    }
                    this.runSetPlayerScoreBackgroundThread().execute();
                    frame2.remove(this);
                    frame2.setVisible(false);
                    frame2.dispose();
                    OneTimeEnd = true;
                    System.exit(0);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Level3And4.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.handleGamepad();
    }

    private double calculateCrossSectionalAreaOfStone() {
        int width = this.s1.getWidth(),
                height = this.s1.getHeight();
        double diameterInPixels = (width + height) / 2.0,
                diameterInMeters = diameterInPixels / this.PIXELS_PER_METER,
                radiusInMeters = diameterInMeters / 2.0;
        return Math.PI * radiusInMeters * radiusInMeters;
    }

    private double calculateDisplacementOfStone(final int stoneNumber) {
        double dragForce, netForce, acceleration, d = 0.0;
        switch (stoneNumber) {
            case 0:
            case 1:
                dragForce = 0.5 * this.DRAG_COEFF * this.K
                        * this.stoneA * this.run1 * this.run1;
                netForce = this.STONE_MASS * this.G - dragForce;
                acceleration = netForce / this.STONE_MASS;
                this.run1 += acceleration * 0.016;
                d = this.run1 * 0.016 + 0.5 * acceleration * 0.016 * 0.016;
                break;
            case 2:
                dragForce = 0.5 * this.DRAG_COEFF * this.K
                        * this.stoneA * this.run2 * this.run2;
                netForce = this.STONE_MASS * this.G - dragForce;
                acceleration = netForce / this.STONE_MASS;
                this.run2 += acceleration * 0.016;
                d = this.run2 * 0.016 + 0.5 * acceleration * 0.016 * 0.016;
                break;
            case 3:
                dragForce = 0.5 * this.DRAG_COEFF * this.K
                        * this.stoneA * this.run3 * this.run3;
                netForce = this.STONE_MASS * this.G - dragForce;
                acceleration = netForce / this.STONE_MASS;
                this.run3 += acceleration * 0.016;
                d = this.run3 * 0.016 + 0.5 * acceleration * 0.016 * 0.016;
                break;
            case 4:
                dragForce = 0.5 * this.DRAG_COEFF * this.K
                        * this.stoneA * this.run4 * this.run4;
                netForce = this.STONE_MASS * this.G - dragForce;
                acceleration = netForce / this.STONE_MASS;
                this.run4 += acceleration * 0.016;
                d = this.run4 * 0.016 + 0.5 * acceleration * 0.016 * 0.016;
                break;
            case 5:
                dragForce = 0.5 * this.DRAG_COEFF * this.K
                        * this.stoneA * this.run5 * this.run5;
                netForce = this.STONE_MASS * this.G - dragForce;
                acceleration = netForce / this.STONE_MASS;
                this.run5 += acceleration * 0.016;
                d = this.run5 * 0.016 + 0.5 * acceleration * 0.016 * 0.016;
                break;
        }
        return d;
    }

    private SwingWorker runSetPlayerScoreBackgroundThread() {
        return new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                Level3And4.this.neo4jdatabase.setPlayerScore(Level3And4.this.score());
                return null;
            }
        };
    }

    private void increaseEnergy(int energy) {
        if (this.enG + energy > 70) {
            energy -= (this.enG + energy) - 70;
        }
        this.enG += energy;
    }

    private void decreaseEnergy(int energy) {
        if (this.enG > 3) {
            this.enG -= energy;
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        headX += changeX;
        headY += changeY;
        if (defaultw) {
            swdX = headX - 25;
            swdY = headY - 10;
        }
        if (know1 == 1) {
            swdX = headX - 25;
            swdY = headY - 10;
        }
        if (know2 == 1) {
            swdX = headX + 25;
            swdY = headY - 10;
        }
        if (know1 == 1 && atc) {
            atcX = headX + 30;
            atcY = headY + 10;
        }
        if (know2 == 1 && atc) {
            atcX = headX - 30;
            atcY = headY + 10;
        }
        if (defaultw && atc) {
            atcX = headX + 30;
            atcY = headY + 10;
        }
        if (jump) {
            jump = false;
            fall = true;
        }
        if (fall) {
            changeY += gravity;
        }
        try {
            know();
        } catch (IOException ex) {
            Logger.getLogger(Level3And4.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.repaint();
    }

    private void handleGamepad() {
        this.gamepadHandler.gamepadStatuses[1] = this.gamepadHandler.update();
        if (this.gamepadHandler.gamepadStatuses[0] && !this.gamepadHandler.gamepadStatuses[1]) {
            this.gamepadHandler.gamepadStatuses[0] = this.gamepadHandler.gamepadStatuses[1];
            JOptionPane.showMessageDialog(this, "The gamepad has disconnected.");
            return;
        }
        if (this.gamepadHandler.isUpButtonPressed()) {
            this.upButtonPressed();
        } else if (this.gamepadHandler.isLeftButtonPressed()) {
            this.leftButtonPressed();
        } else if (this.gamepadHandler.isRightButtonPressed()) {
            this.rightButtonPressed();
        } else if (this.gamepadHandler.isXButtonPressed()) {
            this.xButtonPressed();
        } else if (this.gamepadHandler.isAButtonPressed()) {
            this.aButtonPressed();
        }
        this.gamepadHandler.setToCenter();
    }

    private void upButtonPressed() {
        if (this.k1 || this.k2 || this.k3 || this.k4 || this.k5 || this.k6 || this.k7 || this.e1) {
            this.soeffects(this.jumping);
            this.jump = true;
            this.changeY -= 60;
        }
    }

    private void leftButtonPressed() {
        this.defaultw = false;
        this.know2 = 1;
        this.know1 = 0;
        this.changeX -= 15;
    }

    private void rightButtonPressed() {
        this.defaultw = false;
        this.know2 = 0;
        this.know1 = 1;
        this.changeX += 15;
    }

    private void xButtonPressed() {
        this.atc = true;
    }

    private void aButtonPressed() {
        this.soeffects(this.TakePhotoSound);
        this.TakePhoto();
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int move = ke.getKeyCode();
        if (move == VK_LEFT) {
            this.leftButtonPressed();
        }
        if (move == VK_RIGHT) {
            this.rightButtonPressed();
        }
        if (move == VK_UP) {
            this.upButtonPressed();
        }
        if (move == VK_SPACE) {
            this.xButtonPressed();
        }
        if (move == VK_A) {
            this.aButtonPressed();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        changeX = 0;
    }

    public void soeffects(File file) {
        try {
            cl.clip = AudioSystem.getClip();
            cl.clip.open(AudioSystem.getAudioInputStream(file));
            cl.clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
        }
    }

    public final void effects(File file) {
        try {
            cl2.clip = AudioSystem.getClip();
            cl2.clip.open(AudioSystem.getAudioInputStream(file));
            cl2.clip.start();
            cl2.clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
        }
    }

    public void know() throws IOException {
        if (headY == 600) {
            changeY = 0;
            headY = 600;
        }
        if (headX >= 700) {
            headX = 10;
        } else if (headX <= 5) {
            headX = 700;
        }
        if (this.stony1 >= 700) {
            this.number = this.newrun.nextInt(5);
            this.run1 = this.newone.nextInt(8) + 7;
            this.run2 = this.newone.nextInt(8) + 7;
            this.run3 = this.newone.nextInt(8) + 7;
            this.run4 = this.newone.nextInt(8) + 7;
            this.run5 = this.newone.nextInt(8) + 7;
            this.stony1 = 1;
        } else if (this.stony2 >= 700) {
            this.number = this.newrun.nextInt(5);
            this.run2 = this.newone.nextInt(8) + 7;
            this.run1 = this.newone.nextInt(8) + 7;
            this.run3 = this.newone.nextInt(8) + 7;
            this.run4 = this.newone.nextInt(8) + 7;
            this.run5 = this.newone.nextInt(8) + 7;
            this.stony2 = 1;
        } else if (this.stony3 >= 700) {
            this.number = this.newrun.nextInt(5);
            this.run3 = this.newone.nextInt(8) + 7;
            this.run1 = this.newone.nextInt(8) + 7;
            this.run2 = this.newone.nextInt(8) + 7;
            this.run4 = this.newone.nextInt(8) + 7;
            this.run5 = this.newone.nextInt(8) + 7;
            this.stony3 = 1;
        } else if (this.stony4 >= 700) {
            this.number = this.newrun.nextInt(5);
            this.run4 = this.newone.nextInt(8) + 7;
            this.run1 = this.newone.nextInt(8) + 7;
            this.run2 = this.newone.nextInt(8) + 7;
            this.run3 = this.newone.nextInt(8) + 7;
            this.run5 = this.newone.nextInt(8) + 7;
            this.stony4 = 1;
        } else if (this.stony5 >= 700) {
            this.number = this.newrun.nextInt(5);
            this.run4 = this.newone.nextInt(8) + 7;
            this.run1 = this.newone.nextInt(8) + 7;
            this.run2 = this.newone.nextInt(8) + 7;
            this.run3 = this.newone.nextInt(8) + 7;
            this.run5 = this.newone.nextInt(8) + 7;
            this.stony5 = 1;
        }
        if (this.lose && !this.end) {
            soeffects(gameOver);
            int score = this.score();
            int know = JOptionPane.showConfirmDialog(this, "Your Score Is: " + String.valueOf(score) + "\nGame Over\nDo You Want To Play Again?", "Game Over", this.y_n);
            if (know == 1) {
                this.neo4jdatabase.setPlayerScore(score);
                ClosingOperation.closeBackendProgram();
            } else if (know == 0) {
                try {
                    Runtime.getRuntime().exec(this.path);
                } catch (IOException ex) {
                    Logger.getLogger(Level3And4.class.getName()).log(Level.SEVERE, null, ex);
                }
                ClosingOperation.closeBackendProgram();
            }
        }
        if (this.en1X <= 1) {
            this.numbers = this.num.nextInt(10);
            this.r1 = this.enemy.nextInt((20 - 7) + 1);
            this.en1X = 700;
        } else if (this.en2X <= 1) {
            this.numbers = this.num.nextInt(10);
            this.r2 = this.enemy.nextInt((20 - 7) + 1);
            this.en1X = 700;
        } else if (this.en3X <= 1) {
            this.numbers = this.num.nextInt(10);
            this.r3 = this.enemy.nextInt((20 - 7) + 1);
            this.en3X = 700;
        } else if (this.en4X <= 1) {
            this.numbers = this.num.nextInt(10);
            this.r4 = this.enemy.nextInt((20 - 7) + 1);
            this.en4X = 700;
        } else if (this.en5X <= 1) {
            this.numbers = this.num.nextInt(10);
            this.r5 = this.enemy.nextInt((20 - 7) + 1);
            this.en5X = 700;
        } else if (this.en6X <= 1) {
            this.numbers = this.num.nextInt(10);
            this.r6 = this.enemy.nextInt((20 - 7) + 1);
            this.en6X = 700;
        } else if (this.en7X <= 1) {
            this.numbers = this.num.nextInt(10);
            this.r7 = this.enemy.nextInt((20 - 7) + 1);
            this.en7X = 700;
        } else if (this.en8X <= 1) {
            this.numbers = this.num.nextInt(10);
            this.r8 = this.enemy.nextInt((20 - 7) + 1);
            this.en8X = 700;
        } else if (this.en9X <= 1) {
            this.numbers = this.num.nextInt(10);
            this.r9 = this.enemy.nextInt((20 - 7) + 1);
            this.en9X = 700;
        } else if (this.en10X <= 1) {
            this.numbers = this.num.nextInt(10);
            this.r10 = this.enemy.nextInt((20 - 7) + 1);
            this.en10X = 700;
        }
    }

    public int score() throws IOException {
        File scoreTextFile = new File("sources/score.txt");
        String score;
        final String filepath = scoreTextFile.getCanonicalPath();
        try (BufferedReader getScore = new BufferedReader(new FileReader(filepath))) {
            score = getScore.readLine();
            counter = Integer.parseInt(score);
        }
        return counter + coinsPlus;
    }

    public void UpandDown() {
        int m1 = this.randomMovement.nextInt(23);
        while (m1 == 0) {
            m1 = this.randomMovement.nextInt(23);
        }
        int m2 = this.randomMovement.nextInt(23);
        while (m2 == 0) {
            m2 = this.randomMovement.nextInt(23);
        }
        int m3 = this.randomMovement.nextInt(23);
        while (m3 == 0) {
            m3 = this.randomMovement.nextInt(23);
        }
        int m4 = this.randomMovement.nextInt(23);
        while (m4 == 0) {
            m4 = this.randomMovement.nextInt(23);
        }
        int m5 = this.randomMovement.nextInt(18);
        while (m5 == 0) {
            m5 = this.randomMovement.nextInt(18);
        }
        int m6 = this.randomMovement.nextInt(23);
        while (m6 == 0) {
            m6 = this.randomMovement.nextInt(23);
        }
        if (up1) {
            peice1Y -= m1;
            c1y -= m1;
        }
        if (!up1) {
            peice1Y += m1;
            c1y += m1;
        }
        if (up2) {
            peice2Y -= m2;
            c2y -= m2;
        }
        if (!up2) {
            peice2Y += m2;
            c2y += m2;
        }
        if (up3) {
            peice3Y -= m3;
            c3y -= m3;
        }
        if (!up3) {
            peice3Y += m3;
            c3y += m3;
        }
        if (up4) {
            peice4Y -= m4;
            c4y -= m4;
        }
        if (!up4) {
            peice4Y += m4;
            c4y += m4;
        }
        if (up5) {
            peice5Y += m5;
            c5y += m5;
        }
        if (!up5) {
            peice5Y -= m5;
            c5y -= m5;
        }
        if (up6) {
            peice6Y -= m6;
            c6y -= m6;
        }
        if (!up6) {
            peice6Y += m6;
            c6y += m6;
        }
    }

    public strictfp void circular() {
        this.radian += this.radianPls;
        this.peice7X = peice7X + Math.cos(radian) * 16;
        this.peice7Y = peice7Y + Math.sin(radian) * 20;
    }

    public strictfp void LineCircular() {
        this.LineRadian += this.LineRadianPlus;
        this.Line1XP1 = this.Line1XP1 - Math.cos(this.LineRadian) * 100;
        this.Line1YP1 = this.Line1YP1 - Math.sin(this.LineRadian) * 100;
        this.Line1XP2 = this.Line1XP2 + Math.cos(this.LineRadian) * 100;
        this.Line1YP2 = this.Line1YP2 + Math.sin(this.LineRadian) * 100;
    }

    public void TakePhoto() {
        Point FrameLocation = new Point(this.getLocationOnScreen());
        File toKnowFile = null;
        File toSave = new File("Screenshots/screenshotL3" + String.valueOf(NumberOfPhotos) + ".jpg");
        try {
            toKnowFile = File.createTempFile("screenshotL3" + String.valueOf(NumberOfPhotos), ".jpg", new File("Screenshots/"));
        } catch (IOException ex) {
            Logger.getLogger(Graphics.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        if (toKnowFile.exists()) {
            NumberOfPhotos++;
        }
        try {
            PathOfCaptureImage = new File(toSave.getAbsolutePath());
            TakePhoto = new Robot();
            Rectangle ScreenPhoto = new Rectangle(FrameLocation.x, FrameLocation.y, 686, 663);
            capture = TakePhoto.createScreenCapture(ScreenPhoto);
            CapturePhoto = new ImageIcon(capture);
            ImageIO.write(capture, "jpg", PathOfCaptureImage);
        } catch (AWTException | IOException ex) {
            Logger.getLogger(Graphics.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public void SpeechToText(String speak) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice;
        VoiceManager voicM = VoiceManager.getInstance();
        voice = voicM.getVoice("kevin16");
        voice.allocate();
        try {
            voice.speak(speak);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public Font indieFlower() {
        File font = new File("sources/IndieFlower-Regular.ttf");
        File FontFile = new File(font.getAbsolutePath());
        try {
            this.IndieFlower = Font.createFont(Font.TRUETYPE_FONT, FontFile).deriveFont(28f);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            env.registerFont(Font.createFont(Font.TRUETYPE_FONT, FontFile));
            return IndieFlower;
        } catch (FontFormatException ex) {
            Logger.getLogger(Level3And4.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Level3And4.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IndieFlower;
    }
}
