package main.utils.vectors

import java.awt.Point
import kotlin.math.sqrt

open class Arr(open var x: Double, open var y: Double, open var z: Double) {
    fun arr() = doubleArrayOf(x, y, z)
    fun R0() = R0(x, y, z)
    fun E() = E(x, y, z)
    fun N() = N(x, y, z)
    fun matrix() = Matrix().apply {
        list[0][0] = x
        list[0][1] = y
        list[0][2] = z
    }
}
class R0(x: Double, y: Double, z: Double) : Arr(x, y, z)
class E(override var x: Double, override var y: Double, override var z: Double) : Arr(x, y, z) {
    init {
        val absE = sqrt(x * x + y * y + z * z)
        x /= absE
        y /= absE
        z /= absE
    }
}

class RP0(x: Double, y: Double, z: Double) : Arr(x, y, z)
class N(override var x: Double, override var y: Double, override var z: Double) : Arr(x, y, z) {
    init {
        val absN = sqrt(x * x + y * y + z * z)
        x /= absN
        y /= absN
        z /= absN
    }
}