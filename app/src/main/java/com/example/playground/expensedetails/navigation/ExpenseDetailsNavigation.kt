package com.example.playground.expensedetails.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.playground.expensedetails.view.ExpenseDetailsScreenMain
import com.example.playground.expensedetails.viewmodel.ExpenseDetailsViewModel
import com.example.playground.expensesactivitywithcontracts.routes.ExpensesScreens

fun NavGraphBuilder.expenseDetailsScreen(navController: NavHostController) {
    composable(route = ExpensesScreens.ExpenseDetails.route) { backStackEntry ->
        val viewModel = hiltViewModel<ExpenseDetailsViewModel>()

        ExpenseDetailsScreenMain(viewModel = viewModel, navController = navController)
    }
}
