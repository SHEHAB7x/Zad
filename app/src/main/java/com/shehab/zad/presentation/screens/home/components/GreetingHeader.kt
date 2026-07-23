package com.shehab.zad.presentation.screens.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun GreetingHeader(
    greeting: String,
    dateText: String,
    @DrawableRes backgroundImage: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    ) {

        Image(
            painter = painterResource(backgroundImage),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .matchParentSize()
                .statusBarsPadding()
                .padding(20.dp),
            verticalArrangement = Arrangement.Bottom
        ) {

            Text(
                text = "السلام عليكم",
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = greeting,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = dateText,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.85f),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}