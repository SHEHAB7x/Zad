package com.shehab.zad.data.remote.api

import com.shehab.zad.data.remote.dto.AyahListResponse
import com.shehab.zad.data.remote.dto.SurahListResponse
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