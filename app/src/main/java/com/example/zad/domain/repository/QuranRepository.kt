package com.example.zad.domain.repository

import com.example.zad.domain.model.Ayah
import com.example.zad.domain.model.Surah
import com.example.zad.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface QuranRepository {
    fun getSurahList(): Flow<Resource<List<Surah>>>

    fun getSurahWithAyahs(surahNumber: Int): Flow<Resource<List<Ayah>>>

    fun getAyah(ayahId: Int): Flow<Resource<Ayah>>

    suspend fun toggleFavoriteAyah(ayahId: Int)
}