package com.buzzvil.campaign.utils

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class DisplayUtils @Inject constructor(
    @ApplicationContext private val context: Context,
    private val displayMetrics: DisplayMetrics
) {

    /*val widthDp = windowMetrics.bounds.width() /
            displayMetrics.density

    val widthPx = windowMetrics.bounds.width()*/

    val density = displayMetrics.density
    val densityDpi = displayMetrics.densityDpi
    val scaledDensity = displayMetrics.scaledDensity

    fun Float.convertDpToPixel(): Float = this * displayMetrics.density

    fun Float.convertPixelsToDp(): Float = this / displayMetrics.density


}