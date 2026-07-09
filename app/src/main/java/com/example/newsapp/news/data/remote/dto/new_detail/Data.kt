package com.example.newsapp.news.data.remote.dto.new_detail

import com.google.gson.annotations.SerializedName

data class Data(
    val uuid: String?,
    val title: String?,
    val thumbnail: String?,
    val incipit: String?,
    val body: String?,
    val publisher: String?,
    @SerializedName("published_at") val publishedAt: String?,
    @SerializedName("topics") val tags: List<String>?,
    @SerializedName("original_url") val originalUrl: String?,
    val authors: List<String>?
)