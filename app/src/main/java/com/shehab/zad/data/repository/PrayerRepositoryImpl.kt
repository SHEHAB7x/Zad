package com.shehab.zad.data.repository

import com.shehab.zad.data.location.GeocoderProvider
import com.shehab.zad.data.location.LocationProvider
import com.shehab.zad.domain.repository.PrayerRepository
import com.shehab.zad.domain.utils.Resource
import javax.inject.Inject

class PrayerRepositoryImpl @Inject constructor(
    private val locationProvider: LocationProvider,
    private val geocoderProvider: GeocoderProvider
) : PrayerRepository {

    override suspend fun getLocation(): Resource<Pair<Double, Double>> {
        val location = locationProvider.getCurrentLocation()
        return if (location != null) {
            Resource.Success(location)
        } else {
            Resource.Error("تعذّر الحصول على الموقع")
        }
    }

    override suspend fun getCityName(
        latitude: Double,
        longitude: Double
    ): String? {
        return geocoderProvider.getCityName(latitude,longitude)
    }
}