package com.example.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.playground.expensedetails.navigation.expenseDetailsScreen
import com.example.playground.expensesactivitywithcontracts.navigation.expenseActivityScreen
import com.example.playground.expensesactivitywithcontracts.routes.ExpensesScreens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = ExpensesScreens.ExpensesActivity.route
            ) {
                expenseActivityScreen(
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

    private fun mockActivityActionsCallback(onDataFetchedFromActivity: (String) -> Unit) {
        val dataFetchedFromSomewhere = "Data Fetched from Activity"
        onDataFetchedFromActivity(dataFetchedFromSomewhere)
    }
}
