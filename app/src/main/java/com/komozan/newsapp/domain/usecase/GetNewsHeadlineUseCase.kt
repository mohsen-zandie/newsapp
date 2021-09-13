package com.komozan.newsapp.domain.usecase

import com.komozan.newsapp.data.model.response.everything.EverythingResponse
import com.komozan.newsapp.data.util.Resource
import com.komozan.newsapp.domain.repository.NewsRepository

class GetNewsHeadlineUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(): Resource<EverythingResponse> {
        return newsRepository.getNewsHeadlines()
    }
}