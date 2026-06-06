package com.example.zad.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.zad.data.local.dao.SurahDao
import com.example.zad.data.local.entity.SurahEntity

@Database(
    entities = [
        SurahEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ZadDatabase: RoomDatabase() {
    abstract fun surahDao(): SurahDao
}