package com.komozan.newsapp.data.model

import androidx.annotation.IdRes

data class NewsAgencyItem(
    val title: String,
    val url: String,
    @IdRes val imageSrc: Int,
)