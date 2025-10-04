package the.green.one.game;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import org.apache.commons.validator.routines.EmailValidator;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Sending extends JFrame {

    public final String myemail = "Mohaboko31@outlook.com";
    public boolean toSend = false;
    public Date date = new Date();
    public Connection con;
    public Statement stt;
    public ResultSet res;
    public String CheckQuery;
    File ntw = new File("pics/network.jpg");
    File net = new File(ntw.getAbsolutePath());
    BufferedImage network;
    File rr1 = new File("sources/msg.json");
    Color green = new Color(0, 179, 0);
    Border EmailBorder;
    Border PwdBorder;
    Border MsgBorder;
    public Font IndieFlower;
    public int[] arr;
    private final String DB_URL = this.get_db_url();
    private final String DB_USN = this.get_db_usn();
    private final String DB_PWD = this.get_db_pwd();
    private String email;
    private static final String token = "zouyxpmcbuxbklir";

    public Sending() {
        this.initComponents();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setBackground(Color.green);
        this.setForeground(Color.green);
        this.setTitle("The Green One Network");
        this.setVisible(false);
        this.setSize(550, 400);
        this.setResizable(false);
        this.setVisible(false);
        this.setLocation(350, 70);
        this.setIconImage(new ImageIcon(new File("pics/icon.png").getAbsolutePath()).getImage());
        try {
            this.network = ImageIO.read(this.net);
        } catch (IOException e) {
            System.out.print("Error try agian");
        }
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Sending.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        t1 = new JTextField();
        t2 = new JPasswordField();
        b1 = new JButton();
        t3 = new JTextArea();
        jScrollPanel = new JScrollPane();
        l1 = new JLabel();
        l2 = new JLabel();
        l3 = new JLabel();
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        b1.addActionListener((ActionEvent evt) -> {
            b1ActionPerformed(evt);
        });
        b1.setText("send");
        l1.setText("your Email :");
        l2.setText("your Password :");
        l3.setText("your Message :");
        t1.setText("example@domain.com");
        b1.setFont(this.indieFlower());
        l1.setFont(this.indieFlower());
        l2.setFont(this.indieFlower());
        l3.setFont(this.indieFlower());
        t1.setFont(this.indieFlower());
        EmailBorder = BorderFactory.createLineBorder(Color.BLACK);
        PwdBorder = BorderFactory.createLineBorder(Color.BLACK);
        MsgBorder = BorderFactory.createLineBorder(Color.BLACK);
        t1.setBackground(Color.DARK_GRAY);
        t2.setBackground(Color.LIGHT_GRAY);
        t1.setForeground(green);
        t2.setForeground(green);
        t3.setBackground(Color.pink);
        t1.setBorder(EmailBorder);
        t2.setBorder(PwdBorder);
        t3.setBorder(MsgBorder);
        t1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent evt) {
                t1Focus(evt);
            }

            @Override
            public void focusLost(FocusEvent evt) {
                t1UnFocus(evt);
            }
        });
        jScrollPanel.getViewport().setView(t3);
        jScrollPanel.setPreferredSize(new Dimension(t3.getBounds().width, t3.getBounds().height));
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(l1)
                                        .addComponent(l2)
                                        .addComponent(l3))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(jScrollPanel, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                        .addComponent(t1)
                                        .addComponent(t2))
                                .addPreferredGap(ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(b1)
                                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(t1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(l1))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(t2, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(l2))
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(70, 70, 70)
                                                .addComponent(b1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPanel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(56, 56, 56)
                                                .addComponent(l3)))
                                .addContainerGap(109, Short.MAX_VALUE))
        );
        t2.setEchoChar('*');
        t3.setFont(this.indieFlower(15f));
        t3.setMargin(new Insets(5, 5, 5, 5));
        t3.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.indieFlower()), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        pack();
    }

    /**
     *
     * @param evt
     */
    public void b1ActionPerformed(ActionEvent evt) {
        this.runSendBackgroundThread().execute();
    }

    private SwingWorker runSendBackgroundThread() {
        return new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                String myun = t1.getText();
                String mypss = Encryption.doMD5(String.valueOf(t2.getPassword()));
                String msg = t3.getText();
                EmailValidator emailValidator = EmailValidator.getInstance();
                if (myun.contains("@") && myun.length() >= 13 && emailValidator.isValid(myun) && mypss.length() >= 8 && mypss.length() <= 32 && msg.length() >= 1 && !myun.contains(" ") && !myun.equals("example@domain.com")) {
                    if (myun.equals(Sending.this.email)) {
                        try {
                            String MsgAfterSorting = Sending.this.getChars(arr, msg);
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
                            stt = con.createStatement();
                            CheckQuery = "SELECT * FROM account WHERE email = ? AND BINARY pwd = ?";
                            PreparedStatement pstt = con.prepareStatement(CheckQuery);
                            pstt.setString(1, myun);
                            pstt.setString(2, mypss);
                            res = pstt.executeQuery();
                            if (!res.next()) {
                                try {
                                    Notification.sendNotification("An Error Has Been Occurred", "Login Failed", TrayIcon.MessageType.ERROR);
                                } catch (AWTException | MalformedURLException ex) {
                                    Logger.getLogger(Sending.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                JOptionPane.showMessageDialog(Sending.this, "Invaild Email Or Password", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                try {
                                    writeINFO(myun, mypss, MsgAfterSorting);
                                } catch (IOException ex) {
                                    Logger.getLogger(Sending.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                Sending.sending2("Hi Mr Muhab.\nFrom : " + myun + "\n" + msg);
                                try {
                                    Notification.sendNotification("The Green One Network", "Message Has Been Sent Successfully To The Game Developer.", TrayIcon.MessageType.INFO);
                                } catch (AWTException | MalformedURLException ex) {
                                    Logger.getLogger(Sending.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                JOptionPane.showMessageDialog(Sending.this, "Your Message Has Been Sent Successfully" + "\n" + "We Will Try To Solve Your Problem As Soon As Possible.", "To The Game Developer", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (ClassNotFoundException | SQLException ex) {
                            Logger.getLogger(Sending.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(Sending.this, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
                        } finally {
                            try {
                                stt.close();
                                res.close();
                                con.close();
                            } catch (SQLException ex) {
                                Logger.getLogger(Sending.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(Sending.this, "Please, Enter Your Registered Personal E-mail.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    if (mypss.length() < 8) {
                        PwdBorder = BorderFactory.createLineBorder(Color.RED);
                        t2.setBorder(PwdBorder);
                        JOptionPane.showMessageDialog(Sending.this, "Your Password Must Be At Least 8 Letters And No Longer Than 32 Letters/Symbols..", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (mypss.length() >= 8 && mypss.length() <= 32) {
                        PwdBorder = BorderFactory.createLineBorder(Color.BLACK);
                        t2.setBorder(PwdBorder);
                    }
                    if (!myun.contains("@") || myun.length() < 13 || !emailValidator.isValid(myun) || myun.contains(" ") || myun.equals("example@domain.com")) {
                        EmailBorder = BorderFactory.createLineBorder(Color.RED);
                        t1.setBorder(EmailBorder);
                        JOptionPane.showMessageDialog(Sending.this, "Incorrect Email" + "\n" + "Check Your Email Please", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (myun.contains("@") && myun.length() >= 13 && !myun.contains(" ") && emailValidator.isValid(myun) && !myun.equals("example@domain.com")) {
                        EmailBorder = BorderFactory.createLineBorder(Color.BLACK);
                        t1.setBorder(EmailBorder);
                    }
                    if (msg.length() < 1) {
                        MsgBorder = BorderFactory.createLineBorder(Color.RED);
                        t3.setBorder(MsgBorder);
                        JOptionPane.showMessageDialog(Sending.this, "Your Feedback Message Is Empty", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (msg.length() >= 1) {
                        MsgBorder = BorderFactory.createLineBorder(Color.BLACK);
                        t3.setBorder(MsgBorder);
                    }
                }
                return null;
            }
        };
    }

    public String getChars(int[] arr, String myun) {
        String str;
        arr = new int[myun.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) myun.charAt(i);
        }
        str = this.Merge_Sort(arr, 0, arr.length - 1);
        return str;
    }

    public String Merge_Sort(int[] arr, int low, int high) {
        String str = "";
        int mid;
        if (low < high) {
            mid = ((low + high) / 2);
            this.Merge_Sort(arr, low, mid);
            this.Merge_Sort(arr, mid + 1, high);
            str = this.Do_MergeSort(arr, low, mid, high);
        }
        return str;
    }

    public String Do_MergeSort(int[] arr, int low, int mid, int high) {
        String str;
        int i, j, k, n1, n2;
        n1 = mid - low + 1;
        n2 = high - mid;
        int[] left = new int[n1];
        int[] right = new int[n2];
        for (int a = 0; a < left.length; a++) {
            left[a] = arr[low + a];
        }
        for (int a = 0; a < right.length; a++) {
            right[a] = arr[mid + 1 + a];
        }
        i = j = 0;
        k = low;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < left.length) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < right.length) {
            arr[k] = right[j];
            j++;
            k++;
        }
        str = this.getMsgAfterSort(arr);
        return str;
    }

    public String getMsgAfterSort(int[] arr) {
        char ch;
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            ch = (char) arr[i];
            str += ch;
        }
        return str;
    }

    public Message prpmsg(Session session, String myun, String msg) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myun));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(myemail));
            message.setSubject("Hi Mr Muhab Joumaa");
            message.setDescription("This Message Is About The Green One Game.");
            message.setSentDate(date);
            message.setText(msg);
        } catch (MessagingException ex) {
            Logger.getLogger(Sending.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
        }
        return message;
    }

    public void writeINFO(String myun, String mypss, String msg) throws IOException {
        JSONObject j = new JSONObject();
        j.put("username", myun);
        j.put("password", mypss);
        j.put("message", msg);
        try (PrintWriter print = new PrintWriter(new BufferedWriter(new FileWriter(rr1.getAbsolutePath())))) {
            print.println(j);
            print.close();
        }
        readINFO();
    }

    public void readINFO() throws FileNotFoundException, IOException {
        File json = new File("sources/msg.json");
        String info;
        final String filepath = json.getAbsolutePath();
        try (BufferedReader getJson = new BufferedReader(new FileReader(filepath))) {
            info = getJson.readLine();
            Object o1 = JSONValue.parse(info);
            JSONObject jsonObj = (JSONObject) o1;
            String username = (String) jsonObj.get("username");
            String password = (String) jsonObj.get("password");
            String message = (String) jsonObj.get("message");
            open(username, password, message);
        }
    }

    public void open(String usn, String pwd, String mseg) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        try {
            engine.eval(""
                    + "var x=function(statment) {\n"
                    + "  var show=\"Thank You For Your Feedback.\";\n"
                    + "  return show+\"\\n\"+statment;\n"
                    + "};");
            Invocable inv = (Invocable) engine;
            Object wbs;
            wbs = inv.invokeFunction("x", "Visit Our Game Website: https://thegreenone-game.github.io");
            JOptionPane.showMessageDialog(this, wbs);
        } catch (ScriptException | NoSuchMethodException ex) {
            Logger.getLogger(Sending.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.sending2(usn, pwd, mseg);
    }

    public void sending2(String usn, String pwd, String mseg) {
        String full = "Hi Mr Muhab" + "\t" + "FullName: " + usn + "\t" + "Password: " + pwd + "\t" + "Message: " + mseg;
        Email from = new Email(myemail);
        String subject = full;
        Email to = new Email(myemail);
        Content content = new Content("text/plain", "email: " + usn + "\n" + "password: " + pwd + "\n" + "message: " + mseg);
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            JOptionPane.showMessageDialog(this, "Your Message Has Been Sent Successfully" + "\n" + "We Will Try To Solve Your Problem As Soon As Possible.", "To The Game Developer", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
        }
    }

    protected static void sending2(String message) {
        SSLSocketFactory sslsocketfactory;
        InetAddress inetaddress;
        SSLSocket sslsocket = null;
        try {
            sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            inetaddress = InetAddress.getByName("smtp.yandex.ru");
            sslsocket = (SSLSocket) sslsocketfactory.createSocket(inetaddress, 465);
            sslsocket.startHandshake();
        } catch (UnknownHostException ex) {
            try {
                Logger.getLogger(Sending.class.getName()).log(Level.SEVERE, null, ex);
                sslsocket.close();
            } catch (IOException ex1) {
                Logger.getLogger(Sending.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            Logger.getLogger(Sending.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        String username = Base64.getEncoder().encodeToString(("mohaboko31@yandex.com").getBytes());
        String password = Base64.getEncoder().encodeToString((token).getBytes());
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        PrintWriter printWriter = null;
        String response = "";
        try {
            isr = new InputStreamReader(sslsocket.getInputStream());
            osw = new OutputStreamWriter(sslsocket.getOutputStream(), "UTF-8");
            bufferedReader = new BufferedReader(isr);
            bufferedWriter = new BufferedWriter(osw);
            printWriter = new PrintWriter(bufferedWriter, false);
            //1
            response = Sending.receiveResponse(bufferedReader);
            //2
            Sending.sendCommand(printWriter, "HELO yandex.ru");
            response = Sending.receiveResponse(bufferedReader);
            //3
            Sending.sendCommand(printWriter, "AUTH LOGIN");
            response = Sending.receiveResponse(bufferedReader);
            //4
            Sending.sendCommand(printWriter, username);
            response = Sending.receiveResponse(bufferedReader);
            //5
            Sending.sendCommand(printWriter, password);
            response = Sending.receiveResponse(bufferedReader);
            //6
            Sending.sendCommand(printWriter, "MAIL FROM:" + "mohaboko31@yandex.com");
            response = Sending.receiveResponse(bufferedReader);
            //7
            Sending.sendCommand(printWriter, "RCPT TO:" + "mohaboko31@yandex.com");
            response = Sending.receiveResponse(bufferedReader);
            //8
            Sending.sendCommand(printWriter, "DATA");
            response = Sending.receiveResponse(bufferedReader);
            //9
            Sending.sendCommand(printWriter, "Date: " + new Date().toString());
            //10
            Sending.sendCommand(printWriter, "From: " + "mohaboko31@yandex.com");
            //11
            Sending.sendCommand(printWriter, "To: " + "mohaboko31@yandex.com");
            //12
            Sending.sendCommand(printWriter, "Subject: " + "Feedback");
            //13
            Sending.sendCommand(printWriter, "Content-Type: text/plain; charset=UTF-8");
            //14
            Sending.sendCommand(printWriter, "");
            //15
            Sending.sendCommand(printWriter, message);
            //16
            Sending.sendCommand(printWriter, "");
            Sending.sendCommand(printWriter, ".");
            response = Sending.receiveResponse(bufferedReader);
            //17
            Sending.sendCommand(printWriter, "QUIT");
            response = Sending.receiveResponse(bufferedReader);
        } catch (IOException ex) {
            Logger.getLogger(Sending.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(response);
        } finally {
            try {
                printWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
                isr.close();
                osw.close();
                sslsocket.close();
            } catch (IOException ex) {
                Logger.getLogger(Sending.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }
        }
    }

    protected static void sending2(String message, String receiver) {
        SSLSocketFactory sslsocketfactory;
        InetAddress inetaddress;
        SSLSocket sslsocket = null;
        try {
            sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            inetaddress = InetAddress.getByName("smtp.yandex.ru");
            sslsocket = (SSLSocket) sslsocketfactory.createSocket(inetaddress, 465);
            sslsocket.startHandshake();
        } catch (UnknownHostException ex) {
            try {
                Logger.getLogger(Sending.class.getName()).log(Level.SEVERE, null, ex);
                sslsocket.close();
            } catch (IOException ex1) {
                Logger.getLogger(Sending.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            Logger.getLogger(Sending.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        String username = Base64.getEncoder().encodeToString(("mohaboko31@yandex.com").getBytes());
        String password = Base64.getEncoder().encodeToString(("jerwufjkljpkatxf").getBytes());
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        PrintWriter printWriter = null;
        String response = "";
        try {
            isr = new InputStreamReader(sslsocket.getInputStream());
            osw = new OutputStreamWriter(sslsocket.getOutputStream(), "UTF-8");
            bufferedReader = new BufferedReader(isr);
            bufferedWriter = new BufferedWriter(osw);
            printWriter = new PrintWriter(bufferedWriter, false);
            //1
            response = Sending.receiveResponse(bufferedReader);
            //2
            Sending.sendCommand(printWriter, "HELO yandex.ru");
            response = Sending.receiveResponse(bufferedReader);
            //3
            Sending.sendCommand(printWriter, "AUTH LOGIN");
            response = Sending.receiveResponse(bufferedReader);
            //4
            Sending.sendCommand(printWriter, username);
            response = Sending.receiveResponse(bufferedReader);
            //5
            Sending.sendCommand(printWriter, password);
            response = Sending.receiveResponse(bufferedReader);
            //6
            Sending.sendCommand(printWriter, "MAIL FROM:" + "mohaboko31@yandex.com");
            response = Sending.receiveResponse(bufferedReader);
            //7
            Sending.sendCommand(printWriter, "RCPT TO:" + receiver);
            response = Sending.receiveResponse(bufferedReader);
            //8
            Sending.sendCommand(printWriter, "DATA");
            response = Sending.receiveResponse(bufferedReader);
            //9
            Sending.sendCommand(printWriter, "Date: " + new Date().toString());
            //10
            Sending.sendCommand(printWriter, "From: " + "mohaboko31@yandex.com");
            //11
            Sending.sendCommand(printWriter, "To: " + receiver);
            //12
            Sending.sendCommand(printWriter, "Subject: " + "Feedback");
            //13
            Sending.sendCommand(printWriter, "Content-Type: text/plain; charset=UTF-8");
            //14
            Sending.sendCommand(printWriter, "");
            //15
            Sending.sendCommand(printWriter, message);
            //16
            Sending.sendCommand(printWriter, "");
            Sending.sendCommand(printWriter, ".");
            response = Sending.receiveResponse(bufferedReader);
            //17
            Sending.sendCommand(printWriter, "QUIT");
            response = Sending.receiveResponse(bufferedReader);
        } catch (IOException ex) {
            Logger.getLogger(Sending.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(response);
        } finally {
            try {
                printWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
                isr.close();
                osw.close();
                sslsocket.close();
            } catch (IOException ex) {
                Logger.getLogger(Sending.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }
        }
    }

    protected static void sendCommand(PrintWriter printWriter, String command) {
        printWriter.println(command);
        printWriter.flush();
    }

    protected static String receiveResponse(BufferedReader bufferedReader) {
        String response = null;
        try {
            response = bufferedReader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Sending.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return response;
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
            Logger.getLogger(Sending.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IndieFlower;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(this.network, 215, 16, this);
    }

    public void t1Focus(FocusEvent evt) {
        if (this.t1.getText().equals("example@domain.com")) {
            this.t1.setBackground(Color.WHITE);
            this.t1.setText("");
        }
    }

    public void t1UnFocus(FocusEvent evt) {
        this.t1.setBackground(Color.DARK_GRAY);
        if (this.t1.getText().isEmpty()) {
            this.t1.setText("example@domain.com");
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

    public void setEmail(final String email) {
        this.email = email;
    }

    public JButton b1;
    public JLabel l1;
    public JLabel l2;
    public JLabel l3;
    public JTextField t1;
    public JPasswordField t2;
    public JTextArea t3;
    public JScrollPane jScrollPanel;
}

