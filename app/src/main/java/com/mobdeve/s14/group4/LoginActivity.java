package com.mobdeve.s14.group4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;

    private EditText etEmail;
    private EditText etPassword;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth.AuthStateListener authStateListener;



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
        this.databaseReference = mDatabase.getReference("users");

    }

    private void initComponents(){
        this.btnLogin = findViewById(R.id.login_btn_login);
        this.etEmail = findViewById(R.id.login_et_email);
        this.etPassword = findViewById(R.id.login_et_password);


        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //testing purposes - matt
                Intent i = new Intent(LoginActivity.this, CreateRecipeActivity1.class);
                //this.btnNext.setVisibility(View.GONE);
                startActivity(i);
                finish();
                /*
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (!isEmpty(email, password)) {
                    //add user to db
                    signIn(email, password);
                }*/
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
        FirebaseUser fUser;
        User currUser;

        fUser = FirebaseAuth.getInstance().getCurrentUser();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    
                    Log.d("SIGNED IN", "onAuthStateChanged:signed_in:" + user.getUid());
                    Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    // User is signed out
                    Log.d("TAG", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };





    }

    private void failedLogin(){
        //this.btnNext.setVisibility(View.GONE);
        Toast.makeText(this, "FAIL", Toast.LENGTH_SHORT).show();

    }
}