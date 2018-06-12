package com.jmcaldera.roomexpenses.data.dao

import android.arch.persistence.room.*
import com.jmcaldera.roomexpenses.data.model.CategoryEntity

@Dao
interface CategoryDao: BaseDao<CategoryEntity> {

    @Query("SELECT * FROM categories")
    fun getAllCategories(): List<CategoryEntity>
}