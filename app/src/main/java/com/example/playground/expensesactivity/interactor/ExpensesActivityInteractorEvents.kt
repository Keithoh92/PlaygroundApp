package com.example.playground.expensesactivity.interactor

sealed class ExpensesActivityInteractorEvents {
    data class FetchDataFromActivity(val onDataFetchedFromActivity: (String) -> Unit) : ExpensesActivityInteractorEvents()
}