package com.example.kierowca2.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stops")
data class StopEntity(
    @PrimaryKey @ColumnInfo(name = "stop_id") val stopId: String,
    @ColumnInfo(name = "stop_code") val stopCode: String?,
    @ColumnInfo(name = "stop_name") val stopName: String?,
    @ColumnInfo(name = "stop_lat") val stopLat: Double?,
    @ColumnInfo(name = "stop_lon") val stopLon: Double?,
    @ColumnInfo(name = "location_type") val locationType: Int? = null,
    @ColumnInfo(name = "parent_station") val parentStation: String? = null
)