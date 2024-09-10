package com.example.add_mul_by_kotlin_methods.roomHiltRetro.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.add_mul_by_kotlin_methods.R

@Composable
fun MovieVertical(
    moviePoster: String,
    movieTitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
        Card(
            shape = RoundedCornerShape(24.dp),
            border = BorderStroke(0.5.dp, Color.Gray),
            modifier = modifier
                .clickable {
                    onClick()
                }
        ) {
            Row(
                modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content = {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(moviePoster)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = "movie poster",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize().weight(1f)
                )
                Text(
                    movieTitle,
                    textAlign = TextAlign.Center,
                    modifier = modifier.weight(2f),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            })

        }



}