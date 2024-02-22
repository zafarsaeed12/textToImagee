package com.shabban.texttoimage.Common

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import java.io.File
import java.io.FileOutputStream

fun Context.saveFileToDownloadDirectory(content: String, fileName: String): Boolean {
    try {
        val downloadsDir = getDownloadsDirectory()
        val folder = downloadsDir?.path + "/${Constant.DIRECTORY_NAME}"
        if (!File(folder).exists()) {
            File(folder).mkdirs()
        }

        val decodedBytes = Base64.decode(content, Base64.DEFAULT)
        if (downloadsDir != null) {
            val outputFile = File(File(folder), fileName)

            FileOutputStream(outputFile).use { outputStream ->
                outputStream.write(decodedBytes)
            }

            return true
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return false
}

private fun getDownloadsDirectory(): File? {
    return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
}


fun Context.saveBase64Image(base64Image: String?, fileName: String): Boolean {
    try {
        // Decode the Base64 string to a byte array
        val decodedBytes = Base64.decode(base64Image, Base64.DEFAULT)
        // For Android 10 and above, use MediaStore API
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val relativePath = "${Environment.DIRECTORY_DCIM}/${Constant.DIRECTORY_NAME}"
            val resolver = this.contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, relativePath)
            }
            val imageUri =
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            imageUri?.let {
                resolver.openOutputStream(it)?.use { outputStream ->
                    outputStream.write(decodedBytes)
                }
                return true
            }
        } else {
            // For Android versions below 10, use traditional file writing
            val downloadsDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
            val outputFile = File(downloadsDir, fileName)
            FileOutputStream(outputFile).use { outputStream ->
                outputStream.write(decodedBytes)
            }
            return true
        }
    } catch (e: Exception) {
        e.printStackTrace()
        return false
    }
    return false
}