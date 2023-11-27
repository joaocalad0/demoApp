package com.example.onrequest.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onrequest.schema.dao.MenuItemDao;
import com.example.onrequest.schema.db.AppDatabase;
import com.example.onrequest.schema.entity.item.MenuItem;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends AndroidViewModel {
    private MenuItemDao menuItemDao;
    private MediatorLiveData<List<MenuItem>> menuListLiveData = new MediatorLiveData<>();
    private long currentTableId;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.menuItemDao = AppDatabase.getInstance(application).getMenuItemDao();
        LiveData<List<MenuItem>> sourceLiveData = menuItemDao.getByTableId(currentTableId);
        menuListLiveData.addSource(sourceLiveData, menuItems -> {
            menuListLiveData.setValue(menuItems);
        });
    }

    public LiveData<List<MenuItem>> getMenuForTable(long tableId) {
        return menuItemDao.getByTableId(tableId);
    }
}
