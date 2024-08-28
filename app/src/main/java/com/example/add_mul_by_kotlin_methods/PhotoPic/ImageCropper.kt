package com.example.add_mul_by_kotlin_methods.PhotoPic

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.yalantis.ucrop.UCrop
import java.io.File

@Composable
fun ImageCropper(resultUri: Uri?, onImageCropped: (Uri?) -> Unit) {
    val context = LocalContext.current
    val activity = context as Activity

    val cropLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            UCrop.getOutput(result.data!!)?.let { uri ->
                onImageCropped(uri)
            }
        }
    }

    LaunchedEffect(resultUri) {
        resultUri?.let {
            val destinationUri = Uri.fromFile(File(context.cacheDir, "cropped.jpg"))
            val options = UCrop.Options().apply {
                setCompressionQuality(100)
            }
            val uCropIntent = UCrop.of(it, destinationUri)
                .withOptions(options)
                .withAspectRatio(1f, 1f)
                .getIntent(context)
            cropLauncher.launch(uCropIntent)
        }
    }
}

