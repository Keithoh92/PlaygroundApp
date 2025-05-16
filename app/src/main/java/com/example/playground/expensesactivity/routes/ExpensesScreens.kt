package com.example.playground.expensesactivity.routes

sealed class ExpensesScreens(val route: String) {
    data object ExpensesActivity : ExpensesScreens("expensesActivity")
    data object ExpenseDetails : ExpensesScreens("expenseDetails")
}
