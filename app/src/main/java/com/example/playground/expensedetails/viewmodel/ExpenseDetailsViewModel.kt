package com.example.playground.expensedetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playground.expensedetails.effect.ExpenseDetailsEffect
import com.example.playground.expensedetails.event.ExpenseDetailsEvent
import com.example.playground.expensedetails.state.ExpenseDetailsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseDetailsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ExpenseDetailsUIState())
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<ExpenseDetailsEffect>(Channel.CONFLATED)
    val effect: Flow<ExpenseDetailsEffect> = _effect.receiveAsFlow()

    fun onEvent(event: ExpenseDetailsEvent) {
        when (event) {
            is ExpenseDetailsEvent.NavigateUp ->
                navigateTo(ExpenseDetailsEffect.Navigation.NavigateUp(event.dataToPassBack))
        }
    }

    private fun navigateTo(destination: ExpenseDetailsEffect.Navigation) = viewModelScope.launch {
        _effect.send(destination)
    }
}