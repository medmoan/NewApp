package com.example.newsapp.news.domain.models.new_detail

data class NewDetail(
    val uuid: String,
    val title: String,
    val thumbnail: String,
    val incipit: String,
    val body: String,
    val publisher: String,
    val publishedAt: String,
    val tags: List<String>,
    val originalUrl: String,
    val authors: List<String>
)