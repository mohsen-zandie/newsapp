package com.komozan.newsapp.data.repository.datasource

import com.komozan.newsapp.data.model.response.everything.Article

interface NewsDataLocalDataSource {
    suspend fun saveArticlesToDB(article: Article)
}