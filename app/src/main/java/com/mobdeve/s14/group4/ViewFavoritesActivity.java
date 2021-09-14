package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class ViewFavoritesActivity extends AppCompatActivity {

    private RecyclerView rvRecipes;
    private RecyclerView.LayoutManager manager;
    private FavoriteAdapter adapter;

    private ImageButton ibBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfavorites);
        
        initRecyclerView();
        initComponents();
    }

    private void initComponents() {

        ibBack = findViewById(R.id.ib_favorite_back);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initRecyclerView() {
        rvRecipes = findViewById(R.id.viewfavorites_rv_recipes);

        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvRecipes.setLayoutManager(manager);

        adapter = new FavoriteAdapter(DataHelper.user.getFaveRecipes());
        rvRecipes.setAdapter(adapter);
    }
}