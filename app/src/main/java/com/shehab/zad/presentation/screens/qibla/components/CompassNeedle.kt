package com.shehab.zad.presentation.screens.qibla.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.shehab.zad.presentation.theme.ZadGold
import com.shehab.zad.presentation.theme.ZadGreen
import kotlin.math.min

@Composable
fun CompassNeedle(
    modifier: Modifier = Modifier,
    rotation: Float = 0f
) {

    Canvas(
        modifier = modifier.graphicsLayer {
            rotationZ = rotation
        }
    ) {

        val center = Offset(size.width / 2f, size.height / 2f)

        val radius = min(size.width, size.height) / 2f

        val length = radius * .62f

        val width = length * .14f

        //--------------------
        // Gold Head
        //--------------------

        val head = Path().apply {

            moveTo(center.x, center.y - length)

            lineTo(center.x - width, center.y)

            lineTo(center.x, center.y + width * .45f)

            lineTo(center.x + width, center.y)

            close()
        }

        drawPath(
            path = head,
            color = ZadGold
        )

        //--------------------
        // Green Tail
        //--------------------

        val tail = Path().apply {

            moveTo(center.x, center.y + length * .55f)

            lineTo(center.x - width * .75f, center.y)

            lineTo(center.x, center.y - width * .25f)

            lineTo(center.x + width * .75f, center.y)

            close()
        }

        drawPath(
            path = tail,
            color = ZadGreen
        )

        //--------------------
        // Center Spine
        //--------------------

        drawLine(
            color = ZadGold.copy(alpha = .35f),
            start = Offset(center.x, center.y - length),
            end = Offset(center.x, center.y + length * .55f),
            strokeWidth = 2.dp.toPx()
        )
    }
}