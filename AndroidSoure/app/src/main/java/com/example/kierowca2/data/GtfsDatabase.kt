package com.example.kierowca2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kierowca2.data.entity.*

@Database(
    entities = [
        AgencyEntity::class,
        CalendarEntity::class,
        CalendarDateEntity::class,
        ContractExtEntity::class,
        ControlStopEntity::class,
        FeedInfoEntity::class,
        RouteTypeEntity::class,
        RouteEntity::class,
        ShapePointEntity::class,
        StopTimeEntity::class,
        StopEntity::class,
        TripEntity::class,
        VariantEntity::class,
        VehicleTypeEntity::class,
        AppLogEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class GtfsDatabase : RoomDatabase() {

    abstract fun agencyDao(): AgencyDao
    abstract fun routeDao(): RouteDao
    abstract fun tripDao(): TripDao
    abstract fun stopDao(): StopDao
    abstract fun stopTimeDao(): StopTimeDao
    abstract fun shapeDao(): ShapeDao
    abstract fun variantDao(): VariantDao
    abstract fun vehicleTypeDao(): VehicleTypeDao
    abstract fun calendarDao(): CalendarDao
    abstract fun calendarDateDao(): CalendarDateDao
    abstract fun gtfsDao(): gtfsDao
    abstract fun appLogDao(): AppLogDao

    companion object {
        @Volatile
        private var INSTANCE: GtfsDatabase? = null

        fun getInstance(context: Context): GtfsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GtfsDatabase::class.java,
                    "gtfs_database"
                )
                    .setJournalMode(RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
