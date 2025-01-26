package com.example.playground.external.event

import android.content.Intent

sealed class ExternalScreenEvent {
    data class OnReturnToExpensesScreen(val result: Intent) : ExternalScreenEvent()
    data object FetchFromActivity : ExternalScreenEvent()
    data class DataFetchedFromActivity(val data: String?) : ExternalScreenEvent()
}