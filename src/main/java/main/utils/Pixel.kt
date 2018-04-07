package main.utils

import main.shape.Point
import main.shape.Triangle

class Pixel(x: Int, y: Int) : IntPoint(x, y, 0) {

    constructor(point: Point) : this(point.x.toInt(),point.y.toInt())
companion object {
    val EMPTY = Pixel(-1,-1)
}

    /**
     * Проверка принадлежности точки заданому треугольнику с помощью барицентрических координат
     */
    infix fun inside(triangle: Triangle): Boolean {
        val pa = OrtoManager.pixel(triangle.a)
        val pb = OrtoManager.pixel(triangle.b)
        val pc = OrtoManager.pixel(triangle.c)
        val x = listOf(pa.x, pb.x, pc.x)
        val y = listOf(pa.y, pb.y, pc.y)
        val nominator1: Float = ((y[1] - y[2]) * (this.x - x[2]) + (x[2] - x[1]) * (this.y - y[2])).toFloat()
        val nominator2: Float = ((y[2] - y[0]) * (this.x - x[2]) + (x[0] - x[2]) * (this.y - y[2])).toFloat()
        val denominator: Float = ((y[1] - y[2]) * (x[0] - x[2]) + (x[2] - x[1]) * (y[0] - y[2])).toFloat()
        if (nominator1 / denominator < 0 || nominator2 / denominator < 0) return false
        return 1f - nominator1 / denominator - nominator2 / denominator >= 0
    }

}