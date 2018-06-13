package com.jmcaldera.roomexpenses.core.platform

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity
import com.jmcaldera.roomexpenses.core.extensions.inTransaction
import com.jmcaldera.roomexpenses.R


abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(
                R.id.fragmentContainer) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    private fun addFragment(savedInstanceState: Bundle?) =
            savedInstanceState ?: supportFragmentManager.inTransaction { add(
                    container(), fragment()) }

    abstract fun fragment(): BaseFragment

    @IdRes
    fun container() : Int = R.id.fragmentContainer
}