package com.example.kierowca2.data

import androidx.room.Embedded
import com.example.kierowca2.data.entity.TripEntity

data class TripWithDetails(
    @Embedded
    val trip: TripEntity,
    val startTime: String?,
    val endTime: String?,
    val vehicleTypeName: String?,
    val variantIsMain: Int? // 1 = true, 0 = false
)
