package com.example.newsapp.news.data.remote.dto.news_list

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("has_more") val hasMore: Boolean?,
    @SerializedName("next_offset") val nextOffset: Int?,
    @SerializedName("order_by") val orderBy: String?,
    @SerializedName("page_size") val pageSize: Int?
)