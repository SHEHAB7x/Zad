package com.example.zad.data.repository

import com.example.zad.data.remote.api.ApiService
import com.example.zad.domain.model.Ayah
import com.example.zad.domain.model.Surah
import com.example.zad.domain.repository.QuranRepository
import com.example.zad.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuranRepositoryImp @Inject constructor(
    private val apiService: ApiService
): QuranRepository {
    override fun getSurahList(): Flow<Resource<List<Surah>>> {
        TODO("Not yet implemented")
    }

    override fun getSurahWithAyahs(surahNumber: Int): Flow<Resource<List<Ayah>>> {
        TODO("Not yet implemented")
    }

    override fun getAyah(ayahId: Int): Flow<Resource<Ayah>> {
        TODO("Not yet implemented")
    }

    override suspend fun toggleFavoriteAyah(ayahId: Int) {
        TODO("Not yet implemented")
    }


}