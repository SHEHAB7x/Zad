package com.shehab.zad.presentation.screens.home

import com.shehab.zad.presentation.screens.prayer.PrayerRowData

data class HomeUiState(
    val isLoading: Boolean             = false,
    val prayerRows: List<PrayerRowData> = emptyList(),
    val nextPrayerRow: PrayerRowData?  = null,
    val timeUntilNext: String?         = null,
    val dateText: String               = "",
    val greeting: String = "أهلاً" ,
    val error: String?                 = null,
    val isDay: Boolean                 = true
)
