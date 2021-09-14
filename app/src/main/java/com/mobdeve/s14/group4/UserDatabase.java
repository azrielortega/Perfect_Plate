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
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        this.databaseReference = database.getReference("users");
        this.recipeDatabase = new RecipeDatabase();
    }

    /**
     * Gets user details from database. Returns details in a callbacklistener.
     * */
    public void getFirebaseUser(String userId, final CallbackListener listener){
        this.databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
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
                if (snapshot.exists()){
                    Log.d("SUCCESS", "found user with googleId: " + googleId);
                    for (DataSnapshot userSnapshot : snapshot.getChildren()){
                        FirebaseUser firebaseUser = userSnapshot.getValue(FirebaseUser.class);
                        listener.onSuccess(firebaseUser);
                    }
                }
                else{
                    Log.d("FAILURE", "user with google id " + googleId + " does not exist");
                    listener.onFailure();
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
     *
     * @param newUser  new details to be updated
     * */
    public void updateCurrentUser(User newUser){
        User user = DataHelper.user;

        String username = newUser.getUsername().trim();
        if (!username.isEmpty()){
            user.setUsername(username);
        }

        String firstName = newUser.getFirstName().trim();
        if (!firstName.isEmpty()){
            user.setFirstName(firstName);
        }

        String lastName = newUser.getLastName().trim();
        if (!lastName.isEmpty()){
            user.setLastName(lastName);
        }

        String email = newUser.getEmail().trim();
        if (!email.isEmpty()){
            user.setEmail(email);
        }

        String password = newUser.getPassword().trim();
        if (!password.isEmpty()){
            user.setPassword(password);
        }

        //TODO: set birthday

        databaseReference.child(user.getUserId()).setValue(user);
    }

    /**
     * Adds user recipe under user. Assigns userId as contributorId of recipe and adds recipe Id
     * to the user's list of recipes in the database. Adds recipe to the database.
     *
     * @param recipe    recipe to be added to the database
     * */
    public void addUserRecipe(Recipe recipe){
        User user = DataHelper.user;
        recipe.setContributorId(user.getUserId());
        String recipeId = recipeDatabase.addRecipe(recipe);

        user.addUserRecipeId(recipeId);
        updateUserRecipes(user.getUserId(), user.getUserRecipesList(), user.getUserRecipesCount());
    }

    /**
     * Removes user recipe from user's list and from the recipe db
     * */
    public void removeUserRecipe(String recipeId){
        //remove from recipe db
        recipeDatabase.deleteRecipe(recipeId); //TODO: delete from DataHelper

        //remove from user db
        User user = DataHelper.user;
        user.removeUserRecipe(recipeId);
        updateUserRecipes(user.getUserId(), user.getUserRecipesList(), user.getUserRecipesCount());
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
     * @param recipe  recipe of favorite recipe
     * */
    public void addFaveRecipe(Recipe recipe){
        User user = DataHelper.user;
        user.addFaveRecipe(recipe);
        updateFaveRecipes(user.getUserId(), user.getFaveRecipesList(), user.getFaveRecipesCount());
    }

    /**
     * Removes fave recipe from user's list
     * */
    public void removeFaveRecipe(String recipeId){
        User user = DataHelper.user;
        user.removeFaveRecipeId(recipeId);
        updateFaveRecipes(user.getUserId(), user.getFaveRecipesList(), user.getFaveRecipesCount());
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
