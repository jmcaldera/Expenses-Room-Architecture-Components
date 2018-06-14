package com.jmcaldera.roomexpenses.features.model

import com.jmcaldera.roomexpenses.core.extensions.empty
import com.jmcaldera.roomexpenses.domain.model.Transaction
import org.threeten.bp.LocalDateTime

data class TransactionView(val id: String,
                           val categoryId: String,
                           val name: String = String.empty(),
                           val amount: Int = 0,
                           val date: LocalDateTime) {
    fun toTransaction() = Transaction(id, categoryId, name, amount, date)
}