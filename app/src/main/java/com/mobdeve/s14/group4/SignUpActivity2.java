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

public class SignUpActivity2 extends AppCompatActivity {
    private Button btnSignUp;
    private String email;
    private String password;
    private String username;

    private EditText etFirstN;
    private EditText etLastN;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        this.btnSignUp = findViewById(R.id.signup2_btn_signup);

        this.etFirstN = findViewById(R.id.signup2_et_fname);
        this.etLastN = findViewById(R.id.signup2_et_lname);

        this.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();

                email = i.getStringExtra(SignUpActivity1.KEY_EMAIL);
                password = i.getStringExtra(SignUpActivity1.KEY_PASSWORD);
                username = i.getStringExtra(SignUpActivity1.KEY_USERNAME);
//                email = "hehe@gmail.com";
//                password = "hehehe";
//                username = "hehehe";

                String fname = etFirstN.getText().toString().trim();
                String lname = etLastN.getText().toString().trim();

                if (!isEmpty(email, password, username, fname, lname)) {
                    //add user to db
                    //User user = new User(email, password, username, fname, lname);
                    User user = new User(fname, lname);
                    storeUser(user);
                }
            }
        });

    }


//SET FUNCTION HERE
    private boolean isEmpty(String email, String password, String username, String fname, String lname){

        if(fname.isEmpty()){
//            this.et.setError("Required field");
//           this.etEmail.requestFocus();

            return true;
        }
        return false;
    }


    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    private void storeUser(User u){
        this.btnSignUp.setVisibility(View.VISIBLE);

        //register to firebase
        this.mAuth.createUserWithEmailAndPassword(email, password)
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
}