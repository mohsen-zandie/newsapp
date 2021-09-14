package com.komozan.newsapp.presentation.di

import com.komozan.newsapp.data.api.NewsAPIService
import com.komozan.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.komozan.newsapp.data.repository.datasourceimpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {
    @Provides
    @Singleton
    fun provideNewsRemoteDataSource(service: NewsAPIService): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(service)
    }
}