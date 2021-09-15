package com.komozan.newsapp.data.repository.datasourceimpl

import com.komozan.newsapp.data.db.ArticleDao
import com.komozan.newsapp.data.model.response.everything.Article
import com.komozan.newsapp.data.repository.datasource.NewsDataLocalDataSource

class NewsDataLocalDataSourceImpl(private val dao: ArticleDao) : NewsDataLocalDataSource{
    override suspend fun saveArticlesToDB(article: Article) {
        return dao.insert(article)
    }

}