package com.example.playground.expenses.expensedetails.state

data class ExpenseDetailsUIState(
    val loading: Boolean = false,
    val success: Boolean = true,
    val failure: Boolean = false
)
