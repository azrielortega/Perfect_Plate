package com.mobdeve.s14.group4;

public class Ingredient {
    private String ingredientId;
//    private String recipeId;
    private double quantity;
    private String units;
    private String ingredientName;

    public Ingredient(double quantity, String units, String ingredientName){
        this.quantity = quantity;
        this.units = units;
        this.ingredientName = ingredientName;
    }

    public Ingredient(String ingredientId, double quantity, String units, String ingredientName){
        this.ingredientId = ingredientId;
        this.quantity = quantity;
        this.units = units;
        this.ingredientName = ingredientName;
    }

    public String getId(){ return this.ingredientId; }

//    public String getRecipeId(){
//        return this.recipeId;
//    }

    public double getQuantity(){
        return this.quantity;
    }

    public String getUnits(){
        return this.units;
    }

    public String getIngredientName(){
        return this.ingredientName;
    }

    public void setId(String id) { this.ingredientId = id; }

//    public void setRecipeId(String id){
//        this.recipeId = id;
//    }
}
