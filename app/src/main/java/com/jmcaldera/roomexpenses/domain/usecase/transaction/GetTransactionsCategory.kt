package com.jmcaldera.roomexpenses.domain.usecase.transaction

import com.jmcaldera.roomexpenses.core.exception.Failure
import com.jmcaldera.roomexpenses.core.functional.Either
import com.jmcaldera.roomexpenses.domain.TransactionsRepository
import com.jmcaldera.roomexpenses.domain.model.TransactionCategory
import com.jmcaldera.roomexpenses.domain.usecase.UseCase
import javax.inject.Inject

class GetTransactionsCategory
@Inject constructor(private val repository: TransactionsRepository):
        UseCase<List<TransactionCategory>, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, List<TransactionCategory>> =
            repository.getAllTransactionsCategory()
}