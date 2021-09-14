package com.komozan.newsapp.data.repository

import com.komozan.newsapp.data.model.response.everything.EverythingResponse
import com.komozan.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.komozan.newsapp.data.util.Resource
import com.komozan.newsapp.domain.repository.NewsRepository
import retrofit2.Response

class NewsRepositoryImpl(private val remoteDataSource: NewsRemoteDataSource) : NewsRepository {
    override suspend fun getNewsHeadlines(
        country: String,
        page: Int
    ): Resource<EverythingResponse> {
        return responseToResource(remoteDataSource.getTopHeadlines(country, page))
    }

    private fun responseToResource(response: Response<EverythingResponse>): Resource<EverythingResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}