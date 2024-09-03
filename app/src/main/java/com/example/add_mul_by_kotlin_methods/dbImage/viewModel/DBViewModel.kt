package com.example.add_mul_by_kotlin_methods.dbImage.viewModel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.add_mul_by_kotlin_methods.dbImage.room.ImageEntity
import com.example.add_mul_by_kotlin_methods.dbImage.room.ImageRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DBViewModel @Inject constructor(
    private val repository: ImageRepo,
) : ViewModel() {


    val images: LiveData<List<ImageEntity>> = repository.getAllImages()

    fun addImage(context: Context,image: ImageEntity) {
        viewModelScope.launch {
           try {
               val uri = Uri.parse(image.imageData)
               val byteArrayData = readBytes(context = context,uri)
               val base64ImageData = encodeToBase64(byteArrayData!!)
               val imageEntity = ImageEntity(imageData = base64ImageData)
               repository.insertImage(imageEntity)
           }catch (e:Exception){
               println("Insert Error:--$e")
           }
        }
    }


    fun encodeToBase64(data: ByteArray): String {
        return Base64.encodeToString(data, Base64.DEFAULT)
    }

    fun decodeFromBase64(base64Data: String): ByteArray {
        return Base64.decode(base64Data, Base64.DEFAULT)
    }

    fun readBytes(context: Context, uri: Uri): ByteArray? =
        context.contentResolver.openInputStream(uri)?.use { it.buffered().readBytes() }


    fun byteArrayToBitmap(byteArray: ByteArray): Bitmap? {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    fun deleteImage(imageId:Int){
        repository.deleteImage(imageId)
    }

}