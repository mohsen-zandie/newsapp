package com.komozan.newsapp.domain.usecase

import com.komozan.newsapp.data.model.response.everything.APIResponse
import com.komozan.newsapp.data.util.Resource
import com.komozan.newsapp.domain.repository.NewsRepository

class GetNewsHeadlineUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country: String, page: Int): Resource<APIResponse> {
        return newsRepository.getNewsHeadlines(country, page)
    }
}