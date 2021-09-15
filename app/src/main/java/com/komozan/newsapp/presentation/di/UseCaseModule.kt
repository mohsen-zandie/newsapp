package com.komozan.newsapp.presentation.di

import com.komozan.newsapp.domain.repository.NewsRepository
import com.komozan.newsapp.domain.usecase.GetNewsHeadlineUseCase
import com.komozan.newsapp.domain.usecase.GetSpecifiedNewsAgencyUseCase
import com.komozan.newsapp.domain.usecase.SaveNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGetNewsHeadlinesUseCase(repository: NewsRepository): GetNewsHeadlineUseCase{
        return GetNewsHeadlineUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSaveNewsUseCase(repository: NewsRepository): SaveNewsUseCase{
        return SaveNewsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetSpecifiedNewsAgencyUseCase(repository: NewsRepository): GetSpecifiedNewsAgencyUseCase{
        return GetSpecifiedNewsAgencyUseCase(repository)
    }
}