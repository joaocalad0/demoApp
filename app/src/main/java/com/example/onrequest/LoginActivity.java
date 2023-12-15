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

public class LoginActivity extends AppCompatActivity {
    private EditText userName;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Button button = findViewById(R.id.button);
        Button signin = findViewById(R.id.signin);
        userName = findViewById(R.id.loginUserName);
        password = findViewById(R.id.loginPassword);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //submit(view);
                login();

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
            }
        });
    }

    private void login() {
        String inputUserName = userName.getText().toString();
        String inputPassword = password.getText().toString();

        if (TextUtils.isEmpty(inputUserName) || TextUtils.isEmpty(inputPassword)) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        AppDatabase db = AppDatabase.getInstance(this);
        UserDao userDao = db.getUserDao();

        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            if (user.getUserName().equals(inputUserName) && user.getPassword().equals(inputPassword)) {
                Toast.makeText(this, "Login bem-sucedido", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, TablesActivity.class);
                startActivity(intent);
                submit();
                return;
            }
        }
        Toast.makeText(this, "Nome de utilizador ou senha incorreta", Toast.LENGTH_SHORT).show();
    }

    public void submit() {
        Intent submit = new Intent(this, TablesActivity.class);
        startActivity(submit);
    }

    public void signin() {
        Intent forgot = new Intent(this, CreateAccount.class);
        startActivity(forgot);
    }


    //TODO

}