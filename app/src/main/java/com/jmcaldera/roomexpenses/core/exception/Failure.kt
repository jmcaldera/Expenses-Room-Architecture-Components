package com.jmcaldera.roomexpenses.core.exception

sealed class Failure

class TransactionNotFound : Failure()
class CategoryNotFound : Failure()