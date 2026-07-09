package com.example.newsapp.news.presentation.new_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.news.data.remote.repository.NewsRepositoryImp
import com.example.newsapp.news.domain.repository.NewsRepository


class NewDetailViewModelFactory(
    private val newsRepository: NewsRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewDetailViewModel::class.java)) {
            return NewDetailViewModel(newsRepository) as T
        }
        throw IllegalArgumentException("ViewModel not found!")
    }
}