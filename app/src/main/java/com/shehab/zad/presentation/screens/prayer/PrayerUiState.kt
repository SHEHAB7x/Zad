package com.shehab.zad.presentation.screens.prayer

data class PrayerUiState(
    val isLoading: Boolean             = false,
    val prayerRows: List<PrayerRowData> = emptyList(),
    val nextPrayerRow: PrayerRowData?  = null,
    val timeUntilNext: String?         = null,
    val cityName: String?              = null,
    val dateText: String?              = null,
    val error: String?                 = null
)
