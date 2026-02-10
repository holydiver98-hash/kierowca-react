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
public final class RouteDao_Impl implements RouteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RouteEntity> __insertionAdapterOfRouteEntity;

  private final SharedSQLiteStatement __preparedStmtOfClear;

  public RouteDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
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
    this.__preparedStmtOfClear = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM routes";
        return _query;
      }
    };
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
  public Object getAllRoutes(final Continuation<? super List<RouteEntity>> $completion) {
    final String _sql = "SELECT * FROM routes ORDER BY route_short_name";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<RouteEntity>>() {
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
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getRouteByShortName(final String shortName,
      final Continuation<? super RouteEntity> $completion) {
    final String _sql = "SELECT * FROM routes WHERE route_short_name = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, shortName);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<RouteEntity>() {
      @Override
      @Nullable
      public RouteEntity call() throws Exception {
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
          final RouteEntity _result;
          if (_cursor.moveToFirst()) {
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
            _result = new RouteEntity(_tmpRouteId,_tmpAgencyId,_tmpRouteShortName,_tmpRouteLongName,_tmpRouteDesc,_tmpRouteType,_tmpRouteType2Id,_tmpValidFrom,_tmpValidUntil);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
