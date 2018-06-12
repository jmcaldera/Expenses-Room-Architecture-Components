package com.jmcaldera.roomexpenses.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.jmcaldera.roomexpenses.core.extensions.empty
import com.jmcaldera.roomexpenses.domain.model.Transaction
import java.util.*

@Entity(tableName = "transactions",
        foreignKeys = [(ForeignKey(entity = CategoryEntity::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("categoryId")))])
data class TransactionEntity(
        @PrimaryKey val id: String = UUID.randomUUID().toString(),
        val categoryId: String,
        val name: String = String.empty(),
        val amount: Int = 0
) {
    fun toTransaction() = Transaction(id, categoryId, name, amount)
}