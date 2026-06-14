package com.shehab.zad.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.shehab.zad.data.local.entity.QuranWordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuranWordDao {
    @Query("SELECT * FROM quran_words WHERE pageNumber = :page ORDER BY lineNumber, id")
    fun getPageWords(page: Int): Flow<List<QuranWordEntity>>

    @Query("SELECT MIN(pageNumber) FROM quran_words WHERE surahNumber = :surahNumber")
    suspend fun getStartingPage(surahNumber: Int): Int
}