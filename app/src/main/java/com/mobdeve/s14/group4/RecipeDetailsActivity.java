package com.mobdeve.s14.group4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class RecipeDetailsActivity extends AppCompatActivity {
    private Recipe recipe;

    private TextView tvRecipeName;
    private ImageView ivRecipePic;
    private TextView tvRecipeNameTop;
    private TextView tvStarsSummary;
    private TextView tvFavCount;
    private ImageView ivContributorPic;
    private TextView tvContributorName;
    private TextView tvDescription;
    private TextView tvReviewCount;
    private TextView tvCategory;

    private TextView tvIngredients;
    private TextView tvInstructions;
    private TextView tvReviews;

    private TextView tvReviewName;
    private TextView tvReviewComment;

    private ConstraintLayout clIngredients;
    private ConstraintLayout clInstructions;
    private ConstraintLayout clReviews;
    private LinearLayout llWriteReview;
    private LinearLayout llComment2;
    private LinearLayout llIngredientsCont;
    private LinearLayout llStepsCont;
    private LinearLayout llCommentCont;

    private ImageButton ibFave;
    private ImageButton ibBack;
    private ImageView ivDeleteComment;
    private ImageView ivReviewUserPic;

    private FloatingActionButton fabHeart;
    private Boolean liked = false;
    public static Boolean reviewed = false;

    public static final String KEY_RECIPE_ID = "KEY_RECIPE_ID";

    private DatabaseReference reviewsDatabaseReference;
    private FirebaseDatabase database;

    private TextView tvEmpty;
    private int reviewCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        this.fabHeart = findViewById(R.id.fab_heart);
        this.tvRecipeName = findViewById(R.id.tv_recipe_details_name);
        this.ivRecipePic = findViewById(R.id.iv_recipe_details_pic);
        this.tvRecipeNameTop = findViewById(R.id.tv_recipe_details_name_top);
        this.tvStarsSummary = findViewById(R.id.tv_details_stars_summary);
        this.tvFavCount = findViewById(R.id.tv_details_fav_count);
        this.ivContributorPic = findViewById(R.id.iv_recipe_details_contributor_pic);
        this.tvContributorName = findViewById(R.id.tv_recipe_details_contributor_name);
        this.tvDescription = findViewById(R.id.tv_recipe_details_description);
        this.tvReviewCount = findViewById(R.id.tv_details_review_count);
        this.tvCategory = findViewById(R.id.tv_recipe_details_category);

        this.ivReviewUserPic = findViewById(R.id.iv_review_user_pic);
        this.tvReviewComment = findViewById(R.id.tv_review_comment);
        this.tvReviewName = findViewById(R.id.tv_review_name);

        this.llWriteReview = findViewById(R.id.ll_write_review);
        this.clIngredients = findViewById(R.id.cl_details_ingredients);
        this.clInstructions = findViewById(R.id.cl_details_instructions);
        this.clReviews = findViewById(R.id.cl_details_community_review);
        //this.llComment2 = findViewById(R.id.ll_comment2);

        this.tvIngredients = findViewById(R.id.tv_details_ingredients);
        this.tvInstructions = findViewById(R.id.tv_details_instructions);
        this.tvReviews = findViewById(R.id.tv_details_reviews);
        this.llIngredientsCont = findViewById(R.id.ll_ingredients_cont);
        this.llStepsCont = findViewById(R.id.ll_steps_cont);
        this.llCommentCont = findViewById(R.id.ll_comment_container);
        this.tvEmpty = findViewById(R.id.tv_review_empty);

        this.ibBack = findViewById(R.id.ib_recipe_details_back);

        //this.ivDeleteComment = findViewById(R.id.iv_delete_comment);

        //this.clReviews = findViewById(R.id.cl_details_reviews);

        Intent i = getIntent();
        String id = i.getStringExtra(PopularAdapter.KEY_RECIPE_ID);

        this.recipe = DataHelper.recipeDatabase.findRecipe(id);

        this.tvRecipeName.setText(recipe.getRecipeName());
//        this.ivRecipePic.setImageResource(recipe.getRecipePic());
        this.tvDescription.setText(recipe.getDescription());
        this.tvRecipeNameTop.setText(recipe.getRecipeName());
        //this.tvStarsSummary.setText(String.valueOf(recipe.getRating()));
        this.tvFavCount.setText(String.valueOf(recipe.getFaveCount()));
        this.tvReviewCount.setText(String.valueOf(recipe.getReviewCount()).concat(" reviews"));

        String temp = "Category: ".concat(recipe.getCategory());
        this.tvCategory.setText(temp);

        Log.d("CONTRIBUTORID", recipe.getContributorId());

        // set user
        this.tvContributorName.setText(recipe.getContributorName());


        Log.d("SETTING PIC", recipe.getUploadImage().getmImageUrl());
        //set pic
        Picasso.with(this)
                .load(recipe.getUploadImage().getmImageUrl())
                .placeholder(R.drawable.perfect_plate_transparent_bg)
                .fit()
                .centerCrop()
                .into(this.ivRecipePic);

        // set ingredients
        for (Ingredient ingredient : recipe.getIngredientDetailsList()){
            View ingredientLayout = getLayoutInflater().inflate(R.layout.ingredients_list_template, llIngredientsCont, false);
            llIngredientsCont.addView(ingredientLayout);

            TextView measurement = ingredientLayout.findViewById(R.id.tv_ingredients_amt);
            TextView ingrName = ingredientLayout.findViewById(R.id.tv_ingredient_name);

            String tempM = String.valueOf(ingredient.getQuantity()).concat(" " + ingredient.getUnits());
            measurement.setText(tempM);

            ingrName.setText(ingredient.getIngredientName());
        }

        //set steps
        for (int ctr = 0; ctr < recipe.getStepsList().size(); ctr++){
            View stepsLayout = getLayoutInflater().inflate(R.layout.steps_list_template, llStepsCont, false);
            llStepsCont.addView(stepsLayout);

            TextView number = stepsLayout.findViewById(R.id.tv_step_number);
            TextView str = stepsLayout.findViewById(R.id.tv_step_text);

            number.setText(String.valueOf(ctr+1));
            str.setText(recipe.getStepsList().get(ctr));

        }

        Log.d("AAA", recipe.getId());

        //TODO: convert to recycler view
        for (Review review : DataHelper.allReviews){
            Log.d("recipedetailsid", review.getRecipeId());
            if (review.getRecipeId().equals(recipe.getId())) {
                reviewCount += 1;
                View commentLayout = getLayoutInflater().inflate(R.layout.comment_template, llCommentCont, false);
                llCommentCont.addView(commentLayout);

                TextView name = commentLayout.findViewById(R.id.tv_review_name);
                TextView comment = commentLayout.findViewById(R.id.tv_review_comment);
                ImageView pic = commentLayout.findViewById(R.id.iv_review_load_pic);

                Log.d("REVIEWTEST", review.getId());
                Log.d("REVIEWTEST", review.getComment());
                name.setText(review.getContributorName());
                comment.setText(review.getComment());

                if (review.getUploadImage() != null){
                    pic.setVisibility(View.VISIBLE);
                    Picasso.with(this)
                            .load(review.getUploadImage().getmImageUrl())
                            .placeholder(R.drawable.perfect_plate_transparent_bg)
                            .fit()
                            .centerCrop()
                            .into(pic);

                } else {
                    pic.setVisibility(View.GONE);
                }
            }
        }

        this.tvStarsSummary.setText(recipe.getRatingString());
        String revCtr = reviewCount + " reviews";
        this.tvReviewCount.setText(revCtr);

        if(reviewCount < 1){
            this.tvEmpty.setVisibility(View.VISIBLE);
        } else{
            this.tvEmpty.setVisibility(View.GONE);
        }

        this.clIngredients.setVisibility(View.VISIBLE);

        this.llWriteReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewed = true;
                Intent i = new Intent(v.getContext(), WriteReviewActivity.class);
                i.putExtra(KEY_RECIPE_ID, id); //TODO: ???
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

        this.fabHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!liked){
                    fabHeart.setImageResource(R.drawable.heart_on);
                    liked = true;
                    fabHeart.setColorFilter(getResources().getColor(R.color.proj_red_pink));
                    DataHelper.userDatabase.addFaveRecipe(recipe);
                } else {
                    fabHeart.setImageResource(R.drawable.heart_off);
                    liked = false;
                    fabHeart.setColorFilter(getResources().getColor(R.color.proj_red_pink));
                    DataHelper.userDatabase.removeFaveRecipe(recipe);
                }
            }
        });

        this.ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO: convert to recycler view
        llCommentCont.removeAllViews();
        reviewCount = 0;
        for (Review review : DataHelper.allReviews){
            Log.d("recipedetailsid", review.getRecipeId());
            if (review.getRecipeId().equals(recipe.getId())) {
                reviewCount += 1;
                View commentLayout = getLayoutInflater().inflate(R.layout.comment_template, llCommentCont, false);
                llCommentCont.addView(commentLayout);

                TextView name = commentLayout.findViewById(R.id.tv_review_name);
                TextView comment = commentLayout.findViewById(R.id.tv_review_comment);
                ImageView pic = commentLayout.findViewById(R.id.iv_review_load_pic);

                Log.d("REVIEWTEST", review.getId());
                Log.d("REVIEWTEST", review.getComment());
                name.setText(review.getContributorName());
                comment.setText(review.getComment());

                if (review.getUploadImage() != null){
                    pic.setVisibility(View.VISIBLE);
                    Picasso.with(this)
                            .load(review.getUploadImage().getmImageUrl())
                            .placeholder(R.drawable.perfect_plate_transparent_bg)
                            .fit()
                            .centerCrop()
                            .into(pic);

                } else {
                    pic.setVisibility(View.GONE);
                }
            }
        }

        if(reviewCount < 1){
            this.tvEmpty.setVisibility(View.VISIBLE);
        } else{
            this.tvEmpty.setVisibility(View.GONE);
        }
        this.tvStarsSummary.setText(recipe.getRatingString());
        String revCtr = reviewCount + " reviews";
        this.tvReviewCount.setText(revCtr);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1){
            llComment2.setVisibility(View.VISIBLE);
        }
    }
}