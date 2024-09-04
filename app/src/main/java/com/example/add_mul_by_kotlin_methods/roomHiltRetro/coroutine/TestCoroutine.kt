import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.component.MovieItem
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.coroutine.BellIcon
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.domain.Movie
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.presentation.MovieViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

@Composable
fun TestCoroutine(navController: NavController, viewModel: MovieViewModel = hiltViewModel()) {

    val bell1Duration = 10000L // 10 seconds
    val bell2Duration = 60000L // 1 minute
    val bell3Duration = 300000L // 5 minutes

    // State to control if animation should run
    var isAnimating1 by remember { mutableStateOf(true) }
    var isAnimating2 by remember { mutableStateOf(true) }
    var isAnimating3 by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    val movieTitle1 = remember { mutableStateOf("Waiting for movie...") }
    val movieTitle2 = remember { mutableStateOf("Waiting for movie...") }
    val movieTitle3 = remember { mutableStateOf("Waiting for movie...") }
    val movieList = remember { mutableStateListOf<Movie>() }


    // Define infinite transition
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = -15f,
        targetValue = 15f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1000
                -15f at 0
                15f at 500
                -15f at 1000
            },
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    // Coroutine scope to handle the delay
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            while (true) {
                isAnimating1 = true
                delay(bell1Duration)
                updateMovieTitle(viewModel, movieTitle1, movieList)
                isAnimating1 = false
                delay(bell1Duration)
            }
        }
        coroutineScope.launch {
            while (true) {
                isAnimating2 = true
                delay(bell2Duration)
                updateMovieTitle(viewModel, movieTitle2, movieList)
                isAnimating2 = false
                delay(bell1Duration)
            }
        }
        coroutineScope.launch {
            while (true) {
                isAnimating3 = true
                delay(bell3Duration)
                updateMovieTitle(viewModel, movieTitle3, movieList)
                isAnimating3 = false
                delay(bell1Duration)
            }
        }
        // Stop the animation
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier.fillMaxSize().weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    BellIcon(
                        animation = isAnimating1,
                        angle = angle,
                        onClick = { isAnimating1 = !isAnimating1 })
                    Text(
                        "10Sec",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier.fillMaxSize().weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    BellIcon(
                        animation = isAnimating2,
                        angle = angle,
                        onClick = { isAnimating2 = !isAnimating2 })
                    Text(
                        "1mnt",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier.fillMaxSize().weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    BellIcon(
                        animation = isAnimating3,
                        angle = angle,
                        onClick = { isAnimating3 = !isAnimating3 })
                    Text(
                        "5mnt",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(3f)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                contentPadding = PaddingValues(vertical = 75.dp)
            ) {
                items(movieList) { movie ->
                    MovieItem(
                        movieModel = movie,
                        goToMovieDetail = {
                            println("MovieListId:--${it}")
                            navController.navigate("single_movie/${it}")
                        },
                        isFavClick = {}
                    )
                }

            }
        }
    }
}

fun updateMovieTitle(
    viewModel: MovieViewModel,
    movieTitleState: MutableState<String>,
    movieList: SnapshotStateList<Movie>
) {

    viewModel.currentMovie.value.let { movies ->
        if (movies.isNotEmpty()) {
            val currentIndex = viewModel.currentIndex
            movieTitleState.value = movies[currentIndex].original_title
            viewModel.currentIndex = (currentIndex + 1) % movies.size
            movieList.add(
                Movie(
                    movie_id = movies[currentIndex].movie_id,
                    original_title = movies[currentIndex].original_title,
                    original_language = movies[currentIndex].original_language,
                    overview = movies[currentIndex].overview,
                    popularity = movies[currentIndex].popularity,
                    poster_path = movies[currentIndex].poster_path,
                    backdrop_path = movies[currentIndex].backdrop_path,
                    release_date = movies[currentIndex].release_date,
                    vote_average = movies[currentIndex].vote_average,
                    vote_count = movies[currentIndex].vote_count,
                    adult = movies[currentIndex].adult,
                    casts = movies[currentIndex].casts.toString(),
                )
            )

        }
    }
}
