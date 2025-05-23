package com.example.playground.expensesactivitywithcontracts.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.playground.expensesactivitywithcontracts.contract.ExpensesActivityContract
import com.example.playground.expensesactivitywithcontracts.routes.ExpensesScreens
import com.example.playground.expensesactivitywithcontracts.view.ExpensesActivityScreenMain
import com.example.playground.expensesactivitywithcontracts.viewmodel.ExpensesActivityViewModel

fun NavGraphBuilder.expenseActivityScreen(
    navController: NavHostController,
    fetchDataFromActivity: ((String) -> Unit) -> Unit,
) {
    composable(route = ExpensesScreens.ExpensesActivity.route) { backStackEntry ->
        // Inject our ViewModel
        val viewModel = hiltViewModel<ExpensesActivityViewModel>()

        // Retrieve data passed from another screen within the feature
        val retrievedData = backStackEntry.savedStateHandle.get<String>("result_key").toString()

        // Fire the retrieved data into the ViewModel
        LaunchedEffect(retrievedData) {
            viewModel.onEvent(
                ExpensesActivityContract
                    .ExpensesActivityEvent
                    .ResultFromExpenseDetails(retrievedData)
            )
        }

        ExpensesActivityScreenMain(
            viewModel = viewModel,
            navController = navController,
            fetchDataFromActivity = fetchDataFromActivity,
        )
    }
}
