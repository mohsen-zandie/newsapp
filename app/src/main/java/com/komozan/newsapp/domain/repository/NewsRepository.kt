package com.komozan.newsapp.domain.repository

import com.komozan.newsapp.data.model.response.everything.EverythingResponse
import com.komozan.newsapp.data.util.Resource

interface NewsRepository {
    suspend fun getNewsHeadlines(): Resource<EverythingResponse>
}