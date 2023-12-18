package com.sheenjoshuaamol.parkingmanagerdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class RegisterAccount extends AppCompatActivity {

    EditText email, password, confirmpassword, master;
    Button submit;
    ProgressBar load;
    FirebaseAnalytics analytics = FirebaseAnalytics.getInstance(this);
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        getSupportActionBar().hide();

        SharedPreferences SP = getSharedPreferences("MASTER", MODE_PRIVATE);

        password = findViewById(R.id.etRegisterPassword);
        confirmpassword = findViewById(R.id.etRegisterConfirmPassword);
        email = findViewById(R.id.etRegisterEmail);
        master = findViewById(R.id.etMaster);
        submit = findViewById(R.id.submit);
        load = findViewById(R.id.progressBar);


        findViewById(R.id.switchmode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterAccount.this, SwitchMode.class));
            }
        });
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterAccount.this, LoginAccount.class));
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inpemail, inppassword, inpconfirm, inpmaster;
                inpemail = String.valueOf(email.getText());
                inppassword = String.valueOf(password.getText());
                inpconfirm = String.valueOf(confirmpassword.getText());
                inpmaster = String.valueOf(master.getText());
                mAuth = FirebaseAuth.getInstance();

                if (TextUtils.isEmpty(inpemail)) {
                    Toast.makeText(RegisterAccount.this, "Please input your Email", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(inppassword)) {
                    Toast.makeText(RegisterAccount.this, "Please input your Password", Toast.LENGTH_SHORT).show();
                } else if (!(inppassword.length() >= 8)) {
                    Toast.makeText(RegisterAccount.this, "Please insert minimum of 8 characters in your Password!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(inpconfirm)) {
                    Toast.makeText(RegisterAccount.this, "Please input your Confirm Password", Toast.LENGTH_SHORT).show();
                } else if(!inpconfirm.equals(inppassword)) {
                    Toast.makeText(RegisterAccount.this, "Please make sure that your Password and Confirm Password is identical", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(inpmaster)) {
                    Toast.makeText(RegisterAccount.this, "Please input the Master Key", Toast.LENGTH_SHORT).show();
                } else if (!(inpmaster.equals(SP.getString("key", "0323")))) {
                    Toast.makeText(RegisterAccount.this, "Master Key Incorrect!", Toast.LENGTH_SHORT).show();
                    master.setText("");
                } else {
                    load.setVisibility(View.VISIBLE);
                    //INSERT AUTH BOILERPLATE


                    mAuth.createUserWithEmailAndPassword(inpemail, inppassword)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success
                                        analytics.logEvent("createUserWithEmail_success", null);
                                        Toast.makeText(RegisterAccount.this, "Registered Successfully, redirecting to Operator Screen", Toast.LENGTH_SHORT).show();

                                        TimerTask timer = new TimerTask() {
                                            @Override
                                            public void run() {
                                                load.setVisibility(View.INVISIBLE);
                                                startActivity(new Intent(RegisterAccount.this, LoginAccount.class));
                                            }
                                        };
                                        new Timer().schedule(timer, 3000);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        analytics.logEvent("createUserWithEmail_failure", null);
                                        Toast.makeText(RegisterAccount.this, "Register Failed.", Toast.LENGTH_SHORT).show();
                                        load.setVisibility(View.INVISIBLE);
                                    }
                                }
                            });

                }
            }



    });
}}