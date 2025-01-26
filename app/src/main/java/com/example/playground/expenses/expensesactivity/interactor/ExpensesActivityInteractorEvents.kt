package com.example.playground.expenses.expensesactivity.interactor

sealed class ExpensesActivityInteractorEvents {
    data class FetchDataFromActivity(val onDataFetchedFromActivity: (String) -> Unit) : ExpensesActivityInteractorEvents()
}