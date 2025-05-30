package com.example.playground.expensedetails.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.playground.expensedetails.event.ExpenseDetailsEvent
import com.example.playground.expensedetails.state.ExpenseDetailsUIState
import com.example.playground.ui.theme.PlaygroundTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ExpenseDetailsScreen(
    uiState: StateFlow<ExpenseDetailsUIState>,
    onEvent: (ExpenseDetailsEvent) -> Unit
) {
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