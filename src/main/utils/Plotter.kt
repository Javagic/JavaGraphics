package main.utils

import main.shape.Point
import main.shape.Sphere
import main.utils.VectorSolver.companion.rayXplane
import main.utils.VectorSolver.companion.rayXsphere
import main.utils.VectorSolver.companion.sphereNorm
import main.utils.vectors.*
import main.utils.vectors.Arr.*
import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.math.abs
import kotlin.math.sqrt


class Plotter(val ortoManager: OrtoManager, val image: BufferedImage) {

    fun plotLine(a: Arr, b: Arr, color: Color) = plotLine(Point(a.x, a.y, a.z), Point(b.x, b.y, b.z), color)
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

    fun plotRay(r0: R0, e: E, color: Color) {
        var point2 = rayXplane(r0, e, RP0(ortoManager.startX, 0.0, 0.0), N(1.0, 0.0, 0.0)) //левая
        if (point2 == null || point2.x < ortoManager.startX || point2.x > ortoManager.endX || point2.y > ortoManager.endY || point2.y < ortoManager.startY) {
            point2 = rayXplane(r0, e, RP0(ortoManager.endX, 0.0, 0.0), N(1.0, 0.0, 0.0))//правая
        }
        if (point2 == null || point2.x < ortoManager.startX || point2.x > ortoManager.endX || point2.y > ortoManager.endY || point2.y < ortoManager.startY) {
            point2 = rayXplane(r0, e, RP0(0.0, ortoManager.startY, 0.0), N(0.0, 1.0, 0.0))//нижняя
        }
        if (point2 == null || point2.x < ortoManager.startX || point2.x > ortoManager.endX || point2.y > ortoManager.endY || point2.y < ortoManager.startY) {
            point2 = rayXplane(r0, e, RP0(0.0, ortoManager.endY, 0.0), N(0.0, 1.0, 0.0))//верхняя
        }
        val p1 = ortoManager.pixel(r0)
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

    public fun plotRayXSphereReflection(sphere: Sphere, sphereColor: Color, r0: R0, e: E, rayColor: Color) {
        plotSphere(sphere, sphereColor)
        val point2 = rayXsphere(r0, e, sphere.point, sphere.R)
        if (point2 != null) {
            plotLine(r0, point2, rayColor)
            val sphereRay = diff(e, multiply(sphereNorm(), 2 * scalar(e, sphereNorm()))).E()
            plotRay(point2.R0(), sphereRay, sphereColor)
        } else {
            plotRay(r0, e, rayColor)
        }
    }

    public fun plotRayXSphereRefraction(sphere: Sphere, sphereColor: Color, r0: R0, e: E, rayColor: Color) {
        plotSphere(sphere, sphereColor)
        val point2 = rayXsphere(r0, e, sphere.point, sphere.R)
        if (point2 != null) {
            plotLine(r0, point2, rayColor)
            val n = VectorSolver.companion.rayRefraction(0.5, 1.9, e, sphereNorm())
            plotRay(point2.R0(), n, sphereColor)
        } else {
            plotRay(r0, e, rayColor)
        }
    }
}