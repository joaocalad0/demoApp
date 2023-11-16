package com.example.onrequest.schema.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.onrequest.schema.entity.table.MenuTable;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MenuTableDao_Impl implements MenuTableDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MenuTable> __insertionAdapterOfMenuTable;

  private final EntityDeletionOrUpdateAdapter<MenuTable> __deletionAdapterOfMenuTable;

  private final EntityDeletionOrUpdateAdapter<MenuTable> __updateAdapterOfMenuTable;

  public MenuTableDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMenuTable = new EntityInsertionAdapter<MenuTable>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `MenuTable` (`menuTableId`) VALUES (nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MenuTable value) {
        stmt.bindLong(1, value.getMenuTableId());
      }
    };
    this.__deletionAdapterOfMenuTable = new EntityDeletionOrUpdateAdapter<MenuTable>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `MenuTable` WHERE `menuTableId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MenuTable value) {
        stmt.bindLong(1, value.getMenuTableId());
      }
    };
    this.__updateAdapterOfMenuTable = new EntityDeletionOrUpdateAdapter<MenuTable>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `MenuTable` SET `menuTableId` = ? WHERE `menuTableId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MenuTable value) {
        stmt.bindLong(1, value.getMenuTableId());
        stmt.bindLong(2, value.getMenuTableId());
      }
    };
  }

  @Override
  public void insert(final MenuTable menuTable) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMenuTable.insert(menuTable);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final MenuTable menuTable) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfMenuTable.handle(menuTable);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final MenuTable menuTable) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfMenuTable.handle(menuTable);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public MenuTable getById(final long id) {
    final String _sql = "SELECT * FROM menutable WHERE menuTableId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMenuTableId = CursorUtil.getColumnIndexOrThrow(_cursor, "menuTableId");
      final MenuTable _result;
      if(_cursor.moveToFirst()) {
        final long _tmpMenuTableId;
        _tmpMenuTableId = _cursor.getLong(_cursorIndexOfMenuTableId);
        _result = new MenuTable(_tmpMenuTableId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<MenuTable> getAll() {
    final String _sql = "SELECT * FROM menutable";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMenuTableId = CursorUtil.getColumnIndexOrThrow(_cursor, "menuTableId");
      final List<MenuTable> _result = new ArrayList<MenuTable>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final MenuTable _item;
        final long _tmpMenuTableId;
        _tmpMenuTableId = _cursor.getLong(_cursorIndexOfMenuTableId);
        _item = new MenuTable(_tmpMenuTableId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
