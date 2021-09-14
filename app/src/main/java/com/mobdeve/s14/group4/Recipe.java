package com.mobdeve.s14.group4;

import android.util.Log;

import java.util.ArrayList;

public class Recipe extends FirebaseRecipe
{
    private ArrayList<Ingredient> ingredientDetailsList;

    public Recipe(FirebaseRecipe firebaseRecipe){
        setId(firebaseRecipe.getId());

        setRecipePic(firebaseRecipe.getRecipePic());
        setRecipeName(firebaseRecipe.getRecipeName());
        setDescription(firebaseRecipe.getDescription());

        setFaveCount(firebaseRecipe.getFaveCount());
        setRating(firebaseRecipe.getRating());
        setReviewCount(firebaseRecipe.getReviewCount());

        setContributorId(firebaseRecipe.getContributorId());

        setCookingTime(firebaseRecipe.getCookingTime());
        setPrepTime(firebaseRecipe.getPrepTime());
        setServings(firebaseRecipe.getServings());

        setCategory(firebaseRecipe.getCategory());
        setDifficulty(firebaseRecipe.getDifficulty());

        setIngredientsList(firebaseRecipe.getIngredientsList());
        setStepsList(firebaseRecipe.getStepsList());

        this.ingredientDetailsList = new IngredientDatabase().findIngredients(firebaseRecipe.getIngredientsList());
    }

    //with id from database
    public Recipe(String id, int recipePic, String recipeName, int foodFave, double rating, String contributorId, String desc, int reviewCount, int cookingTime, int prepTime, int servings,
                  String category, String difficulty, ArrayList<String> ingredientList, ArrayList<String> stepsList){
        super(id, recipePic, recipeName, foodFave, rating, contributorId, desc, reviewCount, cookingTime, prepTime, servings, category, difficulty, ingredientList, stepsList);

        //TODO: initialize ingredients list
    }

    public Recipe() {
    }

    //TODO: contributor pic
    public int getContributorPic(){
        int contributorPic = R.drawable.person_gray;
        return contributorPic;
    }

    //TODO: contributor name
    public String getContributorName(){
        //get username
        String contributorName = "John Doe";
        return contributorName;
    }

    public String getRatingString() {
        return String.valueOf(getRating());
    }

    public ArrayList<Ingredient> getIngredientDetailsList(){
        return this.ingredientDetailsList;
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
        //get ingredient from db
        String id = new IngredientDatabase().addIngredient(ingredient);

        //add ingredient to both lists
        this.ingredientDetailsList.add(ingredient);
        addIngredientId(id);
    }

}