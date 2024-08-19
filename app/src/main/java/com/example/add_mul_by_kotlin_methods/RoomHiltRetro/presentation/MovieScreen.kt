package com.example.add_mul_by_kotlin_methods.RoomHiltRetro.presentation

import android.widget.ImageView
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.add_mul_by_kotlin_methods.Navigation.Screens
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.Component.MovieListItem
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.remote.MovieApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun MovieScreen(
    naveController: NavController,
    viewModel: MovieViewModel = hiltViewModel(),
) {
    val insertStatus by viewModel.wishListInsertStatus.observeAsState()
    val movies = viewModel.moviePagingFlow.collectAsLazyPagingItems()
    val context = LocalContext.current
    val wishlistStatus by viewModel.wishlistStatus

    val isRefreshing = remember { mutableStateOf(false) }

    /*insertStatus?.let {
        if (it) {
            Toast.makeText(LocalContext.current, "Item added to wishlist!", Toast.LENGTH_LONG)
                .show()
        } else {
            Toast.makeText(
                LocalContext.current,
                "Failed to add item to wishlist.",
                Toast.LENGTH_LONG
            ).show()
        }
    }*/

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
            SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing.value),
                onRefresh = {
                    isRefreshing.value = true
                    viewModel.getMovieList(1)
                    isRefreshing.value = false
                }) {

                val listState = rememberLazyListState()

                LazyColumn(
                    state = listState,
                    contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp),
                    modifier = Modifier.background(MaterialTheme.colorScheme.background)
                )
                {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Movies",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            IconButton(onClick = {
                                naveController.navigate(Screens.WishList.route)
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "fav",
                                    tint = Color.Red
                                )
                            }

                        }
                    }
                    items(movies.itemCount) {
                        movies[it]?.let { movie ->
                            MovieListItem(movie = movie, onItemClick = {
                                naveController.navigate("single_movie/${movie.movie_id}")
                            }, isInWishlist = wishlistStatus[movie.movie_id] ?: false,
                                onWishlistClick = { isClick ->
                                    if (isClick) {
                                        viewModel.addToWishList(movie.movie_id, isClick)
                                    } else {
                                        viewModel.removeWishList(movie.movie_id)
                                    }
                                })
                        }
                    }
                    item {
                        if (movies.loadState.append is LoadState.Loading) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }


        }



    }

}
