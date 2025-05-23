package com.example.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.playground.externalactivity.navigation.externalScreen
import com.example.playground.externalactivity.routes.ExternalScreens
import com.example.playground.ui.theme.PlaygroundTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExternalActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            navController = rememberNavController()

            PlaygroundTheme {
                NavHost(
                    navController = navController,
                    startDestination = ExternalScreens.ExternalScreen.route
                ) {
                    externalScreen(
                        fetchDataFromActivity = { screenEventLambda ->
                            mockActivityActions(screenEventLambda)
                        }
                    )
                }
            }
        }
    }

    private fun mockActivityActions(onDataFetchedFromActivity: (String) -> Unit) {
        val dataFetchedFromSomewhere = "External Activity data"
        onDataFetchedFromActivity(dataFetchedFromSomewhere)
    }
}