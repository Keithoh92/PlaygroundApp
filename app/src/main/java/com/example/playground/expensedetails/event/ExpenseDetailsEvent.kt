package com.example.playground.expensedetails.event

sealed class ExpenseDetailsEvent {
    data class NavigateUp(val dataToPassBack: String) : ExpenseDetailsEvent()
}
