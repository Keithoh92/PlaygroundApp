package com.example.playground.expensedetails.expensedetails.interactor

sealed class ExpenseDetailsInteractorEvents {
    data class NavigateUp(val dataToPassBack: String) : ExpenseDetailsInteractorEvents()
}