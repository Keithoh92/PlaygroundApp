package com.example.playground.expenses.expensedetails.effect

sealed class ExpenseDetailsEffect {

    sealed class Navigation : ExpenseDetailsEffect() {
        data class NavigateUp(val dataToPassBack: String) : Navigation()
    }
}
