package com.mobdeve.s14.group4;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;

import java.io.Serializable;

public class Review {
    private String reviewId;
    private String comment;
    private double rating;
    private String recipeId;

    private User contributor;
    private UploadImage image;

    public Review(){
    }

    public Review(User contributor, double rating, String comment, String recipeId){
        this.contributor = contributor;
        this.rating = rating;
        this.comment = comment;
        this.recipeId = recipeId;
    }

    public Review(User contributor, double rating, String comment, String recipeId, UploadImage image){
        this.contributor = contributor;
        this.rating = rating;
        this.comment = comment;
        this.recipeId = recipeId;
        this.image = image;
        System.out.println("REVIEWPIC1 " + image.getmImageUrl());
    }

    public Review(String reviewId, String contributorId, double rating, String comment, String recipeId){
        this.reviewId = reviewId;
        setContributorId(contributorId);
        this.rating = rating;
        this.comment = comment;
        this.recipeId = recipeId;
        this.image = null;
    }

    public Review(String reviewId, String contributorId, double rating, String comment, String recipeId, UploadImage image){
        this.reviewId = reviewId;
        setContributorId(contributorId);
        this.rating = rating;
        this.comment = comment;
        this.recipeId = recipeId;
        System.out.println("REVIEWPIC2 " + image.getmImageUrl());
        this.image = image;
    }

    public void setUploadImage (UploadImage ui){
        this.image = ui;
    }

    public UploadImage getUploadImage (){
        return this.image;
    }

    public String getId(){ return this.reviewId; }

    public String getComment(){
        return this.comment;
    }

    public double getRating(){
        return this.rating;
    }

    public String getContributorId(){
        return this.contributor.getUserId();
    }

    public String getRecipeId(){
        return this.recipeId;
    }

    public String getContributorName() {
        return this.contributor.getFullName();
    }

    public void setId(String id) { this.reviewId = id; }

    public void setComment(String comment){
        this.comment = comment;
    }

    public void setRating(double rating){
        this.rating = rating;
    }

    public void setContributorId(String id){
        this.contributor = DataHelper.userDatabase.findUser(id);
    }

    public void setRecipeId(String id){
        this.recipeId = id;
    }
}
