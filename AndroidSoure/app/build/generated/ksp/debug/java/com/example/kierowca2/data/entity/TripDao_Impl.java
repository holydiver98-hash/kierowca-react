package com.example.kierowca2.data.entity;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
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

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TripDao_Impl implements TripDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TripEntity> __insertionAdapterOfTripEntity;

  private final SharedSQLiteStatement __preparedStmtOfClear;

  public TripDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
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
    this.__preparedStmtOfClear = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM trips";
        return _query;
      }
    };
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
  public Object clear(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClear.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClear.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getTrip(final String tripId, final Continuation<? super TripEntity> $completion) {
    final String _sql = "SELECT * FROM trips WHERE trip_id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, tripId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<TripEntity>() {
      @Override
      @Nullable
      public TripEntity call() throws Exception {
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
          final TripEntity _result;
          if (_cursor.moveToFirst()) {
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
            _result = new TripEntity(_tmpTripId,_tmpRouteId,_tmpServiceId,_tmpTripHeadsign,_tmpDirectionId,_tmpShapeId,_tmpBrigadeId,_tmpVehicleId,_tmpVariantId);
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
  public Object getTripsForRoute(final String routeId,
      final Continuation<? super List<TripEntity>> $completion) {
    final String _sql = "SELECT * FROM trips WHERE route_id = ? ORDER BY trip_headsign";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, routeId);
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

  @Override
  public Object getHeadsignsForRoute(final String routeId,
      final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT DISTINCT trip_headsign FROM trips WHERE route_id = ? ORDER BY trip_headsign";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, routeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            _item = _cursor.getString(0);
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
  public Object getTripsForRouteAndDirection(final String routeId, final String headsign,
      final Continuation<? super List<TripEntity>> $completion) {
    final String _sql = "SELECT * FROM trips WHERE route_id = ? AND trip_headsign = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, routeId);
    _argIndex = 2;
    _statement.bindString(_argIndex, headsign);
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

  @Override
  public Object getTripsByRouteAndDirection(final String routeId, final int direction,
      final Continuation<? super List<TripEntity>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM trips \n"
            + "        WHERE route_id = ? \n"
            + "          AND direction_id = ?\n"
            + "        ORDER BY trip_id\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, routeId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, direction);
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

  @Override
  public Object getTripsFiltered(final String routeId, final String variantId,
      final String brigadeId, final String vehicleId,
      final Continuation<? super List<TripEntity>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM trips\n"
            + "        WHERE (? IS NULL OR route_id = ?)\n"
            + "          AND (? IS NULL OR variant_id = ?)\n"
            + "          AND (? IS NULL OR brigade_id = ?)\n"
            + "          AND (? IS NULL OR vehicle_id = ?)\n"
            + "        ORDER BY trip_headsign\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 8);
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
    if (variantId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, variantId);
    }
    _argIndex = 4;
    if (variantId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, variantId);
    }
    _argIndex = 5;
    if (brigadeId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, brigadeId);
    }
    _argIndex = 6;
    if (brigadeId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, brigadeId);
    }
    _argIndex = 7;
    if (vehicleId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, vehicleId);
    }
    _argIndex = 8;
    if (vehicleId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, vehicleId);
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
