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
                "Egg Toast",
                "Fruity French Toast",
                "Meatballs"
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

        String[] contributorId = {
                "1",
                "2",
                "3"
        };
        String[] description = {
                "This Egg Toast is so easy to make, filling, and so incredibly tasty, that it is going to make your morning better. Guaranteed. ",
                "With so many toppings and breads there is an endless variety of French toast you can make. Cinnamon French toast, Brioche French toast, stuffed French toast and even a French toast that tastes like a Starbucks caramel macchiato. If you are feeling adventurous, you can even make French toast in a mug! ",
                "I never knew how to make good meatballs until I found this recipe. I normally make mine with just ground beef and they still taste great. I've used the combination of pork, beef and veal and they are equally good. Definitely use fresh bread crumbs and freshly grate your cheese instead of using the canned variety...it really does make a difference."
        };
        Integer[] reviewCount = {
                123,
                456,
                4500
        };

        for(int i = 0; i < 3; i++){
            foodList.add(new Recipe(foodPics[i], foodName[i], foodFave[i], foodRating[i], contributorId[i], description[i], reviewCount[i], 0, 0, 0));
        }

        return foodList;
    }

}
