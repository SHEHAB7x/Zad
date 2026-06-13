package com.shehab.zad.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "surah_table")
data class SurahEntity(
    @PrimaryKey
    val id: Int,
    val nameAr: String,
    val nameEn: String,
    val nameTransliteration: String,
    val numberOfAyahs: Int,
    val revelationType: String,
)