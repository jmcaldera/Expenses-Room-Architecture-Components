package com.jmcaldera.roomexpenses.core.di.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.jmcaldera.roomexpenses.features.SharedViewModel
import com.jmcaldera.roomexpenses.features.addtransaction.AddTransactionViewModel
import com.jmcaldera.roomexpenses.features.transactions.TransactionsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TransactionsViewModel::class)
    abstract fun bindsTransactionsViewModel(transactionsViewModel: TransactionsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddTransactionViewModel::class)
    abstract fun bindsAddTransactionsViewModel(transactionsViewModel: AddTransactionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel::class)
    abstract fun bindsSharedViewModel(sharedViewModel: SharedViewModel): ViewModel
}