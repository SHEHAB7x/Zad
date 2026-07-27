package com.shehab.zad.data.repository

import com.shehab.zad.data.sensor.SensorProvider
import com.shehab.zad.domain.repository.PrayerRepository
import com.shehab.zad.domain.repository.QiblaRepository
import com.shehab.zad.domain.utils.Resource
import com.shehab.zad.utils.QiblaCalculator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QiblaRepositoryImpl @Inject constructor(
    private val sensorProvider: SensorProvider,
): QiblaRepository {
    override fun getAzimuthStream(): Flow<Float> = sensorProvider.getAzimuth()
}