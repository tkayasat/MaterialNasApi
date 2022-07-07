package com.example.materialnasapi.api

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialnasapi.R
import kotlinx.android.synthetic.main.api_activity.*

class ApiActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.api_activity)
        view_pager.adapter = ViewPagerAdapter(supportFragmentManager)
    }
}