package com.nickgua.ariessample

import android.app.Application

class MyApplication : Application() {
    companion object {
        lateinit var appContext: Application
    }

    init {
        appContext = this
    }
}