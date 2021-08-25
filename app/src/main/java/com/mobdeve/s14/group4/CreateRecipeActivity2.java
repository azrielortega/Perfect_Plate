package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CreateRecipeActivity2 extends AppCompatActivity {

    private int iCtr = 0;

    private Button btnAdd;
    private Button btnNext;
    private LinearLayout llIngredients;

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

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateRecipeActivity2.this, CreateRecipeActivity3.class);
                startActivity(i);
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
}