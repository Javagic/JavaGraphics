package main.rendering

import main.shape.Triangle
import java.awt.Color

interface Render {
    fun process(triangle: Triangle,color: Color)
}