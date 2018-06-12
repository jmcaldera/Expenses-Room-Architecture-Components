package com.jmcaldera.roomexpenses.domain.usecase.transaction

import com.jmcaldera.roomexpenses.core.exception.Failure
import com.jmcaldera.roomexpenses.core.functional.Either
import com.jmcaldera.roomexpenses.domain.TransactionsRepository
import com.jmcaldera.roomexpenses.domain.usecase.UseCase
import com.jmcaldera.roomexpenses.domain.model.Transaction
import javax.inject.Inject

class SaveTransaction
@Inject constructor(private val repository: TransactionsRepository): UseCase<Long, SaveTransaction.Params>() {

    override suspend fun run(params: Params): Either<Failure, Long> =
            repository.saveTransaction(params.transaction)

    data class Params(val transaction: Transaction)
}