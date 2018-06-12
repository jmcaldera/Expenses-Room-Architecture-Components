package com.jmcaldera.roomexpenses.core.exception

sealed class Failure

class TransactionNotFound : Failure()
data class TransactionError(val t: Throwable) : Failure()
class CategoryNotFound : Failure()
data class CategoryError(val t: Throwable) : Failure()