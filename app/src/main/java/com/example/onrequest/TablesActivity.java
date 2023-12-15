package com.example.onrequest;

import static com.example.onrequest.MainActivity.startMainActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onrequest.API.DataSource;
import com.example.onrequest.API.ApiService;
import com.example.onrequest.API.MenuItemAPI;
import com.example.onrequest.schema.dao.MenuItemDao;
import com.example.onrequest.schema.dao.MenuTableDao;
import com.example.onrequest.schema.dao.UserProfileDao;
import com.example.onrequest.schema.db.AppDatabase;
import com.example.onrequest.schema.entity.UserProfile;
import com.example.onrequest.schema.entity.table.MenuTable;
import com.example.onrequest.viewmodel.MenuTableViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TablesActivity extends AppCompatActivity {

    private MenuTableDao tableDao;
    private UserProfileDao userDao;
    private MenuTableViewModel menuTableViewModel;

    private MenuItemDao menuItemDao;
    private MenuTable menuTable;

    private DailyDiscount dailyDiscount;

    private static final int EDIT_PROFILE_REQUEST_CODE = 1;
    private MenuAdapter menuAdapter;

    private TablesAdapter tablesAdapter;

    private ImageView imageViewUserPhoto;
    private MenuItemAPI menuItemAPI;
    private Button button_api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tables_activity);
        RecyclerView recyclerView = findViewById(R.id.RecyclerViewT);
        tableDao = AppDatabase.getInstance(this).getMenuTableDao();
        userDao = AppDatabase.getInstance(this).getUserProfileDao();

        UserProfile userProfile = userDao.getUserProfile();
        updateProfileView();

        this.tablesAdapter = new TablesAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setAdapter(this.tablesAdapter);
        recyclerView.setLayoutManager(layoutManager);
        loadTables();
        //Clique do item(tables)
        tablesAdapter.setOnItemClickListener(menuTable -> {
            startMainActivity(menuTable,this);
        });
        //MVVM
        menuTableViewModel = new ViewModelProvider(this).get(MenuTableViewModel.class);
        //TODO ACABAR DE IMPLEMENTAR O MVVM
        //setContentView(R.layout.tables_activity);

        //ImageView table1 = findViewById(R.id.table1);
        //ImageView table2 = findViewById(R.id.table2);
        //ImageView table3 = findViewById(R.id.table3);
        //Button table4 = findViewById(R.id.table4);
        //Button table5 = findViewById(R.id.table5);
        //Button table6 = findViewById(R.id.table6);

        //table1.setOnClickListener(buttonOnClick(1));
        //table2.setOnClickListener(buttonOnClick(2));
        //table3.setOnClickListener(buttonOnClick(3));
        //table4.setOnClickListener(buttonOnClick(4));
        //table5.setOnClickListener(buttonOnClick(5));
        //table6.setOnClickListener(buttonOnClick(6));
        button_api = findViewById(R.id.button_api);

        button_api.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAllMenuItems();
            }
        });
        TextView userName = findViewById(R.id.textViewUserName);

        if (userProfile != null) {
            userName.setText(userProfile.getName());
        }
        //TODO MVVM
        //menuTableViewModel

        //Bottom Navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_profile) {
                startActivity(new Intent(getApplicationContext(), UserProfileMain.class));
                finish();
                return true;
            }
            return false;
        });

        ImageView imageViewFries = findViewById(R.id.imageViewFries);
        Glide.with(this).load(Uri.parse("https://static.vecteezy.com/system/resources/previews/022/787/312/original/illustration-of-french-fries-in-box-transparent-background-generative-ai-png.png")).into(imageViewFries);


    }

    private void loadTables() {
        // Inicializar tablesAdapter
        tablesAdapter = new TablesAdapter(this);
        menuAdapter = new MenuAdapter(menuTable, new ArrayList<>());

        tableDao.getAll().observe(this, menuTableList -> {
            if (menuTableList != null) {
                // Inicializar menuTable
                menuTable = menuTableList.get(0);

                // Verifica se tablesAdapter já foi inicializado
                if (tablesAdapter != null) {
                    tablesAdapter.refreshList(menuTableList);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                    RecyclerView recyclerView = findViewById(R.id.RecyclerViewT);
                    recyclerView.setAdapter(tablesAdapter);
                    recyclerView.setLayoutManager(layoutManager);
                    menuItemDao = AppDatabase.getInstance(this).getMenuItemDao();
                    tablesAdapter.setOnItemClickListener(menuTable -> startMainActivity(menuTable, this));
                }
            }
        });
    }
    private View.OnClickListener buttonOnClick(long tableId) {
        return view -> startMainActivity(tableDao.getById(tableId), this);
    }

    public void updateProfileView() {
        AppDatabase db = AppDatabase.getInstance(this);
        UserProfileDao userProfileDao = db.getUserProfileDao();
        UserProfile userProfile = userProfileDao.getUserProfile();

        TextView userName = findViewById(R.id.textViewUserName);

        if (userProfile != null) {
            userName.setText("Olá! " + userProfile.getName());
        } else {
            userName.setText("Olá!");
        }
    }
    public void openAllMenuItems(){
        Intent intent = new Intent(this, AllMenuItems.class);
        startActivity(intent);
    }
    @Override
    protected void onResume(){
        super.onResume();
        updateProfileView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_PROFILE_REQUEST_CODE && resultCode == RESULT_OK){
            updateProfileView();

            loadTables();
        }
    }



}

