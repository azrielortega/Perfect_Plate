package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
import android.widget.LinearLayout;
import android.widget.TextView;

public class CreateRecipeActivity2 extends AppCompatActivity {

    private Button btnAdd;
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

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShapeDrawable shape = new ShapeDrawable(new RectShape());
                shape.getPaint().setColor(Color.parseColor("#D5d8d8"));
                shape.getPaint().setStyle(Paint.Style.STROKE);
                shape.getPaint().setStrokeWidth(3);

                EditText etQuantity = new EditText(CreateRecipeActivity2.this);
                EditText etUnit = new EditText(CreateRecipeActivity2.this);
                EditText etName = new EditText(CreateRecipeActivity2.this);

                etQuantity.setHint("0");
                etQuantity.setHintTextColor(Color.parseColor("#9e9f9f"));
                etQuantity.setTextColor(Color.parseColor("#02043F"));
                etQuantity.setBackground(shape);
                etQuantity.setTypeface(Typeface.SANS_SERIF);

                etUnit.setHint("unit");
                etUnit.setHintTextColor(Color.parseColor("#9e9f9f"));
                etUnit.setTextColor(Color.parseColor("#02043F"));
                etUnit.setBackground(shape);
                etUnit.setTypeface(Typeface.SANS_SERIF);

                etName.setHint("Ingredient");
                etName.setHintTextColor(Color.parseColor("#9e9f9f"));
                etName.setTextColor(Color.parseColor("#02043F"));
                etName.setBackground(shape);
                etName.setTypeface(Typeface.SANS_SERIF);

                LinearLayout ll_temp = new LinearLayout(CreateRecipeActivity2.this);
                ll_temp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                ll_temp.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.weight = 0.3f;
                params.leftMargin = 5;

                etQuantity.setLayoutParams(new LinearLayout.LayoutParams(params));

                params.weight = 0.5f;
                etUnit.setLayoutParams(new LinearLayout.LayoutParams(params));

                params.weight = 1.0f;
                etName.setLayoutParams(new LinearLayout.LayoutParams(params));

                ll_temp.addView(etQuantity);
                ll_temp.addView(etUnit);
                ll_temp.addView(etName);

                llIngredients.addView(ll_temp);
            }
        });
    }
}