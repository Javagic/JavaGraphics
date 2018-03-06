package main.shape

open class Point(val x: Int, val y: Int) {
    /**
     * Проверка принадлежности точки заданому треугольнику с помощью барицентрических координат
     */
    infix fun <T : Point> inside(triangle: Triangle<T>): Boolean {
        val x = listOf(triangle.a.x, triangle.b.x, triangle.c.x)
        val y = listOf(triangle.a.y, triangle.b.y, triangle.c.y)
        val nominator1: Float = ((y[1] - y[2]) * (this.x - x[2]) + (x[2] - x[1]) * (this.y - y[2])).toFloat()
        val nominator2: Float = ((y[2] - y[0]) * (this.x - x[2]) + (x[0] - x[2]) * (this.y - y[2])).toFloat()
        val denominator: Float = ((y[1] - y[2]) * (x[0] - x[2]) + (x[2] - x[1]) * (y[0] - y[2])).toFloat()
        if (nominator1 / denominator < 0 || nominator2 / denominator < 0) return false
        return 1f - nominator1 / denominator - nominator2 / denominator >= 0
    }
}
