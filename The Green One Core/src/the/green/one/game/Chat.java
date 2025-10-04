package the.green.one.game;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import jiconfont.icons.font_awesome.FontAwesome;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import static org.neo4j.driver.Config.TrustStrategy.trustAllCertificates;

public class Chat extends JFrame implements WindowListener, KeyListener {

    File sendicon = new File("pics/SendIcon.png");
    ImageIcon SendIcon = new ImageIcon(sendicon.getAbsolutePath());
    public String message;
    public String ClientMsg;
    public Thread Taking;
    public Connection con;
    public Statement stt;
    public ResultSet res;
    public String ComingFrom;
    public String MsgQuery;
    File json = new File("sources/info.json");
    final String filepath = json.getAbsolutePath();
    String info;
    String email;
    String neo4jUser;
    public Font IndieFlower;
    public Date ToMessageDate;
    public SimpleDateFormat ToMessageDateFormat;
    public String ToMessageDateString;
    public File recordicon = new File("pics/RecordIcon.png");
    public ImageIcon RecordIcon = new ImageIcon(recordicon.getAbsolutePath());
    public File playvoiceicon = new File("pics/PlayVoiceIcon.png");
    public ImageIcon PlayVoiceIcon = new ImageIcon(playvoiceicon.getAbsolutePath());
    public AudioFormat audioformat;
    public DataLine.Info datalineinfo;
    public TargetDataLine targetdataline;
    public Thread StartRecord;
    public AudioInputStream audioinputstream;
    public FileInputStream SaveVoice;
    public FileOutputStream PlayVoice;
    public File TheVoicePath;
    public String VoiceMessageQuery;
    public String VoiceMessage = "Voice Message";
    private final Soeffects cl = new Soeffects();
    public int VoiceMessagesIncreasmentNumber = 0;
    public boolean PlayVoiceMessage = false;
    public Color brown = new Color(210, 105, 30);
    public boolean turnon = false;
    public boolean turnoff = true;
    public int turning = 0;
    public String CheckBlockQuery;
    public boolean block;
    public File emojicon = new File("pics/EmojiIcon.png");
    public ImageIcon EmojiIcon = new ImageIcon(emojicon.getAbsolutePath());
    Color LightGreen = new Color(0, 179, 0);
    private Timer communicateTimer = null;
    private TimerTask communicateTask = null;
    // emojis
    File like = new File("pics/like.png");
    File wow = new File("pics/wow.png");
    File sad = new File("pics/sad.png");
    File haha = new File("pics/haha.png");
    File angry = new File("pics/angry.png");
    File love = new File("pics/love.png");
    File cry = new File("pics/cry.png");
    File sunglasses = new File("pics/sunglasses.png");
    File on = new File("pics/on.png");
    File off = new File("pics/off.png");
    ImageIcon e1Icon = new ImageIcon(like.getAbsolutePath());
    ImageIcon e2Icon = new ImageIcon(love.getAbsolutePath());
    ImageIcon e3Icon = new ImageIcon(sad.getAbsolutePath());
    ImageIcon e4Icon = new ImageIcon(cry.getAbsolutePath());
    ImageIcon e5Icon = new ImageIcon(wow.getAbsolutePath());
    ImageIcon e6Icon = new ImageIcon(haha.getAbsolutePath());
    ImageIcon e7Icon = new ImageIcon(angry.getAbsolutePath());
    ImageIcon e8Icon = new ImageIcon(sunglasses.getAbsolutePath());
    ImageIcon ON = new ImageIcon(on.getAbsolutePath());
    ImageIcon OFF = new ImageIcon(off.getAbsolutePath());
    ImageIcon BeforeRe;
    Image MiddleRe;
    Image AfterRe;
    ImageIcon NewIcon;
    public boolean emojis = false;
    public int turnEmoji = 0;
    public boolean EmojiOn = false;
    public boolean EmojiOff = false;
    public Font EmojiColor;
    private final String DB_URL = this.get_db_url();
    private final String DB_USN = this.get_db_usn();
    private final String DB_PWD = this.get_db_pwd();
    private String statusBase64 = "";
    private final Neo4jDatabaseManager neo4jdatabase = Neo4jDatabaseManager.getInstance();
    private final String usn;
    private final Color statusColor = new Color(215, 3, 252);
    private final HashMap<String, String> tableOfUsers;
    private volatile int isTyping = 0, messagesCounter = 1;
    private volatile boolean stopIsTypingThreads = false;
    private volatile Map<String, String> messagesIDs = new HashMap<>();
    private final String[] imagesExtensions = new String[]{"jpg", "jpeg", "png", "gif", "bmp"};
    private final FileNameExtensionFilter imagesFilter = new FileNameExtensionFilter("Image Files", this.imagesExtensions);
    private final File imagesFolderOfUserPath = new File("C:/Users/" + System.getProperty("user.name") + "/Pictures");
    private final boolean visible = IDSerialzation.getSer()[0];

