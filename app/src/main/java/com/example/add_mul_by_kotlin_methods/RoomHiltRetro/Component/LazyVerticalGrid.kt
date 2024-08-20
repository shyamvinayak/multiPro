package com.example.add_mul_by_kotlin_methods.RoomHiltRetro.Component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.add_mul_by_kotlin_methods.RoomHiltRetro.domain.Movie
import androidx.constraintlayout.compose.ConstraintLayout

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

@Composable
fun MovieItem(
    movieModel: Movie,
    modifier: Modifier = Modifier,
    goToMovieDetail: (Int) -> Unit,
    isFavClick: (Boolean) -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 12.dp)
    ) {
        val (movieCard, text) = createRefs()
        MoviePoster(
            imagePath = movieModel.poster_path,
            size = 240.dp,
            modifier = Modifier
                .clickable {
                    goToMovieDetail(movieModel.movie_id)
                }
                .constrainAs(movieCard) {
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                    top.linkTo(parent.top)
                },
            onClick = isFavClick
        )
        Text(
            text = movieModel.original_title,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .clickable {
                    goToMovieDetail(movieModel.movie_id)
                }
                .constrainAs(text) {
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                    linkTo(
                        top = movieCard.bottom,
                        topMargin = 8.dp,
                        bottom = parent.bottom,
                        bottomMargin = 8.dp
                    )
                }
        )
    }
}