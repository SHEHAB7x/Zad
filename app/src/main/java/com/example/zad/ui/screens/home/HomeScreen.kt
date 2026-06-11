package com.example.zad.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zad.ui.screens.home.components.AzkarCard
import com.example.zad.ui.screens.home.components.GreetingHeader
import com.example.zad.ui.screens.home.components.PrayerTimeCard
import com.example.zad.ui.screens.home.components.QuickAccessGrid
import com.example.zad.ui.theme.ZadTheme

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        GreetingHeader(
            greeting = "Good Morning",
            dateText = "الثلاثاء ذو الحجة 1446"
        )  // هنا المفروض view model هو اللي بيجيب الداتا

        PrayerTimeCard()

        SectionLabel(text = "Quick Access")

        QuickAccessGrid(
            onItemClick = { item ->

            }
        )

        SectionLabel(text = "AZKAR OF THE DAY", modifier = Modifier.padding(horizontal = 16.dp))

        AzkarCard(
            title    = "أذكار الصباح",
            subtitle = "Morning azkar · 12 remaining",
            onClick  = {

            },
            modifier = Modifier.padding(horizontal = 16.dp)
        )


    }
}

@Composable
fun SectionLabel(
    text: String,
    modifier: Modifier = Modifier
    ) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
        modifier = modifier.padding(horizontal = 16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    ZadTheme {
        HomeScreen()
    }
}