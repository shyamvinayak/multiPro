package com.example.add_mul_by_kotlin_methods

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.add_mul_by_kotlin_methods.Navigation.NavigationGraph
import com.example.add_mul_by_kotlin_methods.Navigation.Screens
import com.example.add_mul_by_kotlin_methods.ui.theme.MariAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class StartScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MariAppTheme {
                Surface(Modifier.fillMaxWidth()) {
                    Box(Modifier.safeDrawingPadding()) {
                        NavigationGraph()
                    }
                }
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(2f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }
            ),
        )
        delay(3000L)
        navController.navigate(Screens.Home.route)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ma_logo),
            modifier = Modifier.size(300.dp, 300.dp),
            contentDescription = "logo",
        )
    }


}
