package com.sheenjoshuaamol.parkingmanagerdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import com.google.android.material.badge.BadgeUtils;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class LoginAccount extends AppCompatActivity {


    FirebaseAuth mAuth;
    FirebaseAnalytics analytics;
    EditText email, password;
    Button submit, register;
    ProgressBar load;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Toast.makeText(this, "ALREADY LOGGED IN!", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_account);


        mAuth = FirebaseAuth.getInstance();
        analytics = FirebaseAnalytics.getInstance(this);

        email = findViewById(R.id.etLoginEmail);
        password = findViewById(R.id.etLoginPassword);
        submit = findViewById(R.id.submit);
        load = findViewById(R.id.progressBar2);
        register = findViewById(R.id.register);




        findViewById(R.id.switchmode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAccount.this, SwitchMode.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAccount.this, RegisterAccount.class));
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inpemail, inppassword;

                inpemail = String.valueOf(email.getText());
                inppassword = String.valueOf(password.getText());

                if (TextUtils.isEmpty(inpemail)) {
                    Toast.makeText(LoginAccount.this, "Please input your Email", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(inppassword)) {
                    Toast.makeText(LoginAccount.this, "Please input your Password", Toast.LENGTH_SHORT).show();
                } else {
                    load.setVisibility(View.VISIBLE);
                    //FIREBASEAUTH BOILERPLATE HERE


                    mAuth.signInWithEmailAndPassword(inpemail, inppassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                analytics.logEvent("logInWithEmail_success", null);
                                Toast.makeText(LoginAccount.this, "Log In success, redirecting to Operator Screen", Toast.LENGTH_SHORT).show();
                                new Timer().schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        startActivity(new Intent(LoginAccount.this, OperatorActivity.class));
                                        load.setVisibility(View.INVISIBLE);
                                    }
                                }, 2000);

                            } else {
                                // If sign in fails, display a message to the user.
                                analytics.logEvent("logInWithEmail_failure", null);
                                Toast.makeText(LoginAccount.this, "Log In failed.", Toast.LENGTH_SHORT).show();
                                load.setVisibility(View.INVISIBLE);
                            }
                        }
                    });


                }
            }
        });


    }
}