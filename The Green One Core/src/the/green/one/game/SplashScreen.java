package the.green.one.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SplashScreen extends JFrame {

    public BufferedImage blk;
    public double strokeRect = 6.0D;
    public Font IndieFlower;
    File ic = new File("pics/icon.png");
    public final String path = ic.getAbsolutePath();
    ImageIcon icon = new ImageIcon(path);
    JLabel present;
    int delay = 1000;
    int per = 1000;
    Timer tm = new Timer();
    int counter = 11;
    String text1 = "MUHAB JOUMAA PROGRAMMER";
    String text2 = "PRESENTS";
    String load = "Loading...";
    Timer l2 = new Timer();
    File b = new File("pics/black.png");
    File black = new File(b.getAbsolutePath());
    Container container;
    double IncreaseOfLoading = 1;
    Color ColorOfBorder = new Color(191, 196, 174);
    int LoadingNumber = 0;

    public SplashScreen() {
        super("The Green One");
        this.setAlwaysOnTop(true);
        container = getContentPane();
        container.setLayout(new FlowLayout());
        setBackground(Color.black);
        setSize(400, 400);
        setResizable(false);
        setVisible(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocation(450, 100);
        this.setIconImage(icon.getImage());
        present = new JLabel("Muhab Joumaa Programmer");
        present.setBounds(190, 200, 400, 400);
        present.setForeground(Color.white);
        present.setText("The Green One Game");
        container.add(present);
        try {
            blk = ImageIO.read(black);
        } catch (IOException e) {
            System.out.print("Error try agian");
        }
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev) {
                ClosingOperation.closeBackendProgram();
            }
        });
        this.setLocationRelativeTo(null);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.WHITE);
        g.setFont(this.indieFlower());
        g.drawString(text1 + "\n", 60, 200);
        g.drawString(text2 + "\n", 140, 230);
        g.drawString(load + "\n", 160, 280);
        g.drawImage(blk, 0, 0, this);
        g.setColor(Color.GREEN);
        g.fillRect(160, 300, (int) IncreaseOfLoading, 20);
        g2.setStroke(new BasicStroke((float) this.strokeRect));
        g2.setColor(ColorOfBorder);
        g2.drawRect(158, 298, 103, 23);
        g.drawString(LoadingNumber + "%", 270, 317);
    }

    public Font indieFlower() {
        File font = new File("sources/IndieFlower-Regular.ttf");
        File FontFile = new File(font.getAbsolutePath());
        try {
            this.IndieFlower = Font.createFont(Font.TRUETYPE_FONT, FontFile).deriveFont(25f);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            env.registerFont(Font.createFont(Font.TRUETYPE_FONT, FontFile));
            return IndieFlower;
        } catch (FontFormatException ex) {
            Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IndieFlower;
    }
}
