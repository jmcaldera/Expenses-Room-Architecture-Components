package com.jmcaldera.roomexpenses.data.model

import android.arch.persistence.room.*
import com.jmcaldera.roomexpenses.core.extensions.empty
import com.jmcaldera.roomexpenses.domain.model.Transaction
import org.threeten.bp.LocalDateTime
import java.util.*

@Entity(tableName = "transactions",
        indices = [(Index(value = ["transactionId"]))],
        foreignKeys = [(ForeignKey(entity = CategoryEntity::class,
                parentColumns = arrayOf("categoryId"),
                childColumns = arrayOf("categoryId")))])
data class TransactionEntity(
        @PrimaryKey @ColumnInfo(name = "transactionId") val id: String = UUID.randomUUID().toString(),
        val categoryId: String,
        @ColumnInfo(name = "transactionName") val name: String = String.empty(),
        @ColumnInfo(name = "transactionAmount") val amount: Int = 0,
        @ColumnInfo(name = "transactionDate") val date: LocalDateTime
) {
    fun toTransaction() = Transaction(id, categoryId, name, amount, date)
}