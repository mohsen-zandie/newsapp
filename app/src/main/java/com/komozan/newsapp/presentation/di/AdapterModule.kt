package com.komozan.newsapp.presentation.di

import com.komozan.newsapp.presentation.adapter.NewsAdapter
import com.komozan.newsapp.presentation.adapter.NewsAgencyAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
    @Singleton
    @Provides
    fun provideNewsAdapter(): NewsAdapter {
        return NewsAdapter()
    }

    @Singleton
    @Provides
    fun provideNewsAgencyAdapter(): NewsAgencyAdapter {
        return NewsAgencyAdapter()
    }
}