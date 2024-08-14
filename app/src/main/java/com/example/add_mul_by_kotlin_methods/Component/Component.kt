package com.example.add_mul_by_kotlin_methods.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.add_mul_by_kotlin_methods.R
import com.example.add_mul_by_kotlin_methods.RetrofitPro.model.CommonDataItem

@Composable
fun CoverImage(imageUrl: String) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .height(120.dp)
            .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Banner Image",
            contentScale = ContentScale.Fit,
        )
    }
}

@Composable
fun RatingStars(rating: Double) {
    val fullStars = rating.toInt()
    val halfStar = (rating - fullStars) >= 0.5
    val starCount = if (halfStar) fullStars + 1 else fullStars

    Row {
        repeat(starCount) {

            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Star",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
        if (halfStar) {
            Icon(
                imageVector = Icons.Sharp.Star,
                contentDescription = "Half Star",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
        repeat(5 - starCount) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = "Empty Star",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun MovieCard(movie: CommonDataItem) {
    Column(
        modifier = Modifier
            .padding(all = 20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(Modifier.fillMaxSize()) {
            Image(
                painter = rememberAsyncImagePainter(movie.poster_path),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
    }
}

@Composable
fun CircularCastImage(url: String, size: Dp) {
    val placeholderImage: Painter = painterResource(id = R.drawable.dummy)
    val errorImage: Painter = painterResource(id = R.drawable.error_img)
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = "Cast Image",
        placeholder = placeholderImage,
        error = placeholderImage,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(size)
            .clip(CircleShape) // Clip the image to a circle
    )
}
