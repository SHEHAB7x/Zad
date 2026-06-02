package com.example.zad.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = ZadGold,
    onPrimary = ZadBackground,
    background = ZadBackground,
    onBackground = ZadText,
    surface = ZadSurface,
    onSurface = ZadText,
    secondary = ZadGreen,
    onSecondary = ZadBackground
)

private val LightColorScheme = lightColorScheme(
    primary = ZadLightGold,
    onPrimary = ZadLightBackground,
    background = ZadLightBackground,
    onBackground = ZadLightText,
    surface = ZadLightSurface,
    onSurface = ZadLightText,
    secondary = ZadLightGreen,
    onSecondary = ZadLightBackground
)

@Composable
fun ZadTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = ZadTypography,
        content = content
    )
}
