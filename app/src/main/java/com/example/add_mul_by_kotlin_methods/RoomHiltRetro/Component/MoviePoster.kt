package com.example.add_mul_by_kotlin_methods.RoomHiltRetro.Component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.add_mul_by_kotlin_methods.R
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.presentation.FavoriteButton

@Composable
fun MoviePoster (
    imagePath: String,
    size: Dp,
    modifier: Modifier = Modifier,
    onClick: (Boolean) -> Unit
){
    Card(
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(0.5.dp, Color.Gray),
        modifier = modifier
            .height(size)
    ) {
        Box (modifier = Modifier
            .fillMaxSize()){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imagePath)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.placeholder),
                contentDescription = "movie poster",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .padding(6.dp)
                    .size(32.dp),
                color = Color(0x77000000)
            ) {
                FavoriteButton(modifier = Modifier.padding(8.dp), onClick = {onClick(it)})
            }
        }
    }
}