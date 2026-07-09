package com.example.newsapp.news.data.remote.mappers

import com.example.newsapp.news.data.remote.dto.news_list.Data
import com.example.newsapp.news.domain.models.new_detail.NewDetail
import com.example.newsapp.news.data.remote.dto.new_detail.NewDetailDto
import com.example.newsapp.news.data.remote.dto.news_list.NewsDto
import com.example.newsapp.news.domain.models.news_list.Article
import com.example.newsapp.news.domain.models.news_list.NewsModel


import com.example.newsapp.news.domain.repository.OrderBy

fun NewDetailDto.toNewDetail(): NewDetail {
    return NewDetail(
        uuid = data.uuid?: "",
        title = data.title?: "",
        thumbnail = data.thumbnail?: "",
        incipit = data.incipit?: "",
        body = data.body?: "",
        publisher = data.publisher?: "",
        publishedAt = data.publishedAt?: "",
        tags = data.tags?: listOf(""),
        originalUrl = data.originalUrl?: "",
        authors = data.authors?: listOf("")
    )
}
fun Data.toArticle(): Article {
    return Article(
        uuid = uuid?: "",
        title = title?: "",
        publisher = publisher?: "",
        publishedAt = publishedAt?: ""
    )
}
fun NewsDto.toNewsModel(): NewsModel {
    return NewsModel(
        articles = data.map { data -> data.toArticle() },
        hasMore = meta.hasMore?: false,
        orderBy = if (meta.orderBy == OrderBy.ARCHIVE.name.lowercase()) OrderBy.ARCHIVE else OrderBy.RECENT ,
        nextOffset = meta.nextOffset?: 0,
        pageSize = meta.pageSize?: 0
    )
}