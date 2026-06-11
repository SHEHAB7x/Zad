package com.example.zad.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zad.data.local.entity.SurahEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SurahDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSurahList(surahs: List<SurahEntity>)

    @Query("SELECT * FROM surah_table")
    fun getSurahList(): Flow<List<SurahEntity>>

    @Query("SELECT COUNT(*) FROM surah_table")
    suspend fun getSurahCount(): Int
}