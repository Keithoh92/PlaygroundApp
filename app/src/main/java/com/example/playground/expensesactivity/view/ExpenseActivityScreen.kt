package com.example.playground.expensesactivity.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.playground.common.ExpensesErrorScreen
import com.example.playground.common.ExpensesLoadingScreen
import com.example.playground.contract.Compose
import com.example.playground.contract.UIResult
import com.example.playground.expensesactivity.contract.ExpensesActivityContract
import com.example.playground.expensesactivity.view.components.ExpensesActivityContent
import com.example.playground.ui.theme.PlaygroundTheme

@Composable
fun ExpensesActivityScreen(
    uiResult: UIResult<ExpensesActivityContract.ExpenseActivityUIState>,
    onEvent: (ExpensesActivityContract.ExpensesActivityEvent) -> Unit
) {
    val uiState = (uiResult as? UIResult.Loaded)?.uiState?.successData
    PlaygroundTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            uiResult.Compose(
                onLoading = {
                    ExpensesLoadingScreen(loadingType = it, modifier = Modifier.fillMaxSize())
                },
                onLoaded = {
                    uiState?.let { it1 -> ExpensesActivityContent(it1, onEvent) }
                },
                onEmpty = {
                    // Add Empty State
                },
                onError = {
                    ExpensesErrorScreen(errorType = it, modifier = Modifier.fillMaxSize())
                }
            )
        }
    }
}
