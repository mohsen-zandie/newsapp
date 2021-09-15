package com.komozan.newsapp.data.repository

import com.komozan.newsapp.data.model.response.everything.APIResponse
import com.komozan.newsapp.data.model.response.everything.Article
import com.komozan.newsapp.data.repository.datasource.NewsDataLocalDataSource
import com.komozan.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.komozan.newsapp.data.util.Resource
import com.komozan.newsapp.domain.repository.NewsRepository
import retrofit2.Response

class NewsRepositoryImpl(
    private val remoteDataSource: NewsRemoteDataSource,
    private val localDataSource: NewsDataLocalDataSource
) : NewsRepository {
    override suspend fun getNewsHeadlines(
        country: String,
        page: Int
    ): Resource<APIResponse> {
        return responseToResource(remoteDataSource.getTopHeadlines(country, page))
    }

    override suspend fun saveNews(article: Article) {
        localDataSource.saveArticlesToDB(article)
    }

    override suspend fun getSpecifiedNewsAgency(domain: String): Resource<APIResponse> {
        return responseToResource(remoteDataSource.getSpecifiedNewsAgency(domain))
    }

    private fun responseToResource(response: Response<APIResponse>): Resource<APIResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }


}