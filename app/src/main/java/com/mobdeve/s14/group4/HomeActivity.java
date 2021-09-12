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

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private DatabaseReference ingredientDatabaseReference;

    public static ArrayList<Recipe> recipeList;
    private ArrayList<Ingredient> allIngredientList;

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
        this.ingredientDatabaseReference = this.database.getReference("ingredients");
        recipeList = new ArrayList<>();
        this.allIngredientList = new ArrayList<>();

        Log.d("LISTSIZE", String.valueOf(recipeList.size()));



        ingredientDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                allIngredientList.clear();
                for (DataSnapshot ingredientSnapshot : snapshot.getChildren()){
                    String id = ingredientSnapshot.getKey();
                    String ingredientName = ingredientSnapshot.child("ingredientName").getValue().toString();
                    Double qty = Double.valueOf(ingredientSnapshot.child("quantity").getValue().toString());
                    String units = ingredientSnapshot.child("units").getValue().toString();

                    Log.d("INGREDIENT ID", id);
                    Log.d("INGREDIENT NAME", ingredientName);
                    Ingredient temp = new Ingredient(id, qty, units, ingredientName);
                    allIngredientList.add(temp);
                    initRecipes();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });



        //initRecipes

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

    private void initRecipes(){
        final Recipe[] tempRecipe = new Recipe[1];

        databaseReference.addValueEventListener(new ValueEventListener() { // load all recipes
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                int ctr = 0;
                recipeList.clear();
                Recipe recipes = dataSnapshot.getValue(Recipe.class);
                Log.d("meow", "meow");
                Log.d("WAAAAHHHHH", String.valueOf(allIngredientList.size()));
                Log.d("MOOOO", String.valueOf(recipeList.size()));

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
                    ArrayList<String> ingredientList = new ArrayList();
                    ArrayList<Ingredient> ingredientDetailsList = new ArrayList<>();
                    ingredientList = recipeSnapshot.child("ingredientsList").getValue(new GenericTypeIndicator<ArrayList<String>>(){});
                    ArrayList<String> stepsList = new ArrayList<>();
                    stepsList = recipeSnapshot.child("stepsList").getValue(new GenericTypeIndicator<ArrayList<String>>(){});

                    UploadImage mUpload = recipeSnapshot.child("uploadImage").getValue(UploadImage.class);

                    Log.d("MUPLOAD", mUpload.getmImageUrl());


                    for (int i = 0; i < ingredientList.size(); i++) {
                        for (int j = 0; j < allIngredientList.size(); j++){
                            if (ingredientList.get(i).equalsIgnoreCase(allIngredientList.get(j).getId())){
                                ingredientDetailsList.add(allIngredientList.get(j));
                            }
                        }
                    }
                    Log.d("INGRNAME", ingredientDetailsList.get(0).getIngredientName());

                    tempRecipe[0] = new Recipe(id, recipePic, recipeName, foodFave, rating, contributorId, desc, reviewCount, cookingTime, prepTime, servings, category, difficulty, ingredientList, ingredientDetailsList, stepsList, mUpload);
                    recipeList.add(tempRecipe[0]);
                    Log.d("RECIPE LIST SIZE", String.valueOf(recipeList.size()));
                    Log.d("INGREDIENTS", String.valueOf(tempRecipe[0].getIngredientsList()));

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