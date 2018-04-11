package main.shape

import main.shape.primtives.Pixel
import main.utils.LineDrawer
import main.helpers.OrtoManager
import java.awt.Color
import java.awt.image.BufferedImage


class Triangle(a: Point, b: Point, c: Point) : Plane(a, b, c) {
    /**Метод отрисовки трех линий, соединяющих точки треугольника*/
    fun plot(drawer: LineDrawer) {
        val pa = OrtoManager.pixel(a)
        val pb = OrtoManager.pixel(b)
        val pc = OrtoManager.pixel(c)
        drawer.plotLine(pa.x, pa.y, pb.x, pb.y)
                .also { drawer.plotLine(pa.x, pa.y, pc.x, pc.y) }
                .also { drawer.plotLine(pc.x, pc.y, pb.x, pb.y) }
    }

    /**Метод закрашивания треугольника с учетом границ изображения*/
    fun draw(image: BufferedImage, color: Color) {
        val pa = OrtoManager.pixel(a)
        val pb = OrtoManager.pixel(b)
        val pc = OrtoManager.pixel(c)
        val minX = maxOf(1, minOf(pa.x, pb.x, pc.x))
        val minY = maxOf(1, minOf(pa.y, pb.y, pc.y))
        val maxX = minOf(image.height - 1, maxOf(pa.x, pb.x, pc.x))
        val maxY = minOf(image.height - 1, maxOf(pa.y, pb.y, pc.y))
        for (x in minX..maxX)
            for (y in minY..maxY)
                if (Pixel(x, y) inside this)
                    image.setRGB(x, y, color.rgb)
    }

    fun draw(image: BufferedImage, red: Int, green: Int, blue: Int) = draw(image, Color(red, green, blue))

    fun draw(image: BufferedImage, intensity: Double) = draw(image, (255 * intensity).toInt(), (255 * intensity).toInt(), (255 * intensity).toInt())

}
