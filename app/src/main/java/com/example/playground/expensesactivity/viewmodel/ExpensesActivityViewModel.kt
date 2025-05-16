package com.example.playground.expensesactivity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playground.contract.ComposeContract
import com.example.playground.contract.ErrorType
import com.example.playground.contract.LoadingType
import com.example.playground.contract.composeContractDelegate
import com.example.playground.expensesactivity.contract.ExpensesActivityContract.ExpenseActivityUIState
import com.example.playground.expensesactivity.contract.ExpensesActivityContract.ExpensesActivityEffect
import com.example.playground.expensesactivity.contract.ExpensesActivityContract.ExpensesActivityEvent
import com.example.playground.expensesactivity.contract.ExpensesActivityContract.SuccessData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpensesActivityViewModel @Inject constructor() : ViewModel(),
    ComposeContract<
            ExpenseActivityUIState,
            ExpensesActivityEvent,
            ExpensesActivityEffect>
    by composeContractDelegate(ExpenseActivityUIState()) {

    init {
        viewModelScope.launch {
            // Loading state on screen load
            setLoadingResult(loadingType = LoadingType.WithTitle())

            delay(3000)

            // Initial success state loaded onto screen
            setLoadedResult {
                ExpenseActivityUIState(
                    successData = SuccessData(
                        button1Text = "Go to Expense Details",
                        button2Text = "Go to other activity",
                        button3Text = "Fetch Data using interactor",
                        button3HalfText = "Fetch Data using callback",
                        button4Text = "Change my text"
                    )
                )
            }
        }
    }

    override fun onEvent(event: ExpensesActivityEvent) {
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

            is ExpensesActivityEvent.DataFetchedFromThisActivityUsingInteractor -> handleDataFromActivity(event.data)
            is ExpensesActivityEvent.DataFetchedFromThisActivityUsingCallback -> handleDataFromActivity(event.data)

            ExpensesActivityEvent.FetchDataFromThisActivityUsingInteractor -> fetchSomethingFromActivityUsingInteractor()
            ExpensesActivityEvent.FetchDataFromThisActivityUsingCallback -> fetchSomethingFromActivityUsingCallback()
            ExpensesActivityEvent.UpdateButtonText -> {
                setLoadedResult {
                    copy(
                        successData = successData?.copy(
                            button4Text = "Successfully updated my text"
                        )
                    )
                }
            }

            ExpensesActivityEvent.SimulateFailure -> {
                setErrorResult(
                    errorType = ErrorType.WithMessageAndRetry(
                        retryAction = {
                            resetState()
                        }
                    )
                )
            }
        }
    }

    private fun resetState() {
        setLoadedResult {
            copy(successData = successData)
        }
    }

    private fun navigateTo(destination: ExpensesActivityEffect.Navigation) = viewModelScope.launch {
        emitEffect(destination)
    }

    private fun showToastMessage(message: ExpensesActivityEffect.Toast) = viewModelScope.launch {
        emitEffect(message)
    }

    private fun fetchSomethingFromActivityUsingInteractor() = viewModelScope.launch {
        emitEffect(
            ExpensesActivityEffect.FetchFromThisActivity { data ->
                onEvent(ExpensesActivityEvent.DataFetchedFromThisActivityUsingInteractor(data))
            }
        )
    }

    private fun fetchSomethingFromActivityUsingCallback() = viewModelScope.launch {
        emitEffect(
            ExpensesActivityEffect.FetchFromThisActivity { data ->
                onEvent(ExpensesActivityEvent.DataFetchedFromThisActivityUsingCallback(data))
            }
        )
    }

    private fun handleDataFromActivity(data: String?) {
        data?.run {
            showToastMessage(ExpensesActivityEffect.Toast(this))
        }
    }
}