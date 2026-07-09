package com.example.newsapp.news.domain.models.news_list

import com.example.newsapp.news.domain.repository.OrderBy

data class NewsModel(
    val articles: List<Article>,
    val hasMore: Boolean,
    val nextOffset: Int,
    val orderBy: OrderBy,
    val pageSize: Int
)