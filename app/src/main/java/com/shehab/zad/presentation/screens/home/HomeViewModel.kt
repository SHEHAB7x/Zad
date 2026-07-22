package com.shehab.zad.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shehab.zad.domain.usecase.CalculatePrayerTimesUseCase
import com.shehab.zad.domain.usecase.GetCityNameUseCase
import com.shehab.zad.domain.utils.Resource
import com.shehab.zad.presentation.mapper.toPrayerRowList
import com.shehab.zad.presentation.screens.prayer.PrayerStatus
import com.shehab.zad.utils.DateFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val calculatePrayerTimes: CalculatePrayerTimesUseCase,
    private val getCityName: GetCityNameUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun loadHomeData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val dateText = DateFormatter.buildDateText()
            val isDay = isDay()
            val greeting = buildGreeting()

            val prayerResult = async { calculatePrayerTimes(LocalDate.now())}

            when (val result = prayerResult.await()) {
                is Resource.Success -> {
                    val prayerRows = result.data!!.toPrayerRowList()
                    val nextRow = prayerRows.firstOrNull { it.status == PrayerStatus.NEXT }
                    val timeLeft = nextRow?.let { DateFormatter.calculateTimeLeft(it.rawTime) }

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            prayerRows = prayerRows,
                            dateText = dateText,
                            nextPrayerRow = nextRow,
                            timeUntilNext = timeLeft,
                            greeting = greeting,
                            error = null,
                            isDay = isDay,
                        )
                    }
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            dateText = dateText,
                            greeting = greeting,
                            isDay = isDay,
                            error = result.message
                        )
                    }
                }
                is Resource.Loading -> Unit
            }
        }
    }

    private fun buildGreeting(): String {
        return when (LocalTime.now().hour) {
            in 5..11  -> "صباح الخير"
            in 12..17 -> "مساء الخير"
            else      -> "مساء النور"
        }
    }

    private fun isDay(): Boolean{
        val hour = LocalTime.now().hour
        return hour in 6..18
    }

}