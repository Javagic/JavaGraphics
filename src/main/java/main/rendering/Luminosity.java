package main.rendering;

import main.shape.Point;
import main.shape.Triangle;

import java.awt.*;
import java.util.List;
import static main.utils.VectorUtils.scalar;

public class Luminosity {
    public static void render(List<Triangle> triangles, Render render) {
        for (Triangle triangle : triangles) {
            Point n = triangle.normal();
            Point light = new Point(0, 0, -1);
            float intensity = scalar(n, light) / (n.length() * light.length());
           // System.out.println(intensity);
            if (intensity > 0)
                render.process(triangle, new Color((int) (intensity * 255), (int) (intensity * 255), (int) (intensity * 255)));
        }
    }
}
