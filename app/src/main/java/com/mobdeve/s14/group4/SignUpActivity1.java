package com.mobdeve.s14.group4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class SignUpActivity1 extends AppCompatActivity {

    private Button btnNext;

    private TextView tvSignIn;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;

    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

        btnNext = findViewById(R.id.signup1_btn_next);
        tvSignIn = findViewById(R.id.signup1_tv_signin);
        etEmail = findViewById(R.id.signup1_et_email);
        etPassword = findViewById(R.id.signup1_et_password);


        this.initFirebase();

        this.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (!isEmpty(email, password)) {
                    //add user to db
                    User user = new User(email, password);
                    storeUser(user);
                }

//                Intent intent = new Intent(SignUpActivity1.this, SignUpActivity2.class);
//                SignUpActivity1.this.startActivity(intent);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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

    private void initFirebase(){
        this.mAuth = FirebaseAuth.getInstance();
        this.mDatabase = FirebaseDatabase.getInstance();
    }

    private void storeUser(User u){
        this.btnNext.setVisibility(View.VISIBLE);

        //register to firebase
        this.mAuth.createUserWithEmailAndPassword(u.getEmail(), u.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            mDatabase.getReference("users")
                                    .child(mAuth.getCurrentUser().getUid())
                                    .setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
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
        Intent i = new Intent(SignUpActivity1.this, LoginActivity.class);
        this.btnNext.setVisibility(View.GONE);
        startActivity(i);
        finish();
    }

    private void failedRegistration(){
        this.btnNext.setVisibility(View.GONE);
        Toast.makeText(this, "FAIL", Toast.LENGTH_SHORT).show();

    }

}