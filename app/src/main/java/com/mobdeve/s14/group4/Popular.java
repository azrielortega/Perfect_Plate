package com.mobdeve.s14.group4;

public class Popular {
    private int popularPic;
    private String popularName;

    public Popular(int p, String n){
        this.popularPic = p;
        this.popularName = n;
    }

    public int getPopularPic(){
        return this.popularPic;
    }

    public String getPopularName(){
        return this.popularName;
    }
}

