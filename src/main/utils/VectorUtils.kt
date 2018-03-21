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

fun <T : Arr> multiply(a: Matrix, b: Matrix) = Matrix().let {
    for (i in 0 until 3) {
        for (j in 0 until 3) {
            for (k in 0 until 3) {
                it.list[i][j] = a.list[i][k]*b.list[k][j]
            }
        }
    }
}
