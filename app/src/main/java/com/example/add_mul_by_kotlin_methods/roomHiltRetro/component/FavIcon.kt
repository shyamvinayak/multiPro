package com.example.add_mul_by_kotlin_methods.roomHiltRetro.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun FavIcon(
    isFavourited:Boolean = false,
    modifier: Modifier = Modifier,
    onFav:(Boolean)->Unit
) {
    //var isFavorited by remember { mutableStateOf(false) }

    Icon(
        imageVector = if (isFavourited) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
        contentDescription = if (isFavourited) "Remove from favorites" else "Add to favorites",
        tint = if (isFavourited) Color.Red else Color.Gray,
        modifier = modifier
            .clip(CircleShape)
            .background(
                MaterialTheme.colorScheme.background.copy(alpha = 0.6f)
            )
            .padding(8.dp)
            .clickable (
                indication = null, // Disable ripple effect
                interactionSource = remember { MutableInteractionSource() }
            ){
                onFav(!isFavourited)
            }
    )
}