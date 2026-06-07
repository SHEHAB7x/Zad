package com.example.zad.data.mappers

import com.example.zad.data.local.entity.SurahEntity
import com.example.zad.data.remote.dto.SurahDto
import com.example.zad.domain.model.Surah

fun SurahDto.toEntity(): SurahEntity{
    return SurahEntity(
        id = id,
        nameAr = nameArabic,
        nameEn = nameSimple,
        nameTransliteration = translatedName.name,
        numberOfAyahs = versesCount,
        revelationType = revelationPlace
    )
}

fun SurahEntity.toDomain(): Surah{
    return Surah(
        id = id,
        nameAr = nameAr,
        nameEn = nameEn,
        nameTransliteration = nameTransliteration ,
        numberOfAyahs = numberOfAyahs,
        revelationType = revelationType
    )
}