package main.rendering

import main.shape.Point
import main.shape.Triangle
import main.shape.primtives.Face

import java.awt.image.BufferedImage

import main.utils.VectorUtils.scalar

class BackFaceCulling(private val image: BufferedImage) : Render {


    fun process(triangle: Triangle, face: Face, intensity: Double) {
        val n = triangle.normal()
        val v = Point(0.0, 0.0, 1.0)
        if (scalar(n, v) / (n.length() * v.length()) < 0) {
            triangle.draw(image, intensity)
        }
    }


    override fun process(triangle: Triangle, face: Face, intensity1: Double, intensity2: Double, intensity3: Double) {
        val n = triangle.normal()
        val light = Point(0.0, 0.0, -1.0)
        val intensity = scalar(n, light).toDouble() / (n.length() * light.length())
        val v = Point(0.0, 0.0, 1.0)
        if (scalar(n, v) / (n.length() * v.length()) < 0) {
            triangle.draw(image, intensity)
        }
    }
}
