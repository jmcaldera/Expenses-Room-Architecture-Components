package com.jmcaldera.roomexpenses.core.di

import android.content.Context
import com.jmcaldera.roomexpenses.ExpensesApplication
import com.jmcaldera.roomexpenses.data.ExpensesDatabase
import com.jmcaldera.roomexpenses.data.dao.CategoryDao
import com.jmcaldera.roomexpenses.data.dao.TransactionDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: ExpensesApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideApplication(): ExpensesApplication = application

    @Provides
    @Singleton
    fun provideExpensesDatabase(context: Context): ExpensesDatabase =
            ExpensesDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideCategoryDao(db: ExpensesDatabase): CategoryDao = db.categoryDao()

    @Provides
    @Singleton
    fun provideTransactionDao(db: ExpensesDatabase): TransactionDao = db.transactionDao()

}