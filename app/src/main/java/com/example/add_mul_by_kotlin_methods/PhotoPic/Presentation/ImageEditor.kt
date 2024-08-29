package com.example.add_mul_by_kotlin_methods.PhotoPic.Presentation

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.add_mul_by_kotlin_methods.PhotoPic.Component.ImageCropper
import com.example.add_mul_by_kotlin_methods.PhotoPic.Component.ImageDisplay
import com.example.add_mul_by_kotlin_methods.PhotoPic.Component.ImagePicker
import com.example.add_mul_by_kotlin_methods.PhotoPic.Component.RequestPermissions
import com.example.add_mul_by_kotlin_methods.PhotoPic.Component.rememberFolderPickerLauncher
import com.example.add_mul_by_kotlin_methods.PhotoPic.ViewModel.PhotoViewModel

@Composable
fun ImageEditor(viewModel: PhotoViewModel = hiltViewModel()) {
    val context = LocalContext.current
    var pickedImageUri by remember { mutableStateOf<Uri?>(null) }
    var croppedImageUri by remember { mutableStateOf<Uri?>(null) }
    var saveStatus by remember { mutableStateOf<String?>(null) }
    var folderUri by remember { mutableStateOf<Uri?>(null) }
    var showDialog by remember { mutableStateOf(false) }
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
            folderUri?.let { folderUri ->
                OutlinedButton(onClick = {
                    println("SelectedFolder:--$folderUri")
                    val status = viewModel.saveImage(
                        context = context,
                        bitmap = croppedImageUri,
                        folderUri = folderUri
                    )
                    saveStatus = if (status) "Image Saved to directory" else "Failed to Save Image"
                    showDialog = true
                    croppedImageUri = Uri.EMPTY
                }) {
                    Text("Save Image", color = MaterialTheme.colorScheme.onBackground)
                }
            }
        }

        item {
            if (showDialog) {
                AlertDialog(
                    title = {
                        Text(
                            saveStatus ?: "",
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    text = { Text("Open the Saved folder to view your photos.") },
                    onDismissRequest = { showDialog = false },
                    dismissButton = {
                        TextButton(onClick = {
                            showDialog = false
                            folderUri = null
                        }) {
                            Text(
                                "Cancel",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            showDialog = false
                            folderUri?.let { viewModel.openFolder(context, it) }
                        }) {
                            Text(
                                "Ok",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    })

            }

        }

    }

}





