package the.green.one.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
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

public class BlockedAccounts extends JFrame {

    public Connection con;
    public Statement stt;
    public ResultSet res;
    public String SearchQuery;
    ArrayList<JLabel> icons;
    ArrayList<ImageIcon> usernames = new ArrayList<>();
    ArrayList<JButton> UnBlockButtons;
    ArrayList<Action> UnBlockActionArray;
    Border LabelsBorders;
    Border containerborder;
    public boolean Pressed = false;
    String info;
    String email;
    File json = new File("sources/info.json");
    final String filepath = json.getAbsolutePath();
    public String HalfEmail;
    public ArrayList<String> OrigEmail;
    public Font IndieFlower;
    public int ConnectionStatus;
    public String UnBlockQuery;
    public HashMap<String, ImageIcon> tableOfusers;
    public ArrayList<Integer> valsScroll = new ArrayList<>();
    private final String DB_URL = this.get_db_url();
    private final String DB_USN = this.get_db_usn();
    private final String DB_PWD = this.get_db_pwd();
    final File waitingstateicon = new File("pics/waitingStateIcon.gif");
    final ImageIcon WaitingStateIcon = new ImageIcon(waitingstateicon.getAbsolutePath());
    private final String notFoundStat = "Blocked Accounts List Is Empty", waitingStat = "Please, Wait A Moment...";

