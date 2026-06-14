package com.shehab.zad.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class PrayerTimes(
    val day: LocalDate,
    val fajr: LocalTime,
    val sunrise: LocalTime,
    val dhuhr: LocalTime,
    val asr: LocalTime,
    val maghrib: LocalTime,
    val isha: LocalTime
)
