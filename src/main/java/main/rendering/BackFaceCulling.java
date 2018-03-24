package main.rendering;

import main.shape.Point;
import main.shape.Triangle;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.utils.VectorUtils.multiple;

public class BackFaceCulling {

    public static void backFaceCulling(Triangle triangle, BufferedImage image, Color color) {
        Point n = triangle.normal();
        Point v = new Point(0, 0, 1);
        if (multiple(n, v) / (n.length() * v.length()) < 0) {
            triangle.draw(image, color);
        }
    }


}
