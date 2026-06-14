package com.shehab.zad.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val pageNumber: Int,
    val surahNumber: Int,
    val ayahNumber: Int,
    val createAt: Long
)
