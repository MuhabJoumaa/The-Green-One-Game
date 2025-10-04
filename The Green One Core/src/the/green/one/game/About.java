package the.green.one.game;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.UIManager.LookAndFeelInfo;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.swing.UIManager.getInstalledLookAndFeels;
import static javax.swing.UIManager.setLookAndFeel;

public class About extends JFrame {

    public File AboutPage = new File("sources/about.html");
    public JEditorPane about;
    File ico = new File("pics/icon.png");
    public ImageIcon icon = new ImageIcon(ico.getAbsolutePath());
    File u = new File("sources/about.html");
    private JScrollPane jScrollPane1;

    public About() {
        super("About The Game");
        this.initComponents();
        this.setAlwaysOnTop(true);
        this.setVisible(false);
        this.setResizable(false);
        this.setSize(450, 400);
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.m();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        about = new JTextPane();
        jScrollPane1.setViewportView(about);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                                .addContainerGap())
        );
        pack();
    }

    private void m() {
        try {
            URL abt;
            abt = new URL("file:///" + this.AboutPage.getAbsolutePath());
            about.setPage(abt);
            about.setContentType("text/html");
            about.setEditable(false);
        } catch (MalformedURLException ex) {
            Logger.getLogger(About.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(About.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            for (LookAndFeelInfo info : getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(About.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
