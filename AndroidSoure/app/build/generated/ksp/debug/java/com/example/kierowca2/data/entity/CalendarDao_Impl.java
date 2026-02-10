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
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CalendarDao_Impl implements CalendarDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CalendarEntity> __insertionAdapterOfCalendarEntity;

  private final SharedSQLiteStatement __preparedStmtOfClear;

  public CalendarDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
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
    this.__preparedStmtOfClear = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM calendar";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<CalendarEntity> list,
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
  public Object getByServiceId(final String serviceId,
      final Continuation<? super CalendarEntity> $completion) {
    final String _sql = "SELECT * FROM calendar WHERE service_id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, serviceId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CalendarEntity>() {
      @Override
      @Nullable
      public CalendarEntity call() throws Exception {
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
          final CalendarEntity _result;
          if (_cursor.moveToFirst()) {
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
            _result = new CalendarEntity(_tmpServiceId,_tmpMonday,_tmpTuesday,_tmpWednesday,_tmpThursday,_tmpFriday,_tmpSaturday,_tmpSunday,_tmpStartDate,_tmpEndDate);
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
