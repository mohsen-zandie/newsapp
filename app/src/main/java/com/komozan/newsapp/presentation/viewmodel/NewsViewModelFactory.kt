package com.komozan.newsapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.komozan.newsapp.domain.usecase.GetNewsHeadlineUseCase

class NewsViewModelFactory(
    private val application: Application,
    private val getNewsHeadlineUseCase: GetNewsHeadlineUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(application, getNewsHeadlineUseCase) as T
    }
}