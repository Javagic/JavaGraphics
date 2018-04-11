package main.rendering;

import main.shape.Point;
import main.shape.Triangle;
import main.shape.primtives.Face;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;

import static main.utils.VectorUtils.scalar;

public class BackFaceCulling implements Render {
    private BufferedImage image;

    public BackFaceCulling(BufferedImage image) {
        this.image = image;
    }


    @Override
    public void process(@NotNull Triangle triangle, @NotNull Face face, double intensity) {
        Point n = triangle.normal();
        Point v = new Point(0, 0, 1);
        if (scalar(n, v) / (n.length() * v.length()) < 0) {
            triangle.draw(image, intensity);
        }
    }
}
