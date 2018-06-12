package com.jmcaldera.roomexpenses.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Update

@Dao
interface BaseDao<in T> {

    @Insert
    fun insert(entity: T): Long

    @Insert
    fun insert(list: List<T>)

    @Update
    fun update(entity: T)

    @Delete
    fun delete(entity: T)

}