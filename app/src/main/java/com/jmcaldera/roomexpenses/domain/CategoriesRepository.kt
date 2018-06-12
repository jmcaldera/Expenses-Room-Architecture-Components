package com.jmcaldera.roomexpenses.domain

import com.jmcaldera.roomexpenses.core.exception.Failure
import com.jmcaldera.roomexpenses.core.functional.Either
import com.jmcaldera.roomexpenses.domain.model.Category

interface CategoriesRepository {
    /**
     * Categories
     */
    fun getAllCategories(): Either<Failure, List<Category>>

    fun saveCategory(category: Category): Either<Failure, Long>

    fun updateCategory(category: Category): Either<Failure, Int>

    fun deleteCategory(category: Category): Either<Failure, Int>
}