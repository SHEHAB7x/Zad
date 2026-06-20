package com.shehab.zad.domain.usecase

import com.batoulapps.adhan2.CalculationMethod
import com.shehab.zad.domain.model.PrayerTimes
import com.batoulapps.adhan2.Coordinates
import com.batoulapps.adhan2.data.DateComponents
import com.shehab.zad.domain.repository.PrayerRepository
import com.shehab.zad.domain.utils.Resource
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import javax.inject.Inject
import kotlin.time.ExperimentalTime
import kotlin.time.toJavaInstant

class CalculatePrayerTimesUseCase @Inject constructor(
    private val repository: PrayerRepository
) {
    @OptIn(ExperimentalTime::class)
    suspend operator fun invoke(
        date: LocalDate
    ): Resource<PrayerTimes> {

        val locationResource = repository.getLocation()
        if (locationResource is Resource.Error){
           return Resource.Error(locationResource.message ?: "Unknown error")
        }

        val (latitude, longitude) = locationResource.data!!

        val coordinates = Coordinates(latitude, longitude)
        val params = CalculationMethod.MUSLIM_WORLD_LEAGUE.parameters

        val adhanTimes = com.batoulapps.adhan2.PrayerTimes(
            coordinates,
            DateComponents(
                year = date.year,
                month = date.monthValue,
                day = date.dayOfMonth
            ),
            params
        )


        fun toLocalTime(instant: kotlinx.datetime.Instant): LocalTime =
            instant.toJavaInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalTime()

        return Resource.Success(
            PrayerTimes(
            day     = date,
            fajr    = toLocalTime(adhanTimes.fajr),
            sunrise = toLocalTime(adhanTimes.sunrise),
            dhuhr   = toLocalTime(adhanTimes.dhuhr),
            asr     = toLocalTime(adhanTimes.asr),
            maghrib = toLocalTime(adhanTimes.maghrib),
            isha    = toLocalTime(adhanTimes.isha)
            )
        )
    }
}