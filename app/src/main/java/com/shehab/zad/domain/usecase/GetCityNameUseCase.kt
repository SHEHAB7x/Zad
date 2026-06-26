package com.shehab.zad.domain.usecase

import com.shehab.zad.domain.repository.PrayerRepository
import com.shehab.zad.domain.utils.Resource
import javax.inject.Inject

class GetCityNameUseCase @Inject constructor(
    private val repository: PrayerRepository
) {
    suspend operator fun invoke() : String? {
        val locationResource = repository.getLocation()
        if (locationResource is Resource.Error) return null

        val (latitude, longitude) = locationResource.data!!
        return repository.getCityName(latitude, longitude)
    }
}