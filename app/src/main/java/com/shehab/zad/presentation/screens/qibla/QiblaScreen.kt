package com.shehab.zad.presentation.screens.qibla

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.shehab.zad.presentation.screens.qibla.components.QiblaCompass
import com.shehab.zad.presentation.screens.qibla.components.QiblaInfoCard
import com.shehab.zad.presentation.screens.qibla.components.QiblaStatusHeader
import com.shehab.zad.presentation.theme.ZadTheme

@Composable
fun QiblaScreen(uiState: QiblaUiState) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(Modifier.height(24.dp))

            QiblaStatusHeader(
                modifier = Modifier.fillMaxWidth(),
                city     = uiState.cityName ?: "جارٍ التحديد...",
                bearing  = uiState.qiblaBearing
            )

            Spacer(Modifier.height(40.dp))

            QiblaCompass(
                modifier = Modifier.size(280.dp),
                rotation = uiState.needleRotation
            )

            Spacer(Modifier.height(40.dp))

            QiblaInfoCard(
                modifier    = Modifier.fillMaxWidth(),
                direction   = uiState.qiblaBearing,
                distanceKm  = uiState.distanceKm.toInt(),
                isAccurate  = uiState.isAccurate
            )
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun QiblaRoute(viewModel: QiblaViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val locationPermission = rememberPermissionState(
        Manifest.permission.ACCESS_FINE_LOCATION
    ) { isGranted ->
        if (isGranted) viewModel.startQibla()
    }

    LaunchedEffect(Unit) {
        if (locationPermission.status.isGranted) {
            viewModel.startQibla()
        } else {
            locationPermission.launchPermissionRequest()
        }
    }

    QiblaScreen(uiState = uiState)
}

@Preview(showBackground = true)
@Composable
private fun PreviewQiblaScreen() {
    ZadTheme {
        QiblaScreen(
            uiState = QiblaUiState(
                qiblaBearing  = 134.2f,
                needleRotation = 134.2f,
                distanceKm    = 2131.0,
                cityName      = "القاهرة، مصر",
                isAccurate    = true,
                isLoading     = false
            )
        )
    }
}