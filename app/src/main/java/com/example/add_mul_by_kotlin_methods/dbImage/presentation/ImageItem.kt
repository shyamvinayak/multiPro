package com.example.add_mul_by_kotlin_methods.dbImage.presentation

import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import com.example.add_mul_by_kotlin_methods.dbImage.component.DismissBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageItem(
    context:Context,
    imageBitMap:Bitmap,
    modifier: Modifier,
    onRemove: (Bitmap) -> Unit
) {
    val currentItem by rememberUpdatedState(imageBitMap)
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            when (it) {
                SwipeToDismissBoxValue.StartToEnd -> {
                    onRemove(currentItem)
                    Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
                }

                SwipeToDismissBoxValue.EndToStart -> {
                    onRemove(currentItem)
                    Toast.makeText(context, "Item archived", Toast.LENGTH_SHORT).show()
                }

                SwipeToDismissBoxValue.Settled -> return@rememberSwipeToDismissBoxState false
            }
            return@rememberSwipeToDismissBoxState true
        },
        // positional threshold of 25%
        positionalThreshold = { it * .25f }
    )

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = { DismissBackground(dismissState = dismissState) },
        content = {
            Image(
                bitmap = imageBitMap.asImageBitmap(),
                contentDescription = "Image from ByteArray",
                modifier = modifier
            )
        }
    )

}