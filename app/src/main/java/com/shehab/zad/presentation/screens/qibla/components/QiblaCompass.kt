package com.shehab.zad.presentation.screens.qibla.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shehab.zad.presentation.theme.ZadTheme
import androidx.compose.ui.Alignment

@Composable
fun QiblaCompass(
    modifier: Modifier = Modifier,
    rotation: Float = 0f
) {
    val animatedRotation by animateFloatAsState(
        targetValue    = rotation,
        animationSpec  = tween(durationMillis = 300, easing = LinearEasing),
        label          = "compassRotation"
    )

    CompassBackground(
        modifier = modifier
    ) {
        CompassTicks(modifier = Modifier.fillMaxSize())
        CompassDirections()
        CompassNeedle(modifier = Modifier.fillMaxSize(), rotation = animatedRotation)
        CompassCenter(modifier = Modifier.align(Alignment.Center))
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun QiblaCompassPreview() {

    ZadTheme {
        QiblaCompass(
            modifier = Modifier.size(280.dp)
        )
    }
}