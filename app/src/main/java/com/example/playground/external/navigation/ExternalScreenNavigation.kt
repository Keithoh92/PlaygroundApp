package com.example.playground.external.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.playground.external.interactor.ExternalInteractor
import com.example.playground.external.routes.ExternalScreens
import com.example.playground.external.view.ExternalScreenMain
import com.example.playground.external.viewmodel.ExternalViewModel

fun NavGraphBuilder.externalScreen(interactor: ExternalInteractor) {
    composable(route = ExternalScreens.ExternalScreen.route) { backStackEntry ->
        val viewModel = hiltViewModel<ExternalViewModel>()

        ExternalScreenMain(viewModel = viewModel, interactor = interactor)
    }
}