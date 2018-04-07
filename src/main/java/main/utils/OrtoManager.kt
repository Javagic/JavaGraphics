package main.utils

import main.shape.Point
import java.awt.image.BufferedImage

/**
 * Класс осуществляющий перевод из координат с плавающей точкой в пиксельное представление
 * Позволяет избежать переворачиваний текстурных файлов и конечного изображения
 */
class OrtoManager(val image: BufferedImage, val absSize: Int) {


    fun init(){
        Companion.width = image.width
        Companion.height = image.height
        Companion.absSize = absSize
    }
    /**
     * Передача по модулю [absSize] в зависимости от исчиления координат точек в файле *.obj
     */
    companion object {
        var width: Int = 0
        var height: Int = 0
        var absSize: Int = 0

        /**
         * Пересчет точек с дробной частью[Point] в точку с целочисленными координатами[Pixel] для отрисовки на изображении
         */
        fun pixel(point: Point): IntPoint {
            if (point.x !in -absSize..absSize || point.y !in -absSize..absSize) return Pixel.EMPTY
            val x = ((point.x - (-absSize)) * width / ((absSize) - (-absSize))).toInt()
            val z = ((point.z - (-absSize)) * width / ((absSize) - (-absSize))).toInt()
            //потому что в BufferedImage 0,0 находится в верхнем левом углу
            val y = ((absSize - point.y) * height / (absSize - (-absSize))).toInt()
            return IntPoint(x, y, z)
        }

        /**
         * Поскольку в текстурах координаты от  0 до 1, сделал другой пересчет точек
         */
        fun diffusePixel(point: Point): IntPoint {
            if (point.x !in -absSize..absSize || point.y !in -absSize..absSize) return Pixel.EMPTY
            val x = ((absSize - point.x) * width).toInt()
            //потому что в BufferedImage 0,0 находится в верхнем левом углу
            val y = ((absSize - point.y) * height).toInt()
            return IntPoint(x, y, 0)
        }
    }
}