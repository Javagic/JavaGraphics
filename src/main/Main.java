package main;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static main.PaintUtilsKt.line;
import static main.PaintUtilsKt.lineVU;

/**
 * Created by Ilya on 3/2/2018.
 */
public class Main {
    static int width = 800;
    static int height = 800;
    public static void main(String[] args) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < image.getHeight() - 1; i++) {
            for (int j = 0; j < image.getWidth() - 1; j++) {
                image.setRGB(j,i , Color.BLACK.getRGB());
            }
        }
        Color color =  Color.GREEN;
        for (int i = 1; i < 18; i++){
            int x = (int) (width/2 + 200*cos(i*2*3.14/17));
            int y = (int) (height/2 + 200*sin(i*2*3.14/17));
            line(width / 2, height / 2, x, y, image, color);
        }
        ImageUtils.saveImage(image);
    }
}
