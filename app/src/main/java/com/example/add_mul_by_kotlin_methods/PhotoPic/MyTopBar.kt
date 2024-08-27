package com.example.add_mul_by_kotlin_methods.PhotoPic

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    title: String,
    /* onNavigationClick: () -> Unit,
     onActionClick: () -> Unit*/
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        /* navigationIcon = {
             IconButton(onClick = onNavigationClick) {
                 Icon(Icons.Filled.Menu, contentDescription = "Menu")
             }
         },
         actions = {
             IconButton(onClick = onActionClick) {
                 Icon(Icons.Filled.Search, contentDescription = "Search")
             }
         },*/
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary),

        )
}