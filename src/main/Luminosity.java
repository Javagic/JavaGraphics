package main;

import main.shape.Point3D;
import main.shape.Triangle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Luminosity {
    public static void process (BufferedImage image, List<Triangle<Point3D>> triangles){
        for (Triangle<Point3D> triangle : triangles) {
            Point3D n = triangle.normal(triangle.getA(), triangle.getB(), triangle.getC());
            Point3D light = new Point3D(0,0,-1);
            float intensity = BackFaceCulling.multiple(n, light) / (n.length() * light.length());
            System.out.println(intensity);
            if(intensity > 0)
                BackFaceCulling.backFaceCulling(triangle, image, new Color((int)(intensity*255), (int)(intensity*255), (int)(intensity*255)));
        }
    }
}
