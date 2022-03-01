package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AdminAccessActivity extends AppCompatActivity {

    private FloatingActionButton fabAddAdmin;

    private ImageButton ibBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_access);

        initComponents();
    }

    private void initComponents() {

        fabAddAdmin = findViewById(R.id.fab_add_admin);

        ibBack = findViewById(R.id.ib_admin_back);

        fabAddAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminAccessActivity.this, AddAdminActivity.class);
                startActivity(i);
            }
        });

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}