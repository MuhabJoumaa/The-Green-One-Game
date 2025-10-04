package the.green.one.game;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class ImageCompressor {

    public static FileInputStream compressImage(final FileInputStream inputStream) throws IOException {
        BufferedImage image = ImageIO.read(inputStream);
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpeg");
        ImageWriter writer = writers.next();
        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.4f);
        byte[] compressedData;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (MemoryCacheImageOutputStream output = new MemoryCacheImageOutputStream(baos)) {
                writer.setOutput(output);
                IIOImage iioImage = new IIOImage(image, null, null);
                writer.write(null, iioImage, param);
                compressedData = baos.toByteArray();
            }
            writer.dispose();
        }
        File tempFile = File.createTempFile("compressed", ".jpg");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(compressedData);
        }
        return new FileInputStream(tempFile);
    }
}
