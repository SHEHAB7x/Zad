package com.shehab.zad.domain.model

data class Ayah(
    val id: Int,
    val ayahNumber: Int,
    val surahNumber: Int,
    val ayahText: String,
    val translation: String? = null
)
