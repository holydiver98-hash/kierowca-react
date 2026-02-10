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
public final class VariantDao_Impl implements VariantDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<VariantEntity> __insertionAdapterOfVariantEntity;

  private final SharedSQLiteStatement __preparedStmtOfClear;

  public VariantDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
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
    this.__preparedStmtOfClear = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM variants";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<VariantEntity> list,
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
  public Object getById(final String variantId,
      final Continuation<? super VariantEntity> $completion) {
    final String _sql = "SELECT * FROM variants WHERE variant_id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, variantId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<VariantEntity>() {
      @Override
      @Nullable
      public VariantEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfVariantId = CursorUtil.getColumnIndexOrThrow(_cursor, "variant_id");
          final int _cursorIndexOfIsMain = CursorUtil.getColumnIndexOrThrow(_cursor, "is_main");
          final int _cursorIndexOfEquivMainVariantId = CursorUtil.getColumnIndexOrThrow(_cursor, "equiv_main_variant_id");
          final int _cursorIndexOfJoinStopId = CursorUtil.getColumnIndexOrThrow(_cursor, "join_stop_id");
          final int _cursorIndexOfDisjoinStopId = CursorUtil.getColumnIndexOrThrow(_cursor, "disjoin_stop_id");
          final VariantEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpVariantId;
            _tmpVariantId = _cursor.getString(_cursorIndexOfVariantId);
            final Integer _tmpIsMain;
            if (_cursor.isNull(_cursorIndexOfIsMain)) {
              _tmpIsMain = null;
            } else {
              _tmpIsMain = _cursor.getInt(_cursorIndexOfIsMain);
            }
            final String _tmpEquivMainVariantId;
            if (_cursor.isNull(_cursorIndexOfEquivMainVariantId)) {
              _tmpEquivMainVariantId = null;
            } else {
              _tmpEquivMainVariantId = _cursor.getString(_cursorIndexOfEquivMainVariantId);
            }
            final String _tmpJoinStopId;
            if (_cursor.isNull(_cursorIndexOfJoinStopId)) {
              _tmpJoinStopId = null;
            } else {
              _tmpJoinStopId = _cursor.getString(_cursorIndexOfJoinStopId);
            }
            final String _tmpDisjoinStopId;
            if (_cursor.isNull(_cursorIndexOfDisjoinStopId)) {
              _tmpDisjoinStopId = null;
            } else {
              _tmpDisjoinStopId = _cursor.getString(_cursorIndexOfDisjoinStopId);
            }
            _result = new VariantEntity(_tmpVariantId,_tmpIsMain,_tmpEquivMainVariantId,_tmpJoinStopId,_tmpDisjoinStopId);
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
  public Object getAll(final Continuation<? super List<VariantEntity>> $completion) {
    final String _sql = "SELECT * FROM variants";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<VariantEntity>>() {
      @Override
      @NonNull
      public List<VariantEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfVariantId = CursorUtil.getColumnIndexOrThrow(_cursor, "variant_id");
          final int _cursorIndexOfIsMain = CursorUtil.getColumnIndexOrThrow(_cursor, "is_main");
          final int _cursorIndexOfEquivMainVariantId = CursorUtil.getColumnIndexOrThrow(_cursor, "equiv_main_variant_id");
          final int _cursorIndexOfJoinStopId = CursorUtil.getColumnIndexOrThrow(_cursor, "join_stop_id");
          final int _cursorIndexOfDisjoinStopId = CursorUtil.getColumnIndexOrThrow(_cursor, "disjoin_stop_id");
          final List<VariantEntity> _result = new ArrayList<VariantEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VariantEntity _item;
            final String _tmpVariantId;
            _tmpVariantId = _cursor.getString(_cursorIndexOfVariantId);
            final Integer _tmpIsMain;
            if (_cursor.isNull(_cursorIndexOfIsMain)) {
              _tmpIsMain = null;
            } else {
              _tmpIsMain = _cursor.getInt(_cursorIndexOfIsMain);
            }
            final String _tmpEquivMainVariantId;
            if (_cursor.isNull(_cursorIndexOfEquivMainVariantId)) {
              _tmpEquivMainVariantId = null;
            } else {
              _tmpEquivMainVariantId = _cursor.getString(_cursorIndexOfEquivMainVariantId);
            }
            final String _tmpJoinStopId;
            if (_cursor.isNull(_cursorIndexOfJoinStopId)) {
              _tmpJoinStopId = null;
            } else {
              _tmpJoinStopId = _cursor.getString(_cursorIndexOfJoinStopId);
            }
            final String _tmpDisjoinStopId;
            if (_cursor.isNull(_cursorIndexOfDisjoinStopId)) {
              _tmpDisjoinStopId = null;
            } else {
              _tmpDisjoinStopId = _cursor.getString(_cursorIndexOfDisjoinStopId);
            }
            _item = new VariantEntity(_tmpVariantId,_tmpIsMain,_tmpEquivMainVariantId,_tmpJoinStopId,_tmpDisjoinStopId);
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
