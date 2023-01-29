package com.example.lifolio.GoogleMap

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lifolio.databinding.ViewholderSearchResultItemBinding
import com.example.lifolio.GoogleMap.model.SearchResult

class SearchRecyclerAdapter: RecyclerView.Adapter<SearchRecyclerAdapter.SearchResultViewHolder>() {

    private var searchResultList: List<SearchResult> = listOf()
    var currentPage = 1
    var currentSearchString = ""

    private lateinit var searchResultClickListener: (SearchResult) -> Unit  // SearchResult를 parameter로 가지는 void return 함수

    inner class SearchResultViewHolder(
        private val binding: ViewholderSearchResultItemBinding,
        private val searchResultClickListener: (SearchResult) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: SearchResult) = with(binding) {
            titleTextView.text = data.name
            subtitleTextView.text = data.fullAddress
        }

        fun bindViews(data: SearchResult) {
            binding.root.setOnClickListener {
                searchResultClickListener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding = ViewholderSearchResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchResultViewHolder(binding, searchResultClickListener)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bindData(searchResultList[position])
        holder.bindViews(searchResultList[position])
    }

    override fun getItemCount(): Int {
        return searchResultList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSearchResultList(
        searchResultList: List<SearchResult>,
        searchResultClickListener: (SearchResult) -> Unit
    ) {
        this.searchResultList = this.searchResultList + searchResultList
        this.searchResultClickListener = searchResultClickListener
        notifyDataSetChanged()
    }

    fun clearList() {
        searchResultList = listOf()
    }

}