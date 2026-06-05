package com.example.zad.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.zad.ui.screens.home.HomeScreen

@Composable
fun ZadNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable (Screen.Home.route){
            HomeScreen()
        }
        composable(Screen.Quran.route) {
            Text("Quran — coming soon")   // placeholder until we build it
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