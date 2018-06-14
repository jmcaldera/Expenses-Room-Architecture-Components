package com.jmcaldera.roomexpenses.domain.model

import com.jmcaldera.roomexpenses.core.extensions.empty
import org.threeten.bp.LocalDateTime

data class Transaction(
        val id: String,
        val categoryId: String,
        val name: String = String.empty(),
        val amount: Int = 0,
        val date: LocalDateTime
)