package com.jmcaldera.roomexpenses.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.jmcaldera.roomexpenses.data.model.TransactionCategoryJoin

@Dao
interface TransactionCategoryDao {

    @Query("SELECT * FROM transactions INNER JOIN categories ON transactions.categoryId = categories.categoryId ORDER BY transactionDate DESC")
    fun getAllTransactionsWithCategory() : List<TransactionCategoryJoin>
}