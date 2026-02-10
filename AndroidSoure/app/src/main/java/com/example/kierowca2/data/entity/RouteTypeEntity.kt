package com.example.kierowca2.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["route_type2_id"], tableName = "route_types")
data class RouteTypeEntity(
    @ColumnInfo(name = "route_type2_id") val routeType2Id: String,
    @ColumnInfo(name = "route_type2_name") val routeType2Name: String?
)