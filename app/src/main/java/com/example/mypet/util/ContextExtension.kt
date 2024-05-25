package com.example.mypet.util

import android.content.Context
import android.widget.Toast

fun Context.showToast(s: String) {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
}