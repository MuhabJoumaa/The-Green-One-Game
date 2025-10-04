package the.green.one.game;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;

public class Soeffects {

    protected Clip clip;

    public Soeffects() {
        this.clip = new Clip() {
            @Override
            public void open(AudioFormat af, byte[] bytes, int i, int i1) throws LineUnavailableException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void open(AudioInputStream stream) throws LineUnavailableException, IOException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getFrameLength() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public long getMicrosecondLength() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setFramePosition(int i) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setMicrosecondPosition(long l) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setLoopPoints(int i, int i1) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void loop(int i) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void drain() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void flush() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void start() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void stop() {
                try {
                    AudioSystem.getClip().stop();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(Soeffects.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public boolean isRunning() {
                try {
                    return AudioSystem.getClip().isRunning();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(Soeffects.class.getName()).log(Level.SEVERE, null, ex);
                }
                return false;
            }

            @Override
            public boolean isActive() {
                try {
                    return AudioSystem.getClip().isActive();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(Soeffects.class.getName()).log(Level.SEVERE, null, ex);
                }
                return false;
            }

            @Override
            public AudioFormat getFormat() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getBufferSize() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int available() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getFramePosition() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public long getLongFramePosition() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public long getMicrosecondPosition() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public float getLevel() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Line.Info getLineInfo() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void open() throws LineUnavailableException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void close() {
                try {
                    AudioSystem.getClip().close();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(Soeffects.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public boolean isOpen() {
                try {
                    return AudioSystem.getClip().isOpen();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(Soeffects.class.getName()).log(Level.SEVERE, null, ex);
                }
                return false;
            }

            @Override
            public Control[] getControls() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isControlSupported(Control.Type type) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Control getControl(Control.Type type) {
                try {
                    return AudioSystem.getClip().getControl(FloatControl.Type.MASTER_GAIN);
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(Soeffects.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }

            @Override
            public void addLineListener(LineListener ll) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void removeLineListener(LineListener ll) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
}
