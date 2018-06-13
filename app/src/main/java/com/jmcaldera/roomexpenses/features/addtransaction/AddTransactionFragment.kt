package com.jmcaldera.roomexpenses.features.addtransaction


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.toast
import com.jmcaldera.diffutiltest.core.extensions.failure
import com.jmcaldera.diffutiltest.core.extensions.observe

import com.jmcaldera.roomexpenses.R
import com.jmcaldera.roomexpenses.core.exception.CategoryError
import com.jmcaldera.roomexpenses.core.exception.Failure
import com.jmcaldera.roomexpenses.core.exception.TransactionError
import com.jmcaldera.roomexpenses.core.extensions.empty
import com.jmcaldera.roomexpenses.core.extensions.viewModel
import com.jmcaldera.roomexpenses.core.platform.BaseFragment
import com.jmcaldera.roomexpenses.features.SharedViewModel
import com.jmcaldera.roomexpenses.features.addtransaction.adapter.CategoriesAdapter
import com.jmcaldera.roomexpenses.features.model.CategoryView
import com.jmcaldera.roomexpenses.features.model.TransactionView
import kotlinx.android.synthetic.main.fragment_add_transaction.*


/**
 * A simple [Fragment] subclass.
 * Use the [AddTransactionFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AddTransactionFragment : BaseFragment() {

    private lateinit var addTransactionViewModel: AddTransactionViewModel

    private lateinit var sharedViewModel: SharedViewModel

    override fun layoutId(): Int = R.layout.fragment_add_transaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        addTransactionViewModel = viewModel(viewModelFactory) {
            observe(categories, ::showCategories)
            observe(saved, ::handleSavedTransaction)
            failure(failure, ::handleError)
        }

        activity?.let {
            sharedViewModel = ViewModelProviders.of(it, viewModelFactory)[SharedViewModel::class.java]
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        loadCategories()
    }

    private fun initializeViews() {

        buttonSave.setOnClickListener {

            val transaction = TransactionView(String.empty(), (categories.selectedItem as CategoryView).id,
                    nameInputLayout.editText?.text.toString(), amountInputLayout.editText?.text.toString().toInt())

            context?.toast(transaction.toString(), Toast.LENGTH_LONG)
            addTransactionViewModel.saveTransaction(transaction)
        }

        context?.let {
            val adapter = CategoriesAdapter(it, mutableListOf())
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            categories.adapter = adapter
        }
    }

    private fun handleSavedTransaction(saved: Boolean?) {
        saved?.let {
            if (it) {
                sharedViewModel.onTransactionSaved(it)
                activity?.onBackPressed()
            }
        }
    }

    private fun showCategories(list: List<CategoryView>?) {
        list?.forEach { Log.d("Categories", it.toString()) }
        (categories.adapter as CategoriesAdapter).setList(list ?: emptyList())
    }

    private fun handleError(failure: Failure?) {
        when (failure) {

            is TransactionError -> context?.toast(failure.t.message.toString())
            is CategoryError -> context?.toast(failure.t.message.toString())
            else -> {}
        }
    }

    private fun loadCategories() {
        addTransactionViewModel.getCategories()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment AddTransactionFragment.
         */
        @JvmStatic
        fun newInstance() = AddTransactionFragment()
    }
}
