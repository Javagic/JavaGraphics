package main.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Ilya on 3/2/2018.
 */
public class ImageUtils {
    public static void saveImage(BufferedImage image) {
        try {
            // retrieve image
            File outputfile = new File("res/saved.png");
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
