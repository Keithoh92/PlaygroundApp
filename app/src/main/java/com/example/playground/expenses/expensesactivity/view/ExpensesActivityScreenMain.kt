package com.example.playground.expenses.expensesactivity.view

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.example.playground.ExternalActivity
import com.example.playground.expenses.expensesactivity.effect.ExpensesActivityEffect
import com.example.playground.expenses.expensesactivity.event.ExpensesActivityEvent
import com.example.playground.expenses.expensesactivity.interactor.ExpensesActivityInteractor
import com.example.playground.expenses.expensesactivity.interactor.ExpensesActivityInteractorEvents
import com.example.playground.expenses.expensesactivity.routes.ExpensesScreens
import com.example.playground.expenses.expensesactivity.viewmodel.ExpensesActivityViewModel

@Composable
fun ExpensesActivityScreenMain(
    viewModel: ExpensesActivityViewModel,
    interactor: ExpensesActivityInteractor,
    navController: NavController,
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val contract = ActivityResultContracts.StartActivityForResult()

    // Activity Result launcher and listener
    val activityResultLauncher = rememberLauncherForActivityResult(contract = contract) { result ->
        handleActivityResult(result).apply {
            viewModel.onEvent(ExpensesActivityEvent.ResultFromOtherActivity(this))
            /*
            * Just to show we receive the result
            * We can do whatever we want with the results
            * */
            showToastMessage(context, this.toString())
        }
    }

    LaunchedEffect(lifecycleOwner) {
        if (lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    is ExpensesActivityEffect.Navigation.NavigateToOtherActivity ->
                        navigateToOtherActivity(
                            ExternalActivity::class.java,
                            activityResultLauncher,
                            context
                        )

                    is ExpensesActivityEffect.Navigation.NavigateToExpenseDetails -> {
                        navController.navigate(ExpensesScreens.ExpenseDetails.route)
                    }

                    is ExpensesActivityEffect.Toast -> {
                        showToastMessage(context, effect.message)
                    }

                    is ExpensesActivityEffect.FetchFromThisActivity ->
                        interactor.fetchDataFromActivity(
                            ExpensesActivityInteractorEvents.FetchDataFromActivity((effect.onDataFetchedFromActivity))
                        )

                    null -> Unit
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            /*
            * The Disposable Effect here is useful for disposing of anything on the ViewModel when we
            * move away from the screen since the VM stays alive in SingleActivityArchitecture when we move away
            * Example: We have a job running that is observing a flow, we would probably want to kill it before moving away
            * */
        }
    }

    ExpensesActivityScreen(
        uiState = viewModel.uiState,
        onEvent = viewModel::onEvent
    )
}

fun showToastMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun navigateToOtherActivity(
    activity: Class<out Activity>,
    activityResultLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>,
    context: Context
) {
    val intent = Intent(context, activity)
    activityResultLauncher.launch(intent)
}

private fun handleActivityResult(
    result: ActivityResult
): Any? {
    if (result.resultCode == RESULT_OK) {
        val data: Intent? = result.data
        val activityName = data?.getStringExtra("activity_name") ?: "Unknown Activity"

        val returnedValue: Any? = when (activityName) {
            "ExternalActivity" -> data?.getStringExtra("result_key")  // String
            "Activity 2" -> data?.getIntExtra("result_key", -1) // Int
            "Activity 3" -> data?.getBooleanExtra("result_key", false) // Boolean
            else -> {}
        }
        return returnedValue
    }
    return null
}
