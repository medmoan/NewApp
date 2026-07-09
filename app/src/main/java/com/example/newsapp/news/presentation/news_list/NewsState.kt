package com.example.newsapp.news.presentation.news_list

import com.example.newsapp.news.domain.models.news_list.Article

data class NewsState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val offset: Int = 0,
    val nextOffset: Int = 0,
    val error: String? = null,
    val hasMore: Boolean = true
)