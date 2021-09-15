package com.komozan.newsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.komozan.newsapp.data.model.NewsAgencyItem
import com.komozan.newsapp.data.model.response.everything.Article
import com.komozan.newsapp.databinding.NewsAgencyListItemBinding
import com.komozan.newsapp.databinding.NewsListItemBinding

class NewsAgencyAdapter : RecyclerView.Adapter<NewsAgencyAdapter.NewsAgencyViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<NewsAgencyItem>() {
        override fun areItemsTheSame(oldItem: NewsAgencyItem, newItem: NewsAgencyItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: NewsAgencyItem, newItem: NewsAgencyItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAgencyViewHolder {
        val binding =
            NewsAgencyListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsAgencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsAgencyViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class NewsAgencyViewHolder(val binding: NewsAgencyListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newsAgency: NewsAgencyItem) {
            binding.title.text = newsAgency.title
            binding.newsAgencyLogo.setImageResource(newsAgency.imageSrc)

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(newsAgency)
                }
            }
        }

    }

    private var onItemClickListener: ((NewsAgencyItem) -> Unit)? = null

    fun setOnItemClickListener(clickListener: (NewsAgencyItem) -> Unit) {
        onItemClickListener = clickListener
    }
}