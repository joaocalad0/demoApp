package com.example.onrequest;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.onrequest.schema.dao.UserProfileDao;
import com.example.onrequest.schema.db.AppDatabase;
import com.example.onrequest.schema.entity.UserProfile;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class EditUserProfile extends AppCompatActivity {

    private EditText editTextname;
    private EditText editTextemail;
    private ImageView imageViewEditPhoto;
    private Button buttonApply;

    private Uri selectedImageUri;

    private final int GALLERY_REQUEST_CODE = 1000;

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
            selectedImageUri = result.getData().getData();
            if (selectedImageUri != null) {
                Glide.with(this).load(selectedImageUri).into(imageViewEditPhoto);
            } else {
                Toast.makeText(this, "Erro ao obter imagem", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Nenhuma imagem selecionada", Toast.LENGTH_SHORT).show();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        editTextname = findViewById(R.id.editTextname);
        editTextemail = findViewById(R.id.editTextemail);
        imageViewEditPhoto = findViewById(R.id.imageViewEditPhoto);
        buttonApply = findViewById(R.id.buttonApply);

        FloatingActionButton pickImage = findViewById(R.id.pickImage);
        pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLERY_REQUEST_CODE);
            }
        });

        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyChanges();
            }
        });


    }

    private void applyChanges() {
        String name = editTextname.getText().toString();
        String email = editTextemail.getText().toString();

        AppDatabase db = AppDatabase.getInstance(this);
        UserProfileDao userProfileDao = db.getUserProfileDao();
        UserProfile existingProfile = userProfileDao.getUserProfile();

        if (existingProfile != null) {
            existingProfile.setName(name);
            existingProfile.setMail(email);

            if (selectedImageUri != null) {
                existingProfile.setPhoto(selectedImageUri.toString());
            }


            userProfileDao.update(existingProfile);
        } else {
            UserProfile userProfile = new UserProfile(0, name, "", email);
            if (selectedImageUri != null) {
                userProfile.setPhoto(selectedImageUri.toString());
            }
            userProfileDao.insert(userProfile);
        }
        //finish();
        setResult(RESULT_OK);
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}