package main;

import main.rendering.BackFaceCulling;
import main.rendering.Luminosity;
import main.rendering.ZBuffer;
import main.shape.Point;
import main.shape.Triangle;
import main.utils.ImageUtils;
import main.utils.LineVU;
import main.utils.OrtoManager;
import main.utils.Parser;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by Ilya on 3/2/2018.
 */
public class Main {
    static int width = 2000;
    static int height = 2000;
    static BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    public static void main(String[] args) {
        OrtoManager ortoManager = new OrtoManager(image, 1);
        Parser.readFile("src/main/resources/african_head.obj");

        List<Triangle> triangles = Parser.getTriangle();
        for (int i = 0; i < image.getHeight() - 1; i++) {
            for (int j = 0; j < image.getWidth() - 1; j++) {
                image.setRGB(j, i, Color.BLACK.getRGB());
            }
        }
        Luminosity.render(triangles, new ZBuffer(image));
        ImageUtils.saveImage(image);
    }

    private static void plotTriangle() {
        new Triangle(new Point(0.7, 0.3,0), new Point(0.3, 0.5,0), new Point(0.1, 0.5,0))
                .plot(new LineVU(image, Color.RED));
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
