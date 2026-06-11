package com.example.zad.data.remote.api

import com.example.zad.data.remote.dto.AyahListResponse
import com.example.zad.data.remote.dto.SurahListResponse
import com.example.zad.domain.model.Ayah
import com.example.zad.domain.model.Surah
import com.example.zad.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("chapters")
    suspend fun getSurahList(
        @Query("language") language: String = "ar"
    ) : SurahListResponse

    @GET("verses/by_chapter/{chapter_number}")
    suspend fun getAyahsByChapter(@Path("chapter_number") surahNumber: Int) : AyahListResponse

    companion object{
        const val BASE_URL = "https://api.quran.com/api/v4/"
    }
}