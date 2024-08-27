package com.example.add_mul_by_kotlin_methods.PhotoPic


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.provider.Settings
import android.view.SoundEffectConstants
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import com.example.add_mul_by_kotlin_methods.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import java.io.File

private const val CAMERA_PERMISSION_REQUEST_CODE = 1001
private const val CAMERA_CAPTURE_REQUEST_CODE = 1002
private const val GALLERY_PICK_REQUEST_CODE = 1004

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun PhotoPic() {
    val sheetState = rememberModalBottomSheetState()
    val localView = LocalView.current
    val context = LocalContext.current
    val activity = context as Activity
    var showBottomSheet by remember { mutableStateOf(false) }
    var isGranded by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val outputDirectory = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val photoFile = File(outputDirectory, "photo.jpg")
    val photoUri = FileProvider.getUriForFile(context, "com.example.add_mul_by_kotlin_methods.fileprovider", photoFile)
    val state = rememberPermissionState(Manifest.permission.CAMERA)


    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                imageUri = it
            }
        }
    )

    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                // Image was captured successfully
                imageUri = photoUri
            }
        }
    )


    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            takePictureLauncher.launch(photoUri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", activity.packageName, null)
            }
            activity.startActivity(intent)
        }

    }

    if(isGranded){
        takePictureLauncher.launch(photoUri)
    }else{
        LaunchedEffect(Unit) {
            state.launchPermissionRequest()
        }
        PermissionRationale(state)
    }


    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { MyTopBar(title = "Image Picker") },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                OutlinedButton(
                    onClick = { showBottomSheet = true },
                    modifier = Modifier.padding(it)
                ) {
                    Text(
                        text = "Select Image",
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        })



    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState,
            modifier = Modifier.fillMaxHeight(0.3f)
        ) {
            LazyRow(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                item {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp)) {
                        ClickableImage(imageResId = R.drawable.camera,
                            onClick = {
                                localView.playSoundEffect(SoundEffectConstants.CLICK)
                                takePictureLauncher.launch(photoUri)
                                showBottomSheet = false
                            })
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "Select from Camera",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                }
                item {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp)) {
                        ClickableImage(imageResId = R.drawable.gallery,
                            onClick = {
                                galleryLauncher.launch("image/*")
                                showBottomSheet = false
                            })
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "Select from Gallery",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    }


}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionRationale(state: PermissionState) {
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(Modifier.padding(vertical = 120.dp, horizontal = 16.dp)) {
            Icon(painter = painterResource(id = R.drawable.camera), contentDescription = null, tint = MaterialTheme.colorScheme.onBackground)
            Spacer(Modifier.height(8.dp))
            Text("Camera permission required", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(4.dp))
            Text("This is required in order for the app to take pictures")
        }
        val context = LocalContext.current
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                }
               /* startActivity()*/
            }
        ) {
            Text("Go to settings")
        }
    }
}


@Composable
fun ClickableImage(
    imageResId: Int,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = contentDescription,
        modifier = Modifier
            .size(100.dp)
            .clickable { onClick() }
            .padding(16.dp) // Optional: Adjust padding as needed
    )
}


