package com.jmcaldera.roomexpenses.core.di

import android.content.Context
import com.jmcaldera.roomexpenses.ExpensesApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: ExpensesApplication) {

    @Provides @Singleton fun provideApplicationContext(): Context = application

    @Provides @Singleton fun provideApplication(): ExpensesApplication = application

}