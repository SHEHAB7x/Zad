package com.shehab.zad.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SurahListResponse (
        @SerializedName("chapters")
    val chapters: List<SurahDto>
)

data class SurahDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name_arabic")
    val nameArabic: String,
    @SerializedName("name_simple")
    val nameSimple: String,
    @SerializedName("translated_name")
    val translatedName: TranslatedNameDto,
    @SerializedName("verses_count")
    val versesCount: Int,
    @SerializedName("revelation_place")
    val revelationPlace: String
)

data class TranslatedNameDto(
    @SerializedName("name")
    val name: String
)