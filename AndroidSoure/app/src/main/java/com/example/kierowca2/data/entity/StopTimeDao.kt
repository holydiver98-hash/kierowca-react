package com.example.kierowca2.data.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kierowca2.data.entity.StopTimeEntity
import com.example.kierowca2.data.entity.StopEntity

@Dao
interface StopTimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStopTimes(list: List<StopTimeEntity>)

    @Query("DELETE FROM stop_times")
    suspend fun clear()

    // Возвращает упорядоченные stop_id для tripId
    @Query("SELECT s.* FROM stops s INNER JOIN stop_times st ON s.stop_id = st.stop_id WHERE st.trip_id = :tripId ORDER BY st.stop_sequence ASC")
    suspend fun getOrderedStopsForTrip(tripId: String): List<StopEntity>

    // Возвращает stop_times для trip (полезно для временных расчетов)
    @Query("SELECT * FROM stop_times WHERE trip_id = :tripId ORDER BY stop_sequence ASC")
    suspend fun getStopTimesForTrip(tripId: String): List<StopTimeEntity>

    // Проверка наличия последовательности start->end в trip
    @Query("""
        SELECT COUNT(*) FROM (
            SELECT 1 FROM stop_times st1
            JOIN stop_times st2 ON st1.trip_id = st2.trip_id
            WHERE st1.stop_id = :startStop AND st2.stop_id = :endStop
              AND st1.trip_id = :tripId
              AND st1.stop_sequence < st2.stop_sequence
            LIMIT 1
        )
    """)
    suspend fun tripHasStartBeforeEnd(tripId: String, startStop: String, endStop: String): Int
}
