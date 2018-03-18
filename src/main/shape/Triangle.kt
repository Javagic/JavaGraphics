package main.shape

import main.utils.LineDrawer
import java.awt.Color
import java.awt.image.BufferedImage


class Triangle( a: Point,  b: Point,  c: Point) : Plane(a,b,c) {
/*
    */
/**Метод отрисовки трех линий, соединяющих точки треугольника*//*

    fun plot(drawer: LineDrawer) = drawer.plotLine(a.x, a.y, b.x, b.y)
            .also { drawer.plotLine(a.x, a.y, c.x, c.y) }
            .also { drawer.plotLine(c.x, c.y, b.x, b.y) }

    */
/**Метод закрашивания треугольника с учетом границ изображения*//*

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
*/


}
