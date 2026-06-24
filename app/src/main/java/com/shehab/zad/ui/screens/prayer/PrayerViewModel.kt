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
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class PrayerViewModel @Inject constructor(
    private val useCase: CalculatePrayerTimesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PrayerUiState())
    val uiState: StateFlow<PrayerUiState> = _uiState.asStateFlow()


    fun getPrayerTimes() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            when (val result = useCase.invoke(LocalDate.now())) {
                is Resource.Success -> {
                    val prayerTimes = result.data!!
                    val prayerRows  = buildPrayerRows(prayerTimes)
                    val nextRow     = prayerRows.firstOrNull { it.status == PrayerStatus.NEXT }
                    val timeLeft    = nextRow?.let { calculateTimeLeft(it.rawTime) }

                    _uiState.update {
                        it.copy(
                            isLoading      = false,
                            prayerTimes    = prayerTimes,
                            prayerRows     = prayerRows,
                            nextPrayerRow  = nextRow,
                            timeUntilNext  = timeLeft,
                            error          = null
                        )
                    }
                }
                is Resource.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = result.message) }
                }
                is Resource.Loading -> Unit
            }
        }
    }

    private fun buildPrayerRows(prayerTimes: PrayerTimes): List<PrayerRowData> {
        val now = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("h:mm a")

        data class RawPrayer(val nameAr: String, val nameEn: String, val time: LocalTime, val icon: PrayerIcon)

        val raw = listOf(
            RawPrayer("الفجر",   "Fajr",    prayerTimes.fajr,    PrayerIcon.FAJR),
            RawPrayer("الشروق", "Sunrise", prayerTimes.sunrise, PrayerIcon.SUNRISE),
            RawPrayer("الظهر",   "Dhuhr",   prayerTimes.dhuhr,   PrayerIcon.DHUHR),
            RawPrayer("العصر",   "Asr",     prayerTimes.asr,     PrayerIcon.ASR),
            RawPrayer("المغرب", "Maghrib", prayerTimes.maghrib, PrayerIcon.MAGHRIB),
            RawPrayer("العشاء", "Isha",    prayerTimes.isha,    PrayerIcon.ISHA)
        )

        val nextIndex = raw.indexOfFirst { it.time.isAfter(now) }
            .let { if (it == -1) 0 else it }

        return raw.mapIndexed { index, p ->
            val status = when {
                index == nextIndex      -> PrayerStatus.NEXT
                p.time.isBefore(now)    -> PrayerStatus.DONE
                else                     -> PrayerStatus.UPCOMING
            }
            PrayerRowData(
                nameAr  = p.nameAr,
                nameEn  = p.nameEn,
                time    = p.time.format(formatter),
                rawTime = p.time,
                status  = status,
                icon    = p.icon
            )
        }
    }

    private fun calculateTimeLeft(nextTime: LocalTime): String {
        val now = LocalTime.now()
        val duration = if (nextTime.isAfter(now))
            Duration.between(now, nextTime)
        else
            Duration.between(now, LocalTime.MAX).plus(Duration.between(LocalTime.MIN, nextTime))

        val hours   = duration.toHours()
        val minutes = duration.toMinutes() % 60
        return "بعد $hours ساعة و$minutes دقيقة"
    }
}