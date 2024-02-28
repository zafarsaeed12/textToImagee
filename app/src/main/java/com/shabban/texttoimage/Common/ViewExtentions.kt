package com.shabban.texttoimage.Common

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.ImageLoader
import coil.load
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.google.android.material.snackbar.Snackbar
import com.shabban.texttoimage.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.inVisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

//Toast for Activities
fun Activity.showShortToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Activity.showLongToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

//Toast for fragments
fun Fragment.showShortToast(msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun Fragment.showLongToast(msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}

fun View.showSnackBar(msg: String) {
    val snackbar = Snackbar.make(this, msg, Snackbar.LENGTH_SHORT)
    val snackbarView = snackbar.view
    /*  snackbarView.setBackgroundColor(ContextCompat.getColor(context, R.color.snack_bar_bg))
      snackbar.setTextColor(ContextCompat.getColor(context, R.color.title_text_color))*/
    snackbar.show()
}

fun ImageView.loadImageWithCallback(
    url: String?,
    onLoading: () -> Unit,
    onSuccess: () -> Unit,
    onError: () -> Unit,
) {

    val imageLoader = ImageLoader.Builder(context)
        .build()

    val imageRequest = ImageRequest.Builder(context)
        .data(url)
        .listener(
            onStart = {
                onLoading()
            },
            onSuccess = { request, metadata ->
                onSuccess()
                this.load(request) {
                    crossfade(true)
                }
            },
            onError = { request, result ->
                onError
            }
        )
        .build()

    imageLoader.enqueue(imageRequest)
    this.load(imageRequest)
}

fun View.loadImageWithProgress(
    imageUrl: String,
    imageLoader: ImageLoader,
    progressBar: ProgressBar,
    onSuccess: () -> Unit,
    onError: () -> Unit,
) {
    progressBar.visible()
    val request = ImageRequest.Builder(context)
        .data(imageUrl)
        .placeholder(R.drawable.ic_home_img)
        .target(
            onSuccess = { result ->
                onSuccess()
                (this as? ImageView)?.setImageDrawable(result.current)
                progressBar.visibility = View.GONE

            },
            onError = { error ->
                onError()
                progressBar.visibility = View.GONE
            }
        )
        .build()

    imageLoader.enqueue(request)
}


fun ImageView.loadImageFromEncodedStringAsync(
    encodedString: String,
    progressBar: ProgressBar

) {
    CoroutineScope(Dispatchers.Main).launch {

        // Create an ImageLoader instance
        val imageLoader = ImageLoader.Builder(context)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .build()

        // Load the image using Coil


        // Run on a background thread to avoid blocking the main thread
        try {
            val bitmap = loadImageFromEncodedString(encodedString)
            load(bitmap, imageLoader) {
                crossfade(true)
                error(R.drawable.ic_home_img)
            }
        } catch (e: Exception) {
            setImageResource(R.drawable.ic_home_img)
        } finally {
        }
    }
}

private suspend fun loadImageFromEncodedString(encodedString: String): Bitmap {
    return withContext(Dispatchers.IO) {
        // Decode the Base64 encoded string into a ByteArray
        val decodedBytes = Base64.decode(encodedString, Base64.DEFAULT)

        // Create a Bitmap from the ByteArray
        BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }
}


