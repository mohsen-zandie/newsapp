package com.komozan.newsapp.presentation.di

import android.app.Application
import com.komozan.newsapp.domain.usecase.GetNewsHeadlineUseCase
import com.komozan.newsapp.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Provides
    @Singleton
    fun provideNewsViewModelFactory(
        application: Application,
        getNewsHeadlineUseCase: GetNewsHeadlineUseCase
    ): NewsViewModelFactory {
        return NewsViewModelFactory(application, getNewsHeadlineUseCase)
    }
}