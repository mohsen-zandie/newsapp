package com.komozan.newsapp.data.repository.datasourceimpl

import com.komozan.newsapp.data.api.NewsAPIService
import com.komozan.newsapp.data.model.response.everything.EverythingResponse
import com.komozan.newsapp.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val service: NewsAPIService
) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country: String, page: Int): Response<EverythingResponse> {
        return service.getTopHeadlines(country, page)
    }
}