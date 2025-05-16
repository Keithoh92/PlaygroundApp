package com.example.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.playground.expensedetails.expensedetails.navigation.expenseDetailsScreen
import com.example.playground.expensesactivity.interactor.ExpensesActivityInteractor
import com.example.playground.expensesactivity.interactor.ExpensesActivityInteractorEvents
import com.example.playground.expensesactivity.navigation.expenseActivityScreen
import com.example.playground.expensesactivity.routes.ExpensesScreens
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var expensesActivityInteractor: ExpensesActivityInteractor

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        /*
        The interactor here is useful for uni directional data flow from the Screen -> Activity
        We can pass with it an lambda that takes a parameter which will be used to fire whatever it is we
        fetch from the activity back to the ViewModel.
        */
        expensesActivityInteractor.onInteractorEvent {
            when (it) {
                is ExpensesActivityInteractorEvents.FetchDataFromActivity ->
                    mockActivityActionsInteractor(it.onDataFetchedFromActivity)
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
                    fetchDataFromActivity = { screenEventLambda ->
                        // Fetching data from activity using callback
                        mockActivityActionsCallback(screenEventLambda)
                    },
                )

                expenseDetailsScreen(navController = navController)
            }
        }
    }

    private fun mockActivityActionsInteractor(onDataFetchedFromActivity: (String) -> Unit) {
        val dataFetchedFromSomewhere = "Data Fetched from Activity"
        onDataFetchedFromActivity(dataFetchedFromSomewhere)
    }

    private fun mockActivityActionsCallback(onDataFetchedFromActivity: (String) -> Unit) {
        val dataFetchedFromSomewhere = "Data Fetched from Activity"
        onDataFetchedFromActivity(dataFetchedFromSomewhere)
    }
}
