package com.example.add_mul_by_kotlin_methods.PhotoPic

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

fun saveImage(
    context: Context,
    bitmap: Bitmap?,
    folderName:Uri
):Boolean {
    println("FolderName:---$folderName")
    return try {
        val contentResolver = context.contentResolver
        val documentUri = DocumentsContract.buildDocumentUriUsingTree(
            folderName,
            DocumentsContract.getTreeDocumentId(folderName)
        )
        val outputStream: OutputStream? = contentResolver.openOutputStream(documentUri)
        outputStream?.use {
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(context, "Image saved successfully", Toast.LENGTH_SHORT).show()
        }

        true
    } catch (e: Exception) {
        println("Error Msg:--$e")
        false
    }
}