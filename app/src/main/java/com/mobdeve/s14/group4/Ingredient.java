package com.mobdeve.s14.group4;

public class Ingredient {
    private String ingredientId;
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

    public double getQuantity(){
        return this.quantity;
    }

    public String getUnits(){
        return this.units;
    }

    public String getIngredientName(){
        return this.ingredientName;
    }
}