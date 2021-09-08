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
    private ArrayList<Recipe> data;

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

        initData();
        adapter = new FavoriteAdapter(data);
        rvRecipes.setAdapter(adapter);
    }

    private void initData() {
        data = new ArrayList<Recipe>();

        data.add(new Recipe (R.drawable.takoyaki, "Takoyaki", 0, 4.5, "John Doe", "Description", 10, 0, 0 ,0, "Main", "Easy"));
        data.add(new Recipe (R.drawable.adobo, "Adobo", 0, 4.5, "John Doe", "Description", 10, 0, 0 ,0, "Main", "Easy"));
        data.add(new Recipe (R.drawable.curry, "Curry", 0, 4.5, "John Doe", "Description", 10, 0, 0 ,0, "Main", "Easy"));
        data.add(new Recipe (R.drawable.ramen, "Ramen", 0, 4.5, "John Doe", "Description", 10, 0, 0 ,0, "Main", "Easy"));
    }
}