package com.example.add_mul_by_kotlin_methods.dbImage.viewModel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
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

    fun addImage(image: ImageEntity) {
        viewModelScope.launch {
           try {
               repository.insertImage(image)
           }catch (e:Exception){
               println("Insert Error:--$e")
           }
        }
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