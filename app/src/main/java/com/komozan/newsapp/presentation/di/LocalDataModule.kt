package com.komozan.newsapp.presentation.di

import com.komozan.newsapp.data.db.ArticleDao
import com.komozan.newsapp.data.repository.datasource.NewsDataLocalDataSource
import com.komozan.newsapp.data.repository.datasourceimpl.NewsDataLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Provides
    @Singleton
    fun provideNewsLocalDataSource(articleDao: ArticleDao): NewsDataLocalDataSource {
        return NewsDataLocalDataSourceImpl(articleDao)
    }
}