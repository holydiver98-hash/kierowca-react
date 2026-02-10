package com.example.kierowca2.data.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kierowca2.data.entity.RouteEntity

@Dao
interface RouteDao {
    @Query("SELECT * FROM routes ORDER BY route_short_name")
    suspend fun getAllRoutes(): List<RouteEntity>

    @Query("SELECT * FROM routes WHERE route_short_name = :shortName LIMIT 1")
    suspend fun getRouteByShortName(shortName: String): RouteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutes(list: List<RouteEntity>)

    @Query("DELETE FROM routes")
    suspend fun clear()
}
