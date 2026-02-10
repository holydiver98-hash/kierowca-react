package com.example.kierowca2.data.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kierowca2.data.entity.CalendarEntity
import com.example.kierowca2.data.entity.CalendarDateEntity

@Dao
interface CalendarDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<CalendarEntity>)

    @Query("SELECT * FROM calendar WHERE service_id = :serviceId LIMIT 1")
    suspend fun getByServiceId(serviceId: String): CalendarEntity?

    @Query("DELETE FROM calendar")
    suspend fun clear()
}

@Dao
interface CalendarDateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<CalendarDateEntity>)

    @Query("SELECT * FROM calendar_dates WHERE service_id = :serviceId")
    suspend fun getDatesByService(serviceId: String): List<CalendarDateEntity>

    @Query("DELETE FROM calendar_dates")
    suspend fun clear()
}
