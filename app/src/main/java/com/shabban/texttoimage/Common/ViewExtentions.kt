package com.shabban.texttoimage.Common

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.Coil
import com.google.android.material.snackbar.Snackbar
import com.shabban.texttoimage.R


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

/*fun loadImageWithIndicator(imageUrl: String, imageView: ImageView, progressBar: ProgressBar) {
    imageLoadingState.value = ImageLoadingState.Loading
    progressBar.show() // Make the progress bar visible

    Coil.load(imageUrl)
        .placeholder(R.drawable.placeholder_image) // Optional placeholder
        .error(R.drawable.error_image) // Optional error image
        .allowHardware(true) // Optional hardware acceleration
        .crossfade(true) // Optional crossfade animation
        .lifecycle(this) // Attach to view lifecycle (or use into(viewLifecycleOwner))
        .into(imageView) { result ->
            if (result.isSuccess) {
                imageLoadingState.value = ImageLoadingState.Success
            } else {
                imageLoadingState.value = ImageLoadingState.Error
            }
        }
}*/


