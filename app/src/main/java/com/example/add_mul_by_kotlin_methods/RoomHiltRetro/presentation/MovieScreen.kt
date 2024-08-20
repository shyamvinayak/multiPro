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
import androidx.compose.foundation.lazy.grid.GridCells
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
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.Component.LazyVerticalGridMovies
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.Component.MovieListItem
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.Component.MovieTopBar
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun MovieScreen(
    naveController: NavController,
    viewModel: MovieViewModel = hiltViewModel(),
) {
    val movies = viewModel.moviePagingFlow.collectAsLazyPagingItems()
    val context = LocalContext.current
    val wishlistStatus by viewModel.wishlistStatus



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
            }, itemClicked = { naveController.navigate("single_movie/$it") })
            MovieTopBar(title = "Movies", icon = Icons.Filled.Favorite, onClick = {
                if (wishlistStatus.isNotEmpty()) {
                    naveController.navigate(Screens.WishList.route)
                } else {
                    Toast.makeText(context, "No Movies Found", Toast.LENGTH_LONG).show()
                }
            })

        }


    }

}
