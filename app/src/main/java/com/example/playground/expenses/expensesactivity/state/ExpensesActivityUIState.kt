package com.example.playground.expenses.expensesactivity.state

data class ExpensesActivityUIState(
    val loading: Boolean = false,
    val success: Boolean = true,
    val failure: Boolean = false
)
