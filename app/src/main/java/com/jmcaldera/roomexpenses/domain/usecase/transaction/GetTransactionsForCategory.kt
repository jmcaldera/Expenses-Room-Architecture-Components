package com.jmcaldera.roomexpenses.domain.usecase.transaction

import com.jmcaldera.roomexpenses.core.exception.Failure
import com.jmcaldera.roomexpenses.core.functional.Either
import com.jmcaldera.roomexpenses.domain.TransactionsRepository
import com.jmcaldera.roomexpenses.domain.usecase.UseCase
import com.jmcaldera.roomexpenses.domain.model.Transaction
import javax.inject.Inject

class GetTransactionsForCategory
@Inject constructor(private val repository: TransactionsRepository):
        UseCase<List<Transaction>, GetTransactionsForCategory.Params>() {

    override suspend fun run(params: Params): Either<Failure, List<Transaction>> =
            repository.getTransactionsForCategory(params.categoryId)

    data class Params(val categoryId: String)
}