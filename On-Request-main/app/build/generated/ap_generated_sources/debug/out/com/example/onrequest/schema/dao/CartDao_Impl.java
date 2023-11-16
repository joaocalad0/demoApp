package com.example.onrequest.schema.dao;

import android.database.Cursor;
import android.net.Uri;
import androidx.collection.LongSparseArray;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.onrequest.schema.converters.Converters;
import com.example.onrequest.schema.entity.cart.Cart;
import com.example.onrequest.schema.entity.cart.CartMenuItem;
import com.example.onrequest.schema.entity.cart.CartState;
import com.example.onrequest.schema.entity.cart.CartWithMenuItems;
import com.example.onrequest.schema.entity.item.MenuItem;
import com.example.onrequest.schema.entity.item.MenuItemCategory;
import java.lang.Class;
import java.lang.Double;
import java.lang.IllegalArgumentException;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CartDao_Impl implements CartDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Cart> __insertionAdapterOfCart;

  private final EntityInsertionAdapter<CartMenuItem> __insertionAdapterOfCartMenuItem;

  private final EntityDeletionOrUpdateAdapter<Cart> __deletionAdapterOfCart;

  private final EntityDeletionOrUpdateAdapter<CartMenuItem> __deletionAdapterOfCartMenuItem;

  private final EntityDeletionOrUpdateAdapter<Cart> __updateAdapterOfCart;

  private final EntityDeletionOrUpdateAdapter<CartMenuItem> __updateAdapterOfCartMenuItem;

  public CartDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCart = new EntityInsertionAdapter<Cart>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Cart` (`cartId`,`menuTableId`,`cartState`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Cart value) {
        stmt.bindLong(1, value.getCartId());
        if (value.getMenuTableId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getMenuTableId());
        }
        if (value.getCartState() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, __CartState_enumToString(value.getCartState()));
        }
      }
    };
    this.__insertionAdapterOfCartMenuItem = new EntityInsertionAdapter<CartMenuItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `CartMenuItem` (`cartMenuItemId`,`menuItemId`,`cartId`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CartMenuItem value) {
        stmt.bindLong(1, value.getCartMenuItemId());
        if (value.getMenuItemId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getMenuItemId());
        }
        if (value.getCartId() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getCartId());
        }
      }
    };
    this.__deletionAdapterOfCart = new EntityDeletionOrUpdateAdapter<Cart>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Cart` WHERE `cartId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Cart value) {
        stmt.bindLong(1, value.getCartId());
      }
    };
    this.__deletionAdapterOfCartMenuItem = new EntityDeletionOrUpdateAdapter<CartMenuItem>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `CartMenuItem` WHERE `cartMenuItemId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CartMenuItem value) {
        stmt.bindLong(1, value.getCartMenuItemId());
      }
    };
    this.__updateAdapterOfCart = new EntityDeletionOrUpdateAdapter<Cart>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Cart` SET `cartId` = ?,`menuTableId` = ?,`cartState` = ? WHERE `cartId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Cart value) {
        stmt.bindLong(1, value.getCartId());
        if (value.getMenuTableId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getMenuTableId());
        }
        if (value.getCartState() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, __CartState_enumToString(value.getCartState()));
        }
        stmt.bindLong(4, value.getCartId());
      }
    };
    this.__updateAdapterOfCartMenuItem = new EntityDeletionOrUpdateAdapter<CartMenuItem>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `CartMenuItem` SET `cartMenuItemId` = ?,`menuItemId` = ?,`cartId` = ? WHERE `cartMenuItemId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CartMenuItem value) {
        stmt.bindLong(1, value.getCartMenuItemId());
        if (value.getMenuItemId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getMenuItemId());
        }
        if (value.getCartId() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getCartId());
        }
        stmt.bindLong(4, value.getCartMenuItemId());
      }
    };
  }

  @Override
  public long insert(final Cart cart) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfCart.insertAndReturnId(cart);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public long insert(final CartMenuItem cartMenuItem) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfCartMenuItem.insertAndReturnId(cartMenuItem);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Cart cart) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCart.handle(cart);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final CartMenuItem cartMenuItem) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCartMenuItem.handle(cartMenuItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Cart cart) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfCart.handle(cart);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final CartMenuItem cartMenuItem) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfCartMenuItem.handle(cartMenuItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Cart> getAll() {
    final String _sql = "SELECT * FROM cart";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCartId = CursorUtil.getColumnIndexOrThrow(_cursor, "cartId");
      final int _cursorIndexOfMenuTableId = CursorUtil.getColumnIndexOrThrow(_cursor, "menuTableId");
      final int _cursorIndexOfCartState = CursorUtil.getColumnIndexOrThrow(_cursor, "cartState");
      final List<Cart> _result = new ArrayList<Cart>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Cart _item;
        final long _tmpCartId;
        _tmpCartId = _cursor.getLong(_cursorIndexOfCartId);
        final Long _tmpMenuTableId;
        if (_cursor.isNull(_cursorIndexOfMenuTableId)) {
          _tmpMenuTableId = null;
        } else {
          _tmpMenuTableId = _cursor.getLong(_cursorIndexOfMenuTableId);
        }
        final CartState _tmpCartState;
        _tmpCartState = __CartState_stringToEnum(_cursor.getString(_cursorIndexOfCartState));
        _item = new Cart(_tmpCartId,_tmpMenuTableId,_tmpCartState);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Cart getById(final long cartId) {
    final String _sql = "SELECT * FROM cart where cartId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, cartId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCartId = CursorUtil.getColumnIndexOrThrow(_cursor, "cartId");
      final int _cursorIndexOfMenuTableId = CursorUtil.getColumnIndexOrThrow(_cursor, "menuTableId");
      final int _cursorIndexOfCartState = CursorUtil.getColumnIndexOrThrow(_cursor, "cartState");
      final Cart _result;
      if(_cursor.moveToFirst()) {
        final long _tmpCartId;
        _tmpCartId = _cursor.getLong(_cursorIndexOfCartId);
        final Long _tmpMenuTableId;
        if (_cursor.isNull(_cursorIndexOfMenuTableId)) {
          _tmpMenuTableId = null;
        } else {
          _tmpMenuTableId = _cursor.getLong(_cursorIndexOfMenuTableId);
        }
        final CartState _tmpCartState;
        _tmpCartState = __CartState_stringToEnum(_cursor.getString(_cursorIndexOfCartState));
        _result = new Cart(_tmpCartId,_tmpMenuTableId,_tmpCartState);
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
  public Cart getOpenCartByTable(final long tableId) {
    final String _sql = "SELECT * FROM cart c INNER JOIN MENUTABLE m ON c.menuTableId = m.menuTableId WHERE m.menuTableId = ? AND c.cartState = 'OPEN'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, tableId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCartId = CursorUtil.getColumnIndexOrThrow(_cursor, "cartId");
      final int _cursorIndexOfMenuTableId = CursorUtil.getColumnIndexOrThrow(_cursor, "menuTableId");
      final int _cursorIndexOfCartState = CursorUtil.getColumnIndexOrThrow(_cursor, "cartState");
      final int _cursorIndexOfMenuTableId_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "menuTableId");
      final Cart _result;
      if(_cursor.moveToFirst()) {
        final long _tmpCartId;
        _tmpCartId = _cursor.getLong(_cursorIndexOfCartId);
        final Long _tmpMenuTableId;
        if (_cursor.isNull(_cursorIndexOfMenuTableId)) {
          _tmpMenuTableId = null;
        } else {
          _tmpMenuTableId = _cursor.getLong(_cursorIndexOfMenuTableId);
        }
        final CartState _tmpCartState;
        _tmpCartState = __CartState_stringToEnum(_cursor.getString(_cursorIndexOfCartState));
        final Long _tmpMenuTableId_1;
        if (_cursor.isNull(_cursorIndexOfMenuTableId_1)) {
          _tmpMenuTableId_1 = null;
        } else {
          _tmpMenuTableId_1 = _cursor.getLong(_cursorIndexOfMenuTableId_1);
        }
        _result = new Cart(_tmpCartId,_tmpMenuTableId,_tmpCartState);
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
  public List<CartMenuItem> getMenuItemByCart(final long cartId) {
    final String _sql = "SELECT * FROM cartmenuitem c where c.cartId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, cartId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCartMenuItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "cartMenuItemId");
      final int _cursorIndexOfMenuItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "menuItemId");
      final int _cursorIndexOfCartId = CursorUtil.getColumnIndexOrThrow(_cursor, "cartId");
      final List<CartMenuItem> _result = new ArrayList<CartMenuItem>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CartMenuItem _item;
        final long _tmpCartMenuItemId;
        _tmpCartMenuItemId = _cursor.getLong(_cursorIndexOfCartMenuItemId);
        final Long _tmpMenuItemId;
        if (_cursor.isNull(_cursorIndexOfMenuItemId)) {
          _tmpMenuItemId = null;
        } else {
          _tmpMenuItemId = _cursor.getLong(_cursorIndexOfMenuItemId);
        }
        final Long _tmpCartId;
        if (_cursor.isNull(_cursorIndexOfCartId)) {
          _tmpCartId = null;
        } else {
          _tmpCartId = _cursor.getLong(_cursorIndexOfCartId);
        }
        _item = new CartMenuItem(_tmpCartMenuItemId,_tmpMenuItemId,_tmpCartId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<CartWithMenuItems> getCartWithMenuItemByCartId(final long cartId) {
    final String _sql = "SELECT * FROM cartmenuitem c where c.cartId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, cartId);
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
      try {
        final int _cursorIndexOfCartMenuItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "cartMenuItemId");
        final int _cursorIndexOfMenuItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "menuItemId");
        final int _cursorIndexOfCartId = CursorUtil.getColumnIndexOrThrow(_cursor, "cartId");
        final LongSparseArray<Cart> _collectionCart = new LongSparseArray<Cart>();
        final LongSparseArray<MenuItem> _collectionMenuItem = new LongSparseArray<MenuItem>();
        while (_cursor.moveToNext()) {
          if (!_cursor.isNull(_cursorIndexOfCartId)) {
            final long _tmpKey = _cursor.getLong(_cursorIndexOfCartId);
            _collectionCart.put(_tmpKey, null);
          }
          if (!_cursor.isNull(_cursorIndexOfMenuItemId)) {
            final long _tmpKey_1 = _cursor.getLong(_cursorIndexOfMenuItemId);
            _collectionMenuItem.put(_tmpKey_1, null);
          }
        }
        _cursor.moveToPosition(-1);
        __fetchRelationshipCartAscomExampleOnrequestSchemaEntityCartCart(_collectionCart);
        __fetchRelationshipMenuItemAscomExampleOnrequestSchemaEntityItemMenuItem(_collectionMenuItem);
        final List<CartWithMenuItems> _result = new ArrayList<CartWithMenuItems>(_cursor.getCount());
        while(_cursor.moveToNext()) {
          final CartWithMenuItems _item;
          final CartMenuItem _tmpCartMenuItem;
          if (! (_cursor.isNull(_cursorIndexOfCartMenuItemId) && _cursor.isNull(_cursorIndexOfMenuItemId) && _cursor.isNull(_cursorIndexOfCartId))) {
            final long _tmpCartMenuItemId;
            _tmpCartMenuItemId = _cursor.getLong(_cursorIndexOfCartMenuItemId);
            final Long _tmpMenuItemId;
            if (_cursor.isNull(_cursorIndexOfMenuItemId)) {
              _tmpMenuItemId = null;
            } else {
              _tmpMenuItemId = _cursor.getLong(_cursorIndexOfMenuItemId);
            }
            final Long _tmpCartId;
            if (_cursor.isNull(_cursorIndexOfCartId)) {
              _tmpCartId = null;
            } else {
              _tmpCartId = _cursor.getLong(_cursorIndexOfCartId);
            }
            _tmpCartMenuItem = new CartMenuItem(_tmpCartMenuItemId,_tmpMenuItemId,_tmpCartId);
          }  else  {
            _tmpCartMenuItem = null;
          }
          Cart _tmpCart = null;
          if (!_cursor.isNull(_cursorIndexOfCartId)) {
            final long _tmpKey_2 = _cursor.getLong(_cursorIndexOfCartId);
            _tmpCart = _collectionCart.get(_tmpKey_2);
          }
          MenuItem _tmpMenuItem = null;
          if (!_cursor.isNull(_cursorIndexOfMenuItemId)) {
            final long _tmpKey_3 = _cursor.getLong(_cursorIndexOfMenuItemId);
            _tmpMenuItem = _collectionMenuItem.get(_tmpKey_3);
          }
          _item = new CartWithMenuItems();
          _item.cartMenuItem = _tmpCartMenuItem;
          _item.cart = _tmpCart;
          _item.menuItem = _tmpMenuItem;
          _result.add(_item);
        }
        __db.setTransactionSuccessful();
        return _result;
      } finally {
        _cursor.close();
        _statement.release();
      }
    } finally {
      __db.endTransaction();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private String __CartState_enumToString(final CartState _value) {
    if (_value == null) {
      return null;
    } switch (_value) {
      case OPEN: return "OPEN";
      case PAYED: return "PAYED";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private CartState __CartState_stringToEnum(final String _value) {
    if (_value == null) {
      return null;
    } switch (_value) {
      case "OPEN": return CartState.OPEN;
      case "PAYED": return CartState.PAYED;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }

  private void __fetchRelationshipCartAscomExampleOnrequestSchemaEntityCartCart(
      final LongSparseArray<Cart> _map) {
    if (_map.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      LongSparseArray<Cart> _tmpInnerMap = new LongSparseArray<Cart>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), null);
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipCartAscomExampleOnrequestSchemaEntityCartCart(_tmpInnerMap);
          _map.putAll(_tmpInnerMap);
          _tmpInnerMap = new LongSparseArray<Cart>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipCartAscomExampleOnrequestSchemaEntityCartCart(_tmpInnerMap);
        _map.putAll(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `cartId`,`menuTableId`,`cartState` FROM `Cart` WHERE `cartId` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "cartId");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfCartId = 0;
      final int _cursorIndexOfMenuTableId = 1;
      final int _cursorIndexOfCartState = 2;
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final long _tmpKey = _cursor.getLong(_itemKeyIndex);
          if (_map.containsKey(_tmpKey)) {
            final Cart _item_1;
            final long _tmpCartId;
            _tmpCartId = _cursor.getLong(_cursorIndexOfCartId);
            final Long _tmpMenuTableId;
            if (_cursor.isNull(_cursorIndexOfMenuTableId)) {
              _tmpMenuTableId = null;
            } else {
              _tmpMenuTableId = _cursor.getLong(_cursorIndexOfMenuTableId);
            }
            final CartState _tmpCartState;
            _tmpCartState = __CartState_stringToEnum(_cursor.getString(_cursorIndexOfCartState));
            _item_1 = new Cart(_tmpCartId,_tmpMenuTableId,_tmpCartState);
            _map.put(_tmpKey, _item_1);
          }
        }
      }
    } finally {
      _cursor.close();
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

  private void __fetchRelationshipMenuItemAscomExampleOnrequestSchemaEntityItemMenuItem(
      final LongSparseArray<MenuItem> _map) {
    if (_map.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      LongSparseArray<MenuItem> _tmpInnerMap = new LongSparseArray<MenuItem>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), null);
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipMenuItemAscomExampleOnrequestSchemaEntityItemMenuItem(_tmpInnerMap);
          _map.putAll(_tmpInnerMap);
          _tmpInnerMap = new LongSparseArray<MenuItem>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipMenuItemAscomExampleOnrequestSchemaEntityItemMenuItem(_tmpInnerMap);
        _map.putAll(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `menuItemId`,`menuItemName`,`menuItemPrice`,`menuItemAvatar`,`menuItemCategory`,`menuItemDesc` FROM `MenuItem` WHERE `menuItemId` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "menuItemId");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfMenuItemId = 0;
      final int _cursorIndexOfMenuItemName = 1;
      final int _cursorIndexOfMenuItemPrice = 2;
      final int _cursorIndexOfMenuItemAvatar = 3;
      final int _cursorIndexOfMenuItemCategory = 4;
      final int _cursorIndexOfMenuItemDesc = 5;
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final long _tmpKey = _cursor.getLong(_itemKeyIndex);
          if (_map.containsKey(_tmpKey)) {
            final MenuItem _item_1;
            _item_1 = new MenuItem();
            final long _tmpMenuItemId;
            _tmpMenuItemId = _cursor.getLong(_cursorIndexOfMenuItemId);
            _item_1.setMenuItemId(_tmpMenuItemId);
            final String _tmpMenuItemName;
            if (_cursor.isNull(_cursorIndexOfMenuItemName)) {
              _tmpMenuItemName = null;
            } else {
              _tmpMenuItemName = _cursor.getString(_cursorIndexOfMenuItemName);
            }
            _item_1.setMenuItemName(_tmpMenuItemName);
            final Double _tmpMenuItemPrice;
            if (_cursor.isNull(_cursorIndexOfMenuItemPrice)) {
              _tmpMenuItemPrice = null;
            } else {
              _tmpMenuItemPrice = _cursor.getDouble(_cursorIndexOfMenuItemPrice);
            }
            _item_1.setMenuItemPrice(_tmpMenuItemPrice);
            final Uri _tmpMenuItemAvatar;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfMenuItemAvatar)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfMenuItemAvatar);
            }
            _tmpMenuItemAvatar = Converters.fromString(_tmp);
            _item_1.setMenuItemAvatar(_tmpMenuItemAvatar);
            final MenuItemCategory _tmpMenuItemCategory;
            _tmpMenuItemCategory = __MenuItemCategory_stringToEnum(_cursor.getString(_cursorIndexOfMenuItemCategory));
            _item_1.setMenuItemCategory(_tmpMenuItemCategory);
            final String _tmpMenuItemDesc;
            if (_cursor.isNull(_cursorIndexOfMenuItemDesc)) {
              _tmpMenuItemDesc = null;
            } else {
              _tmpMenuItemDesc = _cursor.getString(_cursorIndexOfMenuItemDesc);
            }
            _item_1.setMenuItemDesc(_tmpMenuItemDesc);
            _map.put(_tmpKey, _item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
