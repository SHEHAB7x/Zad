package com.shehab.zad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.shehab.zad.ui.navigation.ZadBottomBar
import com.shehab.zad.ui.navigation.ZadNavGraph
import com.shehab.zad.ui.theme.ZadTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZadTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        ZadBottomBar(navController = navController)
                    },

                ) { paddingValues ->
                    ZadNavGraph(
                        navController = navController,
                        paddingValues = paddingValues
                    )
                }

            }
        }
    }
}
