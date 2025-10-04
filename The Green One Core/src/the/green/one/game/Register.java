package the.green.one.game;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.validator.routines.EmailValidator;
import static org.neo4j.driver.Config.TrustStrategy.trustAllCertificates;

public class Register extends JFrame {

    public Login login = new Login();
    public Connection con;
    public Statement stt;
    public ResultSet res;
    String Email;
    String pss;
    String InsertQuery;
    String CheckQuery;
    File ntw = new File("pics/network.jpg");
    File net = new File(ntw.getAbsolutePath());
    BufferedImage network;
    Color green = new Color(0, 179, 0);
    Random thekey = new Random();
    int key;
    Border EmailBorder;
    Border PwdBorder;
    public static File ProfilePhotoPath;
    public static String PhotoPath = "";
    public static ImageIcon ProfilePhoto;
    public static FileInputStream PhotoInsert;
    public static Border PhotoBorder;
    String[] Option = {"Take Photo (Basic)", "Take Photo (Advanced 'OpenCV Required')", "Choose Photo"};
    File Image = new File("pics/Image.png");
    public ImageIcon ImageSet;
    public static CanvasFrame frame;
    public static CvCapture Capture;
    public static File cam = new File("pics/camera.png"), waitingstateicon = new File("pics/waitingStateIcon.gif");
    public static ImageIcon camer = new ImageIcon(cam.getAbsolutePath()), WaitingStateIcon = new ImageIcon(waitingstateicon.getAbsolutePath());
    public Thread CameraRunning;
    String[] Ok = {"Take Photo"};
    public static Webcam webcam;
    public static WebcamPanel panel;
    public Font IndieFlower;
    private final String DB_URL = this.get_db_url(), DB_USN = this.get_db_usn(), DB_PWD = this.get_db_pwd();
    private final Neo4jDatabaseManager neo4jdatabase = Neo4jDatabaseManager.getInstance();
    private final FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg");

