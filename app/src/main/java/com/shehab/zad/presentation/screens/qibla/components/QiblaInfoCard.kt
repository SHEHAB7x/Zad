package com.shehab.zad.presentation.screens.qibla.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.shehab.zad.presentation.theme.ZadGold
import com.shehab.zad.presentation.theme.ZadGreen
import com.shehab.zad.presentation.theme.ZadSurface
import com.shehab.zad.presentation.theme.ZadTextSecondary

@Composable
fun QiblaInfoCard(
    direction: Float,
    distanceKm: Int,
    isAccurate: Boolean,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(20.dp)
    ) {

        InfoRow(
            title = "اتجاه القبلة",
            value = "%.1f°".format(direction)
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp),
            color = ZadTextSecondary.copy(alpha = .12f)
        )

        InfoRow(
            title = "المسافة إلى مكة",
            value = "$distanceKm كم"
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp),
            color = ZadTextSecondary.copy(alpha = .12f)
        )

        AccuracyRow(isAccurate)

    }
}

@Composable
private fun InfoRow(
    title: String,
    value: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = value,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium
        )

    }

}

@Composable
private fun AccuracyRow(
    accurate: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(end = 10.dp)
                .clip(androidx.compose.foundation.shape.CircleShape)
                .background(
                    if (accurate) ZadGreen else MaterialTheme.colorScheme.error
                )
                .padding(5.dp)
        )

        Text(
            text = if (accurate) "البوصلة دقيقة" else "تحتاج معايرة",
            color = if (accurate) ZadGreen else MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}