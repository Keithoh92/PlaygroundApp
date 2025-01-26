package com.example.playground.expenses.expensedetails.interactor

class ExpensesDetailsInteractor {

    private var onOutgoingEventListener: ((ExpenseDetailsInteractorEvents) -> Unit)? = null

    fun onInteractorEvent(listener: (ExpenseDetailsInteractorEvents) -> Unit) {
        onOutgoingEventListener = listener
    }

    fun navigateUp(event: ExpenseDetailsInteractorEvents) {
        onOutgoingEventListener?.invoke(event)
    }
}