package the.green.one.game;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import static javax.swing.UIManager.getInstalledLookAndFeels;
import static javax.swing.UIManager.setLookAndFeel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class News extends JFrame {

    File ico = new File("pics/icon.png");
    public ImageIcon icon = new ImageIcon(ico.getAbsolutePath());

    public News() {
        super("THE GREEN ONE NETWORK");
        this.initComponents();
        this.setAlwaysOnTop(true);
        this.setVisible(false);
        this.setResizable(false);
        this.setSize(450, 400);
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.openNews();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        News = new JTextPane();
        jScrollPane1.setViewportView(News);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                                .addContainerGap())
        );
        pack();
    }

    private void openNews() {
        try {
            URL NewsURL;
            NewsURL = new URL("https://the-green-one-game.s3.us-west-004.backblazeb2.com/News.html");
            News.setPage(NewsURL);
            News.setContentType("text/html");
            News.setEditable(false);
        } catch (MalformedURLException ex) {
            Logger.getLogger(About.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(About.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            for (UIManager.LookAndFeelInfo info : getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(About.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public JEditorPane News;
    private JScrollPane jScrollPane1;
}
