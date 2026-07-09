package com.example.newsapp.news.presentation.news_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.news.data.remote.repository.NewsRepositoryImp

class NewsViewModelFactory(
    val repository: NewsRepositoryImp
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel not found!")
    }
}