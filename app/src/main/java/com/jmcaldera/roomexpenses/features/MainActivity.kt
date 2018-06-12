package com.jmcaldera.roomexpenses.features

import com.jmcaldera.roomexpenses.core.platform.BaseActivity
import com.jmcaldera.roomexpenses.core.platform.BaseFragment
import com.jmcaldera.roomexpenses.features.transactions.TransactionsFragment

class MainActivity : BaseActivity() {

    override fun fragment(): BaseFragment = TransactionsFragment.newInstance()

}
