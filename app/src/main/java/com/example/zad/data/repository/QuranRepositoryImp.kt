package com.example.zad.data.repository

import com.example.zad.data.local.dao.SurahDao
import com.example.zad.data.mappers.toDomain
import com.example.zad.data.mappers.toEntity
import com.example.zad.data.remote.api.ApiService
import com.example.zad.domain.model.Ayah
import com.example.zad.domain.model.Surah
import com.example.zad.domain.repository.QuranRepository
import com.example.zad.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuranRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val surahDao: SurahDao
): QuranRepository {

    override fun getSurahList(): Flow<Resource<List<Surah>>> = flow {
        emit(Resource.Loading())

        val cachedSurahs = surahDao.getSurahList().first()
        if (cachedSurahs.isNotEmpty())
            emit(Resource.Success(cachedSurahs.map { it.toDomain()}))

        try {
            val data = apiService.getSurahList("ar").chapters

            surahDao.insertSurahList(data.map { it.toEntity() })

            val freshSurahs = surahDao.getSurahList().first()
            emit(Resource.Success(freshSurahs.map { it.toDomain() }))

        } catch (e: Exception){
            val cached = surahDao.getSurahList().first()
            emit(Resource
                .Error(
                e.message ?: "Unknown error occurred",cached.map { it.toDomain() }
                )
            )
        }
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