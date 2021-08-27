package com.mobdeve.s14.group4;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecipeDetailsActivity extends AppCompatActivity {
    private TextView tvRecipeName;
    private ImageView ivRecipePic;
    private TextView tvRecipeNameTop;
    private TextView tvStarsSummary;
    private TextView tvFavCount;
    private ImageView ivContributorPic;
    private TextView tvContributorName;
    private TextView tvDescription;
    private TextView tvReviewCount;
    private LinearLayout llWriteReview;
    private TextView tvIngredients;
    private TextView tvInstructions;

    private ConstraintLayout clIngredients;
    private ConstraintLayout clInstructions;
    private ConstraintLayout clReviews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        this.tvRecipeName = findViewById(R.id.tv_recipe_details_name);
        this.ivRecipePic = findViewById(R.id.iv_recipe_details_pic);
        this.tvRecipeNameTop = findViewById(R.id.tv_recipe_details_name_top);
        this.tvStarsSummary = findViewById(R.id.tv_details_stars_summary);
        this.tvFavCount = findViewById(R.id.tv_details_fav_count);
        this.ivContributorPic = findViewById(R.id.iv_recipe_details_contributor_pic);
        this.tvContributorName = findViewById(R.id.tv_recipe_details_contributor_name);
        this.tvDescription = findViewById(R.id.tv_recipe_details_description);
        this.tvReviewCount = findViewById(R.id.tv_details_review_count);

        this.llWriteReview = findViewById(R.id.ll_write_review);
        this.clIngredients = findViewById(R.id.cl_details_ingredients);
        this.clInstructions = findViewById(R.id.cl_details_instructions);

        this.tvIngredients = findViewById(R.id.tv_details_ingredients);
        this.tvInstructions = findViewById(R.id.tv_details_instructions);

        //this.clReviews = findViewById(R.id.cl_details_reviews);

        Intent i = getIntent();

        int iRecipePic = i.getIntExtra(PopularAdapter.KEY_RECIPE_PIC, 0);
        this.ivRecipePic.setImageResource(iRecipePic);

        String iRecipeName = i.getStringExtra(PopularAdapter.KEY_RECIPE_NAME);
        this.tvRecipeName.setText(iRecipeName);
        this.tvRecipeNameTop.setText(iRecipeName);

//        String iStarsSummary = i.getStringExtra(PopularAdapter.KEY_RECIPE_STARS);
//        this.tvStarsSummary.setText(iStarsSummary);
//
//        int iFavCount = i.getIntExtra(PopularAdapter.KEY_RECIPE_FAV, 0);
//        this.tvFavCount.setText(iFavCount);
//
        int iContributorPic = i.getIntExtra(PopularAdapter.KEY_CONTRIBUTOR_PIC, 0);
        this.ivContributorPic.setImageResource(iContributorPic);
//
        String iContName = i.getStringExtra(PopularAdapter.KEY_CONTRIBUTOR_NAME);
        this.tvContributorName.setText(iContName);
//
        String iDesc = i.getStringExtra(PopularAdapter.KEY_RECIPE_DESCRIPTION);
        this.tvDescription.setText(iDesc);
//
//        int iRC = i.getIntExtra(PopularAdapter.KEY_RECIPE_REVIEWS_COUNT, 0);
//        this.tvReviewCount.setText(iRC);

        this.clIngredients.setVisibility(View.VISIBLE);

        this.llWriteReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), WriteReviewActivity.class);
                startActivity(i);
            }
        });

        this.tvIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clIngredients.setVisibility(View.VISIBLE);
                tvIngredients.setBackgroundResource(R.color.proj_yellow);
                tvIngredients.setTextColor(getResources().getColor(R.color.proj_white));


                clInstructions.setVisibility(View.GONE);
                tvInstructions.setBackgroundColor(getResources().getColor(R.color.proj_white));
                tvInstructions.setTextColor(getResources().getColor(R.color.proj_blue));

            }
        });

        this.tvInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clInstructions.setVisibility(View.VISIBLE);
                tvInstructions.setBackgroundColor(getResources().getColor(R.color.proj_yellow));
                tvInstructions.setTextColor(getResources().getColor(R.color.proj_white));

                clIngredients.setVisibility(View.GONE);
                tvIngredients.setBackgroundResource(R.color.proj_white);
                tvIngredients.setTextColor(getResources().getColor(R.color.proj_blue));
            }
        });

    }
}