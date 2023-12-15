package com.example.onrequest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.onrequest.schema.dao.UserProfileDao;
import com.example.onrequest.schema.db.AppDatabase;
import com.example.onrequest.schema.entity.UserProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserProfileMain extends AppCompatActivity {

    private ImageView imageViewProfilePhoto;
    private TextView textViewName;
    private TextView textViewEmail;
    private Button editButton;

    private Button buttonLocalization;
    private Button button_delivery;


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
        buttonLocalization= findViewById(R.id.buttonLocalization);
        button_delivery = findViewById(R.id.button_delivery);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditProfile();

            }
        });

        buttonLocalization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openReceipt();
            }
        });

        button_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDelivery();
            }
        });




        //Bottom Navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), TablesActivity.class));
                finish();
                return true;
            }
            return false;
        });
    }





    public void openEditProfile(){
        Intent intent = new Intent(this, EditUserProfile.class);
        startActivity(intent);
    }

    public void openReceipt(){
        Intent intent = new Intent(this, ReceiptActivity.class);
        startActivity(intent);
    }

    public void openDelivery(){
        Intent intent = new Intent(this, StartPoint.class);
        startActivity(intent);
    }

    public void updateProfileView(){
        AppDatabase db = AppDatabase.getInstance(this);
        UserProfileDao userProfileDao = db.getUserProfileDao();
        UserProfile userProfile = userProfileDao.getUserProfile();

        if (userProfile != null){
            textViewName.setText(userProfile.getName());
            textViewEmail.setText(userProfile.getMail());

            String photoUri = userProfile.getPhoto();
            if (photoUri != null){
                Glide.with(this).load(Uri.parse(photoUri)).into(imageViewProfilePhoto);
            } else {
                imageViewProfilePhoto.setImageDrawable(null);
                Toast.makeText(this, "Imagem nula", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        updateProfileView();
    }
}