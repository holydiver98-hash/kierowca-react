package com.example.kierowca2.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["trip_id","stop_sequence"], tableName = "stop_times",
    indices = [
        Index("trip_id"),
        Index("stop_id"),
        Index("stop_sequence")
    ])
data class StopTimeEntity(
    @ColumnInfo(name = "trip_id") val tripId: String,
    @ColumnInfo(name = "arrival_time") val arrivalTime: String?,  // HH:MM:SS
    @ColumnInfo(name = "departure_time") val departureTime: String?,
    @ColumnInfo(name = "stop_id") val stopId: String,
    @ColumnInfo(name = "stop_sequence") val stopSequence: Int,
    @ColumnInfo(name = "pickup_type") val pickupType: Int?,
    @ColumnInfo(name = "drop_off_type") val dropOffType: Int?
)