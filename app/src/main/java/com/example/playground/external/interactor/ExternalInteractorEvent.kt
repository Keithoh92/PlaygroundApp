package com.example.playground.external.interactor

import android.content.Intent

sealed class ExternalInteractorEvent {
    data class FetchDataFromActivity(
        val onDataFetchedFromActivity: (String) -> Unit
    ) : ExternalInteractorEvent()
    data class OnBackToExpenses(val resultIntent: Intent) : ExternalInteractorEvent()
}