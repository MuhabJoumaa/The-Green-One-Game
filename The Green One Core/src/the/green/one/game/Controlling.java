package the.green.one.game;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Controlling extends JFrame {

    private final File controllersImagePath = new File("pics/Controllers.jpeg");

    public Controlling() {
        super("Controllers");
        this.setAlwaysOnTop(true);
        this.setBackground(Color.WHITE);
        this.setSize(762, 358);
        this.setResizable(false);
        this.setVisible(false);
        this.setLocation(240, 110);
        this.setIconImage(new ImageIcon(new File("pics/icon.png").getAbsolutePath()).getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        ImageIcon controllersImage = new ImageIcon(this.controllersImagePath.getAbsolutePath());
        JLabel jLabel = new JLabel(controllersImage);
        jLabel.setSize(752, 348);
        this.getContentPane().add(jLabel);
    }
}
