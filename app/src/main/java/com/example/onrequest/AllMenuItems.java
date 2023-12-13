package com.example.onrequest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.onrequest.API.ApiAdapter;
import com.example.onrequest.API.ApiService;
import com.example.onrequest.API.DataSource;
import com.example.onrequest.API.MenuItemAPI;

import java.util.List;

import retrofit2.Call;

public class AllMenuItems extends AppCompatActivity {
    private ApiAdapter apiAdapter;
    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_menu_items);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewA);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apiService = DataSource.getRetrofit().create(ApiService.class);

        Call<List<MenuItemAPI>> call = apiService.getMenuItems();
    }
}