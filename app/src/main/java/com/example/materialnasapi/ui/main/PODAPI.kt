package com.example.materialnasapi.ui.main

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PODAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String): Call<PODDATA>
}