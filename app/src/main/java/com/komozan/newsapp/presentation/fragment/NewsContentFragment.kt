package com.komozan.newsapp.presentation.fragment

import android.net.http.SslError
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.komozan.newsapp.R
import com.komozan.newsapp.databinding.FragmentNewsContentBinding
import com.komozan.newsapp.presentation.activity.MainActivity
import com.komozan.newsapp.presentation.viewmodel.NewsViewModel

class NewsContentFragment : Fragment() {
    private lateinit var binding: FragmentNewsContentBinding
    private lateinit var viewModel: NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsContentBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        val args: NewsContentFragmentArgs by navArgs()
        val article = args.selectedArticle
        showProgressBar()
        binding.webView.apply {
            webViewClient = MyWebViewClient()
            if (!TextUtils.isEmpty(article.url)) {
                loadUrl(article.url!!)
                viewModel.saveArticle(article)
            }
        }
    }

    inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url!!)
            return false
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            hideProgressBar()
        }

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
        }

        override fun onReceivedSslError(
            view: WebView?,
            handler: SslErrorHandler?,
            error: SslError?
        ) {
            handler?.proceed()
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

}