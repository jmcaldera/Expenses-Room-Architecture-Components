package com.jmcaldera.roomexpenses.domain.usecase.category

import com.jmcaldera.roomexpenses.core.exception.Failure
import com.jmcaldera.roomexpenses.core.functional.Either
import com.jmcaldera.roomexpenses.domain.Repository
import com.jmcaldera.roomexpenses.domain.usecase.UseCase
import com.jmcaldera.roomexpenses.domain.model.Category

class SaveCategory(private val repository: Repository): UseCase<Long, SaveCategory.Params>() {

    override suspend fun run(params: Params): Either<Failure, Long> =
            repository.saveCategory(params.category)

    data class Params(val category: Category)
}