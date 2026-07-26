package com.shehab.zad.domain.repository

import com.shehab.zad.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface QiblaRepository {
    fun getQiblaDirection(): Flow<Resource<Float>>
}