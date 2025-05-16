package com.example.playground.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.playground.contract.ErrorType
import com.example.playground.contract.ErrorWithMessage

@Composable
fun ExpensesErrorScreen(errorType: ErrorType, modifier: Modifier) {
    when (errorType) {
        is ErrorType.WithMessage -> ErrorWithMessage(
            errorMessage = stringResource(id = errorType.message),
            modifier = modifier
        )
        is ErrorType.WithMessageAndRetry -> ErrorWithMessage(
            errorMessage = stringResource(id = errorType.message),
            onRetry = errorType.retryAction,
            modifier = modifier
        )
    }
}
