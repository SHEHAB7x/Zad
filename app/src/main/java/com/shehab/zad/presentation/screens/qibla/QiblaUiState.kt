package com.shehab.zad.presentation.screens.qibla

data class QiblaUiState(
    val isLoading: Boolean    = false,
    val needleRotation: Float = 0f,
    val qiblaBearing: Float   = 0f,
    val distanceKm: Double    = 0.0,
    val cityName: String?     = null,
    val isAccurate: Boolean   = true,
    val error: String?        = null
)