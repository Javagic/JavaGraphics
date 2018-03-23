package main.utils

import main.utils.vectors.Arr
import main.utils.vectors.Matrix
import java.lang.Math.sqrt

fun <T : Arr> diff(a: T, b: T) = Arr(a.x - b.x, a.y - b.y, a.z - b.z)
fun <T : Arr> sum(a: T, b: T) = Arr(a.x + b.x, a.y + b.y, a.z + b.z)
fun <T : Arr> multiply(a: T, b: T) = Arr(a.x * b.x, a.y * b.y, a.z * b.z)
fun <T : Arr> multiply(a: T, b: Double) = Arr(a.x * b, a.y * b, a.z * b)
fun <T : Arr> scalar(a: T, b: T) = sum(multiply(a, b))
fun <T : Arr> norm(a: T, b: T) = sqrt(diff(a, b).x * diff(a, b).x + diff(a, b).y * diff(a, b).y * diff(a, b).z * diff(a, b).z)
fun <T : Arr> sum(a: T) = a.arr().sum()

fun multiply(a: Matrix, b: Matrix) = Matrix().also {
    for (i in 0 until 3) {
        for (j in 0 until 3) {
            for (k in 0 until 3) {
                it.list[i][j] += a.list[i][k] * b.list[k][j]
            }
        }
    }
}

fun scalar(a: Matrix, b: Matrix): Double {
    var result = 0.0
    result += a.list[0][0] * b.list[0][0]
    result += a.list[1][0] * b.list[1][0]
    result += a.list[2][0] * b.list[2][0]
    return result
}
