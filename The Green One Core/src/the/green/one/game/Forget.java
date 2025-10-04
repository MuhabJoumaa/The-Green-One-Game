package the.green.one.game;

import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import org.apache.commons.validator.routines.EmailValidator;

public class Forget extends JFrame {

    public Connection con;
    public Statement stt;
    public ResultSet res;
    String Email;
    String pss;
    String checkQuery;
    String ChangePwdQuery;
    BufferedImage network;
    File rr1 = new File("sources/info.json"), json = new File("sources/info.json"),
            sec = new File("pics/secure.png"), waitingstateicon = new File("pics/waitingStateIcon.gif");
    ImageIcon secure = new ImageIcon(sec.getAbsolutePath()), WaitingStateIcon = new ImageIcon(waitingstateicon.getAbsolutePath());
    Color green = new Color(0, 179, 0);
    Border EmailBorder;
    Border PwdBorder;
    String info;
    final String filepath = json.getAbsolutePath();
    Font IndieFlower;
    public int Success = 0;
    public Login in;
    private final String DB_URL = this.get_db_url(), DB_USN = this.get_db_usn(), DB_PWD = this.get_db_pwd();
    private final Random randomCode = new Random();

    public Forget() {
        this.initComponents();
        this.setAlwaysOnTop(true);
        this.setTitle("The Green One Network");
        this.setVisible(false);
        this.setBackground(Color.GREEN);
        this.setResizable(false);
        this.setLocation(400, 100);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setIconImage(new ImageIcon(new File("pics/icon.png").getAbsolutePath()).getImage());
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "error with your interface");
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
        note = new JLabel(secure);
        E = new JLabel();
        email = new JTextField();
        ps = new JLabel();
        pwd = new JPasswordField();
        Reset = new JButton();
        Login = new JButton("Log In");
        game = new JLabel();
        ShowPwd = new JRadioButton("Show Value");
        ShowPwd.setSelected(true);
        note.setText("We Will Send A Verfication Code To Email");
        note.setFont(this.indieFlower());
        Reset.setFont(this.indieFlower());
        ShowPwd.setFont(this.indieFlower());
        pwd.setFont(this.indieFlower());
        email.setFont(this.indieFlower());
        Login.setFont(this.indieFlower());
        getContentPane().add(ShowPwd);
        getContentPane().add(note);
        getContentPane().add(Login);
        EmailBorder = BorderFactory.createLineBorder(Color.BLACK);
        PwdBorder = BorderFactory.createLineBorder(Color.BLACK);
        email.setBackground(Color.DARK_GRAY);
        pwd.setBackground(Color.LIGHT_GRAY);
        email.setForeground(green);
        pwd.setForeground(green);
        email.setBorder(EmailBorder);
        pwd.setBorder(PwdBorder);
        pwd.setEnabled(false);
        pwd.setEchoChar((char) 0);
        Login.setBackground(Color.BLUE);
        Login.setForeground(green);
        ShowPwd.setBounds(320, 270, 170, 20);
        ShowPwd.setEnabled(true);
        Login.setBounds(270, 350, 170, 20);
        note.setBackground(Color.BLACK);
        note.setForeground(Color.BLUE);
        note.setBounds(40, 78, 300, 40);
        jLabel1.setFont(this.indieFlower());
        jLabel1.setForeground(new java.awt.Color(0, 128, 0));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Change Your Password");
        jLabel1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        E.setFont(this.indieFlower());
        E.setText("Email :");
        Reset.addActionListener(this::SendVerificationCodeActionPerformed);
        ShowPwd.addActionListener(this::ShowPwdActionPerformed);
        Login.addActionListener(this::LoginActionPerformed);
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
        ps.setText("Ver. Code");
        Reset.setText("Send-");
        game.setFont(this.indieFlower());
        game.setForeground(new Color(255, 51, 51));
        game.setText("Welcome To The Game's Network");
        game.setIcon(null);
        game.setVisible(true);
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
                                                .addComponent(Reset)))
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
                                .addComponent(Reset)
                                .addContainerGap(73, Short.MAX_VALUE))
        );
        Reset.setBackground(Color.BLACK);
        Reset.setForeground(Color.WHITE);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private int generateCode() {
        int code = this.randomCode.nextInt(999999 - 100000) + 100000;
        return code;
    }

    private SwingWorker runSendVerificationBackgroundThread() {
        return new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                new Thread(() -> {
                    try {
                        SwingUtilities.invokeAndWait(() -> {
                            Forget.this.game.setText("Please, Wait A Moment........");
                            Forget.this.game.setIcon(Forget.this.WaitingStateIcon);
                        });
                    } catch (InterruptedException | InvocationTargetException ex) {
                        Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getMessage());
                    }
                }).start();
                String theemail = email.getText();
                EmailValidator emailValidator = EmailValidator.getInstance();
                int dot = Forget.this.TakeDot(theemail);
                String StringAfterDot = Forget.this.CheckRegex(dot, theemail);
                if (email.getText().contains("@") && email.getText().length() >= 13 && StringAfterDot.matches("[A-Za-z]{2,5}") && emailValidator.isValid(theemail)) {
                    Email = email.getText();
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
                        stt = con.createStatement();
                        checkQuery = "SELECT * FROM account WHERE email = ?";
                        PreparedStatement pstt = con.prepareStatement(checkQuery);
                        pstt.setString(1, Email);
                        res = pstt.executeQuery();
                        if (!res.next()) {
                            EmailBorder = BorderFactory.createLineBorder(Color.RED);
                            email.setBorder(EmailBorder);
                            try {
                                Notification.sendNotification("The Green One Network", "Incorrect Email", TrayIcon.MessageType.ERROR);
                            } catch (AWTException | MalformedURLException ex) {
                                Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            JOptionPane.showMessageDialog(Forget.this, "Invaild Email", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            EmailBorder = BorderFactory.createLineBorder(Color.BLACK);
                            email.setBorder(EmailBorder);
                            Forget.this.sendVerificationCodeToPlayerEmail(Email);
                        }
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(Forget.this, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        try {
                            stt.close();
                            res.close();
                            con.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    if (!email.getText().contains("@") || email.getText().length() < 13 || !StringAfterDot.matches("[A-Za-z]{2,5}") || !emailValidator.isValid(theemail)) {
                        EmailBorder = BorderFactory.createLineBorder(Color.RED);
                        email.setBorder(EmailBorder);
                        JOptionPane.showMessageDialog(Forget.this, "Incorrect Email" + "\n" + "Check Your Email Please", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (email.getText().contains("@") && email.getText().length() >= 13 && emailValidator.isValid(theemail)) {
                        EmailBorder = BorderFactory.createLineBorder(Color.BLACK);
                        email.setBorder(EmailBorder);
                    }
                }
                new Thread(() -> {
                    try {
                        SwingUtilities.invokeAndWait(() -> {
                            Forget.this.game.setText("Welcome To The Game's Network");
                            Forget.this.game.setIcon(null);
                        });
                    } catch (InterruptedException | InvocationTargetException ex) {
                        Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getMessage());
                    }
                }).start();
                return null;
            }
        };
    }

    public void SendVerificationCodeActionPerformed(ActionEvent evt) {
        this.runSendVerificationBackgroundThread().execute();
    }

    private SwingWorker runResetBackgroundThread() {
        return new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                new Thread(() -> {
                    try {
                        SwingUtilities.invokeAndWait(() -> {
                            Forget.this.game.setText("Please, Wait A Moment........");
                            Forget.this.game.setIcon(Forget.this.WaitingStateIcon);
                        });
                    } catch (InterruptedException | InvocationTargetException ex) {
                        Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getMessage());
                    }
                }).start();
                String theemail = email.getText();
                EmailValidator emailValidator = EmailValidator.getInstance();
                int dot = Forget.this.TakeDot(theemail);
                String StringAfterDot = Forget.this.CheckRegex(dot, theemail);
                if (email.getText().contains("@") && email.getText().length() >= 13 && StringAfterDot.matches("[A-Za-z]{2,5}") && pwd.getPassword().length >= 8 && emailValidator.isValid(theemail)) {
                    Email = email.getText();
                    pss = Encryption.doMD5(String.valueOf(pwd.getPassword()));
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
                        stt = con.createStatement();
                        checkQuery = "SELECT * FROM account WHERE email = ?";
                        PreparedStatement pstt = con.prepareStatement(checkQuery);
                        pstt.setString(1, Email);
                        res = pstt.executeQuery();
                        if (!res.next()) {
                            EmailBorder = BorderFactory.createLineBorder(Color.RED);
                            email.setBorder(EmailBorder);
                            try {
                                Notification.sendNotification("The Green One Network", "Incorrect Email", TrayIcon.MessageType.ERROR);
                            } catch (AWTException | MalformedURLException ex) {
                                Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            JOptionPane.showMessageDialog(Forget.this, "Invaild Email", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            EmailBorder = BorderFactory.createLineBorder(Color.BLACK);
                            email.setBorder(EmailBorder);
                            Forget.this.CheckPWD(pss, Email);
                        }
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(Forget.this, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        try {
                            stt.close();
                            res.close();
                            con.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    if (pwd.getPassword().length < 8) {
                        PwdBorder = BorderFactory.createLineBorder(Color.RED);
                        pwd.setBorder(PwdBorder);
                        JOptionPane.showMessageDialog(Forget.this, "Your Password Must Be At Least 8 Letters", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (pwd.getPassword().length >= 8) {
                        PwdBorder = BorderFactory.createLineBorder(Color.BLACK);
                        pwd.setBorder(PwdBorder);
                    }
                    if (!email.getText().contains("@") || email.getText().length() < 13 || !StringAfterDot.matches("[A-Za-z]{2,5}") || !emailValidator.isValid(theemail)) {
                        EmailBorder = BorderFactory.createLineBorder(Color.RED);
                        email.setBorder(EmailBorder);
                        JOptionPane.showMessageDialog(Forget.this, "Incorrect Email" + "\n" + "Check Your Email Please", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (email.getText().contains("@") && email.getText().length() >= 13 && emailValidator.isValid(theemail)) {
                        EmailBorder = BorderFactory.createLineBorder(Color.BLACK);
                        email.setBorder(EmailBorder);
                    }
                }
                new Thread(() -> {
                    try {
                        SwingUtilities.invokeAndWait(() -> {
                            Forget.this.game.setText("Welcome To The Game's Network");
                            Forget.this.game.setIcon(null);
                        });
                    } catch (InterruptedException | InvocationTargetException ex) {
                        Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getMessage());
                    }
                }).start();
                return null;
            }
        };
    }

    private void ResetActionPerformed(ActionEvent evt) {
        this.runResetBackgroundThread().execute();
    }

    private void sendVerificationCodeToPlayerEmail(String email) {
        int code = this.generateCode();
        String message = "The Green One Network\n" + "Your Verification Code Is: " + String.valueOf(code);
        Sending.sending2(message, email);
        this.pwd.setEnabled(true);
        this.Reset.setText("Check");
        this.Reset.removeActionListener(this.Reset.getActionListeners()[0]);
        this.Reset.addActionListener((ActionEvent evt) -> {
            checkVerificationCode(evt, code);
        });
        JOptionPane.showMessageDialog(this, "Verification Code Has Been Sent To Your Email.\nCheck The Messages Received Of Your Email Inbox And Then Enter The Verification Code Sent In The Code Field.", "Verification Code", JOptionPane.INFORMATION_MESSAGE);
    }

    private void checkVerificationCode(ActionEvent evt, int code) {
        try {
            int val = Integer.parseInt(String.valueOf(pwd.getPassword()));
            if (val == code) {
                this.ps.setText("New Pass");
                this.ShowPwd.setSelected(false);
                this.ShowPwd.setEnabled(true);
                this.Reset.setText("Reset");
                this.Reset.removeActionListener(this.Reset.getActionListeners()[0]);
                this.Reset.addActionListener((ActionEvent evt1) -> {
                    ResetActionPerformed(evt);
                });
            } else {
                PwdBorder = BorderFactory.createLineBorder(Color.RED);
                pwd.setBorder(PwdBorder);
                JOptionPane.showMessageDialog(this, "Invalid Code, Try Again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            PwdBorder = BorderFactory.createLineBorder(Color.RED);
            pwd.setBorder(PwdBorder);
            JOptionPane.showMessageDialog(this, "Invalid Code, Try Again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int TakeDot(String email) {
        int IndexOfDot = email.indexOf('.');
        return IndexOfDot;
    }

    public String CheckRegex(int IndexOfDot, String theemail) {
        String AfterDot = theemail.substring(IndexOfDot + 1, theemail.length());
        return AfterDot;
    }

    public void CheckPWD(String pwd, String email) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            ChangePwdQuery = "SELECT pwd FROM account WHERE email = ?";
            PreparedStatement pstt = con.prepareStatement(ChangePwdQuery);
            pstt.setString(1, email);
            res = pstt.executeQuery();
            if (res.next()) {
                if (res.getString("pwd").equals(pwd)) {
                    this.PwdBorder = BorderFactory.createLineBorder(Color.RED);
                    this.pwd.setBorder(this.PwdBorder);
                    try {
                        Notification.sendNotification("The Green One Network", "Password Already In Use", TrayIcon.MessageType.ERROR);
                    } catch (AWTException | MalformedURLException ex) {
                        Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(this, "Password Already In Use. Choose Another Password Please.");
                } else {
                    this.PwdBorder = BorderFactory.createLineBorder(Color.BLACK);
                    this.pwd.setBorder(this.PwdBorder);
                    this.ChangePwd(pwd, email);
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
    }

    public void ChangePwd(String pss, String user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            ChangePwdQuery = "UPDATE account SET pwd = ? WHERE email = ?";
            PreparedStatement pstt = con.prepareStatement(ChangePwdQuery);
            pstt.setString(1, pss);
            pstt.setString(2, user);
            int know = pstt.executeUpdate();
            if (know == 1) {
                JOptionPane.showMessageDialog(this, "Password Has Been Changed Successfully");
                try {
                    Notification.sendNotification("The Green One Network", "Password Has Been Changed Successfully", TrayIcon.MessageType.INFO);
                } catch (AWTException | MalformedURLException ex) {
                    Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.in = new Login();
                this.in.setVisible(true);
                this.setVisible(false);
                this.dispose();
            }
            if (know == 0) {
                JOptionPane.showMessageDialog(this, "Error, Try again");
                try {
                    Notification.sendNotification("The Green One Network", "Error, Try again", TrayIcon.MessageType.INFO);
                } catch (AWTException | MalformedURLException ex) {
                    Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
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
    }

    public void ShowPwdActionPerformed(ActionEvent evt) {
        if (!ShowPwd.isSelected()) {
            pwd.setEchoChar('*');
        } else {
            pwd.setEchoChar((char) 0);
        }
    }

    private SwingWorker runLoginBackgroundThread() {
        return new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                new Thread(() -> {
                    try {
                        SwingUtilities.invokeAndWait(() -> {
                            Forget.this.game.setText("Please, Wait A Moment........");
                            Forget.this.game.setIcon(Forget.this.WaitingStateIcon);
                        });
                    } catch (InterruptedException | InvocationTargetException ex) {
                        Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getMessage());
                    }
                }).start();
                Forget.this.in = new Login();
                Forget.this.in.setVisible(true);
                Forget.this.setVisible(false);
                Forget.this.dispose();
                new Thread(() -> {
                    try {
                        SwingUtilities.invokeAndWait(() -> {
                            Forget.this.game.setText("Welcome To The Game's Network");
                            Forget.this.game.setIcon(null);
                        });
                    } catch (InterruptedException | InvocationTargetException ex) {
                        Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getMessage());
                    }
                }).start();
                return null;
            }
        };
    }

    public void LoginActionPerformed(ActionEvent evt) {
        this.runLoginBackgroundThread().execute();
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
        if (String.valueOf(pwd.getPassword()).length() == 0) {
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
            Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Forget.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IndieFlower;
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
    public JButton Reset;
    public JLabel game;
    public JLabel jLabel1;
    public JLabel ps;
    public JPasswordField pwd;
    public JRadioButton ShowPwd;
    public JButton Login;
    public JLabel note;
}

