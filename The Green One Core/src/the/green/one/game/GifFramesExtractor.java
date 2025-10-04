package the.green.one.game;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GifFramesExtractor {

    public static BufferedImage[] extractFrames(final File gifFile) throws IOException {
        List<BufferedImage> frames;
        try (ImageInputStream iis = ImageIO.createImageInputStream(gifFile)) {
            ImageReader imageReader = ImageIO.getImageReadersByFormatName("gif").next();
            imageReader.setInput(iis, false);
            frames = new ArrayList<>();
            int numFrames = imageReader.getNumImages(true);
            for (int i = 0; i < numFrames; i++) {
                BufferedImage frame = imageReader.read(i);
                frames.add(frame);
            }
            imageReader.dispose();
        }
        BufferedImage[] framesArray = new BufferedImage[frames.size()];
        framesArray = frames.toArray(framesArray);
        return framesArray;
    }
}
