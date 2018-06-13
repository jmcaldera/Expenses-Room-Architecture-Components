package com.jmcaldera.roomexpenses.features.transactions


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.toast
import com.jmcaldera.diffutiltest.core.extensions.failure
import com.jmcaldera.diffutiltest.core.extensions.observe

import com.jmcaldera.roomexpenses.R
import com.jmcaldera.roomexpenses.core.exception.CategoryError
import com.jmcaldera.roomexpenses.core.exception.Failure
import com.jmcaldera.roomexpenses.core.exception.TransactionError
import com.jmcaldera.roomexpenses.core.extensions.inTransaction
import com.jmcaldera.roomexpenses.core.extensions.viewModel
import com.jmcaldera.roomexpenses.core.platform.BaseActivity
import com.jmcaldera.roomexpenses.core.platform.BaseFragment
import com.jmcaldera.roomexpenses.features.SharedViewModel
import com.jmcaldera.roomexpenses.features.addtransaction.AddTransactionFragment
import com.jmcaldera.roomexpenses.features.model.TransactionCategoryView
import com.jmcaldera.roomexpenses.features.transactions.adapter.TransactionsCategoryAdapter
import kotlinx.android.synthetic.main.fragment_transactions.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [TransactionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class TransactionsFragment : BaseFragment() {

    @Inject
    lateinit var transactionsCategoryAdapter: TransactionsCategoryAdapter

    private lateinit var transactionsViewModel: TransactionsViewModel

    private lateinit var sharedViewModel: SharedViewModel

    override fun layoutId(): Int = R.layout.fragment_transactions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        transactionsViewModel = viewModel(viewModelFactory) {
            // observe
            observe(transactionsCategory, ::showTransactionsCategory)
            failure(failure, ::handleError)
        }

        activity?.let {
            sharedViewModel = ViewModelProviders.of(it, viewModelFactory)[SharedViewModel::class.java]
                    .also {
                        observe(it.transactionSaved) { event ->
                            event?.getContentIfNotHandled()?.let { saved ->
                                if (saved) getTransactions()
                            }
                        }
                    }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTransactionsList()
        setupAddTransaction()
        getTransactions()
    }

    private fun setupTransactionsList() {
        transactionsCategoryAdapter.clickListener = { transaction ->
            context?.toast(transaction.transactionName)
        }

        transactions.adapter = transactionsCategoryAdapter
        transactions.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        transactions.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }


    private fun setupAddTransaction() {
        val fab = activity?.findViewById<FloatingActionButton>(R.id.fab)
        fab?.apply {
            setImageResource(R.drawable.ic_add_white_24dp)
            setOnClickListener {
                fragmentManager?.inTransaction {
                    replace((activity as BaseActivity).container(),
                            AddTransactionFragment.newInstance(), "add_transaction")
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                }
            }
        }
    }

    private fun handleError(failure: Failure?) {
        when (failure) {

            is TransactionError -> context?.toast(failure.t.message.toString())
            is CategoryError -> context?.toast(failure.t.message.toString())
            else -> {}
        }
    }

    private fun showTransactionsCategory(list: List<TransactionCategoryView>?) {
        list?.let {
            if (it.isNotEmpty()) {
                showList(it)
            } else {
                showEmptyList()
            }
        }
    }

    private fun showList(list: List<TransactionCategoryView>) {
        noTransactions.isGone = true
        transactionsCategoryAdapter.items = list
    }

    private fun showEmptyList() {
        transactions.isGone = true
        noTransactions.isVisible = true
    }

    private fun getTransactions() {
        transactionsViewModel.getTransactions()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment TransactionsFragment.
         */
        @JvmStatic
        fun newInstance() = TransactionsFragment()
    }
}
