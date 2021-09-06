package com.mobdeve.s14.group4;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IngredientDatabase {
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public IngredientDatabase(){
        this.auth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance();
        this.databaseReference = this.database.getReference("ingredients");
    }

    public String addIngredient(Ingredient ingredient){
        String key = this.databaseReference.push().getKey();

        ingredient.setId(key);

        this.databaseReference.child(key).setValue(ingredient);

        return key;
    }
}
