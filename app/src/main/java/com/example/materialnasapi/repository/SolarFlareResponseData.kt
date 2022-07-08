package com.example.materialnasapi.repository

data class SolarFlareResponseData(
    val flrID: String,
    val beginTime: String,
    val peakTime: String,
    val endTime: String? = null,
    val classType: String,
    val sourceLocation: String,
    val activeRegionNum: Long? = null,
    val link: String
)