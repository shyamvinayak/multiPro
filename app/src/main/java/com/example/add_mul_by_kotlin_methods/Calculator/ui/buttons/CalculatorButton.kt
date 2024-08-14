package com.example.add_mul_by_kotlin_methods.Calculator.ui.buttons

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalculatorSingleButton(
    text: String,
    onClick: ( ) -> Unit,
    onLongClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(4.dp)
            .border(
                width = 1.dp,
                shape = CircleShape,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        Color.Black.copy(alpha = .15f),
                    )
                )
            )
            .scale(1f)
            .combinedClickable(
                onClick = {
                    onClick()
                },
                onLongClick = { onLongClick() }
            )

    ) {
        Text(
            text = text, fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Center)
        )
    }

}

