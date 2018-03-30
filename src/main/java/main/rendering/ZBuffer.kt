package main.rendering

import main.shape.DoublePoint
import main.shape.Triangle
import main.utils.VectorUtils.*
import java.awt.Color
import java.awt.image.BufferedImage

class ZBuffer(var image: BufferedImage) :Render {
    private val zBuffer by lazy { DoubleArray(image.width * image.height, { Double.NEGATIVE_INFINITY }) }

    override fun process(triangle:Triangle, color: Color) {
        var p1 = DoublePoint(triangle.a)
        var p2 = DoublePoint(triangle.b)
        var p3 = DoublePoint(triangle.c)
        if (p1.y == p2.y && p1.y == p3.y) return
        if (p1.y > p2.y) p1 = p2.also { p2 = p1 }
        if (p1.y > p3.y) p1 = p3.also { p3 = p1 }
        if (p2.y > p3.y) p2 = p3.also { p3 = p2 }
        val totalHeight = p3.y - p1.y
        for (i in 0..totalHeight.toInt()) {
            val secondHalf = i > (p2.y - p1.y) || (p2.y == p1.y)
            val segmentHeight = if (secondHalf) p3.y - p2.y else p2.y - p1.y
            val alpha = i.toFloat() / totalHeight
            val beta = (i - (if (secondHalf) p2.y - p1.y else 0.0).toFloat()) / segmentHeight
            var A = sum(p1, multiply(diff(p3, p1), alpha))
            var B = if (secondHalf) sum(p2, multiply(diff(p3, p2), beta)) else sum(p1, multiply(diff(p2, p1), beta))
            if (A.x > B.x) A = B.also { B = A }
            for (j in A.x.toInt()..B.x.toInt()) {
                val phi = if (B.x == A.x) 1.0 else (j - A.x) / (B.x - A.x)
                val P = sum(A, multiply(diff(B, A), phi))
                val idx = (P.x + P.y * image.width).toInt()
                try {
                    if (zBuffer[idx] < P.z) {
                        zBuffer[idx] = P.z
                        image.setRGB(P.x.toInt(), P.y.toInt(), color.rgb)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

        }
    }
}
