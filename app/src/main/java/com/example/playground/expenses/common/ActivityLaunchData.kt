package com.example.playground.expenses.common

import android.app.Activity

data class ActivityLaunchData(
    val activityClass: Class<out Activity>,
    val params: Map<String, String>
)
