package main.shape

class Point3D(x: Int, y: Int, val z: Int) : Point(x,y) {

    fun length(): Float {
        return Math.sqrt((this.x * this.x + this.y * this.y + this.z * this.z).toDouble()).toFloat()
    }
}
