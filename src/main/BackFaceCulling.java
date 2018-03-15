package main;

import main.shape.Point3D;
import main.shape.Triangle;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BackFaceCulling {

    public static void backFaceCulling(Triangle<Point3D> triangle, BufferedImage image, Color color) {

        Point3D n = triangle.normal(triangle.getA(), triangle.getB(), triangle.getC());
        Point3D v = new Point3D(0, 0, 1);

        if(multiple(n, v) / (n.length() * v.length()) < 0) {
            triangle.draw(image, color);
        }
    }

    static float multiple(Point3D A, Point3D B) {
        return (A.getX() * B.getX()) + (A.getY() * B.getY()) + (A.getZ() * B.getZ());
    }
}
