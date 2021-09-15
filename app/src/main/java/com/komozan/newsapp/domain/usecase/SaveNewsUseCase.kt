package com.komozan.newsapp.domain.usecase

import com.komozan.newsapp.data.model.response.everything.Article
import com.komozan.newsapp.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.saveNews(article)
}