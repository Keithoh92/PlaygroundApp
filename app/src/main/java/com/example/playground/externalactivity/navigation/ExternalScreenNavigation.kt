package com.example.playground.externalactivity.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.playground.externalactivity.routes.ExternalScreens
import com.example.playground.externalactivity.view.ExternalScreenMain
import com.example.playground.externalactivity.viewmodel.ExternalViewModel

fun NavGraphBuilder.externalScreen(fetchDataFromActivity: ((String) -> Unit) -> Unit) {
    composable(route = ExternalScreens.ExternalScreen.route) {
        val viewModel = hiltViewModel<ExternalViewModel>()

        ExternalScreenMain(viewModel = viewModel, fetchDataFromActivity = fetchDataFromActivity)
    }
}