package com.example.kierowca2.data.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kierowca2.data.entity.TripEntity

@Dao
interface TripDao {
    @Query("SELECT * FROM trips WHERE trip_id = :tripId LIMIT 1")
    suspend fun getTrip(tripId: String): TripEntity?

    @Query("SELECT * FROM trips WHERE route_id = :routeId ORDER BY trip_headsign")
    suspend fun getTripsForRoute(routeId: String): List<TripEntity>

    @Query("SELECT DISTINCT trip_headsign FROM trips WHERE route_id = :routeId ORDER BY trip_headsign")
    suspend fun getHeadsignsForRoute(routeId: String): List<String>

    @Query("SELECT * FROM trips WHERE route_id = :routeId AND trip_headsign = :headsign")
    suspend fun getTripsForRouteAndDirection(routeId: String, headsign: String): List<TripEntity>

    @Query("""
        SELECT * FROM trips 
        WHERE route_id = :routeId 
          AND direction_id = :direction
        ORDER BY trip_id
    """)
    suspend fun getTripsByRouteAndDirection(
        routeId: String,
        direction: Int
    ): List<TripEntity>

    @Query("""
        SELECT * FROM trips
        WHERE (:routeId IS NULL OR route_id = :routeId)
          AND (:variantId IS NULL OR variant_id = :variantId)
          AND (:brigadeId IS NULL OR brigade_id = :brigadeId)
          AND (:vehicleId IS NULL OR vehicle_id = :vehicleId)
        ORDER BY trip_headsign
    """)
    suspend fun getTripsFiltered(
        routeId: String?,
        variantId: String?,
        brigadeId: String?,
        vehicleId: String?
    ): List<TripEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrips(list: List<TripEntity>)

    @Query("DELETE FROM trips")
    suspend fun clear()
}
