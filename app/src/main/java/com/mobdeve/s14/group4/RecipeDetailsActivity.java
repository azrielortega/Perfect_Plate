package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeDetailsActivity extends AppCompatActivity {
    private TextView tvRecipeName;
    private ImageView ivRecipePic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        this.tvRecipeName = findViewById(R.id.tv_recipe_details_name);
        this.ivRecipePic = findViewById(R.id.iv_recipe_details_pic);

        Intent i = getIntent();

        int iRecipePic = i.getIntExtra(PopularAdapter.KEY_RECIPE_PIC, 0);
        this.ivRecipePic.setImageResource(iRecipePic);

        String iRecipeName = i.getStringExtra(PopularAdapter.KEY_RECIPE_NAME);
        this.tvRecipeName.setText(iRecipeName);
    }
}