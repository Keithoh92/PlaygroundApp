package com.example.playground.externalactivity.view

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.playground.externalactivity.effect.ExternalScreenEffect
import com.example.playground.externalactivity.viewmodel.ExternalViewModel

@Composable
fun ExternalScreenMain(
    viewModel: ExternalViewModel,
    fetchDataFromActivity: ((String) -> Unit) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val activity = context as? Activity

    LaunchedEffect(lifecycleOwner) {
        if (lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    is ExternalScreenEffect.Toast -> {
                        showToastMessage(context, effect.message)
                    }

                    is ExternalScreenEffect.Navigation.NavigateBackToExpenses -> {
                        activity?.setResult(Activity.RESULT_OK, effect.intent)
                        activity?.finish()
                    }

                    is ExternalScreenEffect.FetchFromActivity -> {
                        fetchDataFromActivity { dataFetched ->
                            (effect.onFetchedData.invoke(dataFetched))
                        }
                    }
                }
            }
        }
    }

    ExternalScreen(
        onEvent = viewModel::onEvent,
        state = viewModel.uiState,
    )
}

fun showToastMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}