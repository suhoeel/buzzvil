package com.buzzvil.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BuzzvilApp : Application() {
    var init = false
}