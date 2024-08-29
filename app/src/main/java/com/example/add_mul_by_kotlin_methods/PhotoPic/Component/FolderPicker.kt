package com.example.add_mul_by_kotlin_methods.PhotoPic.Component


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

@Composable
fun rememberFolderPickerLauncher(
    onFolderSelected: (Uri?) -> Unit
) = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocumentTree()) { uri ->
    onFolderSelected(uri)
}