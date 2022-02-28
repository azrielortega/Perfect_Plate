package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvContactNo;
    private TextView tvStreet;
    private TextView tvAddress;

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

        User user = DataHelper.user;
        String address = user.getAddress().getCity() + ", " + user.getAddress().getState() + ", " + user.getAddress().getPostalCode();

        tvName.setText(user.getFullName());
        tvContactNo.setText(user.getContactNo());
        tvStreet.setText(user.getAddress().getStreet());
        tvAddress.setText(address);
    }
}