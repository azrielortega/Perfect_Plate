package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class SearchActivity extends AppCompatActivity {

    private LinearLayout llProfile;
    private LinearLayout llHome;
    private LinearLayout llCreate;
    private LinearLayout llFavorites;

    private CardView cvCategory;
    private CardView cvPasta;
    private CardView cvMain;
    private CardView cvDessert;
    private CardView cvPastry;
    private CardView cvDrinks;

    public static final String KEY_CATEGORY = "KEY_CATEGORY";

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
        llFavorites = findViewById(R.id.ll_fav);
        this.cvCategory = findViewById(R.id.cv_search_appetizer);
        this.cvPasta = findViewById(R.id.cv_search_pasta);
        this.cvMain = findViewById(R.id.cv_search_main);
        this.cvDessert = findViewById(R.id.cv_search_dessert);
        this.cvPastry = findViewById(R.id.cv_search_pastry);
        this.cvDrinks = findViewById(R.id.cv_search_drinks);

        llFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (SearchActivity.this, ViewFavoritesActivity.class);
                startActivity(i);
            }
        });

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

        this.cvCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchActivity.this, SearchFilterActivity.class);
                i.putExtra(KEY_CATEGORY, "Appetizer");
                startActivity(i);

            }
        });

        this.cvPasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchActivity.this, SearchFilterActivity.class);
                i.putExtra(KEY_CATEGORY, "Pasta");
                startActivity(i);

            }
        });

        this.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchActivity.this, SearchFilterActivity.class);
                i.putExtra(KEY_CATEGORY, "Main");
                startActivity(i);

            }
        });

        this.cvDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchActivity.this, SearchFilterActivity.class);
                i.putExtra(KEY_CATEGORY, "Dessert");
                startActivity(i);

            }
        });

        this.cvPastry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchActivity.this, SearchFilterActivity.class);
                i.putExtra(KEY_CATEGORY, "Pastry");
                startActivity(i);

            }
        });

        this.cvDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchActivity.this, SearchFilterActivity.class);
                i.putExtra(KEY_CATEGORY, "Drinks");
                startActivity(i);

            }
        });
    }
}