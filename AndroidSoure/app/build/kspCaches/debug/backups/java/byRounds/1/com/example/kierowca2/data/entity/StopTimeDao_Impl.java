package com.example.kierowca2.data.entity;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
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

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class StopTimeDao_Impl implements StopTimeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<StopTimeEntity> __insertionAdapterOfStopTimeEntity;

  private final SharedSQLiteStatement __preparedStmtOfClear;

  public StopTimeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
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
    this.__preparedStmtOfClear = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM stop_times";
        return _query;
      }
    };
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
  public Object getOrderedStopsForTrip(final String tripId,
      final Continuation<? super List<StopEntity>> $completion) {
    final String _sql = "SELECT s.* FROM stops s INNER JOIN stop_times st ON s.stop_id = st.stop_id WHERE st.trip_id = ? ORDER BY st.stop_sequence ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, tripId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<StopEntity>>() {
      @Override
      @NonNull
      public List<StopEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfStopId = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_id");
          final int _cursorIndexOfStopCode = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_code");
          final int _cursorIndexOfStopName = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_name");
          final int _cursorIndexOfStopLat = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_lat");
          final int _cursorIndexOfStopLon = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_lon");
          final int _cursorIndexOfLocationType = CursorUtil.getColumnIndexOrThrow(_cursor, "location_type");
          final int _cursorIndexOfParentStation = CursorUtil.getColumnIndexOrThrow(_cursor, "parent_station");
          final List<StopEntity> _result = new ArrayList<StopEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StopEntity _item;
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
            _item = new StopEntity(_tmpStopId,_tmpStopCode,_tmpStopName,_tmpStopLat,_tmpStopLon,_tmpLocationType,_tmpParentStation);
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
  public Object getStopTimesForTrip(final String tripId,
      final Continuation<? super List<StopTimeEntity>> $completion) {
    final String _sql = "SELECT * FROM stop_times WHERE trip_id = ? ORDER BY stop_sequence ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, tripId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<StopTimeEntity>>() {
      @Override
      @NonNull
      public List<StopTimeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTripId = CursorUtil.getColumnIndexOrThrow(_cursor, "trip_id");
          final int _cursorIndexOfArrivalTime = CursorUtil.getColumnIndexOrThrow(_cursor, "arrival_time");
          final int _cursorIndexOfDepartureTime = CursorUtil.getColumnIndexOrThrow(_cursor, "departure_time");
          final int _cursorIndexOfStopId = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_id");
          final int _cursorIndexOfStopSequence = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_sequence");
          final int _cursorIndexOfPickupType = CursorUtil.getColumnIndexOrThrow(_cursor, "pickup_type");
          final int _cursorIndexOfDropOffType = CursorUtil.getColumnIndexOrThrow(_cursor, "drop_off_type");
          final List<StopTimeEntity> _result = new ArrayList<StopTimeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StopTimeEntity _item;
            final String _tmpTripId;
            _tmpTripId = _cursor.getString(_cursorIndexOfTripId);
            final String _tmpArrivalTime;
            if (_cursor.isNull(_cursorIndexOfArrivalTime)) {
              _tmpArrivalTime = null;
            } else {
              _tmpArrivalTime = _cursor.getString(_cursorIndexOfArrivalTime);
            }
            final String _tmpDepartureTime;
            if (_cursor.isNull(_cursorIndexOfDepartureTime)) {
              _tmpDepartureTime = null;
            } else {
              _tmpDepartureTime = _cursor.getString(_cursorIndexOfDepartureTime);
            }
            final String _tmpStopId;
            _tmpStopId = _cursor.getString(_cursorIndexOfStopId);
            final int _tmpStopSequence;
            _tmpStopSequence = _cursor.getInt(_cursorIndexOfStopSequence);
            final Integer _tmpPickupType;
            if (_cursor.isNull(_cursorIndexOfPickupType)) {
              _tmpPickupType = null;
            } else {
              _tmpPickupType = _cursor.getInt(_cursorIndexOfPickupType);
            }
            final Integer _tmpDropOffType;
            if (_cursor.isNull(_cursorIndexOfDropOffType)) {
              _tmpDropOffType = null;
            } else {
              _tmpDropOffType = _cursor.getInt(_cursorIndexOfDropOffType);
            }
            _item = new StopTimeEntity(_tmpTripId,_tmpArrivalTime,_tmpDepartureTime,_tmpStopId,_tmpStopSequence,_tmpPickupType,_tmpDropOffType);
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
  public Object tripHasStartBeforeEnd(final String tripId, final String startStop,
      final String endStop, final Continuation<? super Integer> $completion) {
    final String _sql = "\n"
            + "        SELECT COUNT(*) FROM (\n"
            + "            SELECT 1 FROM stop_times st1\n"
            + "            JOIN stop_times st2 ON st1.trip_id = st2.trip_id\n"
            + "            WHERE st1.stop_id = ? AND st2.stop_id = ?\n"
            + "              AND st1.trip_id = ?\n"
            + "              AND st1.stop_sequence < st2.stop_sequence\n"
            + "            LIMIT 1\n"
            + "        )\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindString(_argIndex, startStop);
    _argIndex = 2;
    _statement.bindString(_argIndex, endStop);
    _argIndex = 3;
    _statement.bindString(_argIndex, tripId);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
