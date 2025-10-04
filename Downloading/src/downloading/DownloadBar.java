package downloading;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class DownloadBar extends JFrame {
    
    File iconFile = new File("pics/icon.png");
    ImageIcon icon = new ImageIcon(iconFile.getAbsolutePath());
    
    public DownloadBar(String info, int FileSize) {
        this.infoLabel = new JLabel(info);
        this.downloadBar = new JProgressBar(0, FileSize);
        this.downloadBar.setStringPainted(true);
        this.downloadBar.setForeground(Color.GREEN);
        this.downloadBar.setBorder(BorderFactory.createLineBorder(new Color(245, 87, 66), 2));
        this.infoLabel.setForeground(Color.WHITE);
        this.panel = new JPanel();
        this.infoLabel.setBounds(120, 1, 120, 100);
        this.downloadBar.setBounds(10, 100, 270, 30);
        this.panel.add(this.infoLabel);
        this.panel.add(this.downloadBar);
        this.panel.setBackground(Color.BLACK);
        this.setAlwaysOnTop(true);
        this.setTitle("Downloader");
        this.setBackground(Color.WHITE);
        this.setSize(280, 150);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocation(550, 500);
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev) {
                ClosingOperation.closeBackendProgram();
            }
        });
        this.getContentPane().add(this.panel);
        this.setLocationRelativeTo(null);
    }
    
    protected JLabel infoLabel;
    protected JPanel panel;
    protected JProgressBar downloadBar;
}
