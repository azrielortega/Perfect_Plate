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

public class RecipeDatabase {
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private IngredientDatabase ingredientDatabase;

    private ReviewDatabase reviewDatabase;

    public RecipeDatabase(){
        this.auth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance();
        this.databaseReference = this.database.getReference("recipes");
        this.ingredientDatabase = new IngredientDatabase();
        this.reviewDatabase = new ReviewDatabase();
    }

    /**
     * For initializing DataHelper
     */
    public void getAllRecipes(final CallbackListener callbackListener){
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();

        this.databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot recipeSnapshot : snapshot.getChildren()){
                        FirebaseRecipe firebaseRecipe = recipeSnapshot.getValue(FirebaseRecipe.class);
                        Recipe recipe = new Recipe(firebaseRecipe);

                        recipes.add(recipe);
                    }
                }

                callbackListener.onSuccess(recipes);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                callbackListener.onFailure();
            }
        });
    }

    /**
     * Find a single recipe from data helper
     * */
    public Recipe findRecipe(String id){
        for (Recipe recipe : DataHelper.allRecipes){
            if(id.equals(recipe.getId())){
                return recipe;
            }
        }

        return null;
    }

    /**
     * Find recipes from data helper
     * */
    public ArrayList<Recipe> findRecipes(ArrayList<String> recipeIds){
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();

        for (String id : recipeIds){
            recipes.add(findRecipe(id));
        }

        return recipes;
    }

    public String addRecipe(Recipe recipe){
        Log.d("myTag", "Add Recipe Entered");
        String key = this.databaseReference.push().getKey();

        recipe.setId(key);

        this.databaseReference.child(key).setValue(recipe.getFirebaseRecipe());

        return key;
    }

    public String addReview(Review r){
        Log.d("recipedbrecipeid", r.getRecipeId());
        return this.reviewDatabase.addReview(r);
    }

    public void updateRecipe(String id, Recipe recipe){
        //update rating
        updateRating(id, recipe.getRating());

        //update faveCount
        updateFaveCount(id, recipe.getFaveCount());

        //update reviewCount
        updateReviewCount(id, recipe.getReviewCount());
    }

    public void updateRating(String id, double rating){
        if (rating > 0){
            this.databaseReference.child(id).child("rating").setValue(rating);
        }
    }

    public void updateFaveCount(String id, long faveCount){
        if (faveCount > 0){
            this.databaseReference.child(id).child("faveCount").setValue(faveCount);
        }
    }

    public void updateReviewCount(String id, long reviewCount){
        if (reviewCount > 0){
            this.databaseReference.child(id).child("reviewCount").setValue(reviewCount);
        }
    }

    /**
     * Delete recipe from recipe database.
     * */
    public void deleteRecipe(String id){
        this.databaseReference.child(id).setValue(null);
    }
}
