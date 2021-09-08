package com.mobdeve.s14.group4;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserDatabase {
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private RecipeDatabase recipeDatabase;

    public UserDatabase(){
        this.auth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance();
        this.databaseReference = this.database.getReference("users");
        this.recipeDatabase = new RecipeDatabase();
    }

    public void getUser(String userId, final CallbackListener listener){
        this.databaseReference.child(userId).addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 FirebaseUser firebaseUser = dataSnapshot.getValue(FirebaseUser.class);
                 User user = new User(firebaseUser);

                 listener.onSuccess(user);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                listener.onFailure();
                Log.w("FAIL TAG", "Failed to read value.", error.toException());
            }
        });


    }

    public void addUser(User user){
        String key = this.databaseReference.push().getKey();

        user.setUserId(key);

        this.databaseReference.child(key).setValue(user.getFirebaseUser());
    }

    public void addGoogleUser(User user){
        this.databaseReference.child(user.getUserId()).setValue(user.getFirebaseUser());
    }

    public void updateUser(User user){

    }

    public void updateUserRecipes(String userId, ArrayList<String> recipeList){
        this.databaseReference.child(userId)
                .child("userRecipesCount")
                .setValue(recipeList.size());

        this.databaseReference.child(userId)
                .child("userRecipesList")
                .setValue(recipeList);
    }

    public void removeUserRecipe(User user, Recipe recipe){

    }
}
