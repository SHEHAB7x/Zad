package com.shehab.zad.domain.usecase

import com.batoulapps.adhan2.CalculationMethod
import com.shehab.zad.domain.model.PrayerTimes
import com.batoulapps.adhan2.Coordinates
import com.batoulapps.adhan2.data.DateComponents
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import javax.inject.Inject
import kotlin.time.ExperimentalTime
import kotlin.time.toJavaInstant

class CalculatePrayerTimesUseCase @Inject constructor() {
    @OptIn(ExperimentalTime::class)
    operator fun invoke(
        latitude: Double,
        longitude: Double,
        date: LocalDate
    ): PrayerTimes {
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

        return PrayerTimes(
            day     = date,
            fajr    = toLocalTime(adhanTimes.fajr),
            sunrise = toLocalTime(adhanTimes.sunrise),
            dhuhr   = toLocalTime(adhanTimes.dhuhr),
            asr     = toLocalTime(adhanTimes.asr),
            maghrib = toLocalTime(adhanTimes.maghrib),
            isha    = toLocalTime(adhanTimes.isha)
        )

    }
}