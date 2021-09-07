package com.mobdeve.s14.group4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class CreateRecipeActivity1 extends AppCompatActivity {

    private Button btnCreate1;

    private ImageButton ibBack;

    public static final String KEY_RECIPENAME = "KEY_CR_RECIPENAME";
    public static final String KEY_DESCRIPTION = "KEY_CR_DESCRIPTION";
    public static final String KEY_SERVINGS = "KEY_CR_SERVINGS";
    public static final String KEY_COOKINGTIME = "KEY_CR_COOKINGTIME";
    public static final String KEY_PREPTIME= "KEY_CR_PREPTIME";

    private EditText etRecipeName;

    private EditText etPrepTime;
    private EditText etServings;
    private EditText etCookingTime;
    private EditText etDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe1);

        initComponents();
    }

    private void initComponents() {

        etRecipeName = findViewById(R.id.et_create1_recipe_name);

        etCookingTime = findViewById(R.id.et_create1_cookingtime);
        etPrepTime = findViewById(R.id.et_create1_preptime);
        etServings = findViewById(R.id.et_create1_servings);
        etDescription = findViewById(R.id.et_create1_description);

        btnCreate1 = findViewById(R.id.btn_create1_next);
        ibBack = findViewById(R.id.ib_create1_back);

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCreate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipeName, cookingTime, prepTime, servings, description;

                recipeName = etRecipeName.getText().toString().trim();
                cookingTime = etCookingTime.getText().toString().trim();
                prepTime = etPrepTime.getText().toString().trim();
                servings = etServings.getText().toString().trim();
                description = etDescription.getText().toString().trim();

                Intent i = new Intent(CreateRecipeActivity1.this, CreateRecipeActivity2.class);

                i.putExtra(KEY_RECIPENAME, recipeName);
                i.putExtra(KEY_COOKINGTIME, cookingTime);
                i.putExtra(KEY_DESCRIPTION, description);
                i.putExtra(KEY_PREPTIME, prepTime);
                i.putExtra(KEY_SERVINGS, servings);

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