package com.jmcaldera.roomexpenses.features.model

import com.jmcaldera.roomexpenses.domain.model.TransactionCategory

data class TransactionCategoryView(
        val transactionId: String,
        val transactionName: String,
        val transactionAmount: Int,
        val categoryId: String,
        val categoryName: String) {

    fun toTransactionCategory() = TransactionCategory(transactionId, transactionName,
            transactionAmount, categoryId, categoryName)
}