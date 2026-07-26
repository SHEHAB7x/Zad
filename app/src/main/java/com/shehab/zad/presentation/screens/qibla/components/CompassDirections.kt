package com.shehab.zad.presentation.screens.qibla.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shehab.zad.presentation.theme.ZadTextSecondary

@Composable
fun CompassDirections(
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {

        val offset = 12.dp

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                text = "N",
                color = ZadTextSecondary,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = offset)
            )

            Text(
                text = "S",
                color = ZadTextSecondary,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = -offset)
            )

            Text(
                text = "E",
                color = ZadTextSecondary,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .offset(x = -offset)
            )

            Text(
                text = "W",
                color = ZadTextSecondary,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = offset)
            )
        }
    }
}