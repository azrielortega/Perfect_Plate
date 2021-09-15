package com.mobdeve.s14.group4;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class IngredientDatabase {
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public IngredientDatabase(){
        this.auth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance();
        this.databaseReference = this.database.getReference("ingredients");
    }

    /**
     * For initializing DataHelper
     * */
    public void getAllIngredients(final CallbackListener callbackListener){
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

        this.databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot ingredientSnapshot : snapshot.getChildren()){
                        Ingredient ingredient = ingredientSnapshot.getValue(Ingredient.class);

                        ingredients.add(ingredient);
                    }
                }

                callbackListener.onSuccess(ingredients);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                callbackListener.onFailure();
            }
        });
    }

    /**
     * Get ingredient from list
     * */
    public Ingredient findIngredient(String ingredientId){
        for (Ingredient ingredient : DataHelper.allIngredients){
            if(ingredientId.equalsIgnoreCase(ingredient.getId())){
                return ingredient;
            }
        }

        return null;
    }

    /**
     * Find ingredients from data helper
     * */
    public ArrayList<Ingredient> findIngredients(ArrayList<String> ingredientIds){
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

        for (String id : ingredientIds){
            ingredients.add(findIngredient(id));
        }

        return ingredients;
    }

    public String addIngredient(Ingredient ingredient){
        String key = this.databaseReference.push().getKey();

        ingredient.setId(key);

        this.databaseReference.child(key).setValue(ingredient);

        return key;
    }

}
