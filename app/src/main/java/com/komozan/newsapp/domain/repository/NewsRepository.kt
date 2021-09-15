package com.komozan.newsapp.domain.repository

import com.komozan.newsapp.data.model.response.everything.APIResponse
import com.komozan.newsapp.data.model.response.everything.Article
import com.komozan.newsapp.data.util.Resource

interface NewsRepository {
    suspend fun getNewsHeadlines(country: String, page: Int): Resource<APIResponse>
    suspend fun saveNews(article: Article)
    suspend fun getSpecifiedNewsAgency(domain: String): Resource<APIResponse>
}