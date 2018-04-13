package main.rendering

import main.shape.Triangle
import main.shape.primtives.Face

interface Render {
    fun process(triangle: Triangle, face: Face, intensity1: Double, intensity2: Double, intensity3: Double)
}