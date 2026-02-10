package com.example.kierowca2.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["variant_id", "stop_id"], tableName = "control_stops")
data class ControlStopEntity(
    @ColumnInfo(name = "variant_id") val variantId: String,
    @ColumnInfo(name = "stop_id") val stopId: String
)