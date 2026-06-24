package com.shehab.zad.ui.screens.prayer

import com.shehab.zad.domain.model.PrayerTimes

data class PrayerUiState(
    val isLoading: Boolean             = false,
    val prayerTimes: PrayerTimes?      = null,
    val prayerRows: List<PrayerRowData> = emptyList(),
    val nextPrayerRow: PrayerRowData?  = null,
    val timeUntilNext: String?         = null,
    val error: String?                 = null
)
