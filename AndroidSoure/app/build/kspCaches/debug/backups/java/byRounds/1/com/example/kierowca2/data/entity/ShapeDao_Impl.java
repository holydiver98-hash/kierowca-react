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
public final class ShapeDao_Impl implements ShapeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ShapePointEntity> __insertionAdapterOfShapePointEntity;

  private final SharedSQLiteStatement __preparedStmtOfClear;

  public ShapeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
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
    this.__preparedStmtOfClear = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM shapes";
        return _query;
      }
    };
  }

  @Override
  public Object insertShapePoints(final List<ShapePointEntity> list,
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
  public Object getShapePoints(final String shapeId,
      final Continuation<? super List<ShapePointEntity>> $completion) {
    final String _sql = "SELECT * FROM shapes WHERE shape_id = ? ORDER BY shape_pt_sequence ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, shapeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ShapePointEntity>>() {
      @Override
      @NonNull
      public List<ShapePointEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfShapeId = CursorUtil.getColumnIndexOrThrow(_cursor, "shape_id");
          final int _cursorIndexOfShapePtLat = CursorUtil.getColumnIndexOrThrow(_cursor, "shape_pt_lat");
          final int _cursorIndexOfShapePtLon = CursorUtil.getColumnIndexOrThrow(_cursor, "shape_pt_lon");
          final int _cursorIndexOfShapePtSequence = CursorUtil.getColumnIndexOrThrow(_cursor, "shape_pt_sequence");
          final List<ShapePointEntity> _result = new ArrayList<ShapePointEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ShapePointEntity _item;
            final String _tmpShapeId;
            _tmpShapeId = _cursor.getString(_cursorIndexOfShapeId);
            final double _tmpShapePtLat;
            _tmpShapePtLat = _cursor.getDouble(_cursorIndexOfShapePtLat);
            final double _tmpShapePtLon;
            _tmpShapePtLon = _cursor.getDouble(_cursorIndexOfShapePtLon);
            final int _tmpShapePtSequence;
            _tmpShapePtSequence = _cursor.getInt(_cursorIndexOfShapePtSequence);
            _item = new ShapePointEntity(_tmpShapeId,_tmpShapePtLat,_tmpShapePtLon,_tmpShapePtSequence);
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
