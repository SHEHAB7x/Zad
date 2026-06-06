package com.example.zad.domain.model

data class Surah(
    val id: Int,
    val nameAr: String,
    val nameEn: String,
    val nameTransliteration: String,
    val numberOfAyahs: Int,
    val revelationType: String,
    )
