package com.example.add_mul_by_kotlin_methods.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.navigation.NavController
import com.example.add_mul_by_kotlin_methods.navigation.Screens
import com.example.add_mul_by_kotlin_methods.R

data class GridItem(
    val id: Int,
    val title: String,
    val icon: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val activity = context as Activity
    val showDialog = remember { mutableStateOf(false) }
    val gridItem = listOf(
        GridItem(1, "Calculator", R.drawable.calculator),
        GridItem(2, "BasicMaths", R.drawable.math),
        GridItem(3, "RetrofitAPI", R.drawable.api),
        GridItem(4, "Animation", R.drawable.cube),
        GridItem(5, "Calender", R.drawable.calender),
        GridItem(6, "PDFGen", R.drawable.pdf),
        GridItem(7, "Cropper", R.drawable.picture),
        GridItem(8, "ImageDB", R.drawable.database),
    )

    BackHandler {
        showDialog.value = true
    }

    ExitHomeDialog(showDialog = showDialog,
        onConfirm = {
            finishAffinity(activity)
        },
        onDismiss = {
            
        })

    Scaffold(
        topBar = {
            TopAppBar(
                title =
                {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "logo",
                        modifier = Modifier.size(200.dp)
                    )
                }
            )
        },
        content = { contentPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
                GridViewScreen(gridItem, onItemClick = { gridItem: GridItem ->
                    when (gridItem.id) {
                        1 -> {
                            navController.navigate(Screens.Calculator.route)
                        }

                        2 -> {
                            navController.navigate(Screens.BasicCal.route)
                        }

                        3 -> {
                            navController.navigate(Screens.MoviesMain.route)
                        }

                        4 -> {
                            navController.navigate(Screens.Animation.route)
                        }

                        5 -> {
                            navController.navigate(Screens.Calender.route)
                        }

                        6 -> {
                            navController.navigate(Screens.PDFGen.route)
                        }

                        7 -> {
                            navController.navigate(Screens.PhotoPic.route)
                        }

                        8 -> {
                            navController.navigate(Screens.DBImage.route)
                        }
                    }
                })
            }
        }
    )

}





