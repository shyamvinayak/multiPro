package com.example.add_mul_by_kotlin_methods.roomHiltRetro.presentation


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.example.add_mul_by_kotlin_methods.R
import kotlin.math.absoluteValue

/*https://www.c-sharpcorner.com/article/swipeable-image-slider-in-jetpack-compose*/

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WishListScreen(
    navController: NavController,
    viewModel: MovieViewModel = hiltViewModel(),
    imageCornerRadius: Dp = 16.dp,
    imageHeight: Dp = 250.dp,
    pagerPaddingValues: PaddingValues = PaddingValues(horizontal = 65.dp),
) {

    LaunchedEffect(Unit) {
        viewModel.loadWishlistDetails()
        viewModel.loadSuggestedMovie()
    }
    val movies by viewModel.suggestedMovieDetails
    val wishlistDetails by viewModel.wishlistDetails

    val wishlistPagerState = rememberPagerState(0, 0f) { wishlistDetails.size }
    val moviePagerState = rememberPagerState(0, 0f) { movies.size }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            "WishList",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(start = 15.dp, top = 16.dp, bottom = 8.dp)
                .align(Alignment.Start)
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            HorizontalPager(
                pageSize = PageSize.Fill,
                state = wishlistPagerState,
                contentPadding = pagerPaddingValues,
                modifier = Modifier.weight(1f)
            ) { page ->
                val pageOffset =
                    (wishlistPagerState.currentPage - page) + wishlistPagerState.currentPageOffsetFraction

                val scaleFactor = 0.75f + (1f - 0.75f) * (1f - pageOffset.absoluteValue)

                Box(modifier = Modifier
                    .graphicsLayer {
                        scaleX = scaleFactor
                        scaleY = scaleFactor
                    }
                    .alpha(
                        scaleFactor.coerceIn(0f, 1f)
                    )
                    .padding(10.dp)
                    .clip(RoundedCornerShape(imageCornerRadius))) {

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).scale(Scale.FILL)
                            .crossfade(true).data(wishlistDetails[page].movie.poster_path)
                            .build(),
                        contentDescription = "Image",
                        contentScale = ContentScale.Fit,
                        placeholder = painterResource(id = R.drawable.placeholder),
                        modifier = Modifier
                            .height(imageHeight)
                            .alpha(if (wishlistPagerState.currentPage == page) 1f else 0.5f)
                    )

                }
            }
        }

        Text(
            "Suggested for you",
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(start = 15.dp, top = 16.dp, bottom = 8.dp)
                .align(Alignment.Start)

        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            HorizontalPager(
                pageSize = PageSize.Fill,
                state = moviePagerState,
                contentPadding = pagerPaddingValues,
                modifier = Modifier.weight(1f)
            ) { page ->
                val pageOffset =
                    (moviePagerState.currentPage - page) + moviePagerState.currentPageOffsetFraction

                val scaleFactor = 0.75f + (1f - 0.75f) * (1f - pageOffset.absoluteValue)

                Box(modifier = Modifier
                    .graphicsLayer {
                        scaleX = scaleFactor
                        scaleY = scaleFactor
                    }
                    .alpha(
                        scaleFactor.coerceIn(0f, 1f)
                    )
                    .padding(10.dp)
                    .clip(RoundedCornerShape(imageCornerRadius))) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).scale(Scale.FILL)
                            .crossfade(true).data(movies[page].movie.poster_path).build(),
                        contentDescription = "Image",
                        contentScale = ContentScale.Fit,
                        placeholder = painterResource(id = R.drawable.placeholder),
                        modifier = Modifier
                            .height(imageHeight)
                            .alpha(if (moviePagerState.currentPage == page) 1f else 0.5f)
                    )
                }
            }
        }


    }

    /* Box(modifier = Modifier.fillMaxSize()) {
         LazyColumn {
             item {
                 Text(
                     "WishList",
                     fontSize = 16.sp,
                     fontWeight = FontWeight.Bold,
                     color = MaterialTheme.colorScheme.primary,
                     modifier = Modifier
                         .padding(start = 15.dp, top = 16.dp, bottom = 8.dp)
                 )
             }
             item {
                 LazyRow(
                     Modifier
                         .padding(20.dp)
                         .fillMaxWidth()
                 ) {
                     items(wishlistDetails) { wishListData ->
                         val wishListItems =
                         MovieVertical(
                             moviePoster = wishListData.movie.poster_path,
                             movieTitle = wishListData.movie.original_title,
                             modifier = Modifier
                                 .size(100.dp, 100.dp)
                                 .padding(vertical = 20.dp),
                             onClick = {
                                 navController.navigate("single_movie/${wishListData.movie.movie_id}")
                                 viewModel.clearSearchResults()
                             })

                     }
                 }
             }

             item {
                 Spacer(modifier = Modifier.height(10.dp))
             }

             item {
                 Text(
                     "Suggested for you",
                     fontSize = 16.sp,
                     fontWeight = FontWeight.Bold,
                     color = MaterialTheme.colorScheme.primary,
                     modifier = Modifier
                         .padding(start = 15.dp, top = 16.dp, bottom = 8.dp)

                 )
             }
             item {
                 LazyRow(
                     Modifier
                         .padding(20.dp)
                         .fillMaxWidth()
                 ) {
                     items(movies) { movie ->
                         MovieItem(movieModel = movie.movie.toMovie(),
                             goToMovieDetail = {
                                 navController.navigate("single_movie/${movie.movie.movie_id}")
                             },
                             isFavClick = {})

                     }
                 }

             }
         }
     }*/
}