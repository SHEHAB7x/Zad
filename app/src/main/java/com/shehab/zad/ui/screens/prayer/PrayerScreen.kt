package com.shehab.zad.ui.screens.prayer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.shehab.zad.ui.theme.ZadTheme
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun PrayerScreen(
    uiState : PrayerUiState
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        PrayerHeader()

        uiState.nextPrayerRow?.let { next ->
            NextPrayerCard(
                nextPrayerName   = next.nameAr,
                nextPrayerNameEn = next.nameEn,
                nextPrayerTime   = next.time,
                timeUntilNext    = uiState.timeUntilNext ?: ""
            )

            Spacer(modifier = Modifier.height(16.dp))

            PrayerList(prayers = uiState.prayerRows)
        }
    }
}

@Composable
fun PrayerRoute(
    viewmodel: PrayerViewModel = hiltViewModel()
){
    val uiState by viewmodel.uiState.collectAsState()
    PrayerScreen(
        uiState = uiState
    )
}

@Composable
fun PrayerHeader(
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Text(
                    text = "مواقيت الصلاة",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "${LocalDate.now()}",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "القاهرة",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                )
            }


        }

        LazyRow (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            items(dateRange()) { date ->
                DateCard(
                    date    = date,
                    isToday = date == LocalDate.now()
                )
            }
        }
    }
}

fun dateRange(): List<LocalDate> {
    val today = LocalDate.now()
    return (-2..2).map { offset -> today.plusDays(offset.toLong()) }
}

@Composable
fun DateCard(
    date: LocalDate,
    isToday: Boolean
) {
    Column(
        modifier = Modifier
                .background(
                    color = if (isToday)
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                    else
                        MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(10.dp)
                )
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text  = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale("ar")),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
        )
        Text(
            text  = date.dayOfMonth.toString(),
            style = MaterialTheme.typography.titleMedium,
            color = if (isToday)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun NextPrayerCard(
    nextPrayerName: String,
    nextPrayerTime: String,
    nextPrayerNameEn: String,
    timeUntilNext: String
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(0.4.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f))
    ){
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f)
            ) {
                Text(
                    text = "الصلاة القادمة",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text  = nextPrayerName,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text  = nextPrayerTime,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text  = timeUntilNext,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}

@Composable
fun PrayerListItem(
    nameAr: String,
    nameEn: String,
    time: String,
    status: PrayerStatus,
    icon: PrayerIcon
){
    val isActive = status == PrayerStatus.NEXT

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (isActive)
                    MaterialTheme.colorScheme.surface
                else
                    MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Icon circle
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(
                    color = if (isActive)
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                    else
                        MaterialTheme.colorScheme.surface,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon.toImageVector(),
                contentDescription = null,
                tint = if (isActive)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                modifier = Modifier.size(16.dp)
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = nameAr,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = nameEn,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            )
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = time,
                style = MaterialTheme.typography.titleMedium,
                color = if (isActive)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            )

            val (badgeText, badgeColor) = when (status) {
                PrayerStatus.DONE -> "انتهى" to MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                PrayerStatus.NEXT -> "قادمة" to MaterialTheme.colorScheme.primary
                PrayerStatus.UPCOMING -> "لاحقاً" to MaterialTheme.colorScheme.secondary
            }

            Surface(
                shape = RoundedCornerShape(10.dp),
                color = badgeColor.copy(alpha = 0.15f)
            ) {
                Text(
                    text = badgeText,
                    style = MaterialTheme.typography.labelSmall,
                    color = badgeColor,
                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                )
            }
        }
    }
}

@Composable
fun PrayerList(prayers: List<PrayerRowData>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        prayers.forEach { prayer ->
            PrayerListItem(
                nameAr = prayer.nameAr,
                nameEn = prayer.nameEn,
                time = prayer.time,
                status = prayer.status,
                icon = prayer.icon
            )
        }
    }
}

fun PrayerIcon.toImageVector(): ImageVector = when (this) {
    PrayerIcon.FAJR    -> Icons.Default.NightsStay
    PrayerIcon.SUNRISE -> Icons.Default.WbSunny
    PrayerIcon.DHUHR   -> Icons.Default.LightMode
    PrayerIcon.ASR     -> Icons.Default.WbTwilight
    PrayerIcon.MAGHRIB -> Icons.Default.Brightness4
    PrayerIcon.ISHA    -> Icons.Default.DarkMode
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrayerScreenPreview(){
    ZadTheme {
       PrayerScreen(
           uiState = previewState
       )
    }
}

private val previewState = PrayerUiState(
    timeUntilNext = "02:15",
    nextPrayerRow = PrayerRowData(
        nameAr = "العصر",
        nameEn = "Asr",
        time = "4:30 PM",
        rawTime = LocalTime.of(16, 30),
        status = PrayerStatus.NEXT,
        icon = PrayerIcon.ASR
    ),
    prayerRows = listOf(
        PrayerRowData(
            nameAr = "الفجر",
            nameEn = "Fajr",
            time = "4:10 AM",
            rawTime = LocalTime.of(4, 10),
            status = PrayerStatus.DONE,
            icon = PrayerIcon.FAJR
        ),
        PrayerRowData(
            nameAr = "الشروق",
            nameEn = "Sunrise",
            time = "5:45 AM",
            rawTime = LocalTime.of(5, 45),
            status = PrayerStatus.DONE,
            icon = PrayerIcon.SUNRISE
        ),
        PrayerRowData(
            nameAr = "الظهر",
            nameEn = "Dhuhr",
            time = "12:00 PM",
            rawTime = LocalTime.NOON,
            status = PrayerStatus.DONE,
            icon = PrayerIcon.DHUHR
        ),
        PrayerRowData(
            nameAr = "العصر",
            nameEn = "Asr",
            time = "4:30 PM",
            rawTime = LocalTime.of(16, 30),
            status = PrayerStatus.NEXT,
            icon = PrayerIcon.ASR
        ),
        PrayerRowData(
            nameAr = "المغرب",
            nameEn = "Maghrib",
            time = "7:00 PM",
            rawTime = LocalTime.of(19, 0),
            status = PrayerStatus.UPCOMING,
            icon = PrayerIcon.MAGHRIB
        ),
        PrayerRowData(
            nameAr = "العشاء",
            nameEn = "Isha",
            time = "8:30 PM",
            rawTime = LocalTime.of(20, 30),
            status = PrayerStatus.UPCOMING,
            icon = PrayerIcon.ISHA
        )
    )
)

