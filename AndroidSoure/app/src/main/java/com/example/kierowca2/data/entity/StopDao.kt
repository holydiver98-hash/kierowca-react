package com.example.kierowca2.data.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kierowca2.data.entity.StopEntity

@Dao
interface StopDao {
    @Query("SELECT * FROM stops WHERE stop_id = :stopId LIMIT 1")
    suspend fun getStopById(stopId: String): StopEntity?

    @Query("SELECT * FROM stops WHERE stop_id = :stopId")
    suspend fun getStop(stopId: String): StopEntity?

    @Query("SELECT * FROM stops ORDER BY stop_name")
    suspend fun getAllStops(): List<StopEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStops(list: List<StopEntity>)

    @Query("DELETE FROM stops")
    suspend fun clear()
}
