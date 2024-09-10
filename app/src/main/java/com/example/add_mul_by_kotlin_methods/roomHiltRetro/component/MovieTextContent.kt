package com.example.add_mul_by_kotlin_methods.roomHiltRetro.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.local.Entity.MovieEntity
import java.math.RoundingMode

@Composable
fun MovieTextContent(
    movie: MovieEntity,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
    ) {
        val (imdb,ranking, title, overview,cast) = createRefs()
        IMDBRanking(
            modifier = Modifier
                .constrainAs(imdb) {
                    start.linkTo(parent.start, 24.dp)
                    top.linkTo(parent.top, 16.dp)
                }
        )
        VoteMovie(
            voteAverage = movie.vote_average ?: 0.0,
            voteCount = movie.vote_count.toInt(),
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
            title = movie.original_title,
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
            text = "${movie.overview} ${movie.overview}",
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
                    bottom.linkTo(parent.bottom, 100.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent

                }
        )

      /*  Cast(images = movie.casts,
            modifier = Modifier
                .constrainAs(cast){
                    linkTo(
                        start = parent.start,
                        startMargin = 24.dp,
                        end = parent.end,
                        endMargin = 24.dp
                    )
                    top.linkTo(overview.bottom, 24.dp)
                    bottom.linkTo(parent.bottom, 100.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
        )*/
    }
}

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

@Composable
fun VoteMovie(
    voteAverage: Double,
    voteCount: Int,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier) {
        val (icon, voteAverageRef, voteCountRef) = createRefs()
        Icon(
            imageVector = Icons.Rounded.Star,
            contentDescription = "Star ranking",
            tint = Color(0XFFF9C204),
            modifier = Modifier
                .size(24.dp)
                .constrainAs(icon) {
                    start.linkTo(parent.start, 4.dp)
                    linkTo(
                        top = parent.top,
                        bottom = parent.bottom
                    )
                }
        )
        Text(
            text = voteAverage.toBigDecimal().setScale(1, RoundingMode.CEILING).toString(),
            style = MaterialTheme.typography.headlineLarge,
            color = Color(0XFFF9C204),
            modifier = Modifier
                .constrainAs(voteAverageRef) {
                    start.linkTo(icon.end, 4.dp)
                    linkTo(
                        top = icon.top,
                        bottom = icon.bottom
                    )
                }
        )
        Text(
            text = "($voteCount reviews)",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .constrainAs(voteCountRef) {
                    linkTo(
                        start = voteAverageRef.end,
                        startMargin = 8.dp,
                        end = parent.end,
                        endMargin = 24.dp
                    )
                    linkTo(
                        top = icon.top,
                        bottom = icon.bottom
                    )
                    width = Dimension.fillToConstraints
                }
        )
    }
}