package com.mobdeve.s14.group4;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RecipeDatabase {
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private IngredientDatabase ingredientDatabase;

    public RecipeDatabase(){
        this.auth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance();
        this.databaseReference = this.database.getReference("recipes");
        this.ingredientDatabase = new IngredientDatabase();
    }

    public String addIngredient(Ingredient ingredient){
        return this.ingredientDatabase.addIngredient(ingredient);
    }

    public void addRecipe(Recipe recipe){
        Log.d("myTag", "Add Recipe Entered");
        String key = this.databaseReference.push().getKey();

        recipe.setId(key);

        this.databaseReference.child(key).setValue(recipe.getFirebaseRecipe());
    }

    public void updateRecipe(Recipe recipe){
        this.databaseReference.child(recipe.getId()).setValue(recipe);
    }
}
