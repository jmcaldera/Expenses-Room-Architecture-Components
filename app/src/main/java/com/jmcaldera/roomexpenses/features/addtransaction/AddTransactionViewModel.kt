package com.jmcaldera.roomexpenses.features.addtransaction

import android.arch.lifecycle.MutableLiveData
import com.jmcaldera.roomexpenses.core.functional.fold
import com.jmcaldera.roomexpenses.core.platform.BaseViewModel
import com.jmcaldera.roomexpenses.domain.model.Category
import com.jmcaldera.roomexpenses.domain.usecase.UseCase
import com.jmcaldera.roomexpenses.domain.usecase.category.GetCategories
import com.jmcaldera.roomexpenses.domain.usecase.transaction.SaveTransaction
import com.jmcaldera.roomexpenses.features.model.CategoryView
import javax.inject.Inject

class AddTransactionViewModel
@Inject constructor(private val saveTransaction: SaveTransaction,
                    private val getCategories: GetCategories): BaseViewModel() {

    var categories: MutableLiveData<List<CategoryView>> = MutableLiveData()

    fun getCategories() {
        getCategories.execute({ it.fold(::handleFailure, ::handleCategories) }, UseCase.None())
    }

    private fun handleCategories(list: List<Category>) {
        categories.postValue(list.map { with(it) { CategoryView(id, name) } })
    }
}