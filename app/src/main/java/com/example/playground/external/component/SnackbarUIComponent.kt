package com.example.playground.external.component

import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.playground.external.model.SnackBarHostStateWrapper
import kotlinx.coroutines.delay

@Composable
fun SnackbarUIComponent(
    modifier: Modifier = Modifier,
    snackBarHostStateWrapper: SnackBarHostStateWrapper,
    message: String,
) {
    val snackBarHostState = snackBarHostStateWrapper.hostState

    SnackbarHost(hostState = snackBarHostState) {
        val currentSnackbarData = snackBarHostState.currentSnackbarData
        LaunchedEffect(currentSnackbarData) {
            if (currentSnackbarData != null) {
                delay(7000)
                currentSnackbarData.dismiss()
            }
        }

        SnackbarUIContent(
            modifier = modifier,
            message = message,
            onDismiss = {
                currentSnackbarData?.dismiss()
            }
        )
    }
}