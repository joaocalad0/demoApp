package com.example.onrequest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.onrequest.API.ApiAdapter;
import com.example.onrequest.API.ApiService;
import com.example.onrequest.API.DataSource;
import com.example.onrequest.API.MenuItemAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllMenuItems extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ApiAdapter apiAdapter;
    private ApiService apiService;
    private List<MenuItemAPI> menuItemAPIList = new ArrayList<>(); // Inicialize a lista aqui

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_menu_items);

        recyclerView = findViewById(R.id.recyclerViewA);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        menuItemAPIList = new ArrayList<>();

        apiAdapter = new ApiAdapter(menuItemAPIList, this);
        recyclerView.setAdapter(apiAdapter);

        // Inicializar Retrofit e ApiService
        apiService = DataSource.getRetrofit().create(ApiService.class);

        // Fazer chamada à API
        Call<List<MenuItemAPI>> call = apiService.getMenuItems();
        call.enqueue(new Callback<List<MenuItemAPI>>() {
            @Override
            public void onResponse(Call<List<MenuItemAPI>> call, Response<List<MenuItemAPI>> response) {
                if (response.isSuccessful()) {
                    //menuItemAPIList = response.body(); // Atualize a lista
                    menuItemAPIList.addAll(response.body());
                    apiAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(AllMenuItems.this, "Erro ao obter itens do menu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MenuItemAPI>> call, Throwable t) {
                Toast.makeText(AllMenuItems.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}