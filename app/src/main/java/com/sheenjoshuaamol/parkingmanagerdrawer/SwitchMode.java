package com.sheenjoshuaamol.parkingmanagerdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

public class SwitchMode extends AppCompatActivity {
    Button op;
    Button cust;
    FirebaseAnalytics analytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences SP = getSharedPreferences("SwitchCheck", MODE_PRIVATE);
        //IMPORTANT
        setContentView(R.layout.activity_switch_mode);
        //IMPORTANT
        analytics = FirebaseAnalytics.getInstance(this);




        op = findViewById(R.id.button);
        cust = findViewById(R.id.button2);



        op.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Operate", "OP");
                startActivity(new Intent(SwitchMode.this, RegisterAccount.class));
                SP.edit().putInt("Switch", 1).apply();
                Log.d("Testswitch", "onCreate: "+SP.getInt("Switch", 3));
                analytics.logEvent("OPERATOR_MODE", null);
            }
        });
        cust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Customer", "CUST");
                startActivity(new Intent(SwitchMode.this, MainActivity.class));
                SP.edit().putInt("Switch", 0).apply();
                Log.d("Testswitch", "onCreate: "+SP.getInt("Switch", 3));
                analytics.logEvent("CUSTOMER_MODE", null);
            }
        });

    }
}