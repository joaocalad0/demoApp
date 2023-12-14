package com.example.onrequest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText userName;
    private  EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Button button = findViewById(R.id.button);
         userName = findViewById(R.id.userName);
         password = findViewById(R.id.password);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void submit(View view) {
        Intent submit = new Intent(this, TablesActivity.class);
        startActivity(submit);
    }

    public void forgot(View view) {
        Intent forgot = new Intent(this, CreateAccount.class);
        startActivity(forgot);
    }


    //TODO
    private void login(){
        //String userName = user

    }
}