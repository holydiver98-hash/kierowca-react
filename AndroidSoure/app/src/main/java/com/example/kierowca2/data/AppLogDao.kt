package com.example.kierowca2.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kierowca2.data.entity.AppLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppLogDao {
    @Insert
    suspend fun insert(log: AppLogEntity)

    @Query("SELECT * FROM app_logs ORDER BY timestamp DESC")
    fun getAllLogs(): Flow<List<AppLogEntity>>

    @Query("DELETE FROM app_logs")
    suspend fun clearLogs()
}
