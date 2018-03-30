package main.shape

class DoublePoint(val x: Double, val y: Double, val z: Double = 0.0) {
    constructor(point: DoublePoint) : this(point.x,point.y,point.z)
    constructor(point: Point) : this(point.x.toDouble(),point.y.toDouble(),point.z.toDouble())
}
