package com.example.add_mul_by_kotlin_methods.roomHiltRetro.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieTopBar(title:String,icon:ImageVector,onClick:()->Unit) {
    TopAppBar(
        title = { Text(title, style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.primary)) },
        actions = {
            IconButton(onClick = onClick) {
                Icon(icon, contentDescription = "fav", tint = Color.Red)
            }
        }

    )


}