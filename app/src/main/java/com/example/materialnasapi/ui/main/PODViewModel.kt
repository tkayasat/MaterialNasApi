package com.example.materialnasapi.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PODViewModel {
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}