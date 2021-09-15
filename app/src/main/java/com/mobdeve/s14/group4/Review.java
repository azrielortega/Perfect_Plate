package com.mobdeve.s14.group4;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Review {
    private String reviewId;
    private String comment;
    private float rating;
    private String contributorId;
    private String recipeId;
    private String contribName;


    public Review(String contributorId, float rating, String comment, String recipeId){
       this.contributorId = contributorId;
       this.rating = rating;
       this.comment = comment;
       this.recipeId = recipeId;
    }

    public Review(String reviewId, String contributorId, float rating, String comment, String recipeId){
        this.reviewId = reviewId;
        this.contributorId = contributorId;
        this.rating = rating;
        this.comment = comment;
        this.recipeId = recipeId;
        this.contribName = contribName;
    }

    public String getId(){ return this.reviewId; }

    public String getComment(){
        return this.comment;
    }

    public float getRating(){
        return this.rating;
    }

    public String getContributorId(){
        return this.contributorId;
    }

    public void setId(String id) { this.reviewId = id; }

    public String getRecipeId(){
        return this.recipeId;
    }

    public String getContribName() {
        return contribName;
    }
}
