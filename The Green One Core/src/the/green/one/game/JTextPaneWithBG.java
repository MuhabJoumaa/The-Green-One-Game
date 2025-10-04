package the.green.one.game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JTextPane;

public class JTextPaneWithBG extends JTextPane {

    private Image backgroundImage;

    public JTextPaneWithBG() {
        super();
        this.setOpaque(false);
        try {
            this.backgroundImage = ImageIO.read(new URL("https://the-green-one-game.s3.us-west-004.backblazeb2.com/photo1687536686.jpg"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(JTextPaneWithBG.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JTextPaneWithBG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backgroundImage, 0, 0, getWidth(), getHeight(), this);
        super.paintComponent(g);
    }
}
