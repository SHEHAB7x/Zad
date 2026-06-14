package com.shehab.zad.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quran_words")
data class QuranWordEntity(
    @PrimaryKey val id: Int,
    val pageNumber: Int,
    val lineNumber: Int,
    val surahNumber: Int,
    val ayahNumber: Int,
    val text: String,
    val isAyahEnd: Boolean,
    val lineType: String
)