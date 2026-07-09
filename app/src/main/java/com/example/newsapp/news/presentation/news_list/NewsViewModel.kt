package com.example.newsapp.news.presentation.news_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.common.utils.NetworkResponse
import com.example.newsapp.news.data.remote.repository.NewsRepositoryImp
import com.example.newsapp.news.domain.models.news_list.NewsModel
import com.example.newsapp.news.domain.repository.OrderBy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: NewsRepositoryImp
): ViewModel() {

    private val _newsState = MutableStateFlow(NewsState())
    val newsState = _newsState.asStateFlow()
    private var query = ""
    fun getNews(q: String, offset: Int) {
        val searchQuery = q.ifBlank { "news" }
        query = searchQuery
        viewModelScope.launch {
            repository.getNews(
                language = "en",
                q = searchQuery,
                orderBy = OrderBy.RECENT,
                offset = offset
            ).collect { result ->
                when(result) {
                    is NetworkResponse.Error -> {
                        _newsState.update {
                            it.copy(
                                isLoading = false,
                                error = result.message?: "Couldn't get news!"
                            )
                        }
                    }
                    NetworkResponse.Loading -> {
                        _newsState.update {
                            it.copy(
                                isLoading = true,
                                error = null,
                                articles = if (offset == 0) emptyList() else it.articles
                            )
                        }
                    }
                    is NetworkResponse.Success<NewsModel> -> {
                        if (result.data == null) {
                            _newsState.update {
                                it.copy(
                                    isLoading = false,
                                    error = "No News found!"
                                )
                            }
                        } else {
                            _newsState.update { state ->
                                val currentArticles = if (offset == 0) emptyList() else state.articles
                                state.copy(
                                    articles = currentArticles + result.data.articles,
                                    isLoading = false,
                                    offset = offset,
                                    nextOffset = result.data.nextOffset,
                                    hasMore = result.data.hasMore,
                                    error = null
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    fun loadMoreNews(offset: Int) {
        getNews(query, offset = offset)
    }
}