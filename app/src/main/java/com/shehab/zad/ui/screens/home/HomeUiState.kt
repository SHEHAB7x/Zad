package com.shehab.zad.ui.screens.home

import com.shehab.zad.domain.model.PrayerTimes

data class HomeUiState(
    val isLoading: Boolean             = false,
    val prayerTimes: PrayerTimes?      = null,
    val timeUntilNext: String?         = null,
    val dateText: String?              = null,
    val error: String?                 = null
)
