package com.example.kierowca2.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feed_info")
data class FeedInfoEntity(
    @PrimaryKey @ColumnInfo(name = "feed_publisher_name") val feedPublisherName: String,
    @ColumnInfo(name = "feed_publisher_url") val feedPublisherUrl: String?,
    @ColumnInfo(name = "feed_lang") val feedLang: String?,
    @ColumnInfo(name = "feed_start_date") val feedStartDate: String?,
    @ColumnInfo(name = "feed_end_date") val feedEndDate: String?
)