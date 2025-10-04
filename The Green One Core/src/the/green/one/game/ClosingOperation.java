package the.green.one.game;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author muhab
 */
public class ClosingOperation {
    protected static void closeBackendProgram() {
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("taskkill /F /IM initBack.exe");
        } catch (IOException ex) {
            Logger.getLogger(ClosingOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }
}