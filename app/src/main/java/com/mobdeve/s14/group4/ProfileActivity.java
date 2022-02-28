package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvContactNo;
    private TextView tvStreet;
    private TextView tvAddress;

    private LinearLayout llCart;
    private LinearLayout llSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initComponents();
    }

    private void initComponents() {
        tvName = findViewById(R.id.tv_profile_name);
        tvContactNo = findViewById(R.id.tv_profile_number);
        tvStreet = findViewById(R.id.tv_profile_street);
        tvAddress = findViewById(R.id.tv_profile_city_province_postal);

        llCart = findViewById(R.id.ll_cart);
        llSearch = findViewById(R.id.ll_search);

        User user = DataHelper.user;
        String address = user.getAddress().getCity() + ", " + user.getAddress().getState() + ", " + user.getAddress().getPostalCode();

        tvName.setText(user.getFullName());
        tvContactNo.setText(user.getContactNo());
        tvStreet.setText(user.getAddress().getStreet());
        tvAddress.setText(address);

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
}