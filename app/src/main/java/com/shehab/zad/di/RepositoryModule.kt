package com.shehab.zad.di

import com.shehab.zad.data.repository.PrayerRepositoryImpl
import com.shehab.zad.data.repository.QuranRepositoryImp
import com.shehab.zad.domain.repository.PrayerRepository
import com.shehab.zad.domain.repository.QuranRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindQuranRepository(
        repositoryImp: QuranRepositoryImp
    ): QuranRepository

    @Binds
    @Singleton
    abstract fun bindPrayerRepository(
        prayerRepositoryImpl: PrayerRepositoryImpl
    ): PrayerRepository
}