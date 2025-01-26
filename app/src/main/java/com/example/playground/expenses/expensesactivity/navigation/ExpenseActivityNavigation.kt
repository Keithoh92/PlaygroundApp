package com.example.playground.expenses.expensesactivity.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.playground.expenses.expensesactivity.event.ExpensesActivityEvent
import com.example.playground.expenses.expensesactivity.interactor.ExpensesActivityInteractor
import com.example.playground.expenses.expensesactivity.routes.ExpensesScreens
import com.example.playground.expenses.expensesactivity.view.ExpensesActivityScreenMain
import com.example.playground.expenses.expensesactivity.viewmodel.ExpensesActivityViewModel

fun NavGraphBuilder.expenseActivityScreen(
    interactor: ExpensesActivityInteractor,
    navController: NavHostController,
) {
    composable(route = ExpensesScreens.ExpensesActivity.route) { backStackEntry ->
        // Inject our ViewModel
        val viewModel = hiltViewModel<ExpensesActivityViewModel>()

        // Retrieve data passed from another screen within the feature
        val retrievedData = backStackEntry.savedStateHandle.get<String>("result_key").toString()

        // Fire the retrieved data into the ViewModel
        LaunchedEffect(retrievedData) {
            viewModel.onEvent(ExpensesActivityEvent.ResultFromExpenseDetails(retrievedData))
        }

        ExpensesActivityScreenMain(
            viewModel = viewModel,
            interactor = interactor,
            navController = navController,
        )
    }
}
