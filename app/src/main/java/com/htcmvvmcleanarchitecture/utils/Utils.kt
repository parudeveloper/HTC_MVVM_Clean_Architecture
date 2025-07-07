package com.htcmvvmcleanarchitecture.utils

import android.content.Context
import android.content.Intent

fun shareProduct(context: Context, message: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    context.startActivity(Intent.createChooser(intent, "Share via"))
}