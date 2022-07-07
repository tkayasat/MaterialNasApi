package com.example.materialnasapi.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialnasapi.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(App.instance.currentTheme)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
    }

    fun changeTheme(theme: Int) {
        App.instance.currentTheme = theme
        this.recreate()
    }
}