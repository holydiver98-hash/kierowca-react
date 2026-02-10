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
public final class CalendarDateDao_Impl implements CalendarDateDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CalendarDateEntity> __insertionAdapterOfCalendarDateEntity;

  private final SharedSQLiteStatement __preparedStmtOfClear;

  public CalendarDateDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
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
    this.__preparedStmtOfClear = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM calendar_dates";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<CalendarDateEntity> list,
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
  public Object getDatesByService(final String serviceId,
      final Continuation<? super List<CalendarDateEntity>> $completion) {
    final String _sql = "SELECT * FROM calendar_dates WHERE service_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, serviceId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CalendarDateEntity>>() {
      @Override
      @NonNull
      public List<CalendarDateEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfServiceId = CursorUtil.getColumnIndexOrThrow(_cursor, "service_id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfExceptionType = CursorUtil.getColumnIndexOrThrow(_cursor, "exception_type");
          final List<CalendarDateEntity> _result = new ArrayList<CalendarDateEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CalendarDateEntity _item;
            final String _tmpServiceId;
            _tmpServiceId = _cursor.getString(_cursorIndexOfServiceId);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final Integer _tmpExceptionType;
            if (_cursor.isNull(_cursorIndexOfExceptionType)) {
              _tmpExceptionType = null;
            } else {
              _tmpExceptionType = _cursor.getInt(_cursorIndexOfExceptionType);
            }
            _item = new CalendarDateEntity(_tmpServiceId,_tmpDate,_tmpExceptionType);
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
