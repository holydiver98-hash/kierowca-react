package com.example.kierowca2.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["service_id", "date"], tableName = "calendar_dates")
data class CalendarDateEntity(
    @ColumnInfo(name = "service_id") val serviceId: String,
    @ColumnInfo(name = "date") val date: String, // YYYYMMDD
    @ColumnInfo(name = "exception_type") val exceptionType: Int?
)