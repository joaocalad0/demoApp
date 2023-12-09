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

    private static final int REQUEST_SELECT_GALLERY = 2;
    private static final int RESULT_PROFILE_CREATED = 3;

    private EditText editTextName;
    private EditText editTextEmail;
    private ImageView imageViewEditPhoto;
    private Button buttonApply;

    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        // Cache Views
        editTextName = findViewById(R.id.editTextname);
        editTextEmail = findViewById(R.id.editTextemail);
        imageViewEditPhoto = findViewById(R.id.imageViewEditPhoto);
        buttonApply = findViewById(R.id.buttonApply);

        FloatingActionButton pickImage = findViewById(R.id.pickImage);
        pickImage.setOnClickListener(view -> openGallery());

        buttonApply.setOnClickListener(view -> applyChanges());
    }

    private void applyChanges() {
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String photoUri = selectedImageUri != null ? selectedImageUri.toString() : "";

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

        showMessage("Perfil Atualizado");
        setResult(RESULT_PROFILE_CREATED);
        finish();
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_SELECT_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (uri != null && requestCode == REQUEST_SELECT_GALLERY) {
                selectedImageUri = uri;
                Glide.with(this).load(selectedImageUri).into(imageViewEditPhoto);
            }
        }
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}