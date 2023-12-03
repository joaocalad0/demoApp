package com.example.onrequest.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.onrequest.model.MenuTableRepository;
import com.example.onrequest.schema.entity.table.MenuTable;

import java.util.List;

public class MenuTableViewModel extends AndroidViewModel {
    private MenuTableRepository menuTableRepository;
    private LiveData<List<MenuTable>> getAllTables;

    public MenuTableViewModel(@NonNull Application application){
        super(application);
        menuTableRepository = new MenuTableRepository(application);
        getAllTables = menuTableRepository.getAllTables();
    }

    public LiveData<List<MenuTable>> getGetAllTables() {
        return getAllTables;
    }

    public MenuTable getTableById(long id) {
        return menuTableRepository.getTablesById(id);
    }
}
