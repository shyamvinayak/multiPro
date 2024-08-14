package com.example.add_mul_by_kotlin_methods.RetrofitPro.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.add_mul_by_kotlin_methods.Component.CircularCastImage
import com.example.add_mul_by_kotlin_methods.Component.RatingStars
import com.example.add_mul_by_kotlin_methods.RetrofitPro.SharedPref.retrieveMovieDetails
import com.example.add_mul_by_kotlin_methods.RetrofitPro.ViewModel.MovieViewMainModel
import com.example.add_mul_by_kotlin_methods.RetrofitPro.model.CastX

@Composable
fun SingleMovie(movieId: String,viewModel: MovieViewMainModel = hiltViewModel()) {
    println("DataId:---$movieId")
   val movies by viewModel.singleMovie.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.getMovieById(movieId)
    }

    Scaffold(content = { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            movies?.let { movie->
                Column(modifier = Modifier.padding(15.dp)) {
                    Box(
                        modifier = Modifier
                            .height(190.dp)
                            .fillMaxWidth()
                            .background(White, shape = RoundedCornerShape(8.dp)),
                    ) {

                        AsyncImage(
                            model = movie.backdrop_path,
                            contentDescription = "Poster",
                            contentScale = ContentScale.FillWidth
                        )
                            Card(
                                elevation = CardDefaults.cardElevation(4.dp),
                                modifier = Modifier
                                    .height(180.dp)
                                    .align(Alignment.CenterStart)
                                    .padding(start = 5.dp, top = 20.dp, bottom = 10.dp)
                            ) {
                                AsyncImage(
                                    model = movie.poster_path,
                                    contentDescription = "Banner Image",
                                    contentScale = ContentScale.Fit,
                                )
                            }


                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = movie.original_title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    RatingStars(rating = movie.vote_average)
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(movie.release_date, style = MaterialTheme.typography.titleSmall)
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                    ) {
                        Text(
                            movie.overview,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.verticalScroll(
                                rememberScrollState()
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Cast",
                        style = MaterialTheme.typography.titleLarge,

                        )
                    Spacer(modifier = Modifier.height(5.dp))
                    CastList(movie.casts)
                }
            }

        }

    })
}

@Composable
fun CastList(casts: List<CastX>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(bottom = 30.dp, top = 10.dp, start = 5.dp),

        ) {
        items(casts) { item ->
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularCastImage(url = item.profile_path, size = 90.dp)
                Text(text = item.name, style = MaterialTheme.typography.titleSmall)
            }

        }
    }

}



