package com.mobdeve.s14.group4;

import android.util.Log;

import java.util.ArrayList;

public class Recipe extends FirebaseRecipe
{
    public Recipe(int recipePic, String recipeName, int foodFave, double rating, String contributorId, String desc, int reviewCount, int cookingTime, int prepTime, int servings,
                  String category, String difficulty){
        super(recipePic, recipeName, foodFave, rating, contributorId, desc, reviewCount, cookingTime, prepTime, servings, category, difficulty);
    }
    //with id from database
    public Recipe(String id, int recipePic, String recipeName, int foodFave, double rating, String contributorId, String desc, int reviewCount, int cookingTime, int prepTime, int servings,
                  String category, String difficulty){
        super(id, recipePic, recipeName, foodFave, rating, contributorId, desc, reviewCount, cookingTime, prepTime, servings, category, difficulty);
    }

    public Recipe() {

    }

    public int getContributorPic(){
        int contributorPic = R.drawable.person_gray;
        return contributorPic;
    }

    public String getContributorName(){
        //get username
        String contributorName = "John Doe";
        return contributorName;
    }

    public String getRatingString() {
        return String.valueOf(getRating());
    }

    public FirebaseRecipe getFirebaseRecipe(){
        FirebaseRecipe firebaseRecipe = new FirebaseRecipe();

        firebaseRecipe.setId(getId());

        firebaseRecipe.setRecipePic(getRecipePic());
        firebaseRecipe.setRecipeName(getRecipeName());
        firebaseRecipe.setDescription(getDescription());

        firebaseRecipe.setContributorId(getContributorId());

        firebaseRecipe.setFaveCount(getFaveCount());
        firebaseRecipe.setRating(getRating());
        firebaseRecipe.setReviewCount(getReviewCount());

        firebaseRecipe.setIngredientsList(getIngredientsList());
        firebaseRecipe.setStepsList(getStepsList());

        firebaseRecipe.setCookingTime(getCookingTime());
        firebaseRecipe.setPrepTime(getPrepTime());
        firebaseRecipe.setServings(getServings());

        firebaseRecipe.setCategory(getCategory());
        firebaseRecipe.setDifficulty(getDifficulty());

        Log.d("myTag", String.valueOf(firebaseRecipe));

        return firebaseRecipe;
    }

    public void addIngredient(Ingredient ingredient){
        RecipeDatabase recipeDatabase = new RecipeDatabase();

        String id = recipeDatabase.addIngredient(ingredient);

        addIngredientId(id);
    }

    public void addStepsList(ArrayList<String> steps){
        setStepsList(steps);
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