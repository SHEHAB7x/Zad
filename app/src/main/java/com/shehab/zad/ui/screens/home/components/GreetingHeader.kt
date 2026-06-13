package com.shehab.zad.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shehab.zad.ui.theme.ZadTheme

@Composable
fun GreetingHeader(
    greeting: String = "Good morning",
    dateText: String = "الثلاثاء · 15 ذو الحجة 1446"
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "السلام عليكم",
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = greeting,
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = dateText, // المفروض يكون تاريخ حقيقي بيتغير بتغير التاريخ تلقائي
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelSmall
        )
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingHeaderPreview(){
    ZadTheme {
        GreetingHeader()
    }
}