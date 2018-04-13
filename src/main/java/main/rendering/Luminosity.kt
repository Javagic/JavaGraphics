package main.rendering

import main.shape.Point
import main.shape.Triangle
import main.shape.primtives.Face
import main.utils.VectorUtils.multiply

import main.utils.VectorUtils.scalar

class Luminosity {

    companion object {
        fun render(faces: List<Face>, render: Render, isLight: Boolean) {
            for (face in faces) {
                val triangle = Triangle(face.a.v, face.b.v, face.c.v)
                val light = Point(0.0, 0.3, 1.0)
                val intensity1 = if (isLight) scalar(face.a.vn.normalize(), light).toDouble() else 1.0
                val intensity2 = if (isLight) scalar(face.b.vn.normalize(), light).toDouble() else 1.0
                val intensity3 = if (isLight) scalar(face.c.vn.normalize(), light).toDouble() else 1.0
                render.process(triangle, face, intensity1, intensity2, intensity3)
            }
        }
    }
}