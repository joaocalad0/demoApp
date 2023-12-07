package com.example.onrequest;

import android.view.Menu;

import androidx.lifecycle.LiveData;

import com.example.onrequest.schema.dao.MenuItemDao;
import com.example.onrequest.schema.dao.MenuTableDao;
import com.example.onrequest.schema.entity.item.MenuItem;
import com.example.onrequest.schema.entity.table.MenuTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DailyDiscount {
    private final MenuItemDao menuItemDao;
     double discountPercentage;
    private MenuAdapter adapter;
    private MenuTable menuTable;
    private TablesAdapter tablesAdapter;

    public DailyDiscount(MenuItemDao menuItemDao, double discountPercentage, MenuTable menuTable, TablesAdapter tablesAdapter) {
        this.menuItemDao = menuItemDao;
        this.discountPercentage = discountPercentage;
        this.menuTable = menuTable;
        this.tablesAdapter = tablesAdapter;
        //this.adapter = adapter;

    }

    public void applyDiscount() {

        LiveData<List<MenuItem>> menuItemsLiveData = menuItemDao.getByTableId(menuTable.getMenuTableId());
        menuItemsLiveData.observeForever(menuItems -> {
            if (menuItems != null) {
                Random randomItem = new Random();
                Collections.shuffle(menuItems);
                int numberOfItems = Math.min(5, menuItems.size());
                for (int i = 0; i < numberOfItems; i++) {
                    MenuItem menuItem = menuItems.get(i);
                    double price = menuItem.getMenuItemPrice();
                    double discount = price * discountPercentage;
                    menuItem.setMenuItemPrice(discount);
                    menuItemDao.update(menuItem);
                }
                adapter.refresh(menuItems);
            }
        });
    }

    public double calculateDiscountedPrice(double originalPrice) {
        return originalPrice - (originalPrice * discountPercentage);
    }
}