package com.jmcaldera.roomexpenses.data.dao

import android.arch.persistence.room.*
import com.jmcaldera.roomexpenses.data.model.TransactionEntity

@Dao
interface TransactionDao: BaseDao<TransactionEntity> {

    @Query("SELECT * FROM transactions")
    fun getAllTransactions(): List<TransactionEntity>

    @Query("SELECT * FROM transactions WHERE categoryId=:categoryId")
    fun getTransactionsForCategory(categoryId: String): List<TransactionEntity>

    @Query("SELECT * FROM transactions WHERE transactionId=:id")
    fun getTransactionById(id: String): TransactionEntity

}