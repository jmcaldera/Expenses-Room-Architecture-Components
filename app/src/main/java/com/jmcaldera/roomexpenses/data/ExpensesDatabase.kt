package com.jmcaldera.roomexpenses.data

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.jmcaldera.roomexpenses.data.dao.CategoryDao
import com.jmcaldera.roomexpenses.data.dao.TransactionDao
import com.jmcaldera.roomexpenses.data.model.CategoryEntity
import com.jmcaldera.roomexpenses.data.model.TransactionEntity
import kotlinx.coroutines.experimental.async

@Database(entities = [CategoryEntity::class, TransactionEntity::class], version = 1)
abstract class ExpensesDatabase: RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile private var INSTANCE: ExpensesDatabase? = null

        fun getInstance(context: Context): ExpensesDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context): ExpensesDatabase =
            Room.databaseBuilder(context, ExpensesDatabase::class.java, "expenses.db")
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            fillInDb(context)
                        }
                    }).build()


        private fun fillInDb(context: Context) {
            async { getInstance(context).categoryDao().insert(CATEGORIES.map { CategoryEntity(name = it) }) }
        }
    }
}

private val CATEGORIES = arrayListOf(
        "Aseo", "Comida", "Salidas", "Arriendo", "Agua", "Electricidad", "Internet", "Taxi",
        "Transporte Público", "Ropa", "Otros"
)