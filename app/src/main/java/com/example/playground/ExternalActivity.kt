package com.example.playground

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.playground.external.interactor.ExternalInteractor
import com.example.playground.external.interactor.ExternalInteractorEvent
import com.example.playground.external.navigation.externalScreen
import com.example.playground.external.routes.ExternalScreens
import com.example.playground.ui.theme.PlaygroundTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ExternalActivity : ComponentActivity() {

    @Inject
    lateinit var externalInteractor: ExternalInteractor

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        externalInteractor.onInteractorEvent {
            when (it) {
                is ExternalInteractorEvent.FetchDataFromActivity ->
                    mockActivityActions(it.onDataFetchedFromActivity)

                is ExternalInteractorEvent.OnBackToExpenses -> onBack(it.resultIntent)
            }
        }

        setContent {
            navController = rememberNavController()

            PlaygroundTheme {
                NavHost(
                    navController = navController,
                    startDestination = ExternalScreens.ExternalScreen.route
                ) {
                    externalScreen(interactor = externalInteractor)
                }
            }
        }
    }

    private fun onBack(resultIntent: Intent) {
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    private fun mockActivityActions(onDataFetchedFromActivity: (String) -> Unit) {
        val dataFetchedFromSomewhere = "External Activity data"
        onDataFetchedFromActivity(dataFetchedFromSomewhere)
    }
}