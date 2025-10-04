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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
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
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import static org.neo4j.driver.Config.TrustStrategy.trustAllCertificates;

public class UsersSearch extends JFrame {

    File searchicon = new File("pics/SearchIcon.png");
    File waitingstateicon = new File("pics/waitingStateIcon.gif");
    public Connection con;
    public Statement stt;
    public ResultSet res;
    public String SearchQuery;
    ImageIcon SearchIcon = new ImageIcon(searchicon.getAbsolutePath()), WaitingStateIcon = new ImageIcon(waitingstateicon.getAbsolutePath());
    ArrayList<JLabel> icons;
    ArrayList<ImageIcon> usernames = new ArrayList<>();
    ArrayList<JButton> SendButtons;
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
    public String Message;
    File msgicon = new File("pics/MessageIocn.png");
    ImageIcon MessageIcon = new ImageIcon(msgicon.getAbsolutePath());
    public boolean Pressed = false;
    String info;
    String email;
    String getIPQuery;
    String ipAddress;
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
    public Date ToMessageDate;
    public SimpleDateFormat ToMessageDateFormat;
    public String ToMessageDateString;
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
    private final Color statusColor = new Color(215, 3, 252);
    private final String notFoundStat = "No Players/Users Was Found", waitingStat = "Please, Wait A Moment...";

    public UsersSearch() {
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
        SendBtns = new JButton(this.MessageIcon);
        SendBtns.setIcon(this.MessageIcon);
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
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        SearchLabel.setText("Find Players");
        SearchLabel.setForeground(Color.white);
        SearchLabel.setFont(this.indieFlower());
        notFoundOrWaiting.setText(notFoundStat);
        notFoundOrWaiting.setBackground(Color.BLACK);
        notFoundOrWaiting.setForeground(Color.BLACK);
        notFoundOrWaiting.setFont(this.indieFlower());
        notFoundOrWaiting.setBounds(188, 120, 200, 40);
        notFoundOrWaiting.setVisible(false);
        this.valsScroll.add(0);
        SearchButton.addActionListener((ActionEvent evt) -> {
            SearchButtonActionPerformed(evt);
        });
        scroll.addAdjustmentListener((AdjustmentEvent ae) -> {
            scrollActionPerformed(ae);
        });
        container.addMouseWheelListener(e -> {
            MouseWheelingActionPerformed(e);
        });
        SearchButton.setIcon(SearchIcon);
        SearchButton.setBackground(Color.RED);
        SearchLabel.setBackground(Color.LIGHT_GRAY);
        SearchBox.setBackground(Color.LIGHT_GRAY);
        SearchBox.setFont(this.indieFlower());
        SearchBox.setEnabled(true);
        SearchBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent evt) {
                SearchBox.setBackground(Color.WHITE);
            }

