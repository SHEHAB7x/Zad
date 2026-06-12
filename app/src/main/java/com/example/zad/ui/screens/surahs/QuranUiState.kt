package com.example.zad.ui.screens.surahs

import com.example.zad.domain.model.Surah

data class QuranUiState(
    val isLoading: Boolean = false,
    val surahs: List<Surah> = emptyList(),
    val error: String? = null
)