    public Chat(final String usn, HashMap<String, String> tableOfUsers, Map<String, String> myMessagesIDs) {
        this.initComponents();
        this.addWindowListener(this);
        this.setAlwaysOnTop(true);
        this.setSize(800, 500);
        this.setVisible(false);
        this.setResizable(false);
        this.setIconImage(new ImageIcon(new File("pics/icon.png").getAbsolutePath()).getImage());
        this.setBackground(Color.green);
        this.setTitle("The Green One Network");
        this.usn = usn;
        this.tableOfUsers = tableOfUsers;
        this.messagesIDs.putAll(myMessagesIDs);
        this.getMyUser();
        this.run();
        this.runIsTypingStatusBackgroundThread().execute();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        IconFontSwing.register(FontAwesome.getIconFont());
        Icon sendImageIcon = IconFontSwing.buildIcon(FontAwesome.FILE_IMAGE_O, 22, Color.BLACK);
        MsgBox = new JTextField();
        SendButton = new JButton();
        jScrollPane1 = new JScrollPane();
        TextPanel = new JTextPaneWithBG();
        Title = new JLabel();
        RecordButton = new JButton("Send Voice Message");
        SMS = new JButton();
        PlayTheVoiceMsg = new JButton("Turn On/Off Voice Messages");
        AddEmoji = new JButton("Emojis");
        SendImage = new JButton("Send Photo");
        e1 = new JButton(e1Icon);
        e2 = new JButton(e2Icon);
        e3 = new JButton(e3Icon);
        e4 = new JButton(e4Icon);
        e5 = new JButton(e5Icon);
        e6 = new JButton(e6Icon);
        e7 = new JButton(e7Icon);
        e8 = new JButton(e8Icon);
        imageChooser = new JFileChooser();
        imageChooser.setFont(this.indieFlower(14f));
        e1.setText("Crush");
        e2.setText("Love");
        e3.setText("Sad");
        e4.setText("Crying");
        e5.setText("Surprise");
        e6.setText("Laughing");
        e7.setText("Angry");
        e8.setText("Confidence");
        e1.setFont(this.indieFlower(14f));
        e2.setFont(this.indieFlower(14f));
        e3.setFont(this.indieFlower(14f));
        e4.setFont(this.indieFlower(14f));
        e5.setFont(this.indieFlower(14f));
        e6.setFont(this.indieFlower(14f));
        e7.setFont(this.indieFlower(14f));
        e8.setFont(this.indieFlower(14f));
        photo = new JButton();
        Activity = new JToggleButton();
        AcSt = new JLabel("Activity Status:");
        Activity.setIcon(visible ? ON : OFF);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        SMS.setText("SMS");
        SMS.setFont(this.indieFlower());
        SMS.setBounds(458, 461, 53, 13);
        SMS.setBackground(Color.BLACK);
        SMS.setForeground(Color.WHITE);
        SMS.setEnabled(true);
        Activity.setBackground(visible ? Color.GREEN : Color.WHITE);
        Activity.setBounds(660, 95, 55, 25);
        Activity.setSelected(true);
        Activity.setVisible(true);
        MsgBox.addKeyListener(this);
        MsgBox.setText("Type Something");
        MsgBox.setFont(this.emojiColor());
        MsgBox.setMargin(new Insets(5, 5, 5, 5));
        RecordButton.setBounds(560, 420, 210, 25);
        RecordButton.setIcon(RecordIcon);
        RecordButton.setBackground(Color.WHITE);
        RecordButton.setForeground(Color.BLACK);
        RecordButton.setFont(this.indieFlower(9.5f));
        PlayTheVoiceMsg.setBounds(560, 447, 210, 25);
        PlayTheVoiceMsg.setIcon(PlayVoiceIcon);
        PlayTheVoiceMsg.setBackground(brown);
        PlayTheVoiceMsg.setForeground(Color.BLACK);
        PlayTheVoiceMsg.setFont(this.indieFlower(11f));
        AddEmoji.setBounds(560, 366, 210, 25);
        AddEmoji.setIcon(EmojiIcon);
        AddEmoji.setBackground(Color.RED);
        AddEmoji.setForeground(Color.BLACK);
        AddEmoji.setFont(this.indieFlower(11f));
        SendImage.setBounds(560, 393, 210, 25);
        SendImage.setIcon(sendImageIcon);
        SendImage.setBackground(Color.cyan);
        SendImage.setForeground(Color.BLACK);
        SendImage.setFont(this.indieFlower(11f));
        SendButton.setIcon(SendIcon);
        SendButton.setBackground(LightGreen);
        TextPanel.setContentType("text/html");
        TextPanel.setEditorKit(new HTMLEditorKit());
        TextPanel.setBackground(Color.DARK_GRAY);
        TextPanel.setForeground(Color.WHITE);
        TextPanel.setFont(this.emojiColor());
        TextPanel.setEditable(false);
        TextPanel.setMargin(new Insets(10, 10, 10, 10));
        MsgBox.setBackground(Color.DARK_GRAY);
        MsgBox.setForeground(Color.RED);
        AcSt.setFont(this.indieFlower());
        AcSt.setBounds(550, 95, 102, 22);
        SendButton.setText("");
        SendButton.setFont(this.indieFlower());
        SendButton.addActionListener((ActionEvent evt) -> {
            SendButtonActionPerformed(evt);
        });
        RecordButton.addActionListener((ActionEvent evt) -> {
            RecordButtonActionPerformed(evt);
        });
        PlayTheVoiceMsg.addActionListener((ActionEvent evt) -> {
            PlayVoiceActionPerformed(evt);
        });
        AddEmoji.addActionListener((ActionEvent evt) -> {
            AddEmojiActionPerformed(evt);
        });
        SendImage.addActionListener((ActionEvent evt) -> {
            SendImageActionPerformed(evt);
        });
        SMS.addActionListener((ActionEvent evt) -> {
            SendSMSActionPerformed(evt);
        });
        Activity.addActionListener((ActionEvent evt) -> {
            ActivityStatusActionPerformed(evt);
        });
        MsgBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent evt) {
                MsgBoxFocus(evt);
            }

