package com.example.add_mul_by_kotlin_methods.RoomHiltRetro.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.domain.Movie

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Row ( modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(16.dp)){
            Box (modifier = Modifier
                .height(100.dp)
                .weight(2f)
                .fillMaxWidth()){
                AsyncImage(
                    model = movie.poster_path,
                    contentDescription = movie.original_title,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(180.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight().weight(3f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = movie.original_title,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "VoteRange",
                        tint = Color(0xFFeec023),
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = movie.vote_average.toString(),
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.labelLarge.copy(fontSize = 16.sp),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = movie.release_date,
                    color = Color.LightGray,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                /* Text(
                     text = movie.overview,
                     modifier = Modifier.fillMaxWidth()
                 )
                 Spacer(modifier = Modifier.height(8.dp))*/

            }
        }

       /* Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)
        ) {
            AsyncImage(
                model = movie.poster_path,
                contentDescription = movie.original_title,
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = movie.original_title,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "VoteRange",
                        tint = Color(0xFFeec023),
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = movie.vote_average.toString(),
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.labelLarge.copy(fontSize = 16.sp),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = movie.release_date,
                    color = Color.LightGray,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = movie.overview,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

            }
        }*/

    }
}

@Preview
@Composable
fun BeerItemPreview() {
    MovieItem(
        movie = Movie(
            //id = "1",
            original_language = "en",
            original_title = "Would You Rather",
            release_date = "Sun, 10/14/2012",
            vote_average = 6.0,
            vote_count = 852.0,
            backdrop_path = "https://image.tmdb.org/t/p/original/f41eZ9eD0V8bYacXCheqgg4KZRL.jpg",
            poster_path = "https://image.tmdb.org/t/p/original/uZQYjmEXeET1fOz1av7GYY7xENG.jpg",
            popularity = 12.63,
            adult = 0.0,
            casts = "",
            movie_id = 97051,
            overview = "Desperate to help her ailing brother, a young woman agrees to compete in a deadly game of " + "Would You Rather" + " hosted by a sadistic aristocrat.",

            ),
        modifier = Modifier.fillMaxWidth()
    )
}