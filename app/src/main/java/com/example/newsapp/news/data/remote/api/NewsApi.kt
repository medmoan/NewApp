package com.example.newsapp.news.data.remote.api


import com.example.newsapp.news.data.remote.dto.new_detail.NewDetailDto
import com.example.newsapp.news.data.remote.dto.news_list.NewsDto
import com.example.newsapp.news.domain.repository.OrderBy
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("news")
    suspend fun getNews(
        @Query("in_title") query: String?,
        @Query("language") language: String = "en",
        @Query("country") country: String = "US",
        @Query("topic") topic: String = "world",
        @Query("order_by") orderBy: String = OrderBy.RECENT.name,
        @Query("offset") offset: Int = 0
    ): NewsDto

    @GET("details")
    suspend fun getNewDetail(
        @Query("uuid") uuid: String
    ): NewDetailDto
}