    public Register() {
        this.initComponents();
        this.setAlwaysOnTop(true);
        this.setTitle("The Green One Network");
        this.setVisible(false);
        this.setBackground(green);
        this.setResizable(false);
        this.setLocation(400, 100);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setIconImage(new ImageIcon(new File("pics/icon.png").getAbsolutePath()).getImage());
        try {
            this.network = ImageIO.read(this.net);
        } catch (IOException e) {
            System.out.print("Error try agian");
            try {
                UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "error with your interface");
            }
        }
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev) {
                ClosingOperation.closeBackendProgram();
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new JLabel();
        E = new JLabel();
        email = new JTextField();
        ps = new JLabel();
        pwd = new JPasswordField();
        ga = new JButton();
        ImageChoose = new JFileChooser();
        AddImage = new JButton("Add Photo");
        photo = new JLabel();
        already = new JButton("Already Have An Account!");
        game = new JLabel();
        jLabel1.setFont(this.indieFlower()); // NOI18N
        jLabel1.setForeground(new Color(0, 128, 0));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Create Your Account");
        jLabel1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        E.setFont(this.indieFlower()); // NOI18N
        E.setText("Email :");
        EmailBorder = BorderFactory.createLineBorder(Color.BLACK);
        PwdBorder = BorderFactory.createLineBorder(Color.BLACK);
        email.setBackground(Color.DARK_GRAY);
        pwd.setBackground(Color.LIGHT_GRAY);
        email.setForeground(green);
        pwd.setForeground(green);
        email.setBorder(EmailBorder);
        pwd.setBorder(PwdBorder);
        email.setFont(this.indieFlower());
        pwd.setFont(this.indieFlower());
        ga.addActionListener(this::CreatAccountActionPerformed);
        ga.setFont(this.indieFlower());
        already.addActionListener(this::alreadyActionPerformed);
        already.setFont(this.indieFlower());
        AddImage.addActionListener(this::AddImageActionPerformed);
        AddImage.setFont(this.indieFlower());
        ImageChoose.setFont(this.indieFlower());
        email.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent evt) {
                EmailFocus(evt);
            }

            @Override
            public void focusLost(FocusEvent evt) {
                EmailUnFocus(evt);
            }
        });
        pwd.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent evt) {
                PwdFocus(evt);
            }

            @Override
            public void focusLost(FocusEvent evt) {
                PwdUnFocus(evt);
            }
        });
        ps.setFont(this.indieFlower());
        ps.setText("Password :");
        ga.setText("Create Account");
        game.setFont(this.indieFlower());
        game.setForeground(new Color(255, 51, 51));
        game.setText("Welcome To The Game's Network");
        game.setIcon(null);
        game.setVisible(true);
        already.setBounds(260, 380 + 10, 170, 20);
        AddImage.setBounds(3, 300, 90, 15);
        AddImage.setBackground(Color.BLACK);
        AddImage.setForeground(Color.GREEN);
        photo.setBounds(3, 346, 155, 155);
        this.getContentPane().add(already);
        this.getContentPane().add(this.AddImage);
        this.getContentPane().add(photo);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(187, 187, 187)
                                                        .addComponent(E, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(109, 109, 109)
                                                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                                                .addComponent(email)
                                                                .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                                        .addComponent(game, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                                                                .addComponent(pwd, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
                                                                        .addGap(0, 0, Short.MAX_VALUE)))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(174, 174, 174)
                                                .addComponent(ps, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(161, 161, 161)
                                                .addComponent(ga)))
                                .addContainerGap(134, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(game)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(E)
                                .addGap(18, 18, 18)
                                .addComponent(email, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(ps)
                                .addGap(29, 29, 29)
                                .addComponent(pwd, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(ga)
                                .addContainerGap(73, Short.MAX_VALUE))
        );
        game.setBounds(game.getBounds().x + 80, game.getBounds().y, game.getBounds().width, game.getBounds().height);
        pwd.setEchoChar('*');
        ga.setBackground(Color.BLACK);
        ga.setForeground(Color.WHITE);
        already.setBackground(Color.BLUE);
        already.setForeground(Color.BLACK);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public FileInputStream createImage(final String text, final int width,
            final int height, final Font font, final Color bgColor, final Color textColor) {
        if (text == null || text.isEmpty()) {
            return null;
        }
        String firstLetter = text.substring(0, 1).toUpperCase();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, width, height);
        g2d.setFont(font);
        g2d.setColor(textColor);
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(firstLetter), textHeight = fm.getHeight();
        int x = (width - textWidth) / 2, y = (height - textHeight) / 2 + fm.getAscent();
        g2d.drawString(firstLetter, x, y);
        g2d.dispose();
        String format = "jpg";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis = null;
        try {
            ImageIO.write(image, format, baos);
            File tempFile = File.createTempFile("temp_image", "." + format);
            tempFile.deleteOnExit();
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(baos.toByteArray());
            }
            fis = new FileInputStream(tempFile);
        } catch (IOException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fis;
    }

    private SwingWorker runCreateAccountBackgroundThread() {
        return new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                new Thread(() -> {
                    try {
                        SwingUtilities.invokeAndWait(() -> {
                            Register.this.game.setText("Please, Wait A Moment........");
                            Register.this.game.setIcon(Register.WaitingStateIcon);
                        });
                    } catch (InterruptedException | InvocationTargetException ex) {
                        Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getMessage());
                    }
                }).start();
                String theemail = email.getText().trim();
                EmailValidator emailValidator = EmailValidator.getInstance();
                if (email.getText().contains("@") && email.getText().length() >= 13 && emailValidator.isValid(theemail) && pwd.getPassword().length >= 8 && pwd.getPassword().length <= 32 && !email.getText().contains(" ") && !email.getText().equals("example@domain.com")) {
                    Email = email.getText();
                    pss = Encryption.doMD5(String.valueOf(pwd.getPassword()));
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
                        stt = con.createStatement();
                        CheckQuery = "SELECT * FROM account WHERE email = ?";
                        PreparedStatement pstt = con.prepareStatement(CheckQuery);
                        pstt.setString(1, Email);
                        res = pstt.executeQuery();
                        if (!res.next()) {
                            key = thekey.nextInt((1000 - (-1000)) + 1) + (-1000);
                            InsertQuery = "INSERT INTO account (email, pwd, thekey, photo, ip, msg, voice, lastSeen, con) VALUES (?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL)";
                            PreparedStatement pstt2 = con.prepareStatement(InsertQuery);
                            pstt2.setString(1, Email);
                            pstt2.setString(2, pss);
                            pstt2.setInt(3, key);
                            if (Register.PhotoPath.length() == 0) {
                                PhotoInsert = createImage(Email, 800, 600, indieFlower(512f), Color.GREEN, Color.WHITE);
                            }
                            PhotoInsert = ImageCompressor.compressImage(PhotoInsert);
                            pstt2.setBlob(4, PhotoInsert);
                            pstt2.executeUpdate();
                            try {
                                Notification.sendNotification("The Green One Network", "Welcome To The Green One Network", MessageType.INFO);
                            } catch (AWTException | MalformedURLException ex) {
                                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Register.this.neo4jdatabase.addPlayer(Email.split("@")[0]);
                            JOptionPane.showMessageDialog(Register.this, "Your Account Has Been Created Successfully" + "\n" + "Thank You!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            Register.this.login.setVisible(true);
                            Register.this.setVisible(false);
                            Register.this.dispose();
                        } else {
                            try {
                                Notification.sendNotification("The Green One Network", "You Could Not Create An Account", MessageType.ERROR);
                            } catch (AWTException | MalformedURLException ex) {
                                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            JOptionPane.showMessageDialog(Register.this, "Email Address Is Already Exist" + "\n" + "Try Again!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(Register.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        try {
                            stt.close();
                            res.close();
                            con.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    if (pwd.getPassword().length < 8) {
                        PwdBorder = BorderFactory.createLineBorder(Color.RED);
                        pwd.setBorder(PwdBorder);
                        JOptionPane.showMessageDialog(Register.this, "Your Password Must Be At Least 8 Letters/Symbols..", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (pwd.getPassword().length > 32) {
                        PwdBorder = BorderFactory.createLineBorder(Color.RED);
                        pwd.setBorder(PwdBorder);
                        JOptionPane.showMessageDialog(Register.this, "Your Password Must Be No Longer Than 32 Letters/Symbols..", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (pwd.getPassword().length >= 8 && pwd.getPassword().length <= 32) {
                        PwdBorder = BorderFactory.createLineBorder(Color.BLACK);
                        pwd.setBorder(PwdBorder);
                    }
                    if (!email.getText().contains("@") || email.getText().length() < 13 || !emailValidator.isValid(theemail) || email.getText().contains(" ") || email.getText().equals("example@domain.com")) {
                        EmailBorder = BorderFactory.createLineBorder(Color.RED);
                        email.setBorder(EmailBorder);
                        JOptionPane.showMessageDialog(Register.this, "Incorrect Email" + "\n" + "Check Your Email Please", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (email.getText().contains("@") && email.getText().length() >= 13 && !email.getText().contains(" ") && emailValidator.isValid(theemail) && !email.getText().equals("example@domain.com")) {
                        EmailBorder = BorderFactory.createLineBorder(Color.BLACK);
                        email.setBorder(EmailBorder);
                    }
                }
                new Thread(() -> {
                    try {
                        SwingUtilities.invokeAndWait(() -> {
                            Register.this.game.setText("Welcome To The Game's Network");
                            Register.this.game.setIcon(null);
                        });
                    } catch (InterruptedException | InvocationTargetException ex) {
                        Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getMessage());
                    }
                }).start();
                return null;
            }
        };
    }

    public void CreatAccountActionPerformed(ActionEvent evt) {
        this.runCreateAccountBackgroundThread().execute();
    }

    public int TakeDot(String email) {
        int IndexOfDot = email.indexOf('.');
        return IndexOfDot;
    }

    private SwingWorker runAlreadyHaveAccountBackgroundThread() {
        return new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                new Thread(() -> {
                    try {
                        SwingUtilities.invokeAndWait(() -> {
                            Register.this.game.setText("Please, Wait A Moment........");
                            Register.this.game.setIcon(Register.WaitingStateIcon);
                        });
                    } catch (InterruptedException | InvocationTargetException ex) {
                        Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getMessage());
                    }
                }).start();
                login.setVisible(true);
                Register.this.setVisible(false);
                Register.this.dispose();
                new Thread(() -> {
                    try {
                        SwingUtilities.invokeAndWait(() -> {
                            Register.this.game.setText("Welcome To The Game's Network");
                            Register.this.game.setIcon(null);
                        });
                    } catch (InterruptedException | InvocationTargetException ex) {
                        Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getMessage());
                    }
                }).start();
                return null;
            }
        };
    }

    public void alreadyActionPerformed(ActionEvent evt) {
        this.runAlreadyHaveAccountBackgroundThread().execute();
    }

    public void AddImageActionPerformed(ActionEvent evt) {
        this.ImageSet = new ImageIcon(this.Image.getAbsolutePath());
        int know = JOptionPane.showOptionDialog(this, "Set Profile Photo", "Open Camera Or Choose A Photo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, this.ImageSet, this.Option, this.Option[0]);
        switch (know) {
            case 0:
                TakeButton takebutton = new TakeButton(1, false);
                this.TakePhoto2(takebutton);
                takebutton.setVisible(true);
                break;
            case 1:
                this.TakePhoto();
                TakeButton open1 = new TakeButton(1, false);
                open1.setVisible(true);
                break;
            case 2:
                this.ChoosePhoto();
                break;
            default:
                break;
        }
    }

    public void ChoosePhoto() {
        ImageChoose.setAcceptAllFileFilterUsed(false);
        ImageChoose.setFileFilter(this.filter);
        ImageChoose.setCurrentDirectory(new File("C:/"));
        ImageChoose.setDialogTitle("Select Your Profile Photo");
        ImageChoose.showOpenDialog(this);
        ProfilePhotoPath = ImageChoose.getSelectedFile();
        PhotoPath = Register.ProfilePhotoPath.getAbsolutePath();
        String extension = FilenameUtils.getExtension(PhotoPath);
        if (extension.equals("jpg")) {
            ProfilePhoto = new ImageIcon(Register.PhotoPath);
            if (Register.ProfilePhoto.getIconWidth() > 10 && Register.ProfilePhoto.getIconWidth() <= 160 && Register.ProfilePhoto.getIconHeight() > 10 && Register.ProfilePhoto.getIconHeight() <= 170) {
                photo.setIcon(ProfilePhoto);
                photo.setSize(Register.ProfilePhoto.getIconWidth(), Register.ProfilePhoto.getIconHeight());
                Register.PhotoBorder = BorderFactory.createLineBorder(Color.GREEN);
                photo.setBorder(PhotoBorder);
                try {
                    PhotoInsert = new FileInputStream(Register.ProfilePhotoPath);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, ex.getMessage() + "\n" + "your image is incorrect");
                }
            } else {
                Image Re = ProfilePhoto.getImage();
                Image Resize = Re.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
                ImageIcon TheNew = new ImageIcon(Resize);
                photo.setIcon(TheNew);
                photo.setSize(TheNew.getIconWidth(), TheNew.getIconHeight());
                Register.PhotoBorder = BorderFactory.createLineBorder(Color.GREEN);
                photo.setBorder(PhotoBorder);
                try {
                    PhotoInsert = new FileInputStream(Register.ProfilePhotoPath);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, ex.getMessage() + "\n" + "Your Image Is Incorrect");
                }
            }
        } else {
            Register.PhotoBorder = BorderFactory.createLineBorder(Color.RED);
            photo.setBorder(PhotoBorder);
            JOptionPane.showMessageDialog(this, "Please, Add A JPG Photo.", "Error With Photo", JOptionPane.ERROR_MESSAGE);
        }
        this.repaint();
    }

    public void TakePhoto() {
        this.ImageSet = new ImageIcon(this.Image.getAbsolutePath());
        Image Icon = Register.camer.getImage();
        CameraRunning = new Thread() {
            @Override
            public void run() {
                Capture = opencv_highgui.cvCreateCameraCapture(0);
                opencv_highgui.cvSetCaptureProperty(Capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 600);
                opencv_highgui.cvSetCaptureProperty(Capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 700);
                IplImage IpImage = opencv_highgui.cvQueryFrame(Capture);
                frame = new CanvasFrame("Camera");
                frame.setAlwaysOnTop(true);
                frame.setIconImage(Icon);
                frame.setDefaultCloseOperation(CanvasFrame.DISPOSE_ON_CLOSE);
                frame.setResizable(false);
                frame.setSize(700, 600);
                frame.setLocation(280, 10);
                while (frame.isVisible() && (IpImage = opencv_highgui.cvQueryFrame(Capture)) != null) {
                    frame.showImage(IpImage);
                }
            }
        };
        CameraRunning.start();
    }

    public void TakePhoto2(TakeButton takebutton) {
        this.ImageSet = new ImageIcon(this.Image.getAbsolutePath());
        Image Icon = Register.camer.getImage();
        webcam = Webcam.getDefault();
        if (webcam != null) {
            webcam.setViewSize(WebcamResolution.VGA.getSize());
            panel = new WebcamPanel(webcam);
            panel.setImageSizeDisplayed(true);
            frame = new CanvasFrame("Camera");
            frame.setAlwaysOnTop(true);
            frame.setIconImage(Icon);
            frame.setDefaultCloseOperation(CanvasFrame.DISPOSE_ON_CLOSE);
            frame.setResizable(false);
            frame.setSize(700, 600);
            frame.setLocation(280, 10);
            frame.setLocationRelativeTo(null);
            Point p = frame.getLocationOnScreen();
            takebutton.setCanvasFrame(frame);
            takebutton.setLocation((int) (p.x + (frame.getWidth() * 0.5d - 30)), (int) (p.y + (frame.getHeight() + 10)));
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent we) {
                    takebutton.setVisible(false);
                    takebutton.dispose();
                    frame.setVisible(false);
                    frame.dispose();
                    webcam.close();
                }
            });
            frame.add(panel);
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Could Not Detect Any WebCam" + "\n" + "Please Check Your Camera", " Error With Camera", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void EmailFocus(FocusEvent evt) {
        if (email.getText().equals("example@domain.com")) {
            email.setBackground(Color.WHITE);
            email.setText("");
        }
    }

    public void PwdFocus(FocusEvent evt) {
        if (String.valueOf(pwd.getPassword()).equals("12345678")) {
            pwd.setBackground(Color.WHITE);
            pwd.setText("");
        }
    }

    public void EmailUnFocus(FocusEvent evt) {
        if (email.getText().length() == 0) {
            email.setBackground(Color.DARK_GRAY);
            email.setText("example@domain.com");
        }
    }

    public void PwdUnFocus(FocusEvent evt) {
        if (pwd.getPassword().length == 0) {
            pwd.setBackground(Color.LIGHT_GRAY);
            pwd.setText("12345678");
        }
    }

    public Font indieFlower() {
        File font = new File("sources/IndieFlower-Regular.ttf");
        File FontFile = new File(font.getAbsolutePath());
        try {
            this.IndieFlower = Font.createFont(Font.TRUETYPE_FONT, FontFile).deriveFont(14f);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            env.registerFont(Font.createFont(Font.TRUETYPE_FONT, FontFile));
            return IndieFlower;
        } catch (FontFormatException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IndieFlower;
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

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (Register.PhotoPath.length() == 0) {
            g.drawImage(network, 3, 346, this);
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
    public JLabel E;
    public JTextField email;
    public JButton ga;
    public JButton already;
    public JLabel game;
    public JLabel jLabel1;
    public JLabel ps;
    public JPasswordField pwd;
    public JFileChooser ImageChoose;
    public JButton AddImage;
    public static JLabel photo;
}

