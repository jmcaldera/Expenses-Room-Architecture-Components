package com.jmcaldera.roomexpenses.core.di

import com.jmcaldera.roomexpenses.ExpensesApplication
import com.jmcaldera.roomexpenses.core.di.viewmodel.ViewModelModule
import com.jmcaldera.roomexpenses.features.transactions.TransactionsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(app: ExpensesApplication)

    fun inject(fragment: TransactionsFragment)
}