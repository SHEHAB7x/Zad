package com.example.zad.di

import com.example.zad.data.repository.QuranRepositoryImp
import com.example.zad.domain.repository.QuranRepository
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

}