package com.example.add_mul_by_kotlin_methods.roomHiltRetro.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp

@Composable
fun IMDBRanking(modifier: Modifier = Modifier) {
    Text(
        text = "IMDB 7.0",
        style = MaterialTheme.typography.headlineMedium,
        color = Color.Black,
        modifier = modifier
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Color(0xFFF9C204), Color(0xFFC59902)),
                    startX = 0f,
                    endX = Float.POSITIVE_INFINITY,
                    tileMode = TileMode.Clamp
                ),
                shape = CircleShape
            )
            .padding(
                horizontal = 12.dp,
                vertical = 4.dp
            )
    )
}