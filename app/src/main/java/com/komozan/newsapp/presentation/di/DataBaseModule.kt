package com.komozan.newsapp.presentation.di

import android.app.Application
import androidx.room.Room
import com.komozan.newsapp.data.db.ArticleDao
import com.komozan.newsapp.data.db.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun providesNewsDatabase(application: Application): ArticleDatabase {
        return Room.databaseBuilder(application, ArticleDatabase::class.java, "news_db")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(articleDatabase: ArticleDatabase):ArticleDao{
        return articleDatabase.getArticleDao()
    }
}