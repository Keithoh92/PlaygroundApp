package com.example.playground.external.model

import kotlin.time.TimeMark

data class SnackbarModel(
    val message: String = "",
    val timeMark: TimeMark? = null
)
