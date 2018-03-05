package main;

import main.shape.Point;
import main.shape.Triangle;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static main.PaintUtilsKt.lineVU;

/**
 * Created by Ilya on 3/2/2018.
 */
public class Main {
    static int width = 800;
    static int height = 800;
   static BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    public static void main(String[] args) {
        for (int i = 0; i < image.getHeight() - 1; i++) {
            for (int j = 0; j < image.getWidth() - 1; j++) {
                image.setRGB(j,i , Color.BLACK.getRGB());
            }
        }
        plotTriangle();
        ImageUtils.saveImage(image);
    }

    private static void plotTriangle(){
        new Triangle<Point>(new Point(500,400),new Point(600,700),new Point(300,200)).plot(image,Color.RED);
    }


    private static void drawStar(){
        for (int i = 1; i < 18; i++){
        int x = (int) (width/2 + 200*cos(i*2*3.14/17));
        int y = (int) (height/2 + 200*sin(i*2*3.14/17));
        lineVU(width / 2, height / 2, x, y, image, Color.GREEN);
    }
    }
}
