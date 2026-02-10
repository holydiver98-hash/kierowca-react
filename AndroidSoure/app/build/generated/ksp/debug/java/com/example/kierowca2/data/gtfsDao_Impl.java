package com.example.kierowca2.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.kierowca2.DirectionItem;
import com.example.kierowca2.VehicleItem;
import com.example.kierowca2.data.entity.AgencyEntity;
import com.example.kierowca2.data.entity.CalendarDateEntity;
import com.example.kierowca2.data.entity.CalendarEntity;
import com.example.kierowca2.data.entity.ContractExtEntity;
import com.example.kierowca2.data.entity.ControlStopEntity;
import com.example.kierowca2.data.entity.FeedInfoEntity;
import com.example.kierowca2.data.entity.RouteEntity;
import com.example.kierowca2.data.entity.RouteTypeEntity;
import com.example.kierowca2.data.entity.ShapePointEntity;
import com.example.kierowca2.data.entity.StopEntity;
import com.example.kierowca2.data.entity.StopTimeEntity;
import com.example.kierowca2.data.entity.TripEntity;
import com.example.kierowca2.data.entity.VariantEntity;
import com.example.kierowca2.data.entity.VehicleTypeEntity;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class gtfsDao_Impl implements gtfsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AgencyEntity> __insertionAdapterOfAgencyEntity;

  private final EntityInsertionAdapter<RouteEntity> __insertionAdapterOfRouteEntity;

  private final EntityInsertionAdapter<RouteTypeEntity> __insertionAdapterOfRouteTypeEntity;

  private final EntityInsertionAdapter<TripEntity> __insertionAdapterOfTripEntity;

  private final EntityInsertionAdapter<StopEntity> __insertionAdapterOfStopEntity;

  private final EntityInsertionAdapter<StopTimeEntity> __insertionAdapterOfStopTimeEntity;

  private final EntityInsertionAdapter<ShapePointEntity> __insertionAdapterOfShapePointEntity;

  private final EntityInsertionAdapter<VariantEntity> __insertionAdapterOfVariantEntity;

  private final EntityInsertionAdapter<CalendarEntity> __insertionAdapterOfCalendarEntity;

  private final EntityInsertionAdapter<CalendarDateEntity> __insertionAdapterOfCalendarDateEntity;

  private final EntityInsertionAdapter<VehicleTypeEntity> __insertionAdapterOfVehicleTypeEntity;

  private final EntityInsertionAdapter<ContractExtEntity> __insertionAdapterOfContractExtEntity;

  private final EntityInsertionAdapter<ControlStopEntity> __insertionAdapterOfControlStopEntity;

  private final EntityInsertionAdapter<FeedInfoEntity> __insertionAdapterOfFeedInfoEntity;

  private final EntityDeletionOrUpdateAdapter<AgencyEntity> __updateAdapterOfAgencyEntity;

  public gtfsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAgencyEntity = new EntityInsertionAdapter<AgencyEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `agency` (`agency_id`,`agency_name`,`isEnabled`,`agency_url`,`agency_timezone`,`agency_phone`,`agency_lang`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AgencyEntity entity) {
        statement.bindString(1, entity.getAgencyId());
        if (entity.getAgencyName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getAgencyName());
        }
        final int _tmp = entity.isEnabled() ? 1 : 0;
        statement.bindLong(3, _tmp);
        if (entity.getAgencyUrl() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getAgencyUrl());
        }
        if (entity.getAgencyTimezone() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getAgencyTimezone());
        }
        if (entity.getAgencyPhone() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getAgencyPhone());
        }
        if (entity.getAgencyLang() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getAgencyLang());
        }
      }
    };
    this.__insertionAdapterOfRouteEntity = new EntityInsertionAdapter<RouteEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `routes` (`route_id`,`agency_id`,`route_short_name`,`route_long_name`,`route_desc`,`route_type`,`route_type2_id`,`valid_from`,`valid_until`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RouteEntity entity) {
        statement.bindString(1, entity.getRouteId());
        if (entity.getAgencyId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getAgencyId());
        }
        if (entity.getRouteShortName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getRouteShortName());
        }
        if (entity.getRouteLongName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getRouteLongName());
        }
        if (entity.getRouteDesc() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getRouteDesc());
        }
        if (entity.getRouteType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getRouteType());
        }
        if (entity.getRouteType2Id() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getRouteType2Id());
        }
        if (entity.getValidFrom() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getValidFrom());
        }
        if (entity.getValidUntil() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getValidUntil());
        }
      }
    };
    this.__insertionAdapterOfRouteTypeEntity = new EntityInsertionAdapter<RouteTypeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `route_types` (`route_type2_id`,`route_type2_name`) VALUES (?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RouteTypeEntity entity) {
        statement.bindString(1, entity.getRouteType2Id());
        if (entity.getRouteType2Name() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getRouteType2Name());
        }
      }
    };
    this.__insertionAdapterOfTripEntity = new EntityInsertionAdapter<TripEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `trips` (`trip_id`,`route_id`,`service_id`,`trip_headsign`,`direction_id`,`shape_id`,`brigade_id`,`vehicle_id`,`variant_id`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TripEntity entity) {
        statement.bindString(1, entity.getTripId());
        if (entity.getRouteId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getRouteId());
        }
        if (entity.getServiceId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getServiceId());
        }
        if (entity.getTripHeadsign() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTripHeadsign());
        }
        if (entity.getDirectionId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getDirectionId());
        }
        if (entity.getShapeId() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getShapeId());
        }
        if (entity.getBrigadeId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getBrigadeId());
        }
        if (entity.getVehicleId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getVehicleId());
        }
        if (entity.getVariantId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getVariantId());
        }
      }
    };
    this.__insertionAdapterOfStopEntity = new EntityInsertionAdapter<StopEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `stops` (`stop_id`,`stop_code`,`stop_name`,`stop_lat`,`stop_lon`,`location_type`,`parent_station`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StopEntity entity) {
        statement.bindString(1, entity.getStopId());
        if (entity.getStopCode() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getStopCode());
        }
        if (entity.getStopName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getStopName());
        }
        if (entity.getStopLat() == null) {
          statement.bindNull(4);
        } else {
          statement.bindDouble(4, entity.getStopLat());
        }
        if (entity.getStopLon() == null) {
          statement.bindNull(5);
        } else {
          statement.bindDouble(5, entity.getStopLon());
        }
        if (entity.getLocationType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getLocationType());
        }
        if (entity.getParentStation() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getParentStation());
        }
      }
    };
    this.__insertionAdapterOfStopTimeEntity = new EntityInsertionAdapter<StopTimeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `stop_times` (`trip_id`,`arrival_time`,`departure_time`,`stop_id`,`stop_sequence`,`pickup_type`,`drop_off_type`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StopTimeEntity entity) {
        statement.bindString(1, entity.getTripId());
        if (entity.getArrivalTime() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getArrivalTime());
        }
        if (entity.getDepartureTime() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDepartureTime());
        }
        statement.bindString(4, entity.getStopId());
        statement.bindLong(5, entity.getStopSequence());
        if (entity.getPickupType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getPickupType());
        }
        if (entity.getDropOffType() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getDropOffType());
        }
      }
    };
    this.__insertionAdapterOfShapePointEntity = new EntityInsertionAdapter<ShapePointEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `shapes` (`shape_id`,`shape_pt_lat`,`shape_pt_lon`,`shape_pt_sequence`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ShapePointEntity entity) {
        statement.bindString(1, entity.getShapeId());
        statement.bindDouble(2, entity.getShapePtLat());
        statement.bindDouble(3, entity.getShapePtLon());
        statement.bindLong(4, entity.getShapePtSequence());
      }
    };
    this.__insertionAdapterOfVariantEntity = new EntityInsertionAdapter<VariantEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `variants` (`variant_id`,`is_main`,`equiv_main_variant_id`,`join_stop_id`,`disjoin_stop_id`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VariantEntity entity) {
        statement.bindString(1, entity.getVariantId());
        if (entity.isMain() == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, entity.isMain());
        }
        if (entity.getEquivMainVariantId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getEquivMainVariantId());
        }
        if (entity.getJoinStopId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getJoinStopId());
        }
        if (entity.getDisjoinStopId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDisjoinStopId());
        }
      }
    };
    this.__insertionAdapterOfCalendarEntity = new EntityInsertionAdapter<CalendarEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `calendar` (`service_id`,`monday`,`tuesday`,`wednesday`,`thursday`,`friday`,`saturday`,`sunday`,`start_date`,`end_date`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CalendarEntity entity) {
        statement.bindString(1, entity.getServiceId());
        if (entity.getMonday() == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, entity.getMonday());
        }
        if (entity.getTuesday() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getTuesday());
        }
        if (entity.getWednesday() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getWednesday());
        }
        if (entity.getThursday() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getThursday());
        }
        if (entity.getFriday() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getFriday());
        }
        if (entity.getSaturday() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getSaturday());
        }
        if (entity.getSunday() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getSunday());
        }
        if (entity.getStartDate() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getStartDate());
        }
        if (entity.getEndDate() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getEndDate());
        }
      }
    };
    this.__insertionAdapterOfCalendarDateEntity = new EntityInsertionAdapter<CalendarDateEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `calendar_dates` (`service_id`,`date`,`exception_type`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CalendarDateEntity entity) {
        statement.bindString(1, entity.getServiceId());
        statement.bindString(2, entity.getDate());
        if (entity.getExceptionType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getExceptionType());
        }
      }
    };
    this.__insertionAdapterOfVehicleTypeEntity = new EntityInsertionAdapter<VehicleTypeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `vehicle_types` (`vehicle_type_id`,`vehicle_type_name`,`vehicle_type_description`,`vehicle_type_symbol`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VehicleTypeEntity entity) {
        statement.bindString(1, entity.getVehicleTypeId());
        if (entity.getVehicleTypeName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getVehicleTypeName());
        }
        if (entity.getVehicleTypeDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getVehicleTypeDescription());
        }
        if (entity.getVehicleTypeSymbol() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getVehicleTypeSymbol());
        }
      }
    };
    this.__insertionAdapterOfContractExtEntity = new EntityInsertionAdapter<ContractExtEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `contracts_ext` (`contract_id`,`contract_conclusion_date`,`contract_start_date`,`contract_end_date`,`contract_number`,`contract_short_name`,`contract_operators_name`,`contract_desc`,`contract_op_id`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ContractExtEntity entity) {
        statement.bindString(1, entity.getContractId());
        if (entity.getContractConclusionDate() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getContractConclusionDate());
        }
        if (entity.getContractStartDate() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getContractStartDate());
        }
        if (entity.getContractEndDate() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getContractEndDate());
        }
        if (entity.getContractNumber() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getContractNumber());
        }
        if (entity.getContractShortName() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getContractShortName());
        }
        if (entity.getContractOperatorsName() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getContractOperatorsName());
        }
        if (entity.getContractDesc() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getContractDesc());
        }
        if (entity.getContractOpId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getContractOpId());
        }
      }
    };
    this.__insertionAdapterOfControlStopEntity = new EntityInsertionAdapter<ControlStopEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `control_stops` (`variant_id`,`stop_id`) VALUES (?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ControlStopEntity entity) {
        statement.bindString(1, entity.getVariantId());
        statement.bindString(2, entity.getStopId());
      }
    };
    this.__insertionAdapterOfFeedInfoEntity = new EntityInsertionAdapter<FeedInfoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `feed_info` (`feed_publisher_name`,`feed_publisher_url`,`feed_lang`,`feed_start_date`,`feed_end_date`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FeedInfoEntity entity) {
        statement.bindString(1, entity.getFeedPublisherName());
        if (entity.getFeedPublisherUrl() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getFeedPublisherUrl());
        }
        if (entity.getFeedLang() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getFeedLang());
        }
        if (entity.getFeedStartDate() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getFeedStartDate());
        }
        if (entity.getFeedEndDate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getFeedEndDate());
        }
      }
    };
    this.__updateAdapterOfAgencyEntity = new EntityDeletionOrUpdateAdapter<AgencyEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `agency` SET `agency_id` = ?,`agency_name` = ?,`isEnabled` = ?,`agency_url` = ?,`agency_timezone` = ?,`agency_phone` = ?,`agency_lang` = ? WHERE `agency_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AgencyEntity entity) {
        statement.bindString(1, entity.getAgencyId());
        if (entity.getAgencyName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getAgencyName());
        }
        final int _tmp = entity.isEnabled() ? 1 : 0;
        statement.bindLong(3, _tmp);
        if (entity.getAgencyUrl() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getAgencyUrl());
        }
        if (entity.getAgencyTimezone() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getAgencyTimezone());
        }
        if (entity.getAgencyPhone() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getAgencyPhone());
        }
        if (entity.getAgencyLang() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getAgencyLang());
        }
        statement.bindString(8, entity.getAgencyId());
      }
    };
  }

  @Override
  public Object insertAgencies(final List<AgencyEntity> list,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAgencyEntity.insert(list);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertRoutes(final List<RouteEntity> list,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRouteEntity.insert(list);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertRouteTypes(final List<RouteTypeEntity> list,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRouteTypeEntity.insert(list);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertTrips(final List<TripEntity> list,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTripEntity.insert(list);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertStops(final List<StopEntity> list,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfStopEntity.insert(list);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertStopTimes(final List<StopTimeEntity> list,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfStopTimeEntity.insert(list);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertShapes(final List<ShapePointEntity> list,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfShapePointEntity.insert(list);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertVariants(final List<VariantEntity> list,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfVariantEntity.insert(list);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertCalendar(final List<CalendarEntity> list,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCalendarEntity.insert(list);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertCalendarDates(final List<CalendarDateEntity> list,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCalendarDateEntity.insert(list);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertVehicleTypes(final List<VehicleTypeEntity> list,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfVehicleTypeEntity.insert(list);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertContractsExt(final List<ContractExtEntity> list,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfContractExtEntity.insert(list);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertControlStops(final List<ControlStopEntity> list,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfControlStopEntity.insert(list);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertFeedInfo(final List<FeedInfoEntity> list,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFeedInfoEntity.insert(list);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateAgency(final AgencyEntity agency,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAgencyEntity.handle(agency);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<AgencyEntity>> getAllAgencies() {
    final String _sql = "SELECT * FROM agency ORDER BY agency_name";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"agency"}, new Callable<List<AgencyEntity>>() {
      @Override
      @NonNull
      public List<AgencyEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfAgencyId = CursorUtil.getColumnIndexOrThrow(_cursor, "agency_id");
          final int _cursorIndexOfAgencyName = CursorUtil.getColumnIndexOrThrow(_cursor, "agency_name");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfAgencyUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "agency_url");
          final int _cursorIndexOfAgencyTimezone = CursorUtil.getColumnIndexOrThrow(_cursor, "agency_timezone");
          final int _cursorIndexOfAgencyPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "agency_phone");
          final int _cursorIndexOfAgencyLang = CursorUtil.getColumnIndexOrThrow(_cursor, "agency_lang");
          final List<AgencyEntity> _result = new ArrayList<AgencyEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AgencyEntity _item;
            final String _tmpAgencyId;
            _tmpAgencyId = _cursor.getString(_cursorIndexOfAgencyId);
            final String _tmpAgencyName;
            if (_cursor.isNull(_cursorIndexOfAgencyName)) {
              _tmpAgencyName = null;
            } else {
              _tmpAgencyName = _cursor.getString(_cursorIndexOfAgencyName);
            }
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            final String _tmpAgencyUrl;
            if (_cursor.isNull(_cursorIndexOfAgencyUrl)) {
              _tmpAgencyUrl = null;
            } else {
              _tmpAgencyUrl = _cursor.getString(_cursorIndexOfAgencyUrl);
            }
            final String _tmpAgencyTimezone;
            if (_cursor.isNull(_cursorIndexOfAgencyTimezone)) {
              _tmpAgencyTimezone = null;
            } else {
              _tmpAgencyTimezone = _cursor.getString(_cursorIndexOfAgencyTimezone);
            }
            final String _tmpAgencyPhone;
            if (_cursor.isNull(_cursorIndexOfAgencyPhone)) {
              _tmpAgencyPhone = null;
            } else {
              _tmpAgencyPhone = _cursor.getString(_cursorIndexOfAgencyPhone);
            }
            final String _tmpAgencyLang;
            if (_cursor.isNull(_cursorIndexOfAgencyLang)) {
              _tmpAgencyLang = null;
            } else {
              _tmpAgencyLang = _cursor.getString(_cursorIndexOfAgencyLang);
            }
            _item = new AgencyEntity(_tmpAgencyId,_tmpAgencyName,_tmpIsEnabled,_tmpAgencyUrl,_tmpAgencyTimezone,_tmpAgencyPhone,_tmpAgencyLang);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getAllCalendar(final Continuation<? super List<CalendarEntity>> $completion) {
    final String _sql = "SELECT * FROM calendar";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CalendarEntity>>() {
      @Override
      @NonNull
      public List<CalendarEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfServiceId = CursorUtil.getColumnIndexOrThrow(_cursor, "service_id");
          final int _cursorIndexOfMonday = CursorUtil.getColumnIndexOrThrow(_cursor, "monday");
          final int _cursorIndexOfTuesday = CursorUtil.getColumnIndexOrThrow(_cursor, "tuesday");
          final int _cursorIndexOfWednesday = CursorUtil.getColumnIndexOrThrow(_cursor, "wednesday");
          final int _cursorIndexOfThursday = CursorUtil.getColumnIndexOrThrow(_cursor, "thursday");
          final int _cursorIndexOfFriday = CursorUtil.getColumnIndexOrThrow(_cursor, "friday");
          final int _cursorIndexOfSaturday = CursorUtil.getColumnIndexOrThrow(_cursor, "saturday");
          final int _cursorIndexOfSunday = CursorUtil.getColumnIndexOrThrow(_cursor, "sunday");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "start_date");
          final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "end_date");
          final List<CalendarEntity> _result = new ArrayList<CalendarEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CalendarEntity _item;
            final String _tmpServiceId;
            _tmpServiceId = _cursor.getString(_cursorIndexOfServiceId);
            final Integer _tmpMonday;
            if (_cursor.isNull(_cursorIndexOfMonday)) {
              _tmpMonday = null;
            } else {
              _tmpMonday = _cursor.getInt(_cursorIndexOfMonday);
            }
            final Integer _tmpTuesday;
            if (_cursor.isNull(_cursorIndexOfTuesday)) {
              _tmpTuesday = null;
            } else {
              _tmpTuesday = _cursor.getInt(_cursorIndexOfTuesday);
            }
            final Integer _tmpWednesday;
            if (_cursor.isNull(_cursorIndexOfWednesday)) {
              _tmpWednesday = null;
            } else {
              _tmpWednesday = _cursor.getInt(_cursorIndexOfWednesday);
            }
            final Integer _tmpThursday;
            if (_cursor.isNull(_cursorIndexOfThursday)) {
              _tmpThursday = null;
            } else {
              _tmpThursday = _cursor.getInt(_cursorIndexOfThursday);
            }
            final Integer _tmpFriday;
            if (_cursor.isNull(_cursorIndexOfFriday)) {
              _tmpFriday = null;
            } else {
              _tmpFriday = _cursor.getInt(_cursorIndexOfFriday);
            }
            final Integer _tmpSaturday;
            if (_cursor.isNull(_cursorIndexOfSaturday)) {
              _tmpSaturday = null;
            } else {
              _tmpSaturday = _cursor.getInt(_cursorIndexOfSaturday);
            }
            final Integer _tmpSunday;
            if (_cursor.isNull(_cursorIndexOfSunday)) {
              _tmpSunday = null;
            } else {
              _tmpSunday = _cursor.getInt(_cursorIndexOfSunday);
            }
            final String _tmpStartDate;
            if (_cursor.isNull(_cursorIndexOfStartDate)) {
              _tmpStartDate = null;
            } else {
              _tmpStartDate = _cursor.getString(_cursorIndexOfStartDate);
            }
            final String _tmpEndDate;
            if (_cursor.isNull(_cursorIndexOfEndDate)) {
              _tmpEndDate = null;
            } else {
              _tmpEndDate = _cursor.getString(_cursorIndexOfEndDate);
            }
            _item = new CalendarEntity(_tmpServiceId,_tmpMonday,_tmpTuesday,_tmpWednesday,_tmpThursday,_tmpFriday,_tmpSaturday,_tmpSunday,_tmpStartDate,_tmpEndDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getCalendarDateRange(final Continuation<? super DateRange> $completion) {
    final String _sql = "SELECT MIN(start_date) as minDate, MAX(end_date) as maxDate FROM calendar";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DateRange>() {
      @Override
      @Nullable
      public DateRange call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMinDate = 0;
          final int _cursorIndexOfMaxDate = 1;
          final DateRange _result;
          if (_cursor.moveToFirst()) {
            final String _tmpMinDate;
            if (_cursor.isNull(_cursorIndexOfMinDate)) {
              _tmpMinDate = null;
            } else {
              _tmpMinDate = _cursor.getString(_cursorIndexOfMinDate);
            }
            final String _tmpMaxDate;
            if (_cursor.isNull(_cursorIndexOfMaxDate)) {
              _tmpMaxDate = null;
            } else {
              _tmpMaxDate = _cursor.getString(_cursorIndexOfMaxDate);
            }
            _result = new DateRange(_tmpMinDate,_tmpMaxDate);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTripCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT count(*) FROM trips";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<RouteTypeEntity>> getAvailableRouteTypes() {
    final String _sql = "SELECT * FROM route_types ORDER BY route_type2_name";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"route_types"}, new Callable<List<RouteTypeEntity>>() {
      @Override
      @NonNull
      public List<RouteTypeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfRouteType2Id = CursorUtil.getColumnIndexOrThrow(_cursor, "route_type2_id");
          final int _cursorIndexOfRouteType2Name = CursorUtil.getColumnIndexOrThrow(_cursor, "route_type2_name");
          final List<RouteTypeEntity> _result = new ArrayList<RouteTypeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RouteTypeEntity _item;
            final String _tmpRouteType2Id;
            _tmpRouteType2Id = _cursor.getString(_cursorIndexOfRouteType2Id);
            final String _tmpRouteType2Name;
            if (_cursor.isNull(_cursorIndexOfRouteType2Name)) {
              _tmpRouteType2Name = null;
            } else {
              _tmpRouteType2Name = _cursor.getString(_cursorIndexOfRouteType2Name);
            }
            _item = new RouteTypeEntity(_tmpRouteType2Id,_tmpRouteType2Name);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<RouteEntity>> getRoutesForSelectedAgencies() {
    final String _sql = "\n"
            + "    SELECT * FROM routes \n"
            + "    WHERE agency_id IN (SELECT agency_id FROM agency WHERE isEnabled = 1)\n"
            + "    ORDER BY route_short_name\n";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"routes",
        "agency"}, new Callable<List<RouteEntity>>() {
      @Override
      @NonNull
      public List<RouteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfRouteId = CursorUtil.getColumnIndexOrThrow(_cursor, "route_id");
          final int _cursorIndexOfAgencyId = CursorUtil.getColumnIndexOrThrow(_cursor, "agency_id");
          final int _cursorIndexOfRouteShortName = CursorUtil.getColumnIndexOrThrow(_cursor, "route_short_name");
          final int _cursorIndexOfRouteLongName = CursorUtil.getColumnIndexOrThrow(_cursor, "route_long_name");
          final int _cursorIndexOfRouteDesc = CursorUtil.getColumnIndexOrThrow(_cursor, "route_desc");
          final int _cursorIndexOfRouteType = CursorUtil.getColumnIndexOrThrow(_cursor, "route_type");
          final int _cursorIndexOfRouteType2Id = CursorUtil.getColumnIndexOrThrow(_cursor, "route_type2_id");
          final int _cursorIndexOfValidFrom = CursorUtil.getColumnIndexOrThrow(_cursor, "valid_from");
          final int _cursorIndexOfValidUntil = CursorUtil.getColumnIndexOrThrow(_cursor, "valid_until");
          final List<RouteEntity> _result = new ArrayList<RouteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RouteEntity _item;
            final String _tmpRouteId;
            _tmpRouteId = _cursor.getString(_cursorIndexOfRouteId);
            final String _tmpAgencyId;
            if (_cursor.isNull(_cursorIndexOfAgencyId)) {
              _tmpAgencyId = null;
            } else {
              _tmpAgencyId = _cursor.getString(_cursorIndexOfAgencyId);
            }
            final String _tmpRouteShortName;
            if (_cursor.isNull(_cursorIndexOfRouteShortName)) {
              _tmpRouteShortName = null;
            } else {
              _tmpRouteShortName = _cursor.getString(_cursorIndexOfRouteShortName);
            }
            final String _tmpRouteLongName;
            if (_cursor.isNull(_cursorIndexOfRouteLongName)) {
              _tmpRouteLongName = null;
            } else {
              _tmpRouteLongName = _cursor.getString(_cursorIndexOfRouteLongName);
            }
            final String _tmpRouteDesc;
            if (_cursor.isNull(_cursorIndexOfRouteDesc)) {
              _tmpRouteDesc = null;
            } else {
              _tmpRouteDesc = _cursor.getString(_cursorIndexOfRouteDesc);
            }
            final Integer _tmpRouteType;
            if (_cursor.isNull(_cursorIndexOfRouteType)) {
              _tmpRouteType = null;
            } else {
              _tmpRouteType = _cursor.getInt(_cursorIndexOfRouteType);
            }
            final String _tmpRouteType2Id;
            if (_cursor.isNull(_cursorIndexOfRouteType2Id)) {
              _tmpRouteType2Id = null;
            } else {
              _tmpRouteType2Id = _cursor.getString(_cursorIndexOfRouteType2Id);
            }
            final String _tmpValidFrom;
            if (_cursor.isNull(_cursorIndexOfValidFrom)) {
              _tmpValidFrom = null;
            } else {
              _tmpValidFrom = _cursor.getString(_cursorIndexOfValidFrom);
            }
            final String _tmpValidUntil;
            if (_cursor.isNull(_cursorIndexOfValidUntil)) {
              _tmpValidUntil = null;
            } else {
              _tmpValidUntil = _cursor.getString(_cursorIndexOfValidUntil);
            }
            _item = new RouteEntity(_tmpRouteId,_tmpAgencyId,_tmpRouteShortName,_tmpRouteLongName,_tmpRouteDesc,_tmpRouteType,_tmpRouteType2Id,_tmpValidFrom,_tmpValidUntil);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<DirectionItem>> getDirectionsForRoute(final String routeId) {
    final String _sql = "\n"
            + "    SELECT DISTINCT direction_id AS directionId, trip_headsign AS headsign\n"
            + "    FROM trips\n"
            + "    WHERE route_id = ? AND direction_id IS NOT NULL\n"
            + "    ORDER BY directionId, headsign\n";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, routeId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"trips"}, new Callable<List<DirectionItem>>() {
      @Override
      @NonNull
      public List<DirectionItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDirectionId = 0;
          final int _cursorIndexOfHeadsign = 1;
          final List<DirectionItem> _result = new ArrayList<DirectionItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DirectionItem _item;
            final Integer _tmpDirectionId;
            if (_cursor.isNull(_cursorIndexOfDirectionId)) {
              _tmpDirectionId = null;
            } else {
              _tmpDirectionId = _cursor.getInt(_cursorIndexOfDirectionId);
            }
            final String _tmpHeadsign;
            if (_cursor.isNull(_cursorIndexOfHeadsign)) {
              _tmpHeadsign = null;
            } else {
              _tmpHeadsign = _cursor.getString(_cursorIndexOfHeadsign);
            }
            _item = new DirectionItem(_tmpDirectionId,_tmpHeadsign);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<AgencyEntity>> getSelectedAgenciesFlow() {
    final String _sql = "SELECT * FROM agency WHERE isEnabled = 1 ORDER BY agency_name";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"agency"}, new Callable<List<AgencyEntity>>() {
      @Override
      @NonNull
      public List<AgencyEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfAgencyId = CursorUtil.getColumnIndexOrThrow(_cursor, "agency_id");
          final int _cursorIndexOfAgencyName = CursorUtil.getColumnIndexOrThrow(_cursor, "agency_name");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfAgencyUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "agency_url");
          final int _cursorIndexOfAgencyTimezone = CursorUtil.getColumnIndexOrThrow(_cursor, "agency_timezone");
          final int _cursorIndexOfAgencyPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "agency_phone");
          final int _cursorIndexOfAgencyLang = CursorUtil.getColumnIndexOrThrow(_cursor, "agency_lang");
          final List<AgencyEntity> _result = new ArrayList<AgencyEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AgencyEntity _item;
            final String _tmpAgencyId;
            _tmpAgencyId = _cursor.getString(_cursorIndexOfAgencyId);
            final String _tmpAgencyName;
            if (_cursor.isNull(_cursorIndexOfAgencyName)) {
              _tmpAgencyName = null;
            } else {
              _tmpAgencyName = _cursor.getString(_cursorIndexOfAgencyName);
            }
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            final String _tmpAgencyUrl;
            if (_cursor.isNull(_cursorIndexOfAgencyUrl)) {
              _tmpAgencyUrl = null;
            } else {
              _tmpAgencyUrl = _cursor.getString(_cursorIndexOfAgencyUrl);
            }
            final String _tmpAgencyTimezone;
            if (_cursor.isNull(_cursorIndexOfAgencyTimezone)) {
              _tmpAgencyTimezone = null;
            } else {
              _tmpAgencyTimezone = _cursor.getString(_cursorIndexOfAgencyTimezone);
            }
            final String _tmpAgencyPhone;
            if (_cursor.isNull(_cursorIndexOfAgencyPhone)) {
              _tmpAgencyPhone = null;
            } else {
              _tmpAgencyPhone = _cursor.getString(_cursorIndexOfAgencyPhone);
            }
            final String _tmpAgencyLang;
            if (_cursor.isNull(_cursorIndexOfAgencyLang)) {
              _tmpAgencyLang = null;
            } else {
              _tmpAgencyLang = _cursor.getString(_cursorIndexOfAgencyLang);
            }
            _item = new AgencyEntity(_tmpAgencyId,_tmpAgencyName,_tmpIsEnabled,_tmpAgencyUrl,_tmpAgencyTimezone,_tmpAgencyPhone,_tmpAgencyLang);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<RouteEntity>> getRoutesForSelectedAgenciesFlow(final String routeType2Id) {
    final String _sql = "\n"
            + "    SELECT * FROM routes\n"
            + "    WHERE (? IS NULL OR route_type2_id = ?)\n"
            + "      AND agency_id IN (SELECT agency_id FROM agency WHERE isEnabled = 1)\n"
            + "    ORDER BY route_short_name\n";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (routeType2Id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, routeType2Id);
    }
    _argIndex = 2;
    if (routeType2Id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, routeType2Id);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"routes",
        "agency"}, new Callable<List<RouteEntity>>() {
      @Override
      @NonNull
      public List<RouteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfRouteId = CursorUtil.getColumnIndexOrThrow(_cursor, "route_id");
          final int _cursorIndexOfAgencyId = CursorUtil.getColumnIndexOrThrow(_cursor, "agency_id");
          final int _cursorIndexOfRouteShortName = CursorUtil.getColumnIndexOrThrow(_cursor, "route_short_name");
          final int _cursorIndexOfRouteLongName = CursorUtil.getColumnIndexOrThrow(_cursor, "route_long_name");
          final int _cursorIndexOfRouteDesc = CursorUtil.getColumnIndexOrThrow(_cursor, "route_desc");
          final int _cursorIndexOfRouteType = CursorUtil.getColumnIndexOrThrow(_cursor, "route_type");
          final int _cursorIndexOfRouteType2Id = CursorUtil.getColumnIndexOrThrow(_cursor, "route_type2_id");
          final int _cursorIndexOfValidFrom = CursorUtil.getColumnIndexOrThrow(_cursor, "valid_from");
          final int _cursorIndexOfValidUntil = CursorUtil.getColumnIndexOrThrow(_cursor, "valid_until");
          final List<RouteEntity> _result = new ArrayList<RouteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RouteEntity _item;
            final String _tmpRouteId;
            _tmpRouteId = _cursor.getString(_cursorIndexOfRouteId);
            final String _tmpAgencyId;
            if (_cursor.isNull(_cursorIndexOfAgencyId)) {
              _tmpAgencyId = null;
            } else {
              _tmpAgencyId = _cursor.getString(_cursorIndexOfAgencyId);
            }
            final String _tmpRouteShortName;
            if (_cursor.isNull(_cursorIndexOfRouteShortName)) {
              _tmpRouteShortName = null;
            } else {
              _tmpRouteShortName = _cursor.getString(_cursorIndexOfRouteShortName);
            }
            final String _tmpRouteLongName;
            if (_cursor.isNull(_cursorIndexOfRouteLongName)) {
              _tmpRouteLongName = null;
            } else {
              _tmpRouteLongName = _cursor.getString(_cursorIndexOfRouteLongName);
            }
            final String _tmpRouteDesc;
            if (_cursor.isNull(_cursorIndexOfRouteDesc)) {
              _tmpRouteDesc = null;
            } else {
              _tmpRouteDesc = _cursor.getString(_cursorIndexOfRouteDesc);
            }
            final Integer _tmpRouteType;
            if (_cursor.isNull(_cursorIndexOfRouteType)) {
              _tmpRouteType = null;
            } else {
              _tmpRouteType = _cursor.getInt(_cursorIndexOfRouteType);
            }
            final String _tmpRouteType2Id;
            if (_cursor.isNull(_cursorIndexOfRouteType2Id)) {
              _tmpRouteType2Id = null;
            } else {
              _tmpRouteType2Id = _cursor.getString(_cursorIndexOfRouteType2Id);
            }
            final String _tmpValidFrom;
            if (_cursor.isNull(_cursorIndexOfValidFrom)) {
              _tmpValidFrom = null;
            } else {
              _tmpValidFrom = _cursor.getString(_cursorIndexOfValidFrom);
            }
            final String _tmpValidUntil;
            if (_cursor.isNull(_cursorIndexOfValidUntil)) {
              _tmpValidUntil = null;
            } else {
              _tmpValidUntil = _cursor.getString(_cursorIndexOfValidUntil);
            }
            _item = new RouteEntity(_tmpRouteId,_tmpAgencyId,_tmpRouteShortName,_tmpRouteLongName,_tmpRouteDesc,_tmpRouteType,_tmpRouteType2Id,_tmpValidFrom,_tmpValidUntil);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<DirectionItem>> getDirectionsForRouteFlow(final String routeId) {
    final String _sql = "\n"
            + "    SELECT DISTINCT direction_id AS directionId, trip_headsign AS headsign\n"
            + "    FROM trips\n"
            + "    WHERE route_id = ?\n"
            + "    ORDER BY directionId, headsign\n";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, routeId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"trips"}, new Callable<List<DirectionItem>>() {
      @Override
      @NonNull
      public List<DirectionItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDirectionId = 0;
          final int _cursorIndexOfHeadsign = 1;
          final List<DirectionItem> _result = new ArrayList<DirectionItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DirectionItem _item;
            final Integer _tmpDirectionId;
            if (_cursor.isNull(_cursorIndexOfDirectionId)) {
              _tmpDirectionId = null;
            } else {
              _tmpDirectionId = _cursor.getInt(_cursorIndexOfDirectionId);
            }
            final String _tmpHeadsign;
            if (_cursor.isNull(_cursorIndexOfHeadsign)) {
              _tmpHeadsign = null;
            } else {
              _tmpHeadsign = _cursor.getString(_cursorIndexOfHeadsign);
            }
            _item = new DirectionItem(_tmpDirectionId,_tmpHeadsign);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<String>> getVariantsForRouteAndDirectionFlow(final String routeId,
      final Integer directionId) {
    final String _sql = "\n"
            + "    SELECT DISTINCT variant_id FROM trips\n"
            + "    WHERE route_id = ?\n"
            + "      AND (? IS NULL OR direction_id = ?)\n"
            + "    ORDER BY variant_id\n";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindString(_argIndex, routeId);
    _argIndex = 2;
    if (directionId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, directionId);
    }
    _argIndex = 3;
    if (directionId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, directionId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"trips"}, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            if (_cursor.isNull(0)) {
              _item = null;
            } else {
              _item = _cursor.getString(0);
            }
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<String>> getBrigadesFlow(final String routeId, final Integer directionId,
      final String variantId) {
    final String _sql = "\n"
            + "    SELECT DISTINCT brigade_id FROM trips\n"
            + "    WHERE route_id = ?\n"
            + "      AND (? IS NULL OR direction_id = ?)\n"
            + "      AND (? IS NULL OR variant_id = ?)\n"
            + "    ORDER BY brigade_id\n";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 5);
    int _argIndex = 1;
    _statement.bindString(_argIndex, routeId);
    _argIndex = 2;
    if (directionId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, directionId);
    }
    _argIndex = 3;
    if (directionId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, directionId);
    }
    _argIndex = 4;
    if (variantId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, variantId);
    }
    _argIndex = 5;
    if (variantId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, variantId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"trips"}, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            if (_cursor.isNull(0)) {
              _item = null;
            } else {
              _item = _cursor.getString(0);
            }
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<VehicleItem>> getVehiclesFlow(final String routeId, final Integer directionId,
      final String variantId, final String brigadeId) {
    final String _sql = "\n"
            + "    SELECT DISTINCT t.vehicle_id, vt.vehicle_type_name\n"
            + "    FROM trips t\n"
            + "    LEFT JOIN vehicle_types vt ON t.vehicle_id = vt.vehicle_type_id\n"
            + "    WHERE route_id = ?\n"
            + "      AND (? IS NULL OR direction_id = ?)\n"
            + "      AND (? IS NULL OR variant_id = ?)\n"
            + "      AND (? IS NULL OR brigade_id = ?)\n"
            + "    ORDER BY t.vehicle_id\n";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 7);
    int _argIndex = 1;
    _statement.bindString(_argIndex, routeId);
    _argIndex = 2;
    if (directionId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, directionId);
    }
    _argIndex = 3;
    if (directionId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, directionId);
    }
    _argIndex = 4;
    if (variantId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, variantId);
    }
    _argIndex = 5;
    if (variantId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, variantId);
    }
    _argIndex = 6;
    if (brigadeId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, brigadeId);
    }
    _argIndex = 7;
    if (brigadeId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, brigadeId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"trips",
        "vehicle_types"}, new Callable<List<VehicleItem>>() {
      @Override
      @NonNull
      public List<VehicleItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfVehicleId = 0;
          final int _cursorIndexOfVehicleTypeName = 1;
          final List<VehicleItem> _result = new ArrayList<VehicleItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VehicleItem _item;
            final String _tmpVehicleId;
            if (_cursor.isNull(_cursorIndexOfVehicleId)) {
              _tmpVehicleId = null;
            } else {
              _tmpVehicleId = _cursor.getString(_cursorIndexOfVehicleId);
            }
            final String _tmpVehicleTypeName;
            if (_cursor.isNull(_cursorIndexOfVehicleTypeName)) {
              _tmpVehicleTypeName = null;
            } else {
              _tmpVehicleTypeName = _cursor.getString(_cursorIndexOfVehicleTypeName);
            }
            _item = new VehicleItem(_tmpVehicleId,_tmpVehicleTypeName);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getTripsWithDetailsFiltered(final String routeId, final Integer directionId,
      final String variantId, final String brigadeId, final String vehicleId,
      final String serviceId, final Continuation<? super List<TripWithDetails>> $completion) {
    final String _sql = "\n"
            + "        SELECT DISTINCT\n"
            + "            t.*, \n"
            + "            (SELECT substr(MIN(departure_time), 1, 5) FROM stop_times WHERE trip_id = t.trip_id) AS startTime,\n"
            + "            (SELECT substr(MAX(arrival_time), 1, 5) FROM stop_times WHERE trip_id = t.trip_id) AS endTime,\n"
            + "            vt.vehicle_type_name AS vehicleTypeName,\n"
            + "            v.is_main AS variantIsMain\n"
            + "        FROM trips AS t\n"
            + "        LEFT JOIN vehicle_types AS vt ON t.vehicle_id = vt.vehicle_type_id\n"
            + "        LEFT JOIN variants AS v ON t.variant_id = v.variant_id\n"
            + "        WHERE (? IS NULL OR t.route_id = ?)\n"
            + "          AND (? IS NULL OR t.direction_id = ?)\n"
            + "          AND (? IS NULL OR t.variant_id = ?)\n"
            + "          AND (? IS NULL OR t.brigade_id = ?)\n"
            + "          AND (? IS NULL OR t.vehicle_id = ?)\n"
            + "          AND (? IS NULL OR t.service_id = ?)\n"
            + "        ORDER BY startTime, t.trip_id\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 12);
    int _argIndex = 1;
    if (routeId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, routeId);
    }
    _argIndex = 2;
    if (routeId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, routeId);
    }
    _argIndex = 3;
    if (directionId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, directionId);
    }
    _argIndex = 4;
    if (directionId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, directionId);
    }
    _argIndex = 5;
    if (variantId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, variantId);
    }
    _argIndex = 6;
    if (variantId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, variantId);
    }
    _argIndex = 7;
    if (brigadeId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, brigadeId);
    }
    _argIndex = 8;
    if (brigadeId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, brigadeId);
    }
    _argIndex = 9;
    if (vehicleId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, vehicleId);
    }
    _argIndex = 10;
    if (vehicleId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, vehicleId);
    }
    _argIndex = 11;
    if (serviceId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, serviceId);
    }
    _argIndex = 12;
    if (serviceId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, serviceId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TripWithDetails>>() {
      @Override
      @NonNull
      public List<TripWithDetails> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTripId = CursorUtil.getColumnIndexOrThrow(_cursor, "trip_id");
          final int _cursorIndexOfRouteId = CursorUtil.getColumnIndexOrThrow(_cursor, "route_id");
          final int _cursorIndexOfServiceId = CursorUtil.getColumnIndexOrThrow(_cursor, "service_id");
          final int _cursorIndexOfTripHeadsign = CursorUtil.getColumnIndexOrThrow(_cursor, "trip_headsign");
          final int _cursorIndexOfDirectionId = CursorUtil.getColumnIndexOrThrow(_cursor, "direction_id");
          final int _cursorIndexOfShapeId = CursorUtil.getColumnIndexOrThrow(_cursor, "shape_id");
          final int _cursorIndexOfBrigadeId = CursorUtil.getColumnIndexOrThrow(_cursor, "brigade_id");
          final int _cursorIndexOfVehicleId = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicle_id");
          final int _cursorIndexOfVariantId = CursorUtil.getColumnIndexOrThrow(_cursor, "variant_id");
          final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
          final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
          final int _cursorIndexOfVehicleTypeName = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicleTypeName");
          final int _cursorIndexOfVariantIsMain = CursorUtil.getColumnIndexOrThrow(_cursor, "variantIsMain");
          final List<TripWithDetails> _result = new ArrayList<TripWithDetails>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TripWithDetails _item;
            final String _tmpStartTime;
            if (_cursor.isNull(_cursorIndexOfStartTime)) {
              _tmpStartTime = null;
            } else {
              _tmpStartTime = _cursor.getString(_cursorIndexOfStartTime);
            }
            final String _tmpEndTime;
            if (_cursor.isNull(_cursorIndexOfEndTime)) {
              _tmpEndTime = null;
            } else {
              _tmpEndTime = _cursor.getString(_cursorIndexOfEndTime);
            }
            final String _tmpVehicleTypeName;
            if (_cursor.isNull(_cursorIndexOfVehicleTypeName)) {
              _tmpVehicleTypeName = null;
            } else {
              _tmpVehicleTypeName = _cursor.getString(_cursorIndexOfVehicleTypeName);
            }
            final Integer _tmpVariantIsMain;
            if (_cursor.isNull(_cursorIndexOfVariantIsMain)) {
              _tmpVariantIsMain = null;
            } else {
              _tmpVariantIsMain = _cursor.getInt(_cursorIndexOfVariantIsMain);
            }
            final TripEntity _tmpTrip;
            final String _tmpTripId;
            _tmpTripId = _cursor.getString(_cursorIndexOfTripId);
            final String _tmpRouteId;
            if (_cursor.isNull(_cursorIndexOfRouteId)) {
              _tmpRouteId = null;
            } else {
              _tmpRouteId = _cursor.getString(_cursorIndexOfRouteId);
            }
            final String _tmpServiceId;
            if (_cursor.isNull(_cursorIndexOfServiceId)) {
              _tmpServiceId = null;
            } else {
              _tmpServiceId = _cursor.getString(_cursorIndexOfServiceId);
            }
            final String _tmpTripHeadsign;
            if (_cursor.isNull(_cursorIndexOfTripHeadsign)) {
              _tmpTripHeadsign = null;
            } else {
              _tmpTripHeadsign = _cursor.getString(_cursorIndexOfTripHeadsign);
            }
            final Integer _tmpDirectionId;
            if (_cursor.isNull(_cursorIndexOfDirectionId)) {
              _tmpDirectionId = null;
            } else {
              _tmpDirectionId = _cursor.getInt(_cursorIndexOfDirectionId);
            }
            final String _tmpShapeId;
            if (_cursor.isNull(_cursorIndexOfShapeId)) {
              _tmpShapeId = null;
            } else {
              _tmpShapeId = _cursor.getString(_cursorIndexOfShapeId);
            }
            final String _tmpBrigadeId;
            if (_cursor.isNull(_cursorIndexOfBrigadeId)) {
              _tmpBrigadeId = null;
            } else {
              _tmpBrigadeId = _cursor.getString(_cursorIndexOfBrigadeId);
            }
            final String _tmpVehicleId;
            if (_cursor.isNull(_cursorIndexOfVehicleId)) {
              _tmpVehicleId = null;
            } else {
              _tmpVehicleId = _cursor.getString(_cursorIndexOfVehicleId);
            }
            final String _tmpVariantId;
            if (_cursor.isNull(_cursorIndexOfVariantId)) {
              _tmpVariantId = null;
            } else {
              _tmpVariantId = _cursor.getString(_cursorIndexOfVariantId);
            }
            _tmpTrip = new TripEntity(_tmpTripId,_tmpRouteId,_tmpServiceId,_tmpTripHeadsign,_tmpDirectionId,_tmpShapeId,_tmpBrigadeId,_tmpVehicleId,_tmpVariantId);
            _item = new TripWithDetails(_tmpTrip,_tmpStartTime,_tmpEndTime,_tmpVehicleTypeName,_tmpVariantIsMain);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getStopsWithTimeForTrip(final String tripId,
      final Continuation<? super List<StopWithTime>> $completion) {
    final String _sql = "\n"
            + "        SELECT s.*, substr(st.arrival_time, 1, 5) AS arrivalTime, st.pickup_type AS pickupType\n"
            + "        FROM stop_times st\n"
            + "        JOIN stops s ON st.stop_id = s.stop_id\n"
            + "        WHERE st.trip_id = ?\n"
            + "        ORDER BY st.stop_sequence\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, tripId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<StopWithTime>>() {
      @Override
      @NonNull
      public List<StopWithTime> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfStopId = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_id");
          final int _cursorIndexOfStopCode = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_code");
          final int _cursorIndexOfStopName = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_name");
          final int _cursorIndexOfStopLat = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_lat");
          final int _cursorIndexOfStopLon = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_lon");
          final int _cursorIndexOfLocationType = CursorUtil.getColumnIndexOrThrow(_cursor, "location_type");
          final int _cursorIndexOfParentStation = CursorUtil.getColumnIndexOrThrow(_cursor, "parent_station");
          final int _cursorIndexOfArrivalTime = CursorUtil.getColumnIndexOrThrow(_cursor, "arrivalTime");
          final int _cursorIndexOfPickupType = CursorUtil.getColumnIndexOrThrow(_cursor, "pickupType");
          final List<StopWithTime> _result = new ArrayList<StopWithTime>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StopWithTime _item;
            final String _tmpArrivalTime;
            if (_cursor.isNull(_cursorIndexOfArrivalTime)) {
              _tmpArrivalTime = null;
            } else {
              _tmpArrivalTime = _cursor.getString(_cursorIndexOfArrivalTime);
            }
            final Integer _tmpPickupType;
            if (_cursor.isNull(_cursorIndexOfPickupType)) {
              _tmpPickupType = null;
            } else {
              _tmpPickupType = _cursor.getInt(_cursorIndexOfPickupType);
            }
            final StopEntity _tmpStop;
            final String _tmpStopId;
            _tmpStopId = _cursor.getString(_cursorIndexOfStopId);
            final String _tmpStopCode;
            if (_cursor.isNull(_cursorIndexOfStopCode)) {
              _tmpStopCode = null;
            } else {
              _tmpStopCode = _cursor.getString(_cursorIndexOfStopCode);
            }
            final String _tmpStopName;
            if (_cursor.isNull(_cursorIndexOfStopName)) {
              _tmpStopName = null;
            } else {
              _tmpStopName = _cursor.getString(_cursorIndexOfStopName);
            }
            final Double _tmpStopLat;
            if (_cursor.isNull(_cursorIndexOfStopLat)) {
              _tmpStopLat = null;
            } else {
              _tmpStopLat = _cursor.getDouble(_cursorIndexOfStopLat);
            }
            final Double _tmpStopLon;
            if (_cursor.isNull(_cursorIndexOfStopLon)) {
              _tmpStopLon = null;
            } else {
              _tmpStopLon = _cursor.getDouble(_cursorIndexOfStopLon);
            }
            final Integer _tmpLocationType;
            if (_cursor.isNull(_cursorIndexOfLocationType)) {
              _tmpLocationType = null;
            } else {
              _tmpLocationType = _cursor.getInt(_cursorIndexOfLocationType);
            }
            final String _tmpParentStation;
            if (_cursor.isNull(_cursorIndexOfParentStation)) {
              _tmpParentStation = null;
            } else {
              _tmpParentStation = _cursor.getString(_cursorIndexOfParentStation);
            }
            _tmpStop = new StopEntity(_tmpStopId,_tmpStopCode,_tmpStopName,_tmpStopLat,_tmpStopLon,_tmpLocationType,_tmpParentStation);
            _item = new StopWithTime(_tmpStop,_tmpArrivalTime,_tmpPickupType);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTripsFiltered(final String routeId, final Integer directionId,
      final String variantId, final String brigadeId, final String vehicleId,
      final String serviceId, final Continuation<? super List<TripEntity>> $completion) {
    final String _sql = "\n"
            + "    SELECT * FROM trips\n"
            + "    WHERE (? IS NULL OR route_id = ?)\n"
            + "      AND (? IS NULL OR direction_id = ?)\n"
            + "      AND (? IS NULL OR variant_id = ?)\n"
            + "      AND (? IS NULL OR brigade_id = ?)\n"
            + "      AND (? IS NULL OR vehicle_id = ?)\n"
            + "      AND (? IS NULL OR service_id = ?)\n"
            + "    ORDER BY trip_id\n";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 12);
    int _argIndex = 1;
    if (routeId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, routeId);
    }
    _argIndex = 2;
    if (routeId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, routeId);
    }
    _argIndex = 3;
    if (directionId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, directionId);
    }
    _argIndex = 4;
    if (directionId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, directionId);
    }
    _argIndex = 5;
    if (variantId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, variantId);
    }
    _argIndex = 6;
    if (variantId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, variantId);
    }
    _argIndex = 7;
    if (brigadeId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, brigadeId);
    }
    _argIndex = 8;
    if (brigadeId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, brigadeId);
    }
    _argIndex = 9;
    if (vehicleId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, vehicleId);
    }
    _argIndex = 10;
    if (vehicleId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, vehicleId);
    }
    _argIndex = 11;
    if (serviceId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, serviceId);
    }
    _argIndex = 12;
    if (serviceId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, serviceId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TripEntity>>() {
      @Override
      @NonNull
      public List<TripEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTripId = CursorUtil.getColumnIndexOrThrow(_cursor, "trip_id");
          final int _cursorIndexOfRouteId = CursorUtil.getColumnIndexOrThrow(_cursor, "route_id");
          final int _cursorIndexOfServiceId = CursorUtil.getColumnIndexOrThrow(_cursor, "service_id");
          final int _cursorIndexOfTripHeadsign = CursorUtil.getColumnIndexOrThrow(_cursor, "trip_headsign");
          final int _cursorIndexOfDirectionId = CursorUtil.getColumnIndexOrThrow(_cursor, "direction_id");
          final int _cursorIndexOfShapeId = CursorUtil.getColumnIndexOrThrow(_cursor, "shape_id");
          final int _cursorIndexOfBrigadeId = CursorUtil.getColumnIndexOrThrow(_cursor, "brigade_id");
          final int _cursorIndexOfVehicleId = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicle_id");
          final int _cursorIndexOfVariantId = CursorUtil.getColumnIndexOrThrow(_cursor, "variant_id");
          final List<TripEntity> _result = new ArrayList<TripEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TripEntity _item;
            final String _tmpTripId;
            _tmpTripId = _cursor.getString(_cursorIndexOfTripId);
            final String _tmpRouteId;
            if (_cursor.isNull(_cursorIndexOfRouteId)) {
              _tmpRouteId = null;
            } else {
              _tmpRouteId = _cursor.getString(_cursorIndexOfRouteId);
            }
            final String _tmpServiceId;
            if (_cursor.isNull(_cursorIndexOfServiceId)) {
              _tmpServiceId = null;
            } else {
              _tmpServiceId = _cursor.getString(_cursorIndexOfServiceId);
            }
            final String _tmpTripHeadsign;
            if (_cursor.isNull(_cursorIndexOfTripHeadsign)) {
              _tmpTripHeadsign = null;
            } else {
              _tmpTripHeadsign = _cursor.getString(_cursorIndexOfTripHeadsign);
            }
            final Integer _tmpDirectionId;
            if (_cursor.isNull(_cursorIndexOfDirectionId)) {
              _tmpDirectionId = null;
            } else {
              _tmpDirectionId = _cursor.getInt(_cursorIndexOfDirectionId);
            }
            final String _tmpShapeId;
            if (_cursor.isNull(_cursorIndexOfShapeId)) {
              _tmpShapeId = null;
            } else {
              _tmpShapeId = _cursor.getString(_cursorIndexOfShapeId);
            }
            final String _tmpBrigadeId;
            if (_cursor.isNull(_cursorIndexOfBrigadeId)) {
              _tmpBrigadeId = null;
            } else {
              _tmpBrigadeId = _cursor.getString(_cursorIndexOfBrigadeId);
            }
            final String _tmpVehicleId;
            if (_cursor.isNull(_cursorIndexOfVehicleId)) {
              _tmpVehicleId = null;
            } else {
              _tmpVehicleId = _cursor.getString(_cursorIndexOfVehicleId);
            }
            final String _tmpVariantId;
            if (_cursor.isNull(_cursorIndexOfVariantId)) {
              _tmpVariantId = null;
            } else {
              _tmpVariantId = _cursor.getString(_cursorIndexOfVariantId);
            }
            _item = new TripEntity(_tmpTripId,_tmpRouteId,_tmpServiceId,_tmpTripHeadsign,_tmpDirectionId,_tmpShapeId,_tmpBrigadeId,_tmpVehicleId,_tmpVariantId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
