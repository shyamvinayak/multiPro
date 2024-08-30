package com.example.add_mul_by_kotlin_methods.roomHiltRetro.presentation


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.MovieItem
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.mappers.toMovie

@Composable
fun WishListScreen(navController: NavController, viewModel: MovieViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        viewModel.loadWishlistDetails()
        viewModel.loadSuggestedMovie()
    }
    val movies by viewModel.suggestedMovieDetails
    val wishlistDetails by viewModel.wishlistDetails
    //val wishlistStatus by viewModel.wishlistStatus


    /* Box(modifier = Modifier.fillMaxSize()) {
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

     }*/

    Box(modifier = Modifier.fillMaxSize()) {
         LazyColumn {
             item {
                 Text(
                     "WishList",
                     fontSize = 16.sp,
                     fontWeight = FontWeight.Bold,
                     color = MaterialTheme.colorScheme.primary,
                     modifier = Modifier
                         .padding(start = 15.dp, top = 16.dp, bottom = 8.dp)
                 )
             }
             item {
                 LazyRow(
                     Modifier
                         .padding(20.dp)
                         .fillMaxWidth()) {
                     items(wishlistDetails) { wishListData ->
                         MovieItem(movieModel = wishListData.movie.toMovie(),
                             goToMovieDetail = {
                                 navController.navigate("single_movie/${wishListData.movie.movie_id}")
                             },
                             isFavClick = {})

                     }
                 }
             }

             item {
                 Spacer(modifier = Modifier.height(10.dp))
             }

             item {
                 Text(
                     "Suggested for you",
                     fontSize = 16.sp,
                     fontWeight = FontWeight.Bold,
                     color = MaterialTheme.colorScheme.primary,
                     modifier = Modifier
                         .padding(start = 15.dp, top = 16.dp, bottom = 8.dp)

                 )
             }
             item {
                 LazyRow(
                     Modifier
                         .padding(20.dp)
                         .fillMaxWidth()) {
                     items(movies) { movie ->
                         MovieItem(movieModel = movie.movie.toMovie(),
                             goToMovieDetail = {
                                 navController.navigate("single_movie/${movie.movie.movie_id}")
                             },
                             isFavClick = {})

                     }
                 }
 
             }
         }
    }
}