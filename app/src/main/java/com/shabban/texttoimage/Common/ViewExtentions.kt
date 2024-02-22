package com.shabban.texttoimage.Common

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import com.google.android.material.snackbar.Snackbar


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


