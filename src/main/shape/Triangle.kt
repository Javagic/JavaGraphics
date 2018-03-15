package main.shape

import main.LineDrawer
import java.awt.Color
import java.awt.image.BufferedImage


class Triangle<T : Point>(var a: T, var b: T, var c: T) {
    /**Метод отрисовки трех линий, соединяющих точки треугольника*/
    fun plot(drawer: LineDrawer) = drawer.plotLine(a.x, a.y, b.x, b.y)
            .also { drawer.plotLine(a.x, a.y, c.x, c.y) }
            .also { drawer.plotLine(c.x, c.y, b.x, b.y) }

    /**Метод закрашивания треугольника с учетом границ изображения*/
    fun draw(image: BufferedImage, color: Color) {
        val minX = maxOf(0, minOf(a.x, b.x, c.x))
        val minY = maxOf(0, minOf(a.y, b.y, c.y))
        val maxX = minOf(image.height - 1, maxOf(a.x, b.x, c.x))
        val maxY = minOf(image.height - 1, maxOf(a.y, b.y, c.y))
        for (x in minX..maxX)
            for (y in minY..maxY)
                if (Point(x, y) inside this)
                    image.setRGB(x, y, color.rgb)

    }

    fun normal(A0: Point3D, A1: Point3D, A2: Point3D) : Point3D {
        return Point3D(1 * ((A2.y - A0.y) * (A1.z - A0.z)) - (A2.z - A0.z) * (A1.y - A0.y),
                1 * ((A2.x - A0.x) * (A1.z - A0.z)) - (A2.z - A0.z) * (A1.x - A0.x),
                1 * ((A2.x - A0.x) * (A1.y - A0.y)) - (A2.y - A0.y) * (A1.x - A0.x))
    }
}
