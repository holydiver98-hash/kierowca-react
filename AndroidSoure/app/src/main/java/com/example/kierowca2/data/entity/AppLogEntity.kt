package com.example.kierowca2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_logs")
data class AppLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val message: String,
    val stackTrace: String?,
    val timestamp: Long = System.currentTimeMillis()
)
