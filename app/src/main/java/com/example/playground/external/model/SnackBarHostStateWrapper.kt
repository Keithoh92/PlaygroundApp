package com.example.playground.external.model

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult

class SnackBarHostStateWrapper {
    val hostState: SnackbarHostState = SnackbarHostState()
    suspend fun showSnackbar(): SnackbarResult =
        hostState.showSnackbar(message = "", duration = SnackbarDuration.Indefinite)
}