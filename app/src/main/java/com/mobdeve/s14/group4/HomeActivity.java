package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d("HELLO", "HELLO");

        this.initRecyclerView();
        this.initRecentFeed();
        this.initComponents();
    }

    private void initComponents(){
        this.llProfile = findViewById(R.id.ll_profile);
        this.llSearch = findViewById(R.id.ll_search);

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
    private void initRecyclerView(){

        DataHelper helper = new DataHelper();
        this.popularRecipeList = helper.initFood();
        this.rvPopular = findViewById(R.id.rv_home_main);

        this.popularManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        this.rvPopular.setLayoutManager(this.popularManager);

        //this.initData();
        this.popularAdapter = new PopularAdapter(this.popularRecipeList);
        this.rvPopular.setAdapter(this.popularAdapter);
    }

    private void initRecentFeed(){
        DataHelper helper = new DataHelper();

        this.recentRecipeList = helper.initFood();
        this.rvRecent = findViewById(R.id.rv_home_recent);

        this.recentManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.rvRecent.setLayoutManager(recentManager);

        this.recentAdapter = new RecentAdapter(this.recentRecipeList);
        this.rvRecent.setAdapter(recentAdapter);
    }

//    private void initData(){
//        this.foodList = new ArrayList<Food>();
//
//        this.foodList.add(new Food(R.drawable.popular1, "Food 1"));
//        this.foodList.add(new Food(R.drawable.popular2, "Meow Meow"));
//        this.foodList.add(new Food(R.drawable.popular3, "Poopers poopie meow meow"));
//    }
}