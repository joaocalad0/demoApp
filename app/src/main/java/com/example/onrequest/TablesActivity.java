package com.example.onrequest;

import static com.example.onrequest.MainActivity.startMainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onrequest.schema.dao.MenuTableDao;
import com.example.onrequest.schema.dao.UserProfileDao;
import com.example.onrequest.schema.db.AppDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TablesActivity extends AppCompatActivity {

    private MenuTableDao tableDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tableDao = AppDatabase.getInstance(this).getMenuTableDao();

        setContentView(R.layout.tables_activity);

        Button table1 = findViewById(R.id.table1);
        Button table2 = findViewById(R.id.table2);
        Button table3 = findViewById(R.id.table3);
        Button table4 = findViewById(R.id.table4);
        Button table5 = findViewById(R.id.table5);
        Button table6 = findViewById(R.id.table6);

        table1.setOnClickListener(buttonOnClick(1));
        table2.setOnClickListener(buttonOnClick(2));
        table3.setOnClickListener(buttonOnClick(3));
        table4.setOnClickListener(buttonOnClick(4));
        table5.setOnClickListener(buttonOnClick(5));
        table6.setOnClickListener(buttonOnClick(6));


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


        AppDatabase db = AppDatabase.getInstance(this);
        UserProfileDao userProfileDao = db.getUserProfileDao();

        String userName = getIntent().getStringExtra("user_name");
        TextView textViewUserName = findViewById(R.id.textViewUserName);

        if (userName != null){
            textViewUserName.setText(userName);
        }


    }

    private View.OnClickListener buttonOnClick(long tableId) {
        return view -> startMainActivity(tableDao.getById(tableId), this);
    }

}