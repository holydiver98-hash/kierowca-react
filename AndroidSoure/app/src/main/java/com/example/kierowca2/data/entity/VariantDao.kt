package com.example.kierowca2.data.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kierowca2.data.entity.VariantEntity

@Dao
interface VariantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<VariantEntity>)

    @Query("SELECT * FROM variants WHERE variant_id = :variantId LIMIT 1")
    suspend fun getById(variantId: String): VariantEntity?

    @Query("SELECT * FROM variants")
    suspend fun getAll(): List<VariantEntity>

    @Query("DELETE FROM variants")
    suspend fun clear()
}
