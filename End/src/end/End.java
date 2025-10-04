package end;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class End extends JFrame {

    private final static SoundEffects soundEffects = new SoundEffects();

    private static void soeffectsLoop(final File file) throws LineUnavailableException, IOException {
        try {
            soundEffects.clip = AudioSystem.getClip();
            soundEffects.clip.open(AudioSystem.getAudioInputStream(file));
            soundEffects.clip.start();
            soundEffects.clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            Logger.getLogger(End.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        try {
            soeffectsLoop(new File("sounds/EndingMusic.wav"));
            SwingUtilities.invokeLater(CreditsPanel::new);
        } catch (LineUnavailableException | IOException ex) {
            Logger.getLogger(End.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
