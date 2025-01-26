package com.example.playground.expenses.expensedetails.interactor

sealed class ExpenseDetailsInteractorEvents {
    data class NavigateUp(val dataToPassBack: String) : ExpenseDetailsInteractorEvents()
}