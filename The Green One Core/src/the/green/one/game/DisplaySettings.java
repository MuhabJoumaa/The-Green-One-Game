package the.green.one.game;

import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class DisplaySettings extends JFrame {

    private Font indieFlower;
    private File p = new File("THE GREEN ONE GAME.exe");
    private final String path = this.p.getAbsolutePath();
    private DisplayMode[] displayModes;

    public DisplaySettings() {
        this.getDisplayModes();
        this.initComponents();
        this.removeDuplicatesDisplayParameters(this.resolutionComboBox);
        this.removeDuplicatesDisplayParameters(this.frameRateComboBox);
        this.setAlwaysOnTop(true);
        this.setTitle("Display Settings");
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
        this.jLabel1 = new JLabel();
        this.resolutionComboBox = new JComboBox<>();
        this.jLabel2 = new JLabel();
        this.frameRateComboBox = new JComboBox<>();
        this.apply = new JButton();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.jLabel1.setText("Resolution:");
        this.resolutionComboBox.setModel(new DefaultComboBoxModel<>(this.setSupportedResolutions()));
        this.jLabel2.setText("Frame Rate:");
        this.frameRateComboBox.setModel(new DefaultComboBoxModel<>(this.setSupportedFrameRates()));
        this.apply.setText("Apply");
        this.apply.addActionListener((ActionEvent evt) -> {
            this.applyActionPerformed(evt);
        });
        this.jLabel1.setFont(this.indieFlower(12f));
        this.jLabel2.setFont(this.indieFlower(12f));
        this.resolutionComboBox.setFont(this.indieFlower(14f));
        this.frameRateComboBox.setFont(this.indieFlower(14f));
        this.apply.setFont(this.indieFlower(12f));
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(179, 179, 179)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(this.resolutionComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(this.jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(this.frameRateComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(this.apply, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(this.jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(179, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(this.jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(this.resolutionComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(this.jLabel2)
                                .addGap(30, 30, 30)
                                .addComponent(this.frameRateComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(this.apply)
                                .addContainerGap())
        );
        this.pack();
    }

    private void removeDuplicatesDisplayParameters(JComboBox<String> jComboBox) {
        Set<String> uniqueItems = new HashSet<>();
        for (int i = 0; i < jComboBox.getItemCount(); i++) {
            String item = (String) jComboBox.getItemAt(i);
            uniqueItems.add(item);
        }
        jComboBox.removeAllItems();
        uniqueItems.forEach((item) -> {
            jComboBox.addItem(item);
        });
    }

    private String[] setSupportedResolutions() {
        String[] supportedResolutions = new String[this.displayModes.length];
        for (int i = 0; i < supportedResolutions.length; i++) {
            supportedResolutions[i] = this.displayModes[i].getWidth() + "x" + this.displayModes[i].getHeight();
        }
        return supportedResolutions;
    }

    private String[] setSupportedFrameRates() {
        String[] supportedFrameRates = new String[this.displayModes.length];
        for (int i = 0; i < supportedFrameRates.length; i++) {
            supportedFrameRates[i] = String.valueOf(this.displayModes[i].getRefreshRate());
        }
        return supportedFrameRates;
    }

    private void getDisplayModes() {
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
        this.displayModes = graphicsDevice.getDisplayModes();
    }

    private void applyActionPerformed(ActionEvent evt) {
        String selectedResolution = (String) this.resolutionComboBox.getSelectedItem();
        int frameRate = Integer.parseInt((String) this.frameRateComboBox.getSelectedItem());
        String[] resolutionParts = selectedResolution.split("x");
        int width = Integer.parseInt(resolutionParts[0]);
        int height = Integer.parseInt(resolutionParts[1]);
        IDSerialzation.doSerForDisplaySettings(new Integer[]{width, height, frameRate});
        int know = JOptionPane.showConfirmDialog(this, "The Game's Display Settings Have Been Modified.\nDo You Want To Restart The Game Now?", "Alterations To The Display Settings", 0);
        if (know == 0) {
            try {
                Runtime.getRuntime().exec(this.path);
                ClosingOperation.closeBackendProgram();
            } catch (IOException ex) {
                System.out.print("Sorry");
                Logger.getLogger(DisplaySettings.class.getName()).log(Level.SEVERE, (String) null, ex);
            }
            System.exit(0);
        }
    }

    public Font indieFlower(float size) {
        File font = new File("sources/IndieFlower-Regular.ttf");
        File FontFile = new File(font.getAbsolutePath());
        try {
            this.indieFlower = Font.createFont(Font.TRUETYPE_FONT, FontFile).deriveFont(size);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            env.registerFont(Font.createFont(Font.TRUETYPE_FONT, FontFile));
            return this.indieFlower;
        } catch (FontFormatException ex) {
            Logger.getLogger(DisplaySettings.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(DisplaySettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.indieFlower;
    }

    private JButton apply;
    private JComboBox<String> frameRateComboBox;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JComboBox<String> resolutionComboBox;
}
