package com.example.playground.expensesactivity.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.playground.expensesactivity.contract.ExpensesActivityContract

@Composable
internal fun ExpensesActivityContent(
    state: ExpensesActivityContract.SuccessData,
    onEvent: (ExpensesActivityContract.ExpensesActivityEvent) -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()).padding(top = 50.dp).fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 25.dp),
            text = "Within Feature screen navigation",
            textAlign = TextAlign.Center
        )

        Button(
            onClick = { onEvent(ExpensesActivityContract.ExpensesActivityEvent.NavigateToExpenseDetails) }
        ) {
            Text(
                text = state.button1Text,
                textAlign = TextAlign.Center
            )
        }

        HorizontalDivider()
        Text(
            modifier = Modifier.padding(horizontal = 25.dp),
            text = "Outside feature nav",
            textAlign = TextAlign.Center
        )

        Button(
            onClick = { onEvent(ExpensesActivityContract.ExpensesActivityEvent.GoToOtherActivity) }
        ) {
            Text(
                text = state.button2Text,
                textAlign = TextAlign.Center
            )
        }

        HorizontalDivider()
        Text(
            modifier = Modifier.padding(horizontal = 25.dp),
            text = "Fetch Data from this activity\nCommunication from screen -> ViewModel -> Activity and back with data fetched from activity",
            textAlign = TextAlign.Center
        )

        Button(
            onClick = { onEvent(ExpensesActivityContract.ExpensesActivityEvent.FetchDataFromThisActivityUsingInteractor) }
        ) {
            Text(
                text = state.button3Text,
                textAlign = TextAlign.Center
            )
        }
        Button(
            onClick = { onEvent(ExpensesActivityContract.ExpensesActivityEvent.FetchDataFromThisActivityUsingCallback) }
        ) {
            Text(
                text = state.button3HalfText,
                textAlign = TextAlign.Center
            )
        }

        HorizontalDivider()
        Text(
            modifier = Modifier.padding(horizontal = 25.dp),
            text = "Demonstrate updating the state with Page Contract",
            textAlign = TextAlign.Center
        )
        Button(
            onClick = { onEvent(ExpensesActivityContract.ExpensesActivityEvent.UpdateButtonText) }
        ) {
            Text(
                text = state.button4Text,
                textAlign = TextAlign.Center
            )
        }

        HorizontalDivider()
        Text(
            modifier = Modifier.padding(horizontal = 25.dp),
            text = "Demonstrate updating the uiResult to failure",
            textAlign = TextAlign.Center
        )
        Button(
            onClick = { onEvent(ExpensesActivityContract.ExpensesActivityEvent.SimulateFailure) }
        ) {
            Text(
                text = "Simulate a failure",
                textAlign = TextAlign.Center
            )
        }
    }
}