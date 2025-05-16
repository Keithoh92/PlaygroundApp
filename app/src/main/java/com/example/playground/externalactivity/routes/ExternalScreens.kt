package com.example.playground.externalactivity.routes

sealed class ExternalScreens(val route: String) {
    data object ExternalScreen : ExternalScreens("externalScreen")
}