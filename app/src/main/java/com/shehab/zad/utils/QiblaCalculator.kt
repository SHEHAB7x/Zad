package com.shehab.zad.utils

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

object QiblaCalculator {
    private const val MECCA_LAT = 21.4225
    private const val MECCA_LON = 39.8262

    fun calculateQiblaDirection(
        userLat: Double,
        userLon: Double
    ): Float {
        val lat1 = Math.toRadians(userLat)
        val lat2 = Math.toRadians(MECCA_LAT)
        val dLon = Math.toRadians(MECCA_LON - userLon)

        val x = sin(dLon) * cos(lat2)
        val y = cos(lat1) * sin(lat2) - sin(lat1) * cos(lat2) * cos(dLon)

        val bearing = Math.toDegrees(atan2(x, y))
        val qiblaBearing = (bearing + 360) % 360

        return qiblaBearing.toFloat()
    }

    fun calculateDistanceToMecca(userLat: Double, userLon: Double): Double {
        val earthRadius = 6371.0
        val dLat = Math.toRadians(MECCA_LAT - userLat)
        val dLon = Math.toRadians(MECCA_LON - userLon)
        val a = sin(dLat/2).pow(2) +
                cos(Math.toRadians(userLat)) * cos(Math.toRadians(MECCA_LAT)) *
                sin(dLon/2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1-a))
        return earthRadius * c
    }
}