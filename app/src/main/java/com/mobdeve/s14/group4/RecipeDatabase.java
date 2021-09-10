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

    public String addRecipe(Recipe recipe){
        Log.d("myTag", "Add Recipe Entered");
        String key = this.databaseReference.push().getKey();

        recipe.setId(key);

        this.databaseReference.child(key).setValue(recipe.getFirebaseRecipe());

        return key;
    }

    public void updateRecipe(Recipe recipe){
        this.databaseReference.child(recipe.getId()).setValue(recipe);
    }


//    public void dropRecipes(){
//        this.databaseReference.setValue(null);
//        this.ingredientDatabase.dropIngredients();
//    }
}
