package com.example.kierowca2.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trips")
data class TripEntity(
    @PrimaryKey @ColumnInfo(name = "trip_id") val tripId: String,
    @ColumnInfo(name = "route_id") val routeId: String?,
    @ColumnInfo(name = "service_id") val serviceId: String?,
    @ColumnInfo(name = "trip_headsign") val tripHeadsign: String?,
    @ColumnInfo(name = "direction_id") val directionId: Int?,
    @ColumnInfo(name = "shape_id") val shapeId: String?,
    @ColumnInfo(name = "brigade_id") val brigadeId: String?,     // custom field present in this GTFS
    @ColumnInfo(name = "vehicle_id") val vehicleId: String?,     // custom field present in this GTFS
    @ColumnInfo(name = "variant_id") val variantId: String?
)