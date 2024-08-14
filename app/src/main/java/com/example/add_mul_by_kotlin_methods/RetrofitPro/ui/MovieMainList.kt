package com.example.add_mul_by_kotlin_methods.RetrofitPro.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.add_mul_by_kotlin_methods.Component.CoverImage
import com.example.add_mul_by_kotlin_methods.Component.RatingStars
import com.example.add_mul_by_kotlin_methods.RetrofitPro.model.Data
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.add_mul_by_kotlin_methods.RetrofitPro.SharedPref.storeMovieDetails
import com.example.add_mul_by_kotlin_methods.RetrofitPro.ViewModel.MovieViewMainModel

@Composable
fun MovieMainList(
    navController: NavController,
    viewModel: MovieViewMainModel = hiltViewModel()
) {

    val movies = viewModel.movieResponse.collectAsLazyPagingItems()
    val context = LocalContext.current

    println("movieDetails:--${movies.itemCount}")


    Surface(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        Column {
            Text(
                text = "Movies",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(15.dp))
            LazyColumn {
                items(movies.itemCount) {
                    MovieListCard(movies[it]!!, onClick = { movieDetails ->
                        storeMovieDetails(
                            context,
                            movieId = "${movieDetails.movie_id}",
                            movieDetails
                        )
                        navController.navigate("single_movie/${movieDetails.movie_id}")
                    })
                }
            }

        }

    }
    /* DisposableEffect(Unit) {
         viewModel.addMovieList()
         onDispose {}
     }*/


}

@Composable
fun MovieListCard(movie: Data, onClick: (movieDetails: Data) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
            .clickable {
                onClick(movie)
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            CoverImage(imageUrl = movie.poster_path)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    movie.original_title,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(0.85f)
                        .padding(horizontal = 8.dp)
                )
                Text(movie.release_date, style = MaterialTheme.typography.titleSmall)
                RatingStars(rating = movie.vote_average)
            }
        }



    }
}

