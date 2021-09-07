package com.mobdeve.s14.group4;

import java.util.ArrayList;

public class FirebaseRecipe {
    private String recipeId;
    private int recipePic;
    private String recipeName;
    private String description;

    private int faveCount;
    private double rating;
    private int reviewCount;

    private String contributorId;

    private ArrayList<String> ingredientsList;

    public FirebaseRecipe(){
        this.ingredientsList = new ArrayList<String>();
    }

    public FirebaseRecipe(int recipePic, String recipeName, int faveCount, double rating, String contributorId, String description, int reviewCount){
        this.recipePic = recipePic;
        this.recipeName = recipeName;
        this.description = description;

        this.contributorId = contributorId;

        this.faveCount = faveCount;
        this.rating = rating;
        this.reviewCount = reviewCount;

        this.ingredientsList = new ArrayList<String>();
    }

    public String getId(){
        return this.recipeId;
    }

    public int getRecipePic(){
        return this.recipePic;
    }

    public String getRecipeName(){
        return this.recipeName;
    }

    public int getFaveCount(){
        return this.faveCount;
    }

    public double getRating(){
        return this.rating;
    }

    public String getContributorId(){
        return this.contributorId;
    }

    public String getDescription(){
        return this.description;
    }

    public int getReviewCount(){
        return this.reviewCount;
    }

    public ArrayList<String> getIngredientsList(){
        return this.ingredientsList;
    }

    public int getIngredientsCount(){
        return this.ingredientsList.size();
    }

    public void setId(String id){
        this.recipeId = id;
    }

    public void setRecipePic(int pic){
        this.recipePic = pic;
    }

    public void setRecipeName(String recipeName){
        this.recipeName = recipeName;
    }

    public void setFaveCount(int faveCount){
        this.faveCount = faveCount;
    }

    public void setRating(double rating){
        this.rating = rating;
    }

    public void setContributorId(String id){
        this.contributorId = id;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setIngredientsList(ArrayList<String> list){
        this.ingredientsList = list;
    }

    public void setReviewCount(int reviewCount){
        this.reviewCount = reviewCount;
    }

    public void addIngredientId(String id){
        this.ingredientsList.add(id);
    }
}