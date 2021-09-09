package com.mobdeve.s14.group4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity2 extends AppCompatActivity {
    private Button btnSignUp;
    private String email;
    private String password;
    private String username;

    private String fname;
    private String lname;
    private EditText etFirstN;
    private EditText etLastN;

    private ProgressBar pbSignup;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initFirebase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        this.btnSignUp = findViewById(R.id.signup2_btn_signup);

        this.etFirstN = findViewById(R.id.signup2_et_fname);
        this.etLastN = findViewById(R.id.signup2_et_lname);

        this.pbSignup = findViewById(R.id.pb_signup);

        Intent i = getIntent();

        email = i.getStringExtra(SignUpActivity1.KEY_EMAIL);
        password = i.getStringExtra(SignUpActivity1.KEY_PASSWORD);
        username = i.getStringExtra(SignUpActivity1.KEY_USERNAME);

        Log.d("TEST EMAIL SIGN UP 2", email);
        Log.d("TEST PASSWORD SIGN UP 2", password);
        Log.d("TEST USERNAME SIGN UP 2", username);

        this.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fname = etFirstN.getText().toString().trim();
                lname = etLastN.getText().toString().trim();

                User user = new User(email, password, username, fname, lname);
                if (validateUser(user)){
                    //add user to db

//                    storeUser(user);
                }
            }
        });

    }



    private boolean validateUser(User user){
        boolean isValidUser = true;

        //validate email
        if(user.getEmail().isEmpty()){
            isValidUser = false;
        }

        //validate password
        if (user.getPassword().isEmpty()){
            isValidUser = false;
        }

        //validate username
        if (user.getUsername().isEmpty()){
            isValidUser = false;
        }

        //validate first name
        if (user.getFirstName().isEmpty()){
            isValidUser = false;
        }

        //validate last name
        if (user.getLastName().isEmpty()){
            isValidUser = false;
        }

        return isValidUser;
    }


    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    private void storeUser(User u){
        this.btnSignUp.setVisibility(View.VISIBLE);

        this.pbSignup.setVisibility(View.VISIBLE);
        Log.d("TEST EMAIL STORE", email);
        Log.d("TEST PASSWORD STORE", password);
        Log.d("TEST USERNAME STORE", username);
        Log.d("TEST FNAME STORE", fname);
        Log.d("TEST LNAME STORE", lname);

        Log.d("TEST GETEMAIL STORE", u.getEmail());
        Log.d("TEST GETEMAIL PASSWORD", u.getPassword());

        //register to firebase
        this.mAuth.createUserWithEmailAndPassword(u.getEmail(), u.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            mDatabase.getReference("users")
                                    .child(mAuth.getCurrentUser().getUid())
                                    .setValue(u.getFirebaseUser()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        successfulRegistration();
                                    } else {
                                        failedRegistration();
                                    }
                                }
                            });
                        } else {
                            failedRegistration();
                        }
                    }
                });
    }

    private void successfulRegistration(){
        Toast.makeText(this, "User Registration Successful", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(SignUpActivity2.this, LoginActivity.class);
        this.btnSignUp.setVisibility(View.GONE);
        startActivity(i);
        finish();
    }

    private void failedRegistration(){
        this.btnSignUp.setVisibility(View.GONE);
        Toast.makeText(this, "FAIL", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(SignUpActivity2.this, WriteReviewActivity.class);

    }

    private void initFirebase() {
        this.mAuth = FirebaseAuth.getInstance();
        this.mDatabase = FirebaseDatabase.getInstance();
    }
}