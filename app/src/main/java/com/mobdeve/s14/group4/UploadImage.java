package com.mobdeve.s14.group4;

public class UploadImage {

    private String mName;
    private String mImageUrl;

    public UploadImage(){
        //empty constructor needed
    }

    public UploadImage (String name, String imageUrl){
        if(name.trim().equals("")){
            mName = "No Name";
        }
        else{
            mName = name;
        }

        mImageUrl = imageUrl;
    }

    public String getName (){
        return mName;
    }

    public void setName (String name){
        mName = name;
    }

    public String getmImageUrl (){
        return mImageUrl;
    }

    public void setImageUrl (String url){
        mImageUrl = url;
    }
}
