package com.shehab.zad.domain.model

data class QuranWord(
    val id: Int,
    val pageNumber: Int,
    val lineNumber: Int,
    val surahNumber: Int,
    val ayahNumber: Int,
    val text: String,
    val isAyahEnd: Boolean,
    val lineType: LineType
)

enum class LineType { AYAH, SURAH_HEADER, BASMALA }

data class MushafLine(
    val lineNumber: Int,
    val words: List<QuranWord>,
    val lineType: LineType
)

data class QuranPage(
    val pageNumber: Int,
    val lines: List<MushafLine>
)
