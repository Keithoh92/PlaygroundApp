package com.example.playground.external.view

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.playground.ExternalActivity
import com.example.playground.external.event.ExternalScreenEvent
import com.example.playground.external.state.ExternalScreenUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ExternalScreen(
    onEvent: (ExternalScreenEvent) -> Unit,
    state: StateFlow<ExternalScreenUIState>,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
    ) {
        FilledTonalButtonPlayground(
            onClick = {
                val resultIntent = Intent(context, ExternalActivity::class.java).apply {
                    putExtra("activity_name", "ExternalActivity")
                    putExtra("result_key", "Hello from External Activity!")
                }
                onEvent(ExternalScreenEvent.OnReturnToExpensesScreen(resultIntent))
            },
            text = "Return Result"
        )

        FilledTonalButtonPlayground(
            onClick = { onEvent(ExternalScreenEvent.FetchFromActivity) },
            text = "Fetch Something from Activity"
        )
    }
}

@Composable
fun FilledTonalButtonPlayground(
    onClick: () -> Unit,
    text: String
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
private fun ExternalScreenPreview() {
    ExternalScreen(
        state = MutableStateFlow(ExternalScreenUIState()),
        onEvent = {},
    )
}