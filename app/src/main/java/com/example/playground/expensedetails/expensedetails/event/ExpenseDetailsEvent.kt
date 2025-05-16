package com.example.playground.expensedetails.expensedetails.event

sealed class ExpenseDetailsEvent {

    data class NavigateUp(val dataToPassBack: String) : ExpenseDetailsEvent()

    /*
    * Here wo would have a bunch of events that are contained within the screen and do not need
    * to be exposed anywhere else other than inside this screen and the ViewModel will listen
    * */
}