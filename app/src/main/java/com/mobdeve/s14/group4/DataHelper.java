package com.mobdeve.s14.group4;

import android.content.Context;
import android.os.Handler;
import android.telecom.Call;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DataHelper {
    public static User user;

    public static ArrayList<User> allUsers;

    public static ArrayList<Recipe> allRecipes;
    public static int popularRecipesCount;
    public static ArrayList<Recipe> popularRecipes;

    public static ArrayList<Ingredient> allIngredients;

    public static ArrayList<Review> allReviews;

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
        popularRecipes = new ArrayList<Recipe>();
        allReviews = new ArrayList<Review>();


        userDatabase = new UserDatabase();
        recipeDatabase = new RecipeDatabase();
        ingredientDatabase = new IngredientDatabase();
        reviewDatabase = new ReviewDatabase();

        final CallbackListener recipesListener = new CallbackListener() {
            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onFailure() {

            }
        };

        refreshDatabase(recipesListener);
    }

    public static void refreshDatabase(final CallbackListener recipesListener){
        initAllIngredients();
        initRecipes(recipesListener);
        initAllReviews();
        initAllUsers();
    }

    public static void asyncRefreshDatabase(Context context, final CallbackListener recipesListener){
        Handler handler = new Handler(context.getMainLooper());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                DataHelper.refreshDatabase(recipesListener);
            }
        };

        handler.post(runnable);
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

    public static void initAllUsers(){
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

    public static void initRecipes(final CallbackListener listener){
        recipeDatabase.getAllRecipes(new CallbackListener() {
            @Override
            public void onSuccess(Object o) {
                allRecipes = (ArrayList<Recipe>) o;
                updatePopularity();
                listener.onSuccess(allRecipes);
            }

            @Override
            public void onFailure() {
                listener.onFailure();
                Log.d("FAILURE", "Failed to get recipes");
            }
        });
    }

    public static void initAllIngredients(){
        ingredientDatabase.getAllIngredients(new CallbackListener() {
            @Override
            public void onSuccess(Object o) {
                allIngredients = (ArrayList<Ingredient>) o;
                Log.d("ALLING", String.valueOf(allIngredients.size()));
            }

            @Override
            public void onFailure() {
                Log.d("FAILURE", "Failed to get ingredients");
            }
        });
    }

    public static void initAllReviews(){
        reviewDatabase.getAllReviews(new CallbackListener() {
            @Override
            public void onSuccess(Object o) {
                allReviews = (ArrayList<Review>) o;
                Log.d("ALLREVIEWS", String.valueOf(allReviews.size()));
            }

            @Override
            public void onFailure() {
                Log.d("FAILURE", "Failed to get reviews");
            }
        });

    }

    public static void addRecipe(Recipe recipe){
        allRecipes.add(recipe);

        if (popularRecipesCount < 10){
            popularRecipesCount++;
            popularRecipes.add(recipe);
        }
    }

    public static void updatePopularity(){
        ArrayList<Recipe> newPopular = (ArrayList<Recipe>) allRecipes.clone();

        Collections.sort(newPopular, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe o1, Recipe o2) {
                int o1Fave = o1.getFaveCount();
                int o2Fave = o2.getFaveCount();

                if (o1Fave > o2Fave){
                    return -1;
                }
                else if (o1Fave < o2Fave){
                    return 1;
                }

                return 0;
            }
        });

        int recipeCount = allRecipes.size();
        popularRecipesCount = (recipeCount > 10)? 10 : recipeCount;

        popularRecipes = new ArrayList<>(newPopular.subList(0, popularRecipesCount));
    }

    public static void setGlobalUser(User u){
        user = u;
    }
}
