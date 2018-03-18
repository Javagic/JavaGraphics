package main.utils

import main.shape.Point
import main.utils.VectorUtils.*
import java.util.*
import kotlin.math.abs
import kotlin.math.sqrt

class VectorSolver {
    object companion {
        fun rayXplane(
                x0: Double,
                y0: Double,
                z0: Double,
                ex: Double,
                ey: Double,
                ez: Double,
                rpx0: Double,
                rpy0: Double,
                rpz0: Double,
                nx: Double,
                ny: Double,
                nz: Double
        ): Point? {
            val absN = sqrt(nx * nx + ny * ny + nz * nz)
            val absE = sqrt(ex * ex + ey * ey + ez * ez)
            val R0 = doubleArrayOf(x0, y0, z0)
            val E = doubleArrayOf(ex / absE, ey / absE, ez / absE)
            val RP0 = doubleArrayOf(rpx0, rpy0, rpz0)
            val N = doubleArrayOf(nx / absN, ny / absN, nz / absN)
            //sum((RP0- R0)*normal)==0 && sum(E. * N))
            if (sum(multiply(diff(RP0, R0), N)) == 0.0 && sum(multiply(E, N)) == 0.0) {//луч совпадает с плоскостью
                println("Луч лежит на плоскости")
            } else if (sum(multiply(E, N)) == 0.0) {//луч параллелен
                println("Пересечения нет")
            } else {//луч пересекает
                val t = abs(sum(multiply(diff(RP0, R0), N)) / sum(multiply(E, N)))
                println("Длина луча:" + t)
                val R = sum(R0, multiply(E, t))
                println("Координаты точки пересечения луча с плоскостью:" + Arrays.toString(R))
                return Point(R[0], R[1], R[2])
            }
            return null
        }

        fun rayXsphere(){

        }
    }
}