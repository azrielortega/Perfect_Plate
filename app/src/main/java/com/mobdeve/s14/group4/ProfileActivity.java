package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvContactNo;
    private TextView tvStreet;
    private TextView tvAddress;

    private LinearLayout llCart;
    private LinearLayout llSearch;

    private ConstraintLayout clAdmin;

    private Button btnLogout;

    private ImageButton ibEditProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initComponents();
        initializeUser();
    }
    private void initComponents() {
        //TEXT VIEWS
        tvName = findViewById(R.id.tv_profile_name);
        tvContactNo = findViewById(R.id.tv_profile_number);
        tvStreet = findViewById(R.id.tv_profile_street);
        tvAddress = findViewById(R.id.tv_profile_city_province_postal);

        //LINEAR LAYOUT
        llCart = findViewById(R.id.ll_cart);
        llSearch = findViewById(R.id.ll_search);

        //CONSTRAINT LAYOUT
        clAdmin = findViewById(R.id.cl_profile_admin_func);

        //BUTTONS
        btnLogout = findViewById(R.id.btn_logout);

        //IMAGE BUTTONS
        ibEditProfile = findViewById(R.id.ib_profile_edit);

        User user = DataHelper.user;
        String address = user.getAddress().getCity() + ", " + user.getAddress().getState() + ", " + user.getAddress().getPostalCode();

        tvName.setText(user.getFullName());
        tvContactNo.setText(user.getContactNo());
        tvStreet.setText(user.getAddress().getStreet());
        tvAddress.setText(address);

        this.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLogout.setEnabled(false);
                FirebaseAuth.getInstance().signOut();

                Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        this.ibEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(i);
            }
        });

        this.llSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        this.llCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, AddToCartActivity.class);
                startActivity(i);
            }
        });
    }

    private void initializeUser(){
        DataHelper.userDatabase.getUser(DataHelper.user.getUserId(), new CallbackListener() {
            @Override
            public void onSuccess(Object o) { //If user exists
                User user = (User) o;
                DataHelper.user = user;

                if(!user.isAdmin()){
                    clAdmin.setVisibility(View.GONE);
                }

                String address = user.getAddress().getCity() + ", " + user.getAddress().getState() + ", " + user.getAddress().getPostalCode();

                tvName.setText(user.getFullName());
                tvContactNo.setText(user.getContactNo());
                tvStreet.setText(user.getAddress().getStreet());
                tvAddress.setText(address);
            }

            @Override
            public void onFailure() { //If user does not exist.
                Toast.makeText(ProfileActivity.this, "User does not exist, Log in again", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Check if there were realtime updates to user made by other users
        initializeUser();
    }
}