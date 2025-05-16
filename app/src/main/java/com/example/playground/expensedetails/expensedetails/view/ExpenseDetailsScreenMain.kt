package com.example.playground.expensedetails.expensedetails.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import com.example.playground.expensedetails.expensedetails.effect.ExpenseDetailsEffect
import com.example.playground.expensedetails.expensedetails.viewmodel.ExpenseDetailsViewModel

@Composable
fun ExpenseDetailsScreenMain(
    viewModel: ExpenseDetailsViewModel,
    navController: NavHostController
) {

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner) {
        if (lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    is ExpenseDetailsEffect.Navigation.NavigateUp ->{
                        effect.dataToPassBack.let { dataToPassBack ->
                            navController.previousBackStackEntry?.savedStateHandle?.set(
                                "result_key", dataToPassBack
                            )
                        }
                        navController.popBackStack()
                    }
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {  }
    }

    ExpenseDetailsScreen(
        uiState = viewModel.uiState,
        onEvent = viewModel::onEvent
    )
}