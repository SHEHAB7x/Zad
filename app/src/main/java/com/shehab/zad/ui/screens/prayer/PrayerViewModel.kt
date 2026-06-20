package com.shehab.zad.ui.screens.prayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shehab.zad.domain.model.PrayerTimes
import com.shehab.zad.domain.usecase.CalculatePrayerTimesUseCase
import com.shehab.zad.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class PrayerViewModel @Inject constructor(
    private val useCase: CalculatePrayerTimesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(PrayerUiState())
    val uiState : StateFlow<PrayerUiState> = _uiState.asStateFlow()

    init {
        getPrayerTimes()
    }

    fun getPrayerTimes() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val result = useCase.invoke(LocalDate.now())

            when (result){
                is Resource.Success -> {
                    val prayerTimes = result.data!!
                    val (nextName, timeLeft) = calculateNextPrayer(prayerTimes)

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            prayerTimes = prayerTimes,
                            nextPrayer = nextName,
                            timeUntilNext = timeLeft,
                            error = null
                        )
                    }

                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
                is Resource.Loading -> Unit
            }
        }
    }

    private fun calculateNextPrayer(prayerTimes: PrayerTimes): Pair<String, String> {
        val now = LocalTime.now()

        val prayers = listOf(
            "الفجر" to prayerTimes.fajr,
            "الظهر"   to prayerTimes.dhuhr,
            "العصر"   to prayerTimes.asr,
            "المغرب" to prayerTimes.maghrib,
            "العشاء" to prayerTimes.isha
        )

        val next = prayers.firstOrNull { (_, time) -> time.isAfter(now) }
            ?: prayers.first()

        val duration = Duration.between(now, next.second)
        val hours = duration.toHours()
        val minutes = duration.toMinutes() % 60

        val timeLeftText = "بعد $hours ساعة و$minutes دقيقة"

        return next.first to timeLeftText
    }
}