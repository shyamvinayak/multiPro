package com.example.add_mul_by_kotlin_methods.roomHiltRetro.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.add_mul_by_kotlin_methods.R

@ExperimentalCoilApi
@Composable
fun Poster(
    imageUrl: String,
    screenHeight: Int,
    runtime: Int,
    releaseDate: String,
    voteAverage: Double
) {
    Row(
        modifier = Modifier
            .height((screenHeight * 0.5f).dp)
            .padding(
                bottom = 16.dp, start = 16.dp,
                end = 16.dp,
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {


        //Image Card
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .height((screenHeight * 0.65f).dp)
                .weight(2f)
                .padding(end = 8.dp)
                .background(Color.Transparent),
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                    }).build()
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {


            //Rating Card
            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_round_star_24),
                        contentDescription = "Rating",
                        tint = Color(0xFFFFBC58),
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        text = "Rating",
                        fontWeight = FontWeight.W300,
                        fontSize = 12.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "$voteAverage/10",
                        fontWeight = FontWeight.W600,
                        modifier = Modifier.weight(1f)
                    )
                }
            }


            //Duration Card
            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_round_access_time_filled_24),
                        contentDescription = "Rating",
                        tint = Color(0xFF008956),
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        text = "Duration",
                        fontWeight = FontWeight.W300,
                        fontSize = 12.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = runtime.toString() + "min",
                        fontWeight = FontWeight.W600,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            //Release Date Card
            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_round_date_range_24),
                        contentDescription = "Rating",
                        tint = Color(0xFF811111),
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        text = "Release Year",
                        fontWeight = FontWeight.W300,
                        fontSize = 12.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = releaseDate,
                        fontWeight = FontWeight.W600,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}



@OptIn(ExperimentalCoilApi::class)
@Preview(showBackground = true)
@Composable
fun PreviewPoster(){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    Poster(
        imageUrl ="https://image.tmdb.org/t/p/original/rpS7ROczWulqfaXG2klYapULXKm.jpg" ,
        screenHeight = screenHeight,
        runtime = 8,
        releaseDate = "Wed, 06/19/2019" ,
        voteAverage = 6.0,
    )
}