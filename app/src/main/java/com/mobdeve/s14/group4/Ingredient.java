package com.mobdeve.s14.group4;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private String ingredientId;
    private double quantity;
    private String units;
    private String ingredientName;

    public Ingredient(){}

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

    public void setQuantity(double quantity){
        this.quantity = quantity;
    }

    public void setUnits(String units){
        this.units = units;
    }

    public void setIngredientName(String name){
        this.ingredientName = name;
    }
}
