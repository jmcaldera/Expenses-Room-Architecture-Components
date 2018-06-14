package com.jmcaldera.roomexpenses.features.addtransaction


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
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
import com.jmcaldera.roomexpenses.features.MainActivity
import com.jmcaldera.roomexpenses.features.SharedViewModel
import com.jmcaldera.roomexpenses.features.addtransaction.adapter.CategoriesAdapter
import com.jmcaldera.roomexpenses.features.model.CategoryView
import com.jmcaldera.roomexpenses.features.model.TransactionView
import kotlinx.android.synthetic.main.fragment_add_transaction.*
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle


/**
 * A simple [Fragment] subclass.
 * Use the [AddTransactionFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AddTransactionFragment : BaseFragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
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
        hideFab()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        showFab()
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

        setDateText()
        setTimeText()
        date.setOnClickListener { showDatePicker() }
        time.setOnClickListener { showTimePicker() }
    }

    private fun setDateText() {
        date.text = addTransactionViewModel.getDateTime().toLocalDate()
                .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
    }

    private fun setTimeText() {
        time.text = addTransactionViewModel.getDateTime()
                .format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
    }

    private fun showDatePicker() {
        val dateTime = addTransactionViewModel.getDateTime()
        val datePicker = DatePickerDialog(context, this, dateTime.year,
                dateTime.monthValue - 1, dateTime.dayOfMonth)
        datePicker.show()
    }

    private fun showTimePicker() {
        val dateTime = addTransactionViewModel.getDateTime()
        val timePicker = TimePickerDialog(context, this, dateTime.hour,
                dateTime.minute, false)
        timePicker.show()
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

    private fun hideFab() {
        (activity as MainActivity).hideFab()
    }

    private fun showFab() {
        (activity as MainActivity).showFab()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val oldDate = addTransactionViewModel.getDateTime()
        addTransactionViewModel.setDateTime(LocalDateTime.of(year, month + 1, dayOfMonth, oldDate.hour, oldDate.minute))
        setDateText()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val oldDate = addTransactionViewModel.getDateTime()
        addTransactionViewModel.setDateTime(LocalDateTime.of(oldDate.year, oldDate.month, oldDate.dayOfMonth,
                hourOfDay, minute))
        setTimeText()
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
