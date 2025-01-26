package com.example.playground.external.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.playground.external.interactor.ExternalInteractor
import com.example.playground.external.effect.ExternalScreenEffect
import com.example.playground.external.interactor.ExternalInteractorEvent
import com.example.playground.external.viewmodel.ExternalViewModel

@Composable
fun ExternalScreenMain(
    viewModel: ExternalViewModel,
    interactor: ExternalInteractor
) {

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner) {
        if (lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    is ExternalScreenEffect.Navigation.NavigateBackToExpenses ->
                        interactor.backToExpenses(
                            ExternalInteractorEvent.OnBackToExpenses(effect.intent)
                        )

                    is ExternalScreenEffect.FetchFromActivity ->
                        interactor.fetchDataFromActivity(
                            ExternalInteractorEvent.FetchDataFromActivity(effect.onFetchedData)
                        )
                }
            }
        }
    }

    ExternalScreen(
        onEvent = viewModel::onEvent,
        state = viewModel.uiState,
    )
}