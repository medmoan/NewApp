package com.example.newsapp.news.domain.models.news_list

data class Article(
    val publishedAt: String,
    val publisher: String,
    val title: String,
    val uuid: String
)