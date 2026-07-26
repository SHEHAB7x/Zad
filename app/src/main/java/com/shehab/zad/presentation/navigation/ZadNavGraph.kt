package com.shehab.zad.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shehab.zad.presentation.screens.ayah.AyahScreen
import com.shehab.zad.presentation.screens.home.HomeRoute
import com.shehab.zad.presentation.screens.surahs.SurahListScreen
import com.shehab.zad.presentation.screens.home.HomeScreen
import com.shehab.zad.presentation.screens.prayer.PrayerRoute
import com.shehab.zad.presentation.screens.qibla.QiblaRoute

@Composable
fun ZadNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
){
    NavHost(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable (Screen.Home.route){
            HomeRoute(
                onNavigateToQuran  = { navController.navigate(Screen.Quran.route) },
                onNavigateToQibla  = { navController.navigate(Screen.Qibla.route) },
                onNavigateToPrayer = { navController.navigate(Screen.Prayer.route) },
                onNavigateToAzkar  = { navController.navigate(Screen.Azkar.route) }
            )
        }
        composable(Screen.Quran.route) {
            SurahListScreen(
                onSurahClick = { surahNumber ->
                    navController.navigate(Screen.Ayah.createRoute(surahNumber))
                }
            )
        }
        composable (
            route = Screen.Ayah.route,
            arguments = listOf(
                navArgument("surahNumber") { type = NavType.IntType }
            )
            ) { backStackEntry ->
            val surahNumber = backStackEntry.arguments?.getInt("surahNumber") ?: 1
            AyahScreen(surahNumber = surahNumber)
        }
        composable(Screen.Prayer.route) {
            PrayerRoute()
        }
        composable(Screen.Azkar.route) {
            Text("Azkar — coming soon")
        }
        composable(Screen.Tafseer.route) {
            Text("Tafseer — coming soon")
        }
        composable(Screen.Qibla.route) {
            QiblaRoute()
        }
        composable(Screen.Settings.route) {
            Text("Settings - coming soon")
        }
    }
}