package com.shehab.zad.presentation.screens.qibla

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shehab.zad.domain.repository.PrayerRepository
import com.shehab.zad.domain.usecase.GetCityNameUseCase
import com.shehab.zad.domain.usecase.GetQiblaDirectionUseCase
import com.shehab.zad.domain.utils.Resource
import com.shehab.zad.utils.QiblaCalculator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QiblaViewModel @Inject constructor(
    private val getQiblaDirection: GetQiblaDirectionUseCase,
    private val getCityName: GetCityNameUseCase,
    private val prayerRepository: PrayerRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(QiblaUiState())
    val uiState: StateFlow<QiblaUiState> = _uiState.asStateFlow()

    private var isStarted = false

    fun startQibla() {
        if (isStarted) return
        isStarted = true
        viewModelScope.launch {
            Log.d("QiblaViewModel", "startQibla called")
            val location = prayerRepository.getLocation()
            Log.d("QiblaViewModel", "location result: $location")

            if (location is Resource.Error) {
                _uiState.update { it.copy(error = "تعذر الحصول على الموقع") }
                return@launch
            }

            val (lat, lon) = location.data!!
            val bearing  = QiblaCalculator.calculateQiblaDirection(lat, lon)
            val distance = QiblaCalculator.calculateDistanceToMecca(lat, lon)
            val city     = getCityName()

            _uiState.update {
                it.copy(
                    qiblaBearing = bearing,
                    distanceKm   = distance,
                    cityName     = city
                )
            }

            getQiblaDirection.invoke().collect { azimuth ->
                Log.d("QiblaViewModel", "azimuth received: $azimuth")
                val needleRotation = (bearing - azimuth + 360) % 360
                Log.d("QiblaViewModel", "needleRotation: $needleRotation")
                _uiState.update {
                    it.copy(
                        needleRotation = needleRotation,
                        isLoading = false
                    )
                }
            }
        }
    }
}