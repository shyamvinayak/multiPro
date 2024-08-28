import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.ParcelFileDescriptor
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

fun saveImageToFile(
    fileUri: Uri,
    imageUri: Uri?,
    context: Context
): Boolean {

    val inputStream: InputStream? = context.contentResolver.openInputStream(imageUri ?: return false)
    inputStream ?: return false

    return try {
        val outputStream: OutputStream = context.contentResolver.openOutputStream(fileUri) ?: return false
        inputStream.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}

private fun createFileInFolder(
    context: Context,
    folderUri: Uri,
    fileName: String,
    onFileUriReceived: (Uri?) -> Unit
) {
    val createFileIntent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
        addCategory(Intent.CATEGORY_OPENABLE)
        setType("image/jpeg")
        putExtra(Intent.EXTRA_TITLE, fileName)
        putExtra("android.content.extra.SHOW_ADVANCED", true)
    }

    val activity = context as? Activity
    activity?.startActivityForResult(createFileIntent, REQUEST_CODE_CREATE_FILE)
    // Use a method or callback to receive the result of the file creation
}

const val REQUEST_CODE_CREATE_FILE = 1
