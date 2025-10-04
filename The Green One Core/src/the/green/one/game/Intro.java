package the.green.one.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Intro extends JFrame {

    Soeffects cl = new Soeffects();
    File vid = new File("vids/intro.gif");
    File sO = new File("sounds/sOv.wav");
    File sOv = new File(sO.getAbsolutePath());

    public Intro() {
        super("The Green One");
        this.setAlwaysOnTop(true);
        this.setBackground(Color.black);
        this.setSize(650, 400);
        this.setResizable(false);
        this.setVisible(false);
        this.setLocation(350, 70);
        this.setIconImage(new ImageIcon(new File("pics/icon.png").getAbsolutePath()).getImage());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(new BorderLayout());
        ImageIcon video = new ImageIcon(vid.getAbsolutePath());
        JLabel l = new JLabel(video);
        l.setSize(500, 500);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev) {
                ClosingOperation.closeBackendProgram();
            }
        });
        this.getContentPane().add(l);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public final void s() {
        this.soeffects(sOv);
    }

    public final void soeffects(File file) {
        try {
            this.cl.clip = AudioSystem.getClip();
            this.cl.clip.open(AudioSystem.getAudioInputStream(file));
            this.cl.clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException iOException) {
        }
    }
}
