package com.sheenjoshuaamol.parkingmanagerdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.badge.BadgeUtils;

public class LoginAccount extends AppCompatActivity {



    EditText login, password;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_account);

        login = findViewById(R.id.etLoginEmail);
        password = findViewById(R.id.etLoginPassword);
        submit = findViewById(R.id.submit);




    }
}