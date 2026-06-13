package com.shehab.zad.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.BackHand
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun ZadBottomBar(
    navController: NavController
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val showBottomBar = currentRoute in listOf(
        Screen.Home.route,
        Screen.Quran.route,
        Screen.Prayer.route,
        Screen.Azkar.route,
        Screen.Settings.route
    )

    val bottomBarItem = listOf(
        BottomBarItem(Screen.Home.route,"الرئيسية", Icons.Default.Home),
        BottomBarItem(Screen.Quran.route,    "القرآن",   Icons.Default.MenuBook),
        BottomBarItem(Screen.Prayer.route,    "مواقيت الصلاة",   Icons.Default.Alarm),
        BottomBarItem(Screen.Azkar.route,    "الأذكار",   Icons.Default.BackHand),
        BottomBarItem(Screen.Settings.route,    "الإعدادات",   Icons.Default.Settings),
    )

    if (showBottomBar){
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            tonalElevation = 0.dp
        ){
            bottomBarItem.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    onClick = { navController.navigate(item.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    } },
                    icon = { Icon(item.icon, contentDescription = item.title) },
                    label = { Text(item.title) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor   = MaterialTheme.colorScheme.primary,
                        selectedTextColor   = MaterialTheme.colorScheme.primary,
                        indicatorColor      = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                        unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                )
            }
        }
    }
}