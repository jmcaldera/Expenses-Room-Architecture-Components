package com.jmcaldera.roomexpenses.domain.usecase.transaction

import com.jmcaldera.roomexpenses.core.exception.Failure
import com.jmcaldera.roomexpenses.core.functional.Either
import com.jmcaldera.roomexpenses.domain.Repository
import com.jmcaldera.roomexpenses.domain.usecase.UseCase
import com.jmcaldera.roomexpenses.domain.model.Transaction

class GetTransactionsForCategory(private val repository: Repository):
        UseCase<List<Transaction>, GetTransactionsForCategory.Params>() {

    override suspend fun run(params: Params): Either<Failure, List<Transaction>> =
            repository.getTransactionsForCategory(params.categoryId)

    data class Params(val categoryId: String)
}