package com.mobdeve.s14.group4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public static ArrayList<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d("HELLO", "HELLO");

        this.initComponents(); // init components and load data

        //this.initPopularRecyclerView();
        this.initRecentFeed();
    }

    private void initComponents(){
        this.llProfile = findViewById(R.id.ll_profile);
        this.llSearch = findViewById(R.id.ll_search);
        llCreate = findViewById(R.id.ll_create);
        ivSearch = findViewById(R.id.iv_search);
        llFavorites = findViewById(R.id.ll_fav);

        this.database = FirebaseDatabase.getInstance();
        this.databaseReference = this.database.getReference("recipes");
        this.recipeList = new ArrayList<>();

        final Recipe[] tempRecipe = new Recipe[1];

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                int ctr = 0;
                Recipe recipes = dataSnapshot.getValue(Recipe.class);
                Log.d("meow", "meow");

                for (DataSnapshot recipeSnapshot : dataSnapshot.getChildren()){
                    String id = recipeSnapshot.getKey();
                    int recipePic = 2131230935;
                    String recipeName = recipeSnapshot.child("recipeName").getValue().toString();
                    int foodFave = Integer.valueOf(recipeSnapshot.child("faveCount").getValue().toString());
                    double rating = Double.valueOf(recipeSnapshot.child("rating").getValue().toString());
                    String contributorId = recipeSnapshot.child("contributorId").getValue().toString();
                    String desc = recipeSnapshot.child("description").getValue().toString();
                    int reviewCount = Integer.valueOf(recipeSnapshot.child("reviewCount").getValue().toString());
                    int cookingTime = Integer.valueOf(recipeSnapshot.child("cookingTime").getValue().toString());
                    int prepTime = Integer.valueOf(recipeSnapshot.child("prepTime").getValue().toString());
                    int servings = Integer.valueOf(recipeSnapshot.child("cookingTime").getValue().toString());
                    String category = recipeSnapshot.child("category").getValue().toString();
                    String difficulty = recipeSnapshot.child("difficulty").getValue().toString();

                    tempRecipe[0] = new Recipe(id, recipePic, recipeName, foodFave, rating, contributorId, desc, reviewCount, cookingTime, prepTime, servings, category, difficulty);
                    recipeList.add(tempRecipe[0]);
                    Log.d("RECIPE LIST SIZE", String.valueOf(recipeList.size()));

                    Log.d("getKey", id);
                    Log.d("name", recipeSnapshot.child("recipeName").getValue().toString());
                    Log.d("recipe", String.valueOf(tempRecipe[0]));
                    Log.d("test name", tempRecipe[0].getRecipeName());
                    Log.d("test id", tempRecipe[0].getId());
                    ctr += 1;
                    Log.d("CTR", String.valueOf(ctr));
                }

                initPopularRecyclerView();
                initRecentFeed();
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });





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
        Log.d("RECIPE LIST SIZE aaaaaa", String.valueOf(recipeList.size()));
    }

    private void loadRecipes(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                Recipe recipes = dataSnapshot.getValue(Recipe.class);
                Log.d("recipes", String.valueOf(recipes));
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
    private void initPopularRecyclerView(){

        //DataHelper helper = new DataHelper();
        this.popularRecipeList = this.recipeList;
        Log.d("SIZE FINAL", String.valueOf(this.popularRecipeList.size()));
        this.rvPopular = findViewById(R.id.rv_home_popular);

        this.popularManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        this.rvPopular.setLayoutManager(this.popularManager);

        this.popularAdapter = new PopularAdapter(this.popularRecipeList);
        this.rvPopular.setAdapter(this.popularAdapter);
    }

    private void initRecentFeed(){

        this.recentRecipeList = this.recipeList;
        this.rvRecent = findViewById(R.id.rv_home_recent);

        this.recentManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.rvRecent.setLayoutManager(recentManager);

        this.recentAdapter = new RecentAdapter(this.recentRecipeList);
        this.rvRecent.setAdapter(recentAdapter);
    }

}