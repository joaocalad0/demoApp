package com.example.onrequest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onrequest.schema.dao.UserProfileDao;
import com.example.onrequest.schema.db.AppDatabase;

public class UserProfileMain extends AppCompatActivity {

    private ImageView imageViewProfilePhoto;
    private TextView textViewName;
    private TextView textViewEmail;
    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_main);

        AppDatabase db = AppDatabase.getInstance(this);
        UserProfileDao userProfileDao = db.getUserProfileDao();

        imageViewProfilePhoto = findViewById(R.id.imageViewProfilePhoto);
        textViewName = findViewById(R.id.textViewName);
        textViewEmail = findViewById(R.id.textViewEmail);
        editButton = findViewById(R.id.button_edit);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditProfile();

            }
        });


    }

    public void openEditProfile(){
        Intent intent = new Intent(this, EditUserProfile.class);
        startActivity(intent);
    }
}