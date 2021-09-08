package com.mobdeve.s14.group4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateRecipeActivity1 extends AppCompatActivity {

    private Button btnCreate1;

    private ImageButton ibBack;

    public static final String KEY_RECIPENAME = "KEY_CR_RECIPENAME";
    public static final String KEY_DESCRIPTION = "KEY_CR_DESCRIPTION";
    public static final String KEY_SERVINGS = "KEY_CR_SERVINGS";
    public static final String KEY_COOKINGTIME = "KEY_CR_COOKINGTIME";
    public static final String KEY_PREPTIME= "KEY_CR_PREPTIME";
    public static final String KEY_DIFFICULTY = "KEY_CR_DIFFICULTY";
    public static final String KEY_CATEGORY = "KEY_CR_CATEGORY";

    private EditText etRecipeName;

    private EditText etPrepTime;
    private EditText etServings;
    private EditText etCookingTime;
    private EditText etDescription;

    private Spinner spCategory;

    private TextView tvEasy;
    private TextView tvMedium;
    private TextView tvHard;

    private String difficulty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe1);

        difficulty = "Easy";


        initSpinner();
        initComponents();
    }

    private void initSpinner(){
        spCategory = findViewById(R.id.sp_create1_category);

        ArrayAdapter<String> spAdapter = new ArrayAdapter<String>(CreateRecipeActivity1.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.category));
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(spAdapter);

    }

    private void initComponents() {

        etRecipeName = findViewById(R.id.et_create1_recipe_name);

        etCookingTime = findViewById(R.id.et_create1_cookingtime);
        etPrepTime = findViewById(R.id.et_create1_preptime);
        etServings = findViewById(R.id.et_create1_servings);
        etDescription = findViewById(R.id.et_create1_description);

        btnCreate1 = findViewById(R.id.btn_create1_next);
        ibBack = findViewById(R.id.ib_create1_back);

        tvEasy = findViewById(R.id.tv_create1_easy);
        tvMedium = findViewById(R.id.tv_create1_medium);
        tvHard = findViewById(R.id.tv_create1_hard);

        tvEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvEasy.setTextColor(Color.parseColor("#f4f0ef"));
                tvEasy.setBackgroundColor(Color.parseColor("#FF9400"));

                tvMedium.setTextColor(Color.parseColor("#02043F"));
                tvMedium.setBackgroundColor(Color.parseColor("#FFFFFF"));

                tvHard.setTextColor(Color.parseColor("#02043F"));
                tvHard.setBackgroundColor(Color.parseColor("#FFFFFF"));

                difficulty = "Easy";
            }
        });

        tvMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMedium.setTextColor(Color.parseColor("#f4f0ef"));
                tvMedium.setBackgroundColor(Color.parseColor("#FF9400"));

                tvEasy.setTextColor(Color.parseColor("#02043F"));
                tvEasy.setBackgroundColor(Color.parseColor("#FFFFFF"));

                tvHard.setTextColor(Color.parseColor("#02043F"));
                tvHard.setBackgroundColor(Color.parseColor("#FFFFFF"));

                difficulty = "Medium";
            }
        });

        tvHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHard.setTextColor(Color.parseColor("#f4f0ef"));
                tvHard.setBackgroundColor(Color.parseColor("#FF9400"));

                tvMedium.setTextColor(Color.parseColor("#02043F"));
                tvMedium.setBackgroundColor(Color.parseColor("#FFFFFF"));

                tvEasy.setTextColor(Color.parseColor("#02043F"));
                tvEasy.setBackgroundColor(Color.parseColor("#FFFFFF"));

                difficulty = "Hard";
            }
        });

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCreate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipeName, cookingTime, prepTime, servings, description, category;

                recipeName = etRecipeName.getText().toString().trim();
                cookingTime = etCookingTime.getText().toString().trim();
                prepTime = etPrepTime.getText().toString().trim();
                servings = etServings.getText().toString().trim();
                description = etDescription.getText().toString().trim();
                category = spCategory.getSelectedItem().toString().trim();

                Intent i = new Intent(CreateRecipeActivity1.this, CreateRecipeActivity2.class);

                i.putExtra(KEY_RECIPENAME, recipeName);
                i.putExtra(KEY_COOKINGTIME, cookingTime);
                i.putExtra(KEY_DESCRIPTION, description);
                i.putExtra(KEY_PREPTIME, prepTime);
                i.putExtra(KEY_SERVINGS, servings);
                i.putExtra(KEY_CATEGORY, category);
                i.putExtra(KEY_DIFFICULTY, difficulty);

                startActivityForResult(i, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }
}