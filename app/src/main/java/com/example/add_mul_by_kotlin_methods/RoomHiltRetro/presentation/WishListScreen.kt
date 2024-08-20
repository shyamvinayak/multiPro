package com.example.add_mul_by_kotlin_methods.RoomHiltRetro.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.add_mul_by_kotlin_methods.Navigation.Screens
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.Component.MovieListItem
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.mappers.toMovie

@Composable
fun WishListScreen(navController: NavController, viewModel: MovieViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        viewModel.loadWishlistDetails()
    }

    val wishlistDetails by viewModel.wishlistDetails
    val wishlistStatus by viewModel.wishlistStatus


    Box(modifier = Modifier.fillMaxSize()) {
        val listState = rememberLazyListState()

        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        )
        {
            item {
                Surface(
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    color = Color.Transparent
                ) {
                    Text(
                        text = "Wishlist",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )
                }


            }
            items(wishlistDetails) { wishlist ->
                MovieListItem(movie = wishlist.movie.toMovie(), onItemClick = {},
                    isInWishlist = wishlistStatus[wishlist.movie.movie_id]?:false ,
                    onWishlistClick = { isClick ->
                        if (isClick) {
                            viewModel.addToWishList(
                                wishlist.movie.movie_id,
                                isFav = true,
                                isClicked = true
                            )
                        } else {
                            viewModel.removeWishList(wishlist.movie.movie_id)

                        }
                    })
            }

        }

    }
}