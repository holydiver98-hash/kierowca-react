package com.example.kierowca2.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["shape_id","shape_pt_sequence"], tableName = "shapes")
data class ShapePointEntity(
    @ColumnInfo(name = "shape_id") val shapeId: String,
    @ColumnInfo(name = "shape_pt_lat") val shapePtLat: Double,
    @ColumnInfo(name = "shape_pt_lon") val shapePtLon: Double,
    @ColumnInfo(name = "shape_pt_sequence") val shapePtSequence: Int
)