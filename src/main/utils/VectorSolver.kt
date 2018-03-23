package main.utils

import main.shape.Point
import main.utils.vectors.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.sqrt

class VectorSolver {
    object companion {
        fun rayXplane(
                r0: R0,
                e: E,
                rp0: RP0,
                n: N
        ): Point? {
            //sum((RP0- R0)*normal)==0 && sum(E. * N))
            if (sum(multiply(diff(rp0, r0), n)) == 0.0 && sum(multiply(e, n)) == 0.0) {//луч совпадает с плоскостью
                println("Луч лежит на плоскости")
            } else if (sum(multiply(e, n)) == 0.0) {//луч параллелен
                println("Пересечения нет")
            } else {//луч пересекает
                val t = abs(sum(multiply(diff(rp0, r0), n)) / sum(multiply(e, n)))
                println("Длина луча:" + t)
                val R = sum(r0, multiply(e, t))
                println("Координаты точки пересечения луча с плоскостью:" + R.toString())
                return Point(R.x, R.y, R.z)
            }
            return null
        }

        lateinit var r0: R0
        lateinit var e: E
        lateinit var rp0: RP0
        lateinit var M: Matrix
        var a = 0.0
        var b = 0.0
        var c = 0.0
        var t = 0.0

        fun rayXsphere(
                r0: R0,
                e: E,
                rp0: RP0,
                R: Double
        ): Point? {
            this.r0 = r0
            this.e = e
            this.rp0 = rp0
            val a = diff(r0, rp0)//r0-p0
            if (isCross(R) < 0) {
                println("Пересечения нет")
            } else if (isCross(R) == 0.0) {
                val t1 = scalar(a, e) + tSqrt(R)
                if (t1 > 0) println("t1 = " + t1)
            } else {
                val t1 = -scalar(a, e) + tSqrt(R)
                val t2 = -scalar(a, e) - tSqrt(R)
                val arr1 = sum(r0, multiply(e, t1))
                val arr2 = sum(r0, multiply(e, t2))
                if (t1 > 0 && (abs(t1) <= abs(t2) || t2 <= 0)) return Point(arr1.x, arr1.y, arr1.z).also { t = t1 }//println("t1 = " + t1)
                else if (t2 > 0) return Point(arr2.x, arr2.y, arr2.z).also { t = t2 }//println("t2 = " + t2)
            }
            return null
        }

        private fun isCross(R: Double): Double {
            val a = diff(r0, rp0)//r0-p0
            return scalar(a, e) * scalar(a, e) - (scalar(a, a) - R * R)
        }

        private fun tSqrt(R: Double): Double {
            val a = diff(r0, rp0)//r0-p0
            return sqrt(scalar(a, e) * scalar(a, e) - scalar(a, a) + R * R)
        }

        public fun sphereNorm() = diff(sum(r0, multiply(e, t)), rp0)
                .let { N(it.x / norm(r0, rp0), it.y / norm(r0, rp0), it.z / norm(r0, rp0)) }


        public fun rayRefraction(n1: Double, n2: Double, e: E, n: N): E {
            var v1 = multiply(e, n1)
            var v2 = multiply(multiply(n, n1), scalar(e, n))
            var v3 = 1 - sqrt((n2 * n2 - n1 * n1) / (scalar(e, n) * scalar(e, n) * n1 * n1) + 1)
            return multiply(diff(v1, multiply(v2, v3)), 1 / n2).E()
        }

        fun rayXHyperboloid(
                r0: R0,
                e: E,
                rp0: RP0,
                a: Double,
                b: Double,
                c: Double
        ): Point? {
            val M = Matrix().also {
                it.list.get(0)[0] = b * c
                it.list.get(1)[1] = a * c
                it.list.get(2)[2] = a * b
            }
            this.r0 = r0
            this.e = e
            this.rp0 = rp0
            this.M = M
            this.a = a
            this.b = b
            this.c = c
            val mrp0t = multiply(companion.M, diff(companion.r0, companion.rp0).matrix().T())
            val met = multiply(companion.M, companion.e.matrix().T())
            if (hyperboloidDiscr() < 0) {
                println("Пересечения нет")
            } else if (hyperboloidDiscr() == 0.0) {
                val t1 = -scalar(met, mrp0t) / scalar(met, met)
                if (t1 > 0) println("t1 = " + t1)
            } else {
                val t1 = (-scalar(met, mrp0t) + sqrt(hyperboloidDiscr())) / scalar(met, met)
                val t2 = (-scalar(met, mrp0t) - sqrt(hyperboloidDiscr())) / scalar(met, met)
                val arr1 = sum(r0, multiply(e, t1))
                val arr2 = sum(r0, multiply(e, t2))
                if (t1 > 0 && (abs(t1) <= abs(t2) || t2 <= 0)) return Point(arr1.x, arr1.y, arr1.z).also { t = t1 }//println("t1 = " + t1)
                else if (t2 > 0) return Point(arr2.x, arr2.y, arr2.z).also { t = t2 }//println("t2 = " + t2)
            }
            return null
        }

        fun hyperboloidDiscr(): Double {
            val mrp0t = multiply(M, diff(r0, rp0).matrix().T())
            val met = multiply(M, e.matrix().T())
            val b2 = scalar(met, mrp0t) * scalar(met, mrp0t)
            val res = b2 - scalar(met, met) * (scalar(mrp0t, mrp0t) - a * a * b * b * c * c)
            return res
        }
    }

}