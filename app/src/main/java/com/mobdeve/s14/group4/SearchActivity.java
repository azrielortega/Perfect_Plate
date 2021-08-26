package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class SearchActivity extends AppCompatActivity {

    private LinearLayout llProfile;
    private LinearLayout llHome;
    private LinearLayout llCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initComponents();


    }

    private void initComponents() {
        this.llProfile = findViewById(R.id.ll_profile);
        this.llHome = findViewById(R.id.ll_home);
        llCreate = findViewById(R.id.ll_create);

        this.llProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });

        this.llHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        this.llCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchActivity.this, CreateRecipeActivity1.class);
                startActivity(i);
            }
        });
    }
}