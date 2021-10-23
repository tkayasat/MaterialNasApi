package com.example.materialnasapi.ui.main

sealed class PODSTATE {
    object Loading : PODSTATE()
    data class Success(val PODDATA: PODSTATE) : PODSTATE()
    data class Error(val error: Throwable) : PODSTATE()
}