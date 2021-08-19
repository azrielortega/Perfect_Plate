package com.mobdeve.s14.group4;

import java.util.ArrayList;

public class DataHelper {
    private ArrayList<Integer> popularPics;

    public ArrayList<Popular> initPopular(){
        ArrayList<Popular> popularList = new ArrayList<>();

        int[] popularPics = {
                R.drawable.popular1,
                R.drawable.popular2,
                R.drawable.popular3
        };
        String[] popularName ={
                "Food 1",
                "Nomnomnom",
                "Lorem lorem lorem"
        };

        for(int i = 0; i < 3; i++){
            popularList.add(new Popular(popularPics[i],
                    popularName[i]
            ));
        }

        return popularList;
    }

}
