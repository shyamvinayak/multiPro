package com.example.add_mul_by_kotlin_methods.RoomHiltRetro.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.domain.Movie
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.presentation.FavoriteButton

@Composable
fun MovieListItem(
    movie: Movie,
    isInWishlist: Boolean,
    onItemClick: () -> Unit,
    onWishlistClick: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable(onClick = onItemClick)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.background)
            .clip(RoundedCornerShape(30.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onSurface),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = movie.poster_path
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(98.dp, 145.dp)
                    .padding(16.dp),
                contentScale = ContentScale.Inside
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(Modifier.weight(3f)) {
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
                        color = MaterialTheme.colorScheme.onPrimary,
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

            }
            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .padding(6.dp)
                    .size(32.dp)
                    .weight(1f),
                color = Color(0x77000000)
            ) {
                FavoriteButton(
                    modifier = Modifier.padding(8.dp),
                    isInitiallyFavorite = isInWishlist,
                    onClick = onWishlistClick
                )
            }
        }
    }
}

@Preview
@Composable
fun MovieListPreview() {
    Movie(
        movie_id = 2118,
        overview = "Three detectives in the corrupt and brutal L.A. police force of the 1950s use differing methods to uncover a conspiracy behind the shotgun slayings of the patrons at an all-night diner.",
        vote_average = 7.8,
        poster_path = "https://image.tmdb.org/t/p/original/lWCgf5sD5FpMljjpkRhcC8pXcch.jpg",
        release_date = "Fri, 09/19/1997",
        backdrop_path = "https://image.tmdb.org/t/p/original/fGbM2cqPauRu7ALFPSTDBH9FgpU.jpg",
        adult = 0.0,
        casts = " ",
        original_title = "L.A. Confidential",
        original_language = "en",
        popularity = 20.32,
        vote_count = 4480.0,
    )
}