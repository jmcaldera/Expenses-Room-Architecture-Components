package com.jmcaldera.roomexpenses.features.addtransaction

import android.arch.lifecycle.MutableLiveData
import com.jmcaldera.roomexpenses.core.functional.fold
import com.jmcaldera.roomexpenses.core.platform.BaseViewModel
import com.jmcaldera.roomexpenses.domain.model.Category
import com.jmcaldera.roomexpenses.domain.usecase.UseCase
import com.jmcaldera.roomexpenses.domain.usecase.category.GetCategories
import com.jmcaldera.roomexpenses.domain.usecase.transaction.SaveTransaction
import com.jmcaldera.roomexpenses.features.model.CategoryView
import com.jmcaldera.roomexpenses.features.model.TransactionView
import org.threeten.bp.LocalDateTime
import javax.inject.Inject

class AddTransactionViewModel
@Inject constructor(private val saveTransaction: SaveTransaction,
                    private val getCategories: GetCategories): BaseViewModel() {

    var categories: MutableLiveData<List<CategoryView>> = MutableLiveData()

    var saved: MutableLiveData<Boolean> = MutableLiveData()

    private var dateTime : LocalDateTime = LocalDateTime.now()

    fun getCategories() {
        getCategories.execute({ it.fold(::handleFailure, ::handleCategories) }, UseCase.None())
    }

    fun saveTransaction(transaction: TransactionView) {
        saveTransaction.execute({ it.fold(::handleFailure, ::handleTransaction) },
                SaveTransaction.Params(transaction.toTransaction()))
    }

    private fun handleTransaction(row: Long) {
        saved.value = row > 0
    }

    private fun handleCategories(list: List<Category>) {
        categories.postValue(list.map { with(it) { CategoryView(id, name) } })
    }

    fun getDateTime() = dateTime

    fun setDateTime(newDateTime: LocalDateTime) { dateTime = newDateTime }
}