package main.utils

import main.shape.Point
import main.shape.Sphere
import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.math.sqrt


class Plotter(val ortoManager: OrtoManager, val image: BufferedImage) {

    fun plotLine(a: Point, b: Point, color: Color) {
        val p1 = ortoManager.pixel(a)
        val p2 = ortoManager.pixel(b)
        LineVU(image, color).plotLine(p1.x, p1.y, p2.x, p2.y)
    }

    fun printCoords() {
        try {
            plotLine(Point(0.0, ortoManager.startY), Point(0.0, ortoManager.endY), Color.GREEN)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
        try {
            plotLine(Point(ortoManager.startX, 0.0), Point(ortoManager.endX, 0.0), Color.GREEN)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    fun plotRay(point1: Point, direction: Point, color: Color) {
        val point2 = VectorSolver.companion.rayXplane(point1.x, point1.y, point1.z, direction.x, direction.y, direction.z, ortoManager.startX, 0.0, 0.0, 1.0, 0.0, 0.0)
                ?: VectorSolver.companion.rayXplane(point1.x, point1.y, point1.z, direction.x, direction.y, direction.z, ortoManager.endX, 0.0, 0.0, 1.0, 0.0, 0.0)
                ?: VectorSolver.companion.rayXplane(point1.x, point1.y, point1.z, direction.x, direction.y, direction.z, 0.0, ortoManager.startY, 0.0, 0.0, 1.0, 0.0)
                ?: VectorSolver.companion.rayXplane(point1.x, point1.y, point1.z, direction.x, direction.y, direction.z, 0.0, ortoManager.endY, 0.0, 0.0, 1.0, 0.0)

        val p1 = ortoManager.pixel(point1)
        val p2 = point2?.let { ortoManager.pixel(point2) }
        LineVU(image, color).plotLine(p1.x, p1.y, p2!!.x, p2.y)
    }

    fun plotSphere(sphere: Sphere, color: Color) {
        val pixel = ortoManager.pixel(sphere.point)
        val R = ortoManager.distance(sphere.R)
        val startX = ortoManager.pixel(Point(sphere.point.x - sphere.R, sphere.point.y, sphere.point.z))
        val endX = ortoManager.pixel(Point(sphere.point.x + sphere.R, sphere.point.y, sphere.point.z))
        val startY = ortoManager.pixel(Point(sphere.point.x, sphere.point.y + sphere.R, sphere.point.z))
        val endY = ortoManager.pixel(Point(sphere.point.x, sphere.point.y - sphere.R, sphere.point.z))
        for (x in startX.x..endX.x) {
            for (y in startY.y..endY.y) {
                if (sqrt(((pixel.x - x) * (pixel.x - x) + (pixel.y - y) * (pixel.y - y)).toDouble()).toInt() == R) image.setRGB(x, y, color.rgb)
            }

        }

    }
}