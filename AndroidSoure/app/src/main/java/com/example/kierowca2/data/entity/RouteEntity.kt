package com.example.kierowca2.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routes")
data class RouteEntity(
    @PrimaryKey @ColumnInfo(name = "route_id") val routeId: String,
    @ColumnInfo(name = "agency_id") val agencyId: String?,
    @ColumnInfo(name = "route_short_name") val routeShortName: String?,
    @ColumnInfo(name = "route_long_name") val routeLongName: String?,
    @ColumnInfo(name = "route_desc") val routeDesc: String?,
    @ColumnInfo(name = "route_type") val routeType: Int?,
    @ColumnInfo(name = "route_type2_id") val routeType2Id: String?,
    @ColumnInfo(name = "valid_from") val validFrom: String?,
    @ColumnInfo(name = "valid_until") val validUntil: String?
)