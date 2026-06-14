package com.shehab.zad.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reading_progress")
data class ReadingProgressEntity(
    @PrimaryKey val id: Int = 0,
    val lastPageNumber: Int,
    val updatedAt: Long
)