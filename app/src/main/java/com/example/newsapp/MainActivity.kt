package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newsapp.news.data.remote.repository.NewsRepositoryImp
import com.example.newsapp.news.presentation.new_detail.NewDetailScreen
import com.example.newsapp.news.presentation.new_detail.NewDetailViewModelFactory
import com.example.newsapp.news.presentation.news_list.NewsScreen
import com.example.newsapp.news.presentation.news_list.NewsViewModelFactory
import com.example.newsapp.ui.theme.NewsAppTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val newsViewModelFactory = NewsViewModelFactory(NewsRepositoryImp())
        val newDetailViewModelFactory = NewDetailViewModelFactory(NewsRepositoryImp())
        setContent {
            NewsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = NewsScreen) {
                        composable<NewsScreen> {

                            NewsScreen(
                                modifier = Modifier.padding(innerPadding),
                                onNewsClick = { id ->
                                    navController.navigate(DetailScreen(newsId = id))
                            },
                                newsViewModelFactory = newsViewModelFactory)
                        }

                        composable<DetailScreen> { backStackEntry ->

                            val detail = backStackEntry.toRoute<DetailScreen>()


                            NewDetailScreen(
                                modifier = Modifier.padding(innerPadding),
                                newsId = detail.newsId,
                                newDetailViewModelFactory = newDetailViewModelFactory
                            ) {
                                navController.navigateUp()
                            }

                        }
                    }
                }
            }
        }
    }
}
@Serializable
object NewsScreen

@Serializable
data class DetailScreen(val newsId: String)

