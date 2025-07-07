package com.example.newprojectforhamza.data.local.dao

import androidx.room.*

/**
 * Reusable CRUD helpers for any Room entity.
 * Not annotated with @Dao — Room will process it
 * through the child interface that specifies the concrete type.
 */
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: T)

    @Update
    suspend fun update(entity: T)

    @Delete
    suspend fun delete(entity: T)
}
