package com.jmcaldera.roomexpenses.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.jmcaldera.roomexpenses.domain.model.Category
import java.util.*

@Entity(tableName = "categories",
        indices = [(Index(value = ["categoryId"]))])
data class CategoryEntity(
        @PrimaryKey @ColumnInfo(name = "categoryId") val id: String = UUID.randomUUID().toString(),
        val name: String
) {
    fun toCategory() = Category(id, name)
}