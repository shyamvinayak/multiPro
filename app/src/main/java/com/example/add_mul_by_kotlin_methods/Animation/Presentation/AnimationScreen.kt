package com.example.add_mul_by_kotlin_methods.Animation.Presentation

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.add_mul_by_kotlin_methods.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


data class ListItem(val id: Int, val name: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimationScreen() {
    val listValues = listOf(
        ListItem(id = 1, name = "Like button Click Animation"),
        ListItem(id = 2, name = "Circular Progress Bar"),
        ListItem(id = 3, name = "Bouncing Location")
    )

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedItemId by remember { mutableStateOf<Int?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        LazyColumn {

            item {
                Text(
                    "Simple Animations",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )

            }
            items(listValues, key = { item -> item.id }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable {
                            selectedItemId = it.id
                            showBottomSheet = true
                        },
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Text(
                        text = it.name,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState,
            modifier = Modifier.fillMaxHeight(0.5f)
        ) {
            when (selectedItemId) {
                1 -> LikeButtonAnim()

                2 -> CircularProgress()

                3 -> BouncingLoc()
            }
        }
    }

}

@Composable
fun LikeButtonAnim() {

    var liked by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (liked) 1.5f else 1f)
    val color by animateColorAsState(if (liked) Color.Red else Color.Gray)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Click Like Button",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(15.dp),
            color = MaterialTheme.colorScheme.onPrimary
        )
        IconButton(
            onClick = { liked =!liked},
            modifier = Modifier
                .size(60.dp)
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale
                )
                .background(color = Color.Transparent, shape = CircleShape)
                .padding(8.dp)
            ) {
            Icon(
                imageVector = Icons.Filled.ThumbUp,
                contentDescription = "Like Button",
                tint = color
            )
        }
    }
}

@Composable
fun BouncingLoc() {

    val startColor = Color.Red
    val endColor = Color.Gray
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val animatedColor by infiniteTransition.animateColor(
        initialValue = startColor,
        targetValue = endColor,
        animationSpec = infiniteRepeatable(
            tween(800, easing = FastOutLinearInEasing),
            RepeatMode.Reverse,
        ), label = ""
    )

    val position by infiniteTransition.animateFloat(
        initialValue = -50f,
        targetValue = 50f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse), label = ""
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bouncing Location",
            fontSize = 18.sp,
            modifier = Modifier
                .padding(bottom = 10.dp),
            color = MaterialTheme.colorScheme.onPrimary
        )

        Box(modifier = Modifier.fillMaxSize()) {
            Card(
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                elevation = CardDefaults.cardElevation(8.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.map_img),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Icon(
                imageVector = Icons.Filled.Place,
                contentDescription = "",
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.Center)
                    .offset(y = position.dp),
                tint = animatedColor
            )


        }
    }


}

@Composable
fun CircularProgress() {
    var currentProgress by remember { mutableStateOf(0f) }
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Circular Progress Bar ",
            fontSize = 18.sp,
            modifier = Modifier
                .padding(bottom = 0.dp),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        //Spacer(modifier = Modifier.height(5.dp))
        Button(onClick = {
            loading = true
            scope.launch {
                loadProgress { progress ->
                    currentProgress = progress
                }
                loading = false
            }

        }, enabled = !loading) {
            Text(
                "Click Me",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        //Spacer(modifier = Modifier.height(10.dp))
        if (loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = { currentProgress },
                    modifier = Modifier.size(100.dp),
                    color = MaterialTheme.colorScheme.primary,
                )

                Text(
                    text = "${(currentProgress * 100).toInt()}%",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }


    }
}

suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    for (i in 1..100) {
        updateProgress(i.toFloat() / 100)
        delay(100)
    }
}

 
