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

        Double[] foodRating = {
                4.3,
                5.0,
                3.8
        };

        Integer[] contributorPic = {
                R.drawable.person1,
                R.drawable.person2,
                R.drawable.person3
        };

        String[] contributorName = {
                "Taylor Swift",
                "Ariana Grande",
                "Jennie Kim"
        };
        String[] description = {
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc eleifend lorem vel lorem finibus, nec auctor magna dictum. Vivamus id diam purus. Aliquam tristique nisl sit amet elit pellentesque, a mattis sem ornare. Sed sagittis mi ac urna aliquam, congue venenatis justo vehicula.",
                "Pellentesque congue nulla nec orci sollicitudin finibus. Vivamus vulputate malesuada sapien ac faucibus. Ut vitae pellentesque arcu.",
                ""
        };
        Integer[] reviewCount = {
                123,
                456,
                4500
        };

        for(int i = 0; i < 3; i++){
            foodList.add(new Recipe(foodPics[i], foodName[i], foodFave[i], foodRating[i], contributorPic[i], contributorName[i], description[i], reviewCount[i]));
        }

        return foodList;
    }

}
