package com.shehab.zad.presentation.screens.surahs

import com.shehab.zad.domain.model.Surah

data class QuranUiState(
    val isLoading: Boolean = false,
    val surahs: List<Surah> = emptyList(),
    val error: String? = null
)
