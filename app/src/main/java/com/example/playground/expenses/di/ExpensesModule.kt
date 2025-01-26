package com.example.playground.expenses.di

import com.example.playground.expenses.expensesactivity.interactor.ExpensesActivityInteractor
import com.example.playground.external.interactor.ExternalInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ExpensesModule {

    @Provides
    fun provideExpensesActivityInteractor(): ExpensesActivityInteractor {
        return ExpensesActivityInteractor()
    }

    @Provides
    fun provideExternalInteractor(): ExternalInteractor {
        return ExternalInteractor()
    }
}