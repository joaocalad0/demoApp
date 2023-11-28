package com.example.onrequest.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onrequest.MenuAdapter;
import com.example.onrequest.model.MenuItemRepository;
import com.example.onrequest.schema.entity.item.MenuItem;
import com.example.onrequest.schema.entity.item.MenuItemCategory;

import java.util.List;

public class MenuItemViewModel extends AndroidViewModel {
    private MenuItemRepository menuItemRepository;
    private LiveData<List<MenuItem>> allMenuItems;
    private MenuAdapter adapter;

    public MenuItemViewModel(@NonNull Application application) {
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

