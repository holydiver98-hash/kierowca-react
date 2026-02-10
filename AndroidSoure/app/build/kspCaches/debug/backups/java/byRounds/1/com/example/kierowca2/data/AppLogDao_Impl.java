package com.example.kierowca2.data;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.kierowca2.data.entity.AppLogEntity;
import java.lang.Class;
import java.lang.Exception;
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
public final class AppLogDao_Impl implements AppLogDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AppLogEntity> __insertionAdapterOfAppLogEntity;

  private final SharedSQLiteStatement __preparedStmtOfClearLogs;

  public AppLogDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAppLogEntity = new EntityInsertionAdapter<AppLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `app_logs` (`id`,`message`,`stackTrace`,`timestamp`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AppLogEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getMessage());
        if (entity.getStackTrace() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getStackTrace());
        }
        statement.bindLong(4, entity.getTimestamp());
      }
    };
    this.__preparedStmtOfClearLogs = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM app_logs";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final AppLogEntity log, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAppLogEntity.insert(log);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearLogs(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearLogs.acquire();
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
          __preparedStmtOfClearLogs.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<AppLogEntity>> getAllLogs() {
    final String _sql = "SELECT * FROM app_logs ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"app_logs"}, new Callable<List<AppLogEntity>>() {
      @Override
      @NonNull
      public List<AppLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfStackTrace = CursorUtil.getColumnIndexOrThrow(_cursor, "stackTrace");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final List<AppLogEntity> _result = new ArrayList<AppLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpMessage;
            _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            final String _tmpStackTrace;
            if (_cursor.isNull(_cursorIndexOfStackTrace)) {
              _tmpStackTrace = null;
            } else {
              _tmpStackTrace = _cursor.getString(_cursorIndexOfStackTrace);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item = new AppLogEntity(_tmpId,_tmpMessage,_tmpStackTrace,_tmpTimestamp);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
