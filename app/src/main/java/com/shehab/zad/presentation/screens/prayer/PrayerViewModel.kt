package com.shehab.zad.presentation.screens.prayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shehab.zad.domain.usecase.CalculatePrayerTimesUseCase
import com.shehab.zad.domain.usecase.GetCityNameUseCase
import com.shehab.zad.domain.utils.Resource
import com.shehab.zad.presentation.mapper.toPrayerRowList
import com.shehab.zad.utils.DateFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class PrayerViewModel @Inject constructor(
    private val calculatePrayerTimes: CalculatePrayerTimesUseCase,
    private val getCityName: GetCityNameUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PrayerUiState())
    val uiState: StateFlow<PrayerUiState> = _uiState.asStateFlow()

    fun getPrayerTimes() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val prayerResult = async { calculatePrayerTimes(LocalDate.now()) }
            val cityResult   = async { getCityName() }

            val prayerTimes = prayerResult.await()
            val cityName = cityResult.await()
            val dateText = DateFormatter.buildDateText()

            when (prayerTimes) {
                is Resource.Success -> {
                    val prayerTimes = prayerTimes.data!!
                    val prayerRows  = prayerTimes.toPrayerRowList()
                    val nextRow     = prayerRows.firstOrNull { it.status == PrayerStatus.NEXT }
                    val timeLeft    = nextRow?.let { DateFormatter.calculateTimeLeft(it.rawTime) }

                    _uiState.update {
                        it.copy(
                            isLoading      = false,
                            prayerRows     = prayerRows,
                            nextPrayerRow  = nextRow,
                            timeUntilNext  = timeLeft,
                            cityName      = cityName ?: "موقع غير معروف",
                            dateText      = dateText,
                            error          = null
                        )
                    }
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            cityName = cityName,
                            dateText = dateText,
                            error = prayerTimes.message
                        )
                    }
                }
                is Resource.Loading -> Unit
            }
        }
    }

}