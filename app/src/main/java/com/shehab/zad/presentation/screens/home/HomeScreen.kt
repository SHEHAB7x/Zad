package com.shehab.zad.presentation.screens.home

import android.Manifest
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.shehab.zad.R
import com.shehab.zad.presentation.screens.home.components.AzkarCard
import com.shehab.zad.presentation.screens.home.components.GreetingHeader
import com.shehab.zad.presentation.screens.home.components.PrayerTimeCard
import com.shehab.zad.presentation.screens.home.components.QuickAccessGrid

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToQuran: () -> Unit,
    onNavigateToQibla: () -> Unit,
    onNavigateToPrayer: () -> Unit,
    onNavigateToAzkar: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val locationPermission = rememberPermissionState(
        Manifest.permission.ACCESS_FINE_LOCATION
    ){ isGranted ->
        if (isGranted) viewModel.loadHomeData()
    }

    LaunchedEffect(Unit) {
        if (locationPermission.status.isGranted) {
            viewModel.loadHomeData()
        } else {
            locationPermission.launchPermissionRequest()
        }
    }
    HomeScreen(
        uiState            = uiState,
        onNavigateToQuran  = onNavigateToQuran,
        onNavigateToQibla  = onNavigateToQibla,
        onNavigateToPrayer = onNavigateToPrayer,
        onNavigateToAzkar  = onNavigateToAzkar)
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onNavigateToQuran: () -> Unit = {},
    onNavigateToQibla: () -> Unit = {},
    onNavigateToPrayer: () -> Unit = {},
    onNavigateToAzkar: () -> Unit = {}
) {
    val backgroundImage =
        if (uiState.isDay) {
            R.drawable.home_day
        } else {
            R.drawable.home_night
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        GreetingHeader(
            greeting = uiState.greeting,
            dateText = uiState.dateText,
            backgroundImage = backgroundImage
        )

        PrayerTimeCard(
            nextPrayerName = uiState.nextPrayerRow?.nameAr.orEmpty(),
            nextPrayerNameEn = uiState.nextPrayerRow?.nameEn.orEmpty(),
            nextPrayerTime = uiState.nextPrayerRow?.time.orEmpty(),
            timeUntilNextPrayer = uiState.timeUntilNext.orEmpty(),
            prayerTimes = uiState.prayerRows
        )

        SectionLabel(text = "الوصول السريع")

        QuickAccessGrid(
            onItemClick = {item ->
                when (item.titleAr) {
                    "القرآن الكريم" -> onNavigateToQuran()
                    "اتجاه القبلة" -> onNavigateToQibla()
                    "الأذكار"       -> onNavigateToAzkar()
                    "مواقيت الصلاة" -> onNavigateToPrayer()
                }
            }
        )

        SectionLabel(
            text = "أذكار اليوم",
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        AzkarCard(
            title = "أذكار الصباح",
            subtitle = "Morning azkar · 12 remaining",
            onClick = onNavigateToAzkar,
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