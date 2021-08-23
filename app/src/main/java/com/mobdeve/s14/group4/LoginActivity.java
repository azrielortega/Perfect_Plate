package com.mobdeve.s14.group4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;

    private EditText etEmail;
    private EditText etPassword;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.initComponents();
        this.initFirebase();
    }
    private void initFirebase(){
        this.mDatabase = FirebaseDatabase.getInstance();
        this.mAuth = FirebaseAuth.getInstance();
    }

    private void initComponents(){
        this.btnLogin = findViewById(R.id.login_btn_login);
        this.etEmail = findViewById(R.id.login_et_email);
        this.etPassword = findViewById(R.id.login_et_password);


        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (!isEmpty(email, password)) {
                    //add user to db
                    signIn(email, password);
                }
            }
        });
    }

    private boolean isEmpty(String e, String p){

        if(e.isEmpty()){
            this.etEmail.setError("Required field");
            this.etEmail.requestFocus();
            return true;
        }
        return false;
    }

    private void signIn(String email, String password) {
        // this.pbLogin.setVisibility(View.VISIBLE); ADD PROGRESS BAR LATER

        this.mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           sucessfulLogin();
                        } else {
                            failedLogin();
                        }
                    }
                });
    }
    private void sucessfulLogin(){

        Toast.makeText(this, "User Registration Successful", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
        //this.btnNext.setVisibility(View.GONE);
        startActivity(i);
        finish();
    }

    private void failedLogin(){
        //this.btnNext.setVisibility(View.GONE);
        Toast.makeText(this, "FAIL", Toast.LENGTH_SHORT).show();

    }





}