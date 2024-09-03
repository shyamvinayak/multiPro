package com.example.add_mul_by_kotlin_methods.dbImage.room

import androidx.lifecycle.LiveData
import javax.inject.Inject

class ImageRepo @Inject constructor(private val imageDao: ImageDao) {

    fun getAllImages(): LiveData<List<ImageEntity>> = imageDao.getAllImage()

    suspend fun insertImage(image: ImageEntity) = imageDao.insertImage(image)

    fun deleteImage(imageId: Int) = imageDao.deleteImage(imageId)

    /*fun getImageData(id: Int): LiveData<ByteArray> {
        return Transformations.map(imageDao.getImageDataById(id)) { base64Data ->
            decodeFromBase64(base64Data)
        }
    }*/

}