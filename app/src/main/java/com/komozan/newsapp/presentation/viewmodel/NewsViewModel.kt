package com.komozan.newsapp.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.komozan.newsapp.R
import com.komozan.newsapp.data.model.response.everything.Article
import com.komozan.newsapp.data.model.response.everything.APIResponse
import com.komozan.newsapp.data.util.Resource
import com.komozan.newsapp.domain.usecase.GetNewsHeadlineUseCase
import com.komozan.newsapp.domain.usecase.GetSpecifiedNewsAgencyUseCase
import com.komozan.newsapp.domain.usecase.SaveNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val app: Application,
    private val getNewsHeadlineUseCase: GetNewsHeadlineUseCase,
    private val getSpecifiedNewsAgencyUseCase: GetSpecifiedNewsAgencyUseCase,
    private val saveNewsUseCase: SaveNewsUseCase
) : AndroidViewModel(app) {
    val newsHeadline: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getNewsHeadlines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        newsHeadline.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val apiResult = getNewsHeadlineUseCase.execute(country, page)
                newsHeadline.postValue(apiResult)
            } else {
                newsHeadline.postValue(Resource.Error(app.getString(R.string.network_connection_error)))
            }
        } catch (e: Exception) {
            newsHeadline.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun getSpecifiedNewsAgency(domain: String) = viewModelScope.launch(Dispatchers.IO) {
        newsHeadline.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val apiResult = getSpecifiedNewsAgencyUseCase.execute(domain)
                newsHeadline.postValue(apiResult)
            } else {
                newsHeadline.postValue(Resource.Error(app.getString(R.string.network_connection_error)))
            }
        } catch (e: Exception) {
            newsHeadline.postValue(Resource.Error(e.message.toString()))
        }
    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        saveNewsUseCase.execute(article)
    }


}