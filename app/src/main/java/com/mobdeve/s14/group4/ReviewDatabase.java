package com.mobdeve.s14.group4;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReviewDatabase {
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public ReviewDatabase(){
        this.auth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance();
        this.databaseReference = this.database.getReference("reviews");
    }

    public String addReview(Review review){
        String key = this.databaseReference.push().getKey();

        review.setId(key);

        Log.d("REVIEWDBID", review.getRecipeId());

        this.databaseReference.child(key).setValue(review);

        return key;
    }

    public void dropIngredients(){
        this.databaseReference.setValue(null);
    }
}
