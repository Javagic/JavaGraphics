package main.shape

import main.lineVU
import java.awt.Color
import java.awt.image.BufferedImage

class Triangle<T : Point>(var a: T, var b: T, var c: T) {
    fun plot(image: BufferedImage, color: Color) = lineVU(a.x, a.y, b.x, b.y, image, color)
            .also { lineVU(a.x, a.y, c.x, c.y, image, color) }
            .also { lineVU(c.x, c.y, b.x, b.y, image, color) }
}
