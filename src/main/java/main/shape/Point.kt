package main.shape

class Point(var x: Double, var y: Double, var z: Double = 0.0) {
    constructor(point: Point) : this(point.x,point.y,point.z)

    fun length(): Float {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z).toFloat()
    }
}
