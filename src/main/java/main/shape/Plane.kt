package main.shape

open class Plane(var a: Point, var b: Point, var c: Point) {
    fun normal(): Point {
        return Point(1 * ((c.y - a.y) * (b.z - a.z)) - (c.z - a.z) * (b.y - a.y),
                1 * ((c.x - a.x) * (b.z - a.z)) - (c.z - a.z) * (b.x - a.x),
                1 * ((c.x - a.x) * (b.y - a.y)) - (c.y - a.y) * (b.x - a.x))
    }
}