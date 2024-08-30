package com.example.add_mul_by_kotlin_methods.roomHiltRetro.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.domain.Movie

@Composable
fun LazyVerticalGridMovies(
    list: LazyPagingItems<Movie>,
    modifier: Modifier = Modifier,
    state: LazyGridState = rememberLazyGridState(),
    contentPaddingValues: PaddingValues = PaddingValues(vertical = 75.dp),
    itemClicked: (it: Int) -> Unit,
    isFavClick: (Int, Boolean) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(horizontal = 0.dp),
        contentPadding = contentPaddingValues,
        state = state
    ) {
        items(list.itemCount) { movieModel ->
            list[movieModel]?.let { movie ->
                MovieItem(
                    movieModel = movie,
                    goToMovieDetail = { itemClicked(movie.movie_id) },
                    isFavClick = { isFavClick(movie.movie_id, true) })
            }
        }
    }
}

