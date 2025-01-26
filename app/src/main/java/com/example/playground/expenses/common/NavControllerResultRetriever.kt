package com.example.playground.expenses.common

import androidx.navigation.NavController

class NavControllerResultRetriever(val navController: NavController) {
    fun getData(key: String): String? {
        val value = navController.currentBackStackEntry?.savedStateHandle?.get<String>(key)
        navController.currentBackStackEntry?.savedStateHandle?.apply { set(key, null) }
        return value
    }
}