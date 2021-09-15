package com.komozan.newsapp.domain.usecase

import com.komozan.newsapp.data.model.response.everything.APIResponse
import com.komozan.newsapp.data.util.Resource
import com.komozan.newsapp.domain.repository.NewsRepository

class GetSpecifiedNewsAgencyUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(domain: String): Resource<APIResponse> {
        return newsRepository.getSpecifiedNewsAgency(domain)
    }
}