            @Override
            public void focusLost(FocusEvent evt) {
                if (!SearchBox.getText().equals("")) {
                    SearchButton.setEnabled(true);
                }
                if (SearchBox.getText().equals("")) {
                    SearchButton.setEnabled(false);
                }
            }
        });
        Title.setFont(this.indieFlower(16f));
        Title.setForeground(new Color(51, 255, 51));
        Title.setText("The Green One Network");
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

    private SwingWorker doSearchInBackground() {
        return new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                UsersSearch.this.scroll.setValue(0);
                UsersSearch.this.notFoundOrWaiting.setIcon(UsersSearch.this.WaitingStateIcon);
                UsersSearch.this.notFoundOrWaiting.setText(UsersSearch.this.waitingStat);
                UsersSearch.this.notFoundOrWaiting.setVisible(true);
                UsersSearch.this.container.add(UsersSearch.this.notFoundOrWaiting);
                UsersSearch.this.tableOfusers = new HashMap<>();
                if (!UsersSearch.this.tableOfusers.isEmpty()) {
                    UsersSearch.this.tableOfusers.clear();
                }
                if (UsersSearch.this.SendBtns.isShowing() || UsersSearch.this.Icons.isShowing()
                        || UsersSearch.this.OnOrOff2.isShowing() || UsersSearch.this.block.isShowing() || UsersSearch.this.unblock.isShowing()) {
                    for (int i = 0; i < UsersSearch.this.icons.size(); i++) {
                        UsersSearch.this.container.remove(UsersSearch.this.SendButtons.get(i));
                        UsersSearch.this.container.remove(UsersSearch.this.icons.get(i));
                        UsersSearch.this.container.remove(UsersSearch.this.Status.get(i));
                        UsersSearch.this.container.remove(UsersSearch.this.BlockButtons.get(i));
                        UsersSearch.this.container.remove(UsersSearch.this.UnBlockButtons.get(i));
                    }
                }
                String SearchLike = UsersSearch.this.SearchBox.getText();
                String getEmail;
                UsersSearch.this.icons = new ArrayList<>();
                UsersSearch.this.SendButtons = new ArrayList<>();
                UsersSearch.this.ActionsArray = new ArrayList<>();
                UsersSearch.this.OrigEmail = new ArrayList<>();
                UsersSearch.this.Status = new ArrayList<>();
                UsersSearch.this.BlockButtons = new ArrayList<>();
                UsersSearch.this.UnBlockButtons = new ArrayList<>();
                UsersSearch.this.BlockActionArray = new ArrayList<>();
                UsersSearch.this.UnBlockActionArray = new ArrayList<>();
                UsersSearch.this.statusArray = new HashMap<>();
                int i = 0;
                int b = 0;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    UsersSearch.this.con = DriverManager.getConnection(UsersSearch.this.DB_URL, UsersSearch.this.DB_USN, UsersSearch.this.DB_PWD);
                    UsersSearch.this.stt = UsersSearch.this.con.createStatement();
                    UsersSearch.this.SearchQuery = "SELECT * FROM account WHERE email LIKE ?";
                    PreparedStatement pstt = UsersSearch.this.con.prepareStatement(UsersSearch.this.SearchQuery);
                    pstt.setString(1, SearchLike + "%");
                    UsersSearch.this.res = pstt.executeQuery();
                    while (UsersSearch.this.res.next()) {
                        i++;
                        getEmail = UsersSearch.this.res.getString("email");
                        UsersSearch.this.AddLabels(UsersSearch.this.res.getInt("con"), UsersSearch.this.res.getBytes("photo"), getEmail, i);
                        UsersSearch.this.AddSendButtons(b);
                        b++;
                    }
                    UsersSearch.this.removeBlockButtonsFromCurrentUser();
                    UsersSearch.this.container.revalidate();
                    UsersSearch.this.container.repaint();
                    if (UsersSearch.this.notFoundOrWaiting.isShowing() || UsersSearch.this.notFoundOrWaiting.isVisible()) {
                        UsersSearch.this.notFoundOrWaiting.setVisible(false);
                        UsersSearch.this.container.remove(UsersSearch.this.notFoundOrWaiting);
                    }
                    UsersSearch.this.CheckFounding();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(ex.getMessage());
                } finally {
                    try {
                        UsersSearch.this.stt.close();
                        UsersSearch.this.res.close();
                        UsersSearch.this.con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                return null;
            }
        };
    }

    public void SearchButtonActionPerformed(ActionEvent evt) {
        this.doSearchInBackground().execute();
    }

    public void CheckFounding() {
        this.notFoundOrWaiting.setIcon(null);
        this.notFoundOrWaiting.setText(this.notFoundStat);
        if (this.icons.isEmpty()) {
            this.notFoundOrWaiting.setVisible(true);
            this.container.add(this.notFoundOrWaiting);
        }
    }

    public void AddLabels(int status, byte[] IconBytes, String email, int i) {
        String[] UsernameSplit = email.split("@");
        String Username = UsernameSplit[0];
        this.HalfEmail = UsernameSplit[1];
        this.OrigEmail.add(HalfEmail);
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
            if (this.icons.get(i).getText().equals("mohaboko31")) {
                String user = "mohaboko31";
                StringBuilder newOwner = new StringBuilder(user);
                String owner = user.concat("(Owner)");
                newOwner.replace(0, user.length(), "");
                newOwner.append(owner);
                icons.get(i).setText(newOwner.toString());
            }
            this.icons.get(i).setBounds(10, 110 + k, 239, 100);
            this.Status.get(i).setBounds(255, 107 + s, 125, 30);
            this.container.add(this.icons.get(i));
            this.container.add(this.Status.get(i));
            k += 110;
            s += 110;
        }
    }

    public boolean checkPlayerStatus(String name, JLabel icon) {
        if (name.contains("mohaboko31")) {
            name = "mohaboko31";
        }
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
        if (name.contains("mohaboko31")) {
            name = "mohaboko31";
        }
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

    public void AddSendButtons(int b) {
        SendBtns = new JButton("Send Message");
        SendBtns.setBackground(Color.PINK);
        SendBtns.setForeground(Color.BLACK);
        block = new JButton("Block");
        block.setBackground(Color.BLUE);
        block.setForeground(Color.BLACK);
        unblock = new JButton("UnBlock");
        unblock.setBackground(Color.BLUE);
        unblock.setForeground(Color.BLACK);
        this.SendButtons.add(SendBtns);
        this.BlockButtons.add(block);
        this.UnBlockButtons.add(unblock);
        this.GetButtons(b);
    }

    public void GetButtons(int b2) {
        int k2 = 0;
        for (int i = 0; i <= b2; i++) {
            this.SendButtons.get(i).setBounds(250, 140 + k2, 110, 40);
            this.SendButtons.get(i).setIcon(this.MessageIcon);
            this.SendButtons.get(i).setText("Send Message");
            this.SendButtons.get(i).setFont(this.indieFlower());
            // block buttons
            this.BlockButtons.get(i).setBounds(380, 140 + k2, 60, 40);
            this.BlockButtons.get(i).setText("Block");
            this.BlockButtons.get(i).setFont(this.indieFlower());
            // un block buttons
            this.UnBlockButtons.get(i).setBounds(460, 140 + k2, 70, 40);
            this.UnBlockButtons.get(i).setText("UnBlock");
            this.UnBlockButtons.get(i).setFont(this.indieFlower());
            sendAction = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    SendMessageActionPerformed(evt);
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
            this.ActionsArray.add(sendAction);
            this.SendButtons.get(i).setAction(this.ActionsArray.get(i));
            this.container.add(this.SendButtons.get(i));
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
        this.SendButtons.get(this.SendButtons.size() - 1).setText("Send Message");
        this.BlockButtons.get(this.BlockButtons.size() - 1).setText("Block");
        this.UnBlockButtons.get(this.UnBlockButtons.size() - 1).setText("UnBlock");
    }

    private void removeBlockButtonsFromCurrentUser() {
        for (int i = 0; i < this.icons.size(); i++) {
            String userOfEmail = this.icons.get(i).getText();
            if (userOfEmail.equals("mohaboko31(Owner)")) {
                userOfEmail = "mohaboko31";
            }
            if (userOfEmail.equals(this.email.split("@")[0])) {
                this.container.remove(this.BlockButtons.get(i));
                this.container.remove(this.UnBlockButtons.get(i));
                break;
            }
        }
    }

    public void SendMessageActionPerformed(ActionEvent evt) {
        this.runSendMessageBackgroundThread(evt).execute();
    }

    private SwingWorker runSendMessageBackgroundThread(ActionEvent evt) {
        return new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                UsersSearch.this.ToMessageDate = new Date();
                UsersSearch.this.ToMessageDateFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
                UsersSearch.this.ToMessageDateString = UsersSearch.this.ToMessageDateFormat.format(UsersSearch.this.ToMessageDate);
                Object know = evt.getSource();
                UsersSearch.this.Message = JOptionPane.showInputDialog(UsersSearch.this, "Type a Message", "");
                if (!UsersSearch.this.Message.isEmpty()) {
                    String Usn = UsersSearch.this.icons.get(UsersSearch.this.SendButtons.indexOf(know)).getText();
                    if (Usn.equals("mohaboko31(Owner)")) {
                        Usn = "mohaboko31";
                    }
                    String FullEmail = Usn + "@" + UsersSearch.this.OrigEmail.get(UsersSearch.this.SendButtons.indexOf(know));
                    UsersSearch.this.getIP(FullEmail, UsersSearch.this.Message);
                    UsersSearch.this.neo4jdatabase.createRelationship(UsersSearch.this.email, FullEmail, UsersSearch.this.ToMessageDateString, UsersSearch.this.Message);
                } else {
                    JOptionPane.showMessageDialog(UsersSearch.this, "Message Field Cannot Be Empty, Rewrite Message.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }
        };
    }

    public void BlockActionPerformed(ActionEvent evt) {
        Object know2 = evt.getSource();
        String Usn2 = this.icons.get(this.BlockButtons.indexOf(know2)).getText();
        if (Usn2.equals("mohaboko31(Owner)")) {
            Usn2 = "mohaboko31";
        }
        String FullEmail2 = Usn2 + "@" + this.OrigEmail.get(this.BlockButtons.indexOf(know2));
        this.Block(FullEmail2);
    }

    public void UnBlockActionPerformed(ActionEvent evt) {
        Object know3 = evt.getSource();
        String Usn3 = this.icons.get(this.UnBlockButtons.indexOf(know3)).getText();
        if (Usn3.equals("mohaboko31(Owner)")) {
            Usn3 = "mohaboko31";
        }
        String FullEmail3 = Usn3 + "@" + this.OrigEmail.get(this.UnBlockButtons.indexOf(know3));
        this.UnBlock(FullEmail3);
    }

    public void getIP(String fullemail, String Msg) {
        if (fullemail.contains("mohaboko31")) {
            fullemail = "mohaboko31@gmail.com";
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            getIPQuery = "SELECT * FROM account WHERE email = ?";
            PreparedStatement pstt = con.prepareStatement(getIPQuery);
            pstt.setString(1, fullemail);
            res = pstt.executeQuery();
            if (res.next()) {
                this.ipAddress = res.getString("ip");
                // checking the block first
                this.CheckingBlock(email, ipAddress, Msg, fullemail);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
                BlockQuery = "INSERT INTO blocking (user, usertoblock) VALUES (?, ?)";
                PreparedStatement pstt = con.prepareStatement(BlockQuery);
                pstt.setString(1, email);
                pstt.setString(2, userToblock);
                int know1 = pstt.executeUpdate();
                if (know1 == 1) {
                    this.Notifications("You Blocked This Player Successfully" + "\n" + "He Can not Send Messages To You Anymore");
                    JOptionPane.showMessageDialog(this, "You Blocked This Player" + "\n" + "He Can Not Send Messages To You Anymore");
                }
                if (know1 == 0) {
                    this.Notifications("Error,Try again");
                    JOptionPane.showMessageDialog(this, "Error,Try again");
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void UnBlock(String userToUnblock) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            UnBlockQuery = "DELETE FROM blocking WHERE usertoblock = ?";
            PreparedStatement pstt = con.prepareStatement(UnBlockQuery);
            pstt.setString(1, userToUnblock);
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
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void Notifications(String message) {
        try {
            Notification.sendNotification("The Green One Network", message, TrayIcon.MessageType.WARNING);
        } catch (AWTException | MalformedURLException ex) {
            Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CheckingBlock(String name, String ip, String Msg, String to) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            CheckBlockQuery = "SELECT * FROM blocking WHERE user = ? AND usertoblock = ?";
            PreparedStatement pstt = con.prepareStatement(CheckBlockQuery);
            pstt.setString(1, to);
            pstt.setString(2, name);
            res = pstt.executeQuery();
            if (res.next()) {
                this.blockCheck = true;
                JOptionPane.showMessageDialog(this, "You Can Not Send Messages To This Player", "Forbidden", JOptionPane.ERROR_MESSAGE);
            } else {
                this.blockCheck = false;
                this.Connect(name, ip, Msg, to);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean CheckingBlock(String name, String to, boolean b) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            CheckBlockQuery = "SELECT * FROM blocking WHERE user = ? AND usertoblock = ?";
            PreparedStatement pstt = con.prepareStatement(CheckBlockQuery);
            if (!b) {
                pstt.setString(1, name);
                pstt.setString(2, to);
            } else {
                pstt.setString(1, to);
                pstt.setString(2, name);
            }
            res = pstt.executeQuery();
            this.blockCheck = res.next();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
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

    public void Connect(String name, String ip, String Message, String to) {
        String from = "From:" + "| " + name + " -> " + Message + " ------" + this.ToMessageDateString;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            if (ip == null) {
                MsgQuery = "UPDATE account SET msg = ? WHERE email = ?";
            } else {
                MsgQuery = "UPDATE account SET msg = ? WHERE ip = ?";
            }
            PreparedStatement pstt = con.prepareStatement(MsgQuery);
            pstt.setString(1, from);
            if (ip == null) {
                pstt.setString(2, to);
            } else {
                pstt.setString(2, ip);
            }
            pstt.executeUpdate();
            try {
                Notification.sendNotification("The Green One Network", "Message Was Sent Successfully", TrayIcon.MessageType.INFO);
            } catch (AWTException | MalformedURLException ex) {
                Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this, "Message Was Sent Successfully");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex);
        } finally {
            try {
                stt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
        this.addToNeo4jDatabase(name, to, this.ToMessageDateString, Message);
    }

    private void addToNeo4jDatabase(final String me, final String to, final String date, final String content) {
        try {
            neo4jdatabase.createRelationship(me, to, date, content);
        } catch (Exception ex) {
            Logger.getLogger(Messages.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
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
                        this.SendButtons.get(i).setBounds(250, this.SendButtons.get(i).getBounds().y - this.scroll.getValue(), 110, 40);
                        this.BlockButtons.get(i).setBounds(380, this.BlockButtons.get(i).getBounds().y - this.scroll.getValue(), 60, 40);
                        this.UnBlockButtons.get(i).setBounds(460, this.UnBlockButtons.get(i).getBounds().y - this.scroll.getValue(), 70, 40);
                    }
                } else {
                    for (int i = 0; i < this.icons.size(); i++) {
                        this.Status.get(i).setBounds(255, this.Status.get(i).getBounds().y + this.scroll.getValue(), 125, 30);
                        this.icons.get(i).setBounds(10, this.icons.get(i).getBounds().y + this.scroll.getValue(), 239, 100);
                        this.SendButtons.get(i).setBounds(250, this.SendButtons.get(i).getBounds().y + this.scroll.getValue(), 110, 40);
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
                        this.SendButtons.get(i).setBounds(250, this.SendButtons.get(i).getBounds().y - val, 110, 40);
                        this.BlockButtons.get(i).setBounds(380, this.BlockButtons.get(i).getBounds().y - val, 60, 40);
                        this.UnBlockButtons.get(i).setBounds(460, this.UnBlockButtons.get(i).getBounds().y - val, 70, 40);
                    }
                } else {
                    for (int i = 0; i < this.icons.size(); i++) {
                        this.Status.get(i).setBounds(255, this.Status.get(i).getBounds().y + val, 125, 30);
                        this.icons.get(i).setBounds(10, this.icons.get(i).getBounds().y + val, 239, 100);
                        this.SendButtons.get(i).setBounds(250, this.SendButtons.get(i).getBounds().y + val, 110, 40);
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
        } catch (FontFormatException ex) {
            Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
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
        } catch (FontFormatException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IndieFlower;
    }

    public final String get_db_url() {
        String url = "";
        try {
            URL get_db_url = new URL("https://the-green-one-game.s3.us-west-004.backblazeb2.com/url.html");
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
            URL get_db_usn = new URL("https://the-green-one-game.s3.us-west-004.backblazeb2.com/usn.html");
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
            URL get_db_pwd = new URL("https://the-green-one-game.s3.us-west-004.backblazeb2.com/pwd.html");
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
    public JButton SendBtns;
    public Action sendAction;
    public Action BlockAction;
    public Action UnBlockAction;
    public JLabel OnOrOff2;
    public JButton block;
    public JButton unblock;
    public JLabel notFoundOrWaiting;
    public JTextPane container;
    public JScrollBar scroll;
}
