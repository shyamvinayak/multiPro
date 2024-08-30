package com.example.add_mul_by_kotlin_methods.roomHiltRetro.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun MovieName(movieName:String) {
    Text(
        text = movieName,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
        color = MaterialTheme.colorScheme.onPrimary,
    )
}