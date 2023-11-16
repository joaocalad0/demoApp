package com.example.onrequest.schema.dao;

import android.database.Cursor;
import android.net.Uri;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.onrequest.schema.converters.Converters;
import com.example.onrequest.schema.entity.item.MenuItem;
import com.example.onrequest.schema.entity.item.MenuItemCategory;
import java.lang.Class;
import java.lang.Double;
import java.lang.IllegalArgumentException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MenuItemDao_Impl implements MenuItemDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MenuItem> __insertionAdapterOfMenuItem;

  private final EntityDeletionOrUpdateAdapter<MenuItem> __deletionAdapterOfMenuItem;

  private final EntityDeletionOrUpdateAdapter<MenuItem> __updateAdapterOfMenuItem;

  public MenuItemDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMenuItem = new EntityInsertionAdapter<MenuItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `MenuItem` (`menuItemId`,`menuItemName`,`menuItemPrice`,`menuItemAvatar`,`menuItemCategory`,`menuItemDesc`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MenuItem value) {
        stmt.bindLong(1, value.getMenuItemId());
        if (value.getMenuItemName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getMenuItemName());
        }
        if (value.getMenuItemPrice() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindDouble(3, value.getMenuItemPrice());
        }
        final String _tmp = Converters.toString(value.getMenuItemAvatar());
        if (_tmp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, _tmp);
        }
        if (value.getMenuItemCategory() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, __MenuItemCategory_enumToString(value.getMenuItemCategory()));
        }
        if (value.getMenuItemDesc() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getMenuItemDesc());
        }
      }
    };
    this.__deletionAdapterOfMenuItem = new EntityDeletionOrUpdateAdapter<MenuItem>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `MenuItem` WHERE `menuItemId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MenuItem value) {
        stmt.bindLong(1, value.getMenuItemId());
      }
    };
    this.__updateAdapterOfMenuItem = new EntityDeletionOrUpdateAdapter<MenuItem>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `MenuItem` SET `menuItemId` = ?,`menuItemName` = ?,`menuItemPrice` = ?,`menuItemAvatar` = ?,`menuItemCategory` = ?,`menuItemDesc` = ? WHERE `menuItemId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MenuItem value) {
        stmt.bindLong(1, value.getMenuItemId());
        if (value.getMenuItemName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getMenuItemName());
        }
        if (value.getMenuItemPrice() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindDouble(3, value.getMenuItemPrice());
        }
        final String _tmp = Converters.toString(value.getMenuItemAvatar());
        if (_tmp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, _tmp);
        }
        if (value.getMenuItemCategory() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, __MenuItemCategory_enumToString(value.getMenuItemCategory()));
        }
        if (value.getMenuItemDesc() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getMenuItemDesc());
        }
        stmt.bindLong(7, value.getMenuItemId());
      }
    };
  }

  @Override
  public long insert(final MenuItem menuItem) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfMenuItem.insertAndReturnId(menuItem);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final MenuItem menuItem) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfMenuItem.handle(menuItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final MenuItem menuItem) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfMenuItem.handle(menuItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<MenuItem> getAll() {
    final String _sql = "SELECT * FROM menuitem";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMenuItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "menuItemId");
      final int _cursorIndexOfMenuItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "menuItemName");
      final int _cursorIndexOfMenuItemPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "menuItemPrice");
      final int _cursorIndexOfMenuItemAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "menuItemAvatar");
      final int _cursorIndexOfMenuItemCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "menuItemCategory");
      final int _cursorIndexOfMenuItemDesc = CursorUtil.getColumnIndexOrThrow(_cursor, "menuItemDesc");
      final List<MenuItem> _result = new ArrayList<MenuItem>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final MenuItem _item;
        _item = new MenuItem();
        final long _tmpMenuItemId;
        _tmpMenuItemId = _cursor.getLong(_cursorIndexOfMenuItemId);
        _item.setMenuItemId(_tmpMenuItemId);
        final String _tmpMenuItemName;
        if (_cursor.isNull(_cursorIndexOfMenuItemName)) {
          _tmpMenuItemName = null;
        } else {
          _tmpMenuItemName = _cursor.getString(_cursorIndexOfMenuItemName);
        }
        _item.setMenuItemName(_tmpMenuItemName);
        final Double _tmpMenuItemPrice;
        if (_cursor.isNull(_cursorIndexOfMenuItemPrice)) {
          _tmpMenuItemPrice = null;
        } else {
          _tmpMenuItemPrice = _cursor.getDouble(_cursorIndexOfMenuItemPrice);
        }
        _item.setMenuItemPrice(_tmpMenuItemPrice);
        final Uri _tmpMenuItemAvatar;
        final String _tmp;
        if (_cursor.isNull(_cursorIndexOfMenuItemAvatar)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getString(_cursorIndexOfMenuItemAvatar);
        }
        _tmpMenuItemAvatar = Converters.fromString(_tmp);
        _item.setMenuItemAvatar(_tmpMenuItemAvatar);
        final MenuItemCategory _tmpMenuItemCategory;
        _tmpMenuItemCategory = __MenuItemCategory_stringToEnum(_cursor.getString(_cursorIndexOfMenuItemCategory));
        _item.setMenuItemCategory(_tmpMenuItemCategory);
        final String _tmpMenuItemDesc;
        if (_cursor.isNull(_cursorIndexOfMenuItemDesc)) {
          _tmpMenuItemDesc = null;
        } else {
          _tmpMenuItemDesc = _cursor.getString(_cursorIndexOfMenuItemDesc);
        }
        _item.setMenuItemDesc(_tmpMenuItemDesc);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<MenuItem> getByCategory(final MenuItemCategory category) {
    final String _sql = "SELECT * FROM menuitem WHERE menuItemCategory = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, __MenuItemCategory_enumToString(category));
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMenuItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "menuItemId");
      final int _cursorIndexOfMenuItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "menuItemName");
      final int _cursorIndexOfMenuItemPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "menuItemPrice");
      final int _cursorIndexOfMenuItemAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "menuItemAvatar");
      final int _cursorIndexOfMenuItemCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "menuItemCategory");
      final int _cursorIndexOfMenuItemDesc = CursorUtil.getColumnIndexOrThrow(_cursor, "menuItemDesc");
      final List<MenuItem> _result = new ArrayList<MenuItem>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final MenuItem _item;
        _item = new MenuItem();
        final long _tmpMenuItemId;
        _tmpMenuItemId = _cursor.getLong(_cursorIndexOfMenuItemId);
        _item.setMenuItemId(_tmpMenuItemId);
        final String _tmpMenuItemName;
        if (_cursor.isNull(_cursorIndexOfMenuItemName)) {
          _tmpMenuItemName = null;
        } else {
          _tmpMenuItemName = _cursor.getString(_cursorIndexOfMenuItemName);
        }
        _item.setMenuItemName(_tmpMenuItemName);
        final Double _tmpMenuItemPrice;
        if (_cursor.isNull(_cursorIndexOfMenuItemPrice)) {
          _tmpMenuItemPrice = null;
        } else {
          _tmpMenuItemPrice = _cursor.getDouble(_cursorIndexOfMenuItemPrice);
        }
        _item.setMenuItemPrice(_tmpMenuItemPrice);
        final Uri _tmpMenuItemAvatar;
        final String _tmp;
        if (_cursor.isNull(_cursorIndexOfMenuItemAvatar)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getString(_cursorIndexOfMenuItemAvatar);
        }
        _tmpMenuItemAvatar = Converters.fromString(_tmp);
        _item.setMenuItemAvatar(_tmpMenuItemAvatar);
        final MenuItemCategory _tmpMenuItemCategory;
        _tmpMenuItemCategory = __MenuItemCategory_stringToEnum(_cursor.getString(_cursorIndexOfMenuItemCategory));
        _item.setMenuItemCategory(_tmpMenuItemCategory);
        final String _tmpMenuItemDesc;
        if (_cursor.isNull(_cursorIndexOfMenuItemDesc)) {
          _tmpMenuItemDesc = null;
        } else {
          _tmpMenuItemDesc = _cursor.getString(_cursorIndexOfMenuItemDesc);
        }
        _item.setMenuItemDesc(_tmpMenuItemDesc);
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

  private String __MenuItemCategory_enumToString(final MenuItemCategory _value) {
    if (_value == null) {
      return null;
    } switch (_value) {
      case FOOD: return "FOOD";
      case DRINK: return "DRINK";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private MenuItemCategory __MenuItemCategory_stringToEnum(final String _value) {
    if (_value == null) {
      return null;
    } switch (_value) {
      case "FOOD": return MenuItemCategory.FOOD;
      case "DRINK": return MenuItemCategory.DRINK;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
