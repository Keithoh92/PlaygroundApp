package com.example.playground.external.routes

sealed class ExternalScreens(val route: String) {
    data object ExternalScreen : ExternalScreens("externalScreen")
}