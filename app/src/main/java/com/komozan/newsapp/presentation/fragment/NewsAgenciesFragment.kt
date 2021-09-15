package com.komozan.newsapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.komozan.newsapp.R
import com.komozan.newsapp.data.model.NewsAgencyItem
import com.komozan.newsapp.databinding.FragmentNewsAgenciesBinding
import com.komozan.newsapp.presentation.activity.MainActivity
import com.komozan.newsapp.presentation.adapter.NewsAgencyAdapter
import com.komozan.newsapp.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class NewsAgenciesFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentNewsAgenciesBinding

    @Inject
    lateinit var newsAgencyAdapter: NewsAgencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_agencies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        binding = FragmentNewsAgenciesBinding.bind(view)
        newsAgencyAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putString("selected_agency", it.url)
            }
            (activity as MainActivity).slideDown()
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.newsFragment, false)
                .build()
            findNavController().navigate(R.id.action_newsAgenciesFragment_to_newsFragment, bundle)
        }
        (activity as MainActivity).slideUp()
        initRecyclerView()
        viewNewsList()
    }

    private fun viewNewsList() {
        newsAgencyAdapter.differ.submitList(createNewsAgenciesList())
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = newsAgencyAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun createNewsAgenciesList(): List<NewsAgencyItem> {
        val listItem = ArrayList<NewsAgencyItem>()
        listItem.add(
            NewsAgencyItem(
                "BBC", "bbc.com", R.drawable.ic_bbc_logo
            )
        )
        listItem.add(
            NewsAgencyItem(
                "CNN", "cnn.com", R.drawable.ic_cnn_logo
            )
        )
        listItem.add(
            NewsAgencyItem(
                "Fox News", "foxnews.com", R.drawable.ic_fox_logo
            )
        )
        listItem.add(
            NewsAgencyItem(
                "Tech Crunch", "techcrunch.com", R.drawable.ic_techcrunch_logo
            )
        )
        return listItem
    }
}