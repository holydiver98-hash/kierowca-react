package com.example.kierowca2.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicle_types")
data class VehicleTypeEntity(
    @PrimaryKey @ColumnInfo(name = "vehicle_type_id") val vehicleTypeId: String,
    @ColumnInfo(name = "vehicle_type_name") val vehicleTypeName: String?,
    @ColumnInfo(name = "vehicle_type_description") val vehicleTypeDescription: String?,
    @ColumnInfo(name = "vehicle_type_symbol") val vehicleTypeSymbol: String?
)