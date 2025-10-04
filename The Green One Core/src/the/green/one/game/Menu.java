package the.green.one.game;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_core;
import com.googlecode.javacv.cpp.opencv_highgui;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.SwingUtilities.invokeLater;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import jiconfont.icons.elusive.Elusive;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import static the.green.one.game.TheGreenOneGame.af;
import static the.green.one.game.TheGreenOneGame.frame;
import static the.green.one.game.TheGreenOneGame.gamepadHandler;
import static org.neo4j.driver.Config.TrustStrategy.trustAllCertificates;

public class Menu extends JFrame {

    public int mouseX;
    public int mouseY;
    public String OSname;
    public String OSarch;

    public class MouseClickListener extends MouseAdapter implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent me) {
        }

        @Override
        public void mouseMoved(MouseEvent me) {
            mouseX = me.getX();
            mouseY = me.getY();
            repaint();
        }
    }
    public boolean tostart = false;
    public JButton sound;
    About ob = new About();
    public JButton about;
    public String args[];
    public Rectangle aboutRect;
    public Rectangle startRect;
    public Rectangle exitRect;
    public Rectangle mouse;
    public JButton send;
    public BufferedImage surface;
    private final Sending mail = new Sending();
    File sou = new File("pics/sound.png");
    public final String path1 = sou.getAbsolutePath();
    File souoff = new File("pics/soundoff.png");
    public final String soundof = souoff.getAbsolutePath();
    public ImageIcon snd = new ImageIcon(path1);
    public ImageIcon sndoff = new ImageIcon(soundof);
    File sf1 = new File("pics/bg.png");
    public final String path2 = sf1.getAbsolutePath();
    File sf = new File(path2);
    Color white = new Color(255, 255, 255);
    Color green = new Color(178, 255, 102);
    Color cg1 = new Color(255, 255, 255);
    Color cg2 = new Color(255, 255, 255);
    Color cg3 = new Color(255, 255, 255);
    public boolean turnoff = false;
    public boolean turnon = true;
    public int turning = 0;
    Color LightGreen = new Color(0, 179, 0);
    Border MenuBarBorder;
    String info;
    protected String email;
    File json = new File("sources/info.json");
    final String filepath = json.getAbsolutePath();
    public Connection con;
    public Statement stt;
    public ResultSet res;
    String DeleteQuery;
    String ChangePWDQuery;
    String ChangePhotoQuery;
    String[] Option = {"Take Photo (Basic)", "Take Photo (Advanced 'OpenCV Required')", "Choose Photo"},
            Option1 = {"Take Photo", "Choose Photo", "Remove My Status", "View My Status"},
            lastSeenTurning = {"Visible", "Invisible"};
    public File cam = new File("pics/camera.png");
    public ImageIcon camer = new ImageIcon(cam.getAbsolutePath());
    String[] Ok = {"Take Photo"};
    public static Webcam webcam2;
    public WebcamPanel panel;
    public CanvasFrame frameCanvas;
    public static CanvasFrame frameCanvas2;
    public opencv_highgui.CvCapture Capture;
    public File ProfilePhotoPath;
    public String PhotoPath = "";
    public ImageIcon ProfilePhoto;
    public FileInputStream PhotoInsert;
    File keyicon = new File("pics/KeyIcon.png");
    File accounticon = new File("pics/AccountIcon.png");
    File imageicon = new File("pics/Image.png");
    File speechicon = new File("pics/SpeechIcon.png");
    ImageIcon KeyIcon = new ImageIcon(keyicon.getAbsolutePath());
    ImageIcon AccountIcon = new ImageIcon(accounticon.getAbsolutePath());
    ImageIcon ImageIcon = new ImageIcon(imageicon.getAbsolutePath());
    ImageIcon SpeechIcon = new ImageIcon(speechicon.getAbsolutePath());
    Color LightBlue = new Color(0, 191, 255);
    public boolean TurningComputerSpeech = true;
    Dimension OriginalButtonSize = new Dimension(150, 40);
    Dimension BiggerButtonSize = new Dimension(165, 40);
    Cursor HandCursor = new Cursor(Cursor.HAND_CURSOR);
    Cursor DefaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
    String PhotoQuery;
    String bin;
    ImageIcon AfterBin;
    File searchicon = new File("pics/SearchIcon2.png");
    ImageIcon SearchIcon = new ImageIcon(searchicon.getAbsolutePath());
    public static boolean CheckVisiblity = false;
    public BufferedReader ReadIp;
    public String getIPQuery;
    public URL KnowIP;
    public String ipAddress;
    File msgicon = new File("pics/MessageIcon.png");
    ImageIcon MessageIcon = new ImageIcon(msgicon.getAbsolutePath());
    public Font IndieFlower;
    public String CheckStatusQuery;
    public Color LightRed = new Color(255, 77, 70);
    public File strt = new File("sounds/Start.wav");
    public File ForStart = new File(strt.getAbsolutePath());
    public Soeffects cl = new Soeffects();
    // download update
    public URL link;
    public BufferedReader read;
    public InputStreamReader download;
    public String isNew;
    public final String TheURl = "https://the-green-one-game.s3.us-west-004.backblazeb2.com/update.html";
    public int y_n = 0;
    File p = new File("bin.exe");
    public final String path = p.getAbsolutePath();
    private URL getVersion;
    private BufferedReader readVersionNumber;
    private InputStreamReader getVersionNumber;
    private String isNewVersion;
    private final String VersionURL = "https://the-green-one-game.s3.us-west-004.backblazeb2.com/version.html";
    public BufferedReader readFileOfVersion;
    public final String VersionFilePath = "sources/version.txt";
    public final File updateicon = new File("pics/UpdateIcon.png");
    public final File controllersicon = new File("pics/ControllersIcon.png");
    public final File newsicon = new File("pics/NewsIcon.png");
    public final File rr1 = new File("sources/info.json");
    public ImageIcon UpdateIcon = new ImageIcon(updateicon.getAbsolutePath());
    public ImageIcon NewsIcon = new ImageIcon(newsicon.getAbsolutePath());
    public ImageIcon ControllersIcon = new ImageIcon(controllersicon.getAbsolutePath());
    public File logouticon = new File("pics/logout.png");
    public File blocked_accounts_icon = new File("pics/BlockedIcon.png");
    public File received_messages_icon = new File("pics/ReceivedMessagesIcon.png");
    public ImageIcon LogoutIcon = new ImageIcon(logouticon.getAbsolutePath());
    public ImageIcon BlockedAccountsIcon = new ImageIcon(blocked_accounts_icon.getAbsolutePath());
    public ImageIcon ReceivedMessagesIcon = new ImageIcon(received_messages_icon.getAbsolutePath());
    public Login in;
    private final String DB_URL = this.get_db_url();
    private final String DB_USN = this.get_db_usn();
    private final String DB_PWD = this.get_db_pwd();
    private final String NFT_URL = "https://rarible.com/token/0xc9154424b823b10579895ccbe442d41b9abd96ed:103096473835846565532961317125685978955681480389449358295837594395663277228036";
    private final String NFT_ICON_URL = "https://the-green-one-game.s3.us-west-004.backblazeb2.com/TheGreenOneGameLogo.png";
    private final String Appr1URL = "https://the-green-one-game.s3.us-west-004.backblazeb2.com/Cloud1.png";
    private final String Appr2URL = "https://the-green-one-game.s3.us-west-004.backblazeb2.com/Cloud2.png";
    private final String Appr3URL = "https://the-green-one-game.s3.us-west-004.backblazeb2.com/Cloud3.png";
    private final Neo4jDatabaseManager neo4jdatabase = Neo4jDatabaseManager.getInstance();
    private Icon viewStatusIcon, view;
    private final FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg"),
            filterOfStories = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
    private Boolean[] forSer = {true, true, true};
    private FloatControl floatControl;
    private AudioSettings audioSettingsFrame;
    private final JPanel containerPanel, northPanel, spacerPanel, southPanel;

    public Menu() {
        this.containerPanel = new JPanel();
        this.northPanel = new JPanel();
        this.spacerPanel = new JPanel();
        this.southPanel = new JPanel();
        this.containerPanel.setLayout(new BoxLayout(this.containerPanel, BoxLayout.Y_AXIS));
        this.northPanel.setPreferredSize(new Dimension(400, 450));
        this.northPanel.setLayout(new BorderLayout());
        this.spacerPanel.setPreferredSize(new Dimension(400, 10));
        this.southPanel.setPreferredSize(new Dimension(400, 200));
        this.southPanel.setLayout(new BorderLayout());
        this.getMyUser();
        this.setScoreToNeo4jDatabase();
        this.initComponents();
        this.setAlwaysOnTop(true);
        this.setTitle("Main Menu");
        this.setVisible(false);
        this.setLocation(440, 10);
        this.setResizable(false);
        this.setSize(400, 660);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        MouseClickListener move = new MouseClickListener();
        this.addMouseMotionListener(move);
        this.setIconImage(new ImageIcon(new File("pics/icon.png").getAbsolutePath()).getImage());
        try {
            surface = ImageIO.read(sf);
        } catch (IOException e) {
            System.out.print("Error try agian");
        }
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "error with your interface");
        }
        this.OSname = "Game Edition: " + System.getProperty("os.name");
        this.OSarch = System.getProperty("os.arch");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        IconFontSwing.register(FontAwesome.getIconFont());
        IconFontSwing.register(Elusive.getIconFont());
        Icon playIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PLAY_CIRCLE_OUTLINE, 30, new Color(250, 83, 22));
        Icon aboutIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.INFO_OUTLINE, 30, new Color(250, 83, 22));
        Icon exitIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.EXIT_TO_APP, 30, new Color(250, 83, 22));
        Icon feedbackIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.FEEDBACK, 30, new Color(250, 83, 22));
        Icon myStatusIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.INSERT_EMOTICON, 30, Color.WHITE);
        Icon privacyIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.DEHAZE, 30, Color.BLACK);
        Icon screenIcon = IconFontSwing.buildIcon(Elusive.SCREEN, 30, Color.BLACK);
        Icon audioIcon = IconFontSwing.buildIcon(FontAwesome.AUDIO_DESCRIPTION, 30, Color.BLACK);
        Icon mySpaceIcon = IconFontSwing.buildIcon(Elusive.MYSPACE, 30, Color.BLACK);
        this.view = IconFontSwing.buildIcon(FontAwesome.EYE, 30, Color.BLACK);
        this.viewStatusIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ADD_TO_PHOTOS, 60, Color.GREEN);
        start = new JButton("Start", playIcon);
        sound = new JButton(snd);
        News = new JButton(NewsIcon);
        showControllers = new JButton("Controlling");
        send = new JButton("Send A Feedback", feedbackIcon);
        about = new JButton("About", aboutIcon);
        exit = new JButton("Exit", exitIcon);
        text1 = new JLabel();
        Bar = new JMenuBar();
        ImageChoose = new JFileChooser();
        Network = new JMenu("The Green One Network");
        Account = new JMenu("My Account");
        Store = new JMenu("Store");
        Settings = new JMenu("Settings");
        TurningSpeech = new JMenu("Computer Speech");
        displaySettings = new JMenuItem("Display");
        audioSettings = new JMenuItem("Audio");
        latestScores = new JMenuItem("Latest players' scores");
        privacy = new JMenu("Privacy");
        lastSeen = new JMenuItem("Online Status");
        TurnOn = new JMenuItem("Turn On");
        TurnOff = new JMenuItem("Turn Off");
        FindPlayers = new JMenuItem("Find Players");
        DeleteAccount = new JMenuItem("Delete Account");
        ChangePhoto = new JMenuItem("Change Your Profile Photo");
        ViewMyPhoto = new JMenuItem("View My Profile Photo");
        ChangePWD = new JMenuItem("Change Your Password");
        Messages = new JMenuItem("Messages");
        update = new JMenuItem("Check For Update");
        version = new JMenuItem("Game Version");
        Logout = new JMenuItem("Log Out");
        myStatus = new JMenuItem("My Status");
        Blocked_Accounts = new JMenuItem("Blocked Accounts");
        Received_Messages = new JMenuItem("My Received Messages");
        EnemyAppr = new JMenu("Download Enemy Appearances");
        NFT = new JMenuItem("Purchase the NFT of the game's logo");
        Appr1 = new JMenuItem("Appearance 1");
        Appr2 = new JMenuItem("Appearance 2");
        Appr3 = new JMenuItem("Appearance 3");
        text1.setFont(this.indieFlower(14f));
        ImageChoose.setFont(this.indieFlower(14f));
        Network.setFont(this.indieFlower(14f));
        Account.setFont(this.indieFlower(14f));
        Store.setFont(this.indieFlower(14f));
        Settings.setFont(this.indieFlower(14f));
        TurningSpeech.setFont(this.indieFlower(14f));
        displaySettings.setFont(this.indieFlower(14f));
        audioSettings.setFont(this.indieFlower(14f));
        latestScores.setFont(this.indieFlower(14f));
        privacy.setFont(this.indieFlower(14f));
        update.setFont(this.indieFlower(14f));
        version.setFont(this.indieFlower(14f));
        TurnOn.setFont(this.indieFlower(14f));
        TurnOff.setFont(this.indieFlower(14f));
        lastSeen.setFont(this.indieFlower(14f));
        FindPlayers.setFont(this.indieFlower(14f));
        DeleteAccount.setFont(this.indieFlower(14f));
        ChangePhoto.setFont(this.indieFlower(14f));
        ViewMyPhoto.setFont(this.indieFlower(14f));
        Logout.setFont(this.indieFlower(14f));
        myStatus.setFont(this.indieFlower(14f));
        Blocked_Accounts.setFont(this.indieFlower(14f));
        Received_Messages.setFont(this.indieFlower(14f));
        EnemyAppr.setFont(this.indieFlower(14f));
        NFT.setFont(this.indieFlower(14f));
        Appr1.setFont(this.indieFlower(14f));
        Appr2.setFont(this.indieFlower(14f));
        Appr3.setFont(this.indieFlower(14f));
        ChangePWD.setFont(this.indieFlower(14f));
        Messages.setFont(this.indieFlower(14));
        start.setBackground(new Color(0, 0, 0));
        start.setForeground(cg1);
        start.setBounds(110, 130, 150, 40);
        this.northPanel.add(this.start);
        Bar.setOpaque(true);
        setJMenuBar(Bar);
        Bar.setFont(this.indieFlower(14f));
        Bar.setBackground(Color.GREEN);
        this.MenuBarBorder = BorderFactory.createLineBorder(Color.BLACK);
        Bar.setBorder(MenuBarBorder);
        Bar.add(Network);
        Bar.add(Settings);
        Bar.add(Account);
        Bar.add(Store);
        Settings.add(TurningSpeech);
        Settings.add(displaySettings);
        Settings.add(audioSettings);
        Settings.setOpaque(true);
        Settings.setBackground(LightBlue);
        Messages.setOpaque(true);
        Messages.setBackground(LightGreen);
        Messages.setIcon(MessageIcon);
        TurningSpeech.add(TurnOn);
        TurningSpeech.add(TurnOff);
        TurningSpeech.setOpaque(true);
        TurningSpeech.setBackground(LightBlue);
        TurningSpeech.setIcon(this.SpeechIcon);
        displaySettings.setOpaque(true);
        displaySettings.setBackground(LightBlue);
        displaySettings.setIcon(screenIcon);
        audioSettings.setOpaque(true);
        audioSettings.setBackground(LightBlue);
        audioSettings.setIcon(audioIcon);
        privacy.add(lastSeen);
        privacy.setOpaque(true);
        privacy.setBackground(LightRed);
        privacy.setIcon(privacyIcon);
        lastSeen.setOpaque(true);
        lastSeen.setBackground(LightRed);
        TurnOn.setOpaque(true);
        TurnOff.setOpaque(true);
        update.setOpaque(true);
        update.setBackground(LightGreen);
        version.setOpaque(true);
        version.setBackground(LightGreen);
        TurnOn.setBackground(LightBlue);
        TurnOff.setBackground(LightBlue);
        DeleteAccount.setIcon(this.AccountIcon);
        ViewMyPhoto.setIcon(view);
        update.setIcon(this.UpdateIcon);
        version.setIcon(this.UpdateIcon);
        ChangePWD.setIcon(this.KeyIcon);
        Logout.setIcon(this.LogoutIcon);
        myStatus.setIcon(myStatusIcon);
        Blocked_Accounts.setIcon(this.BlockedAccountsIcon);
        Received_Messages.setIcon(this.ReceivedMessagesIcon);
        try {
            NFT.setIcon(new ImageIcon(new URL(this.NFT_ICON_URL)));
        } catch (MalformedURLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Appr1.setIcon(new ImageIcon(new URL(this.Appr1URL)));
        } catch (MalformedURLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Appr2.setIcon(new ImageIcon(new URL(this.Appr2URL)));
        } catch (MalformedURLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Appr3.setIcon(new ImageIcon(new URL(this.Appr3URL)));
        } catch (MalformedURLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        ChangePhoto.setOpaque(true);
        ViewMyPhoto.setOpaque(true);
        Logout.setOpaque(true);
        myStatus.setOpaque(true);
        Blocked_Accounts.setOpaque(true);
        Received_Messages.setOpaque(true);
        EnemyAppr.setOpaque(true);
        EnemyAppr.setForeground(Color.WHITE);
        NFT.setOpaque(true);
        NFT.setForeground(Color.WHITE);
        Appr1.setOpaque(true);
        Appr1.setForeground(Color.WHITE);
        Appr2.setOpaque(true);
        Appr2.setForeground(Color.WHITE);
        Appr3.setOpaque(true);
        Appr3.setForeground(Color.WHITE);
        ChangePWD.setOpaque(true);
        DeleteAccount.setOpaque(true);
        ChangePhoto.setBackground(LightRed);
        ViewMyPhoto.setBackground(LightRed);
        Logout.setBackground(LightRed);
        myStatus.setBackground(LightRed);
        Blocked_Accounts.setBackground(LightRed);
        Received_Messages.setBackground(LightGreen);
        EnemyAppr.setBackground(Color.BLACK);
        NFT.setBackground(Color.BLACK);
        Appr1.setBackground(Color.BLACK);
        Appr2.setBackground(Color.BLACK);
        Appr3.setBackground(Color.BLACK);
        ChangePWD.setBackground(LightRed);
        DeleteAccount.setBackground(LightRed);
        FindPlayers.setOpaque(true);
        FindPlayers.setBackground(LightGreen);
        FindPlayers.setIcon(SearchIcon);
        latestScores.setOpaque(true);
        latestScores.setBackground(LightGreen);
        latestScores.setIcon(mySpaceIcon);
        Account.add(ChangePhoto);
        Account.add(ViewMyPhoto);
        Account.add(myStatus);
        Account.add(DeleteAccount);
        Account.add(ChangePWD);
        Account.add(Logout);
        Account.add(Blocked_Accounts);
        Account.add(privacy);
        Store.add(NFT);
        Store.add(EnemyAppr);
        EnemyAppr.add(Appr1);
        EnemyAppr.add(Appr2);
        EnemyAppr.add(Appr3);
        Network.add(FindPlayers);
        Network.add(Received_Messages);
        Network.add(Messages);
        Network.add(update);
        Network.add(version);
        Network.add(latestScores);
        News.setBackground(Color.BLACK);
        showControllers.setIcon(ControllersIcon);
        showControllers.setBackground(Color.RED);
        this.northPanel.add(this.News);
        this.northPanel.add(this.showControllers);
        start.addActionListener((ActionEvent evt) -> {
            startActionPerformed(evt);
        });
        DeleteAccount.addActionListener((ActionEvent evt) -> {
            DeleteAccountActionPerformed(evt);
        });
        ChangePWD.addActionListener((ActionEvent evt) -> {
            ChangePWDActionPerformed(evt);
        });
        ChangePhoto.addActionListener((ActionEvent evt) -> {
            ChangePhotoActionPerformed(evt);
        });
        ViewMyPhoto.addActionListener((ActionEvent evt) -> {
            viewMyProfilePhoto(evt);
        });
        Logout.addActionListener((ActionEvent evt) -> {
            LogoutActionPerformed(evt);
        });
        myStatus.addActionListener((ActionEvent evt) -> {
            myStatusActionPerformed(evt);
        });
        Blocked_Accounts.addActionListener((ActionEvent evt) -> {
            BlockedAccountsActionPerformed(evt);
        });
        NFT.addActionListener((ActionEvent evt) -> {
            NFTActionPerformed(evt);
        });
        Appr1.addActionListener((ActionEvent evt) -> {
            Appr1ActionPerformed(evt);
        });
        Appr2.addActionListener((ActionEvent evt) -> {
            Appr2ActionPerformed(evt);
        });
        Appr3.addActionListener((ActionEvent evt) -> {
            Appr3ActionPerformed(evt);
        });
        FindPlayers.addActionListener((ActionEvent evt) -> {
            FindPlayersActionPerformed(evt);
        });
        latestScores.addActionListener((ActionEvent evt) -> {
            LatestScoresActionPerformed(evt);
        });
        Received_Messages.addActionListener((ActionEvent evt) -> {
            ViewReceivedMessagesActionPerformed(evt);
        });
        TurnOn.addActionListener((ActionEvent evt) -> {
            TurnOnActionPerformed(evt);
        });
        TurnOff.addActionListener((ActionEvent evt) -> {
            TurnOffActionPerformed(evt);
        });
        displaySettings.addActionListener((ActionEvent evt) -> {
            displaySettingsActionPerformed(evt);
        });
        audioSettings.addActionListener((ActionEvent evt) -> {
            audioSettingsActionPerformed(evt);
        });
        lastSeen.addActionListener((ActionEvent evt) -> {
            lastSeenActionPerformed(evt);
        });
        Messages.addActionListener((ActionEvent evt) -> {
            MessagesActionPerformed(evt);
        });
        update.addActionListener((ActionEvent evt) -> {
            updateActionPerformed(evt);
        });
        version.addActionListener((ActionEvent evt) -> {
            versionActionPerformed(evt);
        });
        DeleteAccount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(DeleteAccount.getText());
                }
                DeleteAccount.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                DeleteAccount.setForeground(Color.BLACK);
            }
        });
        ChangePWD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(ChangePWD.getText());
                }
                ChangePWD.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                ChangePWD.setForeground(Color.BLACK);
            }
        });
        ChangePhoto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(ChangePhoto.getText());
                }
                ChangePhoto.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                ChangePhoto.setForeground(Color.BLACK);
            }
        });
        ViewMyPhoto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(ViewMyPhoto.getText());
                }
                ViewMyPhoto.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                ViewMyPhoto.setForeground(Color.BLACK);
            }
        });
        Logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(Logout.getText());
                }
                Logout.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                Logout.setForeground(Color.BLACK);
            }
        });
        myStatus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(myStatus.getText());
                }
                myStatus.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                myStatus.setForeground(Color.BLACK);
            }
        });
        Blocked_Accounts.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(Blocked_Accounts.getText());
                }
                Blocked_Accounts.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                Blocked_Accounts.setForeground(Color.BLACK);
            }
        });
        NFT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(NFT.getText());
                }
                NFT.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                NFT.setForeground(Color.WHITE);
            }
        });
        EnemyAppr.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(EnemyAppr.getText());
                }
                EnemyAppr.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                EnemyAppr.setForeground(Color.WHITE);
            }
        });
        Appr1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(Appr1.getText());
                }
                Appr1.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                Appr1.setForeground(Color.WHITE);
            }
        });
        Appr2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(Appr2.getText());
                }
                Appr2.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                Appr2.setForeground(Color.WHITE);
            }
        });
        Appr3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(Appr3.getText());
                }
                Appr3.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                Appr3.setForeground(Color.WHITE);
            }
        });
        FindPlayers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(FindPlayers.getText());
                }
                FindPlayers.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                FindPlayers.setForeground(Color.BLACK);
            }
        });
        latestScores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(latestScores.getText());
                }
                latestScores.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                latestScores.setForeground(Color.BLACK);
            }
        });
        Received_Messages.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(Received_Messages.getText());
                }
                Received_Messages.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                Received_Messages.setForeground(Color.BLACK);
            }
        });
        TurnOn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(TurnOn.getText());
                }
                TurnOn.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                TurnOn.setForeground(Color.BLACK);
            }
        });
        TurnOff.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(TurnOff.getText());
                }
                TurnOff.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                TurnOff.setForeground(Color.BLACK);
            }
        });
        lastSeen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(lastSeen.getText());
                }
                lastSeen.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                lastSeen.setForeground(Color.BLACK);
            }
        });
        TurningSpeech.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(TurningSpeech.getText());
                }
                TurningSpeech.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                TurningSpeech.setForeground(Color.BLACK);
            }
        });
        displaySettings.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(displaySettings.getText());
                }
                displaySettings.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                displaySettings.setForeground(Color.BLACK);
            }
        });
        audioSettings.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(audioSettings.getText());
                }
                audioSettings.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                audioSettings.setForeground(Color.BLACK);
            }
        });
        privacy.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(privacy.getText());
                }
                privacy.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                privacy.setForeground(Color.BLACK);
            }
        });
        Messages.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(Messages.getText());
                }
                Messages.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                Messages.setForeground(Color.BLACK);
            }
        });
        update.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(update.getText());
                }
                update.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                update.setForeground(Color.BLACK);
            }
        });
        version.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText(version.getText());
                }
                version.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                version.setForeground(Color.BLACK);
            }
        });
        start.setFont(this.indieFlower(14f));
        about.setFont(this.indieFlower(14f));
        send.setFont(this.indieFlower(12f));
        exit.setFont(this.indieFlower(14f));
        showControllers.setFont(this.indieFlower(12f));
        sound.setBounds(350, 10, 30, 30);
        News.setBounds(330, 50, 50, 50);
        News.setForeground(Color.BLACK);
        showControllers.setBounds(270, 110, 110, 50);
        showControllers.setForeground(Color.WHITE);
        this.northPanel.add(this.sound);
        sound.addActionListener((ActionEvent evt) -> {
            soundActionPerformed(evt);
        });
        News.addActionListener((ActionEvent evt) -> {
            newsActionPerformed(evt);
        });
        showControllers.addActionListener((ActionEvent evt) -> {
            showControllersActionPerformed(evt);
        });
        News.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText("News");
                }
                News.setBackground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                News.setBackground(Color.BLACK);
            }
        });
        showControllers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if (TurningComputerSpeech) {
                    SpeechToText("Controlling");
                }
                showControllers.setBackground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                showControllers.setBackground(Color.RED);
            }
        });
        about.setBounds(110, 270, 150, 40);
        this.northPanel.add(this.about);
        about.setBackground(new Color(0, 0, 0));
        about.setForeground(cg3);
        about.addActionListener((ActionEvent evt) -> {
            aboutActionPerformed(evt);
        });
        send.setBounds(110, 340, 150, 40);
        this.northPanel.add(this.send);
        send.setBackground(new java.awt.Color(0, 0, 0));
        send.setForeground(cg3);
        send.addActionListener((ActionEvent evt) -> {
            sendActionPerformed(evt);
        });
        send.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                SendHoverEffect(evt);
            }
        });
        send.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent evt) {
                SendNoHoverEffect(evt);
            }
        });
        exit.setBackground(new Color(0, 0, 0));
        exit.setForeground(cg2);
        exit.setBounds(110, 200, 150, 40);
        this.northPanel.add(this.exit);
        exit.addActionListener((ActionEvent evt) -> {
            exitActionPerformed(evt);
        });
        text1.setForeground(new Color(0, 128, 0));
        text1.setText("");
        text1.setPreferredSize(new Dimension(10, 110));
        text1.setHorizontalTextPosition(JLabel.CENTER);
        text1.setVerticalTextPosition(JLabel.TOP);
        News.setPreferredSize(new Dimension(50, 50));
        showControllers.setPreferredSize(new Dimension(50, 50));
        this.northPanel.add(this.text1);
        GLProfile glProfile = GLProfile.getDefault();
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        GLJPanel GLjPanel = new GLJPanel(glCapabilities);
        GLjPanel.addGLEventListener(new JOGLGraphics());
        GLjPanel.setSize(this.southPanel.getPreferredSize());
        FPSAnimator animator = new FPSAnimator(GLjPanel, 60);
        this.southPanel.add(GLjPanel);
        this.containerPanel.add(this.northPanel);
        this.containerPanel.add(this.spacerPanel);
        this.containerPanel.add(this.southPanel);
        this.getContentPane().add(this.containerPanel);
        this.changeBackgroundColor(this.northPanel);
        this.setLocationRelativeTo(null);
        this.CheckProfilePhoto();
        this.setIpAddress();
        this.setData();
        animator.start();
    }

    private void setData() {
        this.forSer = IDSerialzation.getSer().clone();
        this.Visibility(BooleanUtils.toInteger(this.forSer[0]));
        this.setSound(this.forSer[1]);
        this.setComputerSpeech(this.forSer[2]);
    }

    private void lastSeenActionPerformed(ActionEvent evt) {
        int know = JOptionPane.showOptionDialog(this, "You Can Make The Online Status Visible Or Invisible For Everyone.", "Control Your Connection Status On The Network", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, this.view, this.lastSeenTurning, this.lastSeenTurning[BooleanUtils.toInteger(!this.forSer[0])]);
        switch (know) {
            case 0:
                this.forSer[0] = true;
                this.Visibility(1);
                IDSerialzation.doSer(this.forSer);
                break;
            case 1:
                this.forSer[0] = false;
                this.Visibility(0);
                IDSerialzation.doSer(this.forSer);
                break;
            default:
                break;
        }
    }

    private void setSound(boolean b) {
        if (b) {
            this.sound.setIcon(this.snd);
        } else {
            this.sound.setIcon(this.sndoff);
        }
    }

    private void setComputerSpeech(boolean b) {
        this.TurningComputerSpeech = b;
    }

    private void getMyUser() {
        try (BufferedReader getJson = new BufferedReader(new FileReader(filepath))) {
            this.info = getJson.readLine();
            Object o2 = JSONValue.parse(info);
            JSONObject jsonObj2 = (JSONObject) o2;
            this.email = (String) jsonObj2.get("AutoEmail");
            getJson.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setScoreToNeo4jDatabase() {
        File scoreTextFile = new File("sources/score.txt");
        String scoreAsString, filepath = null;
        int score = 0;
        try {
            filepath = scoreTextFile.getCanonicalPath();
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (BufferedReader getScore = new BufferedReader(new FileReader(filepath))) {
            scoreAsString = getScore.readLine();
            if (!scoreAsString.isEmpty()) {
                score = Integer.parseInt(scoreAsString);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.neo4jdatabase.setPlayerScore(this.email.split("@")[0], score);
    }

    public void startActionPerformed(ActionEvent evt) {
        if (gamepadHandler.checkConnection()) {
            JOptionPane.showMessageDialog(this, "A gamepad has been detected and connected.\nYou can now play using the gamepad.");
        }
        this.Visibility(0, this.getDateTime());
        this.setVisible(false);
        this.showControllingInstructions();
        tostart = true;
        frame.setVisible(true);
        try {
            Notification.sendNotification("The Green One Network", "Now You Can Take Screenshots For The Game By Clicking 'a' Button" + "\n" + "Photos Will Be Saved Automatically", TrayIcon.MessageType.INFO);
        } catch (AWTException | MalformedURLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }

    private void showControllingInstructions() {
        try {
            String pic = gamepadHandler.checkConnection() ? "Gamepad.jpeg" : "Controlling.jpg";
            Image img = ImageIO.read(new File("pics/" + pic).getAbsoluteFile());
            Image resizedImg = img.getScaledInstance(618, 462, Image.SCALE_SMOOTH);
            JOptionPane.showMessageDialog(this, "Control Instructions Of The Game", "Control Instructions", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(resizedImg));
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void newsActionPerformed(ActionEvent evt) {
        News news = new News();
        news.setVisible(true);
    }

    public void showControllersActionPerformed(ActionEvent evt) {
        Controlling controlling = new Controlling();
        controlling.setVisible(true);
    }

    public void exitActionPerformed(ActionEvent evt) {
        this.Visibility(0, this.getDateTime());
        ClosingOperation.closeBackendProgram();
        System.exit(0);
    }

    public void updateActionPerformed(ActionEvent evt) {
        this.isUpdate(1);
    }

    public void versionActionPerformed(ActionEvent evt) {
        String ver = this.getVersion();
        JOptionPane.showMessageDialog(this, "Your Game Version Is: " + ver);
    }

    public void soundActionPerformed(ActionEvent evt) {
        switch (BooleanUtils.toInteger(af.clip.isActive())) {
            case 0:
                af.clip.start();
                af.clip.loop(Clip.LOOP_CONTINUOUSLY);
                this.sound.setIcon(snd);
                break;
            case 1:
                af.clip.stop();
                this.sound.setIcon(sndoff);
                break;
            default:
                break;
        }
        if (this.audioSettingsFrame != null && this.audioSettingsFrame.isVisible()) {
            this.audioSettingsFrame.updateVolumeSliderValue();
        }
        this.forSer[1] = af.clip.isActive();
        IDSerialzation.doSer(this.forSer);
    }

    public void aboutActionPerformed(ActionEvent evt) {
        ob.setVisible(true);
    }

    public void sendActionPerformed(ActionEvent evt) {
        this.mail.setEmail(this.email);
        this.mail.setVisible(true);
        if (this.TurningComputerSpeech) {
            this.SpeechToText("please, enter your fullname and choose a password for your account");
        }
    }

    public void DeleteFromBlockingTableFirst() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            DeleteQuery = "DELETE FROM blocking WHERE user = ?";
            PreparedStatement pstt = con.prepareStatement(DeleteQuery);
            pstt.setString(1, this.email);
            pstt.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void DeleteAccountActionPerformed(ActionEvent evt) {
        int know = JOptionPane.showConfirmDialog(this, "Are You Sure You Want To Delete Your Account?", "Delete Account", this.y_n);
        if (know == 0) {
            this.DeleteFromBlockingTableFirst();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
                stt = con.createStatement();
                DeleteQuery = "DELETE FROM account WHERE email = ?";
                PreparedStatement pstt = con.prepareStatement(DeleteQuery);
                pstt.setString(1, this.email);
                pstt.executeUpdate();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage());
            } finally {
                try {
                    stt.close();
                    res.close();
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.neo4jdatabase.deletePlayerNode(this.email.split("@")[0]);
            JSONObject j = new JSONObject();
            j.put("AutoEmail", "example@domain.com");
            j.put("AutoPwd", "12345678");
            try (PrintWriter print = new PrintWriter(new BufferedWriter(new FileWriter(this.rr1.getAbsolutePath())))) {
                print.println(j);
                print.close();
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
            JOptionPane.showMessageDialog(this, "Your Account Has Been Deleted Successfully");
            this.setVisible(false);
            this.in = new Login();
            this.in.setVisible(true);
        }
    }

    public void ChangePhotoActionPerformed(ActionEvent evt) {
        File Image = new File("pics/Image.png");
        ImageIcon ImageSet = new ImageIcon(Image.getAbsolutePath());
        int know = JOptionPane.showOptionDialog(this, "Set Profile Photo", "Open Camera Or Choose A Photo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, ImageSet, this.Option, this.Option[0]);
        switch (know) {
            case 0:
                TakeButton takebutton = new TakeButton(2, false);
                this.TakePhoto2(takebutton);
                takebutton.setVisible(true);
                break;
            case 1:
                this.TakePhoto();
                TakeButton open1 = new TakeButton(2, false);
                open1.setVisible(true);
                break;
            case 2:
                this.ChoosePhoto();
                break;
            default:
                break;
        }
    }

    public void TakePhoto2(TakeButton takebutton) {
        File Image = new File("pics/Image.png");
        ImageIcon ImageSet = new ImageIcon(Image.getAbsolutePath());
        Image Icon = this.camer.getImage();
        webcam2 = Webcam.getDefault();
        if (webcam2 != null) {
            webcam2.setViewSize(WebcamResolution.VGA.getSize());
            panel = new WebcamPanel(webcam2);
            panel.setImageSizeDisplayed(true);
            frameCanvas2 = new CanvasFrame("Camera");
            frameCanvas2.setAlwaysOnTop(true);
            frameCanvas2.setIconImage(Icon);
            frameCanvas2.setDefaultCloseOperation(CanvasFrame.DISPOSE_ON_CLOSE);
            frameCanvas2.setResizable(false);
            frameCanvas2.setSize(700, 600);
            frameCanvas2.setLocation(280, 10);
            frameCanvas2.setLocationRelativeTo(null);
            Point p = frameCanvas2.getLocationOnScreen();
            takebutton.setCanvasFrame(frameCanvas2);
            takebutton.setLocation((int) (p.x + (frameCanvas2.getWidth() * 0.5d - 30)), (int) (p.y + (frameCanvas2.getHeight() + 10)));
            frameCanvas2.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent we) {
                    takebutton.setVisible(false);
                    takebutton.dispose();
                    frameCanvas2.setVisible(false);
                    frameCanvas2.dispose();
                    webcam2.close();
                }
            });
            frameCanvas2.add(panel);
            frameCanvas2.setVisible(true);
            CheckVisiblity = true;
        } else {
            JOptionPane.showMessageDialog(this, "Could Not Detect any WebCam" + "\n" + "Please Check Your Camera", " Error With Camera", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void TakePhoto() {
        Thread CameraRunning;
        File Image = new File("pics/Image.png");
        ImageIcon ImageSet = new ImageIcon(Image.getAbsolutePath());
        Image Icon = this.camer.getImage();
        CameraRunning = new Thread() {
            @Override
            public void run() {
                Capture = opencv_highgui.cvCreateCameraCapture(0);
                opencv_highgui.cvSetCaptureProperty(Capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 600);
                opencv_highgui.cvSetCaptureProperty(Capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 700);
                opencv_core.IplImage IpImage = opencv_highgui.cvQueryFrame(Capture);
                frameCanvas = new CanvasFrame("Camera");
                frameCanvas.setAlwaysOnTop(true);
                frameCanvas.setIconImage(Icon);
                frameCanvas.setDefaultCloseOperation(CanvasFrame.DISPOSE_ON_CLOSE);
                frameCanvas.setResizable(false);
                frameCanvas.setSize(700, 600);
                frameCanvas.setLocation(280, 10);
                while (frameCanvas.isVisible() && (IpImage = opencv_highgui.cvQueryFrame(Capture)) != null) {
                    frameCanvas.showImage(IpImage);
                }
            }
        };
        CameraRunning.start();
    }

    public void ChoosePhoto() {
        ImageChoose.setAcceptAllFileFilterUsed(false);
        ImageChoose.setFileFilter(this.filter);
        ImageChoose.setCurrentDirectory(new File("C:/"));
        ImageChoose.setDialogTitle("Select Your Profile Photo");
        ImageChoose.showOpenDialog(this);
        ProfilePhotoPath = ImageChoose.getSelectedFile();
        try {
            PhotoPath = this.ProfilePhotoPath.getAbsolutePath();
            String extension = FilenameUtils.getExtension(PhotoPath);
            if (extension.equals("jpg")) {
                ProfilePhoto = new ImageIcon(this.PhotoPath);
                try {
                    PhotoInsert = new FileInputStream(this.ProfilePhotoPath);
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
                    stt = con.createStatement();
                    ChangePhotoQuery = "UPDATE account SET photo = ? WHERE email = ?";
                    PreparedStatement pstt = con.prepareStatement(ChangePhotoQuery);
                    PhotoInsert = ImageCompressor.compressImage(PhotoInsert);
                    pstt.setBlob(1, PhotoInsert);
                    pstt.setString(2, this.email);
                    pstt.executeUpdate();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, ex.getMessage() + "\n" + "Your Image Is Incorrect");
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(ex.getMessage());
                } finally {
                    try {
                        stt.close();
                        res.close();
                        con.close();
                        PhotoInsert.close();
                    } catch (SQLException | IOException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                this.CheckProfilePhoto();
                JOptionPane.showMessageDialog(this, "Your Profile Photo Has Been Changed");
            } else {
                JOptionPane.showMessageDialog(this, "Please, Add A JPG Image.", "Error With The Image", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NullPointerException ex) {
        }
    }

    public void ChoosePhoto(boolean b) {
        try {
            ImageChoose.setAcceptAllFileFilterUsed(false);
            ImageChoose.setFileFilter(this.filterOfStories);
            ImageChoose.setCurrentDirectory(new File("C:/"));
            ImageChoose.setDialogTitle("Select Your Story");
            ImageChoose.showOpenDialog(this);
            ProfilePhotoPath = ImageChoose.getSelectedFile();
            try {
                PhotoPath = this.ProfilePhotoPath.getAbsolutePath();
                String extension = FilenameUtils.getExtension(PhotoPath);
                if (extension.equals("jpg") || extension.equals("gif")) {
                    if (extension.equals("jpg")) {
                        ProfilePhoto = new ImageIcon(this.PhotoPath);
                        BufferedImage statusimage = new BufferedImage(ProfilePhoto.getIconWidth(), ProfilePhoto.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                        Graphics graphics = statusimage.createGraphics();
                        ProfilePhoto.paintIcon(null, graphics, 0, 0);
                        graphics.dispose();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write(statusimage, "jpg", baos);
                        byte[] imageBytes = baos.toByteArray();
                        this.neo4jdatabase.setPlayerStatus(this.email, imageBytes);
                    } else if (extension.equals("gif")) {
                        Path pathOfGIFImage = Paths.get(PhotoPath);
                        byte[] data = Files.readAllBytes(pathOfGIFImage);
                        this.neo4jdatabase.setPlayerStatus(this.email, data);
                    }
                    JOptionPane.showMessageDialog(this, "Your Status Has Been Created Successfully.\nYour Status Is Public For All Players Of The Green One Game.");
                } else {
                    JOptionPane.showMessageDialog(this, "Please, Add A JPG Or GIF Image.", "Error With The Image", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NullPointerException ex) {
            }
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public boolean isPasswordExist(String pwd) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            String ChangePwdQuery = "SELECT pwd FROM account WHERE email='" + email + "'";
            PreparedStatement pstt = con.prepareStatement(ChangePwdQuery);
            res = pstt.executeQuery();
            if (res.next()) {
                if (res.getString("pwd").equals(pwd)) {
                    try {
                        Notification.sendNotification("The Green One Network", "Password Already In Use", TrayIcon.MessageType.ERROR);
                    } catch (AWTException | MalformedURLException ex) {
                        Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return false;
                } else {
                    return true;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public void ChangePWDActionPerformed(ActionEvent evt) {
        String NewPassword = JOptionPane.showInputDialog(this, "Enter New Password");
        String md5OfPwd = Encryption.doMD5(NewPassword);
        if (this.isPasswordExist(md5OfPwd)) {
            if (NewPassword.length() >= 8 && NewPassword.length() <= 32) {
                Random NK = new Random();
                int NewKey = NK.nextInt((1000 - (-1000)) + 1) + (-1000);
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
                    stt = con.createStatement();
                    ChangePWDQuery = "UPDATE account SET pwd = ?, thekey = ? WHERE email = ?";
                    PreparedStatement pstt = con.prepareStatement(ChangePWDQuery);
                    pstt.setString(1, md5OfPwd);
                    pstt.setInt(2, NewKey);
                    pstt.setString(3, this.email);
                    pstt.executeUpdate();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                } finally {
                    try {
                        stt.close();
                        res.close();
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                JSONObject j = new JSONObject();
                Encryption encr = new Encryption();
                j.put("AutoEmail", this.email);
                j.put("AutoPwd", encr.encrypt(NewPassword));
                try (PrintWriter print = new PrintWriter(new BufferedWriter(new FileWriter(this.rr1.getAbsolutePath())))) {
                    print.println(j);
                    print.close();
                } catch (IOException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
                JOptionPane.showMessageDialog(this, "Your Password Has Been Changed Successfully");
            } else {
                if (NewPassword.length() < 8) {
                    JOptionPane.showMessageDialog(this, "Your Password Must Be At Least 8 Letters", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (NewPassword.length() > 32) {
                    JOptionPane.showMessageDialog(this, "Your Password Must Be No Longer Than 32 Letters/Symbols..", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Password Already In Use. Choose Another Password Please.");
        }
    }

    public void LogoutActionPerformed(ActionEvent evt) {
        this.Notifications("You Logged Out Successfully \n Please Wait...");
        this.in = new Login();
        this.in.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }

    public void BlockedAccountsActionPerformed(ActionEvent evt) {
        BlockedAccounts blocked_accounts = new BlockedAccounts();
        blocked_accounts.setVisible(true);
        blocked_accounts.getBlockedAccounts().execute();
    }

    public void NFTActionPerformed(ActionEvent evt) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                URI uri = new URI(this.NFT_URL);
                desktop.browse(uri);
            } catch (URISyntaxException | IOException e) {
            }
        } else {
            JOptionPane.showMessageDialog(this, "This isn't available on your device yet.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Appr1ActionPerformed(ActionEvent evt) {
        File old = new File("pics/Cloud.png");
        if (old.exists()) {
            old.delete();
        }
        URLConnection connection;
        URL Appr;
        FileOutputStream fos;
        BufferedInputStream bis;
        try {
            Appr = new URL(this.Appr1URL);
            fos = new FileOutputStream(old.getAbsolutePath());
            connection = Appr.openConnection();
            bis = new BufferedInputStream(connection.getInputStream());
            int onebyte = bis.read();
            while (onebyte != -1) {
                fos.write(onebyte);
                onebyte = bis.read();
            }
            bis.close();
            fos.flush();
            fos.close();
            JOptionPane.showMessageDialog(this, "Appearance Of Enemy Character Has Been Changed Successfully" + "\n" + "You Must Restart The Game To Apply Changes");
        } catch (MalformedURLException | FileNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public void Appr2ActionPerformed(ActionEvent evt) {
        File old = new File("pics/Cloud.png");
        if (old.exists()) {
            old.delete();
        }
        URLConnection connection;
        URL Appr;
        FileOutputStream fos;
        BufferedInputStream bis;
        try {
            Appr = new URL(this.Appr2URL);
            fos = new FileOutputStream(old.getAbsolutePath());
            connection = Appr.openConnection();
            bis = new BufferedInputStream(connection.getInputStream());
            int onebyte = bis.read();
            while (onebyte != -1) {
                fos.write(onebyte);
                onebyte = bis.read();
            }
            bis.close();
            fos.flush();
            fos.close();
            JOptionPane.showMessageDialog(this, "Character Has Been Changed Successfully" + "\n" + "You Must Restart The Game To Apply Changes");
        } catch (MalformedURLException | FileNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public void Appr3ActionPerformed(ActionEvent evt) {
        File old = new File("pics/Cloud.png");
        if (old.exists()) {
            old.delete();
        }
        URLConnection connection;
        URL Appr;
        FileOutputStream fos;
        BufferedInputStream bis;
        try {
            Appr = new URL(this.Appr3URL);
            fos = new FileOutputStream(old.getAbsolutePath());
            connection = Appr.openConnection();
            bis = new BufferedInputStream(connection.getInputStream());
            int onebyte = bis.read();
            while (onebyte != -1) {
                fos.write(onebyte);
                onebyte = bis.read();
            }
            bis.close();
            fos.flush();
            fos.close();
            JOptionPane.showMessageDialog(this, "Character Has Been Changed Successfully" + "\n" + "You Must Restart The Game To Apply Changes");
        } catch (MalformedURLException | FileNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public void TurnOnActionPerformed(ActionEvent evt) {
        this.TurningComputerSpeech = true;
        this.forSer[2] = this.TurningComputerSpeech;
        IDSerialzation.doSer(this.forSer);
        repaint();
    }

    public void TurnOffActionPerformed(ActionEvent evt) {
        this.TurningComputerSpeech = false;
        this.forSer[2] = this.TurningComputerSpeech;
        IDSerialzation.doSer(this.forSer);
        repaint();
    }

    public void displaySettingsActionPerformed(ActionEvent evt) {
        DisplaySettings displaySettings = new DisplaySettings();
        displaySettings.setVisible(true);
    }

    public void audioSettingsActionPerformed(ActionEvent evt) {
        this.audioSettingsFrame = new AudioSettings(this.floatControl);
        audioSettingsFrame.setVisible(true);
    }

    public void FindPlayersActionPerformed(ActionEvent evt) {
        UsersSearch search = new UsersSearch();
        search.setVisible(true);
        try {
            Notification.sendNotification("The Green One Network", "Now You Can Find Another Players And Connect With Them" + "\n", TrayIcon.MessageType.INFO);
        } catch (AWTException | MalformedURLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Notification.sendNotification("The Green One Network", "Please Colse This Window Before Starting The Game" + "\n", TrayIcon.MessageType.WARNING);
        } catch (AWTException | MalformedURLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LatestScoresActionPerformed(ActionEvent evt) {
        ViewPlayersScores viewPlayersScores = new ViewPlayersScores();
        viewPlayersScores.setVisible(true);
        viewPlayersScores.getLatestPlayersScores().execute();
    }

    public void ViewReceivedMessagesActionPerformed(ActionEvent evt) {
        IncomingMessages incomingMessages = new IncomingMessages();
        incomingMessages.setVisible(true);
        incomingMessages.getReceivedMessages().execute();
    }

    public void MessagesActionPerformed(ActionEvent evt) {
        Messages msgs = new Messages();
        invokeLater(() -> {
            msgs.setVisible(true);
        });
        try {
            Notification.sendNotification("The Green One Network", "Now You Can Send Messages To The Players" + "\n", TrayIcon.MessageType.INFO);
        } catch (AWTException | MalformedURLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Notification.sendNotification("The Green One Network", "Please Colse This Window Before Starting The Game" + "\n", TrayIcon.MessageType.WARNING);
        } catch (AWTException | MalformedURLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Point MousePoint = new Point(mouseX, mouseY);
        g.drawRect(150, 55, 160, 50);
        g.drawString(OSname, 155, 75);
        g.drawString(OSarch, 155, 95);
        g.drawImage(surface, 0, 50, this);
        aboutRect = new Rectangle(117, 300, 152, 42);
        startRect = new Rectangle(117, 160, 152, 42);
        exitRect = new Rectangle(117, 230, 152, 42);
        mouse = new Rectangle(mouseX, mouseY, 10, 10);
        if (aboutRect.intersects(mouse) || aboutRect.contains(MousePoint)) {
            about.setCursor(HandCursor);
            about.setFont(this.indieFlower(15f));
            about.setForeground(green);
            if (this.TurningComputerSpeech) {
                SpeechToText(about.getText());
            }
        }
        if (startRect.intersects(mouse) || startRect.contains(MousePoint)) {
            start.setCursor(HandCursor);
            start.setFont(this.indieFlower(15f));
            start.setForeground(green);
            if (this.TurningComputerSpeech || !this.TurningComputerSpeech) {
                soeffects(this.ForStart);
            }
        }
        if (exitRect.intersects(mouse) || exitRect.contains(MousePoint)) {
            exit.setCursor(HandCursor);
            exit.setFont(this.indieFlower(15f));
            exit.setForeground(green);
            if (this.TurningComputerSpeech) {
                SpeechToText(exit.getText());
            }
        }
        if (!aboutRect.intersects(mouse) || !aboutRect.contains(MousePoint)) {
            about.setCursor(DefaultCursor);
            about.setFont(this.indieFlower(14f));
            about.setForeground(white);
        }
        if (!startRect.intersects(mouse) || !startRect.contains(MousePoint)) {
            start.setCursor(DefaultCursor);
            start.setFont(this.indieFlower(14f));
            start.setForeground(white);
        }
        if (!exitRect.intersects(mouse) || !exitRect.contains(MousePoint)) {
            exit.setCursor(DefaultCursor);
            exit.setFont(this.indieFlower(14f));
            exit.setForeground(white);
        }
    }

    public void SendHoverEffect(MouseEvent evt) {
        send.setCursor(HandCursor);
        send.setPreferredSize(BiggerButtonSize);
        send.setSize(BiggerButtonSize);
        send.setFont(this.indieFlower(14f));
        send.setForeground(green);
        if (this.TurningComputerSpeech) {
            SpeechToText(send.getText());
        }
    }

    public void SendNoHoverEffect(MouseEvent evt) {
        send.setCursor(DefaultCursor);
        send.setPreferredSize(OriginalButtonSize);
        send.setSize(OriginalButtonSize);
        send.setFont(this.indieFlower(12f));
        send.setForeground(white);
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

    public void CheckProfilePhoto() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            PhotoQuery = "SELECT * FROM account WHERE email = ?";
            PreparedStatement pstt = con.prepareStatement(PhotoQuery);
            pstt.setString(1, this.email);
            res = pstt.executeQuery();
            if (res.next()) {
                byte[] bin2 = res.getBytes("photo");
                this.bin = String.valueOf(bin2.length);
                this.ProfilePhoto = new ImageIcon(bin2);
                Image img = this.ProfilePhoto.getImage();
                Image Convert = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                this.AfterBin = new ImageIcon(Convert);
                ChangePhoto.setIcon(this.AfterBin);
            } else {
                ChangePhoto.setIcon(this.ImageIcon);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void viewMyProfilePhoto(ActionEvent evt) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            PhotoQuery = "SELECT * FROM account WHERE email = ?";
            PreparedStatement pstt = con.prepareStatement(PhotoQuery);
            pstt.setString(1, this.email);
            res = pstt.executeQuery();
            if (res.next()) {
                byte[] bin = res.getBytes("photo");
                ImageIcon imageicon = new ImageIcon(bin);
                Image img = imageicon.getImage();
                JOptionPane.showMessageDialog(this, "", "Player's Profile Photo", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(img.getScaledInstance(250, 250, Image.SCALE_SMOOTH)));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setIpAddress() {
        try {
            this.KnowIP = new URL("http://ip-api.com/line/?fields=query");
            this.ReadIp = new BufferedReader(new InputStreamReader(this.KnowIP.openStream()));
            this.ipAddress = this.ReadIp.readLine().trim();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            getIPQuery = "UPDATE account SET ip = ? WHERE email = ?";
            PreparedStatement pstt = con.prepareStatement(getIPQuery);
            pstt.setString(1, ipAddress);
            pstt.setString(2, this.email);
            pstt.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Font indieFlower(float size) {
        File font = new File("sources/IndieFlower-Regular.ttf");
        File FontFile = new File(font.getAbsolutePath());
        try {
            this.IndieFlower = Font.createFont(Font.TRUETYPE_FONT, FontFile).deriveFont(size);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            env.registerFont(Font.createFont(Font.TRUETYPE_FONT, FontFile));
            return this.IndieFlower;
        } catch (FontFormatException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.IndieFlower;
    }

    public String getDateTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String getDT = sdf.format(d);
        return getDT;
    }

    public void Visibility(int OnOrOff, String lastseen) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            this.CheckStatusQuery = "UPDATE account SET con = ?, lastSeen = ? WHERE email = ?";
            PreparedStatement pstt = con.prepareStatement(CheckStatusQuery);
            pstt.setInt(1, OnOrOff);
            pstt.setString(2, lastseen);
            pstt.setString(3, this.email);
            pstt.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void Visibility(int OnOrOff) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            this.CheckStatusQuery = "UPDATE account SET con = ? WHERE email = ?";
            PreparedStatement pstt = con.prepareStatement(CheckStatusQuery);
            pstt.setInt(1, OnOrOff);
            pstt.setString(2, this.email);
            pstt.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getVersion() {
        return this.isNewVersion();
    }

    public void isUpdate(int which) {
        try {
            this.getVersion = new URL(this.VersionURL);
            this.getVersionNumber = new InputStreamReader(getVersion.openStream());
            this.readVersionNumber = new BufferedReader(getVersionNumber);
            this.isNewVersion = readVersionNumber.readLine().trim();
            getVersionNumber.close();
            readVersionNumber.close();
            this.link = new URL(this.TheURl);
            this.download = new InputStreamReader(link.openStream());
            this.read = new BufferedReader(download);
            this.isNew = read.readLine().trim();
            String Version_NO = this.isNewVersion();
            if (isNew.equals("new") && !Version_NO.equals(isNewVersion)) {
                this.downloadNew(isNewVersion);
            } else {
                if (which == 1) {
                    JOptionPane.showMessageDialog(this, "There Are Not New Updates");
                }
            }
            download.close();
            read.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public String isNewVersion() {
        String versionNumber = "";
        try {
            this.readFileOfVersion = new BufferedReader(new FileReader(this.VersionFilePath));
            versionNumber += readFileOfVersion.readLine().trim();
            readFileOfVersion.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        return versionNumber;
    }

    public void downloadNew(String Version_NO) {
        int know = JOptionPane.showConfirmDialog(this, "There Is A New Update For The Game: Version " + Version_NO + "\n" + "Do You Want To Download It", "New Version", this.y_n);
        if (know == 1) {
            this.Notifications("Updates Improve The Game And Security");
        }
        if (know == 0) {
            try {
                Runtime.getRuntime().exec("bin.exe");
            } catch (IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
        }
    }

    public void Notifications(String message) {
        try {
            Notification.sendNotification("the green one network", message, TrayIcon.MessageType.WARNING);
        } catch (AWTException | MalformedURLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
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

    public final String get_db_url() {
        String url = "";
        try {
            URL get_db_url = new URL("");
            BufferedReader br;
            try (InputStreamReader isr = new InputStreamReader(get_db_url.openStream())) {
                br = new BufferedReader(isr);
                url += br.readLine().trim();
            }
            br.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error With DataBase Connection");
        } catch (IOException ex) {
            Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error With DataBase Connection");
        }
        return url;
    }

    public final String get_db_usn() {
        String usn = "";
        try {
            URL get_db_usn = new URL("");
            BufferedReader br;
            try (InputStreamReader isr = new InputStreamReader(get_db_usn.openStream())) {
                br = new BufferedReader(isr);
                usn += br.readLine().trim();
            }
            br.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error With DataBase Connection");
        } catch (IOException ex) {
            Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error With DataBase Connection");
        }
        return usn;
    }

    public final String get_db_pwd() {
        String pwd = "";
        try {
            URL get_db_pwd = new URL("");
            BufferedReader br;
            try (InputStreamReader isr = new InputStreamReader(get_db_pwd.openStream())) {
                br = new BufferedReader(isr);
                pwd += br.readLine().trim();
            }
            br.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error With DataBase Connection");
        } catch (IOException ex) {
            Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error With DataBase Connection");
        }
        return pwd;
    }

    public void changeBackgroundColor(final JPanel jPanel) {
        Random rgb = new Random();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int r = rgb.nextInt(255);
                int g = rgb.nextInt(255);
                int b = rgb.nextInt(255);
                Color randomColor = new Color(r, g, b);
                jPanel.setBackground(randomColor);
            }
        }, 1000, 1000);
    }

    public void myStatusActionPerformed(ActionEvent evt) {
        int know = JOptionPane.showOptionDialog(this, "Open Camera Or Choose A Photo, So Players Can See Your Status", "Add A Status To Your Profile For 24 Hours", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, this.viewStatusIcon, this.Option1, this.Option1[0]);
        switch (know) {
            case 0:
                TakeButton takebutton = new TakeButton(2, true, this.email);
                this.TakePhoto2(takebutton);
                takebutton.setVisible(true);
                break;
            case 1:
                this.ChoosePhoto(true);
                break;
            case 2:
                if (this.neo4jdatabase.getPlayerStatus(this.email.split("@")[0]).equals("0")) {
                    JOptionPane.showMessageDialog(this, "You Did Not Upload Any Status To Remove.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    this.neo4jdatabase.removePlayerStatus(this.email);
                    JOptionPane.showMessageDialog(this, "Your Status Has Been Removed Successfully");
                }
                break;
            case 3:
                this.viewMyStatus(this.email.split("@")[0]);
                break;
            default:
                break;
        }
    }

    private void viewMyStatus(String name) {
        String statusBase64 = this.neo4jdatabase.getPlayerStatus(name);
        if (!statusBase64.equals("0")) {
            Image status;
            byte[] statusBytes = Base64.getDecoder().decode(statusBase64);
            status = new ImageIcon(statusBytes).getImage();
            JOptionPane.showMessageDialog(this, "", "Player's Status", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(status.getScaledInstance(250, 250, Image.SCALE_DEFAULT)));
        } else {
            JOptionPane.showMessageDialog(this, "You Did Not Upload Any Status To View.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void initVolumeFloatControl() {
        this.floatControl = (FloatControl) af.clip.getControl(FloatControl.Type.MASTER_GAIN);
    }

    public JButton exit;
    public JButton start;
    public JButton News;
    public JButton showControllers;
    public JLabel text1;
    public JMenuBar Bar;
    public JMenu Network;
    public JMenu Settings;
    public JMenu TurningSpeech;
    public JMenuItem displaySettings;
    public JMenuItem audioSettings;
    public JMenuItem latestScores;
    public JMenu privacy;
    public JMenuItem lastSeen;
    public JMenuItem DeleteAccount;
    public JMenuItem ChangePhoto;
    public JMenuItem ViewMyPhoto;
    public JMenuItem ChangePWD;
    public JMenuItem TurnOn;
    public JMenuItem TurnOff;
    public JMenuItem FindPlayers;
    public JMenuItem Messages;
    public JMenuItem update;
    public JMenuItem version;
    public JMenuItem Logout;
    public JMenuItem Blocked_Accounts;
    public JMenuItem Received_Messages;
    public JMenuItem myStatus;
    public JMenu EnemyAppr;
    public JMenuItem Appr1;
    public JMenuItem Appr2;
    public JMenuItem Appr3;
    public JMenuItem NFT;
    public JMenu Account;
    public JMenu Store;
    public JFileChooser ImageChoose;
}

