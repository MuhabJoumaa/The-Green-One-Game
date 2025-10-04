package the.green.one.game;

import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import static javax.swing.SwingUtilities.invokeLater;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import static org.neo4j.driver.Config.TrustStrategy.trustAllCertificates;

public class IncomingMessages extends JFrame {

    File searchicon = new File("pics/SearchIcon.png");
    File waitingstateicon = new File("pics/waitingStateIcon.gif");
    public Connection con;
    public Statement stt;
    public ResultSet res;
    public String SearchQuery;
    ImageIcon SearchIcon = new ImageIcon(searchicon.getAbsolutePath()), WaitingStateIcon = new ImageIcon(waitingstateicon.getAbsolutePath());
    ArrayList<JLabel> icons;
    ArrayList<ImageIcon> usernames = new ArrayList<>();
    ArrayList<JButton> ViewReceivedMessagesButtons;
    ArrayList<String> usns;
    ArrayList<Action> ActionsArray;
    ArrayList<JLabel> Status;
    ArrayList<JButton> BlockButtons;
    ArrayList<JButton> UnBlockButtons;
    ArrayList<Action> BlockActionArray;
    ArrayList<Action> UnBlockActionArray;
    Map<String, Image> statusArray;
    byte[] IconBytes;
    Border LabelsBorders;
    Border containerborder;
    File msgicon = new File("pics/MessageIocn.png");
    ImageIcon MessageIcon = new ImageIcon(msgicon.getAbsolutePath());
    public boolean Pressed = false;
    String info;
    String email;
    String getIPQuery;
    String ipAddress;
    String getEmail;
    File json = new File("sources/info.json");
    final String filepath = json.getAbsolutePath();
    public String HalfEmail;
    public ArrayList<String> OrigEmail;
    public String MsgQuery;
    public Font IndieFlower;
    File on = new File("pics/Online.png");
    File off = new File("pics/Offline.png");
    ImageIcon Online = new ImageIcon(on.getAbsolutePath());
    ImageIcon Offline = new ImageIcon(off.getAbsolutePath());
    private final ImageIcon verified = new ImageIcon(new File("pics/VerifiedIcon.png").getAbsolutePath());
    public int ConnectionStatus;
    public String BlockQuery;
    public String UnBlockQuery;
    public String CheckBlockQuery;
    public boolean blockCheck;
    private HashMap<String, String> tableOfusers;
    public ArrayList<Integer> valsScroll = new ArrayList<>();
    private final String DB_URL = this.get_db_url();
    private final String DB_USN = this.get_db_usn();
    private final String DB_PWD = this.get_db_pwd();
    private final Neo4jDatabaseManager neo4jdatabase = Neo4jDatabaseManager.getInstance();
    private String users, allMessages;
    private List<String> senders;
    private final Map<String, Integer> counters = new HashMap<>();
    private final Color statusColor = new Color(215, 3, 252);
    private final String notFoundStat = "No Received Messages", waitingStat = "Please, Wait A Moment...";
    private int i = 0, b = 0;

