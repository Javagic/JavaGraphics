package main

import java.awt.*
import java.awt.image.BufferedImage

import java.lang.Math.abs
import java.lang.Math.max

/**
 * a0 = x1
 * b0 = y1
 * a1 = x2
 * b1 = y2
 */
abstract class LineDrawer(image: BufferedImage, color: Color) {
    open fun plotLine(a0: Int, b0: Int, a1: Int, b1: Int) {}
}

class LineVU(val image: BufferedImage, val color: Color) : LineDrawer(image, color) {
    override fun plotLine(a0: Int, b0: Int, a1: Int, b1: Int) {
        var x0 = b0;
        var x1 = b1;
        var y0 = a0;
        var y1 = a1;
        var steep = false
        if (abs(x0 - x1) < abs(y0 - y1)) {
            x0 = y0.also { y0 = x0 }
            x1 = y1.also { y1 = x1 }
            steep = true
        }
        if (x0 > x1) {
            x0 = x1.also { x1 = x0 }
            y0 = y1.also { y1 = y0 }
        }
        val dx = x1 - x0
        val dy = y1 - y0
        val derror: Float = abs(dy / dx.toFloat())
        var error = 0f
        var y = y0
        val sy = if (y1 > y0) 1 else -1
        for (x in x0..x1) {
            val red = ((1 - error) * color.red).toInt()
            val green = ((1 - error) * color.green).toInt()
            val blue = ((1 - error) * color.blue).toInt()

            if (steep) {
                try {
                    image.setRGB(x, y, Color(red, green, blue).rgb)
                    image.setRGB(x, y + sy, Color(color.red - red, color.green - green, color.blue - blue).rgb)
                } catch (e: IllegalArgumentException) {
                    println(green)
                }
            } else {
                image.setRGB(y, x, Color(red, green, blue).rgb)
                image.setRGB(y + sy, x, Color(color.red - red, color.green - green, color.blue - blue).rgb)

            }

            error += derror
            if (error > 1.0) {
                y += sy
                error -= 1f
            }
        }
    }
}

class LineBresinheim(val image: BufferedImage, val color: Color) : LineDrawer(image, color) {
    override fun plotLine(a0: Int, b0: Int, a1: Int, b1: Int) {
        var x0 = a0;
        var x1 = a1;
        var y0 = b0;
        var y1 = b1;
        var steep = false
        if (abs(x0 - x1) < abs(y0 - y1)) {
            x0 = y0.also { y0 = x0 }
            x1 = y1.also { y1 = x1 }
            steep = true
        }
        if (x0 > x1) {
            x0 = x1.also { x1 = x0 }
            y0 = y1.also { y1 = y0 }
        }
        val dx = x1 - x0
        val dy = y1 - y0
        val derror: Float = abs(dy / dx.toFloat())
        var error = 0f
        var y = y0

        for (x in x0..x1) {
            if (!steep) {
                image.setRGB(x, y, color.rgb)
            } else {
                image.setRGB(y, x, color.rgb)
            }
            error += derror
            if (error > .5) {
                y += if (y1 > y0) 1 else -1
                error -= 1.0f
            }
        }
    }

}