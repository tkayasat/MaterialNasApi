package com.example.materialnasapi.repository

sealed class SolarFlareData {
    data class Success(val solarFlareResponseData: SolarFlareResponseData) : SolarFlareData()
    data class Error(val error: Throwable) : SolarFlareData()
    data class Loading(val progress: Int?) : SolarFlareData()
}