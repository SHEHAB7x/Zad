package com.example.zad.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AyahListResponse(
    val verses: List<AyahDto>
)

data class AyahDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("verse_number")
    val verseNumber: Int,
    @SerializedName("verse_key")
    val verseKey: String,
    @SerializedName("text_uthmani")
    val textUthmani: String
)