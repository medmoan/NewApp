package com.example.newsapp.news.presentation.new_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.common.utils.NetworkResponse
import com.example.newsapp.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewDetailViewModel(
    private val newsRepository: NewsRepository
): ViewModel() {
    private val _newDetailState = MutableStateFlow(NewDetailState())
    val newDetailState = _newDetailState.asStateFlow()

    fun getNewDetail(uuid: String) {
        viewModelScope.launch {
            newsRepository.getNewDetail(uuid).collect { result ->
                when (result) {
                    is NetworkResponse.Loading -> {
                        _newDetailState.update {
                            it.copy(isLoading = true, error = null)
                        }

                    }
                    is NetworkResponse.Error -> {
                        _newDetailState.update {
                            it.copy(isLoading = false, error = result.message)
                        }
                    }
                    is NetworkResponse.Success -> {
                        if (result.data == null) {
                            _newDetailState.update {
                                it.copy(isLoading = false, error = "Something went wrong")
                            }
                            return@collect
                        }
                        _newDetailState.update {
                            it.copy(
                                isLoading = false,
                                error = null,
                                title = result.data.title,
                                thumbnail = result.data.thumbnail,
                                body = result.data.body,
                                publisher = result.data.publisher,
                                publishedAt = result.data.publishedAt,
                                tags = result.data.tags
                            )
                        }
                    }
                }
            }

        }
    }
}