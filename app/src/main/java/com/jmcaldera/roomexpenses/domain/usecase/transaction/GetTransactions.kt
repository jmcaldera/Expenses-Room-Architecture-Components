package com.jmcaldera.roomexpenses.domain.usecase.transaction

import com.jmcaldera.roomexpenses.core.exception.Failure
import com.jmcaldera.roomexpenses.core.functional.Either
import com.jmcaldera.roomexpenses.domain.Repository
import com.jmcaldera.roomexpenses.domain.usecase.UseCase
import com.jmcaldera.roomexpenses.domain.model.Transaction

class GetTransactions(private val repository: Repository): UseCase<List<Transaction>, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, List<Transaction>> =
            repository.getAllTransactions()
}