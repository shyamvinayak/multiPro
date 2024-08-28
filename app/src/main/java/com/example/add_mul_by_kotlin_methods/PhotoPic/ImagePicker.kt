package com.example.add_mul_by_kotlin_methods.PhotoPic

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import java.io.File
import com.example.add_mul_by_kotlin_methods.R

@Composable
fun ImagePicker(onImagePicked: (Uri?) -> Unit) {
    val context = LocalContext.current
    val photoUri = remember { mutableStateOf<Uri?>(null) }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            onImagePicked(uri)
        }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                onImagePicked(photoUri.value)
            }
        }


    val takePicture = {
        val photoFile = File.createTempFile("photo", ".jpg", context.cacheDir)
        photoUri.value = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            photoFile
        )
        photoUri.value?.let { uri ->
            cameraLauncher.launch(uri)
        }

    }

    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        CustomButton(label = "Pick from Gallery", icon = R.drawable.gallery, onClick = {
            galleryLauncher.launch("image/*")
        })
        CustomButton(label = "Take Picture", icon = R.drawable.camera, onClick = {
            takePicture()
        })

    }
}
