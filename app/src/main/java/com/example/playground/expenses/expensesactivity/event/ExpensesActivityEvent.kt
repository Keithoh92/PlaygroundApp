package com.example.playground.expenses.expensesactivity.event

sealed class ExpensesActivityEvent {
    data object NavigateToExpenseDetails : ExpensesActivityEvent()
    data class ResultFromExpenseDetails(val result: String) : ExpensesActivityEvent()

    data object GoToOtherActivity : ExpensesActivityEvent()

    data object FetchDataFromThisActivity : ExpensesActivityEvent()
    data class DataFetchedFromThisActivity(val data: String?) : ExpensesActivityEvent()

    data class ResultFromOtherActivity(val result: Any?) : ExpensesActivityEvent()
}
