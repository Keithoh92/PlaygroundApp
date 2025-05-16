package com.example.playground.externalactivity.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.playground.externalactivity.model.SnackBarHostStateWrapper

@Composable
internal fun ExpensesSnackbarUIComponent(
    expensesSnackbar: String?,
    snackbarHostStateWrapper: SnackBarHostStateWrapper,
    onDismiss: () -> Unit
) {
    val showSnackbar = expensesSnackbar != null
//    val snackbarMessage = remember {
//        mutableStateOf(expensesSnackbar.message)
//    }
    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
//            snackbarMessage.value = expensesSnackbar.message
            snackbarHostStateWrapper.showSnackbar()
        }
    }

    val currentSnackbarData = snackbarHostStateWrapper.hostState.currentSnackbarData
    LaunchedEffect(currentSnackbarData) {
        if (currentSnackbarData == null) {
            onDismiss()
        }
    }

    SnackbarUIComponent(
        modifier = Modifier,
        snackBarHostStateWrapper = snackbarHostStateWrapper,
        message = expensesSnackbar ?: ""
    )
}
