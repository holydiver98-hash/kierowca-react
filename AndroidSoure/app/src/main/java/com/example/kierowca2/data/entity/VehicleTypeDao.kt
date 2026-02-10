package com.example.kierowca2.data.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kierowca2.data.entity.VehicleTypeEntity

@Dao
interface VehicleTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<VehicleTypeEntity>)

    @Query("SELECT * FROM vehicle_types")
    suspend fun getAll(): List<VehicleTypeEntity>

    @Query("DELETE FROM vehicle_types")
    suspend fun clear()
}
