package com.komozan.newsapp.presentation.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.komozan.newsapp.R
import com.komozan.newsapp.databinding.FragmentNewsContentBinding

class NewsContentFragment : Fragment() {
    private lateinit var binding: FragmentNewsContentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsContentBinding.bind(view)
        val args: NewsContentFragmentArgs by navArgs()
        val article = args.selectedArticle
        binding.webView.apply {
            webViewClient = WebViewClient()
            if (!TextUtils.isEmpty(article.url)) {
                loadUrl(article.url)
            }
        }
    }

}