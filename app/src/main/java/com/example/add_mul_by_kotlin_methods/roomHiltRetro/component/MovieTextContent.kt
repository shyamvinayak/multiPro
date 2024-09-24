package com.example.add_mul_by_kotlin_methods.roomHiltRetro.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.domain.MovieDetails
import okhttp3.internal.toImmutableList

@Composable
fun MovieTextContent(
    movie: MovieDetails,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
    ) {
        val (imdb,ranking, title, overview) = createRefs()
        IMDBRanking(
            modifier = Modifier
                .constrainAs(imdb) {
                    start.linkTo(parent.start, 24.dp)
                    top.linkTo(parent.top, 16.dp)
                }
        )
        VoteMovie(
            voteAverage = movie.movie.vote_average,
            voteCount = movie.movie.vote_count.toInt(),
            modifier = Modifier
                .constrainAs(ranking) {
                    start.linkTo(imdb.end, 8.dp)
                    end.linkTo(parent.end, 24.dp)
                    top.linkTo(imdb.top)
                    bottom.linkTo(imdb.bottom)
                    width = Dimension.fillToConstraints
                }
        )
        MovieTitle(
            movie = movie,
            modifier = Modifier
                .constrainAs(title) {
                    linkTo(
                        start = parent.start,
                        startMargin = 24.dp,
                        end = parent.end,
                        endMargin = 24.dp
                    )
                    top.linkTo(imdb.bottom, 8.dp)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            text = movie.movie.overview,
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .constrainAs(overview) {
                    linkTo(
                        start = parent.start,
                        startMargin = 24.dp,
                        end = parent.end,
                        endMargin = 24.dp
                    )
                    top.linkTo(title.bottom, 8.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent

                }
        )


        Cast(
            list = movie.movie.casts.toImmutableList(),
            modifier = Modifier
                .constrainAs(createRef()) {
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                    top.linkTo(overview.bottom, 12.dp)
                    bottom.linkTo(parent.bottom,24.dp)
                    width = Dimension.fillToConstraints
                }
        )


    }


}
