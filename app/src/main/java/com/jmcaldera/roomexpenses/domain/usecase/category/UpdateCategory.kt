package com.jmcaldera.roomexpenses.domain.usecase.category

import com.jmcaldera.roomexpenses.core.exception.Failure
import com.jmcaldera.roomexpenses.core.functional.Either
import com.jmcaldera.roomexpenses.domain.CategoriesRepository
import com.jmcaldera.roomexpenses.domain.usecase.UseCase
import com.jmcaldera.roomexpenses.domain.model.Category
import javax.inject.Inject

class UpdateCategory
@Inject constructor(private val repository: CategoriesRepository): UseCase<Int, UpdateCategory.Params>() {

    override suspend fun run(params: Params): Either<Failure, Int> =
            repository.updateCategory(params.category)

    data class Params(val category: Category)
}