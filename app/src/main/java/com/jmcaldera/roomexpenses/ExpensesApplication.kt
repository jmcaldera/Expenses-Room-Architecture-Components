package com.jmcaldera.roomexpenses

import android.app.Application
import com.facebook.stetho.Stetho
import com.jmcaldera.roomexpenses.core.di.ApplicationComponent
import com.jmcaldera.roomexpenses.core.di.ApplicationModule
import com.jmcaldera.roomexpenses.core.di.DaggerApplicationComponent

class ExpensesApplication: Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        initStetho()
        injectMembers()

    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun injectMembers() = appComponent.inject(this)
}