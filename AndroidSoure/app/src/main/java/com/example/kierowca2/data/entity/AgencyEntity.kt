package com.example.kierowca2.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agency")
data class AgencyEntity(
    @PrimaryKey @ColumnInfo(name = "agency_id") val agencyId: String,
    @ColumnInfo(name = "agency_name") val agencyName: String?,
    @ColumnInfo(name = "isEnabled") val isEnabled: Boolean = false,
    @ColumnInfo(name = "agency_url") val agencyUrl: String?,
    @ColumnInfo(name = "agency_timezone") val agencyTimezone: String?,
    @ColumnInfo(name = "agency_phone") val agencyPhone: String?,
    @ColumnInfo(name = "agency_lang") val agencyLang: String?
)