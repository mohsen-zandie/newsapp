package com.komozan.newsapp.presentation.di

import com.komozan.newsapp.data.repository.NewsRepositoryImpl
import com.komozan.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.komozan.newsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepositoryModule(remoteDataSource: NewsRemoteDataSource): NewsRepository {
        return NewsRepositoryImpl(remoteDataSource)
    }
}