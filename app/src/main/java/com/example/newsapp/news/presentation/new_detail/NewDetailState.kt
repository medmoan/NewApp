package com.example.newsapp.news.presentation.new_detail

data class NewDetailState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val title: String = "",
    val thumbnail: String = "",
    val body: String = "",
    val publisher: String = "",
    val publishedAt: String = "",
    val tags: List<String> = emptyList()
)