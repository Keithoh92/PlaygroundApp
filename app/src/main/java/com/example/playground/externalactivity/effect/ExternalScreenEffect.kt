package com.example.playground.externalactivity.effect

import android.content.Intent

sealed class ExternalScreenEffect {

    data class Toast(val message: String) : ExternalScreenEffect()

    data class FetchFromActivity(val onFetchedData: ((String) -> Unit)) : ExternalScreenEffect()

    sealed class Navigation : ExternalScreenEffect() {
        data class NavigateBackToExpenses(val intent: Intent) : Navigation()
    }
}