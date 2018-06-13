package com.jmcaldera.roomexpenses.features

import com.jmcaldera.roomexpenses.core.platform.BaseActivity
import com.jmcaldera.roomexpenses.core.platform.BaseFragment
import com.jmcaldera.roomexpenses.features.transactions.TransactionsFragment
import kotlinx.android.synthetic.main.activity_layout.*

class MainActivity : BaseActivity() {

    override fun fragment(): BaseFragment = TransactionsFragment.newInstance()

    fun showFab() { fab.show() }

    fun hideFab() { fab.hide() }

}
