package com.komozan.newsapp.data.model.response.everything

data class APIResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)