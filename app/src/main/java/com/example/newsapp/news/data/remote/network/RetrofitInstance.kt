package com.example.newsapp.news.data.remote.network


import com.example.newsapp.news.data.remote.api.NewsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val BASEURL = "https://api.freenewsapi.io/v1/"
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .connectTimeout(90, TimeUnit.SECONDS)
        .readTimeout(90, TimeUnit.SECONDS)
        .writeTimeout(90, TimeUnit.SECONDS)
        .addInterceptor(HeaderInterceptor())
        .addInterceptor(loggingInterceptor)
        .build()

    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    val newsApi: NewsApi by lazy {
        getInstance()
            .create(NewsApi::class.java)
    }

}