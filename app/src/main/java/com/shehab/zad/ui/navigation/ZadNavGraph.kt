package com.shehab.zad.ui.navigation

import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shehab.zad.ui.screens.Ayah.AyahScreen
import com.shehab.zad.ui.screens.surahs.SurahListScreen
import com.shehab.zad.ui.screens.home.HomeScreen

@Composable
fun ZadNavGraph(navController: NavHostController){
    NavHost(
        modifier = Modifier.safeDrawingPadding(),
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable (Screen.Home.route){
            HomeScreen()
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
            Text("Prayer — coming soon")
        }
        composable(Screen.Azkar.route) {
            Text("Azkar — coming soon")
        }
        composable(Screen.Tafseer.route) {
            Text("Tafseer — coming soon")
        }
        composable(Screen.Qibla.route) {
            Text("Qibla — coming soon")
        }
    }
}