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
public final class StopDao_Impl implements StopDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<StopEntity> __insertionAdapterOfStopEntity;

  private final SharedSQLiteStatement __preparedStmtOfClear;

  public StopDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
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
    this.__preparedStmtOfClear = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM stops";
        return _query;
      }
    };
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
  public Object getStopById(final String stopId,
      final Continuation<? super StopEntity> $completion) {
    final String _sql = "SELECT * FROM stops WHERE stop_id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, stopId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<StopEntity>() {
      @Override
      @Nullable
      public StopEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfStopId = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_id");
          final int _cursorIndexOfStopCode = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_code");
          final int _cursorIndexOfStopName = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_name");
          final int _cursorIndexOfStopLat = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_lat");
          final int _cursorIndexOfStopLon = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_lon");
          final int _cursorIndexOfLocationType = CursorUtil.getColumnIndexOrThrow(_cursor, "location_type");
          final int _cursorIndexOfParentStation = CursorUtil.getColumnIndexOrThrow(_cursor, "parent_station");
          final StopEntity _result;
          if (_cursor.moveToFirst()) {
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
            _result = new StopEntity(_tmpStopId,_tmpStopCode,_tmpStopName,_tmpStopLat,_tmpStopLon,_tmpLocationType,_tmpParentStation);
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
  public Object getStop(final String stopId, final Continuation<? super StopEntity> $completion) {
    final String _sql = "SELECT * FROM stops WHERE stop_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, stopId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<StopEntity>() {
      @Override
      @Nullable
      public StopEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfStopId = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_id");
          final int _cursorIndexOfStopCode = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_code");
          final int _cursorIndexOfStopName = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_name");
          final int _cursorIndexOfStopLat = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_lat");
          final int _cursorIndexOfStopLon = CursorUtil.getColumnIndexOrThrow(_cursor, "stop_lon");
          final int _cursorIndexOfLocationType = CursorUtil.getColumnIndexOrThrow(_cursor, "location_type");
          final int _cursorIndexOfParentStation = CursorUtil.getColumnIndexOrThrow(_cursor, "parent_station");
          final StopEntity _result;
          if (_cursor.moveToFirst()) {
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
            _result = new StopEntity(_tmpStopId,_tmpStopCode,_tmpStopName,_tmpStopLat,_tmpStopLon,_tmpLocationType,_tmpParentStation);
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
  public Object getAllStops(final Continuation<? super List<StopEntity>> $completion) {
    final String _sql = "SELECT * FROM stops ORDER BY stop_name";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
