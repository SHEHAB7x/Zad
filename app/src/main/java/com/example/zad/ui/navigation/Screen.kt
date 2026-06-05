package com.example.zad.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Quran : Screen("quran")
    data object Prayer : Screen("prayer")
    data object Tafseer : Screen("tafseer")
    data object Azkar : Screen("azkar")
    data object Qibla : Screen("qibla")
}