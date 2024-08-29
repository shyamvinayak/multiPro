package com.example.add_mul_by_kotlin_methods.PhotoPic.Component

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.add_mul_by_kotlin_methods.R

@Composable
fun ImageDisplay(
    imageUri: Uri?,
    modifier: Modifier = Modifier
) {
    val painter = // Placeholder image on error
        rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = imageUri)
                .apply(block = fun ImageRequest.Builder.() {
                    crossfade(true)
                    error(R.drawable.ic_launcher_foreground) // Placeholder image on error
                }).build()
        )

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = "Selected Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }

}