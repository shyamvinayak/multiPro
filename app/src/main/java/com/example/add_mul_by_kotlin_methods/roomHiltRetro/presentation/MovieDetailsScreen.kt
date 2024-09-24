package com.example.add_mul_by_kotlin_methods.roomHiltRetro.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.CloseMovie
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.FavIcon
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.MoviePosterDetail
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.MovieTextContent


@Composable
fun MovieDetailsScreen(
    navController: NavController,
    movieId: String,
    viewModel: MovieViewModel = hiltViewModel()
) {
    val movieDetails by viewModel.movieDetails.collectAsState()
    val state = rememberScrollState()
    LaunchedEffect(movieId) {
        viewModel.getMovieDetails(movieId.toInt())
    }
    val context = LocalContext.current
    //Disable BackPress
    /*BackHandler {}*/
    var isFavorited by remember { mutableStateOf(false) }
    movieDetails.forEach {
        isFavorited = it.wishlist?.isFavorite?:false
            ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state)
                .disableBackSwipe()
                .background(MaterialTheme.colorScheme.surface) //backgroundNight
        ) {

            val (poster, close,fav, textContent) = createRefs()
            MoviePosterDetail(
                path = it.movie.poster_path,
                modifier = Modifier
                    .constrainAs(poster) {
                        top.linkTo(parent.top)
                        linkTo(
                            start = parent.start,
                            end = parent.end
                        )
                    }
                    .clickable {
                        //viewModel.openYoutube(context, it)
                    }
            )

            CloseMovie(
                navController = navController,
                modifier = Modifier.constrainAs(close) {
                    top.linkTo(parent.top, 24.dp)
                    start.linkTo(parent.start, 24.dp)
                }
            )

            FavIcon(
                isFavourited = isFavorited,
                modifier = Modifier.constrainAs(fav) {
                    top.linkTo(parent.top, 24.dp)
                    end.linkTo(parent.end, 24.dp)
                },
                onFav = {clicked->
                    if(clicked){
                        isFavorited = clicked
                        viewModel.addToWishList(movieId.toInt(),isFavorited)
                    }else{
                        isFavorited = clicked
                        viewModel.removeWishList(movieId.toInt())
                    }
                }
            )
            MovieTextContent(
                movie = it,
                modifier = Modifier
                    .constrainAs(textContent) {
                        linkTo(
                            top = poster.bottom,
                            topMargin = -150.dp,
                            bottom = parent.bottom,
                            bias = 0f
                        )
                        linkTo(
                            start = parent.start,
                            end = parent.end
                        )
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    }
            )

        }
    }

}

//Disable SwipeBack
fun Modifier.disableBackSwipe(): Modifier {
    return this.pointerInput(Unit) {
        detectHorizontalDragGestures { change, dragAmount ->
            // Do nothing to intercept the swipe and disable back press
            change.consume() // Consume the change to prevent further processing
        }
    }
}

