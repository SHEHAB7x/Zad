package com.shehab.zad.ui.screens.prayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shehab.zad.domain.model.PrayerTimes
import com.shehab.zad.domain.usecase.CalculatePrayerTimesUseCase
import com.shehab.zad.domain.usecase.GetCityNameUseCase
import com.shehab.zad.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.chrono.HijrahDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import java.util.Locale
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
            val dateText = buildDateText()

            when (prayerTimes) {
                is Resource.Success -> {
                    val prayerTimes = prayerTimes.data!!
                    val prayerRows  = buildPrayerRows(prayerTimes)
                    val nextRow     = prayerRows.firstOrNull { it.status == PrayerStatus.NEXT }
                    val timeLeft    = nextRow?.let { calculateTimeLeft(it.rawTime) }

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

    private fun buildDateText(): String {
        val gregorian = LocalDate.now()
        val hijri = HijrahDate.from(gregorian)

        val gregorianFormatted = gregorian.format(
            DateTimeFormatter.ofPattern("EEEE d MMMM yyyy", Locale("ar"))
        )

        val hijriDay = hijri.get(ChronoField.DAY_OF_MONTH)
        val hijriMonth = hijri.get(ChronoField.MONTH_OF_YEAR)
        val hijriYear  = hijri.get(ChronoField.YEAR)

        val hijriMonthName = HijriMonthNames[hijriMonth - 1]

        return "$gregorianFormatted | $hijriDay $hijriMonthName $hijriYear هـ"
    }

    private val HijriMonthNames = listOf(
        "محرم", "صفر", "ربيع الأول", "ربيع الثاني",
        "جمادى الأولى", "جمادى الثانية", "رجب", "شعبان",
        "رمضان", "شوال", "ذو القعدة", "ذو الحجة"
    )
}