package com.komozan.newsapp.presentation.di

import com.komozan.newsapp.domain.repository.NewsRepository
import com.komozan.newsapp.domain.usecase.GetNewsHeadlineUseCase
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
}