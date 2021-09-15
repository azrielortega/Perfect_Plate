package com.mobdeve.s14.group4;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DataHelper {
    public static User user;

    public static ArrayList<User> allUsers;
    public static ArrayList<Recipe> allRecipes;
    public static ArrayList<Recipe> popularRecipes;
    public static ArrayList<Ingredient> allIngredients;
    public static ArrayList<String> allReviews;

    public static UserDatabase userDatabase;
    public static RecipeDatabase recipeDatabase;
    public static IngredientDatabase ingredientDatabase;
    public static ReviewDatabase reviewDatabase;

    public static void initUser(String uid){
        loadUser(uid);
    }

    public static void initDatabase(){
        allUsers = new ArrayList<User>();
        allRecipes = new ArrayList<Recipe>();

        userDatabase = new UserDatabase();
        recipeDatabase = new RecipeDatabase();
        ingredientDatabase = new IngredientDatabase();
        reviewDatabase = new ReviewDatabase();

        loadAllReviews();
        loadAllIngredients();
        loadRecipes();
        loadAllUsers();
    }

    public static void loadUser(String uid){
        userDatabase.getUser(uid, new CallbackListener() {
            @Override
            public void onSuccess(Object o) {
                setGlobalUser((User) o);
            }

            @Override
            public void onFailure() {
                Log.d("MISSING", "User" + uid + " not found");
            }
        });
    }

    public static void loadAllUsers(){
        userDatabase.getAllUsers(new CallbackListener() {
            @Override
            public void onSuccess(Object o) {
                allUsers = (ArrayList<User>) o;
            }

            @Override
            public void onFailure() {
                Log.d("FAILURE", "Failed to get users");
            }
        });
    }

    public static void loadRecipes(){
        recipeDatabase.getAllRecipes(new CallbackListener() {
            @Override
            public void onSuccess(Object o) {
                allRecipes = (ArrayList<Recipe>) o;
                popularRecipes = (ArrayList<Recipe>) allRecipes.clone();

                Collections.sort(popularRecipes, new Comparator<Recipe>() {
                    @Override
                    public int compare(Recipe o1, Recipe o2) {
                        double o1Fave = o1.getFaveCount();
                        double o2Fave = o2.getFaveCount();
                        if (o1Fave > o2Fave){
                            return 1;
                        }
                        else if (o1Fave < o2Fave){
                            return -1;
                        }

                        return 0;
                    }
                });

                int recipeCount = allRecipes.size();
                int toIndex = (recipeCount > 10)? 10 : recipeCount;
                popularRecipes = new ArrayList<>(popularRecipes.subList(0, recipeCount));
            }

            @Override
            public void onFailure() {
                Log.d("FAILURE", "Failed to get recipes");
            }
        });
    }

    public static void loadAllIngredients(){
        ingredientDatabase.getAllIngredients(new CallbackListener() {
            @Override
            public void onSuccess(Object o) {
                allIngredients = (ArrayList<Ingredient>) o;
            }

            @Override
            public void onFailure() {
                Log.d("FAILURE", "Failed to get ingredients");
            }
        });
    }

    public static void loadAllReviews(){


    }

    public static void setGlobalUser(User u){
        user = u;
    }
}
