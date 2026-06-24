package com.shehab.zad.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shehab.zad.ui.theme.ZadTheme

data class PrayerTime(
    val name: String,
    val time: String,
    val isNext: Boolean = false
)

@Composable
fun PrayerTimeCard(
    nextPrayerName: String = "العصر",
    nextPrayerNameEn: String = "Asr next pryer",
    nextPrayerTime: String = "4:32",
    timeUntilNextPrayer: String = "in 1h 23m",
    prayerTimes: List<PrayerTime> = samplePrayerTimes
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            NextPryerSection(
                nameAr       = nextPrayerName,
                nameEn       = nextPrayerNameEn,
                time         = nextPrayerTime,
                timeUntilNextPrayer = timeUntilNextPrayer
            )
            HorizontalDivider(
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                thickness = 1.dp
            )
            AllPryerTimesRow(prayerTimes = prayerTimes)
        }
    }
}

@Composable
fun NextPryerSection(
    nameAr: String,
    nameEn: String,
    time: String,
    timeUntilNextPrayer: String
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column (verticalArrangement = Arrangement.spacedBy(4.dp)){
            Text(
                text = nameAr,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = ("$nameEn · next prayer"),
                color = MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.labelSmall
            )
        }

        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = time,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium
            )
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f)
            ) {
                Text(
                    text = timeUntilNextPrayer,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
            }
        }
    }
}

@Composable
fun AllPryerTimesRow(prayerTimes: List<PrayerTime>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        prayerTimes.forEach { prayer ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = (prayer.name),
                    color = MaterialTheme.colorScheme.outline,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = (prayer.time),
                    color = if (prayer.isNext)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

val samplePrayerTimes = listOf(
    PrayerTime("Fajr",    "4:12"),
    PrayerTime("Dhuhr",   "12:02"),
    PrayerTime("Asr",     "4:32",  isNext = true),
    PrayerTime("Maghrib", "6:51"),
    PrayerTime("Isha",    "8:21")
)

@Preview
@Composable
fun PrayerTimeCardPreview(){
    ZadTheme {
        PrayerTimeCard()
    }
}