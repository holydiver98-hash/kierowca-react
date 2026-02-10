package com.example.kierowca2.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.kierowca2.DirectionItem
import com.example.kierowca2.VehicleItem
import com.example.kierowca2.data.entity.AgencyEntity
import com.example.kierowca2.data.entity.CalendarDateEntity
import com.example.kierowca2.data.entity.CalendarEntity
import com.example.kierowca2.data.entity.ContractExtEntity
import com.example.kierowca2.data.entity.ControlStopEntity
import com.example.kierowca2.data.entity.FeedInfoEntity
import com.example.kierowca2.data.entity.RouteEntity
import com.example.kierowca2.data.entity.RouteTypeEntity
import com.example.kierowca2.data.entity.ShapePointEntity
import com.example.kierowca2.data.entity.StopEntity
import com.example.kierowca2.data.entity.StopTimeEntity
import com.example.kierowca2.data.entity.TripEntity
import com.example.kierowca2.data.entity.VariantEntity
import com.example.kierowca2.data.entity.VehicleTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface gtfsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAgencies(list: List<AgencyEntity>)

    @Update
    suspend fun updateAgency(agency: AgencyEntity)

    @Query("SELECT * FROM agency ORDER BY agency_name")
    fun getAllAgencies(): Flow<List<AgencyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutes(list: List<RouteEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRouteTypes(list: List<RouteTypeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrips(list: List<TripEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStops(list: List<StopEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStopTimes(list: List<StopTimeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShapes(list: List<ShapePointEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVariants(list: List<VariantEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCalendar(list: List<CalendarEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCalendarDates(list: List<CalendarDateEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicleTypes(list: List<VehicleTypeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContractsExt(list: List<ContractExtEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertControlStops(list: List<ControlStopEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeedInfo(list: List<FeedInfoEntity>)

    @Query("SELECT * FROM calendar")
    suspend fun getAllCalendar(): List<CalendarEntity>

    @Query("SELECT MIN(start_date) as minDate, MAX(end_date) as maxDate FROM calendar")
    suspend fun getCalendarDateRange(): DateRange?

    @Query("SELECT count(*) FROM trips")
    suspend fun getTripCount(): Int

    @Query("SELECT * FROM route_types ORDER BY route_type2_name")
    fun getAvailableRouteTypes(): Flow<List<RouteTypeEntity>>

    @Query("""
    SELECT * FROM routes 
    WHERE agency_id IN (SELECT agency_id FROM agency WHERE isEnabled = 1)
    ORDER BY route_short_name
""")
    fun getRoutesForSelectedAgencies(): Flow<List<RouteEntity>>

    @Query("""
    SELECT DISTINCT direction_id AS directionId, trip_headsign AS headsign
    FROM trips
    WHERE route_id = :routeId AND direction_id IS NOT NULL
    ORDER BY directionId, headsign
""")
    fun getDirectionsForRoute(routeId: String): Flow<List<DirectionItem>>


    // Получить все выбранные (enabled) agency
    @Query("SELECT * FROM agency WHERE isEnabled = 1 ORDER BY agency_name")
    fun getSelectedAgenciesFlow(): Flow<List<AgencyEntity>>

    // Получить routes для выбранных agency (можно фильтровать по route_type2_id null-safe)
    @Query("""
    SELECT * FROM routes
    WHERE (:routeType2Id IS NULL OR route_type2_id = :routeType2Id)
      AND agency_id IN (SELECT agency_id FROM agency WHERE isEnabled = 1)
    ORDER BY route_short_name
""")
    fun getRoutesForSelectedAgenciesFlow(routeType2Id: String? = null): Flow<List<RouteEntity>>

    // Получить уникальные direction/headsign для route
    @Query("""
    SELECT DISTINCT direction_id AS directionId, trip_headsign AS headsign
    FROM trips
    WHERE route_id = :routeId
    ORDER BY directionId, headsign
""")
    fun getDirectionsForRouteFlow(routeId: String): Flow<List<DirectionItem>>

    // Получить variant_id для route + direction
    @Query("""
    SELECT DISTINCT variant_id FROM trips
    WHERE route_id = :routeId
      AND (:directionId IS NULL OR direction_id = :directionId)
    ORDER BY variant_id
""")
    fun getVariantsForRouteAndDirectionFlow(routeId: String, directionId: Int?): Flow<List<String?>>

    // Получить brigade_id для фильтра (по route/direction/variant)
    @Query("""
    SELECT DISTINCT brigade_id FROM trips
    WHERE route_id = :routeId
      AND (:directionId IS NULL OR direction_id = :directionId)
      AND (:variantId IS NULL OR variant_id = :variantId)
    ORDER BY brigade_id
""")
    fun getBrigadesFlow(routeId: String, directionId: Int?, variantId: String?): Flow<List<String?>>

    // Получить vehicle_id и имя для фильтра (по route/direction/variant/brigade)
    @Query("""
    SELECT DISTINCT t.vehicle_id, vt.vehicle_type_name
    FROM trips t
    LEFT JOIN vehicle_types vt ON t.vehicle_id = vt.vehicle_type_id
    WHERE route_id = :routeId
      AND (:directionId IS NULL OR direction_id = :directionId)
      AND (:variantId IS NULL OR variant_id = :variantId)
      AND (:brigadeId IS NULL OR brigade_id = :brigadeId)
    ORDER BY t.vehicle_id
""")
    fun getVehiclesFlow(routeId: String, directionId: Int?, variantId: String?, brigadeId: String?): Flow<List<VehicleItem>>

    @Query("""
        SELECT DISTINCT
            t.*, 
            (SELECT substr(MIN(departure_time), 1, 5) FROM stop_times WHERE trip_id = t.trip_id) AS startTime,
            (SELECT substr(MAX(arrival_time), 1, 5) FROM stop_times WHERE trip_id = t.trip_id) AS endTime,
            vt.vehicle_type_name AS vehicleTypeName,
            v.is_main AS variantIsMain
        FROM trips AS t
        LEFT JOIN vehicle_types AS vt ON t.vehicle_id = vt.vehicle_type_id
        LEFT JOIN variants AS v ON t.variant_id = v.variant_id
        WHERE (:routeId IS NULL OR t.route_id = :routeId)
          AND (:directionId IS NULL OR t.direction_id = :directionId)
          AND (:variantId IS NULL OR t.variant_id = :variantId)
          AND (:brigadeId IS NULL OR t.brigade_id = :brigadeId)
          AND (:vehicleId IS NULL OR t.vehicle_id = :vehicleId)
          AND (:serviceId IS NULL OR t.service_id = :serviceId)
        ORDER BY startTime, t.trip_id
    """)
    suspend fun getTripsWithDetailsFiltered(
        routeId: String?,
        directionId: Int?,
        variantId: String?,
        brigadeId: String?,
        vehicleId: String?,
        serviceId: String?
    ): List<com.example.kierowca2.data.TripWithDetails>

    @Query("""
        SELECT s.*, substr(st.arrival_time, 1, 5) AS arrivalTime, st.pickup_type AS pickupType
        FROM stop_times st
        JOIN stops s ON st.stop_id = s.stop_id
        WHERE st.trip_id = :tripId
        ORDER BY st.stop_sequence
    """)
    suspend fun getStopsWithTimeForTrip(tripId: String): List<com.example.kierowca2.data.StopWithTime>

    @Query("""
    SELECT * FROM trips
    WHERE (:routeId IS NULL OR route_id = :routeId)
      AND (:directionId IS NULL OR direction_id = :directionId)
      AND (:variantId IS NULL OR variant_id = :variantId)
      AND (:brigadeId IS NULL OR brigade_id = :brigadeId)
      AND (:vehicleId IS NULL OR vehicle_id = :vehicleId)
      AND (:serviceId IS NULL OR service_id = :serviceId)
    ORDER BY trip_id
""")
    suspend fun getTripsFiltered(
        routeId: String?,
        directionId: Int?,
        variantId: String?,
        brigadeId: String?,
        vehicleId: String?,
        serviceId: String?
    ): List<TripEntity>
}
