package com.example.samplearchitecture.core.extension

import android.view.View

fun View.dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()
