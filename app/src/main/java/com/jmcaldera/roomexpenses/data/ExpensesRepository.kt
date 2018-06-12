package com.jmcaldera.roomexpenses.data

import com.jmcaldera.roomexpenses.core.exception.CategoryError
import com.jmcaldera.roomexpenses.core.exception.Failure
import com.jmcaldera.roomexpenses.core.exception.TransactionError
import com.jmcaldera.roomexpenses.core.functional.Either
import com.jmcaldera.roomexpenses.core.functional.left
import com.jmcaldera.roomexpenses.core.functional.right
import com.jmcaldera.roomexpenses.data.dao.CategoryDao
import com.jmcaldera.roomexpenses.data.dao.TransactionDao
import com.jmcaldera.roomexpenses.data.model.CategoryEntity
import com.jmcaldera.roomexpenses.data.model.TransactionEntity
import com.jmcaldera.roomexpenses.domain.CategoriesRepository
import com.jmcaldera.roomexpenses.domain.TransactionsRepository
import com.jmcaldera.roomexpenses.domain.model.Category
import com.jmcaldera.roomexpenses.domain.model.Transaction
import javax.inject.Inject
import javax.inject.Singleton

class ExpensesRepository(private val transactionDao: TransactionDao,
                         private val categoryDao: CategoryDao): TransactionsRepository, CategoriesRepository {

    /**
     * Transactions
     */
    override fun getAllTransactions(): Either<Failure, List<Transaction>> =
            try {
                right(transactionDao.getAllTransactions().map { it.toTransaction() })
            } catch (t: Throwable) {
                left(TransactionError(t))
            }

    override fun getTransactionsForCategory(categoryId: String): Either<Failure, List<Transaction>> =
            try {
                right(transactionDao.getTransactionsForCategory(categoryId).map { it.toTransaction() })
            } catch (t: Throwable) {
                left(TransactionError(t))
            }

    override fun saveTransaction(transaction: Transaction): Either<Failure, Long> =
            try {
                right(transactionDao.insert(with(transaction) {
                    TransactionEntity(categoryId = categoryId, name = name, amount = amount)
                }))
            } catch (t: Throwable) {
                left(TransactionError(t))
            }

    override fun updateTransaction(transaction: Transaction): Either<Failure, Int> =
            try {
                right(transactionDao.update(with(transaction) { TransactionEntity(id, categoryId, name, amount) }))
            } catch (t: Throwable) {
                left(TransactionError(t))
            }

    override fun deleteTransaction(transaction: Transaction): Either<Failure, Int> =
            try {
                right(transactionDao.delete(with(transaction) { TransactionEntity(id, categoryId, name, amount) }))
            } catch (t: Throwable) {
                left(TransactionError(t))
            }

    /**
     * Categories
     */
    override fun getAllCategories(): Either<Failure, List<Category>> =
            try {
                right(categoryDao.getAllCategories().map { it.toCategory() })
            } catch (t: Throwable) {
                left(CategoryError(t))
            }

    override fun saveCategory(category: Category): Either<Failure, Long> =
            try {
                right(categoryDao.insert(with(category) { CategoryEntity(name = name) }))
            } catch (t: Throwable) {
                left(CategoryError(t))
            }

    override fun updateCategory(category: Category): Either<Failure, Int> =
            try {
                right(categoryDao.update(with(category) { CategoryEntity(id, name) }))
            } catch (t: Throwable) {
                left(CategoryError(t))
            }

    override fun deleteCategory(category: Category): Either<Failure, Int> =
            try {
                right(categoryDao.delete(with(category) { CategoryEntity(id, name) }))
            } catch (t: Throwable) {
                left(CategoryError(t))
            }
}