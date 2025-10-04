package downloading;

import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Downloading {

    public static BufferedReader takeVersion;
    public static InputStreamReader readVersion;
    public static URL newVersion;
    public static PrintWriter WriteVersion;
    public static BufferedWriter putVersion;
    private static final String DownloadLink = "https://the-green-one-game.s3.us-west-004.backblazeb2.com/THE%20GREEN%20ONE%20GAME.exe",
            versionNumber = "https://the-green-one-game.s3.us-west-004.backblazeb2.com/version.html", VersionFilePath = "sources/version.txt";
    public static URLConnection connection;
    public static FileOutputStream downloadFile;
    public static BufferedInputStream takeBytes;
    public static URL newfile;
    private static DownloadBar bar;
    private static int FileSize = 0;
    private static double downloadedBytes = 0.0, percentOfDownload = 0.0;
    private static DecimalFormat decimalformat;
    private static String info = null, percent = null, version = null;
    private static long startTime = 0L, elapsedTime = 0L, allTime = 0L, remainingTime = 0L, afterConvertRemainingTimeToMin = 0L;
    protected static final File old = new File("THE GREEN ONE GAME.exe");

    public static void main(String[] args) {
        try {
            newVersion = new URL(versionNumber);
            readVersion = new InputStreamReader(newVersion.openStream());
            takeVersion = new BufferedReader(readVersion);
            version = takeVersion.readLine().trim();
            readVersion.close();
            takeVersion.close();
            putVersion = new BufferedWriter(new FileWriter(VersionFilePath));
            WriteVersion = new PrintWriter(putVersion);
            WriteVersion.print(version);
            putVersion.close();
            WriteVersion.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Downloading.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Downloading.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (old.exists()) {
            old.delete();
        }
        try {
            newfile = new URL(DownloadLink);
            decimalformat = new DecimalFormat("##.##");
            downloadFile = new FileOutputStream(old.getAbsolutePath());
            connection = newfile.openConnection();
            FileSize = connection.getContentLength();
            info = "<html>" + Integer.toString(Math.round(FileSize / 1000000)) + "MB" + "</html>";
            percent = decimalformat.format(Math.abs(percentOfDownload)) + "%";
            bar = new DownloadBar(info, FileSize);
            bar.downloadBar.setString(percent);
            takeBytes = new BufferedInputStream(connection.getInputStream());
            Notifications("Download File Has Been Started");
            startTime = System.nanoTime();
            info = "<html>" + Integer.toString(Math.round(FileSize / 1000000)) + "MB" + "<br>" + "Time Remaining: " + takeRemainingTime() + "</html>";
            bar.infoLabel.setText(info);
            int onebyte;
            byte[] data = new byte[1024];
            while ((onebyte = takeBytes.read(data, 0, 1024)) != -1) {
                downloadFile.write(data, 0, onebyte);
                downloadedBytes += onebyte;
                percentOfDownload = (downloadedBytes * 100.0) / FileSize;
                percent = decimalformat.format(Math.abs(percentOfDownload)) + "%";
                info = "<html>" + Integer.toString(Math.round(FileSize / 1000000)) + "MB" + "<br>" + "Time Remaining: " + takeRemainingTime() + "</html>";
                bar.infoLabel.setText(info);
                bar.downloadBar.setString(percent);
                bar.downloadBar.setValue((int) downloadedBytes);
            }
            takeBytes.close();
            downloadFile.flush();
            downloadFile.close();
            bar.setVisible(false);
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(bar, "Update Has Been Downloaded Successfully");
            ClosingOperation.closeBackendProgram();
        } catch (MalformedURLException | FileNotFoundException ex) {
            Logger.getLogger(Downloading.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Downloading.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Notifications(String message) {
        try {
            Notification.sendNotification("The Green One Network", message, TrayIcon.MessageType.WARNING);
        } catch (AWTException | MalformedURLException ex) {
        }
    }

    private static String takeRemainingTime() {
        elapsedTime = System.nanoTime() - startTime;
        allTime = (long) (elapsedTime * FileSize / downloadedBytes);
        remainingTime = allTime - elapsedTime;
        afterConvertRemainingTimeToMin = TimeUnit.SECONDS.convert(remainingTime, TimeUnit.NANOSECONDS);
        return Long.toString(afterConvertRemainingTimeToMin) + "Sec";
    }
}
