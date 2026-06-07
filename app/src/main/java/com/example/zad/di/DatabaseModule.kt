package com.example.zad.di

import android.content.Context
import androidx.room.Room
import com.example.zad.data.local.dao.SurahDao
import com.example.zad.data.local.database.ZadDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ZadDatabase{
        return Room
            .databaseBuilder(
                context,
                ZadDatabase::class.java,
                "zad.db"
            ).build()
    }

    @Provides
    @Singleton
    fun provideSurahDao(database: ZadDatabase): SurahDao{
        return database.surahDao()
    }
}