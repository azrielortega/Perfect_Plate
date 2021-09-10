package com.mobdeve.s14.group4;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class UserDatabase {
    private final DatabaseReference databaseReference;
    private final RecipeDatabase recipeDatabase;

    public UserDatabase(){
//        this.auth = FirebaseAuth.getInstance();
        //    private FirebaseAuth auth;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        this.databaseReference = database.getReference("users");
        this.recipeDatabase = new RecipeDatabase();
    }

    /**
     * Gets user details from database. Returns details in a callbacklistener.
     * */
    public void getFirebaseUser(String userId, final CallbackListener listener){
        this.databaseReference.child(userId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    FirebaseUser firebaseUser = dataSnapshot.getValue(FirebaseUser.class);
                    listener.onSuccess(firebaseUser);
                } else {
                    listener.onFailure();
                }
            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {
                // Failed to read value
                Log.w("FAIL TAG", "User not found.", error.toException());
            }
        });
    }

    public void getUser(String userId, final CallbackListener listener){
        getFirebaseUser(userId, new CallbackListener() {
            @Override
            public void onSuccess(Object o) {
                FirebaseUser firebaseUser = (FirebaseUser) o;
                User user = new User(firebaseUser);
                listener.onSuccess(user);
            }

            @Override
            public void onFailure() {
                listener.onFailure();
            }
        });
    }

    public void getGoogleUser(String googleId, final CallbackListener listener){
        this.databaseReference.orderByChild("googleId").equalTo(googleId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (!snapshot.exists()){
                    listener.onFailure();
                    Log.d("FAILURE", "user with google id " + googleId + " does not exist");
                }
                else{
                    Log.d("SUCCESS", "found user with googleId: " + googleId);
                    listener.onSuccess(snapshot);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Log.e("CANCELLED", "failed to get google user " + error);
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

    /**
     * Updates user values based on non-null and non-empty variables
     * Does not update recipe or ingredient details
     * @param userId   id of user to update
     * @param newUser  new details to be updated
     * */
    public void updateUser(String userId, User newUser){
        getFirebaseUser(userId, new CallbackListener() {
            @Override
            public void onSuccess(Object o) {
                FirebaseUser user = (FirebaseUser) o;

                //if null or empty, update


                databaseReference.child(user.getUserId()).setValue(user);
            }

            @Override
            public void onFailure() {
                Log.d("FAILED TO UPDATE", "Failed to update user: " + userId);
            }
        });
    }

    /**
     * Adds user recipe under user. Assigns userId as contributorId of recipe and adds recipe Id
     * to the user's list of recipes in the database. Adds recipe to the database.
     *
     * @param userId    user id of recipe creator
     * @param recipe    recipe to be added to the database
     * */
    public void addUserRecipe(String userId, Recipe recipe){
        getFirebaseUser(userId, new CallbackListener() {
            @Override
            public void onSuccess(Object o) {
                FirebaseUser user = (FirebaseUser) o;

                //add to recipe database and get id
                //link recipe to user
                recipe.setContributorId(userId);
                String recipeId = recipeDatabase.addRecipe(recipe);

                //add to dbs
                user.addUserRecipeId(recipeId);
                updateUserRecipes(userId, user.getUserRecipesList(), user.getUserRecipesCount());
            }

            @Override
            public void onFailure() {
                Log.d("FAILED TO ADD",
                        "Failed to add recipe: " + recipe.getId() + " for: " + userId);
            }
        });
    }

    /**
     * Removes user recipe from user's list and from the recipe db
     * */
    public void removeUserRecipe(String userId, String recipeId){
        getFirebaseUser(userId, new CallbackListener() {
            @Override
            public void onSuccess(Object o) {
                FirebaseUser user = (FirebaseUser) o;

                //remove from recipe db
                recipeDatabase.deleteRecipe(recipeId);

                //remove from user db
                user.removeUserRecipeId(recipeId);
                updateUserRecipes(userId, user.getUserRecipesList(), user.getUserRecipesCount());
            }

            @Override
            public void onFailure() {
                Log.d("FAILED TO REMOVE",
                        "Failed to remove recipe: " + recipeId + " for: " + userId);
            }
        });
    }

    public void updateUserRecipes(String userId, ArrayList<String> recipeList, int newSize){
        this.databaseReference.child(userId)
                .child("userRecipesCount")
                .setValue(newSize);

        this.databaseReference.child(userId)
                .child("userRecipesList")
                .setValue(recipeList);
    }

    /**
     * Adds fave recipe under user. Increase fave count of recipe
     *
     * @param userId    user id of recipe creator
     * @param recipeId  recipe id of favorite recipe
     * */
    public void addFaveRecipe(String userId, String recipeId){
        getFirebaseUser(userId, new CallbackListener() {
            @Override
            public void onSuccess(Object o) {
                FirebaseUser user = (FirebaseUser) o;

                //add to db
                user.addFaveRecipeId(recipeId);
                updateFaveRecipes(userId, user.getFaveRecipesList(), user.getFaveRecipesCount());
            }

            @Override
            public void onFailure() {
                Log.d("FAILED TO ADD",
                        "Failed to add fave recipe: " + recipeId + " for: " + userId);
            }
        });
    }

    /**
     * Removes fave recipe from user's list
     * */
    public void removeFaveRecipe(String userId, String recipeId){
        getFirebaseUser(userId, new CallbackListener() {
            @Override
            public void onSuccess(Object o) {
                FirebaseUser user = (FirebaseUser) o;

                //remove from user db
                user.removeFaveRecipeId(recipeId);
                updateFaveRecipes(userId, user.getFaveRecipesList(), user.getFaveRecipesCount());
            }

            @Override
            public void onFailure() {
                Log.d("FAILED TO REMOVE",
                        "Failed to remove fave recipe: " + recipeId + " for: " + userId);
            }
        });
    }

    public void updateFaveRecipes(String userId, ArrayList<String> recipeList, int newSize){
        this.databaseReference.child(userId)
                .child("faveRecipesCount")
                .setValue(newSize);

        this.databaseReference.child(userId)
                .child("faveRecipesList")
                .setValue(recipeList);
    }
}