    public IncomingMessages() {
        this.initComponents();
        this.setAlwaysOnTop(true);
        this.setSize(580, 555);
        this.setVisible(false);
        this.setResizable(false);
        this.setIconImage(new ImageIcon(new File("pics/icon.png").getAbsolutePath()).getImage());
        this.setBackground(Color.green);
        this.setTitle("The Green One Network");
        this.getMyUser();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        SearchBox = new JTextField();
        SearchLabel = new JLabel();
        SearchButton = new JButton();
        Title = new JLabel();
        Icons = new JLabel();
        OnOrOff2 = new JLabel();
        ViewMessagesBtns = new JButton(this.MessageIcon);
        ViewMessagesBtns.setIcon(this.MessageIcon);
        block = new JButton();
        unblock = new JButton();
        notFoundOrWaiting = new JLabel();
        container = new JTextPane();
        containerborder = BorderFactory.createLineBorder(Color.BLACK);
        container.setBounds(2, 100, 551, 410);
        container.setBackground(Color.RED);
        container.setEditable(false);
        container.setBorder(this.containerborder);
        scroll = new JScrollBar();
        scroll.setBounds(552, 100, 12, 410);
        scroll.setOrientation(JScrollBar.VERTICAL);
        scroll.setMinimum(0);
        scroll.setMaximum(50);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        notFoundOrWaiting.setText(notFoundStat);
        notFoundOrWaiting.setBackground(Color.BLACK);
        notFoundOrWaiting.setForeground(Color.BLACK);
        notFoundOrWaiting.setFont(this.indieFlower());
        notFoundOrWaiting.setBounds(210, 120, 200, 40);
        notFoundOrWaiting.setVisible(false);
        this.valsScroll.add(0);
        scroll.addAdjustmentListener((AdjustmentEvent ae) -> {
            scrollActionPerformed(ae);
        });
        container.addMouseWheelListener(e -> {
            MouseWheelingActionPerformed(e);
        });
        Title.setFont(this.indieFlower(16f));
        Title.setForeground(new Color(0, 0, 0));
        Title.setText("Viewing My Received Messages");
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(SearchLabel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(SearchBox, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(191, 191, 191)
                                                .addComponent(Title)))
                                .addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addComponent(SearchButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(Title, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(SearchButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(SearchBox, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(SearchLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(445, Short.MAX_VALUE))
        );
        this.getContentPane().add(notFoundOrWaiting);
        this.getContentPane().add(container);
        this.getContentPane().add(scroll);
        this.SearchLabel.setVisible(false);
        this.SearchBox.setVisible(false);
        this.SearchButton.setVisible(false);
        pack();
    }

    private void getMyUser() {
        try (BufferedReader getJson = new BufferedReader(new FileReader(filepath))) {
            info = getJson.readLine();
            Object o2 = JSONValue.parse(info);
            JSONObject jsonObj2 = (JSONObject) o2;
            email = (String) jsonObj2.get("AutoEmail");
            getJson.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SwingWorker getReceivedMessages() {
        return new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                IncomingMessages.this.scroll.setValue(0);
                if (IncomingMessages.this.notFoundOrWaiting.isShowing() || IncomingMessages.this.notFoundOrWaiting.isVisible()) {
                    IncomingMessages.this.notFoundOrWaiting.setVisible(false);
                }
                IncomingMessages.this.tableOfusers = new HashMap<>();
                if (!IncomingMessages.this.tableOfusers.isEmpty()) {
                    IncomingMessages.this.tableOfusers.clear();
                }
                IncomingMessages.this.icons = new ArrayList<>();
                IncomingMessages.this.ViewReceivedMessagesButtons = new ArrayList<>();
                IncomingMessages.this.ActionsArray = new ArrayList<>();
                IncomingMessages.this.OrigEmail = new ArrayList<>();
                IncomingMessages.this.Status = new ArrayList<>();
                IncomingMessages.this.BlockButtons = new ArrayList<>();
                IncomingMessages.this.UnBlockButtons = new ArrayList<>();
                IncomingMessages.this.BlockActionArray = new ArrayList<>();
                IncomingMessages.this.UnBlockActionArray = new ArrayList<>();
                IncomingMessages.this.statusArray = new HashMap<>();
                IncomingMessages.this.notFoundOrWaiting.setBounds(200, 120, 200, 40);
                IncomingMessages.this.notFoundOrWaiting.setIcon(IncomingMessages.this.WaitingStateIcon);
                IncomingMessages.this.notFoundOrWaiting.setText(IncomingMessages.this.waitingStat);
                IncomingMessages.this.notFoundOrWaiting.setVisible(true);
                IncomingMessages.this.container.add(IncomingMessages.this.notFoundOrWaiting);
                IncomingMessages.this.senders = IncomingMessages.this.getSendersFromNeo4jDatabase(IncomingMessages.this.email.split("@")[0]);
                if (!IncomingMessages.this.senders.isEmpty()) {
                    try {
                        ArrayList<String> sendersListAfterChanging = new ArrayList<>();
                        IncomingMessages.this.senders.forEach((str) -> {
                            sendersListAfterChanging.add("'" + str + "%" + "'");
                        });
                        IncomingMessages.this.users = String.join(" OR email LIKE ", sendersListAfterChanging);
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        IncomingMessages.this.con = DriverManager.getConnection(IncomingMessages.this.DB_URL, IncomingMessages.this.DB_USN, IncomingMessages.this.DB_PWD);
                        IncomingMessages.this.stt = IncomingMessages.this.con.createStatement();
                        IncomingMessages.this.SearchQuery = "SELECT * FROM account WHERE email LIKE " + IncomingMessages.this.users;
                        PreparedStatement pstt = con.prepareStatement(SearchQuery);
                        IncomingMessages.this.res = pstt.executeQuery();
                        while (IncomingMessages.this.res.next()) {
                            IncomingMessages.this.i++;
                            IncomingMessages.this.getEmail = IncomingMessages.this.res.getString("email");
                            IncomingMessages.this.AddLabels(res.getInt("con"), res.getBytes("photo"), IncomingMessages.this.getEmail, i);
                            IncomingMessages.this.AddViewingButtons(IncomingMessages.this.b);
                            IncomingMessages.this.b++;
                        }
                        IncomingMessages.this.removeBlockButtonsFromCurrentUser();
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(IncomingMessages.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            IncomingMessages.this.stt.close();
                            IncomingMessages.this.res.close();
                            IncomingMessages.this.con.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(IncomingMessages.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    IncomingMessages.this.container.revalidate();
                    IncomingMessages.this.container.repaint();
                    if (IncomingMessages.this.notFoundOrWaiting.isShowing() || IncomingMessages.this.notFoundOrWaiting.isVisible()) {
                        IncomingMessages.this.notFoundOrWaiting.setVisible(false);
                        IncomingMessages.this.container.remove(IncomingMessages.this.notFoundOrWaiting);
                        IncomingMessages.this.container.revalidate();
                        IncomingMessages.this.container.repaint();
                    }
                } else {
                    IncomingMessages.this.CheckFounding();
                }
                return null;
            }
        };
    }

    private List<String> getSendersFromNeo4jDatabase(final String userOfEmail) {
        List<String> records = null;
        try {
            records = this.neo4jdatabase.getSenders(userOfEmail);
        } catch (Exception ex) {
            Logger.getLogger(IncomingMessages.class.getName()).log(Level.SEVERE, null, ex);
        }
        return records;
    }

    private int getCountersFromNeo4jDatabase(final String emailOfSender) {
        int count = 0;
        try {
            count = this.neo4jdatabase.getCounters(this.email.split("@")[0], emailOfSender);
        } catch (Exception ex) {
            Logger.getLogger(IncomingMessages.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    private String getMessagesContentsFromNeo4jDatabase(final String emailOfSender) {
        String content = "";
        try {
            content = this.neo4jdatabase.getMessages(this.email.split("@")[0], emailOfSender, this.tableOfusers);
        } catch (Exception ex) {
            Logger.getLogger(IncomingMessages.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
    }

    public void CheckFounding() {
        this.notFoundOrWaiting.setBounds(210, 120, 200, 40);
        this.notFoundOrWaiting.setIcon(null);
        this.notFoundOrWaiting.setText(this.notFoundStat);
        if (this.i == 0) {
            this.notFoundOrWaiting.setVisible(true);
            this.container.add(this.notFoundOrWaiting);
            this.container.revalidate();
            this.container.repaint();
        }
    }

    public void AddLabels(int status, byte[] IconBytes, String email, int i) {
        String[] UsernameSplit = email.split("@");
        String Username = UsernameSplit[0];
        this.HalfEmail = UsernameSplit[1];
        this.OrigEmail.add(HalfEmail);
        this.LabelsBorders = BorderFactory.createLineBorder(Color.BLUE);
        ImageIcon BeforeRe = new ImageIcon(IconBytes);
        Image MiddleRe = BeforeRe.getImage();
        Image AfterRe = MiddleRe.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon NewIcon = new ImageIcon(AfterRe);
        this.tableOfusers.put(Username, UsernameSplit[1]);
        boolean isAccountVerified = this.neo4jdatabase.getIsPlayerVerified(Username);
        Icons = new JLabel(NewIcon);
        Icons.setOpaque(true);
        OnOrOff2 = new JLabel();
        if (isAccountVerified) {
            OnOrOff2.setLayout(new BoxLayout(OnOrOff2, BoxLayout.X_AXIS));
        }
        OnOrOff2.setOpaque(false);
        OnOrOff2.setBounds(255, 107, 125, 30);
        Icons.setText(Username);
        Icons.setFont(this.indieFlower());
        try {
            if (this.checkPlayerStatus(Icons.getText().trim(), Icons)) {
                Icons.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent me) {
                        viewStatusActionPerformed(me);
                    }
                });
            }
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(this, ex);
        }
        this.icons.add(Icons);
        if (status == 1) {
            if (isAccountVerified) {
                JLabel l1 = new JLabel(this.verified), l2 = new JLabel(this.Online);
                l2.setText("Online");
                OnOrOff2.add(l1);
                OnOrOff2.add(Box.createRigidArea(new Dimension(20, 0)));
                OnOrOff2.add(l2);
            } else {
                OnOrOff2.setText("Online");
                OnOrOff2.setIcon(this.Online);
            }
        } else if (status == 0) {
            if (isAccountVerified) {
                JLabel l1 = new JLabel(this.verified), l2 = new JLabel(this.Offline);
                l2.setText("Offline");
                OnOrOff2.add(l1);
                OnOrOff2.add(Box.createRigidArea(new Dimension(20, 0)));
                OnOrOff2.add(l2);
            } else {
                OnOrOff2.setText("Offline");
                OnOrOff2.setIcon(this.Offline);
            }
        }
        Status.add(OnOrOff2);
        this.GetLabels(i);
    }

    public void GetLabels(int i2) {
        int k = 0;
        int s = 0;
        for (int i = 0; i < i2; i++) {
            this.icons.get(i).setBounds(10, 110 + k, 239, 100);
            this.Status.get(i).setBounds(255, 107 + s, 125, 30);
            this.container.add(this.icons.get(i));
            this.container.add(this.Status.get(i));
            k += 110;
            s += 110;
        }
    }

    public boolean checkPlayerStatus(String name, JLabel icon) {
        String statusBase64 = this.neo4jdatabase.getPlayerStatus(name);
        boolean b;
        if (statusBase64.equals("0")) {
            b = false;
            this.LabelsBorders = BorderFactory.createLineBorder(Color.BLACK, 3);
            icon.setBorder(LabelsBorders);
            icon.setBackground(Color.WHITE);
        } else {
            b = true;
            Image status;
            byte[] statusBytes = Base64.getDecoder().decode(statusBase64);
            this.LabelsBorders = BorderFactory.createLineBorder(Color.BLUE, 3);
            icon.setBorder(LabelsBorders);
            icon.setBackground(this.statusColor);
            status = new ImageIcon(statusBytes).getImage();
            this.statusArray.put(name, status);
        }
        return b;
    }

    public void viewStatusActionPerformed(MouseEvent me) {
        JLabel player = (JLabel) me.getSource();
        String name = player.getText().trim();
        try {
            if (!this.CheckingBlock(this.email, name + "@" + this.tableOfusers.get(name), true)) {
                JOptionPane.showMessageDialog(this, "", "Player's Status", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(this.statusArray.get(name).getScaledInstance(250, 250, Image.SCALE_DEFAULT)));
            } else {
                JOptionPane.showMessageDialog(this, "You Can Not View The Status Of This Player Photo.", "Forbidden", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }

    public void AddViewingButtons(int b) {
        String user = this.icons.get(0).getText().trim();
        int n = this.getCountersFromNeo4jDatabase(user);
        this.counters.put(user, n);
        ViewMessagesBtns = new JButton("View (" + n + ") Msg.");
        ViewMessagesBtns.setBackground(Color.PINK);
        ViewMessagesBtns.setForeground(Color.BLACK);
        block = new JButton("Block");
        block.setBackground(Color.BLUE);
        block.setForeground(Color.BLACK);
        unblock = new JButton("UnBlock");
        unblock.setBackground(Color.BLUE);
        unblock.setForeground(Color.BLACK);
        this.ViewReceivedMessagesButtons.add(ViewMessagesBtns);
        this.BlockButtons.add(block);
        this.UnBlockButtons.add(unblock);
        this.GetButtons(b);
    }

    public void GetButtons(int b2) {
        int k2 = 0;
        for (int i = 0; i <= b2; i++) {
            this.ViewReceivedMessagesButtons.get(i).setBounds(250, 140 + k2, 110, 40);
            this.ViewReceivedMessagesButtons.get(i).setIcon(this.MessageIcon);
            String user = this.icons.get(i).getText().trim();
            int n = this.getCountersFromNeo4jDatabase(user);
            this.counters.put(user, n);
            this.ViewReceivedMessagesButtons.get(i).setText("View (" + n + ") Msg.");
            this.ViewReceivedMessagesButtons.get(i).setFont(this.indieFlower());
            // block buttons
            this.BlockButtons.get(i).setBounds(380, 140 + k2, 60, 40);
            this.BlockButtons.get(i).setText("Block");
            this.BlockButtons.get(i).setFont(this.indieFlower());
            // un block buttons
            this.UnBlockButtons.get(i).setBounds(460, 140 + k2, 70, 40);
            this.UnBlockButtons.get(i).setText("UnBlock");
            this.UnBlockButtons.get(i).setFont(this.indieFlower());
            viewReceivedMessagesAction = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ViewReceivedMessagesActionPerformed(evt);
                }
            };
            // block actions
            BlockAction = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    BlockActionPerformed(evt);
                }
            };
            // un block actions
            UnBlockAction = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    UnBlockActionPerformed(evt);
                }
            };
            this.ActionsArray.add(viewReceivedMessagesAction);
            this.ViewReceivedMessagesButtons.get(i).setAction(this.ActionsArray.get(i));
            this.container.add(this.ViewReceivedMessagesButtons.get(i));
            // block add array
            this.BlockActionArray.add(BlockAction);
            this.BlockButtons.get(i).setAction(this.BlockActionArray.get(i));
            this.container.add(this.BlockButtons.get(i));
            // un block add array
            this.UnBlockActionArray.add(UnBlockAction);
            this.UnBlockButtons.get(i).setAction(this.UnBlockActionArray.get(i));
            this.container.add(this.UnBlockButtons.get(i));
            k2 += 110;
        }
        this.ViewReceivedMessagesButtons.get(this.ViewReceivedMessagesButtons.size() - 1).
                setText("View (" + this.getCountersFromNeo4jDatabase(this.icons.get(this.icons.size() - 1).getText().trim()) + ") Msg.");
        this.BlockButtons.get(this.BlockButtons.size() - 1).setText("Block");
        this.UnBlockButtons.get(this.UnBlockButtons.size() - 1).setText("UnBlock");
    }

    private void removeBlockButtonsFromCurrentUser() {
        for (int i = 0; i < this.icons.size(); i++) {
            if (this.icons.get(i).getText().equals(this.email.split("@")[0])) {
                this.container.remove(this.BlockButtons.get(i));
                this.container.remove(this.UnBlockButtons.get(i));
                break;
            }
        }
    }

    public void ViewReceivedMessagesActionPerformed(ActionEvent evt) {
        Object who = evt.getSource();
        this.runGettingMessagesBackgroundThread(who).execute();
    }

    private SwingWorker runGettingMessagesBackgroundThread(Object who) {
        return new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                new Thread(() -> {
                    try {
                        SwingUtilities.invokeAndWait(() -> {
                            IncomingMessages.this.container.removeAll();
                            IncomingMessages.this.notFoundOrWaiting.setText(IncomingMessages.this.waitingStat);
                            IncomingMessages.this.notFoundOrWaiting.setVisible(true);
                            IncomingMessages.this.container.add(IncomingMessages.this.notFoundOrWaiting);
                            IncomingMessages.this.container.revalidate();
                            IncomingMessages.this.container.repaint();
                        });
                    } catch (InterruptedException | InvocationTargetException ex) {
                        Logger.getLogger(IncomingMessages.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getMessage());
                    }
                }).start();
                String usn = IncomingMessages.this.icons.get(IncomingMessages.this.ViewReceivedMessagesButtons.indexOf(who)).getText();
                IncomingMessages.this.allMessages = IncomingMessages.this.getMessagesContentsFromNeo4jDatabase(usn);
                Chat chat = new Chat(usn, IncomingMessages.this.tableOfusers, IncomingMessages.this.neo4jdatabase.getMyMessagesIDsHashmap());
                chat.TextPanel.setText(IncomingMessages.this.allMessages);
                invokeLater(() -> {
                    chat.setVisible(true);
                });
                return null;
            }

            @Override
            protected void done() {
                invokeLater(() -> {
                    IncomingMessages.this.setVisible(false);
                    IncomingMessages.this.dispose();
                });
            }
        };
    }

    public void BlockActionPerformed(ActionEvent evt) {
        Object know2 = evt.getSource();
        String Usn2 = this.icons.get(this.BlockButtons.indexOf(know2)).getText();
        String FullEmail2 = Usn2 + "@" + this.OrigEmail.get(this.BlockButtons.indexOf(know2));
        this.Block(FullEmail2);
    }

    public void UnBlockActionPerformed(ActionEvent evt) {
        Object know3 = evt.getSource();
        String Usn3 = this.icons.get(this.UnBlockButtons.indexOf(know3)).getText();
        String FullEmail3 = Usn3 + "@" + this.OrigEmail.get(this.UnBlockButtons.indexOf(know3));
        this.UnBlock(FullEmail3);
    }

    public void Block(String userToblock) {
        try {
            this.CheckingBlock(email, userToblock, false);
            if (this.blockCheck) {
                JOptionPane.showMessageDialog(this, "You Have Already Blocked This Player", "Forbidden", JOptionPane.ERROR_MESSAGE);
            } else {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
                stt = con.createStatement();
                BlockQuery = "INSERT INTO blocking (user,usertoblock) VALUES ('" + email + "','" + userToblock + "')";
                PreparedStatement pstt = con.prepareStatement(BlockQuery);
                int know1 = pstt.executeUpdate();
                if (know1 == 1) {
                    this.Notifications("You Blocked This Player Successfully" + "\n" + "He Can Not Send Messages To You Anymore");
                    JOptionPane.showMessageDialog(this, "You Blocked This Player" + "\n" + "He Can Not Send Messages To You Anymore");
                }
                if (know1 == 0) {
                    this.Notifications("Error,Try again");
                    JOptionPane.showMessageDialog(this, "Error,Try again");
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(IncomingMessages.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void UnBlock(String userToUnblock) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            UnBlockQuery = "DELETE FROM blocking WHERE usertoblock='" + userToUnblock + "'";
            PreparedStatement pstt = con.prepareStatement(UnBlockQuery);
            int know1 = pstt.executeUpdate();
            if (know1 == 1) {
                this.Notifications("You Unblocked This Player Successfully" + "\n" + "He Can Send Messages To You Now");
                JOptionPane.showMessageDialog(this, "You Unblocked This Player Successfully" + "\n" + "He Can Send Messages To You Now");
            }
            if (know1 == 0) {
                this.Notifications("Error,Try again");
                JOptionPane.showMessageDialog(this, "Error,Try again");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(IncomingMessages.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void Notifications(String message) {
        try {
            Notification.sendNotification("the green one network", message, TrayIcon.MessageType.WARNING);
        } catch (AWTException | MalformedURLException ex) {
            Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean CheckingBlock(String name, String to, boolean b) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            if (!b) {
                CheckBlockQuery = "SELECT * FROM blocking WHERE user='" + name + "' AND usertoblock='" + to + "'";
            } else {
                CheckBlockQuery = "SELECT * FROM blocking WHERE user='" + to + "' AND usertoblock='" + name + "'";
            }
            PreparedStatement pstt = con.prepareStatement(CheckBlockQuery);
            res = pstt.executeQuery();
            this.blockCheck = res.next();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.blockCheck;
    }

    public void scrollActionPerformed(AdjustmentEvent ae) {
        int val = this.scroll.getValue();
        if (this.Status != null) {
            if (!this.Status.isEmpty()) {
                this.valsScroll.add(val);
                int k = (this.valsScroll.size() == 2) ? 1 : 2;
                if (this.valsScroll.get(this.valsScroll.size() - 1) >= this.valsScroll.get(this.valsScroll.size() - k)) {
                    for (int i = 0; i < this.icons.size(); i++) {
                        this.Status.get(i).setBounds(255, this.Status.get(i).getBounds().y - this.scroll.getValue(), 125, 30);
                        this.icons.get(i).setBounds(10, this.icons.get(i).getBounds().y - this.scroll.getValue(), 239, 100);
                        this.ViewReceivedMessagesButtons.get(i).setBounds(250, this.ViewReceivedMessagesButtons.get(i).getBounds().y - this.scroll.getValue(), 110, 40);
                        this.BlockButtons.get(i).setBounds(380, this.BlockButtons.get(i).getBounds().y - this.scroll.getValue(), 60, 40);
                        this.UnBlockButtons.get(i).setBounds(460, this.UnBlockButtons.get(i).getBounds().y - this.scroll.getValue(), 70, 40);
                    }
                } else {
                    for (int i = 0; i < this.icons.size(); i++) {
                        this.Status.get(i).setBounds(255, this.Status.get(i).getBounds().y + this.scroll.getValue(), 125, 30);
                        this.icons.get(i).setBounds(10, this.icons.get(i).getBounds().y + this.scroll.getValue(), 239, 100);
                        this.ViewReceivedMessagesButtons.get(i).setBounds(250, this.ViewReceivedMessagesButtons.get(i).getBounds().y + this.scroll.getValue(), 110, 40);
                        this.BlockButtons.get(i).setBounds(380, this.BlockButtons.get(i).getBounds().y + this.scroll.getValue(), 60, 40);
                        this.UnBlockButtons.get(i).setBounds(460, this.UnBlockButtons.get(i).getBounds().y + this.scroll.getValue(), 70, 40);
                    }
                }
            }
        }
    }

    public void MouseWheelingActionPerformed(MouseWheelEvent mwe) {
        int val = mwe.getWheelRotation();
        if (this.Status != null) {
            if (!this.Status.isEmpty()) {
                int units = this.scroll.getMaximum() / this.Status.size();
                this.scroll.setValue(mwe.getWheelRotation() + units);
                this.valsScroll.add(val);
                int k = (this.valsScroll.size() == 2) ? 1 : 2;
                if (this.valsScroll.get(this.valsScroll.size() - 1) >= this.valsScroll.get(this.valsScroll.size() - k)) {
                    for (int i = 0; i < this.icons.size(); i++) {
                        this.Status.get(i).setBounds(255, this.Status.get(i).getBounds().y - val, 125, 30);
                        this.icons.get(i).setBounds(10, this.icons.get(i).getBounds().y - val, 239, 100);
                        this.ViewReceivedMessagesButtons.get(i).setBounds(250, this.ViewReceivedMessagesButtons.get(i).getBounds().y - val, 110, 40);
                        this.BlockButtons.get(i).setBounds(380, this.BlockButtons.get(i).getBounds().y - val, 60, 40);
                        this.UnBlockButtons.get(i).setBounds(460, this.UnBlockButtons.get(i).getBounds().y - val, 70, 40);
                    }
                } else {
                    for (int i = 0; i < this.icons.size(); i++) {
                        this.Status.get(i).setBounds(255, this.Status.get(i).getBounds().y + val, 125, 30);
                        this.icons.get(i).setBounds(10, this.icons.get(i).getBounds().y + val, 239, 100);
                        this.ViewReceivedMessagesButtons.get(i).setBounds(250, this.ViewReceivedMessagesButtons.get(i).getBounds().y + val, 110, 40);
                        this.BlockButtons.get(i).setBounds(380, this.BlockButtons.get(i).getBounds().y + val, 60, 40);
                        this.UnBlockButtons.get(i).setBounds(460, this.UnBlockButtons.get(i).getBounds().y + val, 70, 40);
                    }
                }
            }
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
            Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
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
    public JLabel SearchLabel;
    public JTextField SearchBox;
    public JButton SearchButton;
    public JLabel Title;
    public JLabel Icons;
    public JButton ViewMessagesBtns;
    public Action viewReceivedMessagesAction;
    public Action BlockAction;
    public Action UnBlockAction;
    public JLabel OnOrOff2;
    public JButton block;
    public JButton unblock;
    public JLabel notFoundOrWaiting;
    public JTextPane container;
    public JScrollBar scroll;
}

