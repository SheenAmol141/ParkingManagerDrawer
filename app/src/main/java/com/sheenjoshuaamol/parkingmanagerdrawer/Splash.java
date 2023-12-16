package com.sheenjoshuaamol.parkingmanagerdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;



public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();


        SharedPreferences SP = getSharedPreferences("First Open", MODE_PRIVATE);

        SharedPreferences Sa = getSharedPreferences("SwitchCheck", MODE_PRIVATE);
        Log.d("Testswitch", "onCreate: "+Sa.getInt("Switch", 3));

        TimerTask timer = new TimerTask() {
            @Override
            public void run() {


                    if (Sa.getInt("Switch", 3) == 1) {
                        startActivity(new Intent(Splash.this, LoginAccount.class));
                    } else if (Sa.getInt("Switch", 3) == 0) {
                        startActivity(new Intent(Splash.this, MainActivity.class));
                    } else {
                        startActivity(new Intent(Splash.this, OnboardingActivity.class));
                        SP.edit().putBoolean("opened", true).apply();
                    }
            }
        };
        new Timer().schedule(timer, 2000);
    }
}