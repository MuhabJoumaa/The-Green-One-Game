package the.green.one.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.FloatControl;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;

public class AudioSettings extends JFrame {
    
    private Font IndieFlower;
    private FloatControl floatControl;
    
    public AudioSettings(FloatControl floatControl) {
        this.floatControl = floatControl;
        this.initComponents();
        this.setAlwaysOnTop(true);
        this.setTitle("Audio Settings");
        this.setSize(200, 310);
        this.setVisible(false);
        this.setResizable(false);
        this.setIconImage(new ImageIcon(new File("pics/icon.png").getAbsolutePath()).getImage());
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "error with your interface");
        }
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {
        this.volumeSlider = new JSlider(JSlider.VERTICAL, 0, 100, this.convertToVolume(this.floatControl.getValue()));
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.volumeSlider.setFont(indieFlower(16f));
        this.volumeSlider.setBackground(Color.WHITE);
        this.volumeSlider.setMajorTickSpacing(10);
        this.volumeSlider.setMinorTickSpacing(1);
        this.volumeSlider.setPaintTicks(true);
        this.volumeSlider.setPaintLabels(true);
        this.volumeSlider.setSnapToTicks(true);
        this.volumeSlider.setBounds(65, 30, 70, 250);
        Border volumeSliderLineBorder = BorderFactory.createLineBorder(Color.BLACK, 2),
                volumeSliderEmptyBorder = BorderFactory.createEmptyBorder(3, 40, 3, 40),
                volumeSliderCompoundBorder = BorderFactory.createCompoundBorder(volumeSliderLineBorder, volumeSliderEmptyBorder);
        this.volumeSlider.setBorder(volumeSliderCompoundBorder);
        this.volumeSlider.addChangeListener((ChangeEvent ce) -> {
            int volume = this.volumeSlider.getValue();
            float gain = this.convertToGain(volume);
            this.floatControl.setValue(gain);
            IDSerialzation.doSerForAudioSettings(gain);
        });
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        this.getContentPane().add(this.volumeSlider);
        this.pack();
    }
    
    public Font indieFlower(float size) {
        File font = new File("sources/IndieFlower-Regular.ttf");
        File FontFile = new File(font.getAbsolutePath());
        try {
            this.IndieFlower = Font.createFont(Font.TRUETYPE_FONT, FontFile).deriveFont(size);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            env.registerFont(Font.createFont(Font.TRUETYPE_FONT, FontFile));
            return this.IndieFlower;
        } catch (FontFormatException ex) {
            Logger.getLogger(AudioSettings.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(AudioSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.IndieFlower;
    }
    
    public void updateVolumeSliderValue() {
        int volume = this.convertToVolume(this.floatControl.getValue());
        this.volumeSlider.setValue(volume);
        this.repaint();
        this.revalidate();
    }
    
    private int convertToVolume(float gain) {
        return (int) (Math.pow(10.0, gain / 20.0) * 100.0);
    }
    
    private float convertToGain(int volume) {
        return (float) (Math.log10(volume / 100.0) * 20.0);
    }
    
    private JSlider volumeSlider;
}
