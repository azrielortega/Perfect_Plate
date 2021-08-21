package com.mobdeve.s14.group4;

public class Recipe {
    private int recipePic;
    private String recipeName;

    public Recipe(int p, String n){
        this.recipePic = p;
        this.recipeName = n;
    }

    public int getFoodPic(){
        return this.recipePic;
    }

    public String getFoodName(){
        return this.recipeName;
    }
}

