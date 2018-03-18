package main.shape

import java.awt.Color
import java.awt.image.BufferedImage

class Sphere(val point: Point, val R: Double) {
   /* fun draw(image: BufferedImage, color: Color) {
        val minX = maxOf(0.0, point.x - R)
        val minY = maxOf(0.0, point.y - R)
        val maxX = minOf(image.width - 1, point.x + R)
        val maxY = minOf(image.height - 1, point.y + R)
        for (x in minX..maxX)
            for (y in minY..maxY)
                if (Point(x, y) inside this)
                    image.setRGB(x, y, color.rgb)
    }
*/
}