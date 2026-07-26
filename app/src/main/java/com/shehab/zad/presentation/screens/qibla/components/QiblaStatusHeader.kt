package com.shehab.zad.presentation.screens.qibla.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.shehab.zad.presentation.theme.ZadGold
import com.shehab.zad.presentation.theme.ZadText
import com.shehab.zad.presentation.theme.ZadTextSecondary

@Composable
fun QiblaStatusHeader(
    city: String,
    bearing: Float,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column {

            Text(
                text = "اتجاه القبلة",
                style = MaterialTheme.typography.titleMedium,
                color = ZadText
            )

            Text(
                text = city,
                style = MaterialTheme.typography.bodySmall,
                color = ZadTextSecondary
            )

        }

        Column(
            horizontalAlignment = Alignment.End
        ) {

            Text(
                text = "%.1f°".format(bearing),
                style = MaterialTheme.typography.titleMedium,
                color = ZadGold
            )

            Text(
                text = "من الشمال",
                style = MaterialTheme.typography.bodySmall,
                color = ZadTextSecondary
            )

        }

    }

}