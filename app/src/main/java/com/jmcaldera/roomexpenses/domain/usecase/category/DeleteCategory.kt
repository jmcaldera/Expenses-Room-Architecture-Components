package com.jmcaldera.roomexpenses.domain.usecase.category

import com.jmcaldera.roomexpenses.core.exception.Failure
import com.jmcaldera.roomexpenses.core.functional.Either
import com.jmcaldera.roomexpenses.domain.Repository
import com.jmcaldera.roomexpenses.domain.usecase.UseCase
import com.jmcaldera.roomexpenses.domain.model.Category

class DeleteCategory(private val repository: Repository): UseCase<Unit, DeleteCategory.Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> =
            repository.deleteCategory(params.category)

    data class Params(val category: Category)
}