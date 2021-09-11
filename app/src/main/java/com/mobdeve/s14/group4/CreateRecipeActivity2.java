package com.mobdeve.s14.group4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class CreateRecipeActivity2 extends AppCompatActivity {

    private int iCtr = 0;

    private Button btnAdd;
    private Button btnNext;
    private ImageButton ibBack;
    private LinearLayout llIngredients;

    public static final String KEY_INGREDIENTS = "KEY_CR_INGREDIENTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createrecipe2);

        initComponents();
    }

    private void initComponents() {

        btnAdd = findViewById(R.id.createrecipe2_btn_add);
        llIngredients = findViewById(R.id.createrecipe2_ll_ingredients);
        btnNext = findViewById(R.id.createrecipe2_btn_next);
        ibBack = findViewById(R.id.createrecipe2_ib_back);

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Ingredient> ingredients = new ArrayList<>();

                final int ingredientCount = llIngredients.getChildCount();

                for (int i = 0; i < ingredientCount; i++){
                    View tempV = llIngredients.getChildAt(i);
                    EditText etQty = tempV.findViewById(R.id.createrecipe_ingredients_et_number);
                    EditText etUnit = tempV.findViewById(R.id.createrecipe_ingredients_et_unit);
                    EditText etName = tempV.findViewById(R.id.createrecipe_ingredients_et_name);

                    Double qty = Double.parseDouble(etQty.getText().toString().trim());
                    String unit = etUnit.getText().toString().trim();
                    String name = etName.getText().toString().trim();

                    ingredients.add(new Ingredient(qty, unit, name));
                }

                Intent tempI = getIntent();

                String recipeName = tempI.getStringExtra(CreateRecipeActivity1.KEY_RECIPENAME);
                String cookingTime =tempI.getStringExtra(CreateRecipeActivity1.KEY_COOKINGTIME);
                String prepTime = tempI.getStringExtra(CreateRecipeActivity1.KEY_PREPTIME);
                String servings = tempI.getStringExtra(CreateRecipeActivity1.KEY_SERVINGS);
                String description =tempI.getStringExtra(CreateRecipeActivity1.KEY_DESCRIPTION);
                String difficulty = tempI.getStringExtra(CreateRecipeActivity1.KEY_DIFFICULTY);
                String category =tempI.getStringExtra(CreateRecipeActivity1.KEY_CATEGORY);
                Bitmap image = (Bitmap) tempI.getParcelableExtra(CreateRecipeActivity1.KEY_IMAGE);

                Intent i = new Intent(CreateRecipeActivity2.this, CreateRecipeActivity3.class);

                i.putExtra(KEY_INGREDIENTS, (Serializable) ingredients);
                i.putExtra(CreateRecipeActivity1.KEY_RECIPENAME, recipeName);
                i.putExtra(CreateRecipeActivity1.KEY_COOKINGTIME, cookingTime);
                i.putExtra(CreateRecipeActivity1.KEY_DESCRIPTION, description);
                i.putExtra(CreateRecipeActivity1.KEY_PREPTIME, prepTime);
                i.putExtra(CreateRecipeActivity1.KEY_SERVINGS, servings);
                i.putExtra(CreateRecipeActivity1.KEY_CATEGORY, category);
                i.putExtra(CreateRecipeActivity1.KEY_DIFFICULTY, difficulty);
                i.putExtra(CreateRecipeActivity1.KEY_IMAGE, image);

                startActivityForResult(i, 1);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCtr ++;
                View ingredientLayout = getLayoutInflater().inflate(R.layout.createrecipe_ingredients_template, llIngredients, false);
                ImageView ivDelete = ingredientLayout.findViewById(R.id.createrecipe_ingredients_iv_delete);

                ivDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (iCtr > 0){
                            llIngredients.removeView(ingredientLayout);
                        }
                    }
                });

                llIngredients.addView(ingredientLayout);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }
}