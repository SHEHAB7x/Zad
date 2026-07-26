package com.shehab.zad.presentation.screens.qibla

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

    fun startQibla() {
        viewModelScope.launch {
            val location = prayerRepository.getLocation()
            if (location is Resource.Success) {
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
            }

            getQiblaDirection.invoke().collect { resource ->
                when (resource) {
                    is Resource.Loading -> _uiState.update { it.copy(isLoading = true) }
                    is Resource.Success -> _uiState.update {
                        it.copy(isLoading = false, needleRotation = resource.data!!)
                    }
                    is Resource.Error   -> _uiState.update {
                        it.copy(isLoading = false, error = resource.message)
                    }
                }
            }
        }
    }
}