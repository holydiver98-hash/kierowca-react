package com.example.kierowca2.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.kierowca2.data.entity.AgencyDao;
import com.example.kierowca2.data.entity.AgencyDao_Impl;
import com.example.kierowca2.data.entity.CalendarDao;
import com.example.kierowca2.data.entity.CalendarDao_Impl;
import com.example.kierowca2.data.entity.CalendarDateDao;
import com.example.kierowca2.data.entity.CalendarDateDao_Impl;
import com.example.kierowca2.data.entity.RouteDao;
import com.example.kierowca2.data.entity.RouteDao_Impl;
import com.example.kierowca2.data.entity.ShapeDao;
import com.example.kierowca2.data.entity.ShapeDao_Impl;
import com.example.kierowca2.data.entity.StopDao;
import com.example.kierowca2.data.entity.StopDao_Impl;
import com.example.kierowca2.data.entity.StopTimeDao;
import com.example.kierowca2.data.entity.StopTimeDao_Impl;
import com.example.kierowca2.data.entity.TripDao;
import com.example.kierowca2.data.entity.TripDao_Impl;
import com.example.kierowca2.data.entity.VariantDao;
import com.example.kierowca2.data.entity.VariantDao_Impl;
import com.example.kierowca2.data.entity.VehicleTypeDao;
import com.example.kierowca2.data.entity.VehicleTypeDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class GtfsDatabase_Impl extends GtfsDatabase {
  private volatile AgencyDao _agencyDao;

  private volatile RouteDao _routeDao;

  private volatile TripDao _tripDao;

  private volatile StopDao _stopDao;

  private volatile StopTimeDao _stopTimeDao;

  private volatile ShapeDao _shapeDao;

  private volatile VariantDao _variantDao;

  private volatile VehicleTypeDao _vehicleTypeDao;

  private volatile CalendarDao _calendarDao;

  private volatile CalendarDateDao _calendarDateDao;

  private volatile gtfsDao _gtfsDao;

  private volatile AppLogDao _appLogDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `agency` (`agency_id` TEXT NOT NULL, `agency_name` TEXT, `isEnabled` INTEGER NOT NULL, `agency_url` TEXT, `agency_timezone` TEXT, `agency_phone` TEXT, `agency_lang` TEXT, PRIMARY KEY(`agency_id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `calendar` (`service_id` TEXT NOT NULL, `monday` INTEGER, `tuesday` INTEGER, `wednesday` INTEGER, `thursday` INTEGER, `friday` INTEGER, `saturday` INTEGER, `sunday` INTEGER, `start_date` TEXT, `end_date` TEXT, PRIMARY KEY(`service_id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `calendar_dates` (`service_id` TEXT NOT NULL, `date` TEXT NOT NULL, `exception_type` INTEGER, PRIMARY KEY(`service_id`, `date`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `contracts_ext` (`contract_id` TEXT NOT NULL, `contract_conclusion_date` TEXT, `contract_start_date` TEXT, `contract_end_date` TEXT, `contract_number` TEXT, `contract_short_name` TEXT, `contract_operators_name` TEXT, `contract_desc` TEXT, `contract_op_id` TEXT, PRIMARY KEY(`contract_id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `control_stops` (`variant_id` TEXT NOT NULL, `stop_id` TEXT NOT NULL, PRIMARY KEY(`variant_id`, `stop_id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `feed_info` (`feed_publisher_name` TEXT NOT NULL, `feed_publisher_url` TEXT, `feed_lang` TEXT, `feed_start_date` TEXT, `feed_end_date` TEXT, PRIMARY KEY(`feed_publisher_name`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `route_types` (`route_type2_id` TEXT NOT NULL, `route_type2_name` TEXT, PRIMARY KEY(`route_type2_id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `routes` (`route_id` TEXT NOT NULL, `agency_id` TEXT, `route_short_name` TEXT, `route_long_name` TEXT, `route_desc` TEXT, `route_type` INTEGER, `route_type2_id` TEXT, `valid_from` TEXT, `valid_until` TEXT, PRIMARY KEY(`route_id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `shapes` (`shape_id` TEXT NOT NULL, `shape_pt_lat` REAL NOT NULL, `shape_pt_lon` REAL NOT NULL, `shape_pt_sequence` INTEGER NOT NULL, PRIMARY KEY(`shape_id`, `shape_pt_sequence`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `stop_times` (`trip_id` TEXT NOT NULL, `arrival_time` TEXT, `departure_time` TEXT, `stop_id` TEXT NOT NULL, `stop_sequence` INTEGER NOT NULL, `pickup_type` INTEGER, `drop_off_type` INTEGER, PRIMARY KEY(`trip_id`, `stop_sequence`))");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_stop_times_trip_id` ON `stop_times` (`trip_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_stop_times_stop_id` ON `stop_times` (`stop_id`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_stop_times_stop_sequence` ON `stop_times` (`stop_sequence`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `stops` (`stop_id` TEXT NOT NULL, `stop_code` TEXT, `stop_name` TEXT, `stop_lat` REAL, `stop_lon` REAL, `location_type` INTEGER, `parent_station` TEXT, PRIMARY KEY(`stop_id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `trips` (`trip_id` TEXT NOT NULL, `route_id` TEXT, `service_id` TEXT, `trip_headsign` TEXT, `direction_id` INTEGER, `shape_id` TEXT, `brigade_id` TEXT, `vehicle_id` TEXT, `variant_id` TEXT, PRIMARY KEY(`trip_id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `variants` (`variant_id` TEXT NOT NULL, `is_main` INTEGER, `equiv_main_variant_id` TEXT, `join_stop_id` TEXT, `disjoin_stop_id` TEXT, PRIMARY KEY(`variant_id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `vehicle_types` (`vehicle_type_id` TEXT NOT NULL, `vehicle_type_name` TEXT, `vehicle_type_description` TEXT, `vehicle_type_symbol` TEXT, PRIMARY KEY(`vehicle_type_id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `app_logs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `message` TEXT NOT NULL, `stackTrace` TEXT, `timestamp` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'cd1edd7da8b029c9360f075e76b890f2')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `agency`");
        db.execSQL("DROP TABLE IF EXISTS `calendar`");
        db.execSQL("DROP TABLE IF EXISTS `calendar_dates`");
        db.execSQL("DROP TABLE IF EXISTS `contracts_ext`");
        db.execSQL("DROP TABLE IF EXISTS `control_stops`");
        db.execSQL("DROP TABLE IF EXISTS `feed_info`");
        db.execSQL("DROP TABLE IF EXISTS `route_types`");
        db.execSQL("DROP TABLE IF EXISTS `routes`");
        db.execSQL("DROP TABLE IF EXISTS `shapes`");
        db.execSQL("DROP TABLE IF EXISTS `stop_times`");
        db.execSQL("DROP TABLE IF EXISTS `stops`");
        db.execSQL("DROP TABLE IF EXISTS `trips`");
        db.execSQL("DROP TABLE IF EXISTS `variants`");
        db.execSQL("DROP TABLE IF EXISTS `vehicle_types`");
        db.execSQL("DROP TABLE IF EXISTS `app_logs`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsAgency = new HashMap<String, TableInfo.Column>(7);
        _columnsAgency.put("agency_id", new TableInfo.Column("agency_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAgency.put("agency_name", new TableInfo.Column("agency_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAgency.put("isEnabled", new TableInfo.Column("isEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAgency.put("agency_url", new TableInfo.Column("agency_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAgency.put("agency_timezone", new TableInfo.Column("agency_timezone", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAgency.put("agency_phone", new TableInfo.Column("agency_phone", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAgency.put("agency_lang", new TableInfo.Column("agency_lang", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAgency = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAgency = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAgency = new TableInfo("agency", _columnsAgency, _foreignKeysAgency, _indicesAgency);
        final TableInfo _existingAgency = TableInfo.read(db, "agency");
        if (!_infoAgency.equals(_existingAgency)) {
          return new RoomOpenHelper.ValidationResult(false, "agency(com.example.kierowca2.data.entity.AgencyEntity).\n"
                  + " Expected:\n" + _infoAgency + "\n"
                  + " Found:\n" + _existingAgency);
        }
        final HashMap<String, TableInfo.Column> _columnsCalendar = new HashMap<String, TableInfo.Column>(10);
        _columnsCalendar.put("service_id", new TableInfo.Column("service_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCalendar.put("monday", new TableInfo.Column("monday", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCalendar.put("tuesday", new TableInfo.Column("tuesday", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCalendar.put("wednesday", new TableInfo.Column("wednesday", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCalendar.put("thursday", new TableInfo.Column("thursday", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCalendar.put("friday", new TableInfo.Column("friday", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCalendar.put("saturday", new TableInfo.Column("saturday", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCalendar.put("sunday", new TableInfo.Column("sunday", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCalendar.put("start_date", new TableInfo.Column("start_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCalendar.put("end_date", new TableInfo.Column("end_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCalendar = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCalendar = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCalendar = new TableInfo("calendar", _columnsCalendar, _foreignKeysCalendar, _indicesCalendar);
        final TableInfo _existingCalendar = TableInfo.read(db, "calendar");
        if (!_infoCalendar.equals(_existingCalendar)) {
          return new RoomOpenHelper.ValidationResult(false, "calendar(com.example.kierowca2.data.entity.CalendarEntity).\n"
                  + " Expected:\n" + _infoCalendar + "\n"
                  + " Found:\n" + _existingCalendar);
        }
        final HashMap<String, TableInfo.Column> _columnsCalendarDates = new HashMap<String, TableInfo.Column>(3);
        _columnsCalendarDates.put("service_id", new TableInfo.Column("service_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCalendarDates.put("date", new TableInfo.Column("date", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCalendarDates.put("exception_type", new TableInfo.Column("exception_type", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCalendarDates = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCalendarDates = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCalendarDates = new TableInfo("calendar_dates", _columnsCalendarDates, _foreignKeysCalendarDates, _indicesCalendarDates);
        final TableInfo _existingCalendarDates = TableInfo.read(db, "calendar_dates");
        if (!_infoCalendarDates.equals(_existingCalendarDates)) {
          return new RoomOpenHelper.ValidationResult(false, "calendar_dates(com.example.kierowca2.data.entity.CalendarDateEntity).\n"
                  + " Expected:\n" + _infoCalendarDates + "\n"
                  + " Found:\n" + _existingCalendarDates);
        }
        final HashMap<String, TableInfo.Column> _columnsContractsExt = new HashMap<String, TableInfo.Column>(9);
        _columnsContractsExt.put("contract_id", new TableInfo.Column("contract_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContractsExt.put("contract_conclusion_date", new TableInfo.Column("contract_conclusion_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContractsExt.put("contract_start_date", new TableInfo.Column("contract_start_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContractsExt.put("contract_end_date", new TableInfo.Column("contract_end_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContractsExt.put("contract_number", new TableInfo.Column("contract_number", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContractsExt.put("contract_short_name", new TableInfo.Column("contract_short_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContractsExt.put("contract_operators_name", new TableInfo.Column("contract_operators_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContractsExt.put("contract_desc", new TableInfo.Column("contract_desc", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContractsExt.put("contract_op_id", new TableInfo.Column("contract_op_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysContractsExt = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesContractsExt = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoContractsExt = new TableInfo("contracts_ext", _columnsContractsExt, _foreignKeysContractsExt, _indicesContractsExt);
        final TableInfo _existingContractsExt = TableInfo.read(db, "contracts_ext");
        if (!_infoContractsExt.equals(_existingContractsExt)) {
          return new RoomOpenHelper.ValidationResult(false, "contracts_ext(com.example.kierowca2.data.entity.ContractExtEntity).\n"
                  + " Expected:\n" + _infoContractsExt + "\n"
                  + " Found:\n" + _existingContractsExt);
        }
        final HashMap<String, TableInfo.Column> _columnsControlStops = new HashMap<String, TableInfo.Column>(2);
        _columnsControlStops.put("variant_id", new TableInfo.Column("variant_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsControlStops.put("stop_id", new TableInfo.Column("stop_id", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysControlStops = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesControlStops = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoControlStops = new TableInfo("control_stops", _columnsControlStops, _foreignKeysControlStops, _indicesControlStops);
        final TableInfo _existingControlStops = TableInfo.read(db, "control_stops");
        if (!_infoControlStops.equals(_existingControlStops)) {
          return new RoomOpenHelper.ValidationResult(false, "control_stops(com.example.kierowca2.data.entity.ControlStopEntity).\n"
                  + " Expected:\n" + _infoControlStops + "\n"
                  + " Found:\n" + _existingControlStops);
        }
        final HashMap<String, TableInfo.Column> _columnsFeedInfo = new HashMap<String, TableInfo.Column>(5);
        _columnsFeedInfo.put("feed_publisher_name", new TableInfo.Column("feed_publisher_name", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeedInfo.put("feed_publisher_url", new TableInfo.Column("feed_publisher_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeedInfo.put("feed_lang", new TableInfo.Column("feed_lang", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeedInfo.put("feed_start_date", new TableInfo.Column("feed_start_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeedInfo.put("feed_end_date", new TableInfo.Column("feed_end_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFeedInfo = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFeedInfo = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFeedInfo = new TableInfo("feed_info", _columnsFeedInfo, _foreignKeysFeedInfo, _indicesFeedInfo);
        final TableInfo _existingFeedInfo = TableInfo.read(db, "feed_info");
        if (!_infoFeedInfo.equals(_existingFeedInfo)) {
          return new RoomOpenHelper.ValidationResult(false, "feed_info(com.example.kierowca2.data.entity.FeedInfoEntity).\n"
                  + " Expected:\n" + _infoFeedInfo + "\n"
                  + " Found:\n" + _existingFeedInfo);
        }
        final HashMap<String, TableInfo.Column> _columnsRouteTypes = new HashMap<String, TableInfo.Column>(2);
        _columnsRouteTypes.put("route_type2_id", new TableInfo.Column("route_type2_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRouteTypes.put("route_type2_name", new TableInfo.Column("route_type2_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRouteTypes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRouteTypes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRouteTypes = new TableInfo("route_types", _columnsRouteTypes, _foreignKeysRouteTypes, _indicesRouteTypes);
        final TableInfo _existingRouteTypes = TableInfo.read(db, "route_types");
        if (!_infoRouteTypes.equals(_existingRouteTypes)) {
          return new RoomOpenHelper.ValidationResult(false, "route_types(com.example.kierowca2.data.entity.RouteTypeEntity).\n"
                  + " Expected:\n" + _infoRouteTypes + "\n"
                  + " Found:\n" + _existingRouteTypes);
        }
        final HashMap<String, TableInfo.Column> _columnsRoutes = new HashMap<String, TableInfo.Column>(9);
        _columnsRoutes.put("route_id", new TableInfo.Column("route_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("agency_id", new TableInfo.Column("agency_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("route_short_name", new TableInfo.Column("route_short_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("route_long_name", new TableInfo.Column("route_long_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("route_desc", new TableInfo.Column("route_desc", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("route_type", new TableInfo.Column("route_type", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("route_type2_id", new TableInfo.Column("route_type2_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("valid_from", new TableInfo.Column("valid_from", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutes.put("valid_until", new TableInfo.Column("valid_until", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRoutes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRoutes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRoutes = new TableInfo("routes", _columnsRoutes, _foreignKeysRoutes, _indicesRoutes);
        final TableInfo _existingRoutes = TableInfo.read(db, "routes");
        if (!_infoRoutes.equals(_existingRoutes)) {
          return new RoomOpenHelper.ValidationResult(false, "routes(com.example.kierowca2.data.entity.RouteEntity).\n"
                  + " Expected:\n" + _infoRoutes + "\n"
                  + " Found:\n" + _existingRoutes);
        }
        final HashMap<String, TableInfo.Column> _columnsShapes = new HashMap<String, TableInfo.Column>(4);
        _columnsShapes.put("shape_id", new TableInfo.Column("shape_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShapes.put("shape_pt_lat", new TableInfo.Column("shape_pt_lat", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShapes.put("shape_pt_lon", new TableInfo.Column("shape_pt_lon", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShapes.put("shape_pt_sequence", new TableInfo.Column("shape_pt_sequence", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysShapes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesShapes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoShapes = new TableInfo("shapes", _columnsShapes, _foreignKeysShapes, _indicesShapes);
        final TableInfo _existingShapes = TableInfo.read(db, "shapes");
        if (!_infoShapes.equals(_existingShapes)) {
          return new RoomOpenHelper.ValidationResult(false, "shapes(com.example.kierowca2.data.entity.ShapePointEntity).\n"
                  + " Expected:\n" + _infoShapes + "\n"
                  + " Found:\n" + _existingShapes);
        }
        final HashMap<String, TableInfo.Column> _columnsStopTimes = new HashMap<String, TableInfo.Column>(7);
        _columnsStopTimes.put("trip_id", new TableInfo.Column("trip_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStopTimes.put("arrival_time", new TableInfo.Column("arrival_time", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStopTimes.put("departure_time", new TableInfo.Column("departure_time", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStopTimes.put("stop_id", new TableInfo.Column("stop_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStopTimes.put("stop_sequence", new TableInfo.Column("stop_sequence", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStopTimes.put("pickup_type", new TableInfo.Column("pickup_type", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStopTimes.put("drop_off_type", new TableInfo.Column("drop_off_type", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStopTimes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStopTimes = new HashSet<TableInfo.Index>(3);
        _indicesStopTimes.add(new TableInfo.Index("index_stop_times_trip_id", false, Arrays.asList("trip_id"), Arrays.asList("ASC")));
        _indicesStopTimes.add(new TableInfo.Index("index_stop_times_stop_id", false, Arrays.asList("stop_id"), Arrays.asList("ASC")));
        _indicesStopTimes.add(new TableInfo.Index("index_stop_times_stop_sequence", false, Arrays.asList("stop_sequence"), Arrays.asList("ASC")));
        final TableInfo _infoStopTimes = new TableInfo("stop_times", _columnsStopTimes, _foreignKeysStopTimes, _indicesStopTimes);
        final TableInfo _existingStopTimes = TableInfo.read(db, "stop_times");
        if (!_infoStopTimes.equals(_existingStopTimes)) {
          return new RoomOpenHelper.ValidationResult(false, "stop_times(com.example.kierowca2.data.entity.StopTimeEntity).\n"
                  + " Expected:\n" + _infoStopTimes + "\n"
                  + " Found:\n" + _existingStopTimes);
        }
        final HashMap<String, TableInfo.Column> _columnsStops = new HashMap<String, TableInfo.Column>(7);
        _columnsStops.put("stop_id", new TableInfo.Column("stop_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStops.put("stop_code", new TableInfo.Column("stop_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStops.put("stop_name", new TableInfo.Column("stop_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStops.put("stop_lat", new TableInfo.Column("stop_lat", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStops.put("stop_lon", new TableInfo.Column("stop_lon", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStops.put("location_type", new TableInfo.Column("location_type", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStops.put("parent_station", new TableInfo.Column("parent_station", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStops = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStops = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStops = new TableInfo("stops", _columnsStops, _foreignKeysStops, _indicesStops);
        final TableInfo _existingStops = TableInfo.read(db, "stops");
        if (!_infoStops.equals(_existingStops)) {
          return new RoomOpenHelper.ValidationResult(false, "stops(com.example.kierowca2.data.entity.StopEntity).\n"
                  + " Expected:\n" + _infoStops + "\n"
                  + " Found:\n" + _existingStops);
        }
        final HashMap<String, TableInfo.Column> _columnsTrips = new HashMap<String, TableInfo.Column>(9);
        _columnsTrips.put("trip_id", new TableInfo.Column("trip_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrips.put("route_id", new TableInfo.Column("route_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrips.put("service_id", new TableInfo.Column("service_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrips.put("trip_headsign", new TableInfo.Column("trip_headsign", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrips.put("direction_id", new TableInfo.Column("direction_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrips.put("shape_id", new TableInfo.Column("shape_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrips.put("brigade_id", new TableInfo.Column("brigade_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrips.put("vehicle_id", new TableInfo.Column("vehicle_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrips.put("variant_id", new TableInfo.Column("variant_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTrips = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTrips = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTrips = new TableInfo("trips", _columnsTrips, _foreignKeysTrips, _indicesTrips);
        final TableInfo _existingTrips = TableInfo.read(db, "trips");
        if (!_infoTrips.equals(_existingTrips)) {
          return new RoomOpenHelper.ValidationResult(false, "trips(com.example.kierowca2.data.entity.TripEntity).\n"
                  + " Expected:\n" + _infoTrips + "\n"
                  + " Found:\n" + _existingTrips);
        }
        final HashMap<String, TableInfo.Column> _columnsVariants = new HashMap<String, TableInfo.Column>(5);
        _columnsVariants.put("variant_id", new TableInfo.Column("variant_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVariants.put("is_main", new TableInfo.Column("is_main", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVariants.put("equiv_main_variant_id", new TableInfo.Column("equiv_main_variant_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVariants.put("join_stop_id", new TableInfo.Column("join_stop_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVariants.put("disjoin_stop_id", new TableInfo.Column("disjoin_stop_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVariants = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVariants = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVariants = new TableInfo("variants", _columnsVariants, _foreignKeysVariants, _indicesVariants);
        final TableInfo _existingVariants = TableInfo.read(db, "variants");
        if (!_infoVariants.equals(_existingVariants)) {
          return new RoomOpenHelper.ValidationResult(false, "variants(com.example.kierowca2.data.entity.VariantEntity).\n"
                  + " Expected:\n" + _infoVariants + "\n"
                  + " Found:\n" + _existingVariants);
        }
        final HashMap<String, TableInfo.Column> _columnsVehicleTypes = new HashMap<String, TableInfo.Column>(4);
        _columnsVehicleTypes.put("vehicle_type_id", new TableInfo.Column("vehicle_type_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVehicleTypes.put("vehicle_type_name", new TableInfo.Column("vehicle_type_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVehicleTypes.put("vehicle_type_description", new TableInfo.Column("vehicle_type_description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVehicleTypes.put("vehicle_type_symbol", new TableInfo.Column("vehicle_type_symbol", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVehicleTypes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVehicleTypes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVehicleTypes = new TableInfo("vehicle_types", _columnsVehicleTypes, _foreignKeysVehicleTypes, _indicesVehicleTypes);
        final TableInfo _existingVehicleTypes = TableInfo.read(db, "vehicle_types");
        if (!_infoVehicleTypes.equals(_existingVehicleTypes)) {
          return new RoomOpenHelper.ValidationResult(false, "vehicle_types(com.example.kierowca2.data.entity.VehicleTypeEntity).\n"
                  + " Expected:\n" + _infoVehicleTypes + "\n"
                  + " Found:\n" + _existingVehicleTypes);
        }
        final HashMap<String, TableInfo.Column> _columnsAppLogs = new HashMap<String, TableInfo.Column>(4);
        _columnsAppLogs.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppLogs.put("message", new TableInfo.Column("message", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppLogs.put("stackTrace", new TableInfo.Column("stackTrace", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppLogs.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAppLogs = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAppLogs = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAppLogs = new TableInfo("app_logs", _columnsAppLogs, _foreignKeysAppLogs, _indicesAppLogs);
        final TableInfo _existingAppLogs = TableInfo.read(db, "app_logs");
        if (!_infoAppLogs.equals(_existingAppLogs)) {
          return new RoomOpenHelper.ValidationResult(false, "app_logs(com.example.kierowca2.data.entity.AppLogEntity).\n"
                  + " Expected:\n" + _infoAppLogs + "\n"
                  + " Found:\n" + _existingAppLogs);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "cd1edd7da8b029c9360f075e76b890f2", "7fabea0de8f931c7c634667fc28fd7fe");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "agency","calendar","calendar_dates","contracts_ext","control_stops","feed_info","route_types","routes","shapes","stop_times","stops","trips","variants","vehicle_types","app_logs");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `agency`");
      _db.execSQL("DELETE FROM `calendar`");
      _db.execSQL("DELETE FROM `calendar_dates`");
      _db.execSQL("DELETE FROM `contracts_ext`");
      _db.execSQL("DELETE FROM `control_stops`");
      _db.execSQL("DELETE FROM `feed_info`");
      _db.execSQL("DELETE FROM `route_types`");
      _db.execSQL("DELETE FROM `routes`");
      _db.execSQL("DELETE FROM `shapes`");
      _db.execSQL("DELETE FROM `stop_times`");
      _db.execSQL("DELETE FROM `stops`");
      _db.execSQL("DELETE FROM `trips`");
      _db.execSQL("DELETE FROM `variants`");
      _db.execSQL("DELETE FROM `vehicle_types`");
      _db.execSQL("DELETE FROM `app_logs`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(AgencyDao.class, AgencyDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RouteDao.class, RouteDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TripDao.class, TripDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(StopDao.class, StopDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(StopTimeDao.class, StopTimeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ShapeDao.class, ShapeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(VariantDao.class, VariantDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(VehicleTypeDao.class, VehicleTypeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CalendarDao.class, CalendarDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CalendarDateDao.class, CalendarDateDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(gtfsDao.class, gtfsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AppLogDao.class, AppLogDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public AgencyDao agencyDao() {
    if (_agencyDao != null) {
      return _agencyDao;
    } else {
      synchronized(this) {
        if(_agencyDao == null) {
          _agencyDao = new AgencyDao_Impl(this);
        }
        return _agencyDao;
      }
    }
  }

  @Override
  public RouteDao routeDao() {
    if (_routeDao != null) {
      return _routeDao;
    } else {
      synchronized(this) {
        if(_routeDao == null) {
          _routeDao = new RouteDao_Impl(this);
        }
        return _routeDao;
      }
    }
  }

  @Override
  public TripDao tripDao() {
    if (_tripDao != null) {
      return _tripDao;
    } else {
      synchronized(this) {
        if(_tripDao == null) {
          _tripDao = new TripDao_Impl(this);
        }
        return _tripDao;
      }
    }
  }

  @Override
  public StopDao stopDao() {
    if (_stopDao != null) {
      return _stopDao;
    } else {
      synchronized(this) {
        if(_stopDao == null) {
          _stopDao = new StopDao_Impl(this);
        }
        return _stopDao;
      }
    }
  }

  @Override
  public StopTimeDao stopTimeDao() {
    if (_stopTimeDao != null) {
      return _stopTimeDao;
    } else {
      synchronized(this) {
        if(_stopTimeDao == null) {
          _stopTimeDao = new StopTimeDao_Impl(this);
        }
        return _stopTimeDao;
      }
    }
  }

  @Override
  public ShapeDao shapeDao() {
    if (_shapeDao != null) {
      return _shapeDao;
    } else {
      synchronized(this) {
        if(_shapeDao == null) {
          _shapeDao = new ShapeDao_Impl(this);
        }
        return _shapeDao;
      }
    }
  }

  @Override
  public VariantDao variantDao() {
    if (_variantDao != null) {
      return _variantDao;
    } else {
      synchronized(this) {
        if(_variantDao == null) {
          _variantDao = new VariantDao_Impl(this);
        }
        return _variantDao;
      }
    }
  }

  @Override
  public VehicleTypeDao vehicleTypeDao() {
    if (_vehicleTypeDao != null) {
      return _vehicleTypeDao;
    } else {
      synchronized(this) {
        if(_vehicleTypeDao == null) {
          _vehicleTypeDao = new VehicleTypeDao_Impl(this);
        }
        return _vehicleTypeDao;
      }
    }
  }

  @Override
  public CalendarDao calendarDao() {
    if (_calendarDao != null) {
      return _calendarDao;
    } else {
      synchronized(this) {
        if(_calendarDao == null) {
          _calendarDao = new CalendarDao_Impl(this);
        }
        return _calendarDao;
      }
    }
  }

  @Override
  public CalendarDateDao calendarDateDao() {
    if (_calendarDateDao != null) {
      return _calendarDateDao;
    } else {
      synchronized(this) {
        if(_calendarDateDao == null) {
          _calendarDateDao = new CalendarDateDao_Impl(this);
        }
        return _calendarDateDao;
      }
    }
  }

  @Override
  public gtfsDao gtfsDao() {
    if (_gtfsDao != null) {
      return _gtfsDao;
    } else {
      synchronized(this) {
        if(_gtfsDao == null) {
          _gtfsDao = new gtfsDao_Impl(this);
        }
        return _gtfsDao;
      }
    }
  }

  @Override
  public AppLogDao appLogDao() {
    if (_appLogDao != null) {
      return _appLogDao;
    } else {
      synchronized(this) {
        if(_appLogDao == null) {
          _appLogDao = new AppLogDao_Impl(this);
        }
        return _appLogDao;
      }
    }
  }
}
