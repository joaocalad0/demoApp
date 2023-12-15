package com.example.onrequest;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onrequest.schema.db.AppDatabase;

import java.util.List;

public class CreateAccount extends AppCompatActivity {

    private EditText editTextTextUser;
    private EditText editTextTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        editTextTextUser = findViewById(R.id.editTextTextUser);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);

        Button buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();

            }
        });
    }

    private void register() {
        String inputUserName = editTextTextUser.getText().toString();
        String inputPassword = editTextTextPassword.getText().toString();

        if (TextUtils.isEmpty(inputUserName) || TextUtils.isEmpty(inputPassword)) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        AppDatabase db = AppDatabase.getInstance(this);
        UserDao userDao = db.getUserDao();

        List<User> existingUsers = userDao.getAllUsers();
        for (User user : existingUsers) {
            if (user.getUserName().equals(inputUserName)) {
                Toast.makeText(this, "Este nome já existe", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        User newUser = new User();
        newUser.setUserName(inputUserName);
        newUser.setPassword(inputPassword);

        userDao.insertUser(newUser);

        Toast.makeText(this, "Registro feito com sucesso", Toast.LENGTH_SHORT).show();
        finish();
    }
}