package com.example.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.playground.expenses.expensedetails.navigation.expenseDetailsScreen
import com.example.playground.expenses.expensesactivity.event.ExpensesActivityEvent
import com.example.playground.expenses.expensesactivity.interactor.ExpensesActivityInteractor
import com.example.playground.expenses.expensesactivity.interactor.ExpensesActivityInteractorEvents
import com.example.playground.expenses.expensesactivity.navigation.expenseActivityScreen
import com.example.playground.expenses.expensesactivity.routes.ExpensesScreens
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var expensesActivityInteractor: ExpensesActivityInteractor

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        expensesActivityInteractor.onInteractorEvent {
            when (it) {
                is ExpensesActivityInteractorEvents.FetchDataFromActivity ->
                    mockActivityActions(it.onDataFetchedFromActivity)
            }
        }

        setContent {
            navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = ExpensesScreens.ExpensesActivity.route
            ) {
                expenseActivityScreen(
                    interactor = expensesActivityInteractor,
                    navController = navController,
                )

                expenseDetailsScreen(navController = navController)
            }
        }
    }

    private fun mockActivityActions(onDataFetchedFromActivity: (String) -> Unit) {
        val dataFetchedFromSomewhere = "Data Fetched from Activity"
        onDataFetchedFromActivity(dataFetchedFromSomewhere)
    }
}
