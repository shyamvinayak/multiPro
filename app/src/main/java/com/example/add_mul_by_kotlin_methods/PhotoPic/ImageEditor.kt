package com.example.add_mul_by_kotlin_methods.PhotoPic

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import saveImageToFile
import java.io.BufferedReader

import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream

@Composable
fun ImageEditor() {
    val context = LocalContext.current
    var pickedImageUri by remember { mutableStateOf<Uri?>(null) }
    var croppedImageUri by remember { mutableStateOf<Uri?>(null) }
    var saveStatus by remember { mutableStateOf<String?>(null) }
    var folderUri by remember { mutableStateOf<Uri?>(null) }
    val folderPickerLauncher = rememberFolderPickerLauncher { uri -> folderUri = uri }


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            RequestPermissions()
        }
        item {
            ImagePicker { uri ->
                pickedImageUri = uri
                println("Pick Image:---${pickedImageUri}")
            }
        }

        item {
            pickedImageUri?.let {
                ImageCropper(
                    resultUri = it,
                    onImageCropped = { uri ->
                        croppedImageUri = uri
                        folderPickerLauncher.launch(null)
                    })
                ImageDisplay(imageUri = croppedImageUri)
            }
        }

        item {
            val contentResolver = context.contentResolver
            folderUri?.let {
                OutlinedButton(onClick = {
                    println("SelectedFolder:--$it")
                    val status =
                        saveImage(
                            context = context,
                            bitmap = uriToBitmap(
                                contentResolver = contentResolver,
                                uri = croppedImageUri!!
                            ),
                            folderName = it
                        )
                    saveStatus =
                        if (status) "Image saved successfully!" else "Failed to save image."
                    croppedImageUri = Uri.EMPTY
                    /* val inputStream: InputStream? = croppedImageUri?.let { it1 ->
                         context.contentResolver.openInputStream(
                             it1
                         )
                     }*/
                    /* inputStream?.use {
                         val status =
                             saveImage(
                                 context = context, bitmap = BitmapFactory.decodeStream(it), folderName =
                             )
                         saveStatus =
                             if (status) "Image saved successfully!" else "Failed to save image."
                         croppedImageUri = Uri.EMPTY
                     }*/
                    /* val status =
                         saveImageToFile(context = context, imageUri = croppedImageUri, fileUri = it)
                     saveStatus = if (status) "Image saved successfully!" else "Failed to save image."*/
                }) {
                    Text("Save Image", color = MaterialTheme.colorScheme.onBackground)
                }
            }
        }

        item {
            saveStatus?.let {
                Text(
                    text = it,
                    color = if (it.startsWith("Failed")) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                )
            }
        }

    }


}

fun uriToBitmap(contentResolver: ContentResolver, uri: Uri): Bitmap? {
    return try {
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        e.printStackTrace() // Handle the exception as needed
        null
    }
}
