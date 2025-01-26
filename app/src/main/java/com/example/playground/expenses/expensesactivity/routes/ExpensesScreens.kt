package com.example.playground.expenses.expensesactivity.routes

sealed class ExpensesScreens(val route: String) {
    data object ExpensesActivity : ExpensesScreens("expensesActivity")
    data object ExpenseDetails : ExpensesScreens("expenseDetails")
}
