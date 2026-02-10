package com.example.kierowca2

import androidx.room.ColumnInfo

data class VehicleItem(
    @ColumnInfo(name = "vehicle_id") val vehicleId: String?,
    @ColumnInfo(name = "vehicle_type_name") val vehicleTypeName: String?
)
