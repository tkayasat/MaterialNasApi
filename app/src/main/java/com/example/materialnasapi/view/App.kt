package com.example.materialnasapi.view

import android.app.Application
import com.example.materialnasapi.R

class App : Application() {

    var currentTheme = R.style.Theme_NasAPI

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
    }
}