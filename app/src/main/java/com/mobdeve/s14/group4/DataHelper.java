package com.mobdeve.s14.group4;

import java.util.ArrayList;

public class DataHelper {

    public ArrayList<Recipe> initFood(){
        ArrayList<Recipe> foodList = new ArrayList<>();

        int[] foodPics = {
                R.drawable.popular1,
                R.drawable.popular2,
                R.drawable.popular3
        };
        String[] foodName ={
                "Food 1",
                "Nomnomnom",
                "Lorem lorem lorem"
        };
        Integer[] foodFave = {
                23,
                35,
                123
        };

        for(int i = 0; i < 3; i++){
            foodList.add(new Recipe(foodPics[i], foodName[i]));
        }

        return foodList;
    }

}
