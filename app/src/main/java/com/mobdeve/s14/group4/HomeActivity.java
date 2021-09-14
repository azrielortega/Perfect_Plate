package com.mobdeve.s14.group4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rvPopular;
    private ArrayList<Recipe> popularRecipeList;
    private RecyclerView rvRecent;
    private ArrayList<Recipe> recentRecipeList;

    private RecyclerView.LayoutManager popularManager;
    private PopularAdapter popularAdapter;
    private RecyclerView.LayoutManager recentManager;
    private RecentAdapter recentAdapter;

    private LinearLayout llProfile;
    private LinearLayout llSearch;
    private LinearLayout llCreate;
    private LinearLayout llFavorites;

    private ImageView ivSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d("HELLO", "HELLO");

        this.initComponents(); // init components and load data

        this.initPopularRecyclerView();
        this.initRecentFeed();
    }

    private void initComponents(){
        this.llProfile = findViewById(R.id.ll_profile);
        this.llSearch = findViewById(R.id.ll_search);
        llCreate = findViewById(R.id.ll_create);
        ivSearch = findViewById(R.id.iv_search);
        llFavorites = findViewById(R.id.ll_fav);

        llFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (HomeActivity.this, ViewFavoritesActivity.class);
                startActivity(i);
            }
        });

        this.llProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });

        this.llSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });

        this.llCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, CreateRecipeActivity1.class);
                startActivity(i);
            }
        });
    }

    private void initPopularRecyclerView(){
        this.popularRecipeList = DataHelper.sortedRecipes;
        Log.d("SIZE FINAL", String.valueOf(this.popularRecipeList.size()));
        this.rvPopular = findViewById(R.id.rv_home_popular);

        this.popularManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        this.rvPopular.setLayoutManager(this.popularManager);

        this.popularAdapter = new PopularAdapter(this.popularRecipeList);
        this.rvPopular.setAdapter(this.popularAdapter);
    }

    private void initRecentFeed(){
        this.recentRecipeList = DataHelper.allRecipes;
        this.rvRecent = findViewById(R.id.rv_home_recent);

        this.recentManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.rvRecent.setLayoutManager(recentManager);

        this.recentAdapter = new RecentAdapter(this.recentRecipeList);
        this.rvRecent.setAdapter(recentAdapter);
    }

}