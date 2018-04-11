package main.rendering

import main.shape.Triangle
import main.shape.primtives.Face
import main.helpers.OrtoManager
import main.shape.primtives.Pixel
import main.utils.VectorUtils.*
import java.awt.Color
import java.awt.image.BufferedImage

class ZBuffer(var image: BufferedImage, var diffuseImage: BufferedImage, var allowTextures: Boolean, val zBuffer: DoubleArray) : Render {


    override fun process(triangle: Triangle, face: Face, intensity: Double) {
        var p0 = OrtoManager.pixel(triangle.a)
        var p1 = OrtoManager.pixel(triangle.b)
        var p2 = OrtoManager.pixel(triangle.c)
        var uv0 = OrtoManager.diffusePixel(face.a.vt)
        var uv1 = OrtoManager.diffusePixel(face.b.vt)
        var uv2 = OrtoManager.diffusePixel(face.c.vt)
        if (p0 == Pixel.EMPTY || p1 == Pixel.EMPTY || p2 == Pixel.EMPTY) return
        if (p0.y == p1.y && p0.y == p2.y) return
        if (p0.y > p1.y) {
            p0 = p1.also { p1 = p0 }
            uv0 = uv1.also { uv1 = uv0 }
        }
        if (p0.y > p2.y) {
            p0 = p2.also { p2 = p0 }
            uv0 = uv2.also { uv2 = uv0 }

        }
        if (p1.y > p2.y) {
            p1 = p2.also { p2 = p1 }
            uv1 = uv2.also { uv2 = uv1 }

        }
        val totalHeight = p2.y - p0.y
        for (i in 0..totalHeight) {
            val secondHalf = i > (p1.y - p0.y) || (p1.y == p0.y)
            val segmentHeight = if (secondHalf) p2.y - p1.y else p1.y - p0.y
            val alpha = i.toDouble() / totalHeight
            val beta = (i - (if (secondHalf) p1.y - p0.y else 0).toDouble()) / segmentHeight
            var A = sum(p0, multiply(diff(p2, p0), alpha))
            var B = if (secondHalf) sum(p1, multiply(diff(p2, p1), beta)) else sum(p0, multiply(diff(p1, p0), beta))
            var uvA = sum(uv0, multiply(diff(uv2, uv0), alpha))
            var uvB = if (secondHalf) sum(uv1, multiply(diff(uv2, uv1), beta)) else sum(uv0, multiply(diff(uv1, uv0), beta))

            if (A.x > B.x) {
                A = B.also { B = A }
                uvA = uvB.also { uvB = uvA }
            }
            for (j in A.x..B.x) {
                val phi = if (B.x == A.x) 1.0 else (j - A.x).toDouble() / (B.x - A.x)
                val P = sum(A, multiply(diff(B, A), phi))
                val uvP = sum(uvA, multiply(diff(uvB, uvA), phi))
                P.x = j; P.y = p0.y + i;
                val idx = (P.x + P.y * image.width)
                if (idx > zBuffer.size - 1) continue
                if (zBuffer[idx] < P.z) {
                    zBuffer[idx] = P.z.toDouble()
                    val col = diffuseImage.getRGB(uvP.x, uvP.y)

                    val red = (if (allowTextures) ((col shr 16) and 0xff) else 255) * intensity
                    val green = (if (allowTextures) ((col shr 8) and 0xff) else 255) * intensity
                    val blue = (if (allowTextures) ((col) and 0xff) else 255) * intensity


                    image.setRGB(P.x, P.y, Color(red.toInt(), green.toInt(), blue.toInt()).rgb)
                }

            }

        }
    }
}
