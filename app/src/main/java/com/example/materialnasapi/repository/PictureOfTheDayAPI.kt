package com.example.materialnasapi.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayAPI {
    @GET("planetary/pod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String): Call<PODServerResponseData>
}