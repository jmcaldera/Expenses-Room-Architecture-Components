package com.jmcaldera.roomexpenses.features.transactions

import android.arch.lifecycle.MutableLiveData
import com.jmcaldera.roomexpenses.core.functional.fold
import com.jmcaldera.roomexpenses.core.platform.BaseViewModel
import com.jmcaldera.roomexpenses.domain.model.TransactionCategory
import com.jmcaldera.roomexpenses.domain.usecase.UseCase
import com.jmcaldera.roomexpenses.domain.usecase.transaction.GetTransactionsCategory
import com.jmcaldera.roomexpenses.features.model.TransactionCategoryView
import javax.inject.Inject

class TransactionsViewModel
@Inject constructor(private val getTransactionsCategory: GetTransactionsCategory) : BaseViewModel() {

    var transactionsCategory: MutableLiveData<List<TransactionCategoryView>> = MutableLiveData()

    fun getTransactions() {
        getTransactionsCategory.execute({ it.fold(::handleFailure, ::handleTransactionCategory) }, UseCase.None())
    }

    private fun handleTransactionCategory(list: List<TransactionCategory>) {
        this.transactionsCategory.postValue(list.map { with(it) {
            TransactionCategoryView(transactionId, transactionName, transactionAmount,
                    transactionDate, categoryId, categoryName) }
        })
    }

}