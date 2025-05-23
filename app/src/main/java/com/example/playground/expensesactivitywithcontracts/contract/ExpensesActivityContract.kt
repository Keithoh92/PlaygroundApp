package com.example.playground.expensesactivitywithcontracts.contract

interface ExpensesActivityContract {

    /*
    Setting successData here is necessary since we may have huge data classes
    that cannot have default values until we do some setup on the VM so
    a workaround is to set successData (your screens data class) to null until success result
    is loaded on the VM
     */
    data class ExpenseActivityUIState(
        val successData: SuccessData? = null
    )

    data class SuccessData(
        val button1Text: String,
        val button2Text: String,
        val button3Text: String,
        val button3HalfText: String,
        val button4Text: String,
    )

    sealed class ExpensesActivityEvent {
        data object NavigateToExpenseDetails : ExpensesActivityEvent()
        data class ResultFromExpenseDetails(val result: String) : ExpensesActivityEvent()

        data object GoToOtherActivity : ExpensesActivityEvent()
        data class ResultFromOtherActivity(val result: Any?) : ExpensesActivityEvent()

        data object UpdateButtonText : ExpensesActivityEvent()
        data object SimulateFailure : ExpensesActivityEvent()

        data object FetchDataFromThisActivityUsingCallback : ExpensesActivityEvent()
        data class DataFetchedFromThisActivityUsingCallback(val data: String?) : ExpensesActivityEvent()
    }

    sealed class ExpensesActivityEffect {
        data class Toast(val message: String) : ExpensesActivityEffect()

        data class FetchFromThisActivity(
            val onDataFetchedFromActivity: ((String) -> Unit)
        ) : ExpensesActivityEffect()

        sealed class Navigation : ExpensesActivityEffect() {
            data object NavigateToExpenseDetails : Navigation()
            data object NavigateToOtherActivity : Navigation()
        }
    }
}