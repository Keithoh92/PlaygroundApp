package com.example.playground.expenses.expensedetails.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.playground.expenses.expensesactivity.routes.ExpensesScreens
import com.example.playground.expenses.expensedetails.view.ExpenseDetailsScreenMain
import com.example.playground.expenses.expensedetails.viewmodel.ExpenseDetailsViewModel

@Composable
fun NavController.navigateToExpenseDetails(navOptions: NavOptions? = null) {
    this.navigate(ExpensesScreens.ExpenseDetails, navOptions)
}

fun NavGraphBuilder.expenseDetailsScreen(navController: NavHostController) {
    composable(route = ExpensesScreens.ExpenseDetails.route) { backStackEntry ->
        val viewModel = hiltViewModel<ExpenseDetailsViewModel>()

        ExpenseDetailsScreenMain(viewModel = viewModel, navController = navController)
    }
}
