package com.example.playground.expenses.expensesactivity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playground.expenses.expensesactivity.effect.ExpensesActivityEffect
import com.example.playground.expenses.expensesactivity.event.ExpensesActivityEvent
import com.example.playground.expenses.expensesactivity.state.ExpensesActivityUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpensesActivityViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = MutableStateFlow(ExpensesActivityUIState())
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<ExpensesActivityEffect?>(Channel.CONFLATED)
    val effect: Flow<ExpensesActivityEffect?> = _effect.receiveAsFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), null)

    fun onEvent(event: ExpensesActivityEvent) {
        when (event) {
            ExpensesActivityEvent.GoToOtherActivity ->
                navigateTo(ExpensesActivityEffect.Navigation.NavigateToOtherActivity)

            ExpensesActivityEvent.NavigateToExpenseDetails ->
                navigateTo(
                    ExpensesActivityEffect.Navigation.NavigateToExpenseDetails
                )

            is ExpensesActivityEvent.ResultFromOtherActivity -> Unit

            is ExpensesActivityEvent.ResultFromExpenseDetails ->
                if (event.result != "null") {
                    showToastMessage(ExpensesActivityEffect.Toast(event.result))
                }

            is ExpensesActivityEvent.DataFetchedFromThisActivity -> handleDataFromActivity(event.data)

            ExpensesActivityEvent.FetchDataFromThisActivity -> fetchSomethingFromActivity()
        }
    }

    private fun navigateTo(destination: ExpensesActivityEffect.Navigation) = viewModelScope.launch {
        _effect.send(destination)
    }

    private fun showToastMessage(message: ExpensesActivityEffect.Toast) = viewModelScope.launch {
        _effect.send(message)
    }

    private fun fetchSomethingFromActivity() = viewModelScope.launch {
        _effect.send(ExpensesActivityEffect.FetchFromThisActivity { data ->
            onEvent(ExpensesActivityEvent.DataFetchedFromThisActivity(data))
        })
    }

    private fun handleDataFromActivity(data: String?) {
        data?.run {
            showToastMessage(ExpensesActivityEffect.Toast(this))
        }
    }
}