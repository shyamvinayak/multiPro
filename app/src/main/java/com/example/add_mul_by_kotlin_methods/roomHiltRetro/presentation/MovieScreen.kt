package com.example.add_mul_by_kotlin_methods.roomHiltRetro.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.add_mul_by_kotlin_methods.navigation.Screens
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.LazyVerticalGridMovies
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.MovieTopBar

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
            }, itemClicked = {
                naveController.navigate("single_movie/$it") })
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
