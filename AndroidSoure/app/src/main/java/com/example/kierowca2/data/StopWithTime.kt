package com.example.kierowca2.data

import androidx.room.Embedded
import com.example.kierowca2.data.entity.StopEntity

data class StopWithTime(
    @Embedded
    val stop: StopEntity,
    val arrivalTime: String?,
    val pickupType: Int?
)
