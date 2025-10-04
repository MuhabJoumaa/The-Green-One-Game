package the.green.one.game;

import jiconfont.IconCode;
import jiconfont.IconFont;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class IconFontSwing {

    private static final List<IconFont> fonts = new ArrayList<>();

    public static synchronized void register(IconFont iconFont) {
        if (IconFontSwing.fonts.contains(iconFont) == false) {
            IconFontSwing.fonts.add(iconFont);
        }
    }

    public static synchronized final Font buildFont(String fontFamily) {
        try {
            for (IconFont iconFont : IconFontSwing.fonts) {
                if (iconFont.getFontFamily().equals(fontFamily)) {
                    return Font.createFont(Font.TRUETYPE_FONT, iconFont.getFontInputStream());
                }
            }
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(IconFontSwing.class.getName()).log(Level.SEVERE,
                    "Font load failure", ex);
        }
        Logger.getLogger(IconFontSwing.class.getName()).log(Level.SEVERE, "Font not found: {0}", fontFamily);
        throw new IllegalArgumentException("Font not found: " + fontFamily);
    }

    private IconFontSwing() {
    }

    public static Image buildImage(IconCode iconCode, float size) {
        return buildImage(iconCode, size, Color.BLACK);
    }

    public static Image buildImage(IconCode iconCode, float size, Color color) {
        Font font = buildFont(iconCode, size);
        String text = Character.toString(iconCode.getUnicode());
        return buildImage(text, font, color);
    }

    public static Icon buildIcon(IconCode iconCode, float size) {
        return buildIcon(iconCode, size, Color.BLACK);
    }

    public static Icon buildIcon(IconCode iconCode, float size, Color color) {
        return new ImageIcon(buildImage(iconCode, size, color));
    }

    private static BufferedImage buildImage(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setForeground(color);
        label.setFont(font);
        Dimension dim = label.getPreferredSize();
        int width = dim.width + 1;
        int height = dim.height + 1;
        label.setSize(width, height);
        BufferedImage bufImage
                = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufImage.createGraphics();
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(
                RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        label.print(g2d);
        g2d.dispose();
        return bufImage;
    }

    private static Font buildFont(IconCode iconCode, float size) {
        Font font = IconFontSwing.buildFont(iconCode.getFontFamily());
        return font.deriveFont(size);
    }

}
