package com.jmcaldera.roomexpenses.domain

import com.jmcaldera.roomexpenses.core.exception.Failure
import com.jmcaldera.roomexpenses.core.functional.Either
import com.jmcaldera.roomexpenses.domain.model.Transaction

interface TransactionsRepository {

    /**
     * Transactions
     */
    fun getAllTransactions(): Either<Failure, List<Transaction>>

    fun getTransactionsForCategory(categoryId: String): Either<Failure, List<Transaction>>

    fun saveTransaction(transaction: Transaction): Either<Failure, Long>

    fun updateTransaction(transaction: Transaction): Either<Failure, Int>

    fun deleteTransaction(transaction: Transaction): Either<Failure, Int>

}