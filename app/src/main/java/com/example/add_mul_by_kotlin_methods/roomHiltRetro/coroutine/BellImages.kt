package com.example.add_mul_by_kotlin_methods.roomHiltRetro.coroutine

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.add_mul_by_kotlin_methods.R

@Composable
fun BellIcon(animation: Boolean, angle: Float, onClick: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.ic_bell), // Replace with your bell image resource
        contentDescription = null,
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
        modifier = Modifier
            .size(100.dp)
            .padding(16.dp)
            .graphicsLayer(
                scaleX = 1f,
                scaleY = 1f,
                rotationZ = if (animation) angle else 0f
            )
            .clickable {
                onClick()
            }
    )
}
