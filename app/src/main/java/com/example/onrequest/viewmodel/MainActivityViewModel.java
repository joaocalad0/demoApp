package com.example.onrequest.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.onrequest.model.MenuItemRepository;
import com.example.onrequest.schema.dao.MenuItemDao;
import com.example.onrequest.schema.db.AppDatabase;
import com.example.onrequest.schema.entity.item.MenuItem;
import com.example.onrequest.schema.entity.item.MenuItemCategory;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private MenuItemRepository menuItemRepository;
    //private MenuItemDao menuItemDao;
    private LiveData<List<MenuItem>> allMenuItems;
    //private long currentTableId;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        menuItemRepository = new MenuItemRepository(application);
        allMenuItems = menuItemRepository.getAllMenuItems();
    }

    public LiveData<List<MenuItem>> getAllMenuItems() {
        return allMenuItems;
    }

    public LiveData<List<MenuItem>> getMenuItemsByTableId(long tableId) {
        return menuItemRepository.getMenuItemsByTableId(tableId);
    }

    public List<MenuItem> getMenuItemsByCategory(MenuItemCategory category) {
        return menuItemRepository.getMenuItemsByCategory(category);
    }
}

