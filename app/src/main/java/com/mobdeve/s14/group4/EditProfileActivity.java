package com.mobdeve.s14.group4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.File;

public class EditProfileActivity extends AppCompatActivity {

    private Button btnEdit;

    private ImageView ivPic;

    private EditText etName;
    private EditText etUsername;
    private EditText etEmail;

    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private ImageView ivEmail;

    private Uri dataPic;

    public static final int IMAGE_GALLERY_REQUEST_EP = 21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        this.initComponents();
    }

    private void initComponents() {

        ivPic = findViewById(R.id.editprofile_iv_profile_pic);

        etName = findViewById(R.id.editprofile_et_name);
        etUsername = findViewById(R.id.editprofile_et_username);
        etEmail = findViewById(R.id.editprofile_et_email);
        ivEmail = findViewById(R.id.editprofile_iv_email);

        ivPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (Intent.ACTION_PICK);

                File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String path = pictureDirectory.getPath();

                dataPic = Uri.parse(path);

                i.setDataAndType(dataPic, "image/*");

                startActivityForResult(i, IMAGE_GALLERY_REQUEST_EP);
            }
        });


        btnEdit = findViewById(R.id.editprofile_btn_edit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etName.getText()) ||
                        TextUtils.isEmpty(etUsername.getText() )||
                        TextUtils.isEmpty(etEmail.getText())){

                    if(TextUtils.isEmpty(etName.getText()))
                        etName.setError("Name is Required");


                    if(TextUtils.isEmpty(etUsername.getText()))
                        etUsername.setError("Username is Required");


                    if(TextUtils.isEmpty(etEmail.getText()))
                        etEmail.setError("Email is Required");


                    Toast.makeText(EditProfileActivity.this, "Fill Up All Values", Toast.LENGTH_LONG).show();
                }
                else{
                    String fname, lname, username, email;
                    String [] fullName;

                    fullName = etName.getText().toString().trim().split(" ");

                    Toast.makeText(EditProfileActivity.this, "Fill Up All Values", Toast.LENGTH_LONG).show();


                    finish();
                }
            }
        });

        loadInfo();
    }

    private void loadInfo() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        Log.d("PROFILE SIGNED IN", "onAuthStateChanged:signed_in:" + user.getUid());
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());

        databaseReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String fname, lname, username, email;
                fname = dataSnapshot.child("firstName").getValue(String.class);
                lname = dataSnapshot.child("lastName").getValue(String.class);
                username = dataSnapshot.child("username").getValue(String.class);
                email = dataSnapshot.child("email").getValue(String.class);

                if (email == null){
                    etEmail.setVisibility(View.GONE);
                    ivEmail.setVisibility(View.GONE);

                }
                else{
                    etEmail.setText(email);
                }

                String fullName = fname + " " + lname;
                etName.setText(fullName);
                etUsername.setText(username);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("FAIL TAG", "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == IMAGE_GALLERY_REQUEST_EP){
                dataPic = data.getData();
                Picasso.with(this).load(dataPic).into(ivPic);
            }
            else{
                finish();
            }
        }
        else {
            finish();
        }
    }
}