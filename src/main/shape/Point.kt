package main.shape

import main.utils.vectors.Arr

class Point( x: Double,  y: Double,  z: Double = 0.0) : Arr(x,y,z) {
    fun length(): Float {
        return Math.sqrt((this.x * this.x + this.y * this.y + this.z * this.z).toDouble()).toFloat()
    }

    /**
     * Проверка принадлежности точки заданому треугольнику с помощью барицентрических координат
     */
    infix fun inside(triangle: Triangle): Boolean {
        val x = listOf(triangle.a.x, triangle.b.x, triangle.c.x)
        val y = listOf(triangle.a.y, triangle.b.y, triangle.c.y)
        val nominator1: Float = ((y[1] - y[2]) * (this.x - x[2]) + (x[2] - x[1]) * (this.y - y[2])).toFloat()
        val nominator2: Float = ((y[2] - y[0]) * (this.x - x[2]) + (x[0] - x[2]) * (this.y - y[2])).toFloat()
        val denominator: Float = ((y[1] - y[2]) * (x[0] - x[2]) + (x[2] - x[1]) * (y[0] - y[2])).toFloat()
        if (nominator1 / denominator < 0 || nominator2 / denominator < 0) return false
        return 1f - nominator1 / denominator - nominator2 / denominator >= 0
    }

  /*  infix fun inside(sphere: Sphere): Boolean {
        return ((sphere.point.x - x)*(sphere.point.x - x) + (sphere.point.y - y)*(sphere.point.y - y)== sphere.R)
    }*/
}