            @Override
            public void focusLost(FocusEvent evt) {
                MsgBoxUnFocus(evt);
            }
        });
        jScrollPane1.setViewportView(TextPanel);
        jScrollPane1.setPreferredSize(new Dimension(400, 300));
        Title.setFont(this.indieFlower());
        Title.setForeground(new Color(13, 163, 53));
        Title.setText("The Green One Network");
        photo.setBounds(545, 25, 250, 50);
        photo.setEnabled(true);
        photo.setFont(this.indieFlower());
        photo.setBackground(Color.WHITE);
        photo.addActionListener((ActionEvent ae) -> {
            this.takeProfileStatus(ae);
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
                                                        .addComponent(jScrollPane1)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(MsgBox, GroupLayout.PREFERRED_SIZE, 437, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(SendButton, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(198, 198, 198)
                                                .addComponent(Title, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Title, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(MsgBox)
                                        .addComponent(SendButton, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                                .addContainerGap())
        );
        // emojis
        e1.setBackground(Color.RED);
        e2.setBackground(Color.RED);
        e3.setBackground(Color.RED);
        e4.setBackground(Color.RED);
        e5.setBackground(Color.RED);
        e6.setBackground(Color.RED);
        e7.setBackground(Color.RED);
        e8.setBackground(Color.RED);
        e1.setBounds(560, 366, 210, 25);
        e2.setBounds(560, 339, 210, 25);
        e3.setBounds(560, 312, 210, 25);
        e4.setBounds(560, 285, 210, 25);
        e5.setBounds(560, 258, 210, 25);
        e6.setBounds(560, 231, 210, 25);
        e7.setBounds(560, 204, 210, 25);
        e8.setBounds(560, 177, 210, 25);
        e1.setVisible(false);
        e2.setVisible(false);
        e3.setVisible(false);
        e4.setVisible(false);
        e5.setVisible(false);
        e6.setVisible(false);
        e7.setVisible(false);
        e8.setVisible(false);
        e1.addActionListener((ActionEvent evt) -> {
            e1ActionPerformed(evt);
        });
        e2.addActionListener((ActionEvent evt) -> {
            e2ActionPerformed(evt);
        });
        e3.addActionListener((ActionEvent evt) -> {
            e3ActionPerformed(evt);
        });

        e4.addActionListener((ActionEvent evt) -> {
            e4ActionPerformed(evt);
        });
        e5.addActionListener((ActionEvent evt) -> {
            e5ActionPerformed(evt);
        });
        e6.addActionListener((ActionEvent evt) -> {
            e6ActionPerformed(evt);
        });
        e7.addActionListener((ActionEvent evt) -> {
            e7ActionPerformed(evt);
        });
        e8.addActionListener((ActionEvent evt) -> {
            e8ActionPerformed(evt);
        });
        // new things
        this.getContentPane().add(this.Activity);
        this.getContentPane().add(this.AcSt);
        this.getContentPane().add(this.RecordButton);
        this.getContentPane().add(this.PlayTheVoiceMsg);
        this.getContentPane().add(this.SendImage);
        this.getContentPane().add(this.AddEmoji);
        this.getContentPane().add(this.e1);
        this.getContentPane().add(this.e2);
        this.getContentPane().add(this.e3);
        this.getContentPane().add(this.e4);
        this.getContentPane().add(this.e5);
        this.getContentPane().add(this.e6);
        this.getContentPane().add(this.e7);
        this.getContentPane().add(this.e8);
        this.getContentPane().add(this.SMS);
        this.getContentPane().add(this.photo);
        pack();
    }

    private void getMyUser() {
        try (BufferedReader getJson = new BufferedReader(new FileReader(this.filepath))) {
            this.info = getJson.readLine();
            Object o2 = JSONValue.parse(this.info);
            JSONObject jsonObj2 = (JSONObject) o2;
            this.email = (String) jsonObj2.get("AutoEmail");
            this.neo4jUser = this.email.split("@")[0];
            getJson.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getPlayer() {
        String halfOfEmail = this.TakeReceiverAlways();
        return halfOfEmail + "@" + this.tableOfUsers.get(halfOfEmail);
    }

    public boolean on_off() {
        boolean vis = false;
        int conVIS = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            String getVISQuery = "SELECT con FROM account WHERE email = ?";
            PreparedStatement pstt = con.prepareStatement(getVISQuery);
            pstt.setString(1, this.getPlayer());
            res = pstt.executeQuery();
            if (res.next()) {
                conVIS = res.getInt("con");
            }
            if (conVIS == 0) {
                vis = false;
            } else if (conVIS == 1) {
                vis = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return vis;
    }

    public String getDT() {
        String DT = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            String getDTQuery = "SELECT lastSeen FROM account WHERE email = ?";
            PreparedStatement pstt = con.prepareStatement(getDTQuery);
            pstt.setString(1, this.getPlayer());
            res = pstt.executeQuery();
            if (res.next()) {
                DT = res.getString("lastSeen");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return DT;
    }

    public void MsgBoxFocus(FocusEvent evt) {
        if (this.MsgBox.getText().equals("Type Something")) {
            this.MsgBox.setBackground(Color.WHITE);
            this.MsgBox.setText("");
        }
    }

    public void MsgBoxUnFocus(FocusEvent evt) {
        this.MsgBox.setBackground(Color.DARK_GRAY);
        if (this.MsgBox.getText().isEmpty()) {
            this.MsgBox.setText("Type Something");
        }
    }

    public void PlayVoiceActionPerformed(ActionEvent evt) {
        this.PlayVoiceMessage = true;
        this.PlayTheVoiceMsg.setBackground(Color.green);
        turnoff = false;
        turnon = true;
        turning += 1;
        switch (turning) {
            case 2:
                if (!turnon && turnoff) {
                    this.PlayVoiceMessage = true;
                    turnon = true;
                    turnoff = false;
                }
                this.PlayTheVoiceMsg.setBackground(Color.green);
                turning = 0;
                break;
            case 1:
                if (!turnoff && turnon) {
                    this.PlayVoiceMessage = false;
                    turnon = false;
                    turnoff = true;
                }
                this.PlayTheVoiceMsg.setBackground(this.brown);
                break;
        }
    }

    public void SendImageActionPerformed(ActionEvent evt) {
        this.runSendImageBackgroundThread().execute();
    }

    public void AddEmojiActionPerformed(ActionEvent evt) {
        this.emojis = true;
        this.EmojiOn = true;
        this.EmojiOff = false;
        this.turnEmoji += 1;
        switch (turnEmoji) {
            case 2:
                if (!EmojiOn && EmojiOff) {
                    this.emojis = true;
                    EmojiOn = true;
                    EmojiOff = false;
                }
                e1.setVisible(true);
                e2.setVisible(true);
                e3.setVisible(true);
                e4.setVisible(true);
                e5.setVisible(true);
                e6.setVisible(true);
                e7.setVisible(true);
                e8.setVisible(true);
                turnEmoji = 0;
                break;
            case 1:
                if (!EmojiOff && EmojiOn) {
                    this.emojis = false;
                    EmojiOn = false;
                    EmojiOff = true;
                }
                e1.setVisible(false);
                e2.setVisible(false);
                e3.setVisible(false);
                e4.setVisible(false);
                e5.setVisible(false);
                e6.setVisible(false);
                e7.setVisible(false);
                e8.setVisible(false);
                break;
        }
    }

    public void SendButtonActionPerformed(ActionEvent evt) {
        try {
            this.runSendMessageBackgroundThread().execute();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void send(String email, String TheEmail) {
        if (this.MsgBox.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "The Message Is Empty. Try To Type Something");
        } else {
            this.ToMessageDate = new Date();
            this.ToMessageDateFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
            this.ToMessageDateString = this.ToMessageDateFormat.format(this.ToMessageDate);
            this.TextPanel.setForeground(Color.WHITE);
            String msgboxContent = this.MsgBox.getText();
            this.message = "<h2 style='background-color: black'><font color='white'>" + "Me: "
                    + "</h2></font>" + "<h1 style='background-color: white' align='right'><font id='msg"
                    + this.messagesCounter + "' color='orange'>" + msgboxContent + " ------" + this.ToMessageDateString + "</font></h1>";
            this.messagesIDs.put(msgboxContent + " ------" + this.ToMessageDateString, "msg" + this.messagesCounter);
            this.messagesCounter++;
            String msgDB = "From:" + "| " + email + " -> " + msgboxContent + " ------" + this.ToMessageDateString;
            if (EmojiManager.containsEmoji(msgDB)) {
                String cutEmojis = EmojiParser.parseToHtmlDecimal(msgDB);
                msgDB = String.valueOf(cutEmojis);
            }
            this.append(this.message + "\n\n");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
                stt = con.createStatement();
                MsgQuery = "UPDATE account SET msg = ? WHERE email = ?";
                PreparedStatement pstt = con.prepareStatement(MsgQuery);
                pstt.setString(1, msgDB);
                pstt.setString(2, TheEmail);
                pstt.executeUpdate();
                this.Notifications("Message Was Sent Successfully");
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    stt.close();
                    res.close();
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.addToNeo4jDatabase(email, TheEmail, this.ToMessageDateString, msgboxContent);
            this.MsgBox.setText("Type Something");
            JScrollBar scrollBar = this.jScrollPane1.getVerticalScrollBar();
            scrollBar.setValue(scrollBar.getMaximum());
        }
    }

    private void addToNeo4jDatabase(final String me, final String to, final String date, final String content) {
        try {
            this.neo4jdatabase.createRelationship(me, to, date, content);
        } catch (Exception ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addToNeo4jDatabase(final String me, final String to, final String date, final String content, final String base64OfImage) {
        try {
            this.neo4jdatabase.createRelationship(me, to, date, content, base64OfImage);
        } catch (Exception ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private TimerTask recordingAudioTimerTask(Timer recordingAudioCounter) {
        return new TimerTask() {
            @Override
            public void run() {
                float sec;
                try {
                    sec = Float.parseFloat(Chat.this.RecordButton.getText());
                } catch (NumberFormatException ex) {
                    sec = 2.5f;
                }
                if (sec == 0) {
                    this.cancel();
                    recordingAudioCounter.cancel();
                    recordingAudioCounter.purge();
                }
                sec -= 0.5;
                if (sec >= 0) {
                    Chat.this.RecordButton.setText(String.valueOf(sec));
                }
            }
        };
    }

    public void RecordButtonActionPerformed(ActionEvent evt) {
        Thread recordingAudioThread = new Thread(() -> {
            try {
                Chat.this.isTyping = 2;
                Chat.this.neo4jdatabase.setTypingStatus(Chat.this.neo4jUser, Chat.this.getIsTyping());
                SwingUtilities.invokeAndWait(() -> {
                    Chat.this.RecordButton.setForeground(Color.RED);
                    Chat.this.RecordButton.setText("2.5");
                    Timer recordingAudioCounter = new Timer();
                    recordingAudioCounter.scheduleAtFixedRate(this.recordingAudioTimerTask(recordingAudioCounter), 0, 500);
                });
                Chat.this.Notifications("Voice Recording Started\nFor 2 Seconds.");
                Toolkit.getDefaultToolkit().beep();
                // Recording process
                try {
                    Chat.this.audioformat = new AudioFormat(16000, 8, 2, true, true);
                    Chat.this.datalineinfo = new DataLine.Info(TargetDataLine.class, Chat.this.audioformat);
                    if (!AudioSystem.isLineSupported(Chat.this.datalineinfo)) {
                        JOptionPane.showMessageDialog(Chat.this, "Check Your Audio Input Device (Microphone).");
                    } else {
                        Chat.this.targetdataline = (TargetDataLine) AudioSystem.getLine(Chat.this.datalineinfo);
                        synchronized (Chat.this.targetdataline) {
                            Chat.this.targetdataline.open();
                            Chat.this.targetdataline.start();
                        }
                        Chat.this.StartRecord = new Thread(() -> {
                            try {
                                Chat.this.audioinputstream = new AudioInputStream(Chat.this.targetdataline);
                                File SaveRecordFile = new File("sounds/VoiceRecord.wav");
                                Chat.this.TheVoicePath = new File(SaveRecordFile.getAbsolutePath());
                                AudioSystem.write(Chat.this.audioinputstream, AudioFileFormat.Type.WAVE, SaveRecordFile);
                            } catch (IOException ex) {
                                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        Chat.this.StartRecord.start();
                        Thread.sleep(2500);
                        Chat.this.targetdataline.stop();
                        Chat.this.StartRecord.join();
                        Chat.this.isTyping = 0;
                        Chat.this.SendMsgFirst();
                        Chat.this.Notifications("Voice Recording Has Been Completed.");
                    }
                } catch (LineUnavailableException | InterruptedException ex) {
                    Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        Chat.this.targetdataline.close();
                        Chat.this.audioinputstream.close();
                    } catch (NullPointerException | IOException ex) {
                        Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                SwingUtilities.invokeLater(() -> {
                    Chat.this.RecordButton.setForeground(Color.BLACK);
                    Chat.this.RecordButton.setText("Send Voice Message");
                });
            } catch (InterruptedException | InvocationTargetException ex) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        recordingAudioThread.setPriority(Thread.MAX_PRIORITY);
        recordingAudioThread.start();
    }

    public void Notifications(String message) {
        try {
            Notification.sendNotification("the green one network", message, TrayIcon.MessageType.WARNING);
        } catch (AWTException | MalformedURLException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SaveAudio(String TheEmail) {
        try {
            SaveVoice = new FileInputStream(this.TheVoicePath);
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            VoiceMessageQuery = "UPDATE account SET voice = ? WHERE email = ?";
            PreparedStatement pstt = con.prepareStatement(VoiceMessageQuery);
            pstt.setBlob(1, SaveVoice);
            pstt.setString(2, TheEmail);
            pstt.executeUpdate();
        } catch (FileNotFoundException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private SwingWorker runSendImageBackgroundThread() {
        return new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                Chat.this.imageChooser.setAcceptAllFileFilterUsed(false);
                Chat.this.imageChooser.setFileFilter(Chat.this.imagesFilter);
                Chat.this.imageChooser.setCurrentDirectory(Chat.this.imagesFolderOfUserPath);
                Chat.this.imageChooser.setDialogTitle("Select The Photo");
                Chat.this.imageChooser.showOpenDialog(Chat.this);
                File imageFile = Chat.this.imageChooser.getSelectedFile();
                String imageFilePath = imageFile.getAbsolutePath();
                String extension = FilenameUtils.getExtension(imageFilePath);
                ImageIcon imageIcon = new ImageIcon(imageFilePath);
                BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics graphics = bufferedImage.createGraphics();
                imageIcon.paintIcon(null, graphics, 0, 0);
                graphics.dispose();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, extension, baos);
                byte[] imageBytes = baos.toByteArray();
                String imageContents = Base64.getEncoder().encodeToString(imageBytes);
                Chat.this.ToMessageDate = new Date();
                Chat.this.ToMessageDateFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
                Chat.this.ToMessageDateString = Chat.this.ToMessageDateFormat.format(Chat.this.ToMessageDate);
                Chat.this.TextPanel.setForeground(Color.WHITE);
                String p = Chat.this.getPlayer();
                String msgContent = imageFile.getName();
                Chat.this.message = "<h2 style='background-color: black'><font color='white'>" + "Me: " + "</h2></font>"
                        + "<h1 style='background-color: white' align='right'><font id='msg"
                        + Chat.this.messagesCounter + "' color='orange'>" + msgContent + " ------"
                        + Chat.this.ToMessageDateString + "</font></h1><br><p align='right'><img src='"
                        + imageFile.toURI().toURL().toExternalForm() + "' width='150' height='150' align='right'/></p>";
                Chat.this.messagesIDs.put(msgContent + " ------" + Chat.this.ToMessageDateString, "msg" + Chat.this.messagesCounter);
                Chat.this.messagesCounter++;
                String msgDB = "From:" + "| " + Chat.this.email + " -> " + msgContent + " ------" + Chat.this.ToMessageDateString;
                Chat.this.append(Chat.this.message + "\n\n");
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
                    stt = con.createStatement();
                    MsgQuery = "UPDATE account SET msg = ? WHERE email = ?";
                    PreparedStatement pstt = con.prepareStatement(MsgQuery);
                    pstt.setString(1, msgDB);
                    pstt.setString(2, p);
                    pstt.executeUpdate();
                    Chat.this.Notifications("Message Was Sent Successfully");
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        stt.close();
                        res.close();
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                Chat.this.addToNeo4jDatabase(Chat.this.email, p, Chat.this.ToMessageDateString, msgContent, imageContents);
                JScrollBar scrollBar = Chat.this.jScrollPane1.getVerticalScrollBar();
                scrollBar.setValue(scrollBar.getMaximum());
                return null;
            }
        };
    }

    public void SendMsgFirst() {
        this.ToMessageDate = new Date();
        this.ToMessageDateFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
        this.ToMessageDateString = this.ToMessageDateFormat.format(this.ToMessageDate);
        this.VoiceMessagesIncreasmentNumber++;
        this.TextPanel.setForeground(Color.WHITE);
        String p = this.getPlayer();
        String msgContent = this.VoiceMessage + Integer.toString(this.VoiceMessagesIncreasmentNumber);
        this.message = "<h2 style='background-color: black'><font color='white'>" + "Me: "
                + "</h2></font>" + "<h1 style='background-color: white' align='right'><font id='msg"
                + this.messagesCounter + "' color='orange'>" + msgContent + " ------" + this.ToMessageDateString + "</font></h1>";
        this.messagesIDs.put(msgContent + " ------" + this.ToMessageDateString, "msg" + this.messagesCounter);
        this.messagesCounter++;
        String msgDB = "From:" + "| " + this.email + " -> " + msgContent + " ------" + this.ToMessageDateString;
        this.append(this.message + "\n\n");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            MsgQuery = "UPDATE account SET msg = ? WHERE email = ?";
            PreparedStatement pstt = con.prepareStatement(MsgQuery);
            pstt.setString(1, msgDB);
            pstt.setString(2, p);
            pstt.executeUpdate();
            this.SaveAudio(p);
            this.Notifications("Message Was Sent Successfully");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.addToNeo4jDatabase(this.email, p, this.ToMessageDateString, msgContent);
        JScrollBar scrollBar = this.jScrollPane1.getVerticalScrollBar();
        scrollBar.setValue(scrollBar.getMaximum());
    }

    // emojis
    public void e1ActionPerformed(ActionEvent evt) {
        String msgText1 = this.MsgBox.getText();
        String AddEmoji1 = msgText1 + EmojiParser.parseToUnicode(Emojis.LIKED.getEmojiCode());
        this.MsgBox.setText(String.valueOf(AddEmoji1));
    }

    public void e2ActionPerformed(ActionEvent evt) {
        String msgText2 = this.MsgBox.getText();
        String AddEmoji2 = msgText2 + EmojiParser.parseToUnicode(Emojis.LOVE.getEmojiCode()) + "|LOVE|";
        this.MsgBox.setText(String.valueOf(AddEmoji2));
    }

    public void e3ActionPerformed(ActionEvent evt) {
        String msgText3 = this.MsgBox.getText();
        String AddEmoji3 = msgText3 + EmojiParser.parseToUnicode(Emojis.SAD.getEmojiCode());
        this.MsgBox.setText(String.valueOf(AddEmoji3));
    }

    public void e4ActionPerformed(ActionEvent evt) {
        String msgText4 = this.MsgBox.getText();
        String AddEmoji4 = msgText4 + EmojiParser.parseToUnicode(Emojis.CRY.getEmojiCode());
        this.MsgBox.setText(String.valueOf(AddEmoji4));
    }

    public void e5ActionPerformed(ActionEvent evt) {
        String msgText5 = this.MsgBox.getText();
        String AddEmoji5 = msgText5 + EmojiParser.parseToUnicode(Emojis.WOW.getEmojiCode());
        this.MsgBox.setText(String.valueOf(AddEmoji5));
    }

    public void e6ActionPerformed(ActionEvent evt) {
        String msgText6 = this.MsgBox.getText();
        String AddEmoji6 = msgText6 + EmojiParser.parseToUnicode(Emojis.VERYLAUGH.getEmojiCode());
        this.MsgBox.setText(String.valueOf(AddEmoji6));
    }

    public void e7ActionPerformed(ActionEvent evt) {
        String msgText7 = this.MsgBox.getText();
        String AddEmoji7 = msgText7 + EmojiParser.parseToUnicode(Emojis.ANGRY.getEmojiCode());
        this.MsgBox.setText(String.valueOf(AddEmoji7));
    }

    public void e8ActionPerformed(ActionEvent evt) {
        String msgText8 = this.MsgBox.getText();
        String AddEmoji8 = msgText8 + EmojiParser.parseToUnicode(Emojis.CONFIDENT.getEmojiCode());
        this.MsgBox.setText(String.valueOf(AddEmoji8));
    }

    //sms message
    public void SendSMSActionPerformed(ActionEvent evt) {
        if (this.CutTheString().contains("mohaboko31")) {
            String from = this.TakeReceiverAlways();
            NexmoClient client = new NexmoClient.Builder().apiKey("9789a55e").apiSecret("B13KZYvwolKR7sAs").build();
            String messageText = MsgBox.getText();
            TextMessage message = new TextMessage(from, "79676695811", messageText);
            SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
            this.Notifications("Message Was Sent Successfully To The Game Developer");
        } else {
            this.Notifications("The SMS Messages Can Only Be Send To The Game Developer");
        }
    }

    private SwingWorker runActivityButtonBackgroundThread(ActionEvent evt) {
        return new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                boolean status = ((JToggleButton) evt.getSource()).isSelected();
                if (status) {
                    Chat.this.Activity.setSelected(true);
                    Chat.this.Activity.setBackground(Color.GREEN);
                    Chat.this.Activity.setIcon(Chat.this.ON);
                    Chat.this.Visibility(1);
                } else {
                    Chat.this.Activity.setSelected(false);
                    Chat.this.Activity.setBackground(Color.WHITE);
                    Chat.this.Activity.setIcon(Chat.this.OFF);
                    Chat.this.Visibility(0);
                }
                return null;
            }
        };
    }

    public void ActivityStatusActionPerformed(ActionEvent evt) {
        this.runActivityButtonBackgroundThread(evt).execute();
    }
    private JTextField MsgBox;
    private JButton SendButton;
    protected JTextPane TextPanel;
    private JLabel Title, AcSt;
    private JScrollPane jScrollPane1;
    private JButton RecordButton;
    private JButton SMS;
    private JButton PlayTheVoiceMsg;
    private JButton AddEmoji;
    private JButton SendImage;
    private JButton e1;
    private JButton e2;
    private JButton e3;
    private JButton e4;
    private JButton e5;
    private JButton e6;
    private JButton e7;
    private JButton e8;
    private JButton photo;
    private JToggleButton Activity;
    private JFileChooser imageChooser;

    public void setProfileBorderAndGetStatus() {
        if (!this.block) {
            this.statusBase64 = this.neo4jdatabase.getPlayerStatus(this.TakeReceiverAlways());
            if (!statusBase64.equals("0")) {
                this.photo.setBackground(this.statusColor);
            } else {
                this.photo.setBackground(Color.WHITE);
            }
        } else {
            this.photo.setBackground(Color.WHITE);
        }
    }

    private void takeProfileStatus(ActionEvent ae) {
        if (!this.block) {
            if (!this.statusBase64.equals("0")) {
                this.getPlayerStatus(ae, this.statusBase64);
            }
        }
    }

    private void getPlayerStatus(ActionEvent ae, String statusBase64) {
        Image status;
        byte[] statusBytes = Base64.getDecoder().decode(statusBase64);
        status = new ImageIcon(statusBytes).getImage();
        this.viewPlayerStatus(status);
    }

    private void viewPlayerStatus(Image status) {
        JOptionPane.showMessageDialog(this, "", "Player's Status", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(status.getScaledInstance(250, 250, Image.SCALE_DEFAULT)));
    }

    private String getLastMessageContentFromNeo4jDatabase(final String emailOfSender) {
        String content = this.neo4jdatabase.getLastMessage(this.neo4jUser, emailOfSender, this.tableOfUsers);
        return content;
    }

    public void takeProfilePic() {
        if (this.block) {
            File accounticon = new File("pics/AccountIcon.png");
            this.BeforeRe = new ImageIcon(accounticon.getAbsolutePath());
            this.photo.setIcon(this.BeforeRe);
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
                stt = con.createStatement();
                MsgQuery = "SELECT * FROM account WHERE email = ?";
                PreparedStatement pstt = con.prepareStatement(MsgQuery);
                pstt.setString(1, this.getPlayer());
                res = pstt.executeQuery();
                if (res.next()) {
                    byte[] ProfPicBin = res.getBytes("photo");
                    ImageIcon BeforeRe = new ImageIcon(ProfPicBin);
                    Image MiddleRe = BeforeRe.getImage();
                    Image AfterRe = MiddleRe.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    ImageIcon NewProfPic = new ImageIcon(AfterRe);
                    this.photo.setIcon(NewProfPic);
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    stt.close();
                    res.close();
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void takeProfileLastSeen() {
        if (this.block) {
            this.photo.setText("You Can't See Info");
        } else {
            if (this.on_off()) {
                this.photo.setText("Online Now");
            } else {
                String DT = this.getDT();
                this.photo.setText("Last Seen: " + DT);
            }
        }
    }

    public void VoiceMessageRead(String player) {
        if (player.equals(this.getPlayer())) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
                stt = con.createStatement();
                MsgQuery = "SELECT voice FROM account WHERE email = ?";
                PreparedStatement pstt = con.prepareStatement(MsgQuery);
                pstt.setString(1, email);
                res = pstt.executeQuery();
                if (res.next()) {
                    byte[] voiceMsg;
                    voiceMsg = res.getBytes("voice");
                    if (voiceMsg.length != 0) {
                        try {
                            this.PlayVoice = new FileOutputStream("sounds/VoiceMsg.wav");
                            this.PlayVoice.write(voiceMsg);
                            this.PlayVoice.flush();
                            this.PlayVoice.close();
                            File voice = new File("sounds/VoiceMsg.wav");
                            File PlayAudio = new File(voice.getAbsolutePath());
                            if (this.PlayVoiceMessage) {
                                this.soeffects(PlayAudio);
                            }
                        } catch (NullPointerException ex) {
                        }
                    }
                }
            } catch (ClassNotFoundException | SQLException | IOException ex) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            } finally {
                try {
                    stt.close();
                    res.close();
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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

    public Font indieFlower() {
        File font = new File("sources/IndieFlower-Regular.ttf");
        File FontFile = new File(font.getAbsolutePath());
        try {
            this.IndieFlower = Font.createFont(Font.TRUETYPE_FONT, FontFile).deriveFont(14f);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            env.registerFont(Font.createFont(Font.TRUETYPE_FONT, FontFile));
            return IndieFlower;
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
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
            return IndieFlower;
        } catch (FontFormatException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IndieFlower;
    }

    public Font emojiColor() {
        File font2 = new File("sources/DejaVuSans.ttf");
        File FontFile2 = new File(font2.getAbsolutePath());
        try {
            this.EmojiColor = Font.createFont(Font.TRUETYPE_FONT, FontFile2).deriveFont(12f);
            GraphicsEnvironment env2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            env2.registerFont(Font.createFont(Font.TRUETYPE_FONT, FontFile2));
            return EmojiColor;
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return EmojiColor;
    }

    public void CheckingBlock(String name, String to) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            CheckBlockQuery = "SELECT * FROM blocking WHERE (user = ? AND usertoblock = ?) OR (usertoblock = ? AND user = ?)";
            PreparedStatement pstt = con.prepareStatement(CheckBlockQuery);
            pstt.setString(1, to);
            pstt.setString(2, name);
            pstt.setString(3, name);
            pstt.setString(4, to);
            res = pstt.executeQuery();
            if (res.next()) {
                this.block = true;
                JOptionPane.showMessageDialog(this, "You Can Not Send Messages To This Player");
            } else {
                this.block = false;
                this.send(name, to);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void CheckingBlock2(String name, String to) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            CheckBlockQuery = "SELECT * FROM blocking WHERE (user = ? AND usertoblock = ?) OR (usertoblock = ? AND user = ?)";
            PreparedStatement pstt = con.prepareStatement(CheckBlockQuery);
            pstt.setString(1, to);
            pstt.setString(2, name);
            pstt.setString(3, name);
            pstt.setString(4, to);
            res = pstt.executeQuery();
            this.block = res.next();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String CutTheString() {
        Document doc = Jsoup.parse(this.TextPanel.getText());
        String Area = doc.body().text().trim();
        String[] splitOfArea = Area.split(">");
        String First = splitOfArea[0];
        String[] split1 = First.split("@");
        String FirstHalf = split1[0];
        String TheUsn = FirstHalf;
        return TheUsn;
    }

    public String TakeReceiverAlways() {
        String BeforeCut2 = this.CutTheString();
        String TheEmail2 = BeforeCut2.substring(7);
        return TheEmail2.trim();
    }

    public void Visibility(int OnOrOff) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            String CheckStatusQuery = "UPDATE account SET con = ? WHERE email = ?";
            PreparedStatement pstt = con.prepareStatement(CheckStatusQuery);
            pstt.setInt(1, OnOrOff);
            pstt.setString(2, email);
            pstt.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private SwingWorker runSendMessageBackgroundThread() {
        return new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                Chat.this.CheckingBlock(Chat.this.email, Chat.this.getPlayer());
                return null;
            }
        };
    }

    private SwingWorker runBackgroundThread() {
        return new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                if (Chat.this.block) {
                    Chat.this.Notifications("You Can Not Receive Messages From This Player");
                } else {
                    Chat.this.ClientMsg = Chat.this.getLastMessageContentFromNeo4jDatabase(Chat.this.usn);
                    if (Chat.this.ClientMsg.contains(Emojis.LOVE.getEmojiCode()) || Chat.this.ClientMsg.contains("|LOVE|")) {
                        Chat.this.ShowLove(true);
                    } else {
                        Chat.this.ShowLove(false);
                    }
                    String putEmojis = EmojiParser.parseToUnicode(Chat.this.ClientMsg);
                    Chat.this.ClientMsg = String.valueOf(putEmojis);
                    if (!Chat.this.ClientMsg.isEmpty() && !Chat.this.ClientMsg.equals("\n\n")) {
                        Chat.this.Notifications("One New Message");
                        Chat.this.TextPanel.setForeground(Color.ORANGE);
                        Chat.this.append(Chat.this.ClientMsg);
                        SwingUtilities.invokeAndWait(() -> {
                            JScrollBar scrollBar = Chat.this.jScrollPane1.getVerticalScrollBar();
                            scrollBar.setValue(scrollBar.getMaximum());
                        });
                        if (Chat.this.ClientMsg.contains("Voice Message")) {
                            int i = Chat.this.ClientMsg.indexOf("From:|") + 7;
                            try {
                                Chat.this.VoiceMessageRead(Chat.this.ClientMsg.substring(i,
                                        Chat.this.ClientMsg.indexOf("->") - 1));
                            } catch (NullPointerException ex) {
                            }
                        }
                    }
                }
                Chat.this.takeProfilePic();
                Chat.this.checkSeenStatus();
                Chat.this.takeProfileLastSeen();
                Chat.this.setProfileBorderAndGetStatus();
                return null;
            }
        };
    }

    private void checkSeenStatus() {
        this.messagesIDs.entrySet().forEach((entry) -> {
            boolean isSeen = this.neo4jdatabase.getMessageSeenStatus(this.neo4jUser, this.usn, entry.getKey());
            if (isSeen) {
                HTMLDocument document = (HTMLDocument) this.TextPanel.getDocument();
                Element element = document.getElement(entry.getValue());
                Style style = document.addStyle("seen_style", null);
                StyleConstants.setForeground(style, Color.GREEN);
                int start = element.getStartOffset(), end = element.getEndOffset();
                document.setCharacterAttributes(start, end - start, style, false);
            }
        });
    }

    private SwingWorker runIsTypingStatusBackgroundThread() {
        return new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                Thread gettingTypingStatusThread = new Thread(() -> {
                    while (true) {
                        if (Chat.this.stopIsTypingThreads) {
                            break;
                        }
                        int isTyping = Chat.this.neo4jdatabase.getTypingStatus(Chat.this.getPlayer().split("@")[0]);
                        switch (isTyping) {
                            case 1:
                                Chat.this.photo.setText("Is Typing...");
                                break;
                            case 2:
                                Chat.this.photo.setText("Is Recording Voice...");
                                break;
                            default:
                                break;
                        }
                    }
                });
                Thread settingTypingStatusThread = new Thread(() -> {
                    while (true) {
                        if (Chat.this.stopIsTypingThreads) {
                            break;
                        }
                        Chat.this.neo4jdatabase.setTypingStatus(Chat.this.neo4jUser, Chat.this.getIsTyping());
                    }
                });
                gettingTypingStatusThread.setDaemon(true);
                settingTypingStatusThread.setDaemon(true);
                settingTypingStatusThread.start();
                gettingTypingStatusThread.start();
                return null;
            }
        };
    }

    private void run() {
        try {
            new Thread(() -> {
                try {
                    SwingUtilities.invokeAndWait(() -> {
                        JScrollBar scrollBar = this.jScrollPane1.getVerticalScrollBar();
                        scrollBar.setValue(scrollBar.getMaximum());
                    });
                } catch (InterruptedException | InvocationTargetException ex) {
                    Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }).start();
            this.communicateTimer = new Timer();
            this.communicateTask = this.communicateTask();
            this.communicateTimer.scheduleAtFixedRate(this.communicateTask, 3500L, 5000L);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private TimerTask communicateTask() {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    Chat.this.CheckingBlock2(Chat.this.email, Chat.this.getPlayer());
                    Chat.this.runBackgroundThread().execute();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        };
    }

    public void ShowLove(boolean know) {
        if (know) {
            Image MiddleRe = e2Icon.getImage();
            Image AfterRe = MiddleRe.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon finalIcon = new ImageIcon(AfterRe);
            this.SendButton.setIcon(finalIcon);
        } else {
            this.SendButton.setIcon(SendIcon);
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
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error With DataBase Connection");
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error With DataBase Connection");
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error With DataBase Connection");
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error With DataBase Connection");
        }
        return pwd;
    }

    private synchronized int getIsTyping() {
        return this.isTyping;
    }

    private void append(String msg) {
        HTMLDocument htmlDocument = (HTMLDocument) this.TextPanel.getDocument();
        HTMLEditorKit htmlEditorKit = (HTMLEditorKit) this.TextPanel.getEditorKit();
        try {
            htmlEditorKit.insertHTML(htmlDocument, htmlDocument.getLength(), msg, 0, 0, null);
        } catch (BadLocationException | IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent we) {
        if (this.cl.clip.isOpen() || this.cl.clip.isActive() || this.cl.clip.isRunning()) {
            this.cl.clip.close();
            this.cl.clip.stop();
        }
        this.stopIsTypingThreads = true;
        this.communicateTask.cancel();
        this.communicateTimer.cancel();
        this.communicateTimer.purge();
        IncomingMessages incomingMessages = new IncomingMessages();
        incomingMessages.setVisible(true);
        incomingMessages.getReceivedMessages().execute();
        this.setVisible(false);
        this.dispose();
    }

    @Override
    public void windowClosed(WindowEvent we) {
    }

    @Override
    public void windowIconified(WindowEvent we) {
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowActivated(WindowEvent we) {
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        this.isTyping = 1;
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        this.isTyping = 0;
    }
}

