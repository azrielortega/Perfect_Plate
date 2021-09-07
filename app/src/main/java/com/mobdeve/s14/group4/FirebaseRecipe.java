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

    private int contributorPic;
    private String contributorName;

    private ArrayList<String> ingredientsList;

    public FirebaseRecipe(){
        this.ingredientsList = new ArrayList<String>();
    }

    public FirebaseRecipe(int recipePic, String recipeName, int faveCount, double rating, int contributorPic, String contributorName, String description, int reviewCount){
        this.recipePic = recipePic;
        this.recipeName = recipeName;
        this.faveCount = faveCount;
        this.rating = rating;
        this.contributorPic = contributorPic;
        this.contributorName = contributorName;
        this.description = description;
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

    public int getContributorPic(){
        return this.contributorPic;
    }

    public String getContributorName(){
        return this.contributorName;
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

    public void setContributorPic(int pic){
        this.contributorPic = pic;
    }

    public void setContributorName(String name){
        this.contributorName = name;
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
