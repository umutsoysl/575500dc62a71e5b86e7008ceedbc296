package com.umut.soysal.spacedelivery.core.util

import kotlin.math.pow
import kotlin.math.sqrt

object DistanceExtension {

    fun calculateDistance(x1: Double, x2: Double, y1: Double, y2: Double): Double {
        val firstPoint = (x2 - x1).pow(2.0)
        val secondPoint = (y2 - y1).pow(2.0)
        return sqrt(firstPoint + secondPoint).format(2)
    }
}