package main.utils

import main.shape.Point
import main.utils.vectors.Arr
import java.awt.image.BufferedImage

class OrtoManager(val startX: Double,
                  val endX: Double,
                  val startY: Double,
                  val endY: Double,
                  val image: BufferedImage) {
    fun pixel(point: Point): Pixel {
        if (point.x !in startX..endX || point.y !in startY..endY) throw IllegalArgumentException("Точка вне границ")
        val x = ((point.x - startX) * image.width / (endX - startX)).toInt()
        //потому что в BufferedImage 0,0 находится в верхнем левом углу
        val y = ((endY - point.y) * image.height / (endY - startY)).toInt()
        return Pixel(x, y)
    }

    fun pixel(arr: Arr) = pixel(Point(arr.x, arr.y, arr.z))

    fun point(x:Double,y:Double) :Point{
        val x1 = (endX - startX)/((x - startX) * image.width)
        val y1 = (endY - startY) /((endY - y) * image.height)
        return Point(x1,y1,0.0)
    }
    fun distance(double: Double) = (double * image.height / (endY - startY)).toInt()
}