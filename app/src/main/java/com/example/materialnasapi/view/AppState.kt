package com.example.materialnasapi.view

import com.example.materialnasapi.repository.PODServerResponseData
import com.example.materialnasapi.repository.SolarFlareResponseData

sealed class AppState {
    data class Success(val serverResponseData: PODServerResponseData) : AppState()
    data class SuccessSolar(val serverResponseData: SolarFlareResponseData) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}