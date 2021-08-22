package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private RecyclerView rvRecipes;
    private RecyclerView.LayoutManager manager;
    private ProfileAdapter adapter;

    private ArrayList<Recipe> data;

    private ImageView ivEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.initRecyclerView();
        this.initComponents();
    }

    private void initComponents(){
        ivEdit = findViewById(R.id.profile_iv_edit);

        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                ProfileActivity.this.startActivity(intent);
            }
        });
    }

    private void initRecyclerView() {

        rvRecipes = findViewById(R.id.profile_rv_recipes);

        manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvRecipes.setLayoutManager(manager);

        this.initData();
        adapter = new ProfileAdapter(data);
        rvRecipes.setAdapter(adapter);

    }

    private void initData() {
        data = new ArrayList<Recipe>();

        data.add(new Recipe (R.drawable.takoyaki, "Takoyaki", 0, 4.5, R.drawable.person_gray,  "John Doe", "Description", 10));
        data.add(new Recipe (R.drawable.adobo, "Adobo", 0, 4.5, R.drawable.person_gray,  "John Doe", "Description", 10));
        data.add(new Recipe (R.drawable.curry, "Curry", 0, 4.5, R.drawable.person_gray,  "John Doe", "Description", 10));
        data.add(new Recipe (R.drawable.ramen, "Ramen", 0, 4.5, R.drawable.person_gray,  "John Doe", "Description", 10));

    }
}