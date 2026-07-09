package com.example.newsapp.news.data.remote.network

import com.example.newsapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


class HeaderInterceptor : Interceptor {

    override fun intercept(
        chain: Interceptor.Chain
    ): Response {
        val request = chain.request()
            .newBuilder()
            .header("Accept", "application/json")
            .header("x-api-key", BuildConfig.NEWS_API_KEY)
            .build()


        return chain.proceed(request)
    }
}