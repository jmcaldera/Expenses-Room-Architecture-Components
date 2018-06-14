package com.jmcaldera.roomexpenses

import android.app.Application
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
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
        initTimeZone()
        injectMembers()
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun initTimeZone() {
        AndroidThreeTen.init(this)
    }

    private fun injectMembers() = appComponent.inject(this)
}