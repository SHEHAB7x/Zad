package com.shehab.zad.domain.usecase

import com.shehab.zad.domain.repository.QiblaRepository
import com.shehab.zad.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQiblaDirectionUseCase @Inject constructor(
    private val repository: QiblaRepository
) {
    operator fun invoke(): Flow<Float> = repository.getAzimuthStream()
}