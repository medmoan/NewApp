package com.example.newsapp.news.data.remote.dto.news_list

import com.google.gson.annotations.SerializedName

data class Data(
    val uuid: String?,
    val title: String?,
    val publisher: String?,
    @SerializedName("published_at") val publishedAt: String?
)