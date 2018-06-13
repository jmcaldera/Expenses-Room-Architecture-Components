package com.jmcaldera.roomexpenses.features

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.jmcaldera.roomexpenses.core.platform.BaseViewModel
import com.jmcaldera.roomexpenses.core.platform.Event
import javax.inject.Inject

class SharedViewModel
@Inject constructor() : BaseViewModel() {

    private val _transactionSaved = MutableLiveData<Event<Boolean>>()

    val transactionSaved: LiveData<Event<Boolean>>
        get() = _transactionSaved

    fun onTransactionSaved(saved: Boolean) {
        _transactionSaved.value = Event(saved)
    }
}