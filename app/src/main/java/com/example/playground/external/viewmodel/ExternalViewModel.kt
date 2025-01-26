package com.example.playground.external.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playground.external.effect.ExternalScreenEffect
import com.example.playground.external.event.ExternalScreenEvent
import com.example.playground.external.state.ExternalScreenUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExternalViewModel @Inject constructor(): ViewModel() {

    private val _effect = Channel<ExternalScreenEffect>()
    val effect: Flow<ExternalScreenEffect> = _effect.receiveAsFlow()

    private val _uiState = MutableStateFlow(ExternalScreenUIState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: ExternalScreenEvent) {
        when (event) {
            is ExternalScreenEvent.OnReturnToExpensesScreen ->
                navigateTo(ExternalScreenEffect.Navigation.NavigateBackToExpenses(event.result))

            ExternalScreenEvent.FetchFromActivity -> fetchSomethingFromActivity()
            is ExternalScreenEvent.DataFetchedFromActivity -> handleDataFromActivity(event.data)
        }
    }

    private fun navigateTo(destination: ExternalScreenEffect.Navigation) = viewModelScope.launch {
        _effect.send(destination)
    }

    private fun fetchSomethingFromActivity() = viewModelScope.launch {
        _effect.send(ExternalScreenEffect.FetchFromActivity { data ->
            onEvent(
                ExternalScreenEvent.DataFetchedFromActivity(data)
            )
        })
    }

    private fun handleDataFromActivity(data: String?) {
        if (data != null) {
            // Do something with the Data
        }
    }
}