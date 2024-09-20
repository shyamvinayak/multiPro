package com.example.add_mul_by_kotlin_methods.roomHiltRetro.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CloseMovie(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = Icons.Filled.Close,
        contentDescription = "Close screen",
        tint = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
            .clip(CircleShape)
            .background(
                MaterialTheme.colorScheme.background.copy(
                    alpha = 0.6f
                )
            )
            .padding(8.dp)
            .clickable {
                navController.popBackStack()
            }
    )
}