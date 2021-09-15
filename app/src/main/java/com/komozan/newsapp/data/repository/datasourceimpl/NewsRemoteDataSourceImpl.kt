package com.komozan.newsapp.data.repository.datasourceimpl

import com.komozan.newsapp.data.api.NewsAPIService
import com.komozan.newsapp.data.model.response.everything.APIResponse
import com.komozan.newsapp.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val service: NewsAPIService
) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country: String, page: Int): Response<APIResponse> {
        return service.getTopHeadlines(country, page)
    }

    override suspend fun getSpecifiedNewsAgency(domain: String): Response<APIResponse> {
        return service.getSpecifiedNewsAgency(domain)
    }
}