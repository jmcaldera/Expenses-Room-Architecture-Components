package com.jmcaldera.roomexpenses.domain.model

data class TransactionCategory(
        val transactionId: String,
        val transactionName: String,
        val transactionAmount: Int,
        val categoryId: String,
        val categoryName: String
)