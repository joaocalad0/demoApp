package com.example.onrequest.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.onrequest.schema.dao.MenuItemDao;
import com.example.onrequest.schema.db.AppDatabase;
import com.example.onrequest.schema.entity.item.MenuItem;
import com.example.onrequest.schema.entity.item.MenuItemCategory;

import java.util.List;

public class MenuItemRepository {

    private MenuItemDao menuItemDao;
    private LiveData<List<MenuItem>> allMenuItems;

    public MenuItemRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        menuItemDao = database.getMenuItemDao();
        allMenuItems = menuItemDao.getAll();
    }

    public LiveData<List<MenuItem>> getAllMenuItems() {
        return allMenuItems;
    }

    public LiveData<List<MenuItem>> getMenuItemsByTableId(long tableId) {
        return menuItemDao.getByTableId(tableId);
    }

    public List<MenuItem> getMenuItemsByCategory(MenuItemCategory category) {
        return menuItemDao.getByCategory(category);
    }

    public long insertMenuItem(MenuItem menuItem) {
        return menuItemDao.insert(menuItem);
    }

    public void updateMenuItem(MenuItem menuItem) {
        menuItemDao.update(menuItem);
    }

    public void deleteMenuItem(MenuItem menuItem) {
        menuItemDao.delete(menuItem);
    }
}


