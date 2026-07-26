package com.shehab.zad.presentation.screens.qibla.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.shehab.zad.presentation.theme.ZadBackground
import com.shehab.zad.presentation.theme.ZadGold

@Composable
fun CompassCenter(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .size(18.dp)
            .shadow(
                elevation = 6.dp,
                shape = CircleShape,
                clip = false
            )
            .background(
                color = ZadGold,
                shape = CircleShape
            )
            .border(
                width = 2.dp,
                color = ZadBackground,
                shape = CircleShape
            )
    )

}