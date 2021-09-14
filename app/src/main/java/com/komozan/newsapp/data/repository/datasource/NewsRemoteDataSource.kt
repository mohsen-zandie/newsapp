package com.komozan.newsapp.data.repository.datasource

import com.komozan.newsapp.data.model.response.everything.EverythingResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(country: String, page: Int): Response<EverythingResponse>
}