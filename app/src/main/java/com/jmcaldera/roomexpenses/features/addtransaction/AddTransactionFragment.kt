package com.jmcaldera.roomexpenses.features.addtransaction


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.toast
import com.jmcaldera.diffutiltest.core.extensions.observe

import com.jmcaldera.roomexpenses.R
import com.jmcaldera.roomexpenses.core.extensions.empty
import com.jmcaldera.roomexpenses.core.extensions.viewModel
import com.jmcaldera.roomexpenses.core.platform.BaseFragment
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

    override fun layoutId(): Int = R.layout.fragment_add_transaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        addTransactionViewModel = viewModel(viewModelFactory) {
            observe(categories, ::showCategories)
        }
    }

    private fun showCategories(list: List<CategoryView>?) {
        list?.forEach { Log.d("Categories", it.toString()) }
        (categories.adapter as CategoriesAdapter).setList(list ?: emptyList())
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
        }

        context?.let {
            val adapter = CategoriesAdapter(it, mutableListOf())
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            categories.adapter = adapter
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
