package com.jmcaldera.roomexpenses.domain.usecase.transaction

import com.jmcaldera.roomexpenses.core.exception.Failure
import com.jmcaldera.roomexpenses.core.functional.Either
import com.jmcaldera.roomexpenses.domain.TransactionsRepository
import com.jmcaldera.roomexpenses.domain.usecase.UseCase
import com.jmcaldera.roomexpenses.domain.model.Transaction
import javax.inject.Inject

class UpdateTransaction
@Inject constructor(private val repository: TransactionsRepository): UseCase<Int, UpdateTransaction.Params>() {

    override suspend fun run(params: Params): Either<Failure, Int> =
            repository.updateTransaction(params.transaction)

    data class Params(val transaction: Transaction)
}