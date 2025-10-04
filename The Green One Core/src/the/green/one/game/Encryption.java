package the.green.one.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Encryption {

    Connection con;
    ResultSet res;
    Statement stt;
    String getKey;
    int key;
    int decKey;
    private final String DB_URL = this.get_db_url();
    private final String DB_USN = this.get_db_usn();
    private final String DB_PWD = this.get_db_pwd();
    private static MessageDigest md5 = null;

    public Encryption() {
        try {
            Encryption.md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public final String encrypt(String text1, String AutoEmail) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            getKey = "SELECT * FROM account WHERE email = ? AND pwd = ?";
            PreparedStatement pstt = con.prepareStatement(getKey);
            pstt.setString(1, AutoEmail);
            pstt.setString(2, Encryption.doMD5(text1));
            res = pstt.executeQuery();
            if (res.next()) {
                key = res.getInt("thekey");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        char letter;
        int toknow;
        String newtext = "";
        if (key > 26) {
            key = key % 26;
        } else if (key < 0) {
            key = (key % 26) + 26;
        }
        for (int i = 0; i < text1.length(); i++) {
            letter = text1.charAt(i);
            if (Character.isLetter(letter)) {
                if (Character.isUpperCase(letter)) {
                    if ((letter + key) <= 'Z') {
                        newtext += (char) (letter + key);
                    } else if ((letter + key) > 90) {
                        toknow = (int) ((letter + key) - 'Z');
                        letter = (char) ('A' + toknow);
                        newtext += (char) (letter);
                    }
                } else if (Character.isLowerCase(letter)) {
                    if ((letter + key) <= 'z') {
                        newtext += (char) (letter + key);
                    } else if ((letter + key) > 122) {
                        toknow = (int) ((letter + key) - 'z');
                        letter = (char) ('a' + toknow);
                        newtext += (char) (letter);
                    }
                }
            } else {
                newtext += letter;
            }
        }
        return newtext;
    }

    public String decrypt(String newtext, String AutoEmail) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            getKey = "SELECT * FROM account WHERE email = ?";
            PreparedStatement pstt = con.prepareStatement(getKey);
            pstt.setString(1, AutoEmail);
            res = pstt.executeQuery();
            if (res.next()) {
                decKey = res.getInt("thekey");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        char letter;
        int toknow;
        String text = "";
        if (decKey > 26) {
            decKey = decKey % 26;
        } else if (decKey < 0) {
            decKey = (decKey % 26) + 26;
        }
        for (int i = 0; i < newtext.length(); i++) {
            letter = newtext.charAt(i);
            if (Character.isLetter(letter)) {
                if (Character.isUpperCase(letter)) {
                    if ((letter - decKey) >= 'A') {
                        text += (char) (letter - decKey);
                    } else if ((letter - decKey) < 65) {
                        toknow = (int) ('A' - (letter - decKey));
                        letter = (char) ('Z' - toknow);
                        text += (char) (letter);
                    }
                } else if (Character.isLowerCase(letter)) {
                    if ((letter - decKey) >= 'a') {
                        text += (char) (letter - decKey);
                    } else if ((letter - decKey) < 97) {
                        toknow = (int) ('a' - (letter - decKey));
                        letter = (char) ('z' - toknow);
                        text += (char) (letter);
                    }
                }
            } else {
                text += letter;
            }
        }
        return text;
    }

    public final String encrypt(String text1) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USN, DB_PWD);
            stt = con.createStatement();
            getKey = "SELECT * FROM account WHERE pwd = ?";
            PreparedStatement pstt = con.prepareStatement(getKey);
            pstt.setString(1, Encryption.doMD5(text1));
            res = pstt.executeQuery();
            if (res.next()) {
                key = res.getInt("thekey");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                stt.close();
                res.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        char letter;
        int toknow;
        String newtext = "";
        if (key > 26) {
            key = key % 26;
        } else if (key < 0) {
            key = (key % 26) + 26;
        }
        for (int i = 0; i < text1.length(); i++) {
            letter = text1.charAt(i);
            if (Character.isLetter(letter)) {
                if (Character.isUpperCase(letter)) {
                    if ((letter + key) <= 'Z') {
                        newtext += (char) (letter + key);
                    } else if ((letter + key) > 90) {
                        toknow = (int) ((letter + key) - 'Z');
                        letter = (char) ('A' + toknow);
                        newtext += (char) (letter);
                    }
                } else if (Character.isLowerCase(letter)) {
                    if ((letter + key) <= 'z') {
                        newtext += (char) (letter + key);
                    } else if ((letter + key) > 122) {
                        toknow = (int) ((letter + key) - 'z');
                        letter = (char) ('a' + toknow);
                        newtext += (char) (letter);
                    }
                }
            } else {
                newtext += letter;
            }
        }
        return newtext;
    }
    
    public static final String doMD5(String password) {
        byte[] passwordBytes = password.getBytes();
        Encryption.md5.update(passwordBytes);
        byte[] digest = Encryption.md5.digest();
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            hexString.append(Integer.toHexString(0xFF & digest[i]));
        }
        return hexString.toString();
    }

    public final String get_db_url() {
        String url = "";
        try {
            URL get_db_url = new URL("https://the-green-one-game.s3.us-west-004.backblazeb2.com/url.html");
            BufferedReader br;
            try (InputStreamReader isr = new InputStreamReader(get_db_url.openStream())) {
                br = new BufferedReader(isr);
                url += br.readLine().trim();
            }
            br.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error With DataBase Connection");
        } catch (IOException ex) {
            Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error With DataBase Connection");
        }
        return url;
    }

    public final String get_db_usn() {
        String usn = "";
        try {
            URL get_db_usn = new URL("https://the-green-one-game.s3.us-west-004.backblazeb2.com/usn.html");
            BufferedReader br;
            try (InputStreamReader isr = new InputStreamReader(get_db_usn.openStream())) {
                br = new BufferedReader(isr);
                usn += br.readLine().trim();
            }
            br.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error With DataBase Connection");
        } catch (IOException ex) {
            Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error With DataBase Connection");
        }
        return usn;
    }

    public final String get_db_pwd() {
        String pwd = "";
        try {
            URL get_db_pwd = new URL("https://the-green-one-game.s3.us-west-004.backblazeb2.com/pwd.html");
            BufferedReader br;
            try (InputStreamReader isr = new InputStreamReader(get_db_pwd.openStream())) {
                br = new BufferedReader(isr);
                pwd += br.readLine().trim();
            }
            br.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error With DataBase Connection");
        } catch (IOException ex) {
            Logger.getLogger(TheGreenOneGame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error With DataBase Connection");
        }
        return pwd;
    }
}
