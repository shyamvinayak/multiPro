package com.example.add_mul_by_kotlin_methods.photoPic.ViewModel

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.DocumentsContract
import android.webkit.MimeTypeMap
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(): ViewModel() {
    fun saveImage(
        context: Context,
        bitmap: Uri?,
        folderUri: Uri,
    ): Boolean {
        println("FolderName:---$folderUri")
        return try {
            val contentResolver = context.contentResolver

            bitmap?.let { imageUri ->
                val fileName =
                    generateFileName(getFileExtensionFromUri(context, imageUri) ?: ".jpg")
                val uri = createFileInDirectory(context, folderUri, fileName)

                if (uri != null) {
                    contentResolver.openOutputStream(uri)?.use { outputStream ->
                        val imageBytes = readBytes(context, imageUri)
                        outputStream.write(imageBytes)
                        outputStream.flush()

                    }
                }
            }
            true
        } catch (e: Exception) {
            println("Error Msg:--$e")
            false
        }
    }

    fun openFolder(context: Context, folderUri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            // Set the data and type for the folder
            data = folderUri
            // Set type to the generic MIME type for directories
            setType("*/*")
            // Flag to grant read permissions
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }

        // Start the activity if it is available
        context.startActivity(Intent.createChooser(intent, "Open Folder"))
    }


    private fun generateFileName(extension: String): String {
        val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        // Get the current date and time
        val now = Date()
        // Format the date and time
        val dateString = dateFormat.format(now)
        // Return the file name with the extension
        return "$dateString$extension"
    }

    private fun getFileExtensionFromUri(context: Context, uri: Uri): String? {
        val contentResolver = context.contentResolver
        var extension: String? = null

        // Get MIME type of the file from the ContentResolver
        val mimeType = contentResolver.getType(uri)

        if (mimeType != null) {
            // Get file extension from MIME type
            extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType)
        }

        return extension?.let { ".$it" } // Prepend the dot for the extension
    }

    @Throws(IOException::class)
    private fun readBytes(context: Context, uri: Uri): ByteArray? =
        context.contentResolver.openInputStream(uri)?.use { it.buffered().readBytes() }

    private fun createFileInDirectory(context: Context, folderUri: Uri, fileName: String): Uri? {
        val contentResolver = context.contentResolver
        ContentValues().apply {
            put(DocumentsContract.Document.COLUMN_DISPLAY_NAME, fileName)
            put(DocumentsContract.Document.COLUMN_MIME_TYPE, "image/jpeg")
        }

        return try {
            val treeUri = DocumentsContract.buildDocumentUriUsingTree(
                folderUri,
                DocumentsContract.getTreeDocumentId(folderUri)
            )
            DocumentsContract.createDocument(contentResolver, treeUri, "image/jpeg", fileName)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}