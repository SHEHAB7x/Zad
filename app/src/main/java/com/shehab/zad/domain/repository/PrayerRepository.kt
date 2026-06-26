package com.shehab.zad.domain.repository

import com.shehab.zad.domain.model.PrayerTimes
import com.shehab.zad.domain.utils.Resource

interface PrayerRepository {
    suspend fun getLocation(): Resource<Pair<Double, Double>>
    suspend fun getCityName(latitude: Double, longitude: Double): String?
}