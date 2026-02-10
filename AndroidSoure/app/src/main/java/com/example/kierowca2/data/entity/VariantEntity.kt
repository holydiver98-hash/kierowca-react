package com.example.kierowca2.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "variants")
data class VariantEntity(
    @PrimaryKey @ColumnInfo(name = "variant_id") val variantId: String,
    @ColumnInfo(name = "is_main") val isMain: Int?,
    @ColumnInfo(name = "equiv_main_variant_id") val equivMainVariantId: String?,
    @ColumnInfo(name = "join_stop_id") val joinStopId: String?,
    @ColumnInfo(name = "disjoin_stop_id") val disjoinStopId: String?
)