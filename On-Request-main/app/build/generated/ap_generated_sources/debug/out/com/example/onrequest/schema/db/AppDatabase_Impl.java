package com.example.onrequest.schema.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.example.onrequest.schema.dao.CartDao;
import com.example.onrequest.schema.dao.CartDao_Impl;
import com.example.onrequest.schema.dao.MenuItemDao;
import com.example.onrequest.schema.dao.MenuItemDao_Impl;
import com.example.onrequest.schema.dao.MenuTableDao;
import com.example.onrequest.schema.dao.MenuTableDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile MenuItemDao _menuItemDao;

  private volatile CartDao _cartDao;

  private volatile MenuTableDao _menuTableDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `MenuItem` (`menuItemId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `menuItemName` TEXT, `menuItemPrice` REAL NOT NULL, `menuItemAvatar` TEXT, `menuItemCategory` TEXT, `menuItemDesc` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Cart` (`cartId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `menuTableId` INTEGER, `cartState` TEXT, FOREIGN KEY(`menuTableId`) REFERENCES `MenuTable`(`menuTableId`) ON UPDATE CASCADE ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_Cart_menuTableId` ON `Cart` (`menuTableId`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `CartMenuItem` (`cartMenuItemId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `menuItemId` INTEGER, `cartId` INTEGER, FOREIGN KEY(`cartId`) REFERENCES `Cart`(`cartId`) ON UPDATE CASCADE ON DELETE CASCADE , FOREIGN KEY(`menuItemId`) REFERENCES `MenuItem`(`menuItemId`) ON UPDATE CASCADE ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_CartMenuItem_menuItemId` ON `CartMenuItem` (`menuItemId`)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_CartMenuItem_cartId` ON `CartMenuItem` (`cartId`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `MenuTable` (`menuTableId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '67e57f0d4b45215446766dd695baa7b9')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `MenuItem`");
        _db.execSQL("DROP TABLE IF EXISTS `Cart`");
        _db.execSQL("DROP TABLE IF EXISTS `CartMenuItem`");
        _db.execSQL("DROP TABLE IF EXISTS `MenuTable`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsMenuItem = new HashMap<String, TableInfo.Column>(6);
        _columnsMenuItem.put("menuItemId", new TableInfo.Column("menuItemId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMenuItem.put("menuItemName", new TableInfo.Column("menuItemName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMenuItem.put("menuItemPrice", new TableInfo.Column("menuItemPrice", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMenuItem.put("menuItemAvatar", new TableInfo.Column("menuItemAvatar", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMenuItem.put("menuItemCategory", new TableInfo.Column("menuItemCategory", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMenuItem.put("menuItemDesc", new TableInfo.Column("menuItemDesc", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMenuItem = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMenuItem = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMenuItem = new TableInfo("MenuItem", _columnsMenuItem, _foreignKeysMenuItem, _indicesMenuItem);
        final TableInfo _existingMenuItem = TableInfo.read(_db, "MenuItem");
        if (! _infoMenuItem.equals(_existingMenuItem)) {
          return new RoomOpenHelper.ValidationResult(false, "MenuItem(com.example.onrequest.schema.entity.item.MenuItem).\n"
                  + " Expected:\n" + _infoMenuItem + "\n"
                  + " Found:\n" + _existingMenuItem);
        }
        final HashMap<String, TableInfo.Column> _columnsCart = new HashMap<String, TableInfo.Column>(3);
        _columnsCart.put("cartId", new TableInfo.Column("cartId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCart.put("menuTableId", new TableInfo.Column("menuTableId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCart.put("cartState", new TableInfo.Column("cartState", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCart = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysCart.add(new TableInfo.ForeignKey("MenuTable", "CASCADE", "CASCADE",Arrays.asList("menuTableId"), Arrays.asList("menuTableId")));
        final HashSet<TableInfo.Index> _indicesCart = new HashSet<TableInfo.Index>(1);
        _indicesCart.add(new TableInfo.Index("index_Cart_menuTableId", false, Arrays.asList("menuTableId"), Arrays.asList("ASC")));
        final TableInfo _infoCart = new TableInfo("Cart", _columnsCart, _foreignKeysCart, _indicesCart);
        final TableInfo _existingCart = TableInfo.read(_db, "Cart");
        if (! _infoCart.equals(_existingCart)) {
          return new RoomOpenHelper.ValidationResult(false, "Cart(com.example.onrequest.schema.entity.cart.Cart).\n"
                  + " Expected:\n" + _infoCart + "\n"
                  + " Found:\n" + _existingCart);
        }
        final HashMap<String, TableInfo.Column> _columnsCartMenuItem = new HashMap<String, TableInfo.Column>(3);
        _columnsCartMenuItem.put("cartMenuItemId", new TableInfo.Column("cartMenuItemId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCartMenuItem.put("menuItemId", new TableInfo.Column("menuItemId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCartMenuItem.put("cartId", new TableInfo.Column("cartId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCartMenuItem = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysCartMenuItem.add(new TableInfo.ForeignKey("Cart", "CASCADE", "CASCADE",Arrays.asList("cartId"), Arrays.asList("cartId")));
        _foreignKeysCartMenuItem.add(new TableInfo.ForeignKey("MenuItem", "CASCADE", "CASCADE",Arrays.asList("menuItemId"), Arrays.asList("menuItemId")));
        final HashSet<TableInfo.Index> _indicesCartMenuItem = new HashSet<TableInfo.Index>(2);
        _indicesCartMenuItem.add(new TableInfo.Index("index_CartMenuItem_menuItemId", false, Arrays.asList("menuItemId"), Arrays.asList("ASC")));
        _indicesCartMenuItem.add(new TableInfo.Index("index_CartMenuItem_cartId", false, Arrays.asList("cartId"), Arrays.asList("ASC")));
        final TableInfo _infoCartMenuItem = new TableInfo("CartMenuItem", _columnsCartMenuItem, _foreignKeysCartMenuItem, _indicesCartMenuItem);
        final TableInfo _existingCartMenuItem = TableInfo.read(_db, "CartMenuItem");
        if (! _infoCartMenuItem.equals(_existingCartMenuItem)) {
          return new RoomOpenHelper.ValidationResult(false, "CartMenuItem(com.example.onrequest.schema.entity.cart.CartMenuItem).\n"
                  + " Expected:\n" + _infoCartMenuItem + "\n"
                  + " Found:\n" + _existingCartMenuItem);
        }
        final HashMap<String, TableInfo.Column> _columnsMenuTable = new HashMap<String, TableInfo.Column>(1);
        _columnsMenuTable.put("menuTableId", new TableInfo.Column("menuTableId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMenuTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMenuTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMenuTable = new TableInfo("MenuTable", _columnsMenuTable, _foreignKeysMenuTable, _indicesMenuTable);
        final TableInfo _existingMenuTable = TableInfo.read(_db, "MenuTable");
        if (! _infoMenuTable.equals(_existingMenuTable)) {
          return new RoomOpenHelper.ValidationResult(false, "MenuTable(com.example.onrequest.schema.entity.table.MenuTable).\n"
                  + " Expected:\n" + _infoMenuTable + "\n"
                  + " Found:\n" + _existingMenuTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "67e57f0d4b45215446766dd695baa7b9", "a332805011cebc0828d1b58d41a1a464");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "MenuItem","Cart","CartMenuItem","MenuTable");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `MenuItem`");
      _db.execSQL("DELETE FROM `Cart`");
      _db.execSQL("DELETE FROM `CartMenuItem`");
      _db.execSQL("DELETE FROM `MenuTable`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(MenuItemDao.class, MenuItemDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CartDao.class, CartDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MenuTableDao.class, MenuTableDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public MenuItemDao getMenuItemDao() {
    if (_menuItemDao != null) {
      return _menuItemDao;
    } else {
      synchronized(this) {
        if(_menuItemDao == null) {
          _menuItemDao = new MenuItemDao_Impl(this);
        }
        return _menuItemDao;
      }
    }
  }

  @Override
  public CartDao getCartDao() {
    if (_cartDao != null) {
      return _cartDao;
    } else {
      synchronized(this) {
        if(_cartDao == null) {
          _cartDao = new CartDao_Impl(this);
        }
        return _cartDao;
      }
    }
  }

  @Override
  public MenuTableDao getMenuTableDao() {
    if (_menuTableDao != null) {
      return _menuTableDao;
    } else {
      synchronized(this) {
        if(_menuTableDao == null) {
          _menuTableDao = new MenuTableDao_Impl(this);
        }
        return _menuTableDao;
      }
    }
  }
}
