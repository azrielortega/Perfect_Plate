package com.mobdeve.s14.group4;

import java.util.ArrayList;

public class Recipe {
    private int recipePic;
    private String recipeName;
    private int foodFave;
    private double rating;
    private int contributorPic;
    private String contributorName;
    private String desc;
    private int reviewCount;
    private ArrayList<Ingredient> ingredientsList;

    public Recipe(int recipePic, String recipeName, int foodFave, double rating, int contributorPic, String contributorName, String desc, int reviewCount){
        this.recipePic = recipePic;
        this.recipeName = recipeName;
        this.foodFave = foodFave;
        this.rating = rating;
        this.contributorPic = contributorPic;
        this.contributorName = contributorName;
        this.desc = desc;
        this.reviewCount = reviewCount;
        this.ingredientsList = new ArrayList<Ingredient>();
    }

    public int getFoodPic(){
        return this.recipePic;
    }

    public String getFoodName(){
        return this.recipeName;
    }

    public int getFoodFave(){
        return this.foodFave;
    }

    public double getRating(){ return this.rating; }

    public String getRatingString() { return String.valueOf(this.rating); }

    public int getContributorPic(){
        return this.contributorPic;
    }

    public String getContributorName(){
        return this.contributorName;
    }

    public String getDesc(){
        return this.desc;
    }

    public int getReviewCount(){
            return this.reviewCount;
    }

    public void addIngredient(double quantity, String units, String ingredientName){
        this.ingredientsList.add(new Ingredient(quantity, units, ingredientName));
    }

    public void addIngredient(String id, double quantity, String units, String ingredientName){
        this.ingredientsList.add(new Ingredient(id, quantity, units, ingredientName));
    }

    public ArrayList<Ingredient> getIngredientsList(){
        return this.ingredientsList;
    }
}


