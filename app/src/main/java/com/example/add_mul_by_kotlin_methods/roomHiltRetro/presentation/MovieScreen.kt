package com.example.add_mul_by_kotlin_methods.roomHiltRetro.presentation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.add_mul_by_kotlin_methods.navigation.Screens
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.LazyVerticalGridMovies
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.MovieItem
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.MoviePoster
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.MovieTopBar
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.SearchBar
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.domain.Movie
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.local.Entity.MovieEntity
import com.example.add_mul_by_kotlin_methods.ui.theme.text


//Not completed SearchBar ,I refer in this website for implementing Search bar :-https://rhythamnegi.com/material-3-search-bar-in-jetpack-compose-android-example

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MovieScreen(
    naveController: NavController,
    viewModel: MovieViewModel = hiltViewModel(),
) {
    val movies = viewModel.moviePagingFlow.collectAsLazyPagingItems()
    val searchMovies = viewModel.searchDetails.collectAsState()
    val searchQuery = remember { mutableStateOf("") }
    val context = LocalContext.current
    val wishlistStatus by viewModel.wishlistStatus
    var searchHistory = remember { mutableStateListOf("") }
    val active = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = movies.loadState) {
        if (movies.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (movies.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        if (movies.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Column(modifier = Modifier.fillMaxSize()) {
                MovieTopBar(title = "Movies", icon = Icons.Filled.Favorite, onClick = {
                    naveController.navigate(Screens.Coroutines.route)
                    /*if (wishlistStatus.isNotEmpty()) {
                        naveController.navigate(Screens.WishList.route)
                    } else {
                        Toast.makeText(context, "No Movies Found", Toast.LENGTH_LONG).show()
                    }*/
                })
                SearchBar(
                    active = active,
                    searchQuery = searchQuery,
                    onChangeValue = { newQuery ->
                        if(newQuery.isNotEmpty()){
                            searchQuery.value = newQuery
                            viewModel.searchMovies(newQuery)
                        }else{
                            viewModel.clearSearchResults()
                        }

                    },
                    content = {
                        Column(Modifier.verticalScroll(rememberScrollState())) {
                            repeat(searchMovies.value.size) { idx ->
                                val movie = searchMovies.value[idx]
                                val resultText =
                                    "Suggestion ${movie.original_title}"
                                Box(modifier = Modifier.size(100.dp, 100.dp).padding(10.dp)) {
                                    MoviePoster(
                                        imagePath = movie.poster_path,
                                        size = 240.dp,
                                        modifier = Modifier
                                            .clickable {
                                                naveController.navigate("single_movie/${movie.movie_id}")
                                                viewModel.clearSearchResults()
                                            },
                                        onClick = {}
                                    )
                                }
                                /*ListItem(
                                    headlineContent = { Text(resultText, color = MaterialTheme.colorScheme.onBackground) },
                                    supportingContent = { Text("Additional info", color = MaterialTheme.colorScheme.onBackground) },
                                    leadingContent = {
                                        Icon(
                                            Icons.Filled.Star,
                                            contentDescription = null
                                        )
                                    },
                                    colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                                    modifier =
                                    Modifier
                                        .clickable {
                                            searchQuery.value = resultText
                                        }
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 4.dp)
                                )*/
                            }
                        }
                    }
                )
                LazyVerticalGridMovies(list = movies, isFavClick = { id, isClick ->
                    if (isClick) {
                        viewModel.addToWishList(
                            id,
                            isFav = true,
                            isClicked = true
                        )
                    } else {
                        viewModel.removeWishList(id)
                    }
                }, itemClicked = {
                    naveController.navigate("single_movie/$it")
                })

            }

        }


    }

}
