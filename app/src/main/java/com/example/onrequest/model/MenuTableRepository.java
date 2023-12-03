package com.example.onrequest.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.onrequest.schema.dao.MenuTableDao;
import com.example.onrequest.schema.db.AppDatabase;
import com.example.onrequest.schema.entity.table.MenuTable;

import java.util.List;

public class MenuTableRepository {
    private MenuTableDao menuTableDao;
    private LiveData<List<MenuTable>> getAllTables;

    public MenuTableRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
        menuTableDao = database.getMenuTableDao();

    }

    public LiveData<List<MenuTable>>getAllTables(){
        return getAllTables;
    }

    public MenuTable getTablesById(long id){
        return menuTableDao.getById(id);
    }
}
