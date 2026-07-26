package com.shehab.zad.presentation.screens.qibla.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.shehab.zad.presentation.theme.ZadGold
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CompassTicks(
    modifier: Modifier = Modifier
) {

    Canvas(modifier = modifier) {

        val radius = size.minDimension / 2f

        val center = Offset(
            x = size.width / 2,
            y = size.height / 2
        )

        repeat(72) { index ->

            val angle = Math.toRadians((index * 5).toDouble() - 90)

            val isMajor = index % 3 == 0

            val tickLength =
                if (isMajor) 14.dp.toPx()
                else 7.dp.toPx()

            val stroke =
                if (isMajor) 2.dp.toPx()
                else 1.dp.toPx()

            val start = Offset(
                x = center.x + (radius - tickLength - 10.dp.toPx()) * cos(angle).toFloat(),
                y = center.y + (radius - tickLength - 10.dp.toPx()) * sin(angle).toFloat()
            )

            val end = Offset(
                x = center.x + (radius - 10.dp.toPx()) * cos(angle).toFloat(),
                y = center.y + (radius - 10.dp.toPx()) * sin(angle).toFloat()
            )

            drawLine(
                color = ZadGold.copy(alpha = .45f),
                start = start,
                end = end,
                strokeWidth = stroke,
                cap = StrokeCap.Round
            )
        }
    }
}