package com.example.playground.externalactivity.interactor

class ExternalInteractor {

    private var onOutgoingEventListener: ((ExternalInteractorEvent) -> Unit)? = null

    fun onInteractorEvent(listener: (ExternalInteractorEvent) -> Unit) {
        onOutgoingEventListener = listener
    }

    fun fetchDataFromActivity(interactorEvents: ExternalInteractorEvent) {
        onOutgoingEventListener?.invoke(interactorEvents)
    }

    fun backToExpenses(interactorEvents: ExternalInteractorEvent) {
        onOutgoingEventListener?.invoke(interactorEvents)
    }
}
