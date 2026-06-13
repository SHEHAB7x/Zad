package com.shehab.zad.domain.repository

import com.shehab.zad.domain.model.Ayah
import com.shehab.zad.domain.model.Surah
import com.shehab.zad.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface QuranRepository {
    fun getSurahList(): Flow<Resource<List<Surah>>>

    fun getSurahWithAyahs(surahNumber: Int): Flow<Resource<List<Ayah>>>

    fun getAyah(ayahId: Int): Flow<Resource<Ayah>>

    suspend fun toggleFavoriteAyah(ayahId: Int)
}