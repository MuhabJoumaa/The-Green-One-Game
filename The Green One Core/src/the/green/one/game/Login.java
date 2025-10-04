package the.green.one.game;

import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
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
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import org.apache.commons.validator.routines.EmailValidator;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Login extends JFrame {

    Menu menu = null;
    public Connection con;
    public Statement stt;
    public ResultSet res;
    String Email, pss, checkQuery;
    BufferedImage network;
    private String AutoEmail, AutoPwd;
    File ntw = new File("pics/network.jpg"), net = new File(ntw.getAbsolutePath()),
            rr1 = new File("sources/info.json"), json = new File("sources/info.json"),
            sec = new File("pics/secure.png"), p = new File("THE GREEN ONE GAME.exe"),
            waitingstateicon = new File("pics/waitingStateIcon.gif");
    Color green = new Color(0, 179, 0);
    String info, bin = "", email2;
    FileOutputStream Image;
    ImageIcon ProfilePhoto, AfterBin, secure = new ImageIcon(sec.getAbsolutePath()),
            icon = new ImageIcon(ntw.getAbsolutePath()),
            WaitingStateIcon = new ImageIcon(waitingstateicon.getAbsolutePath());
    Border PhotoBorder, EmailBorder, PwdBorder;
    Font IndieFlower;
    final String inbin = "ConvertToBin", unbin = "ConvertToDec";
    public final String filepath = json.getAbsolutePath(), path = this.p.getAbsolutePath();
    public int Success = 0;
    public Register CreateAccount;
    ScriptEngineManager manager;
    ScriptEngine engine;
    private final String DB_URL = this.get_db_url(), DB_USN = this.get_db_usn(), DB_PWD = this.get_db_pwd();

    public Login() {
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
            this.network = ImageIO.read(this.net);
        } catch (IOException e) {
            System.out.print("Error try agian");
        }
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
        ga = new JButton();
        game = new JLabel();
        ProfileImage = new JLabel();
        Forget = new JButton("Forget Password");
        create_account = new JButton("Create Account");
        remember = new JRadioButton("Remember Me");
        ShowPwd = new JRadioButton("Show Password");
        ShowPwd.setSelected(false);
        remember.setSelected(true);
        note.setText("Passwords Will Be Encrypted Automatically");
        note.setFont(this.indieFlower());
        ga.setFont(this.indieFlower());
        Forget.setFont(this.indieFlower());
        create_account.setFont(this.indieFlower());
        remember.setFont(this.indieFlower());
        ShowPwd.setFont(this.indieFlower());
        pwd.setFont(this.indieFlower());
        email.setFont(this.indieFlower());
        getContentPane().add(this.remember);
        getContentPane().add(this.ShowPwd);
        getContentPane().add(this.note);
        getContentPane().add(this.ProfileImage);
        getContentPane().add(this.Forget);
        getContentPane().add(this.create_account);
        this.ProfileImage.setBounds(3, 346, 155, 121);
        Forget.setBounds(270, 350, 170, 20);
        create_account.setBounds(270, 322, 170, 20);
        EmailBorder = BorderFactory.createLineBorder(Color.BLACK);
        PwdBorder = BorderFactory.createLineBorder(Color.BLACK);
        PhotoBorder = BorderFactory.createLineBorder(Color.GREEN);
        email.setBackground(Color.DARK_GRAY);
        pwd.setBackground(Color.LIGHT_GRAY);
        email.setForeground(green);
        pwd.setForeground(green);
        email.setBorder(EmailBorder);
        pwd.setBorder(PwdBorder);
        this.ProfileImage.setBorder(PhotoBorder);
        try {
            try (BufferedReader getJson = new BufferedReader(new FileReader(filepath))) {
                info = getJson.readLine();
                Object o2 = JSONValue.parse(info);
                JSONObject jsonObj2 = (JSONObject) o2;
                String autoEmail = (String) jsonObj2.get("AutoEmail");
                email.setText(autoEmail);
                this.email2 = autoEmail;
                Encryption encr2 = new Encryption();
                String toUnBin = "";
                if (!email.getText().equals("example@domain.com")) {
                    String pwd = (String) jsonObj2.get("AutoPwd");
                    toUnBin += this.ConnectToJS(this.unbin, pwd);
                }
                pwd.setText(encr2.decrypt(toUnBin, (String) jsonObj2.get("AutoEmail")));
                getJson.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            checkQuery = "SELECT * FROM account WHERE email = ?";
            PreparedStatement pstt = con.prepareStatement(checkQuery);
            pstt.setString(1, email.getText());
            res = pstt.executeQuery();
            if (res.next()) {
                byte[] bin2 = res.getBytes("photo");
                this.bin = String.valueOf(bin2.length);
                this.ProfilePhoto = new ImageIcon(bin2);
                Image img = this.ProfilePhoto.getImage();
                Image Convert = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
                this.AfterBin = new ImageIcon(Convert);
                this.ProfileImage.setIcon(AfterBin);
                this.ProfileImage.setSize(this.AfterBin.getIconWidth(), this.AfterBin.getIconHeight());
                this.repaint();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage() + "An Error Has Been Occured.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        remember.setBounds(270, 380, 170, 20);
        ShowPwd.setBounds(320, 270, 170, 20);
        note.setBackground(Color.BLACK);
        note.setForeground(Color.BLUE);
        note.setBounds(40, 78, 300, 40);
        jLabel1.setFont(this.indieFlower());
        jLabel1.setForeground(new java.awt.Color(0, 128, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Log In To Your Account");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        E.setFont(this.indieFlower());
        E.setText("Email :");
        ga.addActionListener(this::LoginActionPerformed);
        ShowPwd.addActionListener(this::ShowPwdActionPerformed);
        Forget.addActionListener(this::ForgetActionPerformed);
        create_account.addActionListener(this::CreateAccountActionPerformed);
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
        ga.setText("Login");
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
        ga.setBackground(Color.BLACK);
        ga.setForeground(Color.WHITE);
        Forget.setBackground(Color.BLUE);
        Forget.setForeground(Color.BLACK);
        create_account.setBackground(Color.ORANGE);
        create_account.setForeground(Color.black);
        pack();
        this.setLocationRelativeTo(null);
    }

    private SwingWorker openMenu() {
        return new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                Login.this.menu = new Menu();
                Login.this.menu.initVolumeFloatControl();
                Login.this.menu.isUpdate(0);
                Login.this.menu.setVisible(true);
                Login.this.setVisible(false);
                Login.this.dispose();
                return null;
            }
        };
    }

    public void LoginActionPerformed(ActionEvent evt) {
        this.runLoginBackgroundThread().execute();
    }

    private SwingWorker runLoginBackgroundThread() {
        return new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                new Thread(() -> {
                    try {
                        SwingUtilities.invokeAndWait(() -> {
                            Login.this.game.setText("Please, Wait A Moment........");
                            Login.this.game.setIcon(Login.this.WaitingStateIcon);
                            Login.this.ga.setEnabled(false);
                        });
                    } catch (InterruptedException | InvocationTargetException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getMessage());
                    }
                }).start();
                EmailValidator emailValidator = EmailValidator.getInstance();
                if (email.getText().contains("@") && email.getText().length() >= 13 && emailValidator.isValid(email.getText()) && pwd.getPassword().length >= 8) {
                    Email = email.getText();
                    String copyOfPss = String.valueOf(pwd.getPassword());
                    pss = Encryption.doMD5(copyOfPss);
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
                        stt = con.createStatement();
                        checkQuery = "SELECT * FROM account WHERE email = ? AND BINARY pwd = ?";
                        PreparedStatement pstt = con.prepareStatement(checkQuery);
                        pstt.setString(1, Email);
                        pstt.setString(2, pss);
                        res = pstt.executeQuery();
                        if (!res.next()) {
                            EmailBorder = BorderFactory.createLineBorder(Color.RED);
                            email.setBorder(EmailBorder);
                            PwdBorder = BorderFactory.createLineBorder(Color.RED);
                            pwd.setBorder(PwdBorder);
                            Login.this.ga.setEnabled(true);
                            try {
                                Notification.sendNotification("The Green One Network", "Login Failed", TrayIcon.MessageType.ERROR);
                            } catch (AWTException | MalformedURLException ex) {
                                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            JOptionPane.showMessageDialog(Login.this, "Invaild Email Or Password", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            EmailBorder = BorderFactory.createLineBorder(Color.BLACK);
                            email.setBorder(EmailBorder);
                            PwdBorder = BorderFactory.createLineBorder(Color.BLACK);
                            pwd.setBorder(PwdBorder);
                            Login.this.Success = 1;
                            if (remember.isSelected()) {
                                AutoEmail = res.getString("email");
                                AutoPwd = copyOfPss;
                                JSONObject j = new JSONObject();
                                Encryption encr = new Encryption();
                                j.put("AutoEmail", AutoEmail);
                                j.put("AutoPwd", Login.this.ConnectToJS(Login.this.inbin, encr.encrypt(AutoPwd, AutoEmail)));
                                try (PrintWriter print = new PrintWriter(new BufferedWriter(new FileWriter(rr1.getAbsolutePath())))) {
                                    print.println(j);
                                    print.close();
                                } catch (IOException ex) {
                                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                                    JOptionPane.showMessageDialog(Login.this, ex.getMessage());
                                }
                            }
                            try {
                                Notification.sendNotification("The Green One Network", "Enter The Green One Adventure", TrayIcon.MessageType.NONE);
                            } catch (AWTException | MalformedURLException ex) {
                                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            SwingUtilities.invokeLater(Login.this.openMenu()::execute);
                        }
                    } catch (ClassNotFoundException | SQLException ex) {
                        Login.this.ga.setEnabled(true);
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(Login.this, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        try {
                            stt.close();
                            res.close();
                            con.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    Login.this.ga.setEnabled(true);
                    if (pwd.getPassword().length < 8) {
                        PwdBorder = BorderFactory.createLineBorder(Color.RED);
                        pwd.setBorder(PwdBorder);
                        JOptionPane.showMessageDialog(Login.this, "Your Password Must Be At Least 8 Letters/Symbols..", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (pwd.getPassword().length > 32) {
                        PwdBorder = BorderFactory.createLineBorder(Color.RED);
                        pwd.setBorder(PwdBorder);
                        JOptionPane.showMessageDialog(Login.this, "Your Password Must Be No Longer Than 32 Letters/Symbols..", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (pwd.getPassword().length >= 8 && pwd.getPassword().length <= 32) {
                        PwdBorder = BorderFactory.createLineBorder(Color.BLACK);
                        pwd.setBorder(PwdBorder);
                    }
                    if (!email.getText().contains("@") || email.getText().length() < 13 || !emailValidator.isValid(email.getText()) || email.getText().contains(" ") || email.getText().equals("example@domain.com")) {
                        EmailBorder = BorderFactory.createLineBorder(Color.RED);
                        email.setBorder(EmailBorder);
                        JOptionPane.showMessageDialog(Login.this, "Incorrect Email" + "\n" + "Check Your Email Please", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (email.getText().contains("@") && email.getText().length() >= 13 && emailValidator.isValid(email.getText()) && !email.getText().contains(" ") && !email.getText().equals("example@domain.com")) {
                        EmailBorder = BorderFactory.createLineBorder(Color.BLACK);
                        email.setBorder(EmailBorder);
                    }
                }
                if (Login.this.email2.equals("example@domain.com") && Login.this.Success == 1) {
                    Runtime.getRuntime().exec(Login.this.path);
                    ClosingOperation.closeBackendProgram();
                }
                new Thread(() -> {
                    try {
                        SwingUtilities.invokeAndWait(() -> {
                            Login.this.game.setText("Welcome To The Game's Network");
                            Login.this.game.setIcon(null);
                        });
                    } catch (InterruptedException | InvocationTargetException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getMessage());
                    }
                }).start();
                return null;
            }
        };
    }

    public void ShowPwdActionPerformed(ActionEvent evt) {
        if (!ShowPwd.isSelected()) {
            pwd.setEchoChar('*');
        } else if (ShowPwd.isSelected()) {
            pwd.setEchoChar((char) 0);
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
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IndieFlower;
    }

    private SwingWorker runForgetBackgroundThread() {
        return new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                new Thread(() -> {
                    try {
                        SwingUtilities.invokeAndWait(() -> {
                            Login.this.game.setText("Please, Wait A Moment........");
                            Login.this.game.setIcon(Login.this.WaitingStateIcon);
                        });
                    } catch (InterruptedException | InvocationTargetException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getMessage());
                    }
                }).start();
                Forget forget = new Forget();
                forget.setVisible(true);
                Login.this.setVisible(false);
                Login.this.dispose();
                new Thread(() -> {
                    try {
                        SwingUtilities.invokeAndWait(() -> {
                            Login.this.game.setText("Welcome To The Game's Network");
                            Login.this.game.setIcon(null);
                        });
                    } catch (InterruptedException | InvocationTargetException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getMessage());
                    }
                }).start();
                return null;
            }
        };
    }

    public void ForgetActionPerformed(ActionEvent evt) {
        this.runForgetBackgroundThread().execute();
    }

    private SwingWorker runCreateAccountBackgroundThread() {
        return new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                new Thread(() -> {
                    try {
                        SwingUtilities.invokeAndWait(() -> {
                            Login.this.game.setText("Please, Wait A Moment........");
                            Login.this.game.setIcon(Login.this.WaitingStateIcon);
                        });
                    } catch (InterruptedException | InvocationTargetException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getMessage());
                    }
                }).start();
                Login.this.CreateAccount = new Register();
                Login.this.CreateAccount.setVisible(true);
                Login.this.setVisible(false);
                Login.this.dispose();
                new Thread(() -> {
                    try {
                        SwingUtilities.invokeAndWait(() -> {
                            Login.this.game.setText("Welcome To The Game's Network");
                            Login.this.game.setIcon(null);
                        });
                    } catch (InterruptedException | InvocationTargetException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getMessage());
                    }
                }).start();
                return null;
            }
        };
    }

    public void CreateAccountActionPerformed(ActionEvent evt) {
        this.runCreateAccountBackgroundThread().execute();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (this.bin.length() == 0) {
            g.drawImage(network, 3, 346, this);
        }
    }

    public String ConnectToJS(String FuncName, String pass) {
        Object result = "";
        manager = new ScriptEngineManager();
        engine = manager.getEngineByName("nashorn");
        try {
            engine.eval(""
                    + "function ConvertToBin(s) {\n"
                    + "var res=\"\";\n"
                    + "var final=\"\";\n"
                    + "for(var i=0;i<s.length;i++) {\n"
                    + "var c=s.charCodeAt(i);\n"
                    + "res=\"\";\n"
                    + "while(c!==0) {\n"
                    + "var rem=c % 2;\n"
                    + "var frac=rem % 1;\n"
                    + "var reminder=rem-frac;\n"
                    + "res+=reminder.toString();\n"
                    + "c=parseInt(c/2);\n"
                    + "}\n"
                    + "res+=\"2\";\n"
                    + "for(var j=res.length-1;j>=0;j--) {\n"
                    + "final+=res.charAt(j);\n"
                    + "}\n"
                    + "}\n"
                    + "return final;\n"
                    + "}\n"
                    + "function ConvertToDec(s) {\n"
                    + "	var res=\"\";\n"
                    + "	var final=\"\";\n"
                    + "	var two=1;\n"
                    + "	var dig=0;\n"
                    + "	var charValue=0;\n"
                    + "	for(var i=s.length-1;i>=0;i--) {\n"
                    + "		var c=s.charAt(i);\n"
                    + "		if(c==='2') {\n"
                    + "			charValue=String.fromCharCode(dig);\n"
                    + "			res+=charValue;\n"
                    + "			dig=0;\n"
                    + "			two=1;\n"
                    + "			continue;\n"
                    + "		}\n"
                    + "		dig+=parseInt(c)*two;\n"
                    + "		two*=2;\n"
                    + "	}\n"
                    + "	for(var j=res.length-1;j>=0;j--) {\n"
                    + "		final+=res.charAt(j);\n"
                    + "	}\n"
                    + "	return final;\n"
                    + "}");
            Invocable inv = (Invocable) engine;
            result = inv.invokeFunction(FuncName, pass);
        } catch (ScriptException | NoSuchMethodException ex) {
            Logger.getLogger(Sending.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex);
        }
        return result.toString();
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
    public JLabel game;
    public JLabel jLabel1;
    public JLabel ps;
    public JPasswordField pwd;
    public JRadioButton remember;
    public JRadioButton ShowPwd;
    public JLabel note;
    public JLabel ProfileImage;
    public JButton Forget;
    public JButton create_account;
}

