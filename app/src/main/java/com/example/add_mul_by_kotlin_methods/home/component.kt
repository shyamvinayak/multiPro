package com.example.add_mul_by_kotlin_methods.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun GridViewScreen(gridItems: List<GridItem>, onItemClick: (GridItem) -> Unit) {

    LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(10.dp)) {
        items(gridItems) { item ->
            GridItemView(item, onItemClick)
        }
    }
}

@Composable
fun GridItemView(gridItem: GridItem, onItemClick: (GridItem) -> Unit) {

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(gridItem.icon)
            .crossfade(true)
            .build()
    )

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemClick(gridItem) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painter,
                contentDescription = "GIF loading",
                modifier = Modifier
                    .size(48.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = gridItem.title, style = MaterialTheme.typography.bodyLarge)
        }
    }
}



fun isGif(fileName: String): Boolean {
    return fileName.endsWith(".gif", ignoreCase = true)
}


