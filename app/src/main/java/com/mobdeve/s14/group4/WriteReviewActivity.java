package com.mobdeve.s14.group4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;

import java.sql.SQLOutput;

public class WriteReviewActivity extends AppCompatActivity {

    private ImageButton ibSubmit;
    private ImageButton ibBack;
    private RatingBar rbRating;
    private EditText etReview;

    private Boolean submitted = false;
    public static final String KEY_SUBMITTED = "KEY_SUBMITTED";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        this.ibSubmit = findViewById(R.id.ib_review_submit);
        this.ibBack = findViewById(R.id.ib_review_back);
        this.rbRating = findViewById(R.id.rb_rating);
        this.etReview = findViewById(R.id.et_review);

        Intent i = getIntent();
        String recipeId = i.getStringExtra("KEY_RECIPE_ID");



        this.ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        this.ibSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numStars = rbRating.getNumStars();
                float rating = rbRating.getRating();
                Log.d("RATING", String.valueOf(rating));
                String rev = etReview.getText().toString();

                RecipeDatabase rb = new RecipeDatabase();

                FirebaseRecipe fr = new FirebaseRecipe();
                fr = fr.findRecipe(recipeId);



                /////////////////////////////TEMP
                String contributorId = "9GqIbsKba4Mi1p75l7HvGkg5nJh1";

                ReviewDatabase db = new ReviewDatabase();
                Review review = new Review(contributorId, rating, rev, fr.getId());

                Log.d("REVIEWWW", fr.getId());
                System.out.println(review.getRecipeId());
                //adding review to realtime database
                fr.addReview(review);


                finish();
            }
        });


    }

}