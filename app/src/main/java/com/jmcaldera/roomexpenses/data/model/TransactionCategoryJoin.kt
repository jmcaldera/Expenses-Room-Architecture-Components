package com.jmcaldera.roomexpenses.data.model

import com.jmcaldera.roomexpenses.domain.model.TransactionCategory
import org.threeten.bp.LocalDateTime

data class TransactionCategoryJoin(
        val transactionId: String,
        val transactionName: String,
        val transactionAmount: Int,
        val transactionDate: LocalDateTime,
        val categoryId: String,
        val categoryName: String) {

    fun toTransactionCategory() = TransactionCategory(transactionId, transactionName,
            transactionAmount, transactionDate, categoryId, categoryName)
}