package com.example.zad.ui.screens.home

import com.example.zad.core.presentation.UiEffect
import com.example.zad.core.presentation.UiIntent
import com.example.zad.core.presentation.UiState

data class HomeState(
    val greeting: String = "Assalamu alaikum",
    val subtitle: String = "Your daily companion for Quran, Azkar, prayer, and learning.",
    val sections: List<HomeSection> = defaultHomeSections,
) : UiState

data class HomeSection(
    val title: String,
    val description: String,
)

sealed interface HomeIntent : UiIntent {
    data class SectionClicked(val sectionTitle: String) : HomeIntent
}

sealed interface HomeEffect : UiEffect {
    data class ShowMessage(val message: String) : HomeEffect
}

private val defaultHomeSections = listOf(
    HomeSection(
        title = "Quran",
        description = "Read, listen, bookmark, and continue where you stopped.",
    ),
    HomeSection(
        title = "Azkar",
        description = "Morning, evening, prayer, sleep, and custom remembrance lists.",
    ),
    HomeSection(
        title = "Prayer Times",
        description = "Accurate times, notifications, and location-aware settings.",
    ),
    HomeSection(
        title = "Learning",
        description = "Structured Islamic knowledge paths with progress tracking.",
    ),
)
