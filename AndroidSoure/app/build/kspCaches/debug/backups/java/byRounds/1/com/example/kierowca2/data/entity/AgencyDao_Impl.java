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
public final class AgencyDao_Impl implements AgencyDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AgencyEntity> __insertionAdapterOfAgencyEntity;

  private final SharedSQLiteStatement __preparedStmtOfClear;

  private final SharedSQLiteStatement __preparedStmtOfUpdateName;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAgencySelection;

  public AgencyDao_Impl(@NonNull final RoomDatabase __db) {
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
    this.__preparedStmtOfClear = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM agency";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateName = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE agency SET agency_name = ? WHERE agency_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateAgencySelection = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE agency SET isEnabled = ? WHERE agency_id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<AgencyEntity> list,
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
  public Object updateName(final String id, final String name,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateName.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, name);
        _argIndex = 2;
        _stmt.bindString(_argIndex, id);
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
          __preparedStmtOfUpdateName.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateAgencySelection(final String id, final boolean selected,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateAgencySelection.acquire();
        int _argIndex = 1;
        final int _tmp = selected ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        _stmt.bindString(_argIndex, id);
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
          __preparedStmtOfUpdateAgencySelection.release(_stmt);
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
  public Object getById(final String id, final Continuation<? super AgencyEntity> $completion) {
    final String _sql = "SELECT * FROM agency WHERE agency_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AgencyEntity>() {
      @Override
      @Nullable
      public AgencyEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfAgencyId = CursorUtil.getColumnIndexOrThrow(_cursor, "agency_id");
          final int _cursorIndexOfAgencyName = CursorUtil.getColumnIndexOrThrow(_cursor, "agency_name");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfAgencyUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "agency_url");
          final int _cursorIndexOfAgencyTimezone = CursorUtil.getColumnIndexOrThrow(_cursor, "agency_timezone");
          final int _cursorIndexOfAgencyPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "agency_phone");
          final int _cursorIndexOfAgencyLang = CursorUtil.getColumnIndexOrThrow(_cursor, "agency_lang");
          final AgencyEntity _result;
          if (_cursor.moveToFirst()) {
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
            _result = new AgencyEntity(_tmpAgencyId,_tmpAgencyName,_tmpIsEnabled,_tmpAgencyUrl,_tmpAgencyTimezone,_tmpAgencyPhone,_tmpAgencyLang);
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
