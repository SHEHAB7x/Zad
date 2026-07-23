package com.shehab.zad.presentation.mapper

import com.shehab.zad.domain.model.PrayerTimes
import com.shehab.zad.presentation.screens.prayer.PrayerIcon
import com.shehab.zad.presentation.screens.prayer.PrayerRowData
import com.shehab.zad.presentation.screens.prayer.PrayerStatus
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun PrayerTimes.toPrayerRowList(): List<PrayerRowData> {
    val now = LocalTime.now()
    val formatter = DateTimeFormatter.ofPattern("HH:mm")

    data class RawPrayer(val nameAr: String, val nameEn: String, val time: LocalTime, val icon: PrayerIcon)

    val raw = listOf(
        RawPrayer("الفجر",   "Fajr",    fajr,    PrayerIcon.FAJR),
        RawPrayer("الشروق", "Sunrise", sunrise, PrayerIcon.SUNRISE),
        RawPrayer("الظهر",   "Dhuhr",   dhuhr,   PrayerIcon.DHUHR),
        RawPrayer("العصر",   "Asr",     asr,     PrayerIcon.ASR),
        RawPrayer("المغرب", "Maghrib", maghrib, PrayerIcon.MAGHRIB),
        RawPrayer("العشاء", "Isha",    isha,    PrayerIcon.ISHA)
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