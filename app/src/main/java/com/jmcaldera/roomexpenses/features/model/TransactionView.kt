package com.jmcaldera.roomexpenses.features.model

import com.jmcaldera.roomexpenses.core.extensions.empty

data class TransactionView(val id: String,
                           val categoryId: String,
                           val name: String = String.empty(),
                           val amount: Int = 0)