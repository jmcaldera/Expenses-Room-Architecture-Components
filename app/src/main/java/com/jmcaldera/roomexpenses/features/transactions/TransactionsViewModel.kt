package com.jmcaldera.roomexpenses.features.transactions

import android.arch.lifecycle.MutableLiveData
import com.jmcaldera.roomexpenses.core.functional.fold
import com.jmcaldera.roomexpenses.core.platform.BaseViewModel
import com.jmcaldera.roomexpenses.domain.model.Transaction
import com.jmcaldera.roomexpenses.domain.usecase.UseCase
import com.jmcaldera.roomexpenses.domain.usecase.transaction.GetTransactions
import com.jmcaldera.roomexpenses.features.model.TransactionView
import javax.inject.Inject

class TransactionsViewModel
@Inject constructor(private val getTransactions: GetTransactions) : BaseViewModel() {

    var transactions: MutableLiveData<List<TransactionView>> = MutableLiveData()

    fun getTransactions() {
        getTransactions.execute({ it.fold(::handleFailure, ::handleTransactions) }, UseCase.None())
    }

    private fun handleTransactions(list: List<Transaction>) {
        this.transactions.postValue(list.map { with(it) { TransactionView(id, categoryId, name, amount) } })
    }

}