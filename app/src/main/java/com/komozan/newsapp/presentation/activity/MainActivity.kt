package com.komozan.newsapp.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.komozan.newsapp.R
import com.komozan.newsapp.databinding.ActivityMainBinding
import com.komozan.newsapp.presentation.adapter.NewsAdapter
import com.komozan.newsapp.presentation.viewmodel.NewsViewModel
import com.komozan.newsapp.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var factory: NewsViewModelFactory
    @Inject
    lateinit var newsAdapter: NewsAdapter
    lateinit var viewModel: NewsViewModel
    private lateinit var child : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
        child = binding.bottomNavigation
        viewModel = ViewModelProvider(this, factory).get(NewsViewModel::class.java)
    }

    fun slideUp() {
        child.clearAnimation()
        child.visibility = View.VISIBLE
        child.animate().translationY(0f).duration = 200
    }

    fun slideDown() {
        child.clearAnimation()
        child.animate().translationY(200f).duration = 200
        MainScope().launch {
            delay(200)
            child.visibility = View.GONE
        }
    }
}