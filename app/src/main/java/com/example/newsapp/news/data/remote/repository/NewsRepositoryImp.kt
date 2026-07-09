package com.example.newsapp.news.data.remote.repository

import com.example.newsapp.common.utils.NetworkResponse
import com.example.newsapp.news.data.remote.mappers.toNewsModel
import com.example.newsapp.news.data.remote.network.RetrofitInstance
import com.example.newsapp.news.domain.models.new_detail.NewDetail
import com.example.newsapp.news.domain.models.news_list.NewsModel
import com.example.newsapp.news.domain.repository.NewsRepository
import com.example.newsapp.news.domain.repository.OrderBy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import com.example.newsapp.news.data.remote.mappers.toNewDetail

class NewsRepositoryImp: NewsRepository {
    override suspend fun getNews(
        q: String,
        language: String,
        orderBy: OrderBy,
        offset: Int
    ): Flow<NetworkResponse<NewsModel>> {
        return flow {
            emit(NetworkResponse.Loading)
            try {
                val news = RetrofitInstance.newsApi.getNews(
                    language = if (language.isBlank()) "en" else language,
                    query = if (q.isBlank()) "news" else q,
                    orderBy = orderBy.value,
                    offset = offset
                ).toNewsModel()
                emit(NetworkResponse.Success(news))
                return@flow
            }
            catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                emit(NetworkResponse.Error(errorBody ?: e.message()))
                return@flow
            }
            catch (e: java.io.IOException) {
                emit(NetworkResponse.Error(e.message ?: "Couldn't reach server, check your internet connection"))
                return@flow
            }
            catch (e: Exception) {
                emit(NetworkResponse.Error("${e.message}"))
                return@flow
            }

        }

    }

    override suspend fun getNewDetail(uuid: String): Flow<NetworkResponse<NewDetail>> {
        return flow {
            emit(NetworkResponse.Loading)
            try {
                val newDetail = RetrofitInstance.newsApi.getNewDetail(
                    uuid
                ).toNewDetail()
                emit(NetworkResponse.Success(newDetail))
                return@flow
            }
            catch (e: retrofit2.HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                emit(NetworkResponse.Error(errorBody ?: e.message()))
                return@flow
            }
            catch (e: Exception) {
                emit(NetworkResponse.Error(e.message))
                return@flow
            }
        }
    }
}