package end;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CreditsPanel extends JPanel implements ActionListener {
    
    private final JFrame jFrame;
    private final String credits;
    private final Timer timer;
    private int yPos;
    
    public CreditsPanel() {
        this.jFrame = new JFrame("The End Of The Green One Game");
        this.jFrame.setSize(800, 600);
        this.yPos = this.jFrame.getHeight();
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setLocationRelativeTo(null);
        this.jFrame.setIconImage(new ImageIcon(new File("pics/icon.png").getAbsolutePath()).getImage());
        this.jFrame.add(this);
        this.jFrame.setVisible(true);
        this.setBackground(Color.BLACK);
        this.credits = "========================================\n"
                + "              GAME CREDITS              \n"
                + "========================================\n"
                + "Game Title: The Green One\n"
                + "Developed entirely from scratch by: software engineer Muhab Joumaa\n"
                + "\n"
                + "Special Thanks:\n"
                + "- Online communities for inspiration\n"
                + "\n"
                + "Technologies Used:\n"
                + "Game Engine:\n"
                + "- Java\n"
                + "- Java AWT\n"
                + "- Java Swing\n"
                + "- JavaFX\n"
                + "- JOGL (Java OpenGL)\n"
                + "- C/C++\n"
                + "- OpenGL\n"
                + "- OpenGL GLUT\n"
                + "\n"
                + "Integrated Social Network:\n"
                + "- Java Sockets\n"
                + "- Java Mail API\n"
                + "- MySQL\n"
                + "- Neo4j AuraDB\n"
                + "- JSON\n"
                + "\n"
                + "Game's Website:\n"
                + "- HTML\n"
                + "- CSS\n"
                + "- JavaScript\n"
                + "- jQuery\n"
                + "- Bootstrap\n"
                + "- AJAX\n"
                + "\n"
                + "2D Paintings and Characters:\n"
                + "- CorelDraw\n"
                + "- Photoshop\n"
                + "- Canva\n"
                + "\n"
                + "3D Models:\n"
                + "- Blender\n"
                + "\n"
                + "For more information, visit:\n"
                + "https://thegreenone-game.github.io"
                + "\n\n\n\n\n\n\n\n\n\n\n\n"
                + "THANK YOU FOR PLAYING!";
        this.timer = new Timer(20, this);
        this.timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(this.indieFlower());
        g2d.setColor(Color.WHITE);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        int y = this.yPos;
        for (String line : this.credits.split("\n")) {
            int stringLength = (int) g2d.getFontMetrics().getStringBounds(line, g2d).getWidth(),
                    x = this.getWidth() / 2 - stringLength / 2;
            g2d.drawString(line, x, y);
            y += 25;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.yPos == -1110) {
            this.timer.stop();
        }
        this.yPos--;
        this.repaint();
    }
    
    private Font indieFlower() {
        Font indieFlower = null;
        File font = new File("sources/IndieFlower-Regular.ttf");
        File FontFile = new File(font.getAbsolutePath());
        try {
            indieFlower = Font.createFont(Font.TRUETYPE_FONT, FontFile).deriveFont(16f);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            env.registerFont(Font.createFont(Font.TRUETYPE_FONT, FontFile));
            return indieFlower;
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(CreditsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return indieFlower;
    }
}
