package com.example.playground.expensesactivity.interactor

class ExpensesActivityInteractor {

    private var interactorListener: ((ExpensesActivityInteractorEvents) -> Unit)? = null

    fun onInteractorEvent(listener: (ExpensesActivityInteractorEvents) -> Unit) {
        interactorListener = listener
    }

    fun fetchDataFromActivity(interactorEvents: ExpensesActivityInteractorEvents) {
        interactorListener?.invoke(interactorEvents)
    }
}