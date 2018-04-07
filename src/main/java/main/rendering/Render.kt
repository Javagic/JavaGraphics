package main.rendering

import main.shape.Triangle
import main.utils.Face
import main.utils.Pixel
import java.awt.Color

interface Render {
    fun process(triangle: Triangle, face: Face, intensity : Double)
}