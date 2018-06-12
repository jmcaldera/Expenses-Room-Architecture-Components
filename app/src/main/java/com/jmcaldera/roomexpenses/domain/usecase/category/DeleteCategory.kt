package com.jmcaldera.roomexpenses.domain.usecase.category

import com.jmcaldera.roomexpenses.core.exception.Failure
import com.jmcaldera.roomexpenses.core.functional.Either
import com.jmcaldera.roomexpenses.domain.CategoriesRepository
import com.jmcaldera.roomexpenses.domain.usecase.UseCase
import com.jmcaldera.roomexpenses.domain.model.Category
import javax.inject.Inject

class DeleteCategory
@Inject constructor(private val repository: CategoriesRepository): UseCase<Int, DeleteCategory.Params>() {

    override suspend fun run(params: Params): Either<Failure, Int> =
            repository.deleteCategory(params.category)

    data class Params(val category: Category)
}