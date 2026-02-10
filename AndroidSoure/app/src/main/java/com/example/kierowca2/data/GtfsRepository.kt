package com.example.kierowca2.data

import android.content.Context
import com.example.kierowca2.GtfsImporter

class GtfsRepository(
    private val context: Context,
    private val db: GtfsDatabase
) {

    suspend fun importGtfs(url: String, onProgress: (String) -> Unit) {
        GtfsImporter(context, db).import(url, onProgress)
    }

    suspend fun getRoutes() = db.routeDao().getAllRoutes()

    suspend fun getTrips(routeId: String) = db.tripDao().getTripsForRoute(routeId)
}
