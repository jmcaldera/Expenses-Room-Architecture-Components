package com.jmcaldera.roomexpenses.domain.usecase.transaction

import com.jmcaldera.roomexpenses.core.exception.Failure
import com.jmcaldera.roomexpenses.core.functional.Either
import com.jmcaldera.roomexpenses.domain.TransactionsRepository
import com.jmcaldera.roomexpenses.domain.usecase.UseCase
import com.jmcaldera.roomexpenses.domain.model.Transaction
import javax.inject.Inject

class GetTransactions
@Inject constructor(private val repository: TransactionsRepository): UseCase<List<Transaction>, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, List<Transaction>> =
            repository.getAllTransactions()
}