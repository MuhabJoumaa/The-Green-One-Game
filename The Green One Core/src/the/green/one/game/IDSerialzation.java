package the.green.one.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IDSerialzation implements Serializable {

    protected final long GameID;
    protected String madeBY;
    protected String madeIn;
    protected String Languages;
    protected String versionNO;
    protected transient String requires;

    protected IDSerialzation(long GameID, String madeBy, String madeIn, String Languages, String versionNO, String requiers) {
        this.GameID = GameID;
        this.madeBY = madeBy;
        this.madeIn = madeIn;
        this.Languages = Languages;
        this.versionNO = versionNO;
        this.requires = requiers;
    }

    public static void doSer(Boolean[] data) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("sources/data.ser");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IDSerialzation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IDSerialzation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.close();
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(IDSerialzation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void doSerForDisplaySettings(Integer[] displayParameters) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("sources/display_settings.ser");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(displayParameters);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IDSerialzation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IDSerialzation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.close();
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(IDSerialzation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void doSerForAudioSettings(Float volumeFloatControlValue) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("sources/audio_settings.ser");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(volumeFloatControlValue);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IDSerialzation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IDSerialzation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.close();
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(IDSerialzation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static Boolean[] getSer() {
        File serFile = new File("sources/data.ser");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Boolean[] data = {true, true, true};
        if (serFile.exists()) {
            try {
                fis = new FileInputStream(serFile.getAbsolutePath());
                ois = new ObjectInputStream(fis);
                data = (Boolean[]) ois.readObject();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(IDSerialzation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(IDSerialzation.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fis.close();
                    ois.close();
                } catch (IOException ex) {
                    Logger.getLogger(IDSerialzation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return data;
    }

    public static Integer[] getSerForDisplaySettings() {
        File serFile = new File("sources/display_settings.ser");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Integer[] displayParameters = {0, 0, 0};
        if (serFile.exists()) {
            try {
                fis = new FileInputStream(serFile.getAbsolutePath());
                ois = new ObjectInputStream(fis);
                displayParameters = (Integer[]) ois.readObject();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(IDSerialzation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(IDSerialzation.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fis.close();
                    ois.close();
                } catch (IOException ex) {
                    Logger.getLogger(IDSerialzation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return displayParameters;
    }

    public static Float getSerForAudioSettings() {
        File serFile = new File("sources/audio_settings.ser");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Float volumeFloatControlValue = 0f;
        if (serFile.exists()) {
            try {
                fis = new FileInputStream(serFile.getAbsolutePath());
                ois = new ObjectInputStream(fis);
                volumeFloatControlValue = (Float) ois.readObject();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(IDSerialzation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(IDSerialzation.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fis.close();
                    ois.close();
                } catch (IOException ex) {
                    Logger.getLogger(IDSerialzation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return volumeFloatControlValue;
    }
}
