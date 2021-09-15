package com.mobdeve.s14.group4;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FirebaseRecipe {
    private String recipeId;

    private int recipePic;
    private UploadImage image;
    private String recipeName;
    private String description;

    private int faveCount;
    private double rating;
    private int reviewCount;

    private String contributorId;

    private int cookingTime;
    private int prepTime;
    private int servings;

    private String category;
    private String difficulty;

    private ArrayList<String> ingredientsList;
    private ArrayList<String> stepsList;
    private ArrayList<String> reviewArrayList;

    public FirebaseRecipe(){
        this.ingredientsList = new ArrayList<String>();
        this.reviewArrayList = new ArrayList<String>();
        this.stepsList = new ArrayList<String>();
    }

    //get from database
    public FirebaseRecipe(String id, int recipePic, String recipeName, int faveCount, double rating, String contributorId, String desc, int reviewCount, int cookingTime, int prepTime, int servings,
                  String category, String difficulty, ArrayList<String> ingredients, ArrayList<String> steps, UploadImage upload, ArrayList<String> reviews){
        this.recipeId = id;
        this.recipePic = recipePic;
        this.recipeName = recipeName;
        this.description = desc;

        this.contributorId = contributorId;

        this.faveCount = faveCount;
        this.rating = rating;
        this.reviewCount = reviewCount;

        this.ingredientsList = ingredients;
        this.stepsList = steps;
        this.reviewArrayList = reviews;

        this.cookingTime = cookingTime;
        this.prepTime = prepTime;
        this.servings = servings;

        this.category = category;
        this.difficulty = difficulty;

        this.image = upload;
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
    public ArrayList<String> getReviewArrayList(){
        return this.reviewArrayList;
    }

    public int getIngredientsCount(){
        return this.ingredientsList.size();
    }

    public ArrayList<String> getStepsList(){ return this.stepsList;}

    public int getPrepTime (){
        return this.prepTime;
    }

    public int getCookingTime (){
        return this.cookingTime;
    }

    public int getServings (){
        return this.servings;
    }

    public String getDifficulty(){return this.difficulty;}

    public String getCategory () {return this.category;}

    public UploadImage getUploadImage (){
        return this.image;
    }

    public void setUploadImage (UploadImage ui){
        this.image = ui;
    }

    public void setDifficulty (String d) {this.difficulty = d;}

    public void setCategory (String c) {this.category = c;}

    public void setStepsList(ArrayList<String> list) { this.stepsList = list;}

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
    public void setReviewList(ArrayList<String> list){
        this.reviewArrayList = list;
    }

    public void setReviewCount(int reviewCount){
        this.reviewCount = reviewCount;
    }

    public void addIngredientId(String id){
        this.ingredientsList.add(id);
    }
    public  void addReviewId(String id){
        this.reviewArrayList.add(id);
    }

    public void setPrepTime (int pt){
        this.prepTime = pt;
    }

    public void setCookingTime (int ct){
        this.cookingTime = ct;
    }

    public void setServings (int s){
        this.servings = s;
    }
}
