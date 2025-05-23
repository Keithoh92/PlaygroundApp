package com.example.playground.ui

import app.cash.turbine.test
import com.example.playground.BaseTest
import com.example.playground.contract.UIResult
import com.example.playground.expensesactivitywithcontracts.contract.ExpensesActivityContract
import com.example.playground.expensesactivitywithcontracts.viewmodel.ExpensesActivityViewModel
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ExpenseActivityViewModelTest : BaseTest() {
    private val testDispatcher = StandardTestDispatcher()

    @RelaxedMockK
    private lateinit var viewmodel: ExpensesActivityViewModel

    override fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewmodel = ExpensesActivityViewModel()
    }

    @Test
    fun `GIVEN expenseActivityViewModel WHEN initialised THEN assert uiResult is Loading`() {
        assert(viewmodel.uiResult.value is UIResult.Loading)
    }

    @Test
    fun `GIVEN expenseActivityViewModel WHEN initialised THEN assert uiResult is Loaded after 3 seconds`() = runTest {
        advanceTimeBy(3001)
        assert(viewmodel.uiResult.value is UIResult.Loaded)
    }

    @Test
    fun `GIVEN viewmodel WHEN ResultFromExpenseDetails THEN assert toast effect is emitted with message`() = runTest {
        val message = "TEST"
        val expected = ExpensesActivityContract.ExpensesActivityEffect.Toast(message)

        viewmodel.onEvent(
            ExpensesActivityContract.ExpensesActivityEvent.ResultFromExpenseDetails(message)
        )

        viewmodel.effect.test {
            assertEquals(expected, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `GIVEN viewmodel WHEN UpdateButtonText event THEN assert button state is updated`() = runTest {
        advanceTimeBy(3001)
        val messageBefore = "Change my text"
        val stateBefore = viewmodel.uiState.value.successData?.button4Text
        assertEquals(messageBefore, stateBefore)

        viewmodel.onEvent(ExpensesActivityContract.ExpensesActivityEvent.UpdateButtonText)

        val messageAfter = "Successfully updated my text"
        val stateAfter = viewmodel.uiState.value.successData?.button4Text

        assertEquals(messageAfter, stateAfter)
    }

}
