package com.mobdeve.s14.group4;

import java.util.ArrayList;

public class Recipe {
    private String recipeId;
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

    public void addIngredient(Ingredient ingredient){
        this.ingredientsList.add(ingredient);
    }

    public void removeIngredient(String ingredientId){
        int size = this.getIngredientsCount();
        int remove_index = -1;
        for (int i = 0; i < size; i++){
            if (ingredientId.equals(this.ingredientsList.get(i).getIngredientId())){
                remove_index = i;
            }
        }

        if (remove_index != -1){
            this.ingredientsList.remove(remove_index);
            //remove also from database
        }
    }

    public ArrayList<Ingredient> getIngredientsList(){
        return this.ingredientsList;
    }

    public int getIngredientsCount(){ return this.ingredientsList.size();}


}


