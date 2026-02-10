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
public final class VehicleTypeDao_Impl implements VehicleTypeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<VehicleTypeEntity> __insertionAdapterOfVehicleTypeEntity;

  private final SharedSQLiteStatement __preparedStmtOfClear;

  public VehicleTypeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVehicleTypeEntity = new EntityInsertionAdapter<VehicleTypeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `vehicle_types` (`vehicle_type_id`,`vehicle_type_name`,`vehicle_type_description`,`vehicle_type_symbol`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VehicleTypeEntity entity) {
        statement.bindString(1, entity.getVehicleTypeId());
        if (entity.getVehicleTypeName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getVehicleTypeName());
        }
        if (entity.getVehicleTypeDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getVehicleTypeDescription());
        }
        if (entity.getVehicleTypeSymbol() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getVehicleTypeSymbol());
        }
      }
    };
    this.__preparedStmtOfClear = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM vehicle_types";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<VehicleTypeEntity> list,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfVehicleTypeEntity.insert(list);
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
  public Object getAll(final Continuation<? super List<VehicleTypeEntity>> $completion) {
    final String _sql = "SELECT * FROM vehicle_types";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<VehicleTypeEntity>>() {
      @Override
      @NonNull
      public List<VehicleTypeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfVehicleTypeId = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicle_type_id");
          final int _cursorIndexOfVehicleTypeName = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicle_type_name");
          final int _cursorIndexOfVehicleTypeDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicle_type_description");
          final int _cursorIndexOfVehicleTypeSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicle_type_symbol");
          final List<VehicleTypeEntity> _result = new ArrayList<VehicleTypeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VehicleTypeEntity _item;
            final String _tmpVehicleTypeId;
            _tmpVehicleTypeId = _cursor.getString(_cursorIndexOfVehicleTypeId);
            final String _tmpVehicleTypeName;
            if (_cursor.isNull(_cursorIndexOfVehicleTypeName)) {
              _tmpVehicleTypeName = null;
            } else {
              _tmpVehicleTypeName = _cursor.getString(_cursorIndexOfVehicleTypeName);
            }
            final String _tmpVehicleTypeDescription;
            if (_cursor.isNull(_cursorIndexOfVehicleTypeDescription)) {
              _tmpVehicleTypeDescription = null;
            } else {
              _tmpVehicleTypeDescription = _cursor.getString(_cursorIndexOfVehicleTypeDescription);
            }
            final String _tmpVehicleTypeSymbol;
            if (_cursor.isNull(_cursorIndexOfVehicleTypeSymbol)) {
              _tmpVehicleTypeSymbol = null;
            } else {
              _tmpVehicleTypeSymbol = _cursor.getString(_cursorIndexOfVehicleTypeSymbol);
            }
            _item = new VehicleTypeEntity(_tmpVehicleTypeId,_tmpVehicleTypeName,_tmpVehicleTypeDescription,_tmpVehicleTypeSymbol);
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
