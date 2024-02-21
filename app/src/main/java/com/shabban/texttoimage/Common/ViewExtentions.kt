package com.shabban.texttoimage.Common

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment


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


