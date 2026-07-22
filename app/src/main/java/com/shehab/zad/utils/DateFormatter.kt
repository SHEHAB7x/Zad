package com.shehab.zad.utils

import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.chrono.HijrahDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import java.util.Locale

object DateFormatter {
    fun buildDateText(): String {
        val gregorian = LocalDate.now()
        val hijri = HijrahDate.from(gregorian)

        val gregorianFormatted = gregorian.format(
            DateTimeFormatter.ofPattern("EEEE d MMMM yyyy,", Locale("ar"))
        )

        val hijriDay = hijri.get(ChronoField.DAY_OF_MONTH)
        val hijriMonth = hijri.get(ChronoField.MONTH_OF_YEAR)
        val hijriYear = hijri.get(ChronoField.YEAR)

        val hijriMonthName = HijriMonthNames[hijriMonth - 1]
        return "$gregorianFormatted | $hijriDay $hijriMonthName $hijriYear هـ "
    }
    private val HijriMonthNames = listOf(
        "محرم", "صفر", "ربيع الأول", "ربيع الثاني",
        "جمادى الأولى", "جمادى الثانية", "رجب", "شعبان",
        "رمضان", "شوال", "ذو القعدة", "ذو الحجة"
    )

    fun calculateTimeLeft(nextTime: LocalTime): String {
        val now = LocalTime.now()
        val duration = if (nextTime.isAfter(now))
            Duration.between(now, nextTime)
        else
            Duration.between(now, LocalTime.MAX)
                .plus(Duration.between(LocalTime.MIN, nextTime))

        val hours   = duration.toHours()
        val minutes = duration.toMinutes() % 60
        return "بعد $hours ساعة و$minutes دقيقة"
    }
}