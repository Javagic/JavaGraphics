package main.rendering

import main.shape.Point
import main.shape.Triangle
import main.utils.Face
import main.utils.Pixel

import java.awt.*

import main.utils.VectorUtils.scalar

class Luminosity {

    companion object {
        fun render(faces: List<Face>, render: Render, isLight: Boolean) {
            for (face in faces) {
                val triangle = Triangle(face.a.v, face.b.v, face.c.v)
                val n = triangle.normal()
                val light = Point(0.0, 0.0, -1.0)
                val intensity = scalar(n, light).toDouble() / (n.length() * light.length())
                if (intensity > 0)
                    render.process(triangle, face, if (isLight) intensity else 1.0)
            }
        }
    }


}
