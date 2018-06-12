package com.jmcaldera.roomexpenses.domain.usecase.category

import com.jmcaldera.roomexpenses.core.exception.Failure
import com.jmcaldera.roomexpenses.core.functional.Either
import com.jmcaldera.roomexpenses.domain.CategoriesRepository
import com.jmcaldera.roomexpenses.domain.usecase.UseCase
import com.jmcaldera.roomexpenses.domain.model.Category
import javax.inject.Inject

class GetCategories
@Inject constructor(private val repository: CategoriesRepository): UseCase<List<Category>, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, List<Category>> =
            repository.getAllCategories()
}