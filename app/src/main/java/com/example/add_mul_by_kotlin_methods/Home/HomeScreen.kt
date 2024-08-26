package com.example.add_mul_by_kotlin_methods.Home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.add_mul_by_kotlin_methods.Navigation.Screens
import com.example.add_mul_by_kotlin_methods.R

data class GridItem(
    val id: Int,
    val title: String,
    val icon: Int
)

@Composable
fun HomeScreen(title: String,navController: NavController) {
    val gridItem = listOf(
        GridItem(1, "Calculator", R.drawable.calculator),
        GridItem(2, "BasicMaths", R.drawable.math),
        GridItem(3, "RetrofitAPI", R.drawable.api),
        GridItem(4, "Animation", R.drawable.cube),
        GridItem(5, "Calender", R.drawable.calender),
    )

    Surface(modifier = Modifier.padding(all = 20.dp)) {
        Column(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = title,
                fontSize = 25.sp,
                fontWeight = FontWeight.W400,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
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
                }
            })
        }
    }
}





