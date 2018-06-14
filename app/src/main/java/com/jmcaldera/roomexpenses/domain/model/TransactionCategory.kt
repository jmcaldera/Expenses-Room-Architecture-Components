package com.jmcaldera.roomexpenses.domain.model

import org.threeten.bp.LocalDateTime

data class TransactionCategory(
        val transactionId: String,
        val transactionName: String,
        val transactionAmount: Int,
        val transactionDate: LocalDateTime,
        val categoryId: String,
        val categoryName: String
)