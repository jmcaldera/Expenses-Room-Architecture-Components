package com.jmcaldera.roomexpenses.domain

import com.jmcaldera.roomexpenses.core.exception.Failure
import com.jmcaldera.roomexpenses.core.functional.Either
import com.jmcaldera.roomexpenses.domain.model.Category
import com.jmcaldera.roomexpenses.domain.model.Transaction

interface Repository {

    /**
     * Transactions
     */
    fun getAllTransactions(): Either<Failure, List<Transaction>>

    fun getTransactionsForCategory(categoryId: String): Either<Failure, List<Transaction>>

    fun saveTransaction(transaction: Transaction): Either<Failure, Long>

    fun updateTransaction(transaction: Transaction): Either<Failure, Unit>

    fun deleteTransaction(transaction: Transaction): Either<Failure, Unit>

    /**
     * Categories
     */
    fun getAllCategories(): Either<Failure, List<Category>>

    fun saveCategory(category: Category): Either<Failure, Long>

    fun updateCategory(category: Category): Either<Failure, Unit>

    fun deleteCategory(category: Category): Either<Failure, Unit>
}