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
    private val prayerRepository: PrayerRepository
): QiblaRepository {
    override fun getQiblaDirection(): Flow<Resource<Float>> = flow {
        val location = prayerRepository.getLocation()

        if (location is Resource.Error){
            emit(Resource.Error("تعذر الحصول على الموقع"))
            return@flow
        }

        val (lat, lon) = location.data!!

        val qiblaBearing = QiblaCalculator.calculateQiblaDirection(lat,lon)

        sensorProvider.getAzimuth().collect { azimuth ->
            val needleRotation = (qiblaBearing - azimuth + 360) % 360
            emit(Resource.Success(needleRotation))
        }

    }
}