package com.example.playground.expenses.expensedetails.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.playground.expenses.expensedetails.event.ExpenseDetailsEvent
import com.example.playground.expenses.expensedetails.state.ExpenseDetailsUIState
import com.example.playground.expenses.expensesactivity.state.ExpensesActivityUIState
import com.example.playground.expenses.expensesactivity.event.ExpensesActivityEvent
import com.example.playground.ui.theme.PlaygroundTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ExpenseDetailsScreen(
    uiState: StateFlow<ExpenseDetailsUIState>,
    onEvent: (ExpenseDetailsEvent) -> Unit
) {

    val state = uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { onEvent(ExpenseDetailsEvent.NavigateUp("Hey from Expense Details")) }
        ) {
            Text(text = "Go back to Expense Activity")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpenseDetailsScreenPreview() {
    PlaygroundTheme {
        ExpenseDetailsScreen(
            uiState = MutableStateFlow(ExpenseDetailsUIState()),
            onEvent = {}
        )
    }
}