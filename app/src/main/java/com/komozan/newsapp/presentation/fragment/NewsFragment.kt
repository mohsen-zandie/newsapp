package com.komozan.newsapp.presentation.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.komozan.newsapp.R
import com.komozan.newsapp.data.util.Resource
import com.komozan.newsapp.databinding.FragmentNewsBinding
import com.komozan.newsapp.presentation.activity.MainActivity
import com.komozan.newsapp.presentation.adapter.NewsAdapter
import com.komozan.newsapp.presentation.viewmodel.NewsViewModel

class NewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private val country = "us"
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).newsAdapter
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_article", it)
            }
            (activity as MainActivity).slideDown()
            findNavController().navigate(R.id.action_newsFragment_to_newsContentFragment, bundle)
        }
        (activity as MainActivity).slideUp()
        binding = FragmentNewsBinding.bind(view)
        setOnRefreshListener()
        initRecyclerView()
        getData()
        viewNewsList()
    }

    private fun setOnRefreshListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            showProgressBar()
            getData()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        binding.recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    page++
                    getData()
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun getData() {
        val args: NewsFragmentArgs by navArgs()
        val selectedAgency = args.selectedAgency
        if (TextUtils.isEmpty(selectedAgency)) {
            viewModel.getNewsHeadlines(country, page)
        } else {
            viewModel.getSpecifiedNewsAgency(selectedAgency)
        }
    }

    private fun viewNewsList() {
        viewModel.newsHeadline.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        showMessage(it)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

}