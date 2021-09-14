package com.komozan.newsapp.data.api

import com.komozan.newsapp.BuildConfig
import com.komozan.newsapp.data.model.response.everything.EverythingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country")
        country: String,
        @Query("page")
        page: Int,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): Response<EverythingResponse>
}