package com.mobdeve.s14.group4;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ReviewDatabase {
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public ReviewDatabase(){
        this.auth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance();
        this.databaseReference = this.database.getReference("reviews");
    }

//    public void

    public String addReview(Review review){
        String key = this.databaseReference.push().getKey();

        review.setId(key);

        Log.d("REVIEWDBID", review.getRecipeId());

        this.databaseReference.child(key).setValue(review);

        return key;
    }

    /**
     * For initializing DataHelper
     * */
    public void getAllReviews(final CallbackListener callbackListener){
        ArrayList<Review> reviews = new ArrayList<Review>();

        this.databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot reviewSnapshot : snapshot.getChildren()){
                        Review review = reviewSnapshot.getValue(Review.class);

                        reviews.add(review);
                    }
                }

                callbackListener.onSuccess(reviews);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                callbackListener.onFailure();
            }
        });
    }
    public void dropIngredients(){
        this.databaseReference.setValue(null);
    }
}
