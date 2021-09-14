package com.mobdeve.s14.group4;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DataHelper {
    public static User user;
    public static ArrayList<Recipe> allRecipes;
    public static ArrayList<Recipe> sortedRecipes;

    public static void loadUser(String uid){
        new UserDatabase().getUser(uid, new CallbackListener() {
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

    public static void loadRecipes(){
        new RecipeDatabase().getAllRecipes(new CallbackListener() {
            @Override
            public void onSuccess(Object o) {
                allRecipes = (ArrayList<Recipe>) o;
                sortedRecipes = (ArrayList<Recipe>) allRecipes.clone();
                Collections.sort(sortedRecipes, new Comparator<Recipe>() {
                    @Override
                    public int compare(Recipe o1, Recipe o2) {
                        double o1Rating = o1.getRating();
                        double o2Rating = o2.getRating();
                        if (o1Rating > o2Rating){
                            return 1;
                        }
                        else if (o1Rating < o2Rating){
                            return -1;
                        }

                        return 0;
                    }
                });
            }

            @Override
            public void onFailure() {
                Log.d("FAILURE", "Failed to getRecipes");
            }
        });
    }

    public static void setGlobalUser(User u){
        user = u;
    }
}
