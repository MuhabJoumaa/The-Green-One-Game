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
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_SPACE;
import static java.awt.event.KeyEvent.VK_UP;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.jdesktop.swingx.border.DropShadowBorder;

public class GameGraphics extends JPanel implements ActionListener, KeyListener,
        ProjectileMotionSimulationListener, Runnable {

    Random newone = new Random();
    Random newrun = new Random();
    Random c = new Random();
    Random drop = new Random();
    Random newballSpeed = new Random();
    public int x3 = 47;
    public int y3 = 420;
    public int know1 = 0;
    public int know2 = 0;
    public int know4 = 0;
    public int know5 = 0;
    public boolean know3 = true;
    public int changeX = 1;
    public int changeY;
    public boolean def = true;
    public int waterx = 0;
    public int watery = 650;
    public int co = 150;
    File hea = new File("pics/head.png");
    File dow = new File("pics/down.png");
    File u = new File("pics/up.png");
    File lef = new File("pics/left.png");
    File ea = new File("sounds/eat.wav");
    File sto = new File("pics/ston.png");
    File ap = new File("pics/apl.png");
    File coi = new File("pics/coin.png");
    File fall = new File("sounds/falling.wav");
    File rai = new File("sounds/rain.wav");
    File drow = new File("sounds/drown.wav");
    File coiE = new File("sounds/coinSound.wav");
    File sff = new File("pics/surface.jpg");
    File drr = new File("pics/drop.png");
    File ico = new File("pics/icon.png");
    File ss1 = new File("pics/ston.png");
    File ss2 = new File("pics/ston.png");
    File ss3 = new File("pics/ston.png");
    File ss4 = new File("pics/ston.png");
    File ss5 = new File("pics/ston.png");
    File ww1 = new File("pics/wall.png");
    File ww2 = new File("pics/wall.png");
    File wate = new File("pics/water.png");
    File leve2 = new File("sounds/level2.wav");
    File lm = new File("resources/3118.lm");
    File dic = new File("resources/3118.dic");
    File cld = new File("pics/Cloud.png");
    public boolean knowl3 = false;
    ImageIcon head = new ImageIcon(this.hea.getAbsolutePath());
    ImageIcon down = new ImageIcon(this.dow.getAbsolutePath());
    ImageIcon up = new ImageIcon(this.u.getAbsolutePath());
    ImageIcon left = new ImageIcon(this.lef.getAbsolutePath());
    ImageIcon ston = new ImageIcon(this.sto.getAbsolutePath());
    ImageIcon apl = new ImageIcon(this.ap.getAbsolutePath());
    ImageIcon coin = new ImageIcon(this.coi.getAbsolutePath());
    File falling = new File(this.fall.getAbsolutePath());
    File drowning = new File(this.drow.getAbsolutePath());
    File coinEff = new File(this.coiE.getAbsolutePath());
    File sf = new File(this.sff.getAbsolutePath());
    File dr = new File(this.drr.getAbsolutePath());
    File eat = new File(this.ea.getAbsolutePath());
    ImageIcon icon = new ImageIcon(this.ico.getAbsolutePath());
    File st1 = new File(this.ss1.getAbsolutePath());
    File st2 = new File(this.ss2.getAbsolutePath());
    File st3 = new File(this.ss3.getAbsolutePath());
    File st4 = new File(this.ss4.getAbsolutePath());
    File st5 = new File(this.ss5.getAbsolutePath());
    File wall1Pic = new File(this.ww1.getAbsolutePath());
    File wall2Pic = new File(this.ww2.getAbsolutePath());
    File waterPic = new File(this.wate.getAbsolutePath());
    File level2 = new File(this.leve2.getAbsolutePath());
    File Cl = new File(this.cld.getAbsolutePath());
    public int neX = this.newone.nextInt(551) + 30;
    public int neY = this.newone.nextInt(551);
    public int coinX = this.c.nextInt(551);
    public int coinY = this.c.nextInt(551);
    public int dropX = this.drop.nextInt(700);
    public int dropY = this.drop.nextInt(700);
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
    public int counter = 0;
    public int delay = 3000;
    public int per = 3000;
    public int wall1X = 0;
    public int wall1Y = 260;
    public int wall2X = 600;
    public int wall2Y = 260;
    public double strokeRect = 5.0D;
    public int y_n = 0;
    SplashScreen count = new SplashScreen();
    public boolean lose = false;
    public BufferedImage surface;
    public BufferedImage s1;
    public BufferedImage s2;
    public BufferedImage s3;
    public BufferedImage s4;
    public BufferedImage s5;
    public BufferedImage wat;
    public BufferedImage w1;
    public BufferedImage w2;
    public BufferedImage Cloud;
    public BufferedImage waterdrops;
    public Integer lvl = 1;
    public boolean knowwall = false;
    public int enB = 70;
    public int enG = 70;
    public Color redColor = new Color(255, 0, 0);
    public Color greenColor = new Color(124, 252, 0);
    public String t1 = "faster";
    Soeffects cl = new Soeffects();
    public Timer timer = new Timer(100, this);
    public Timer watertimer;
    File rain;
    public Font IndieFlower;
    File p = new File("THE GREEN ONE GAME.exe");
    public final String path = this.p.getAbsolutePath();
    public File clsn = new File("sounds/collision.wav");
    public File collision = new File(clsn.getAbsolutePath());
    public boolean nextLevel = false;
    File gmo = new File("sounds/gameOver.wav");
    File gameOver = new File(gmo.getAbsolutePath());
    File cnt = new File("sounds/count1.wav");
    File count1 = new File(cnt.getAbsolutePath());
    BufferedImage capture;
    Robot TakePhoto;
    ImageIcon CapturePhoto;
    File PathOfCaptureImage;
    int num = 1;
    File tp = new File("sounds/TakePhoto.wav");
    File TakePhotoSound = new File(tp.getAbsolutePath());
    Rectangle face;
    Rectangle apple;
    Rectangle coins;
    Rectangle stone1;
    Rectangle stone2;
    Rectangle stone3;
    Rectangle stone4;
    Rectangle stone5;
    Rectangle sr1;
    Rectangle sr2;
    Rectangle sr3;
    Rectangle sr4;
    Rectangle sr5;
    Rectangle water;
    Rectangle wall1;
    Rectangle wall2;
    //the snake
    File ri = new File("pics/rightX.png");
    File le = new File("pics/leftX.png");
    File upi = new File("pics/upX.png");
    File dowi = new File("pics/downX.png");
    ImageIcon rightt = new ImageIcon(ri.getAbsolutePath());
    ImageIcon leftt = new ImageIcon(le.getAbsolutePath());
    ImageIcon upp = new ImageIcon(upi.getAbsolutePath());
    ImageIcon downn = new ImageIcon(dowi.getAbsolutePath());
    int headSX = 20;
    int headSY = 60;
    int changeSX = 0;
    int changeSY = 0;
    int rightS = 0;
    int leftS = 0;
    int upS = 0;
    int downS = 0;
    int bodySX = 7;
    int bodySY = 20;
    int newHeadX;
    int newHeadY;
    int newAddX;
    int newAddY;
    int size = 10;
    double beachBall1RotationAngle = 0, beachBall2RotationAngle = 0,
            beachBall3RotationAngle = 0;
    boolean defaultm = true;
    boolean r = false;
    boolean l = false;
    boolean d = false;
    boolean a = false;
    Linked_List<Integer> snakeSX = new Linked_List<>();
    Linked_List<Integer> snakeSY = new Linked_List<>();
    File bo = new File("pics/bodyS.png");
    ImageIcon bodyS = new ImageIcon(bo.getAbsolutePath());
    Rectangle parts;
    // shadows
    DropShadowBorder ShadowOfTgo = new DropShadowBorder();
    int ShadowSize = 16;
    int ShadowCorner = 14;
    double ShadowOpacity = 0.85;
    // smart follow
    final int R = 1;
    int[] dataX = new int[R];
    int[] dataY = new int[R];
    int index, n, i, intervalX, intervalY, newX, newY;
    // Bouncing Ball
    int ball1X, ball1Y, ball1Width, ball1Height, ball1SpeedX, ball1SpeedY;
    boolean ball1MoveLeft, ball1MoveUp;
    int ball2X, ball2Y, ball2Width, ball2Height, ball2SpeedX, ball2SpeedY;
    boolean ball2MoveLeft, ball2MoveUp;
    int ball3X, ball3Y, ball3Width, ball3Height, ball3SpeedX, ball3SpeedY;
    boolean ball3MoveLeft, ball3MoveUp;
    Rectangle wall1Left;
    Rectangle wall1Right;
    Rectangle wall1Up;
    Rectangle wall1Bottom;
    Rectangle wall2Left;
    Rectangle wall2Right;
    Rectangle wall2Up;
    Rectangle wall2Bottom;
    Rectangle ball1Rect;
    Rectangle ball2Rect;
    Rectangle ball3Rect;
    Rectangle st1Left;
    Rectangle st1Right;
    Rectangle st1Up;
    Rectangle st1Bottom;
    Rectangle st2Left;
    Rectangle st2Right;
    Rectangle st2Up;
    Rectangle st2Bottom;
    Rectangle st3Left;
    Rectangle st3Right;
    Rectangle st3Up;
    Rectangle st3Bottom;
    Rectangle st4Left;
    Rectangle st4Right;
    Rectangle st4Up;
    Rectangle st4Bottom;
    Rectangle st5Left;
    Rectangle st5Right;
    Rectangle st5Up;
    Rectangle st5Bottom;
    File beach_ball1 = new File("pics/BeachBall.png");
    File beach_ball2 = new File("pics/BeachBall.png");
    File beach_ball3 = new File("pics/BeachBall.png");
    File beachball1 = new File(beach_ball1.getAbsolutePath());
    File beachball2 = new File(beach_ball2.getAbsolutePath());
    File beachball3 = new File(beach_ball3.getAbsolutePath());
    File bounceball = new File("sounds/BounceBallEffect.wav");
    File BounceBallEffect = new File(bounceball.getAbsolutePath());
    private BufferedImage BeachBall1, BeachBall2, BeachBall3, rotatedBeachBall1,
            rotatedBeachBall2, rotatedBeachBall3;
    private final Neo4jDatabaseManager neo4jdatabase = Neo4jDatabaseManager.getInstance();
    private final GamepadHandler gamepadHandler;
    // artillery gun
    private File artilleryGunBaseFile = new File(new File("pics/artillery_gun_base.png").getAbsolutePath()),
            artilleryGunFile = new File(new File("pics/artillery_gun.png").getAbsolutePath()),
            artilleryGunShotSoundEffect = new File(new File("sounds/artillery_gun_shot_sound_effect.wav").getAbsolutePath()),
            projectileExplosionSoundEffect = new File(new File("sounds/explosion_sound_effect.wav").getAbsolutePath());
    private BufferedImage artilleryGunBase, artilleryGun;
    private final File explosionEffectFile = new File(new File("pics/explosion_effect.gif").getAbsolutePath());
    private BufferedImage[] explosionEffectFrames;
    private int artilleryGunX, artilleryGunY;
    // free fall of the projectile
    private final double K = 1.225, G = 9.81, DRAG_COEFF = 0.47;
    private final int PROJECTILE_SPEED_FACTOR = 30, PIXELS_PER_METER = 100;
    private final ProjectileMotionSimulator projectileMotionSimulator;
    private int coInMS = this.co * 10;
    private int maxNumberOfProjectiles = 6,
            numberOfProjectiles = (int) (Math.random() * (this.maxNumberOfProjectiles - 1) + 1);
    private Projectile[] projectiles = new Projectile[this.numberOfProjectiles];
    // free fall of the stones
    private final double STONE_MASS = 650.5;
    private double stoneA = 0.0;
    // Honrom
    private int circleRadius = 100;
    private int NUMBER_OF_HONROMS = 10;
    private final File honromFile = new File(new File("pics/honrom.png").getAbsolutePath());
    private BufferedImage honromImage;
    private final List<Honrom> honroms = new ArrayList<>(this.NUMBER_OF_HONROMS);

    public GameGraphics(GamepadHandler gamepadHandler) {
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(true);
        this.setBackground(Color.gray);
        this.projectileMotionSimulator = new ProjectileMotionSimulator();
        try {
            this.explosionEffectFrames = GifFramesExtractor.extractFrames(this.explosionEffectFile);
        } catch (IOException ex) {
            Logger.getLogger(GameGraphics.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        this.gamepadHandler = gamepadHandler;
        this.gamepadHandler.gamepadStatuses[0] = this.gamepadHandler.checkConnection();
        try {
            this.surface = ImageIO.read(this.sf);
        } catch (IOException e) {
            System.out.print("Error try agian");
        }
        try {
            this.Cloud = ImageIO.read(this.Cl);
        } catch (IOException e) {
            System.out.print("Error try agian");
        }
        try {
            this.waterdrops = ImageIO.read(this.dr);
        } catch (IOException e) {
            System.out.print("Error try agian");
        }
        try {
            this.w1 = ImageIO.read(this.wall1Pic);
        } catch (IOException e) {
            System.out.print("Error try agian");
        }
        try {
            this.w2 = ImageIO.read(this.wall2Pic);
        } catch (IOException e) {
            System.out.print("Error try agian");
        }
        try {
            this.s1 = ImageIO.read(this.st1);
        } catch (IOException e) {
            System.out.print("Error try agian");
        }
        this.stoneA = this.calculateCrossSectionalAreaOfStone();
        try {
            this.s2 = ImageIO.read(this.st2);
        } catch (IOException e) {
            System.out.print("Error try agian");
        }
        try {
            this.s3 = ImageIO.read(this.st3);
        } catch (IOException e) {
            System.out.print("Error try agian");
        }
        try {
            this.s4 = ImageIO.read(this.st4);
        } catch (IOException e) {
            System.out.print("Error try agian");
        }
        try {
            this.s5 = ImageIO.read(this.st5);
        } catch (IOException e) {
            System.out.print("Error try agian");
        }
        try {
            this.wat = ImageIO.read(this.waterPic);
        } catch (IOException e) {
            System.out.print("Error try agian");
        }
        try {
            this.honromImage = ImageIO.read(this.honromFile);
        } catch (IOException e) {
            System.out.print("Error try agian");
        }
        for (int i = 0; i < this.NUMBER_OF_HONROMS; i++) {
            this.honroms.add(new Honrom(this.circleRadius, 700 / 2, 700 / 2, this.honromImage.getWidth(), this.honromImage.getHeight()));
        }
        try {
            this.BeachBall1 = ImageIO.read(this.beachball1);
            this.rotatedBeachBall1 = this.rotate(this.BeachBall1, this.beachBall1RotationAngle);
            this.BeachBall2 = ImageIO.read(this.beachball2);
            this.rotatedBeachBall2 = this.rotate(this.BeachBall2, this.beachBall2RotationAngle);
            this.BeachBall3 = ImageIO.read(this.beachball3);
            this.rotatedBeachBall3 = this.rotate(this.BeachBall3, this.beachBall3RotationAngle);
        } catch (IOException e) {
            System.out.print("Error try agian");
        }
        try {
            this.artilleryGunBase = ImageIO.read(this.artilleryGunBaseFile);
        } catch (IOException e) {
            System.out.print("Error try agian");
        }
        try {
            this.artilleryGun = ImageIO.read(this.artilleryGunFile);
        } catch (IOException e) {
            System.out.print("Error try agian");
        }
        this.initProjectilesArray(this.numberOfProjectiles);
        if (!this.knowl3) {
            this.rain = new File(this.rai.getAbsolutePath());
        }
        this.ball1Width = 40;
        this.ball1Height = 40;
        this.ball1X = 400;
        this.ball1Y = 100;
        this.ball1SpeedX = 20;
        this.ball1SpeedY = 20;
        this.ball2Width = 40;
        this.ball2Height = 40;
        this.ball2X = 600;
        this.ball2Y = 200;
        this.ball2SpeedX = 20;
        this.ball2SpeedY = 20;
        this.ball3Width = 40;
        this.ball3Height = 40;
        this.ball3X = 200;
        this.ball3Y = 400;
        this.ball3SpeedX = 20;
        this.ball3SpeedY = 20;
        this.timer.start();
    }

    private double calculateCrossSectionalAreaOfProjectile(final BufferedImage projectile) {
        int width = projectile.getWidth(), height = projectile.getHeight();
        double diameterInPixels = (width + height) / 2.0,
                diameterInMeters = diameterInPixels / this.PIXELS_PER_METER,
                radiusInMeters = diameterInMeters / 2.0;
        return Math.PI * radiusInMeters * radiusInMeters;
    }

    private double calculateCrossSectionalAreaOfStone() {
        int width = this.s1.getWidth(),
                height = this.s1.getHeight();
        double diameterInPixels = (width + height) / 2.0,
                diameterInMeters = diameterInPixels / this.PIXELS_PER_METER,
                radiusInMeters = diameterInMeters / 2.0;
        return Math.PI * radiusInMeters * radiusInMeters;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g.drawImage(this.surface, 0, 0, this);
        this.face = new Rectangle(this.x3 + 5, this.y3, 30, 30);
        // honroms drawing
        if (!this.knowwall) {
            Rectangle[] honromsRects = new Rectangle[this.NUMBER_OF_HONROMS];
            g2.setStroke(new BasicStroke((float) this.strokeRect));
            this.drawCircle(g2, 700 / 2, 700 / 2, this.circleRadius);
            for (int i = 0; i < this.NUMBER_OF_HONROMS; i++) {
                Honrom honrom = this.honroms.get(i);
                int honromX = honrom.getX(), honromY = honrom.getY();
                honromsRects[i] = new Rectangle(honromX, honromY, honrom.getWidth(), honrom.getHeight());
                honrom.setCircleRadius(this.circleRadius);
                honrom.move();
                g.drawImage(this.honromImage, honromX, honromY, this);
            }
            this.circleRadius++;
            if (this.circleRadius > 250) {
                this.circleRadius = 100;
            }
            for (Rectangle honromRect : honromsRects) {
                if (this.face.intersects(honromRect)) {
                    this.decreaseEnergy(2);
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        }
        g.fillRect(10, 10, this.enB, 20);
        if (enG <= 30) {
            g.setColor(redColor);
        } else {
            g.setColor(greenColor);
        }
        this.ShadowOfTgo.setShadowColor(Color.BLACK);
        g.fillRect(10, 10, this.enG, 20);
        g2.setStroke(new BasicStroke((float) this.strokeRect));
        g.setColor(Color.RED);
        g2.drawRect(8, 8, 75, 25);
        if (co == 73 || co == 72 || co == 71 || co == 6 || co == 5 || co == 4) {
            g.setColor(redColor);
        } else {
            g.setColor(Color.WHITE);
        }
        g.setFont(this.indieFlower());
        g.drawString(Integer.toString(co), 8, 60);
        String score = "Score:\n" + String.valueOf(this.counter);
        Area cut = new Area(new Ellipse2D.Double((this.waterx + 285), (this.watery - 5), 290.0D, 55.0D));
        this.apple = new Rectangle(this.neX, this.neY, 11, 11);
        this.coins = new Rectangle(this.coinX, this.coinY, 21, 21);
        this.stone1 = new Rectangle(this.stonx1, this.stony1, 90, 90);
        this.stone2 = new Rectangle(this.stonx2, this.stony2, 90, 90);
        this.stone3 = new Rectangle(this.stonx3, this.stony3, 90, 90);
        this.stone4 = new Rectangle(this.stonx4, this.stony4, 90, 90);
        this.stone5 = new Rectangle(this.stonx5, this.stony5, 90, 90);
        this.sr1 = new Rectangle(this.stonx1, this.stony1 + 80, 90, 5);
        this.sr2 = new Rectangle(this.stonx2, this.stony2 + 80, 90, 5);
        this.sr3 = new Rectangle(this.stonx3, this.stony3 + 80, 90, 5);
        this.sr4 = new Rectangle(this.stonx4, this.stony4 + 80, 90, 5);
        this.sr5 = new Rectangle(this.stonx5, this.stony5 + 80, 90, 5);
        this.water = new Rectangle(this.waterx, this.watery + 30, 705, 5);
        this.wall1 = new Rectangle(this.wall1X, this.wall1Y, 170, 170);
        this.wall2 = new Rectangle(this.wall2X, this.wall2Y, 170, 170);
        this.wall1Left = new Rectangle(this.wall1X, this.wall1Y + 20, 50, 130);
        this.wall1Right = new Rectangle(this.wall1X + 120, this.wall1Y + 20, 50, 130);
        this.wall1Up = new Rectangle(this.wall1X, this.wall1Y, 170, 10);
        this.wall1Bottom = new Rectangle(this.wall1X, this.wall1Y + 160, 170, 10);
        this.wall2Left = new Rectangle(this.wall2X, this.wall2Y + 20, 50, 130);
        this.wall2Right = new Rectangle(this.wall2X + 120, this.wall2Y + 20, 50, 130);
        this.wall2Up = new Rectangle(this.wall2X, this.wall2Y, 170, 10);
        this.wall2Bottom = new Rectangle(this.wall2X, this.wall2Y + 160, 170, 10);
        this.ball1Rect = new Rectangle(this.ball1X, this.ball1Y, this.ball1Width, this.ball1Height);
        this.ball2Rect = new Rectangle(this.ball2X, this.ball2Y, this.ball2Width, this.ball2Height);
        this.ball3Rect = new Rectangle(this.ball3X, this.ball3Y, this.ball3Width, this.ball3Height);
        this.st1Left = new Rectangle(this.stonx1, this.stony1 + 20, 30, 50);
        this.st1Right = new Rectangle(this.stonx1 + 60, this.stony1 + 20, 30, 50);
        this.st1Up = new Rectangle(this.stonx1, this.stony1, 90, 10);
        this.st1Bottom = new Rectangle(this.stonx1, this.stony1 + 80, 90, 10);
        this.st2Left = new Rectangle(this.stonx2, this.stony2 + 20, 30, 50);
        this.st2Right = new Rectangle(this.stonx2 + 60, this.stony2 + 20, 30, 50);
        this.st2Up = new Rectangle(this.stonx2, this.stony2, 90, 10);
        this.st2Bottom = new Rectangle(this.stonx2, this.stony2 + 80, 90, 10);
        this.st3Left = new Rectangle(this.stonx3, this.stony3 + 20, 30, 50);
        this.st3Right = new Rectangle(this.stonx3 + 60, this.stony3 + 20, 30, 50);
        this.st3Up = new Rectangle(this.stonx3, this.stony3, 90, 10);
        this.st3Bottom = new Rectangle(this.stonx3, this.stony3 + 80, 90, 10);
        this.st4Left = new Rectangle(this.stonx4, this.stony4 + 20, 30, 50);
        this.st4Right = new Rectangle(this.stonx4 + 60, this.stony4 + 20, 30, 50);
        this.st4Up = new Rectangle(this.stonx4, this.stony4, 90, 10);
        this.st4Bottom = new Rectangle(this.stonx4, this.stony4 + 80, 90, 10);
        this.st5Left = new Rectangle(this.stonx5, this.stony5 + 20, 30, 50);
        this.st5Right = new Rectangle(this.stonx5 + 60, this.stony5 + 20, 30, 50);
        this.st5Up = new Rectangle(this.stonx5, this.stony5, 90, 10);
        this.st5Bottom = new Rectangle(this.stonx5, this.stony5 + 80, 90, 10);
        g.setColor(Color.pink);
        g.setFont(this.indieFlower());
        g.drawString("Level " + String.valueOf(lvl.intValue()), 560, 50);
        if (this.knowwall) {
            Graphics2D g2d = (Graphics2D) g.create();
            this.lvl = 2;
            g.drawImage(this.w1, this.wall1X, this.wall1Y, this);
            g.drawImage(this.w2, this.wall2X, this.wall2Y, this);
            g2d.setColor(this.redColor);
            g2d.drawImage(this.rotatedBeachBall1, this.ball1X, this.ball1Y, this);
            g2d.drawImage(this.rotatedBeachBall2, this.ball2X, this.ball2Y, this);
            g2d.drawImage(this.rotatedBeachBall3, this.ball3X, this.ball3Y, this);
            g2d.dispose();
            if (this.face.intersects(this.ball1Rect)
                    || this.face.intersects(this.ball2Rect)
                    || this.face.intersects(this.ball3Rect)) {
                soeffects(this.BounceBallEffect);
                this.decreaseEnergy(2);
                Toolkit.getDefaultToolkit().beep();
            }
            this.bounceBall();
        }
        Area shape = new Area(water);
        water.add(shape.getBounds2D());
        shape.subtract(cut);
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
        g.setColor(Color.WHITE);
        g.setFont(this.indieFlower());
        g.drawString(score, 565, 25);
        g.setColor(Color.white);
        g.drawImage(this.wat, this.waterx, --this.watery, this);
        if (this.def) {
            this.head.paintIcon(this, g, this.x3, this.y3);
        }
        g.setColor(Color.white);
        this.apl.paintIcon(this, g, this.neX, this.neY);
        this.coin.paintIcon(this, g, this.coinX, this.coinY);
        // shadows
        if (this.know1 == 1) {
            this.left.paintIcon(this, g, this.x3, this.y3);
            this.ShadowOfTgo.setShowRightShadow(true);
            this.ShadowOfTgo.setShowLeftShadow(false);
            this.ShadowOfTgo.setShowTopShadow(false);
            this.ShadowOfTgo.setShowBottomShadow(false);
            this.ShadowOfTgo.setShadowSize(this.ShadowSize);
            this.ShadowOfTgo.setShadowOpacity((float) this.ShadowOpacity);
            this.ShadowOfTgo.setCornerSize(this.ShadowCorner);
            this.ShadowOfTgo.paintBorder(this, g, this.x3 + 13, this.y3, this.left.getIconWidth(), this.left.getIconHeight() + 3);
        } else if (this.know2 == 1) {
            this.head.paintIcon(this, g, this.x3, this.y3);
            this.ShadowOfTgo.setShowLeftShadow(true);
            this.ShadowOfTgo.setShowRightShadow(false);
            this.ShadowOfTgo.setShowTopShadow(false);
            this.ShadowOfTgo.setShowBottomShadow(false);
            this.ShadowOfTgo.setShadowSize(this.ShadowSize);
            this.ShadowOfTgo.setShadowOpacity((float) this.ShadowOpacity);
            this.ShadowOfTgo.setCornerSize(this.ShadowCorner);
            this.ShadowOfTgo.paintBorder(this, g, this.x3 - 15, this.y3, this.head.getIconWidth(), this.head.getIconHeight() + 3);
        } else if (this.know4 == 1) {
            this.up.paintIcon(this, g, this.x3, this.y3);
            this.ShadowOfTgo.setShowBottomShadow(true);
            this.ShadowOfTgo.setShowRightShadow(false);
            this.ShadowOfTgo.setShowLeftShadow(false);
            this.ShadowOfTgo.setShowTopShadow(false);
            this.ShadowOfTgo.setShadowSize(this.ShadowSize);
            this.ShadowOfTgo.setShadowOpacity((float) this.ShadowOpacity);
            this.ShadowOfTgo.setCornerSize(this.ShadowCorner);
            this.ShadowOfTgo.paintBorder(this, g, this.x3, this.y3 + 8, this.up.getIconWidth(), this.up.getIconHeight() + 3);
        } else if (this.know5 == 1) {
            this.down.paintIcon(this, g, this.x3, this.y3);
            this.ShadowOfTgo.setShowTopShadow(true);
            this.ShadowOfTgo.setShowRightShadow(false);
            this.ShadowOfTgo.setShowLeftShadow(false);
            this.ShadowOfTgo.setShowBottomShadow(false);
            this.ShadowOfTgo.setShadowSize(this.ShadowSize - 3);
            this.ShadowOfTgo.setShadowOpacity((float) (this.ShadowOpacity - 0.1));
            this.ShadowOfTgo.setCornerSize(this.ShadowCorner);
            this.ShadowOfTgo.paintBorder(this, g, this.x3, this.y3 - 11, this.down.getIconWidth(), this.down.getIconHeight() + 3);
        }
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
        if (face.intersects(coins)) {
            counter++;
            this.coinX = this.c.nextInt(551) + 30;
            this.coinY = this.c.nextInt(551) + 50;
            this.know3 = false;
            soeffects(this.coinEff);
            Toolkit.getDefaultToolkit().beep();
        }
        if (face.intersects(apple)) {
            this.increaseEnergy(10);
            this.neX = this.newone.nextInt(551) + 30;
            this.neY = this.newone.nextInt(551) + 50;
            this.know3 = false;
            soeffects(this.eat);
        }
        if ((face.intersects(stone1) && stone1.y != 1) || (face.intersects(stone2) && stone2.y != 1)
                || (face.intersects(stone3) && stone3.y != 1) || (face.intersects(stone4) && stone4.y != 1)
                || (face.intersects(stone5) && stone5.y != 1) || (face.intersects(wall1) && face.intersects(wall2))) {
            this.decreaseEnergy(10);
            Toolkit.getDefaultToolkit().beep();
        }
        if (this.enG <= 3) {
            this.lose = true;
            Toolkit.getDefaultToolkit().beep();
        }
        if (wall1.intersects(wall2)) {
            this.soeffects(collision);
            this.wall1X = 0;
            this.wall2X = 600;
            wall();
        }
        if (face.intersects(water)) {
            this.soeffects(this.drowning);
            this.lose = true;
            Toolkit.getDefaultToolkit().beep();
        }
        if (stone1.y == 1 && TheGreenOneGame.know) {
            this.soeffects(this.falling);
        }
        if (sr1.intersects(water) || sr2.intersects(water)
                || sr3.intersects(water) || sr4.intersects(water)
                || sr5.intersects(water)) {
            this.soeffects(this.drowning);
        }
        if (this.knowwall && !this.knowl3) {
            this.wall();
            for (int i = 0; i <= 20; i++) {
                g.drawImage(this.waterdrops, this.dropX, this.dropY, this);
                this.dropX = this.drop.nextInt(700);
                this.dropY = this.drop.nextInt(700);
            }
            this.soeffects(this.rain);
            if (this.co == 50 || this.knowl3) {
                TheGreenOneGame.af.clip.stop();
            }
        }
        try {
            this.printScore(counter);
        } catch (IOException ex) {
            Logger.getLogger(Graphics.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.drawImage(this.Cloud, this.newX, this.newY, this);
        this.InserToarray(this.x3, this.y3);
        // snake painting
        this.snakeSX.add(0, this.headSX);
        this.snakeSX.add(1, this.bodySX);
        this.snakeSY.add(0, this.headSY);
        this.snakeSY.add(1, this.bodySY);
        g.setColor(Color.green);
        if (this.defaultm) {
            this.rightt.paintIcon(this, g, this.headSX, this.headSY);
        }
        if (this.r) {
            this.rightt.paintIcon(this, g, this.headSX, this.headSY);
        }
        if (this.l) {
            this.leftt.paintIcon(this, g, this.headSX, this.headSY);
        }
        if (this.a) {
            this.upp.paintIcon(this, g, this.headSX, this.headSY);
        }
        if (this.d) {
            this.downn.paintIcon(this, g, this.headSX, this.headSY);
        }
        this.bodyS.paintIcon(this, g, this.newHeadX, this.newHeadY);
        this.newHeadX = (int) this.snakeSX.deleteFirst();
        this.snakeSX.addFirst(this.newHeadX);
        this.newHeadY = (int) this.snakeSY.deleteFirst();
        this.snakeSY.addFirst(this.newHeadY);
        Rectangle faceS = new Rectangle(this.headSX, this.headSY, 28, 28);
        for (int i = 2; i <= this.size; i += 2) {
            this.snakeSX.addFirst(this.newAddX);
            this.snakeSY.addFirst(this.newAddY);
            this.newAddX = (int) this.snakeSX.deleteFirst();
            this.newAddY = (int) this.snakeSY.deleteFirst();
            try {
                this.bodyS.paintIcon(this, g, this.snakeSX.getElementAt(i), this.snakeSY.getElementAt(i));
            } catch (NullPointerException ex) {
            }
        }
        for (int i = 6; i < this.size; i++) {
            try {
                this.parts = new Rectangle(this.snakeSX.getElementAt(i), this.snakeSY.getElementAt(i), 15, 15);
                if (this.face.intersects(this.parts)) {
                    this.decreaseEnergy(1);
                    this.ShadowOfTgo.setShadowColor(this.redColor);
                    this.ShadowOfTgo.setShadowSize(this.ShadowSize);
                    this.ShadowOfTgo.setShadowOpacity((float) this.ShadowOpacity);
                    this.ShadowOfTgo.setCornerSize(this.ShadowCorner);
                    this.ShadowOfTgo.paintBorder(this, g, this.parts.x, this.parts.y + 8, 7, 5);
                    Toolkit.getDefaultToolkit().beep();
                }
            } catch (NullPointerException ex) {
            }
        }
        if (this.face.intersects(faceS)) {
            this.decreaseEnergy(1);
            this.ShadowOfTgo.setShadowColor(this.redColor);
            this.ShadowOfTgo.setShadowSize(this.ShadowSize);
            this.ShadowOfTgo.setShadowOpacity((float) this.ShadowOpacity);
            this.ShadowOfTgo.setCornerSize(this.ShadowCorner);
            this.ShadowOfTgo.paintBorder(this, g, faceS.x, faceS.y + 8, 7, 5);
            Toolkit.getDefaultToolkit().beep();
        }
        // artillery gun painting
        int artilleryGunBaseX = -1, artilleryGunBaseY = (this.getHeight() / 2) - 70;
        this.artilleryGunX = artilleryGunBaseX + 4;
        this.artilleryGunY = artilleryGunBaseY + 15;
        boolean canShootTheProjectiles = true;
        if (this.coInMS % 100 == 0) {
            this.numberOfProjectiles = (int) (Math.random() * (this.maxNumberOfProjectiles - 1) + 1);
            this.initProjectilesArray(this.numberOfProjectiles);
        }
        for (Projectile projectile : this.projectiles) {
            if (projectile == null) {
                canShootTheProjectiles = false;
            }
        }
        if (canShootTheProjectiles) {
            for (int i = 0; i < this.projectiles.length; i++) {
                if ((this.coInMS == 1500 || this.coInMS % 35 == 0) || this.projectiles[i].isMoving()) {
                    if (!this.projectiles[i].isMoving()) {
                        this.projectiles[i].getEvenIndexList().clear();
                        this.projectiles[i].getOddIndexList().clear();
                        this.projectiles[i].setVelocity(0.0);
                        this.projectiles[i].setRotationAngle(0.0);
                        this.shootTheProjectile(i, this.getHeight());
                    }
                    int projectileW = this.projectiles[i].getRotatedImage().getWidth(),
                            projectileH = this.projectiles[i].getRotatedImage().getHeight();
                    this.projectiles[i].setMoving(true);
                    if (this.projectiles[i].getCurrentIndexOfPositions() < this.projectiles[i].getEvenIndexList().size() - 1) {
                        double x1, x2, y1, y2, dx, dy, vx, vy;
                        x1 = (int) this.projectiles[i].getEvenIndexList().get(this.projectiles[i].getCurrentIndexOfPositions()).doubleValue();
                        y1 = this.getHeight() - -((int) this.projectiles[i].getOddIndexList().get(this.projectiles[i].getCurrentIndexOfPositions()).doubleValue()) - projectileW;
                        x2 = (int) this.projectiles[i].getEvenIndexList().get(this.projectiles[i].getCurrentIndexOfPositions() + 1).doubleValue();
                        y2 = this.getHeight() - -((int) this.projectiles[i].getOddIndexList().get(this.projectiles[i].getCurrentIndexOfPositions() + 1).doubleValue()) - projectileW;
                        dx = x2 - x1;
                        dy = y2 - y1;
                        vx = dx / 0.016;
                        vy = dy / 0.016;
                        this.projectiles[i].setVelocity(Math.sqrt(vx * vx + vy * vy));
                        this.projectiles[i].setX((int) x1);
                        this.projectiles[i].setY((int) y1);
                        // System.out.println("Projectile: (" + this.projectileX + ", " + this.projectileY + ")");
                        this.projectiles[i].setCurrentIndexOfPositions(this.projectiles[i].getCurrentIndexOfPositions() + this.PROJECTILE_SPEED_FACTOR);
                    } else {
                        double projectileD = this.calculateDisplacementOfProjectile(i);
                        this.projectiles[i].setY(this.projectiles[i].getY() + (int) (projectileD * this.PIXELS_PER_METER));
                    }
                    this.projectiles[i].getRectangle().setRect(this.projectiles[i].getX(), this.projectiles[i].getY(), projectileW, projectileH);
                    this.projectiles[i].setRotationAngle(this.projectiles[i].getRotationAngle() + this.projectiles[i].getVelocity());
                    this.projectiles[i].setRotatedImage(this.rotate(this.projectiles[i].getImage(), this.projectiles[i].getRotationAngle()));
                    if (this.projectiles[i].getY() <= this.getHeight()) {
                        g.drawImage(this.projectiles[i].getRotatedImage(), this.projectiles[i].getX(), this.projectiles[i].getY(), this);
                    } else {
                        this.projectiles[i].setMoving(false);
                    }
                    Rectangle rect = this.projectiles[i].getRectangle();
                    if ((rect.intersects(this.stone1) && this.stone1.y != 1)
                            || (rect.intersects(this.stone2) && this.stone2.y != 1)
                            || (rect.intersects(this.stone3) && this.stone3.y != 1)
                            || (rect.intersects(this.stone4) && this.stone4.y != 1)
                            || (rect.intersects(this.stone5) && this.stone5.y != 1)
                            || (rect.intersects(this.sr1) && this.stone1.y != 1)
                            || (rect.intersects(this.sr2) && this.stone2.y != 1)
                            || (rect.intersects(this.sr3) && this.stone3.y != 1)
                            || (rect.intersects(this.sr4) && this.stone4.y != 1)
                            || (rect.intersects(this.sr5) && this.stone5.y != 1)
                            || ((rect.intersects(this.wall1)
                            || rect.intersects(this.wall2)
                            || rect.intersects(this.wall1Left)
                            || rect.intersects(this.wall1Right)
                            || rect.intersects(this.wall1Up)
                            || rect.intersects(this.wall1Bottom)
                            || rect.intersects(this.wall2Left)
                            || rect.intersects(this.wall2Right)
                            || rect.intersects(this.wall2Up)
                            || rect.intersects(this.wall2Bottom)
                            || rect.intersects(this.ball1Rect)
                            || rect.intersects(this.ball2Rect)
                            || rect.intersects(this.ball3Rect))
                            && this.knowwall)
                            || ((rect.intersects(this.st1Left)
                            || rect.intersects(this.st1Right)
                            || rect.intersects(this.st1Up)
                            || rect.intersects(this.st1Bottom))
                            && this.stone1.y != 1)
                            || ((rect.intersects(this.st2Left)
                            || rect.intersects(this.st2Right)
                            || rect.intersects(this.st2Up)
                            || rect.intersects(this.st2Bottom))
                            && this.stone2.y != 1)
                            || ((rect.intersects(this.st3Left)
                            || rect.intersects(this.st3Right)
                            || rect.intersects(this.st3Up)
                            || rect.intersects(this.st3Bottom))
                            && this.stone3.y != 1)
                            || ((rect.intersects(this.st4Left)
                            || rect.intersects(this.st4Right)
                            || rect.intersects(this.st4Up)
                            || rect.intersects(this.st4Bottom))
                            && this.stone4.y != 1)
                            || ((rect.intersects(this.st5Left)
                            || rect.intersects(this.st5Right)
                            || rect.intersects(this.st5Up)
                            || rect.intersects(this.st5Bottom))
                            && this.stone5.y != 1)
                            || rect.intersects(this.face)
                            || rect.intersects(faceS)) {
                        this.projectiles[i].setMoving(false);
                        for (BufferedImage explosionEffectFrame : this.explosionEffectFrames) {
                            g.drawImage(explosionEffectFrame,
                                    this.projectiles[i].getX() - (projectileW + 20),
                                    this.projectiles[i].getY() - (projectileH + 20),
                                    projectileW + 60,
                                    projectileH + 60,
                                    this);
                        }
                        this.soeffects(this.projectileExplosionSoundEffect);
                        if (rect.intersects(this.face)) {
                            this.decreaseEnergy(4);
                        }
                    }
                }
            }
        }
        g.drawImage(this.artilleryGunBase, artilleryGunBaseX, artilleryGunBaseY, this);
        g.drawImage(this.artilleryGun, this.artilleryGunX, this.artilleryGunY, this);
        for (Projectile projectile : this.projectiles) {
            Rectangle rect = projectile.getRectangle();
            if (rect.intersects(this.water)) {
                this.soeffects(this.drowning);
            }
        }
        this.handleGamepad();
        g.dispose();
    }

    private void drawCircle(Graphics2D g2, int xCenter, int yCenter, int r) {
        g2.drawOval(xCenter - r, yCenter - r, 2 * r, 2 * r);
    }

    private void initProjectilesArray(final int len) {
        this.projectiles = Arrays.copyOf(this.projectiles, len);
        for (int i = 0; i < len; i++) {
            this.projectiles[i] = new Projectile();
            this.projectiles[i].setRotatedImage(this.rotate(this.projectiles[i].getImage(), this.projectiles[i].getRotationAngle()));
            this.projectiles[i].setA(this.calculateCrossSectionalAreaOfProjectile(this.projectiles[i].getImage()));
        }
    }

    private double calculateDisplacementOfProjectile(final int i) {
        double dragForce = 0.5 * this.DRAG_COEFF * this.K
                * this.projectiles[i].getA() * this.projectiles[i].getVelocity() * this.projectiles[i].getVelocity(),
                netForce = this.projectiles[i].getMass() * this.G - dragForce,
                acceleration = netForce / this.projectiles[i].getMass(), d;
        this.projectiles[i].setVelocity(this.projectiles[i].getVelocity() + acceleration * 0.016);
        d = this.projectiles[i].getVelocity() * 0.016 + 0.5 * acceleration * 0.016 * 0.016;
        return d;
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
        if (this.co == 71) {
            if (this.counter < 14) {
                this.soeffects(this.gameOver);
                this.nextLevel = false;
                int know = JOptionPane.showConfirmDialog(this, "Your Score Is: " + String.valueOf(this.counter) + "\n" + "You Must Earn More Coins For The Next Level " + "\nGame Over\nDo You Want To Play Again?", "Game Over", this.y_n);
                if (know == 1) {
                    this.neo4jdatabase.setPlayerScore(this.counter);
                    ClosingOperation.closeBackendProgram();
                } else if (know == 0) {
                    try {
                        Runtime.getRuntime().exec(this.path);
                        ClosingOperation.closeBackendProgram();
                    } catch (IOException ex) {
                        Logger.getLogger(Graphics.class.getName()).log(Level.SEVERE, (String) null, ex);
                    }
                    System.exit(0);
                }
                System.exit(0);
            } else {
                this.maxNumberOfProjectiles = 10;
                this.nextLevel = true;
                this.soeffects(this.level2);
            }
        }
        if (this.co == 70 && nextLevel) {
            this.knowwall = true;
        }
        this.timer.start();
        this.toknow();
        this.x3 += this.changeX;
        this.y3 += this.changeY;
        this.headSX += this.changeSX;
        this.headSY += this.changeSY;
        if (!this.know3) {
            this.know3 = true;
        }
        if (this.watery == 450) {
            this.watery = 650;
        }
        if (this.co == 75 || this.co == 74) {
            this.SpeechToText(this.t1);
        }
        if (this.knowwall) {
            this.beachBall1RotationAngle += this.ball1SpeedX + this.ball1SpeedY;
            this.beachBall2RotationAngle += this.ball2SpeedX + this.ball2SpeedY;
            this.beachBall3RotationAngle += this.ball3SpeedX + this.ball3SpeedY;
            this.rotatedBeachBall1 = this.rotate(this.BeachBall1, this.beachBall1RotationAngle);
            this.rotatedBeachBall2 = this.rotate(this.BeachBall2, this.beachBall2RotationAngle);
            this.rotatedBeachBall3 = this.rotate(this.BeachBall3, this.beachBall3RotationAngle);
        }
        this.coInMS--;
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
        } else if (this.gamepadHandler.isDownButtonPressed()) {
            this.downButtonPressed();
        } else if (this.gamepadHandler.isLeftButtonPressed()) {
            this.leftButtonPressed();
        } else if (this.gamepadHandler.isRightButtonPressed()) {
            this.rightButtonPressed();
        } else if (this.gamepadHandler.isStartButtonPressed()) {
            this.startButtonPressed();
        } else if (this.gamepadHandler.isAButtonPressed()) {
            this.aButtonPressed();
        }
    }

    private BufferedImage rotate(final BufferedImage sphere, final double angle) {
        double radians = Math.toRadians(angle), sin = Math.abs(Math.sin(radians)),
                cos = Math.abs(Math.cos(radians));
        int width = sphere.getWidth(), height = sphere.getHeight(),
                newWidth = (int) Math.floor(width * cos + height * sin),
                newHeight = (int) Math.floor(height * cos + width * sin),
                x = width / 2, y = height / 2;
        BufferedImage rotatedBeachBall = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedBeachBall.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - width) / 2, (newHeight - height) / 2);
        at.rotate(radians, x, y);
        g2d.setTransform(at);
        g2d.drawImage(sphere, 0, 0, this);
        g2d.dispose();
        return rotatedBeachBall;
    }

    private void shootTheProjectile(final int i, final int groundHeight) {
        double v0 = Math.random() * (1500.0 - 250.0) + 250.0,
                projectileInitialY = this.artilleryGunY + 89;
        this.projectiles[i].setMass(Math.random() * (200.2 - 25.1) + 25.1);
        /* System.out.println("h0 = " + projectileInitialY + "m, v0 = " + v0 + "m/s, "
                + "m = " + this.projectileMass + "kg."); */
        this.projectiles[i].setY(0);
        this.projectiles[i].setCurrentIndexOfPositions(0);
        this.soeffects(this.artilleryGunShotSoundEffect);
        this.projectileMotionSimulator.simulateProjectileMotionInBackground(
                i,
                v0,
                this.projectiles[i].getTHETA(), projectileInitialY, this.K,
                this.projectiles[i].getMass(), this.G, groundHeight,
                (ProjectileMotionSimulationListener) this);
    }

    @Override
    public void onProjectileMotionSimulationComplete(final int i, List<List<Double>> projectilePositions) {
        this.projectiles[i].setPositions(projectilePositions);
        this.projectiles[i].setEvenIndexList(projectilePositions.get(0).stream().distinct().collect(Collectors.toList()));
        this.projectiles[i].setOddIndexList(projectilePositions.get(1).stream().distinct().collect(Collectors.toList()));
    }

    private void upButtonPressed() {
        this.def = false;
        this.know4 = 1;
        this.know5 = 0;
        this.know2 = 0;
        this.know1 = 0;
        this.changeX = 0;
        this.changeY = 1;
        this.changeY += 15;
        this.changeY *= -1;
        if (this.upS != 1) {
            if (this.headSY > this.y3) {
                if (this.d && this.know4 == 1) {
                    this.changeSY = 1;
                    this.changeSY += 30;
                    this.changeSY *= -1;
                }
                this.downS = 1;
                this.leftS = 0;
                this.rightS = 0;
                this.changeSX = 0;
                this.changeSY = 1;
                this.changeSY += 30;
                this.changeSY *= -1;
                this.defaultm = false;
                this.l = false;
                this.r = false;
                this.a = true;
                this.d = false;
            }
            if (this.headSY < this.y3) {
                if (this.a && this.know5 == 1) {
                    this.changeSY = 1;
                    this.changeSY *= -1;
                    this.changeSY += 30;
                }
                this.upS = 1;
                this.leftS = 0;
                this.rightS = 0;
                this.changeSX = 0;
                this.changeSY = 1;
                this.changeSY *= -1;
                this.changeSY += 30;
                this.defaultm = false;
                this.l = false;
                this.r = false;
                this.a = false;
                this.d = true;
            }
        }
    }

    private void downButtonPressed() {
        this.def = false;
        this.know5 = 1;
        this.know1 = 0;
        this.know2 = 0;
        this.know4 = 0;
        this.changeX = 0;
        this.changeY = 1;
        this.changeY *= -1;
        this.changeY += 15;
        if (this.downS != 1) {
            if (this.headSY < this.y3) {
                if (this.a && this.know5 == 1) {
                    this.changeSY = 1;
                    this.changeSY *= -1;
                    this.changeSY += 30;
                }
                this.upS = 1;
                this.leftS = 0;
                this.rightS = 0;
                this.changeSX = 0;
                this.changeSY = 1;
                this.changeSY *= -1;
                this.changeSY += 30;
                this.defaultm = false;
                this.l = false;
                this.r = false;
                this.a = false;
                this.d = true;
            }
            if (this.headSY > this.y3) {
                if (this.d && this.know4 == 1) {
                    this.changeSY = 1;
                    this.changeSY += 30;
                    this.changeSY *= -1;
                }
                this.downS = 1;
                this.leftS = 0;
                this.rightS = 0;
                this.changeSX = 0;
                this.changeSY = 1;
                this.changeSY += 30;
                this.changeSY *= -1;
                this.defaultm = false;
                this.l = false;
                this.r = false;
                this.a = true;
                this.d = false;
            }
        }
    }

    private void leftButtonPressed() {
        this.def = false;
        this.know1 = 1;
        this.know2 = 0;
        this.know4 = 0;
        this.know5 = 0;
        this.changeY = 0;
        this.changeX += -15;
        if (this.leftS != 1) {
            if (this.headSX > this.x3) {
                if (this.r && this.know1 == 1) {
                    this.changeSX += -30;
                }
                this.rightS = 1;
                this.upS = 0;
                this.downS = 0;
                this.changeSY = 0;
                this.changeSX += -30;
                this.defaultm = false;
                this.l = true;
                this.r = false;
                this.a = false;
                this.d = false;
            }
            if (this.headSX < this.x3) {
                if (this.l && this.know2 == 1) {
                    this.changeSX += 25;
                }
                this.leftS = 1;
                this.upS = 0;
                this.downS = 0;
                this.changeSY = 0;
                this.changeSX += 25;
                this.defaultm = false;
                this.l = false;
                this.r = true;
                this.a = false;
                this.d = false;
            }
        }
    }

    private void rightButtonPressed() {
        this.def = false;
        this.know2 = 1;
        this.know1 = 0;
        this.know4 = 0;
        this.know5 = 0;
        this.changeY = 0;
        this.changeX += 10;
        if (this.rightS != 1) {
            if (this.headSX < this.x3) {
                if (this.l && this.know2 == 1) {
                    this.changeSX += 25;
                }
                this.leftS = 1;
                this.upS = 0;
                this.downS = 0;
                this.changeSY = 0;
                this.changeSX += 25;
                this.defaultm = false;
                this.l = false;
                this.r = true;
                this.a = false;
                this.d = false;
            }
            if (this.headSX > this.x3) {
                if (this.r && this.know1 == 1) {
                    this.changeSX += -30;
                }
                this.rightS = 1;
                this.upS = 0;
                this.downS = 0;
                this.changeSY = 0;
                this.changeSX += -30;
                this.defaultm = false;
                this.l = true;
                this.r = false;
                this.a = false;
                this.d = false;
            }
        }
    }

    private void startButtonPressed() {
        this.changeX = 0;
        this.changeY = 0;
        JOptionPane.showMessageDialog(this, "You Paused The Game.\nPress Ok To Continue.");
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
        if (move == VK_UP) {
            this.upButtonPressed();
        }
        if (move == VK_DOWN) {
            this.downButtonPressed();
        }
        if (move == VK_RIGHT) {
            this.rightButtonPressed();
        }
        if (move == VK_SPACE) {
            this.startButtonPressed();
        }
        if (move == VK_A) {
            this.aButtonPressed();
        }
        //snake movement
        int moveS = ke.getKeyCode();
        if (moveS == VK_LEFT && leftS != 1) {
            if (headSX > x3) {
                if (r && know1 == 1) {
                    this.changeSX += -30;
                }
                rightS = 1;
                upS = 0;
                downS = 0;
                this.changeSY = 0;
                this.changeSX += -30;
                defaultm = false;
                l = true;
                r = false;
                a = false;
                d = false;
            }
            if (headSX < x3) {
                if (l && know2 == 1) {
                    this.changeSX += 25;
                }
                leftS = 1;
                upS = 0;
                downS = 0;
                this.changeSY = 0;
                this.changeSX += 25;
                defaultm = false;
                l = false;
                r = true;
                a = false;
                d = false;
            }
        }
        if (moveS == VK_RIGHT && rightS != 1) {
            if (headSX < x3) {
                if (l && know2 == 1) {
                    this.changeSX += 25;
                }
                leftS = 1;
                upS = 0;
                downS = 0;
                this.changeSY = 0;
                this.changeSX += 25;
                defaultm = false;
                l = false;
                r = true;
                a = false;
                d = false;
            }
            if (headSX > x3) {
                if (r && know1 == 1) {
                    this.changeSX += -30;
                }
                rightS = 1;
                upS = 0;
                downS = 0;
                this.changeSY = 0;
                this.changeSX += -30;
                defaultm = false;
                l = true;
                r = false;
                a = false;
                d = false;
            }
        }
        if (moveS == VK_UP && upS != 1) {
            if (headSY > y3) {
                if (d && know4 == 1) {
                    this.changeSY = 1;
                    this.changeSY += 30;
                    this.changeSY *= -1;
                }
                downS = 1;
                leftS = 0;
                rightS = 0;
                this.changeSX = 0;
                this.changeSY = 1;
                this.changeSY += 30;
                this.changeSY *= -1;
                defaultm = false;
                l = false;
                r = false;
                a = true;
                d = false;
            }
            if (headSY < y3) {
                if (a && know5 == 1) {
                    this.changeSY = 1;
                    this.changeSY *= -1;
                    this.changeSY += 30;
                }
                upS = 1;
                leftS = 0;
                rightS = 0;
                this.changeSX = 0;
                this.changeSY = 1;
                this.changeSY *= -1;
                this.changeSY += 30;
                defaultm = false;
                l = false;
                r = false;
                a = false;
                d = true;
            }
        }
        if (moveS == VK_DOWN && downS != 1) {
            if (headSY < y3) {
                if (a && know5 == 1) {
                    this.changeSY = 1;
                    this.changeSY *= -1;
                    this.changeSY += 30;
                }
                upS = 1;
                leftS = 0;
                rightS = 0;
                this.changeSX = 0;
                this.changeSY = 1;
                this.changeSY *= -1;
                this.changeSY += 30;
                defaultm = false;
                l = false;
                r = false;
                a = false;
                d = true;
            }
            if (headSY > y3) {
                if (d && know4 == 1) {
                    this.changeSY = 1;
                    this.changeSY += 30;
                    this.changeSY *= -1;
                }
                downS = 1;
                leftS = 0;
                rightS = 0;
                this.changeSX = 0;
                this.changeSY = 1;
                this.changeSY += 30;
                this.changeSY *= -1;
                defaultm = false;
                l = false;
                r = false;
                a = true;
                d = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    // smart way

    public void InserToarray(int X, int Y) {
        this.intervalX = (int) (X / 1.2);
        this.intervalY = (int) (Y / 1.2);
        int[] arrX = new int[abs(intervalX)];
        int[] arrY = new int[abs(intervalY)];
        int maxX = intervalX;
        int minX = X - 100;
        int rangeX = maxX - minX + 1;
        int maxY = intervalY;
        int minY = Y - 100;
        int rangeY = maxY - minY + 1;
        for (int i = 0; i < arrX.length; i++) {
            arrX[i] = (int) (Math.random() * rangeX) + minX;
        }
        for (int i = 0; i < arrY.length; i++) {
            arrY[i] = (int) (Math.random() * rangeY) + minY;
        }
        this.doCombinationX(arrX, arrX.length - 1, R, 0, dataX, 0);
        this.doCombinationY(arrY, arrY.length - 1, R, 0, dataY, 0);
    }

    public void doCombinationX(int arrX[], int n, int r, int index, int dataX[], int i) {
        if (index == r) {
            this.newX = dataX[0];
        }
        if (i > n) {
            i--;
        } else {
            if (index != r) {
                dataX[index] = (int) (arrX[i] * 1.7);
                this.doCombinationX(arrX, n, r, index + 1, dataX, i + 1);
                this.doCombinationX(arrX, n, r, index, dataX, i + 1);
                if (index > 0) {
                    index--;
                }
            }
        }
    }

    public void doCombinationY(int arrY[], int n, int r, int index, int dataY[], int i) {
        if (index == r) {
            this.newY = dataY[0];
        }
        if (i > n) {
            i--;
        } else {
            if (index != r) {
                dataY[index] = (int) (arrY[i] * 1.7);
                this.doCombinationY(arrY, n, r, index + 1, dataY, i + 1);
                this.doCombinationY(arrY, n, r, index, dataY, i + 1);
                if (index > 0) {
                    index--;
                }
            }
        }
    }

    public void toknow() {
        if (this.lose) {
            soeffects(gameOver);
            int know = JOptionPane.showConfirmDialog(this, "Your Score Is: " + String.valueOf(this.counter) + "\nGame Over\nDo You Want To Play Again?", "Game Over", this.y_n);
            if (know == 1) {
                this.neo4jdatabase.setPlayerScore(this.counter);
                ClosingOperation.closeBackendProgram();
            } else if (know == 0) {
                try {
                    Runtime.getRuntime().exec(this.path);
                    ClosingOperation.closeBackendProgram();
                } catch (IOException ex) {
                    System.out.print("sorry");
                    Logger.getLogger(Graphics.class.getName()).log(Level.SEVERE, (String) null, ex);
                }
            }
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
        if (this.x3 >= 700) {
            this.x3 = 48;
        }
        if (this.x3 <= 0) {
            this.x3 = 695;
        }
        if (this.y3 >= 700) {
            this.y3 = 40;
        }
        if (this.y3 <= 0) {
            this.y3 = 695;
        }
        if (this.headSX >= 700) {
            this.headSX = 20;
        }
        if (this.headSX <= 0) {
            this.headSX = 695;
        }
        if (this.headSY >= 700) {
            this.headSY = 60;
        }
        if (this.headSY <= 0) {
            this.headSY = 695;
        }
    }

    public final void soeffects(File file) {
        try {
            this.cl.clip = AudioSystem.getClip();
            this.cl.clip.open(AudioSystem.getAudioInputStream(file));
            this.cl.clip.start();
        } catch (IOException | javax.sound.sampled.LineUnavailableException | javax.sound.sampled.UnsupportedAudioFileException iOException) {
        }
    }

    public void wall() {
        this.wall1X += 7;
        this.wall2X -= 7;
    }

    public void printScore(int counter) throws IOException {
        File txt = new File("sources/score.txt");
        final String filepath = txt.getCanonicalPath();
        try (PrintWriter print = new PrintWriter(new BufferedWriter(new FileWriter(filepath)))) {
            print.print(counter);
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

    public void TakePhoto() {
        Point FrameLocation = new Point(this.getLocationOnScreen());
        File toKnowFile = null;
        File toSave = new File("Screenshots/screenshot" + String.valueOf(num) + ".jpg");
        try {
            toKnowFile = File.createTempFile("screenshot" + String.valueOf(num), ".jpg", new File("Screenshots/"));
        } catch (IOException ex) {
            Logger.getLogger(Graphics.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        if (toKnowFile.exists()) {
            num++;
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

    public Font indieFlower() {
        File font = new File("sources/IndieFlower-Regular.ttf");
        File FontFile = new File(font.getAbsolutePath());
        try {
            this.IndieFlower = Font.createFont(Font.TRUETYPE_FONT, FontFile).deriveFont(28f);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            env.registerFont(Font.createFont(Font.TRUETYPE_FONT, FontFile));
            return IndieFlower;
        } catch (FontFormatException ex) {
            Logger.getLogger(Graphics.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Graphics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IndieFlower;
    }

    public void bounceBall() {
        if (this.ball1Rect.intersects(this.ball2Rect)) {
            this.ball1X -= this.ball1SpeedX;
            this.ball1Y -= this.ball1SpeedY;
            this.ball2X += this.ball2SpeedX;
            this.ball2Y += this.ball2SpeedY;
            this.soeffects(this.BounceBallEffect);
        }
        if (this.ball1Rect.intersects(this.ball3Rect)) {
            this.ball1X -= this.ball1SpeedX;
            this.ball1Y -= this.ball1SpeedY;
            this.ball3X += this.ball3SpeedX;
            this.ball3Y += this.ball3SpeedY;
            this.soeffects(this.BounceBallEffect);
        }
        if (this.ball2Rect.intersects(this.ball1Rect)) {
            this.ball2X -= this.ball2SpeedX;
            this.ball2Y -= this.ball2SpeedY;
            this.ball1X += this.ball1SpeedX;
            this.ball1Y += this.ball1SpeedY;
            this.soeffects(this.BounceBallEffect);
        }
        if (this.ball2Rect.intersects(this.ball3Rect)) {
            this.ball2X -= this.ball2SpeedX;
            this.ball2Y -= this.ball2SpeedY;
            this.ball3X += this.ball3SpeedX;
            this.ball3Y += this.ball3SpeedY;
            this.soeffects(this.BounceBallEffect);
        }
        if (this.ball3Rect.intersects(this.ball1Rect)) {
            this.ball3X -= this.ball3SpeedX;
            this.ball3Y -= this.ball3SpeedY;
            this.ball1X += this.ball1SpeedX;
            this.ball1Y += this.ball1SpeedY;
            this.soeffects(this.BounceBallEffect);
        }
        if (this.ball3Rect.intersects(this.ball2Rect)) {
            this.ball3X -= this.ball3SpeedX;
            this.ball3Y -= this.ball3SpeedY;
            this.ball2X += this.ball2SpeedX;
            this.ball2Y += this.ball2SpeedY;
            this.soeffects(this.BounceBallEffect);
        }

        if (this.ball1X >= (690 - (this.ball1Width + 5)) || this.ball1Rect.intersects(this.wall1Left) || this.ball1Rect.intersects(this.wall2Left)
                || (this.ball1Rect.intersects(this.st1Left) && this.stone1.y != 1) || (this.ball1Rect.intersects(this.st2Left) && this.stone2.y != 1)
                || (this.ball1Rect.intersects(this.st3Left) && this.stone3.y != 1) || (this.ball1Rect.intersects(this.st4Left) && this.stone4.y != 1)
                || (this.ball1Rect.intersects(this.st5Left) && this.stone5.y != 1)) {
            this.ball1MoveLeft = true;
            this.soeffects(this.BounceBallEffect);
        }
        if (this.ball1X <= 10 || this.ball1Rect.intersects(this.wall1Right) || this.ball1Rect.intersects(this.wall2Right)
                || (this.ball1Rect.intersects(this.st1Right) && this.stone1.y != 1) || (this.ball1Rect.intersects(this.st2Right) && this.stone2.y != 1)
                || (this.ball1Rect.intersects(this.st3Right) && this.stone3.y != 1) || (this.ball1Rect.intersects(this.st4Right) && this.stone4.y != 1)
                || (this.ball1Rect.intersects(this.st5Right) && this.stone5.y != 1)) {
            this.ball1MoveLeft = false;
            this.soeffects(this.BounceBallEffect);
        }
        if (this.ball1MoveLeft) {
            this.ball1X -= this.ball1SpeedX;
        } else {
            this.ball1X += this.ball1SpeedX;
        }
        if (this.ball1Y >= (690 - (this.ball1Height + 5)) || this.ball1Rect.intersects(this.wall1Up) || this.ball1Rect.intersects(this.wall2Up)
                || (this.ball1Rect.intersects(this.st1Up) && this.stone1.y != 1) || (this.ball1Rect.intersects(this.st2Up) && this.stone2.y != 1)
                || (this.ball1Rect.intersects(this.st3Up) && this.stone3.y != 1) || (this.ball1Rect.intersects(this.st4Up) && this.stone4.y != 1)
                || (this.ball1Rect.intersects(this.st5Up) && this.stone5.y != 1)) {
            this.ball1MoveUp = true;
            this.soeffects(this.BounceBallEffect);
        }
        if (this.ball1Y <= 10 || this.ball1Rect.intersects(this.wall1Bottom) || this.ball1Rect.intersects(this.wall2Bottom)
                || (this.ball1Rect.intersects(this.st1Bottom) && this.stone1.y != 1) || (this.ball1Rect.intersects(this.st2Bottom) && this.stone2.y != 1)
                || (this.ball1Rect.intersects(this.st3Bottom) && this.stone3.y != 1) || (this.ball1Rect.intersects(this.st4Bottom) && this.stone4.y != 1)
                || (this.ball1Rect.intersects(this.st5Bottom) && this.stone5.y != 1)) {
            this.ball1MoveUp = false;
            this.soeffects(this.BounceBallEffect);
        }
        if (this.ball1MoveUp) {
            this.ball1Y -= this.ball1SpeedY;
        } else {
            this.ball1Y += this.ball1SpeedY;
        }

        if (this.ball2X >= (690 - (this.ball2Width + 5)) || this.ball2Rect.intersects(this.wall1Left) || this.ball2Rect.intersects(this.wall2Left)
                || (this.ball2Rect.intersects(this.st1Left) && this.stone1.y != 1) || (this.ball2Rect.intersects(this.st2Left) && this.stone2.y != 1)
                || (this.ball2Rect.intersects(this.st3Left) && this.stone3.y != 1) || (this.ball2Rect.intersects(this.st4Left) && this.stone4.y != 1)
                || (this.ball2Rect.intersects(this.st5Left) && this.stone5.y != 1)) {
            this.ball2MoveLeft = true;
            this.soeffects(this.BounceBallEffect);
        }
        if (this.ball2X <= 10 || this.ball2Rect.intersects(this.wall1Right) || this.ball2Rect.intersects(this.wall2Right)
                || (this.ball2Rect.intersects(this.st1Right) && this.stone1.y != 1) || (this.ball2Rect.intersects(this.st2Right) && this.stone2.y != 1)
                || (this.ball2Rect.intersects(this.st3Right) && this.stone3.y != 1) || (this.ball2Rect.intersects(this.st4Right) && this.stone4.y != 1)
                || (this.ball2Rect.intersects(this.st5Right) && this.stone5.y != 1)) {
            this.ball2MoveLeft = false;
            this.soeffects(this.BounceBallEffect);
        }
        if (this.ball2MoveLeft) {
            this.ball2X -= this.ball2SpeedX;
        } else {
            this.ball2X += this.ball2SpeedX;
        }
        if (this.ball2Y >= (690 - (this.ball2Height + 5)) || this.ball2Rect.intersects(this.wall1Up) || this.ball2Rect.intersects(this.wall2Up)
                || (this.ball2Rect.intersects(this.st1Up) && this.stone1.y != 1) || (this.ball2Rect.intersects(this.st2Up) && this.stone2.y != 1)
                || (this.ball2Rect.intersects(this.st3Up) && this.stone3.y != 1) || (this.ball2Rect.intersects(this.st4Up) && this.stone4.y != 1)
                || (this.ball2Rect.intersects(this.st5Up) && this.stone5.y != 1)) {
            this.ball2MoveUp = true;
            this.soeffects(this.BounceBallEffect);
        }
        if (this.ball2Y <= 10 || this.ball2Rect.intersects(this.wall1Bottom) || this.ball2Rect.intersects(this.wall2Bottom)
                || (this.ball2Rect.intersects(this.st1Bottom) && this.stone1.y != 1) || (this.ball2Rect.intersects(this.st2Bottom) && this.stone2.y != 1)
                || (this.ball2Rect.intersects(this.st3Bottom) && this.stone3.y != 1) || (this.ball2Rect.intersects(this.st4Bottom) && this.stone4.y != 1)
                || (this.ball2Rect.intersects(this.st5Bottom) && this.stone5.y != 1)) {
            this.ball2MoveUp = false;
            this.soeffects(this.BounceBallEffect);
        }
        if (this.ball2MoveUp) {
            this.ball2Y -= this.ball2SpeedY;
        } else {
            this.ball2Y += this.ball2SpeedY;
        }

        if (this.ball3X >= (690 - (this.ball3Width + 5)) || this.ball3Rect.intersects(this.wall1Left) || this.ball3Rect.intersects(this.wall2Left)
                || (this.ball3Rect.intersects(this.st1Left) && this.stone1.y != 1) || (this.ball3Rect.intersects(this.st2Left) && this.stone2.y != 1)
                || (this.ball3Rect.intersects(this.st3Left) && this.stone3.y != 1) || (this.ball3Rect.intersects(this.st4Left) && this.stone4.y != 1)
                || (this.ball3Rect.intersects(this.st5Left) && this.stone5.y != 1)) {
            this.ball3MoveLeft = true;
            this.soeffects(this.BounceBallEffect);
        }
        if (this.ball3X <= 10 || this.ball3Rect.intersects(this.wall1Right) || this.ball3Rect.intersects(this.wall2Right)
                || (this.ball3Rect.intersects(this.st1Right) && this.stone1.y != 1) || (this.ball3Rect.intersects(this.st2Right) && this.stone2.y != 1)
                || (this.ball3Rect.intersects(this.st3Right) && this.stone3.y != 1) || (this.ball3Rect.intersects(this.st4Right) && this.stone4.y != 1)
                || (this.ball3Rect.intersects(this.st5Right) && this.stone5.y != 1)) {
            this.ball3MoveLeft = false;
            this.soeffects(this.BounceBallEffect);
        }
        if (this.ball3MoveLeft) {
            this.ball3X -= this.ball3SpeedX;
        } else {
            this.ball3X += this.ball3SpeedX;
        }
        if (this.ball3Y >= (690 - (this.ball3Height + 5)) || this.ball3Rect.intersects(this.wall1Up) || this.ball3Rect.intersects(this.wall2Up)
                || (this.ball3Rect.intersects(this.st1Up) && this.stone1.y != 1) || (this.ball3Rect.intersects(this.st2Up) && this.stone2.y != 1)
                || (this.ball3Rect.intersects(this.st3Up) && this.stone3.y != 1) || (this.ball3Rect.intersects(this.st4Up) && this.stone4.y != 1)
                || (this.ball3Rect.intersects(this.st5Up) && this.stone5.y != 1)) {
            this.ball3MoveUp = true;
            this.soeffects(this.BounceBallEffect);
        }
        if (this.ball3Y <= 10 || this.ball3Rect.intersects(this.wall1Bottom) || this.ball3Rect.intersects(this.wall2Bottom)
                || (this.ball3Rect.intersects(this.st1Bottom) && this.stone1.y != 1) || (this.ball3Rect.intersects(this.st2Bottom) && this.stone2.y != 1)
                || (this.ball3Rect.intersects(this.st3Bottom) && this.stone3.y != 1) || (this.ball3Rect.intersects(this.st4Bottom) && this.stone4.y != 1)
                || (this.ball3Rect.intersects(this.st5Bottom) && this.stone5.y != 1)) {
            this.ball3MoveUp = false;
            this.soeffects(this.BounceBallEffect);
        }
        if (this.ball3MoveUp) {
            this.ball3Y -= this.ball3SpeedY;
        } else {
            this.ball3Y += this.ball3SpeedY;
        }
        if (this.ball1Rect.intersects(this.water) || this.ball2Rect.intersects(this.water) || this.ball3Rect.intersects(this.water)) {
            this.soeffects(this.drowning);
        }
        this.ball1SpeedX = this.ball1SpeedY = this.newballSpeed.nextInt(40);
        this.ball2SpeedX = this.ball2SpeedY = this.newballSpeed.nextInt(40);
        this.ball3SpeedX = this.ball3SpeedY = this.newballSpeed.nextInt(40);
    }

    @Override
    public void run() {
    }
}
