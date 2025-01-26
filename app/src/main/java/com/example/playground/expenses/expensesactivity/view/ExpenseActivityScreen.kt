package com.example.playground.expenses.expensesactivity.view

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
import com.example.playground.expenses.expensesactivity.state.ExpensesActivityUIState
import com.example.playground.expenses.expensesactivity.event.ExpensesActivityEvent
import com.example.playground.ui.theme.PlaygroundTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ExpensesActivityScreen(
    uiState: StateFlow<ExpensesActivityUIState>,
    onEvent: (ExpensesActivityEvent) -> Unit
) {

    val state = uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { onEvent(ExpensesActivityEvent.NavigateToExpenseDetails) }
        ) {
            Text(text = "Go to Expense Details")
        }

        Button(
            onClick = { onEvent(ExpensesActivityEvent.GoToOtherActivity) }
        ) {
            Text(text = "Go to other activity")
        }

        Button(
            onClick = { onEvent(ExpensesActivityEvent.FetchDataFromThisActivity) }
        ) {
            Text(text = "Fetch Data from this activity")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyScreen1Preview() {
    PlaygroundTheme {
        ExpensesActivityScreen(
            uiState = MutableStateFlow(ExpensesActivityUIState()),
            onEvent = {}
        )
    }
}