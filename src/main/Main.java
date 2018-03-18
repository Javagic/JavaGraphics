package main;

import main.rendering.Luminosity;
import main.shape.Point;
import main.shape.Triangle;
import main.utils.ImageUtils;
import main.utils.LineVU;
import main.utils.Parser;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by Ilya on 3/2/2018.
 */
public class Main {
    static int width = 1000;
    static int height = 1000;
    static BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    static Color[] colors = { new Color(40, 40, 40),
            new Color(80, 80, 80),
            new Color(120, 120, 120),
            new Color(160, 160, 160),
            new Color(200, 200, 200),
            new Color(240, 240, 240)};

    public static void main(String[] args) {
        Parser.readFile("res/african_head.obj", height);


        List<Triangle> mList = Parser.getTriangle();
        System.out.println(mList.size());


        for (int i = 0; i < image.getHeight() - 1; i++) {
            for (int j = 0; j < image.getWidth() - 1; j++) {
                image.setRGB(j, i, Color.BLACK.getRGB());
            }
        }
        Luminosity.process(image,mList);
        ImageUtils.saveImage(image);
    }

    private static void plotTriangle() {
        new Triangle(new Point(100, 1,0), new Point(100, 200,0), new Point(200, 100,0))
                .plot(new LineVU(image, Color.RED));
    }

    private static void plotStar() {
        for (int i = 1; i < 18; i++) {
            int x = (int) (width / 2 + 200 * cos(i * 2 * 3.14 / 17));
            int y = (int) (height / 2 + 200 * sin(i * 2 * 3.14 / 17));
            new LineVU(image, Color.GREEN).plotLine(width / 2, height / 2, x, y);
        }
    }

    private static void plotTriangle(Point p1, Point p2, Point p3) {
        new Triangle(p1, p2, p3)
                .plot(new LineVU(image, Color.RED));
    }


    private static void drawTriangle(Point p1, Point p2, Point p3) {
        new Triangle(p1, p2, p3)
                .draw(image, Color.RED);
    }

}
