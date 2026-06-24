package com.shehab.zad.ui.screens.prayer

import java.time.LocalTime

data class PrayerRowData(
    val nameAr: String,
    val nameEn: String,
    val time: String,
    val rawTime: LocalTime,
    val status: PrayerStatus,
    val icon: PrayerIcon
)