package com.mobdeve.s14.group4;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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

    private TextView tvIngredients;
    private TextView tvInstructions;
    private TextView tvReviews;

    private ConstraintLayout clIngredients;
    private ConstraintLayout clInstructions;
    private ConstraintLayout clReviews;
    private LinearLayout llWriteReview;
    private LinearLayout llComment2;

    private ImageButton ibFave;
    private ImageButton ibBack;
    private ImageView ivDeleteComment;

    private Boolean liked = false;
    public static Boolean reviewed = false;

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
        this.clReviews = findViewById(R.id.cl_details_community_review);
        this.llComment2 = findViewById(R.id.ll_comment2);

        this.tvIngredients = findViewById(R.id.tv_details_ingredients);
        this.tvInstructions = findViewById(R.id.tv_details_instructions);
        this.tvReviews = findViewById(R.id.tv_details_reviews);

        this.ibFave = findViewById(R.id.ib_recipe_details_fav);
        this.ibBack = findViewById(R.id.ib_recipe_details_back);

        this.ivDeleteComment = findViewById(R.id.iv_delete_comment);

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
                reviewed = true;
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

                clReviews.setVisibility(View.GONE);
                tvReviews.setBackgroundColor(getResources().getColor(R.color.proj_white));
                tvReviews.setTextColor(getResources().getColor(R.color.proj_blue));

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

                clReviews.setVisibility(View.GONE);
                tvReviews.setBackgroundColor(getResources().getColor(R.color.proj_white));
                tvReviews.setTextColor(getResources().getColor(R.color.proj_blue));
            }
        });

        this.tvReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clReviews.setVisibility(View.VISIBLE);
                tvReviews.setBackgroundColor(getResources().getColor(R.color.proj_yellow));
                tvReviews.setTextColor(getResources().getColor(R.color.proj_white));

                clIngredients.setVisibility(View.GONE);
                tvIngredients.setBackgroundResource(R.color.proj_white);
                tvIngredients.setTextColor(getResources().getColor(R.color.proj_blue));

                clInstructions.setVisibility(View.GONE);
                tvInstructions.setBackgroundColor(getResources().getColor(R.color.proj_white));
                tvInstructions.setTextColor(getResources().getColor(R.color.proj_blue));
            }
        });

        this.ibFave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!liked){
                    ibFave.setImageResource(R.drawable.heart_on);
                    liked = true;
                    ibFave.setColorFilter(getResources().getColor(R.color.proj_red_pink));
                } else {
                    ibFave.setImageResource(R.drawable.heart_off);
                    liked = false;
                    ibFave.setColorFilter(getResources().getColor(R.color.proj_white));
                }
            }
        });

        this.ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        this.ivDeleteComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llComment2.setVisibility(View.GONE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1){
            llComment2.setVisibility(View.VISIBLE);
        }
    }
}