package com.komozan.newsapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.komozan.newsapp.domain.usecase.GetNewsHeadlineUseCase
import com.komozan.newsapp.domain.usecase.GetSpecifiedNewsAgencyUseCase
import com.komozan.newsapp.domain.usecase.SaveNewsUseCase

class NewsViewModelFactory(
    private val application: Application,
    private val getNewsHeadlineUseCase: GetNewsHeadlineUseCase,
    private val getSpecifiedNewsAgencyUseCase: GetSpecifiedNewsAgencyUseCase,
    private val saveNewsUseCase: SaveNewsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(
            application,
            getNewsHeadlineUseCase,
            getSpecifiedNewsAgencyUseCase,
            saveNewsUseCase
        ) as T
    }
}