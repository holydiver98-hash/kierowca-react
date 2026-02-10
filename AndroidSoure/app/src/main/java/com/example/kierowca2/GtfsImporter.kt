package com.example.kierowca2

import android.content.Context
import android.util.Log
import com.example.kierowca2.data.GtfsDatabase
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
import com.example.kierowca2.utils.AppLogger
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileReader
import java.io.IOException
import java.util.zip.ZipInputStream

class GtfsImporter(private val context: Context, private val db: GtfsDatabase) {

    suspend fun import(url: String, onProgress: (String) -> Unit) {
        withContext(Dispatchers.IO) {
            try {
                // 0. Подготовка и бэкап
                val tripCount = db.gtfsDao().getTripCount()
                if (tripCount > 0) {
                    onProgress("Создание резервной копии...")
                    backupDatabase(context)
                }

                // Удаляем старый кеш файла перед загрузкой
                val zipFile = File(context.cacheDir, "gtfs.zip")
                if (zipFile.exists()) {
                    zipFile.delete()
                    Log.d("GtfsImporter", "Old cache zip deleted")
                }

                // 1. Скачивание
                onProgress("Загрузка данных…")
                val client = OkHttpClient()
                val request = Request.Builder().url(url).build()
                val response = client.newCall(request).execute()
                if (!response.isSuccessful) throw IOException("Ошибка загрузки: ${response.message}")

                zipFile.outputStream().use { output ->
                    response.body?.byteStream()?.copyTo(output)
                }

                // 2. Очистка базы данных ПЕРЕД распаковкой
                onProgress("Очистка старой базы данных...")
                db.clearAllTables()
                Log.d("GtfsImporter", "Database cleared")

                // 3. Распаковка
                onProgress("Распаковка архива…")
                val extractDir = File(context.filesDir, "gtfs_data")
                if (extractDir.exists()) extractDir.deleteRecursively()
                extractDir.mkdirs()

                ZipInputStream(BufferedInputStream(FileInputStream(zipFile))).use { zis ->
                    var entry = zis.nextEntry
                    while (entry != null) {
                        val outFile = File(extractDir, entry.name)
                        if (entry.isDirectory) {
                            outFile.mkdirs()
                        } else {
                            outFile.parentFile?.mkdirs()
                            outFile.outputStream().use { zis.copyTo(it) }
                        }
                        zis.closeEntry()
                        entry = zis.nextEntry
                    }
                }

                fun getCsvReader(fileName: String): CSVReader? {
                    val file = File(extractDir, fileName)
                    if (!file.exists()) {
                        val list = extractDir.listFiles() ?: return null
                        val found = list.find { it.name.equals(fileName, ignoreCase = true) } ?: return null
                        return CSVReader(FileReader(found))
                    }
                    return CSVReader(FileReader(file))
                }

                // 4. Импорт таблиц
                
                // Agency
                onProgress("Импорт: Agency...")
                getCsvReader("agency.txt")?.use { reader ->
                    reader.readNext()
                    val batch = mutableListOf<AgencyEntity>()
                    var row = reader.readNext()
                    while (row != null) {
                        if (row.isNotEmpty()) {
                            batch.add(AgencyEntity(
                                agencyId = row[0],
                                agencyName = row.getOrNull(1),
                                isEnabled = row.getOrNull(1)?.contains("MPK Autobusy") == true,
                                agencyUrl = row.getOrNull(2),
                                agencyTimezone = row.getOrNull(3),
                                agencyPhone = row.getOrNull(4),
                                agencyLang = row.getOrNull(5)
                            ))
                        }
                        row = reader.readNext()
                    }
                    db.gtfsDao().insertAgencies(batch)
                }

                // Routes
                onProgress("Импорт: Routes...")
                getCsvReader("routes.txt")?.use { reader ->
                    reader.readNext()
                    val batch = mutableListOf<RouteEntity>()
                    var row = reader.readNext()
                    while (row != null) {
                        if (row.isNotEmpty()) {
                            batch.add(RouteEntity(
                                routeId = row[0],
                                agencyId = row.getOrNull(1),
                                routeShortName = row.getOrNull(2),
                                routeLongName = row.getOrNull(3),
                                routeDesc = row.getOrNull(4),
                                routeType = row.getOrNull(5)?.toIntOrNull(),
                                routeType2Id = row.getOrNull(6),
                                validFrom = row.getOrNull(7),
                                validUntil = row.getOrNull(8)
                            ))
                            if (batch.size >= 1000) {
                                db.gtfsDao().insertRoutes(batch)
                                batch.clear()
                            }
                        }
                        row = reader.readNext()
                    }
                    if (batch.isNotEmpty()) db.gtfsDao().insertRoutes(batch)
                }

                // Trips
                onProgress("Импорт: Trips...")
                getCsvReader("trips.txt")?.use { reader ->
                    reader.readNext()
                    val batch = mutableListOf<TripEntity>()
                    var row = reader.readNext()
                    while (row != null) {
                        if (row.isNotEmpty()) {
                            batch.add(TripEntity(
                                routeId = row.getOrNull(0),
                                serviceId = row.getOrNull(1),
                                tripId = row[2],
                                tripHeadsign = row.getOrNull(3),
                                directionId = row.getOrNull(4)?.toIntOrNull(),
                                shapeId = row.getOrNull(5),
                                brigadeId = row.getOrNull(6),
                                vehicleId = row.getOrNull(7),
                                variantId = row.getOrNull(8)
                            ))
                            if (batch.size >= 2000) {
                                db.gtfsDao().insertTrips(batch)
                                batch.clear()
                            }
                        }
                        row = reader.readNext()
                    }
                    if (batch.isNotEmpty()) db.gtfsDao().insertTrips(batch)
                }

                // Stops
                onProgress("Импорт: Stops...")
                getCsvReader("stops.txt")?.use { reader ->
                    reader.readNext()
                    val batch = mutableListOf<StopEntity>()
                    var row = reader.readNext()
                    while (row != null) {
                        if (row.isNotEmpty()) {
                            batch.add(StopEntity(
                                stopId = row[0],
                                stopCode = row.getOrNull(1),
                                stopName = row.getOrNull(2),
                                stopLat = row.getOrNull(3)?.toDoubleOrNull(),
                                stopLon = row.getOrNull(4)?.toDoubleOrNull()
                            ))
                            if (batch.size >= 2000) {
                                db.gtfsDao().insertStops(batch)
                                batch.clear()
                            }
                        }
                        row = reader.readNext()
                    }
                    if (batch.isNotEmpty()) db.gtfsDao().insertStops(batch)
                }

                // Stop Times
                onProgress("Импорт: Stop Times...")
                getCsvReader("stop_times.txt")?.use { reader ->
                    reader.readNext()
                    val batch = mutableListOf<StopTimeEntity>()
                    var row = reader.readNext()
                    var count = 0
                    while (row != null) {
                        if (row.isNotEmpty()) {
                            batch.add(StopTimeEntity(
                                tripId = row[0],
                                arrivalTime = row.getOrNull(1),
                                departureTime = row.getOrNull(2),
                                stopId = row.getOrNull(3) ?: "",
                                stopSequence = row.getOrNull(4)?.toIntOrNull() ?: 0,
                                pickupType = row.getOrNull(5)?.toIntOrNull(),
                                dropOffType = row.getOrNull(6)?.toIntOrNull()
                            ))
                            if (batch.size >= 30000) {
                                db.gtfsDao().insertStopTimes(batch)
                                count += batch.size
                                onProgress("Загружено стоп-таймов: $count")
                                batch.clear()
                            }
                        }
                        row = reader.readNext()
                    }
                    if (batch.isNotEmpty()) db.gtfsDao().insertStopTimes(batch)
                }

                // Shapes
                onProgress("Импорт: Shapes...")
                getCsvReader("shapes.txt")?.use { reader ->
                    reader.readNext()
                    val batch = mutableListOf<ShapePointEntity>()
                    var row = reader.readNext()
                    while (row != null) {
                        if (row.isNotEmpty()) {
                            batch.add(ShapePointEntity(
                                shapeId = row[0],
                                shapePtLat = row[1].toDouble(),
                                shapePtLon = row[2].toDouble(),
                                shapePtSequence = row[3].toInt()
                            ))
                            if (batch.size >= 5000) {
                                db.gtfsDao().insertShapes(batch)
                                batch.clear()
                            }
                        }
                        row = reader.readNext()
                    }
                    if (batch.isNotEmpty()) db.gtfsDao().insertShapes(batch)
                }

                // Variants
                onProgress("Импорт: Variants...")
                getCsvReader("variants.txt")?.use { reader ->
                    reader.readNext()
                    val batch = mutableListOf<VariantEntity>()
                    var row = reader.readNext()
                    while (row != null) {
                        if (row.isNotEmpty()) {
                            batch.add(VariantEntity(
                                variantId = row[0],
                                isMain = row.getOrNull(1)?.toIntOrNull(),
                                equivMainVariantId = row.getOrNull(2),
                                joinStopId = row.getOrNull(3),
                                disjoinStopId = row.getOrNull(4)
                            ))
                        }
                        row = reader.readNext()
                    }
                    db.gtfsDao().insertVariants(batch)
                }

                // Calendar
                onProgress("Импорт: Calendar...")
                getCsvReader("calendar.txt")?.use { reader ->
                    reader.readNext()
                    val batch = mutableListOf<CalendarEntity>()
                    var row = reader.readNext()
                    while (row != null) {
                        if (row.isNotEmpty()) {
                            batch.add(CalendarEntity(
                                serviceId = row[0],
                                monday = row.getOrNull(1)?.toIntOrNull(),
                                tuesday = row.getOrNull(2)?.toIntOrNull(),
                                wednesday = row.getOrNull(3)?.toIntOrNull(),
                                thursday = row.getOrNull(4)?.toIntOrNull(),
                                friday = row.getOrNull(5)?.toIntOrNull(),
                                saturday = row.getOrNull(6)?.toIntOrNull(),
                                sunday = row.getOrNull(7)?.toIntOrNull(),
                                startDate = row.getOrNull(8),
                                endDate = row.getOrNull(9)
                            ))
                        }
                        row = reader.readNext()
                    }
                    db.gtfsDao().insertCalendar(batch)
                }

                // Calendar Dates
                onProgress("Импорт: Calendar Dates...")
                getCsvReader("calendar_dates.txt")?.use { reader ->
                    reader.readNext()
                    val batch = mutableListOf<CalendarDateEntity>()
                    var row = reader.readNext()
                    while (row != null) {
                        if (row.isNotEmpty()) {
                            batch.add(CalendarDateEntity(
                                serviceId = row[0],
                                date = row[1],
                                exceptionType = row.getOrNull(2)?.toIntOrNull()
                            ))
                            if (batch.size >= 1000) {
                                db.gtfsDao().insertCalendarDates(batch)
                                batch.clear()
                            }
                        }
                        row = reader.readNext()
                    }
                    if (batch.isNotEmpty()) db.gtfsDao().insertCalendarDates(batch)
                }

                // Vehicle Types
                onProgress("Импорт: Vehicle Types...")
                getCsvReader("vehicle_types.txt")?.use { reader ->
                    reader.readNext()
                    val batch = mutableListOf<VehicleTypeEntity>()
                    var row = reader.readNext()
                    while (row != null) {
                        if (row.isNotEmpty()) {
                            batch.add(VehicleTypeEntity(
                                vehicleTypeId = row[0],
                                vehicleTypeName = row.getOrNull(1),
                                vehicleTypeDescription = row.getOrNull(2),
                                vehicleTypeSymbol = row.getOrNull(3)
                            ))
                        }
                        row = reader.readNext()
                    }
                    db.gtfsDao().insertVehicleTypes(batch)
                }

                // Contracts Ext
                onProgress("Импорт: Contracts...")
                getCsvReader("contracts_ext.txt")?.use { reader ->
                    reader.readNext()
                    val batch = mutableListOf<ContractExtEntity>()
                    var row = reader.readNext()
                    while (row != null) {
                        if (row.isNotEmpty()) {
                            batch.add(ContractExtEntity(
                                contractId = row[0],
                                contractConclusionDate = row.getOrNull(1),
                                contractStartDate = row.getOrNull(2),
                                contractEndDate = row.getOrNull(3),
                                contractNumber = row.getOrNull(4),
                                contractShortName = row.getOrNull(5),
                                contractOperatorsName = row.getOrNull(6),
                                contractDesc = row.getOrNull(7),
                                contractOpId = row.getOrNull(8)
                            ))
                        }
                        row = reader.readNext()
                    }
                    db.gtfsDao().insertContractsExt(batch)
                }

                // Control Stops
                onProgress("Импорт: Control Stops...")
                getCsvReader("control_stops.txt")?.use { reader ->
                    reader.readNext()
                    val batch = mutableListOf<ControlStopEntity>()
                    var row = reader.readNext()
                    while (row != null) {
                        if (row.isNotEmpty()) {
                            batch.add(ControlStopEntity(
                                variantId = row[0],
                                stopId = row[1]
                            ))
                            if (batch.size >= 1000) {
                                db.gtfsDao().insertControlStops(batch)
                                batch.clear()
                            }
                        }
                        row = reader.readNext()
                    }
                    if (batch.isNotEmpty()) db.gtfsDao().insertControlStops(batch)
                }

                // Feed Info
                onProgress("Импорт: Feed Info...")
                getCsvReader("feed_info.txt")?.use { reader ->
                    reader.readNext()
                    val batch = mutableListOf<FeedInfoEntity>()
                    var row = reader.readNext()
                    while (row != null) {
                        if (row.isNotEmpty()) {
                            batch.add(FeedInfoEntity(
                                feedPublisherName = row[0],
                                feedPublisherUrl = row.getOrNull(1),
                                feedLang = row.getOrNull(2),
                                feedStartDate = row.getOrNull(3),
                                feedEndDate = row.getOrNull(4)
                            ))
                        }
                        row = reader.readNext()
                    }
                    db.gtfsDao().insertFeedInfo(batch)
                }

                // Route Types
                onProgress("Импорт: Route Types...")
                getCsvReader("route_types.txt")?.use { reader ->
                    reader.readNext()
                    val batch = mutableListOf<RouteTypeEntity>()
                    var row = reader.readNext()
                    while (row != null) {
                        if (row.isNotEmpty()) {
                            batch.add(RouteTypeEntity(
                                routeType2Id = row[0],
                                routeType2Name = row.getOrNull(1)
                            ))
                        }
                        row = reader.readNext()
                    }
                    db.gtfsDao().insertRouteTypes(batch)
                }

                // Очистка временных файлов
                extractDir.deleteRecursively()
                onProgress("Готово!")
                
            } catch (e: Exception) {
                AppLogger.logError(context, "GTFS Import Error", e)
                throw e
            }
        }
    }

    private fun backupDatabase(context: Context) {
        try {
            val dbFile = context.getDatabasePath("gtfs_database")
            if (dbFile.exists()) {
                val backupFile = File(dbFile.parent, "gtfs_database.bak")
                db.openHelper.writableDatabase.query("PRAGMA wal_checkpoint(FULL)")
                dbFile.copyTo(backupFile, overwrite = true)
                Log.d("GtfsImporter", "Backup created")
            }
        } catch (e: Exception) {
            Log.e("GtfsImporter", "Backup failed", e)
        }
    }
}
