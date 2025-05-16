package com.example.playground.contract

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ComposeContract<State, Event, Effect> {
    val uiState: StateFlow<State>
    val uiResult: StateFlow<UIResult<State>>
    val effect: Flow<Effect>

    fun onEvent(event: Event)

    fun setLoadedResult(block: State.() -> State)

    fun setLoadingResult(loadingType: LoadingType)

    fun setErrorResult(errorType: ErrorType)

    fun setEmptyResult(emptyType: EmptyType)

    fun CoroutineScope.emitEffect(effect: Effect)
}

/*
    We can use this one when we do not have a UIResult for the screen i.e.
    When we don't have loaded, empty or error states, we just have a uiState
 */
@Stable
@Composable
fun <State, Event, Effect> ComposeContract<State, Event, Effect>.unpack() = Triple(
    uiState.collectAsState().value, ::onEvent, effect
)

/*
    We can use this one when we can have various states for a screen i.e Loaded, Empty, Error and Loading.
 */
@Stable
@Composable
fun <State, Event, Effect> ComposeContract<State, Event, Effect>.unpackWithUiResult() = Triple(
    uiResult.collectAsState().value, ::onEvent, effect
)
