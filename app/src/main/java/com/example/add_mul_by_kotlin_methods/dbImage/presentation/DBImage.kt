package com.example.add_mul_by_kotlin_methods.dbImage.presentation

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.add_mul_by_kotlin_methods.dbImage.viewModel.DBViewModel
import com.example.add_mul_by_kotlin_methods.dbImage.room.ImageEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DBImage(viewModel: DBViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val images by viewModel.images.observeAsState(emptyList())
    var pickedImageUri by remember { mutableStateOf<Uri?>(null) }


    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
           try {
               pickedImageUri = uri
               viewModel.readBytes(context, pickedImageUri!!)
               viewModel.addImage(context = context,ImageEntity(imageData = pickedImageUri.toString()))
           }catch (e:Exception){
               println("InsertError:----$e")
           }
        }


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {

            TopAppBar(
                title = {
                    Text(
                        "Image in Room",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                actions = {
                    IconButton(
                        onClick = { galleryLauncher.launch("image/*") }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Selector",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }

                }
            )
        }
        items(images.size) { index ->
            /*val uri = Uri.parse(images[index].imageData)
            val bytArrayImage = viewModel.readBytes(context, uri)
            val bitmap = viewModel.byteArrayToBitmap(bytArrayImage!!)*/
            //val bitmap = viewModel.decodeFromBase64(images[index].imageData)
            val base64ImageData = images[index].imageData
            val byteArray = viewModel.decodeFromBase64(base64ImageData)
            val bitmap = viewModel.byteArrayToBitmap(byteArray)
            bitmap?.let {
                ImageItem(
                    context = context,
                    imageBitMap = it,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    onRemove = {
                        viewModel.deleteImage(images[index].id)
                    }
                )
            } ?: run {
                Text(text = "No image available", color = MaterialTheme.colorScheme.onBackground)
            }

        }

    }


}





