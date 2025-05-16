package com.example.playground.expensesactivity.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.playground.expensesactivity.contract.ExpensesActivityContract
import com.example.playground.expensesactivity.interactor.ExpensesActivityInteractor
import com.example.playground.expensesactivity.routes.ExpensesScreens
import com.example.playground.expensesactivity.view.ExpensesActivityScreenMain
import com.example.playground.expensesactivity.viewmodel.ExpensesActivityViewModel

fun NavGraphBuilder.expenseActivityScreen(
    interactor: ExpensesActivityInteractor,
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
            viewModel.onEvent(ExpensesActivityContract.ExpensesActivityEvent.ResultFromExpenseDetails(retrievedData))
        }

        ExpensesActivityScreenMain(
            viewModel = viewModel,
            interactor = interactor,
            navController = navController,
            fetchDataFromActivity = fetchDataFromActivity,
        )
    }
}
