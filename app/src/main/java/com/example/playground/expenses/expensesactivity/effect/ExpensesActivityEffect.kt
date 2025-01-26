package com.example.playground.expenses.expensesactivity.effect

sealed class ExpensesActivityEffect {

    data class Toast(val message: String) : ExpensesActivityEffect()

    data class FetchFromThisActivity(
        val onDataFetchedFromActivity: (String) -> Unit
    ) : ExpensesActivityEffect()

    sealed class Navigation : ExpensesActivityEffect() {
        data object NavigateToExpenseDetails : Navigation()
        data object NavigateToOtherActivity : Navigation()
    }
}
