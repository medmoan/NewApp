package com.example.newsapp.news.domain.repository

import com.example.newsapp.common.utils.NetworkResponse
import com.example.newsapp.news.domain.models.new_detail.NewDetail
import com.example.newsapp.news.domain.models.news_list.NewsModel
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(
        q: String,
        language: String,
        orderBy: OrderBy,
        offset: Int
    ): Flow<NetworkResponse<NewsModel>>

    suspend fun getNewDetail(uuid: String): Flow<NetworkResponse<NewDetail>>
}