package com.mobdeve.s14.group4;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Recipe extends FirebaseRecipe
{
    public Recipe(int recipePic, String recipeName, int foodFave, double rating, int contributorPic, String contributorName, String desc, int reviewCount){
        super(recipePic, recipeName, foodFave, rating, contributorPic, contributorName, desc, reviewCount);
    }

    public String getRatingString() {
        return String.valueOf(getRating());
    }

    public FirebaseRecipe getFirebaseRecipe(){
        FirebaseRecipe firebaseRecipe = new FirebaseRecipe();

        firebaseRecipe.setId(getId());

        firebaseRecipe.setRecipePic(getFoodPic());
        firebaseRecipe.setFoodName(getFoodName());
        firebaseRecipe.setDescription(getDescription());

        firebaseRecipe.setContributorPic(getContributorPic());
        firebaseRecipe.setContributorName(getContributorName());

        firebaseRecipe.setFoodFave(getFoodFave());
        firebaseRecipe.setRating(getRating());
        firebaseRecipe.setReviewCount(getReviewCount());

        firebaseRecipe.setIngredientsList(getIngredientsList());

        return firebaseRecipe;
    }

    public void addIngredient(Ingredient ingredient){
        RecipeDatabase recipeDatabase = new RecipeDatabase();

        String id = recipeDatabase.addIngredient(ingredient);

        addIngredientId(id);
    }

//    public void removeIngredient(int ingredientId){
//        int size = this.getIngredientsCount();
//        int remove_index = -1;
//
//        for (int i = 0; i < size; i++){
////            if (ingredientId == this.ingredientsList.get(i).getId()){
////                remove_index = i;
////            }
//        }
//
//        if (remove_index != -1){
////            this.ingredientsList.remove(remove_index);
//            //remove also from database
//            //update recipe
//        }
//    }
//
//    public void updateRecipe(){
//        RecipeDatabase recipeDatabase = new RecipeDatabase();
//
//        recipeDatabase.updateRecipe(this);
//    }
}