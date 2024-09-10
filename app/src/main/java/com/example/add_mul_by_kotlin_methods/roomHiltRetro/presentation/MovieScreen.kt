package com.example.add_mul_by_kotlin_methods.roomHiltRetro.presentation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.add_mul_by_kotlin_methods.navigation.Screens
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.LazyVerticalGridMovies
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.MovieItem
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.MoviePoster
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.MoviePosterDetail
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.MovieTopBar
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.MovieVertical
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.SearchBar
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.domain.Movie
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.local.Entity.MovieEntity
import com.example.add_mul_by_kotlin_methods.ui.theme.text
import kotlinx.coroutines.NonDisposableHandle.parent


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
//                    naveController.navigate(Screens.Coroutines.route)
                    if (wishlistStatus.isNotEmpty()) {
                        naveController.navigate(Screens.WishList.route)
                    } else {
                        Toast.makeText(context, "No Movies Found", Toast.LENGTH_LONG).show()
                    }
                })
                SearchBar(
                    active = active,
                    searchQuery = searchQuery,
                    onChangeValue = { newQuery ->
                        println("NewQuery:--$newQuery")
                        if(newQuery.isNotEmpty()){
                            searchQuery.value = newQuery
                            viewModel.searchMovies(newQuery)
                        }else{
                            viewModel.clearSearchResults()
                        }

                    },

                    onClear = {
                        if(it){
                            viewModel.clearSearchResults()
                        }
                    },

                    content = {
                        LazyColumn(modifier = Modifier
                            .fillMaxSize()) {
                            items(searchMovies.value.size){
                                val movie = searchMovies.value[it]
                                val resultText = movie.original_title
                                val moviePoster = movie.poster_path
                                val movieId = movie.movie_id

                                MovieVertical(
                                    moviePoster = moviePoster,
                                    movieTitle = resultText,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .padding(vertical = 20.dp),
                                    onClick = {
                                        naveController.navigate("single_movie/${movieId}")
                                        viewModel.clearSearchResults()
                                    })

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
