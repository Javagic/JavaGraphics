package main;

import main.rendering.BackFaceCulling;
import main.rendering.Luminosity;
import main.rendering.ZBuffer;
import main.shape.Point;
import main.shape.Triangle;
import main.utils.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by Ilya on 3/2/2018.
 */
public class Main {
    static int width = 1024;
    static int height = 1024;
    static BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    static BufferedImage diffuseImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    public static void main(String[] args) {
        new OrtoManager(image, 1).init();
        Parser.readFile("src/main/resources/african_head.obj");

        List<Face> faces = Parser.getFaces();
        for (int i = 0; i < image.getHeight() - 1; i++) {
            for (int j = 0; j < image.getWidth() - 1; j++) {
                image.setRGB(j, i, Color.BLACK.getRGB());
            }
        }
        try {
            diffuseImage = ((BufferedImage) TGAReader.getImage("src/main/resources/african_head_diffuse .tga"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Luminosity.Companion.render(faces, new ZBuffer(image, diffuseImage));
        ImageUtils.saveImage(image);
    }

    private static void plotTriangle() {
        new Triangle(new Point(0.7, 0.3, 0), new Point(0.3, 0.5, 0), new Point(0.1, 0.5, 0))
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
