package com.komozan.newsapp.data.repository.datasourceimpl

import com.komozan.newsapp.data.api.NewsAPIService
import com.komozan.newsapp.data.model.response.everything.EverythingResponse
import com.komozan.newsapp.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewRemoteDataSourceImpl(
    private val service: NewsAPIService,
    private val country: String,
    private val page: Int
) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(): Response<EverythingResponse> {
        return service.getTopHeadlines(country, page)
    }
}