    public BlockedAccounts() {
        this.getMyUser();
        this.initComponents();
        this.setSize(580, 555);
        this.setAlwaysOnTop(true);
        this.setVisible(false);
        this.setResizable(false);
        this.setIconImage(new ImageIcon(new File("pics/icon.png").getAbsolutePath()).getImage());
        this.setBackground(Color.green);
        this.setTitle("The Green One Network");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        SearchLabel = new JLabel();
        SearchBox = new JTextField();
        SearchButton = new JButton();
        Title = new JLabel("List Of Blocked Accounts");
        Icons = new JLabel();
        notFoundOrWaiting = new JLabel(notFoundStat);
        unblock = new JButton();
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
        notFoundOrWaiting.setBackground(Color.BLACK);
        notFoundOrWaiting.setForeground(Color.BLACK);
        notFoundOrWaiting.setFont(this.indieFlower());
        notFoundOrWaiting.setBounds(180, 120, 200, 40);
        notFoundOrWaiting.setVisible(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.valsScroll.add(0);
        scroll.addAdjustmentListener((AdjustmentEvent ae) -> {
            scrollActionPerformed(ae);
        });
        container.addMouseWheelListener(e -> {
            MouseWheelingActionPerformed(e);
        });
        Title.setFont(this.indieFlower(16f));
        Title.setForeground(new Color(51, 255, 51));
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
                                        .addGroup(layout.createParallelGroup(Alignment.BASELINE)
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

    public SwingWorker getBlockedAccounts() {
        return new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                BlockedAccounts.this.scroll.setValue(0);
                if (BlockedAccounts.this.notFoundOrWaiting.isShowing() || BlockedAccounts.this.notFoundOrWaiting.isVisible()) {
                    BlockedAccounts.this.notFoundOrWaiting.setVisible(false);
                }
                BlockedAccounts.this.notFoundOrWaiting.setIcon(BlockedAccounts.this.WaitingStateIcon);
                BlockedAccounts.this.notFoundOrWaiting.setText(BlockedAccounts.this.waitingStat);
                BlockedAccounts.this.notFoundOrWaiting.setVisible(true);
                BlockedAccounts.this.container.add(BlockedAccounts.this.notFoundOrWaiting);
                BlockedAccounts.this.tableOfusers = new HashMap<>();
                if (!BlockedAccounts.this.tableOfusers.isEmpty()) {
                    BlockedAccounts.this.tableOfusers.clear();
                }
                BlockedAccounts.this.icons = new ArrayList<>();
                BlockedAccounts.this.OrigEmail = new ArrayList<>();
                BlockedAccounts.this.UnBlockButtons = new ArrayList<>();
                BlockedAccounts.this.UnBlockActionArray = new ArrayList<>();
                int i = 0;
                int b = 0;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    BlockedAccounts.this.con = DriverManager.getConnection(BlockedAccounts.this.DB_URL, BlockedAccounts.this.DB_USN, BlockedAccounts.this.DB_PWD);
                    BlockedAccounts.this.stt = BlockedAccounts.this.con.createStatement();
                    BlockedAccounts.this.SearchQuery = "SELECT * FROM account WHERE email IN (SELECT usertoblock FROM blocking WHERE user = ?)";
                    PreparedStatement pstt = BlockedAccounts.this.con.prepareStatement(BlockedAccounts.this.SearchQuery);
                    pstt.setString(1, BlockedAccounts.this.email);
                    BlockedAccounts.this.res = pstt.executeQuery();
                    while (BlockedAccounts.this.res.next()) {
                        i++;
                        BlockedAccounts.this.AddLabels(BlockedAccounts.this.res.getBytes("photo"), BlockedAccounts.this.res.getString("email"), i);
                        BlockedAccounts.this.AddButtons(b);
                        b++;
                    }
                    BlockedAccounts.this.container.revalidate();
                    BlockedAccounts.this.container.repaint();
                    if (BlockedAccounts.this.notFoundOrWaiting.isShowing() || BlockedAccounts.this.notFoundOrWaiting.isVisible()) {
                        BlockedAccounts.this.notFoundOrWaiting.setVisible(false);
                        BlockedAccounts.this.container.remove(BlockedAccounts.this.notFoundOrWaiting);
                    }
                    BlockedAccounts.this.CheckFounding();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(UsersSearch.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(BlockedAccounts.this, ex.getMessage());
                } finally {
                    try {
                        BlockedAccounts.this.stt.close();
                        BlockedAccounts.this.res.close();
                        BlockedAccounts.this.con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(BlockedAccounts.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                return null;
            }
        };
    }

    public void CheckFounding() {
        this.notFoundOrWaiting.setIcon(null);
        this.notFoundOrWaiting.setText(this.notFoundStat);
        if (this.icons.isEmpty()) {
            this.notFoundOrWaiting.setVisible(true);
            this.container.add(this.notFoundOrWaiting);
        }
    }

    public void AddLabels(byte[] IconBytes, String email, int i) {
        String[] UsernameSplit = email.split("@");
        String Username = UsernameSplit[0];
        this.HalfEmail = UsernameSplit[1];
        this.OrigEmail.add(HalfEmail);
        this.LabelsBorders = BorderFactory.createLineBorder(Color.BLUE);
        ImageIcon BeforeRe = new ImageIcon(IconBytes);
        Image MiddleRe = BeforeRe.getImage();
        Image AfterRe = MiddleRe.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon NewIcon = new ImageIcon(AfterRe);
        this.tableOfusers.put(Username, NewIcon);
        Icons = new JLabel(NewIcon);
        Icons.setText(Username);
        Icons.setFont(this.indieFlower());
        Icons.setBorder(LabelsBorders);
        Icons.setBackground(Color.RED);
        this.icons.add(Icons);
        this.GetLabels(i);
    }

    public void GetLabels(int i2) {
        int k = 0;
        for (int i = 0; i < i2; i++) {
            this.icons.get(i).setBounds(10, 110 + k, 259, 100);
            this.container.add(this.icons.get(i));
            k += 110;
        }
    }

    public void AddButtons(int b) {
        unblock = new JButton("UnBlock");
        unblock.setBackground(Color.BLUE);
        unblock.setForeground(Color.BLACK);
        this.UnBlockButtons.add(unblock);
        this.GetButtons(b);
    }

    public void GetButtons(int b2) {
        int k2 = 0;
        for (int i = 0; i <= b2; i++) {
            // un block buttons
            this.UnBlockButtons.get(i).setBounds(340, 140 + k2, 180, 40);
            this.UnBlockButtons.get(i).setText("UnBlock");
            this.UnBlockButtons.get(i).setFont(this.indieFlower());
            this.UnBlockButtons.get(i).setVisible(true);
            // un block actions
            UnBlockAction = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    UnBlockActionPerformed(evt);
                }
            };
            // un block add array
            this.UnBlockActionArray.add(UnBlockAction);
            this.UnBlockButtons.get(i).setAction(this.UnBlockActionArray.get(i));
            this.container.add(this.UnBlockButtons.get(i));
            k2 += 110;
        }
        this.UnBlockButtons.get(this.UnBlockButtons.size() - 1).setText("UnBlock");
    }

    public void UnBlockActionPerformed(ActionEvent evt) {
        Object know3 = evt.getSource();
        String Usn3 = this.icons.get(this.UnBlockButtons.indexOf(know3)).getText();
        String FullEmail3 = Usn3 + "@" + this.OrigEmail.get(this.UnBlockButtons.indexOf(know3));
        this.UnBlock(FullEmail3);
        if (this.unblock.isShowing()) {
            this.container.removeAll();
        }
        this.getBlockedAccounts().execute();
    }

    public void UnBlock(String userToUnblock) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            UnBlockQuery = "DELETE FROM blocking WHERE usertoblock = ?";
            PreparedStatement pstt = con.prepareStatement(UnBlockQuery);
            pstt.setString(1, userToUnblock);
            if (pstt.executeUpdate() == 1) {
                JOptionPane.showMessageDialog(this, "You Have Unblocked This Player Successfully" + "\n" + "He Can Send Messages To You Now");
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
                Logger.getLogger(BlockedAccounts.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void scrollActionPerformed(AdjustmentEvent ae) {
        int val = this.scroll.getValue();
        if (!this.icons.isEmpty()) {
            this.valsScroll.add(val);
            if (this.valsScroll.get(this.valsScroll.size() - 1) >= this.valsScroll.get(this.valsScroll.size() - 2)) {
                for (int i = 0; i < this.icons.size(); i++) {
                    this.icons.get(i).setBounds(10, this.icons.get(i).getBounds().y - this.scroll.getValue(), 259, 100);
                    this.UnBlockButtons.get(i).setBounds(340, this.UnBlockButtons.get(i).getBounds().y - this.scroll.getValue(), 180, 40);
                }
            } else {
                for (int i = 0; i < this.icons.size(); i++) {
                    this.icons.get(i).setBounds(10, this.icons.get(i).getBounds().y + this.scroll.getValue(), 259, 100);
                    this.UnBlockButtons.get(i).setBounds(340, this.UnBlockButtons.get(i).getBounds().y + this.scroll.getValue(), 180, 40);
                }
            }
        }
    }

    public void MouseWheelingActionPerformed(MouseWheelEvent mwe) {
        int val = mwe.getWheelRotation();
        if (!this.icons.isEmpty()) {
            int units = this.scroll.getMaximum() / this.icons.size();
            this.scroll.setValue(mwe.getWheelRotation() + units);
            this.valsScroll.add(val);
            if (this.valsScroll.get(this.valsScroll.size() - 1) >= this.valsScroll.get(this.valsScroll.size() - 2)) {
                for (int i = 0; i < this.icons.size(); i++) {
                    this.icons.get(i).setBounds(10, this.icons.get(i).getBounds().y - val, 259, 100);
                    this.UnBlockButtons.get(i).setBounds(340, this.UnBlockButtons.get(i).getBounds().y - val, 180, 40);
                }
            } else {
                for (int i = 0; i < this.icons.size(); i++) {
                    this.icons.get(i).setBounds(10, this.icons.get(i).getBounds().y + val, 259, 100);
                    this.UnBlockButtons.get(i).setBounds(340, this.UnBlockButtons.get(i).getBounds().y + val, 180, 40);
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
            Logger.getLogger(BlockedAccounts.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(BlockedAccounts.class.getName()).log(Level.SEVERE, null, ex);
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
    public JLabel notFoundOrWaiting;
    public JLabel Icons;
    public Action UnBlockAction;
    public JButton unblock;
    public JTextPane container;
    public JScrollBar scroll;
}
