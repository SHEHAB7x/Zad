package com.shehab.zad.presentation.screens.qibla.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.shehab.zad.presentation.theme.ZadBackground
import com.shehab.zad.presentation.theme.ZadGold
import com.shehab.zad.presentation.theme.ZadSurface

@Composable
fun CompassBackground(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit = {}
) {

    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        val innerSize = maxWidth * 0.82f

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.background)
                .border(
                    width = 2.dp,
                    color = ZadSurface,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier
                    .size(innerSize)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface)
                    .border(
                        width = 1.dp,
                        color = ZadGold.copy(alpha = 0.25f),
                        shape = CircleShape
                    )
                    .then(
                        Modifier
                    ),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
        }
    }
}